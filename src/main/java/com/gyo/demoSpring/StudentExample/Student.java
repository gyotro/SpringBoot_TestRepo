package com.gyo.demoSpring.StudentExample;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Student {

    // appronfondirne il significato
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    // appronfondirne il significato
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @Id // Questa Annotation indica qual è la chiave (per coincidenza è lo stesso nome del parametro della Classe)
    private Long id;
    @Column(name = "nome_student") // cosí indichiamo il nome che vogliamo dare ad una colonna
    private String name;
    private String email;
    private LocalDate dob;
    @Transient  // in questo modo non lo serializziamo, ma lo calcoliamo noi a runtime, xcio lo eliminiamo dappertutto
    private int age;


    public Student() { }

    public Student(Long id, String name, String email, LocalDate dob
      //      , int age
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
 //       this.age = age;
    }

    public Student(String name, String email, LocalDate dob
    //        , int age
    ) {
        this.name = name;
        this.email = email;
        this.dob = dob;
 //       this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    // Qui ci andiamo a calcolare age a Runtime (quindi non verrà scritto in tabella, ma calcolato quando si va in get
    public int getAge() {

        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
