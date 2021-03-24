package com.gyo.demoSpring.StudentExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// Questa Clase corrisponde all'API Layer (dove si specificano i servizi disponibili)

// @Component // questa annotazione è connessa all'Autowired: definisce un Bean
@Service // component e service sono simili, approfondire le differenze
public class StudentService {

    // Richiamiamo all'interfaccia del Data Access Layer

    @Autowired
    private StudentRepository studentRepository;


    /* si puó tralasciare il costruttore se facciamo la injection direttamente nella variabile del repository
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

     */

    public List<Student> getStudents()
    {
        /*
        Student[] retList = {new Student(1L, "Gyo", "gio@email.it", LocalDate.of( 1981, Month.JUNE, 17), 34),
                new Student(2L, "Arly", "ary@email.it", LocalDate.of( 1986, Month.JANUARY, 29), 24)};

*/
        return studentRepository.findAll();
    }

    public List<StatusEntity> addStudent(List<Student> student) {
        List<StatusEntity> resList = new ArrayList<>();
            student.forEach(s -> {
                Optional<Student> optStud = studentRepository.checkEmail(s.getEmail());
                if (!optStud.isPresent()) {
                    studentRepository.save(s); // andrebbe Gestito il caso in cui già è presente con una risposta errata
                    resList.add(new StatusEntity(s.getId(), "OK"));
                }
                else
                    resList.add( new StatusEntity("User with email: " + s.getEmail() + " already exists"));
            });
       // studentRepository.saveAll(student);
        return resList;
    }

    public void deleteStudent(Long studentId) {
        boolean bPresent = studentRepository.findById(studentId).isPresent();
        if(bPresent)
            studentRepository.deleteById(studentId);
        else
            throw new IllegalStateException("Student with id " + studentId + " does nos exist!!");
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        boolean bPresent = studentRepository.findById(studentId).isPresent();
        if(bPresent) {
            Student studAux = studentRepository.findById(studentId).get();
            /* inutile richiamare il repository, facciamo tutto dal Service
            studentRepository.updateStudent(studAux, name, email);
            */
            if(name != null && !name.isEmpty())
                studAux.setName(name);
            if(email != null && !email.isEmpty())
                studAux.setEmail(email);
        }
        else
            throw new IllegalStateException("Student with id " + studentId + " does nos exist!!");
    }

    // l'operazione di Save, se è giá esistente, sovrascrive i campi, altrimenti crea
    // ovviamente per l'operazione di Update dobbiamo anche inserire la chiave
    public void upsert(List<Student> student) {
        studentRepository.saveAll(student);
    }
}
