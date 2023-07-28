package com.example.domain.exception

import com.example.domain.models.ResponseData
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.sql.SQLDataException

fun Application.configureException(){
    install(StatusPages){
        exception<Throwable> { call, cause ->
            when (cause) {
                is IllegalArgumentException -> {
                    call.respond(ResponseData("give the input properly"))
                }
                is NullPointerException -> {
                    call.respond(ResponseData(cause.toString()))
                }
                is ContentTransformationException -> {
                    call.respond(HttpStatusCode.BadRequest, "Invalid JSON format")
                }
                is SQLDataException -> {
                    call.respond(HttpStatusCode.BadRequest,cause.toString())
                }
                else -> {
                    call.respond(ResponseData("text = 500: $cause, status = ${HttpStatusCode.InternalServerError}"))
                }
            }
        }
        exception<RequestValidationException >{ call,cause ->
            call.respond(HttpStatusCode.BadRequest, cause.reasons.joinToString())
        }
        }



}