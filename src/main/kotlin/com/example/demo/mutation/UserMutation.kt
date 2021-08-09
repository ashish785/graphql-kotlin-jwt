package com.example.demo.mutation

import com.example.demo.controller.Jwt
import com.example.demo.security.JwtSupport
import com.example.demo.service.UserService
import com.expediagroup.graphql.annotations.GraphQLDescription
import com.expediagroup.graphql.spring.operations.Mutation
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class UserMutation(
    private val userService: UserService,
    private val encoder: PasswordEncoder,
    private val jwtSupport: JwtSupport
    ) : Mutation{

        @GraphQLDescription("Logs in user and returns JWT")
        fun login(email: String, password: String) : Jwt? {
              val user =  userService.findUserByEmail(email);
            if (user != null) {
                if (encoder.matches(password, user.password)) {
                    if (user != null) {
                        return Jwt(jwtSupport.generate(user.email).value)
                    }
                }
            }
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        }
}