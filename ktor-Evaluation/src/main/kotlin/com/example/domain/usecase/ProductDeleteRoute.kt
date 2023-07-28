package com.example.domain.usecase

import com.example.data.DatabaseFactory.dbQuery
import com.example.domain.models.RequestData
import com.example.domain.models.ResponseData
import com.example.domain.dao.DAOFacade
import com.example.domain.dao.DAOFacadeImpl
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.productDeleteRoute(){
    route("/product"){
        val dao: DAOFacade = DAOFacadeImpl()
        post("/delete") {
            val request = call.receive<RequestData>()
            if (dao.checkProductCategoryExists(request)) {
                val dbDelete = dao.deleteProducts(request)
                if (dbDelete > 0) {
                    call.respond(ResponseData("Deleted successfully"))
                } else {
                    call.respond(ResponseData("Deletion failed"))
                }
            }else {
                call.respond(ResponseData("The product category ${request.query} does not exists"))
            }
        }
    }
}