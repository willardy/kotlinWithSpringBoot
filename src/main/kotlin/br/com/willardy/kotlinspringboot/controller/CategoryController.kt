package br.com.willardy.kotlinspringboot.controller

import br.com.willardy.kotlinspringboot.model.Category
import br.com.willardy.kotlinspringboot.repository.CategoryRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val categoryRepository: CategoryRepository) {
    @GetMapping
    fun getAllCategorys(): List<Category> = categoryRepository.findAll()

    @PostMapping
    fun createNewCategory(@Valid @RequestBody category: Category): ResponseEntity<Category> {
        categoryRepository.save(category)
        return ResponseEntity.ok(category)
    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable(value = "id") categoryId: Long): ResponseEntity<Category> {
        return categoryRepository.findById(categoryId).map { category ->
            ResponseEntity.ok(category)
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteCategoryById(@PathVariable(value = "id") categoryId: Long): ResponseEntity<Void> {
        return categoryRepository.findById(categoryId).map { category ->
            categoryRepository.delete(category)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}