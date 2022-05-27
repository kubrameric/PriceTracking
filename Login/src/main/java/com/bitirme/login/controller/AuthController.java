package com.bitirme.login.controller;

import com.bitirme.login.dto.JwtResponse;
import com.bitirme.login.dto.LoginRequest;
import com.bitirme.login.dto.MessageResponse;
import com.bitirme.login.dto.SignupRequest;
import com.bitirme.login.model.Role;
import com.bitirme.login.model.RoleType;
import com.bitirme.login.model.User;
import com.bitirme.login.security.JwtUtils;
import com.bitirme.login.security.MyUserDetails;
import com.bitirme.login.service.RoleService;
import com.bitirme.login.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final RoleService roleService;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/checkJwt")
    public Boolean checkJwt(@RequestParam("token") String token) {
        return jwtUtils.validateJwtToken(token);

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/signin")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(
                item -> item.getAuthority()).collect(Collectors.toList());

        return JwtResponse
                .builder()
                .token(jwt)
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {

        if (userService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username is already taken!"));
        }

        if (userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: email is already in use! "));
        }

        User user = User
                .builder()
                .name(signupRequest.getName())
                .surname(signupRequest.getSurname())
                .username(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .phoneNumber(signupRequest.getPhoneNumber())
                .password(encoder.encode(signupRequest.getPassword()))
                .build();

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName(RoleType.ROLE_USER);
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(RoleType.ROLE_ADMIN);
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleService.findByName(RoleType.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.createNewUser(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }
}
