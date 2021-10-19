package pe.isil.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pe.isil.dto.Mensaje;
import pe.isil.security.dto.JwtDto;
import pe.isil.security.dto.LoginUsuario;
import pe.isil.security.dto.NuevoUsuario;
import pe.isil.security.enums.RolNombre;
import pe.isil.security.jwt.JwtProvider;
import pe.isil.security.model.entity.Rol;
import pe.isil.security.model.entity.Usuario;
import pe.isil.security.service.RolService;
import pe.isil.security.service.UsuarioService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final RolService rolService;
    private final JwtProvider jwtProvider;

    public AuthController(PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UsuarioService usuarioService, RolService rolService, JwtProvider jwtProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
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

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/registro-usuario")
    public ResponseEntity<?> registerUser(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        return register(nuevoUsuario, bindingResult, "USER");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registro-manager")
    public ResponseEntity<?> registerManager(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        return register(nuevoUsuario, bindingResult, "MANAGER");
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")   // tambien para bloquear por clave errada al 3er intento
    public ResponseEntity<?> deletedManager(@PathVariable("id") Integer id) {
        return delete(id, new Rol(1, RolNombre.ADMIN));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<?> deletedUser(@PathVariable("id") Integer id) {
        return delete(id, new Rol(1, RolNombre.MANAGER));
    }

    public ResponseEntity<?> delete(Integer id, Rol rolUserActual) {
        if (!usuarioService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);

        Usuario usuario = usuarioService.getById(id).get();
        if (usuario.getRoles().contains(rolUserActual)) {
            return new ResponseEntity(new Mensaje("No tiene permiso para realizar esta operacion"), HttpStatus.FORBIDDEN);
        }
        usuarioService.delete(id);
        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }

}
