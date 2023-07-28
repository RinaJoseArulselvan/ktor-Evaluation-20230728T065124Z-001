package com.example.domain.usecase

import com.example.domain.models.RequestData
import com.example.domain.models.ResponseData
import com.example.domain.dao.DAOFacade
import com.example.domain.dao.DAOFacadeImpl
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.productFetchRoute(){
    route("/product") {
        val dao: DAOFacade = DAOFacadeImpl()
        post {
            val productsBasedOnCategory = dao.fetchAllProduct()
            if (productsBasedOnCategory.isNotEmpty()) {
                call.respond(productsBasedOnCategory)
            } else {
                call.respond(ResponseData("Products not found"))
            }
        }
        post("/fetch") {
            val request = call.receive<RequestData>()
            val productsBasedOnCategory = dao.fetchProduct(request)
                if (productsBasedOnCategory.isNotEmpty()) {
                    call.respond(productsBasedOnCategory)
                } else {
                    call.respond(ResponseData("Products not found for category ${request.query}"))
                }
        }
    }

}