package com.rabelo.movies.controller

import com.rabelo.movies.model.Movie
import com.rabelo.movies.repository.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.*

@RestController
@RequestMapping(value = ["/movie"])
class MovieEndpoint @Autowired constructor(private val repository: MovieRepository) {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun post(@RequestBody movie: Movie): Mono<Movie> {
        movie.id = UUID.randomUUID().toString()

        return repository.save(movie)
    }

    @GetMapping(produces = ["application/stream+json"])
    fun get(): Flux<Movie> {
        return repository.findAll()
                .delayElements(Duration.ofSeconds(3))
    }

    @PutMapping
    fun put(@RequestBody movie: Movie): Mono<Movie> {
        return repository.save(movie)
    }

}