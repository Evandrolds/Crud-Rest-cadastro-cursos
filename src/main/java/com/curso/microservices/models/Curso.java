package com.curso.microservices.models;

import javax.persistence.*;
import lombok.*;

/**
 *
 * @author Evandro
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_cursos")
public class Curso implements CursoAbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name="Codi")
    private Long id;
    @Column(nullable =false)
    private String title;
    @Column(nullable = false)
    private String name;
    @Column (name= "Duração_h",nullable = false)
    private Integer duration;
    @Column(nullable = false)
    private Double price;

}
