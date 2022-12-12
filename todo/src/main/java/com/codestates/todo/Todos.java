package com.codestates.todo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Todos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    private String title;

    //@Column(name = "todo_order", nullable = false)
    private Integer todo_order;

    //@Column(nullable = false)
    private Boolean completed;
}
