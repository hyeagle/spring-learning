package com.example.jpa.book;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(path = "/api/v1/book")
@RestController
public class BookController {
    @Resource
    RedBookRepository redBookRepository;

    @PostMapping("/")
    public RedBook addRedBook(@RequestBody RedBook redBook) {
        return redBookRepository.save(redBook);
    }
}
