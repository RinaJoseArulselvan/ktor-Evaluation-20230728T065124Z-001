package com.example.domain.usecase

import com.example.domain.dao.DAOFacade
import com.example.domain.dao.DAOFacadeImpl
import com.example.domain.models.ResponseData
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.productsInsertRoute(){
    route("/product") {
        val dao: DAOFacade = DAOFacadeImpl()
        get ("/insert") {
            dao.insertProducts(productIntoDB())
            call.respond(ResponseData("Inserted Successfully"))
        }
    }
}