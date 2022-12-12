package com.codestates.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodosService {

    private final TodosRepository todosRepository;

    public TodosService(TodosRepository todosRepository) {
        this.todosRepository = todosRepository;
    }

    public Todos createTodos(Todos todos){
        return todosRepository.save(todos);
    }

    public Todos updateTodos(Todos todos){
        Todos findtodos = findTodo(todos.getId()).orElseThrow(
                ()-> new RuntimeException()
        );

        Optional.ofNullable(todos.getTitle())
                .ifPresent(title -> findtodos.setTitle(title));
        Optional.ofNullable(todos.getTodo_order())
                .ifPresent(order -> findtodos.setTodo_order(order));
        Optional.ofNullable(todos.getCompleted())
                .ifPresent(complete -> findtodos.setCompleted(complete));

        return todosRepository.save(findtodos);
    }

    public Page<Todos> findTodos(int page, int size){
        return todosRepository.findAll(PageRequest.of(page, size,
                Sort.by("todosOrder").descending()));
    }

    public Optional<Todos> findTodo(Long id){
        return todosRepository.findById(id);
    }

    public void deleteTodo(Long id){
        todosRepository.deleteById(id);
    }

    public void deleteTodos(){
        todosRepository.deleteAll();
    }

}
