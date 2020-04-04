package com.example.cloudkotlin.blog

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/article")
class ArticleController(private val repository: ArticleRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAllByOrderByAddedAtDesc()

    @GetMapping("/{slug}")
    fun findOne(@PathVariable slug: String)
        = repository.findBySlug(slug) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
}

@RestController
@RequestMapping("/api/user")
class UserController(private val repository: UserRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAll()

    @GetMapping("/{login}")
    fun findOne(@PathVariable login: String)
        = repository.findByLogin(login) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This user does not exist")

    @PostMapping("/addUser")
    fun addUser(@RequestBody newUser: User) = repository.save(newUser)
}