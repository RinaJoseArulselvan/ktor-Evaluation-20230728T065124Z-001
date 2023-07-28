package com.example.plugins

import com.example.domain.usecase.*
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        productsInsertRoute()
        productSearchRoute()
        productFetchRoute()
        productDeleteRoute()
        productUpdateRoute()
    }
}
