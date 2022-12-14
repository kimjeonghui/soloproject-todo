package com.codestates.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TodosDto {
    @Getter
    @Setter
    public static class Post{

        private String title;
        private Integer todo_order;
        private Boolean completed;
    }
    @Getter
    @Setter
    public static class Patch{
        private String title;
        private Integer todo_order;
        private Boolean completed;
    }

    @AllArgsConstructor
    @Getter
    public static class Response{
        private Long id;
        private String title;
        private int todo_order;
        private boolean completed;


    }
}
