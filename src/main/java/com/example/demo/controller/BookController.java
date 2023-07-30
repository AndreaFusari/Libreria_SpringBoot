package com.example.demo.controller;

import com.example.demo.dto.BaseResponse;
import com.example.demo.dto.BookRequestDto;
import com.example.demo.dto.BookResponseDto;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping(value = "/book")
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto bookRequestDto){
        return ResponseEntity.ok(bookService.createBook(bookRequestDto));
    }
    @GetMapping(value="/book")
    public ResponseEntity<BookRequestDto> getBook(@RequestParam Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    @GetMapping(value="/books")
    public ResponseEntity<List<BookRequestDto>> getBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
    @PutMapping(value="/book")
    public ResponseEntity<BaseResponse> updateBook(@RequestParam Long id, @RequestBody BookRequestDto bookRequestDto){
        return ResponseEntity.ok(bookService.updateBook(id,bookRequestDto));
    }
    @DeleteMapping(value = "/book")
    public ResponseEntity<BaseResponse> deleteBook(@RequestParam Long id){
        return ResponseEntity.ok(bookService.deleteBookById(id));
    }


}
