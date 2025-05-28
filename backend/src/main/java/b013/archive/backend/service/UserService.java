package b013.archive.backend.service;

import b013.archive.backend.data.dto.UserDto;
import b013.archive.backend.data.entity.User;
import b013.archive.backend.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto.UserResponseDto> getAllUser() {
        List<User> user = userRepository.findAll();

        return user.stream()
                .map(UserDto.UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserDto.UserResponseDto getUserById(int id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + id));

        return new UserDto.UserResponseDto(entity);
    }
}