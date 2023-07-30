package com.example.demo.service;

import com.example.demo.dto.BaseResponse;
import com.example.demo.dto.BookRequestDto;
import com.example.demo.dto.BookResponseDto;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
@Autowired
private BookRepository bookRepository;

public BookResponseDto createBook(BookRequestDto bookDto){
    Book book  = new Book();
    book.setAuthor(bookDto.getAuthor());
    book.setTitle(bookDto.getTitle());
    book.setIsbn(bookDto.getIsbn());
    bookRepository.save(book);
    BookResponseDto response = new BookResponseDto();
    response.setId(bookRepository.findByIsbn(bookDto.getIsbn()).getId());
    response.setMessage("Book created");
    return response;

}

public BookRequestDto getBookById(Long id){
    Optional<Book> oBookDto = bookRepository.findById(id);
    if (oBookDto.isPresent()){
        Book book = oBookDto.get();
        BookRequestDto bookDto = new BookRequestDto();
        bookDto.setId(book.getId());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());

    return bookDto;
    }
    else throw new RuntimeException("id non trovato");

}
public List<BookRequestDto> getAllBooks(){
    return bookRepository.findAll().stream().map(book -> new BookRequestDto(book.getId(),book.getAuthor(),book.getTitle(),book.getIsbn())).toList();
}
public BaseResponse updateBook(Long id, BookRequestDto update){
   Optional<Book> oBook = bookRepository.findById(id);
   if(oBook.isPresent()){
       Book book = oBook.get();
       if(update.getTitle()!=null){ book.setTitle(update.getTitle());}
       if(update.getAuthor()!=null){book.setAuthor(update.getAuthor());}
       if(update.getIsbn()!=null){book.setIsbn(update.getIsbn());}
       bookRepository.save(book);
       BookResponseDto bookResponseDto = new BookResponseDto();
       bookResponseDto.setMessage("Book updated");
       bookResponseDto.setId(id);
       return bookResponseDto;

   } else return new BaseResponse("id non trovato");
}
public BaseResponse deleteBookById(Long id){
    bookRepository.deleteById(id);
    return new BaseResponse("Book Deleted");
}


}
