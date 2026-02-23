package com.ummugul.libraryapi.dto;


import lombok.Data;

import java.util.List;

@Data
    public class AuthorDetailDto {
    private String firstname;
    private String lastname;
    private List<String> bookNames;

}