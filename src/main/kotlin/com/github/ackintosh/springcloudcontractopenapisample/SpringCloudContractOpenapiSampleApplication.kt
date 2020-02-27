package com.github.ackintosh.springcloudcontractopenapisample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class SpringCloudContractOpenapiSampleApplication

fun main(args: Array<String>) {
    runApplication<SpringCloudContractOpenapiSampleApplication>(*args)
}

@RestController
class Controller {
    @GetMapping("/example")
    fun example() = "Hello! Spring Cloud Contract OpenAPI!"
}
