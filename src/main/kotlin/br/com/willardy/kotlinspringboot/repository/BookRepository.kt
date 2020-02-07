package br.com.willardy.kotlinspringboot.repository

import br.com.willardy.kotlinspringboot.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Long>