package com.curso.microservices.services;

import com.curso.microserices.exceptions.CursoExceptions;
import com.curso.microservices.models.Curso;
import com.curso.microservices.repositories.CorsoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Evandro
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(
        @Autowired))
public class CursoService {

    private final CorsoRepository repository;

    public Curso insert(Curso curso) {
        return repository.save(curso);
    }

    public Iterable<Curso> findAll(Pageable pageable) {
        log.info("Listando todos os cursos");
        return repository.findAll(pageable);
    }

    public Optional<Curso> findById(long id) {
        Optional<Curso> c = repository.findById(id);
        if (c == null) {
            throw new CursoExceptions("Id not found!");
        }
        return c;

    }

    public Curso updateById(Curso curso, long id) {
        Optional<Curso> oldCurso = repository.findById(id);
        if (oldCurso.isPresent()) {
            Curso c = oldCurso.get();
            c.setTitle(curso.getTitle());
            c.setName(curso.getName());
            c.setDuration(curso.getDuration());
            c.setPrice(curso.getPrice());
            repository.save(c);
            return c;
        } else {
            throw new CursoExceptions("Id not found");
        }

    }

    public void deleteById(long id) {
        repository.deleteById(id);

    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
