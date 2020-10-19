package spring.intro;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.intro.config.AppConfig;
import spring.intro.model.User;
import spring.intro.service.UserService;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Iryna", "Dymytreyeva", "1236@gmail.com"));
        userService.add(new User("Valentina", "Ivanova", "valia07@ukr.net"));
        userService.add(new User("Kirill", "Vasiliev", "kot.kot@gmail.com"));

        userService.listUsers().forEach(logger::info);
    }
}
