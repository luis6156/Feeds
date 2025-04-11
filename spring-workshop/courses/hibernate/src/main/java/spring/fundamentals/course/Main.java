package spring.fundamentals.course;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.fundamentals.course.configuration.AppConfig;
import spring.fundamentals.course.model.User;
import spring.fundamentals.course.repository.PlayGroundRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Spring Fundamentals - Hibernate");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        PlayGroundRepository playGroundRepository = context.getBean(PlayGroundRepository.class);
        playGroundRepository.play();

//        User user = playGroundRepository.findUser(1);
//        user.setUsername("new_fake");
//        System.out.println(user);

//        System.out.println(playGroundRepository.findAll());

    }
}