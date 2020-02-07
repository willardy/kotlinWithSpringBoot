package br.com.willardy.kotlinspringboot.repository

import br.com.willardy.kotlinspringboot.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository: JpaRepository<Category, Long>