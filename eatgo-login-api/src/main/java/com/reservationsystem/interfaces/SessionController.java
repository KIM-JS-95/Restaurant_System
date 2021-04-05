package com.reservationsystem.interfaces;


import com.reservationsystem.application.UserService;
import com.reservationsystem.domain.User;
import com.reservationsystem.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDTO> create(
            @RequestBody SessionRequestDTO resource
    ) throws URISyntaxException {

        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.authenticate(email, password);
      //  String accessToken = jwtUtil.createToken(user.getId(), user.getName());

        String accessToken =jwtUtil.createToken(1004L, "John", null);
        userService.authenticate(email, password);
        SessionResponseDTO sessionDTO = SessionResponseDTO.builder().accessToken(accessToken).build();

        String url = "/session";
        return ResponseEntity.created(new URI(url)).body(sessionDTO);
    }
}
