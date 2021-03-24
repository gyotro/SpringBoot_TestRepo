package com.gyo.demoSpring.StudentExample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.tools.jar.CommandLine;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {

        return args -> {
            /*
            Student[] retList = {new Student("Gyo", "gio@email.it", LocalDate.of( 1981, Month.JUNE, 17)
           //         , 34
            ),
                    new Student("Arly", "ary@email.it", LocalDate.of( 1986, Month.JANUARY, 29)
           //                 , 24
                    )};
            studentRepository.saveAll(Arrays.asList(retList));
        };
             */
            System.out.println("Arranque de la Aplicación desde el Bean");

        };
    }

}
