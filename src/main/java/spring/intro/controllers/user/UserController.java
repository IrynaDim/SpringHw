package spring.intro.controllers.user;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.config.AppConfig;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);

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
        List<User> allUsers = userService.listUsers();
        User user = allUsers.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst().get();
        return changeToResponseDto(user);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        List<User> allUsers = userService.listUsers();
        List<UserResponseDto> allUsersDto = allUsers.stream()
                .map(user -> changeToResponseDto(user))
                .collect(Collectors.toList());
        return allUsersDto;
    }

    private UserResponseDto changeToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
