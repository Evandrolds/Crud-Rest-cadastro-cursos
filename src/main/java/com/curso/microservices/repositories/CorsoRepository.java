package com.curso.microservices.repositories;

import com.curso.microservices.models.Curso;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Evandro
 */
@Repository
public interface CorsoRepository extends PagingAndSortingRepository<Curso, Long>{
    
}
