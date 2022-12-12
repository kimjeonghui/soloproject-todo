package com.codestates.todo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/")
public class TodosController {
    private TodosService todosService;
    private TodosMapper mapper;

    public TodosController(TodosService todosService, TodosMapper mapper) {
        this.todosService = todosService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postToTodo(@Valid @RequestBody TodosDto.Post todosPostDto){
        log.info("title: {}, order:{}, completed:{}",todosPostDto.getTitle(), todosPostDto.getTodo_order(), todosPostDto.getCompleted());
        Todos todo = mapper.todosPostDtoToTodos(todosPostDto);
        log.info("title: {}, order:{}, completed:{}",todo.getTitle(), todo.getTodo_order(), todo.getCompleted());
        Todos todos = todosService.createTodos(mapper.todosPostDtoToTodos(todosPostDto));

        return new ResponseEntity<>( //복습
              mapper.todosToTodosResponseDto(todos), HttpStatus.CREATED);

    }


}
