package com.example.domain.dao

import com.example.domain.models.*

interface DAOFacade {
    suspend fun insertProducts(productsList: List<Product>)
    suspend fun searchProducts(requestData: RequestData):List<Product?>
    suspend fun fetchProduct(requestData: RequestData):List<Product?>
    suspend fun fetchAllProduct():List<Product?>
    suspend fun deleteProducts(requestData: RequestData):Int
    suspend fun updateProduct(productRequestData: ProductRequestData):Int
    suspend fun checkProductIdExists(id:Int):Boolean
    suspend fun checkProductCategoryExists(requestData: RequestData):Boolean
}