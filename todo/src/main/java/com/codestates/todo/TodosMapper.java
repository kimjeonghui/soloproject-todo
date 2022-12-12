package com.codestates.todo;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodosMapper {
    Todos todosPostDtoToTodos(TodosDto.Post requset);
    Todos todosPatchDtoToTodos(TodosDto.Patch todosPatchDto);
    TodosDto.Response todosToTodosResponseDto(Todos todos);
    List<TodosDto.Response> todosToTodosResponseDtos(List<Todos> todos);

}
