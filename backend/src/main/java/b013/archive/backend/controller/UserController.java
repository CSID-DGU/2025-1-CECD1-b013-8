package b013.archive.backend.controller;

import b013.archive.backend.data.dto.UserDto;
import b013.archive.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 음식 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserDto.UserResponseDto> getUserById(@PathVariable int id) {
        UserDto.UserResponseDto responseDto = userService.getUserById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 음식 조회
    @GetMapping
    public ResponseEntity<List<UserDto.UserResponseDto>> getUserList() {
        List<UserDto.UserResponseDto> userList = userService.getAllUser();

        return ResponseEntity.ok(userList);
    }
}