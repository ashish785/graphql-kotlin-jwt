package com.example.demo.repository

import com.example.demo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRepository : JpaRepository<User, String> {
    fun findUserByEmail(email: String): User?
}