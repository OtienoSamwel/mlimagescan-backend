package com.otienosamwel.mlimagescan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class MlimagescanApplication

fun main(args: Array<String>) {
    runApplication<MlimagescanApplication>(*args)
}
