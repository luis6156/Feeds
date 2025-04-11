package com;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.configuration.AppConfig;
import com.model.Post;
import com.model.Profile;
import com.model.User;
import com.repository.ExerciseRepository;
import com.repository.PlayGroundRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Spring Fundamentals - JDBC");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        PlayGroundRepository playGroundRepository = context.getBean(PlayGroundRepository.class);

        //insert method 1
        insertScenario1(playGroundRepository);
        //insert method 2
        insertScenario2(playGroundRepository);
        //error in case of duplicate insert
//        errorForDuplicatedInsertScenario(playGroundRepository);
        //find all profiles
        findAllScenario(playGroundRepository);

        //exercises
        ExerciseRepository exerciseRepository = context.getBean(ExerciseRepository.class);
        // ex 1 - insert post
        // ex 2- insert tag
        // ex 3 - remove post by id
        // ex 4 - select all post

        exerciseRepository.insertPost(new Post(new User( 1,"Test","test@gmail.com"), "", "", new ArrayList<>()));
        exerciseRepository.removePostById(1);
    }

    private static void findAllScenario(PlayGroundRepository playGroundRepository) {
        System.out.println("--------- Find All Scenario ---------");
        List<Profile> profiles = playGroundRepository.findAll();
        System.out.println("profiles: " + profiles);
    }

    private static void errorForDuplicatedInsertScenario(PlayGroundRepository playGroundRepository) {
        System.out.println("--------- Error for duplicated insert scenario ---------");
        int count = playGroundRepository.deleteByUserId(1);
        System.out.println("deleted profiles by user id: " + count);
        int id = playGroundRepository.insertReturnId(1, "Insert method 2", "http://profile_insert_method2.com");
        System.out.println("Returned id: " + id);
        int duplicatedId = playGroundRepository.insertReturnId(1, "Insert method 2", "http://profile_insert_method2.com");
        System.out.println("duplicated Inserted profile: " + duplicatedId);
    }

    private static void insertScenario1(PlayGroundRepository playGroundRepository) {
        System.out.println("--------- Insert Scenario 1: no id returned ---------");
        int count = playGroundRepository.deleteByUserId(1);
        System.out.println("deleted profiles by user id: " + count);
        playGroundRepository.insert(1, "Insert method 1", "http://profile_insert_method1.com");
    }

    private static void insertScenario2(PlayGroundRepository playGroundRepository) {
        System.out.println("--------- Insert Scenario 2: id returned ---------");
        int count = playGroundRepository.deleteByUserId(1);
        System.out.println("deleted profiles by user id: " + count);
        int id = playGroundRepository.insertReturnId(1, "Insert method 2", "http://profile_insert_method2.com");
        System.out.println("Returned id: " + id);
    }
}