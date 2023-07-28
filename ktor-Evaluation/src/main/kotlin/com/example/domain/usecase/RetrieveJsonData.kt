package com.example.domain.usecase

import com.example.domain.models.Product
import com.example.domain.models.ProductsData

import com.google.gson.Gson

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

fun productIntoDB(): List<Product> {
    val url = URL("https://dummyjson.com/products")
    val connection = url.openConnection()
    val inputStream = connection.getInputStream()
    val reader = BufferedReader(InputStreamReader(inputStream))
    val response = StringBuilder()
    var line: String?

    while (reader.readLine().also { line = it } != null) {
        response.append(line)
    }
    reader.close()
    val gson = Gson()
    val productsData = gson.fromJson(response.toString(), ProductsData::class.java)
    return productsData.products
}
