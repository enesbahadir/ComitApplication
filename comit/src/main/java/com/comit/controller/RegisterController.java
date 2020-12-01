package com.comit.controller;

import com.comit.model.*;
import com.comit.payload.JwtResponse;
import com.comit.payload.LoginForm;
import com.comit.payload.MessageResponse;
import com.comit.payload.UserForm;
import com.comit.repository.RoleRepository;
import com.comit.security.jwt.JwtUtils;
import com.comit.security.service.UserDetailsImpl;
import com.comit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * Authentication işlemlerini gerçekleştiren api controller
 */
@RestController
public class RegisterController {

    private final UserService userService;

    private final RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    public RegisterController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    /**
     * Sistemde kayıtlı olan kullanıcıyı verilen id değerine göre dönen HTTP-GET metodu
     *
     * @param id istenen User nesnesine ait id değeri
     * @return istenen User nesnesi
     */
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    /**
     * Verilen username değeri ile sistemde bu username ile kayıtlı User nesnesi olup olmadığını dönen metod
     *
     * @param username Kontrol edilecek olan username değeri
     * @return sistem bu username ile kayıtlı bir kullanıcı varsa false, yoksa true döner
     */
    @GetMapping("/users/username")
    public boolean isUsernameExist(@RequestParam("username") String username) {
        return userService.getUserByUserName(username) == null;
    }

    /**
     * Sisteme kayıt olmak için kullanınlan HTTP-POST metodu
     *
     * @param signUpRequest Kayıt olamk için gerekli UserForm nesnesi
     * @return işlemin başarılı veya hatalı olduğunu bildiren mesaj
     */
    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserForm signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Hata: Kullanıcı adı daha önce kayıt edilmiş!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(), signUpRequest.getSurName());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Hata: Rol bulunamadı."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if ("ADMIN".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ADMIN)
                            .orElseThrow(() -> new RuntimeException("Hata: Rol bulunamadı."));
                    roles.add(adminRole);
                } else {
                    Role modRole = roleRepository.findByName(ERole.USER)
                            .orElseThrow(() -> new RuntimeException("Hata: Rol bulunamadı."));
                    roles.add(modRole);
                }
            });
        }

        user.setRoles(roles);
        userService.createUserWithSecurity(user);
        return ResponseEntity.ok(new MessageResponse("Kullanıcı kayıt işlemi başarıyla gerçekleşti!"));
    }

    /**
     * Sisteme kayıt olmak için kullanılan HTTP-POST metodu
     *
     * @param loginRequest giriş işlemini gerçekleştirmek için kullanılan LoginForm nesnesi
     * @return giriş işlemi başarılı ise kullanıcıya ait id, kullanıcı adı ve token değerlerini döner
     */
    @PostMapping("/api/auth/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getAuthorities().toString()));
    }
}
