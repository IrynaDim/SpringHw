package spring.intro.controllers.user;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/inject")
    public String inject() {
        userService.add(new User("Iryna", "Dymytreyeva", "1236@gmail.com"));
        userService.add(new User("Valentina", "Ivanova", "valia07@ukr.net"));
        userService.add(new User("Kirill", "Vasiliev", "kot.kot@gmail.com"));
        userService.add(new User("Jurgen ", "Klopp", "klopp@gmail.com"));
        return "Data was injected successfully.";
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return convertToResponseDto(user);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(user -> convertToResponseDto(user))
                .collect(Collectors.toList());
    }

    private UserResponseDto convertToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

}
