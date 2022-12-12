package com.codestates.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodosRepository extends JpaRepository<Todos, Long> {
}
