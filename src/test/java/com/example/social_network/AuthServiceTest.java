package com.example.social_network;

import com.example.social_network.application.UserServiceImpl;
import com.example.social_network.domain.User;
import com.example.social_network.infrastructure.dto.auth.*;
import com.example.social_network.domain.repository.UserRepository;
import com.example.social_network.infrastructure.security.JwtService;
import com.example.social_network.infrastructure.common.ResponseBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl authService;

    @Test
    void login_ShouldReturnSuccess_WhenCredentialsAreValid() {
        // Arrange
        String username = "user1@mail.com";
        String password = "123456";
        String encodedPassword = "123456";

        User user = new User();
        user.setId(1L);
        user.setEmail(username);
        user.setPassword(password);

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("mocked-token");
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);

        // Act
        AuthRequestDto request = new AuthRequestDto();
        request.setEmail(user.getEmail());
        request.setPassword(password);
        AuthResponseDto response = authService.login(request);
        ResponseBase<AuthResponseDto> rb = new ResponseBase<>();
        rb.setData(response);
        rb.setError(0);
        rb.setHttpStatusCode(200);

        // Assert
        assertEquals(0, rb.getError());
        assertEquals(200, rb.getHttpStatusCode());
        assertEquals("mocked-token", rb.getData().getToken());
        assertEquals(username, rb.getData().getUserDto().getEmail());
    }
}
