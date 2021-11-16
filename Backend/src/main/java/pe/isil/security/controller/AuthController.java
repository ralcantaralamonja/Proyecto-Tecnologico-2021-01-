package pe.isil.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.isil.reservas.dto.Mensaje;
import pe.isil.security.dto.JwtDto;
import pe.isil.security.dto.LoginUsuario;
import pe.isil.security.dto.NuevoUsuario;
import pe.isil.security.dto.UsuarioDto;
import pe.isil.security.enums.RolNombre;
import pe.isil.security.jwt.JwtProvider;
import pe.isil.security.model.entity.Rol;
import pe.isil.security.model.entity.Usuario;
import pe.isil.security.service.RolService;
import pe.isil.security.service.UserDetailsServiceImpl;
import pe.isil.security.service.UsuarioService;

import javax.validation.Valid;
import java.util.*;

//@EnableGlobalMethodSecurity
@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final UserDetailsServiceImpl userDetailsService;
    private final RolService rolService;
    private final JwtProvider jwtProvider;

    public AuthController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UsuarioService usuarioService, UserDetailsServiceImpl userDetailsService, RolService rolService, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.userDetailsService = userDetailsService;
        this.rolService = rolService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        System.out.println("bool " + usuarioService.existByUsername(loginUsuario.getUsername()));
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("los campos no pueden estar vacios"), HttpStatus.BAD_REQUEST);
        if (!usuarioService.existByUsername(loginUsuario.getUsername()))
            return new ResponseEntity(new Mensaje("Usuario no existe"), HttpStatus.NOT_FOUND);
        if (usuarioService.existByUsername(loginUsuario.getUsername())) {
            int estado = usuarioService.getByUsername(loginUsuario.getUsername()).get().getEstado();
            if (estado == 3) {
                return new ResponseEntity(new Mensaje("Usuario no existe"), HttpStatus.NOT_FOUND);
            } else {
                if (!passwordEncoder.matches(loginUsuario.getPassword(), usuarioService.getByUsername(loginUsuario.getUsername()).get().getPassword())) {
                    return new ResponseEntity(new Mensaje("Clave incorrecta"), HttpStatus.UNAUTHORIZED);
                }
                if (estado == 2) {
                    return new ResponseEntity(
                            new Mensaje("Su usuario no esta activo. Comuniquese con el administrador del sistema")
                            , HttpStatus.UNAUTHORIZED);
                }
            }
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @PostMapping("/users/registro-user/{username}")
    public ResponseEntity<?> registerUser(@Valid @RequestBody NuevoUsuario nuevoUsuario, @PathVariable String username, BindingResult bindingResult) {
        UserDetails details = userDetailsService.loadUserByUsername(username);
        if (details != null) {
            if (details.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("MANAGER"))) {
                return register(nuevoUsuario, bindingResult, "USER");
            }
        }
        return new ResponseEntity<>(new Mensaje("No autorizado"), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/users/registro-manager/{username}")
    public ResponseEntity<?> registerManager(@Valid @RequestBody NuevoUsuario nuevoUsuario, @PathVariable String username, BindingResult bindingResult) {
        UserDetails details = userDetailsService.loadUserByUsername(username);
        if (details != null) {
            if (details.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                return register(nuevoUsuario, bindingResult, "MANAGER");
            }
        }
        return new ResponseEntity<>(new Mensaje("No autorizado"), HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> register(NuevoUsuario nuevoUsuario, BindingResult bindingResult, String rol) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos incorrectos o correo invalido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existByUsername(nuevoUsuario.getUsername()))
            return new ResponseEntity(new Mensaje("Usuario ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existByCorreo(nuevoUsuario.getCorreo()))
            return new ResponseEntity(new Mensaje("Correo ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombres(), nuevoUsuario.getApellidos(),
                        nuevoUsuario.getUsername(), passwordEncoder.encode(nuevoUsuario.getPassword()),
                        nuevoUsuario.getCorreo());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.USER).get());
        if (rol.equalsIgnoreCase("MANAGER"))
            roles.add(rolService.getByRolNombre(RolNombre.MANAGER).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Se registro correctamente"), HttpStatus.CREATED);
    }

    @PutMapping("/users/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username, @RequestBody LoginUsuario usuario) {
        UserDetails details = userDetailsService.loadUserByUsername(usuario.getUsername());
        if (usuarioService.getByUsername(username).get()==null){
            return new ResponseEntity<>(new Mensaje("No se encontro usuario"), HttpStatus.NOT_FOUND);
        }
        Integer id = usuarioService.getByUsername(username).get().getUsuarioId();
        if (details != null) {
            if (details.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                return delete(id, new Rol(1, RolNombre.ADMIN));
            } else if (details.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("MANAGER"))) {
                return delete(id, new Rol(2, RolNombre.MANAGER));
            }
        }
        return new ResponseEntity<>(new Mensaje("No autorizado"), HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/users/activate/{username}")
    public ResponseEntity<?> activateUser(@PathVariable("username") String username, @RequestBody LoginUsuario usuario) {
        UserDetails details = userDetailsService.loadUserByUsername(usuario.getUsername());
        if (usuarioService.getByUsername(username).get()==null){
            return new ResponseEntity<>(new Mensaje("No se encontro usuario"), HttpStatus.NOT_FOUND);
        }
        Integer id = usuarioService.getByUsername(username).get().getUsuarioId();
        if (details != null) {
            if (details.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                return activate(id, new Rol(1, RolNombre.ADMIN));
            } else if (details.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("MANAGER"))) {
                return activate(id, new Rol(2, RolNombre.MANAGER));
            }
        }
        return new ResponseEntity<>(new Mensaje("No autorizado"), HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/users/bloqueo")
    public ResponseEntity<?> bloquearUsuario(@RequestBody LoginUsuario loginUsuario) {
        if (!usuarioService.existByUsername(loginUsuario.getUsername()))
            return new ResponseEntity(new Mensaje("Usuario no existe"), HttpStatus.NOT_FOUND);
        return usuarioService.getByUsername(loginUsuario.getUsername()).map(
                usuario -> {
                    usuario.setEstado(2);
                    usuarioService.save(usuario);
                    return new ResponseEntity(new Mensaje("Usuario bloqueado, comuniquese con un administrador"), HttpStatus.OK);
                }
        ).orElse(new ResponseEntity(new Mensaje("Usuario no existe"), HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UsuarioDto>> usuariosList() {
        List<Usuario> usuarios = usuarioService.findAll();
        List<UsuarioDto> dtoList = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioDto dto = new UsuarioDto();
            dto.setUsuarioId(usuario.getUsuarioId());
            dto.setNombres(usuario.getNombres());
            dto.setApellidos(usuario.getApellidos());
            dto.setUsername(usuario.getUsername());
            dto.setFechaCreacion(usuario.getFechaCreacion());
            dto.setCorreo(usuario.getCorreo());
            dto.setEstado(usuario.getEstado());
            Set<Rol> roles = usuario.getRoles();
            List<Rol> rolList = new ArrayList<>(roles);
            switch (rolList.size()){
                case 3:
                    dto.setRol("ADMIN");
                    break;
                case 2:
                    dto.setRol("MANAGER");
                    break;
                case 1:
                    dto.setRol("USER");
                    break;
                default:
                    dto.setRol("INDEFINIDO");
            }
            dtoList.add(dto);
        }
        return new ResponseEntity<List<UsuarioDto>>(dtoList, HttpStatus.OK);
    }

    public ResponseEntity<?> activate(Integer id, Rol rolUserActual) {
        if (!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

        Usuario usuario = usuarioService.getById(id).get();
        if (usuario.getRoles().stream().anyMatch(rol -> rol.getRolNombre().equals(rolUserActual.getRolNombre()))) {
            return new ResponseEntity(new Mensaje("No tiene permiso para realizar esta operacion"), HttpStatus.UNAUTHORIZED);
        }
        usuarioService.activate(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Integer id, Rol rolUserActual) {
        if (!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

        Usuario usuario = usuarioService.getById(id).get();
        if (usuario.getRoles().stream().anyMatch(rol -> rol.getRolNombre().equals(rolUserActual.getRolNombre()))) {
            return new ResponseEntity(new Mensaje("No tiene permiso para realizar esta operacion"), HttpStatus.UNAUTHORIZED);
        }
        usuarioService.delete(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }

}