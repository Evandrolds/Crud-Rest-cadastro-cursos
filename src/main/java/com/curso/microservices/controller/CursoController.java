package com.curso.microservices.controller;

import com.curso.microserices.exceptions.CursoExceptions;
import com.curso.microservices.services.CursoService;
import com.curso.microservices.models.Curso;
import java.util.Optional;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Evandro
 */
@RestController
@RequestMapping("/cursos")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(
        @Autowired))
public class CursoController {
    
    private final CursoService service;
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Curso> insert(@RequestBody Curso curso) {
        return new ResponseEntity<>(service.insert(curso), HttpStatus.CREATED);
    }    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Curso>> listCursos(Pageable pageable) {
        return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable("id") long id){
        Optional<Curso> c = service.findById(id);
        if(c.isPresent()){
            return new ResponseEntity<>(c.get(),HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       
    }
    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable("id") long id, @RequestBody Curso curso) {
        return service.findById(id).map(x -> {
            x.setTitle(curso.getTitle());
            x.setName(curso.getName());
            x.setDuration(curso.getDuration());
            x.setPrice(curso.getPrice());
            Curso c = service.insert(x);
            return ResponseEntity.status(HttpStatus.CREATED).body(c);
        }).orElse(ResponseEntity.noContent().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        return service.findById(id).map(x -> {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).build();
        }).orElse(ResponseEntity.noContent().build());
    }
    
}
