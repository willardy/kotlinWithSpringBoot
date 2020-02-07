package br.com.willardy.kotlinspringboot.controller

import br.com.willardy.kotlinspringboot.model.Book
import br.com.willardy.kotlinspringboot.repository.BookRepository
import br.com.willardy.kotlinspringboot.repository.CategoryRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/books")
class BookController(private val bookRepository: BookRepository, private val categoryRepository: CategoryRepository) {
    @GetMapping
    fun getAllBooks(): List<Book> = bookRepository.findAll()

    @PostMapping
    fun createNewBook(@Valid @RequestBody book: Book) {
        book.category?.let { categoryRepository.save(it) }
        bookRepository.save(book)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable(value = "id") bookId: Long): ResponseEntity<Book> {
        return bookRepository.findById(bookId).map { book ->
            ResponseEntity.ok(book)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteBookById(@PathVariable(value = "id") bookId: Long): ResponseEntity<Void> {
        return bookRepository.findById(bookId).map { book ->
            bookRepository.delete(book)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}