package com.example.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductsData(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
