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

    public Todos updateTodos(Long id, Todos todos){
        Todos findTodo = findTodo(id);

        Optional.ofNullable(todos.getTitle())
                .ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todos.getTodo_order())
                .ifPresent(order -> findTodo.setTodo_order(order));
        Optional.ofNullable(todos.getCompleted())
                .ifPresent(complete -> findTodo.setCompleted(complete));

        return todosRepository.save(findTodo);
    }

    public Page<Todos> findTodos(int page, int size){
        return todosRepository.findAll(PageRequest.of(page, size));
        //,Sort.by("todo_order").descending()));
    }

    public Todos findTodo(Long id){
        Optional<Todos> todo = todosRepository.findById(id);
        Todos findtodos = todo.orElseThrow(
                ()-> new RuntimeException()
        );

        return findtodos;
    }

    public void deleteTodo(Long id){
        todosRepository.deleteById(id);
    }

    public void deleteTodos(){
        todosRepository.deleteAll();
    }

}
