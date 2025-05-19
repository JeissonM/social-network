package com.example.social_network.infrastructure.config;

import com.example.social_network.domain.User;
import com.example.social_network.domain.Post;
import com.example.social_network.domain.repository.PostRepository;
import com.example.social_network.domain.repository.UserRepository;
import com.example.social_network.infrastructure.dto.UserRequestDto;
import com.example.social_network.infrastructure.mapper.UserMapper;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public DatabaseSeeder(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) {
        for (int i = 1; i <= 5; i++) {
            String email = "user" + i + "@example.com";
            if (userRepository.findByEmail(email).isPresent()) {
                continue; // Ya existe, saltar al siguiente
            }

            UserRequestDto userDto = new UserRequestDto();
            userDto.setAlias("Alias" + i);
            userDto.setNames("Nombre" + i);
            userDto.setLastName("Apellido" + i);
            userDto.setEmail("user" + i + "@example.com");
            userDto.setPassword("password" + i);
            userDto.setBirthDate(LocalDate.now());

            User user = UserMapper.toEntity(userDto);
            User savedUser = userRepository.save(user);

            Post post = new Post();
            post.setContent("Este es el contenido de prueba del usuario " + i);
            post.setAuthor(savedUser);
            postRepository.save(post);
        }

        System.out.println("✔ Usuarios y publicaciones de prueba insertados");
    }
}
