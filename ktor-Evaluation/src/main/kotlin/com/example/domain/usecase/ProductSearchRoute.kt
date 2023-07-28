package com.example.domain.usecase

import com.example.data.ProductEntity
import com.example.domain.models.Product
import com.example.domain.models.RequestData
import com.example.domain.models.ResponseData
import com.example.data.DatabaseFactory.dbQuery
import com.example.domain.dao.DAOFacade
import com.example.domain.dao.DAOFacadeImpl
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.select

fun Route.productSearchRoute(){
    route("/product"){
        post("/search") {
            val dao: DAOFacade = DAOFacadeImpl()
            val request = call.receive<RequestData>()
            val productsBasedOnTitle = dao.searchProducts(request)
            if (productsBasedOnTitle.isNotEmpty()){
                call.respond(productsBasedOnTitle)
            }else{
                call.respond(ResponseData("Products not found for this title or in description ${request.query}"))
            }
        }

    }
}