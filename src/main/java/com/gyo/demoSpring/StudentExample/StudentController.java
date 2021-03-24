package com.gyo.demoSpring.StudentExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Questa Clase corrisponde all'API Layer (dove si specificano gli endpoint)

@RestController  // indica che il risultato sarà un rest
@RequestMapping( path = "api/v1/student") // operazione su quel path
public class StudentController {

    private final StudentService studentService;

    @Autowired  // con questa annotazione si istanzia automaticamente studentService (dependency Injection)
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping // indica che l'operazione è una get
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<List<StatusEntity>> registerStudent(@RequestBody List<Student> student) // gli diciamo di prendere lo student dalla request
    {
        List<StatusEntity> resList = studentService.addStudent(student);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<List<StatusEntity>>(resList, httpHeaders, HttpStatus.CREATED);
    }
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id)
    {
        studentService.deleteStudent(id);
    }
    // In realtà per la patch si potrebbe fare la stessa cosa di una POST, copiare esattamente il metodo ed
    // è come se facesse una UPSERT
    @PatchMapping(path = "{studentId}")
    public void updaterStudent(@PathVariable("studentId") Long id,
                               @RequestBody Student student)
    {
            /*
            Gli diciamo di prendere i dati dal body dell'Update, ma avremmo anche potuto prerderli come parametri della chiamata,
            quindi mettere alla fine della URl i parametri ?name="nuovo nome"&email="new email"
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "email",required = false) String email
             */

        studentService.updateStudent(id, student.getName(), student.getEmail());
    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void upsertStudent(@RequestBody List<Student> student) // gli diciamo di prendere lo student dalla request
    {
        studentService.upsert(student);
    }
}
