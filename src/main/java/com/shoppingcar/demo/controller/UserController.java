package com.shoppingcar.demo.controller;

import com.shoppingcar.demo.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {

    public static void writeLog(String text) {

        log.error(text);

    }

    @PostMapping(value = "/user")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) throws IllegalArgumentException {
        log.info("REST request to login user: {}", username);

        if (checkUser(username, pwd)) {
            log.info("User: {} authenticated", username);
            String token = getJWTToken(username);
            User user = new User();
            user.setUser(username);
            user.setToken(token);
            log.info("REST request to login user: {}", username);
            return user;

        } else {
            throw new IllegalArgumentException("Usuario o password no válidos");

        }


    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("JWTFactory")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

    private boolean checkUser(String username, String pwd) {
        Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
        Path filePath = Paths.get(root.toString(), "src", "main", "resources", "users.txt");
        try (Stream<String> stream = Files.lines(Paths.get(filePath.toUri()))) {

            Optional<String[]> response = stream.map(str -> str.split(","))
                    .filter(str -> str[0].trim().equalsIgnoreCase(username) && str[1].trim().equals(pwd))
                    .findFirst();

            return response.isPresent();

        } catch (IOException e) {
            return false;

        }
    }


}
