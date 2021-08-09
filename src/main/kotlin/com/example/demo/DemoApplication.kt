package com.example.demo

import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.util.ResourceUtils
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

@SpringBootApplication
class DemoApplication(private val userRepository: UserRepository) : CommandLineRunner{
	override fun run(vararg args: String?) {
		val mapper = ObjectMapper()
		val typeReference: TypeReference<List<User>?> = object : TypeReference<List<User>?>() {}
		val file: File = ResourceUtils.getFile("classpath:users.json")
		val inputStream: InputStream = FileInputStream(file)
		try {
			val users: List<User>? = mapper.readValue(inputStream, typeReference)
			if (users != null) {
				for (user in users) {
					userRepository.save(user);
				}
			}
			println("Users Saved!")
		} catch (e: IOException) {
			println("Unable to save users: " + e.message)
		}
	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
