package com.codestates.todo;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodosMapper {
    Todos todosPostDtoToTodos(TodosDto.Post requset);
    Todos todosPatchDtoToTodos(TodosDto.Patch todosPatchDto);
    TodosDto.Response todosToTodosResponseDto(Todos todos);
    List<TodosDto.Response> todosToTodosResponseDtos(List<Todos> todos);

}
