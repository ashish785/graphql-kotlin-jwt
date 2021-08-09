package com.example.demo.query

import com.example.demo.model.User
import com.example.demo.service.UserService
import org.springframework.stereotype.Component
import com.expediagroup.graphql.spring.operations.Query

@Component
class UserQuery(private val service: UserService) : Query {

    fun users(): List<User> {
        return service.findAll()
    }

    fun user(email: String): User? {
        return service.findUserByEmail(email);
    }
}