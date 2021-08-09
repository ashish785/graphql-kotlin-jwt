package com.example.demo.service

import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository){
    fun save(user: User): User{
        return userRepository.save(user);
    }

    fun findUserByEmail(email: String) : User?{
        return userRepository.findUserByEmail(email)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }
}