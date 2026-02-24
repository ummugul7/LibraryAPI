package com.ummugul.libraryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//pgadmin http://localhost:15432
@SpringBootApplication(scanBasePackages = "com.ummugul.libraryapi")
public class LibraryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApiApplication.class, args);
    }

}
