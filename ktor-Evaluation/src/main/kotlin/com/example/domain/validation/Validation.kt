package com.example.domain.validation

import com.example.domain.models.RequestData
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation(){
    install(RequestValidation){
        validate<RequestData> {
            if (it.query != ""){
                ValidationResult.Valid
            }else
                ValidationResult.Invalid("Empty String")
        }
    }
}