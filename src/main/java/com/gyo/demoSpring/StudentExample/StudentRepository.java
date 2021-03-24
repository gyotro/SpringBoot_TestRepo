package com.gyo.demoSpring.StudentExample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

// Ora definiamo il Data Access Layer
@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> { // Long perché il nostro Id è di tipo Long

    // Qui si puó scrivere della Business Logic e richiamarla dalle funzioni di Post/Get/...

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> checkEmail(String email);

    /*
    Meglio non definire qui la logica di Business
    @Transactional
    default void updateStudent(Student stud, String name, String email)
    {
        if(name != null && !name.isEmpty())
            stud.setName(name);
        if(email != null && !email.isEmpty())
            stud.setEmail(email);
    }
    */
}
