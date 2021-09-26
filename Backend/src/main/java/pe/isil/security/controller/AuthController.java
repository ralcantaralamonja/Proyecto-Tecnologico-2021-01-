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

    @PostMapping("/registro")
    public ResponseEntity<?> register(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos incorrectos o correo invalido"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existByUsername(nuevoUsuario.getUsername()))
            return new ResponseEntity(new Mensaje("Usuario ya existe"), HttpStatus.BAD_REQUEST);
        if (usuarioService.existByCorreo(nuevoUsuario.getCorreo()))
            return new ResponseEntity(new Mensaje("Correo ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getUsername(), passwordEncoder.encode(nuevoUsuario.getPassword()),
                        nuevoUsuario.getCorreo());
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.USER).get());
        if (nuevoUsuario.getRoles().contains("ADMIN"))
            roles.add(rolService.getByRolNombre(RolNombre.ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Se registro correctamente"), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("usuario o contraseña incorrectos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt= jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
