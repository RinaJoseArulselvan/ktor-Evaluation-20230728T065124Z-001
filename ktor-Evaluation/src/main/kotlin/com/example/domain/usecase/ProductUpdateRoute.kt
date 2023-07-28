package com.example.domain.usecase

import com.example.domain.models.*
import com.example.domain.dao.DAOFacade
import com.example.domain.dao.DAOFacadeImpl
import com.google.gson.Gson
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.productUpdateRoute() {
    route("/product") {
        val dao: DAOFacade = DAOFacadeImpl()
        post("/update") {
            val jsonData = call.receiveText()
            val productRequestData = Gson().fromJson(jsonData, ProductRequestData::class.java)
            if (dao.checkProductIdExists(productRequestData.id)) {
                val dbUpdate = dao.updateProduct(productRequestData)
                if (dbUpdate > 0) {
                    call.respond(ResponseData("Product Updated Successfully"))
                } else {
                    call.respond(ResponseData("Updation failed"))
                }
            }else{
                call.respond(ResponseData("Product id ${productRequestData.id} does not exists"))
                return@post
            }
        }
    }
}
     /*
     post("/title") {
         val request = call.receive<UpdateTitle>()
         val dbUpdate = dbQuery{
             ProductEntity.update({
                 ProductEntity.id eq request.id
             }) {
                 it[title] = request.title
             }
         }
         if (dbUpdate == 1){
             call.respond(ResponseData("Title Updated Successfully"))
         }else{
             call.respond(ResponseData("Title Updation failed"))
         }
     }

     post("/description") {
         val request = call.receive<UpdateDescription>()
         val dbUpdate = dbQuery{
             ProductEntity.update({
                 ProductEntity.id eq request.id
             }) {
                 it[description] = request.description
             }
         }
         if (dbUpdate == 1){
             call.respond(ResponseData("Description Updated Successfully"))
         }else{
             call.respond(ResponseData("Description Updation failed"))
         }
     }

     post("/price") {
         val request = call.receive<UpdatePrice>()
         val dbUpdate = dbQuery{
             ProductEntity.update({
                 ProductEntity.id eq request.id
             }) {
                 it[price] = request.price
             }
         }
         if (dbUpdate == 1){
             call.respond(ResponseData("Price Updated Successfully"))
         }else{
             call.respond(ResponseData("Price Updation failed"))
         }
     }

     post("/discountPercentage") {
         val request = call.receive<UpdateDiscountPercentage>()
         val dbUpdate = dbQuery{
             ProductEntity.update({
                 ProductEntity.id eq request.id
             }) {
                 it[discountPercentage] = request.discountPercentage
             }
         }
         if (dbUpdate == 1){
             call.respond(ResponseData("discountPercentage Updated Successfully"))
         }else{
             call.respond(ResponseData("discountPercentage Updation failed"))
         }
     }

     post("/rating") {
         val request = call.receive<UpdateRating>()
         val dbUpdate = dbQuery{
             ProductEntity.update({
                 ProductEntity.id eq request.id
             }) {
                 it[rating] = request.rating
             }
         }
         if (dbUpdate == 1){
             call.respond(ResponseData("Rating Updated Successfully"))
         }else{
             call.respond(ResponseData("Rating Updation failed"))
         }
     }

     post("/stock") {
         val request = call.receive<UpdateStock>()
         val dbUpdate = dbQuery{
             ProductEntity.update({
                 ProductEntity.id eq request.id
             }) {
                 it[stock] = request.stock
             }
         }
         if (dbUpdate == 1){
             call.respond(ResponseData("Stock Updated Successfully"))
         }else{
             call.respond(ResponseData("Stock Updation failed"))
         }
     }

     post("/brand") {
         val request = call.receive<UpdateBrand>()
         val dbUpdate = dbQuery{
             ProductEntity.update({
                 ProductEntity.id eq request.id
             }) {
                 it[brand] = request.brand
             }
         }
         if (dbUpdate == 1){
             call.respond(ResponseData("Brand Updated Successfully"))
         }else{
             call.respond(ResponseData("Brand Updation failed"))
         }
     }

     post("/category") {
         val request = call.receive<UpdateCategory>()
         val dbUpdate = dbQuery{
             ProductEntity.update({
                 ProductEntity.id eq request.id
             }) {
                 it[category] = request.category
             }
         }
         if (dbUpdate == 1){
             call.respond(ResponseData("Category Updated Successfully"))
         }else{
             call.respond(ResponseData("Category Updation failed"))
         }
     }

     post("/thumbnail") {
         val request = call.receive<UpdateThumbnail>()
         val dbUpdate = dbQuery{
             ProductEntity.update({
                 ProductEntity.id eq request.id
             }) {
                 it[thumbnail] = request.thumbnail
             }
         }
         if (dbUpdate == 1){
             call.respond(ResponseData("Thumbnail Updated Successfully"))
         }else{
             call.respond(ResponseData("Thumbnail Updation failed"))
         }
     }

     post("/images") {
         val request = call.receive<UpdateImages>()
         val dbUpdate = dbQuery{
             ProductEntity.update({
                 ProductEntity.id eq request.id
             }) {
                 it[images] = request.images
             }
         }
         if (dbUpdate == 1){
             call.respond(ResponseData("Images Updated Successfully"))
         }else{
             call.respond(ResponseData("Images Updation failed"))
         }
     }
     */

