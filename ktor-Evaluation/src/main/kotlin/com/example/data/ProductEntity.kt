package com.example.data

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object ProductEntity : Table("products"){
    val id:Column<Int> = integer("id")
    val title:Column<String> = varchar("title",255)
    val description:Column<String> = varchar("description" ,255)
    val price:Column<Double> = double("price")
    val discountPercentage:Column<Double> = double("discountPercentage")
    val rating:Column<Double> = double("rating")
    val stock:Column<Int> = integer("stock")
    val brand:Column<String> = varchar("brand",255)
    val category:Column<String> = varchar("category" , 255)
    val thumbnail:Column<String> = varchar("thumbnail" , 255)
    val images = text("images").nullable()

    override val primaryKey = PrimaryKey(id)

}

