package com.rabelo.movies.repository

import com.rabelo.movies.model.Movie
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: ReactiveCrudRepository<Movie, String>