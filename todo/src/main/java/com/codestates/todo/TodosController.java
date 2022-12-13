package com.codestates.todo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
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
        //log.info("title: {}, order:{}, completed:{}",todo.getTitle(), todo.getTodo_order(), todo.getCompleted());
        Todos todos = todosService.createTodos(mapper.todosPostDtoToTodos(todosPostDto));

        return new ResponseEntity<>( //복습
              mapper.todosToTodosResponseDto(todos), HttpStatus.CREATED);

    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTodo(@PathVariable("id") @Positive long id,
                                      @Valid @RequestBody TodosDto.Patch todoPatchDto) {
        log.info("title: {}, order:{}, completed:{}",todoPatchDto.getTitle(), todoPatchDto.getTodo_order(), todoPatchDto.getCompleted());
        Todos todo = mapper.todosPatchDtoToTodos(todoPatchDto);

        Todos updatedTodo = todosService.updateTodos(id, todo);


        log.info("todo completed:{}", updatedTodo.getCompleted());
        return new ResponseEntity<>(
                (mapper.todosToTodosResponseDto(updatedTodo)),
                HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getTodo(@PathVariable("id") @Positive long id){
        Todos todo = todosService.findTodo(id);
        return new ResponseEntity<>(
                mapper.todosToTodosResponseDto(todo), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodos(@Positive @RequestParam int page, @Positive @RequestParam int size){
        Page<Todos> pageTodos = todosService.findTodos(page - 1, size);
        List<Todos> todosList = pageTodos.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.todosToTodosResponseDtos(todosList),
                        pageTodos),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(
            @PathVariable("id") @Positive long id) {
        todosService.deleteTodo(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteAll(){
        todosService.deleteTodos();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
