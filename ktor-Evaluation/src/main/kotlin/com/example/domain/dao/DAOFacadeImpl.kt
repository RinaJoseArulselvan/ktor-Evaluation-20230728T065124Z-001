package com.example.domain.dao

import com.example.data.DatabaseFactory.dbQuery
import com.example.data.ProductEntity
import com.example.domain.models.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFacadeImpl : DAOFacade {

    override suspend fun insertProducts(productsList: List<Product>) {
        val dbInsert = dbQuery {
            productsList.map { product ->
                ProductEntity.insert {
                    it[id] = product.id
                    it[title] = product.title
                    it[description] = product.description
                    it[price] = product.price
                    it[discountPercentage] = product.discountPercentage
                    it[rating] = product.rating
                    it[stock] = product.stock
                    it[brand] = product.brand
                    it[category] = product.category
                    it[thumbnail] = product.thumbnail
                    it[images] = product.images.joinToString(",")
                }
            }

        }

    }

    override suspend fun searchProducts(requestData: RequestData): List<Product?> = dbQuery {
        ProductEntity.select {
            (ProductEntity.title eq requestData.query) or
                    (ProductEntity.description like "%${requestData.query}%")
        }.map {
            it[ProductEntity.images]?.split(",")?.toList()?.let { it1 ->
                Product(
                    it[ProductEntity.id], it[ProductEntity.title], it[ProductEntity.description],
                    it[ProductEntity.price], it[ProductEntity.discountPercentage], it[ProductEntity.rating],
                    it[ProductEntity.stock], it[ProductEntity.brand], it[ProductEntity.category],
                    it[ProductEntity.thumbnail], it1
                )
            }
        }

    }

    override suspend fun fetchProduct(requestData: RequestData): List<Product?> = dbQuery {
        ProductEntity.select {
            ProductEntity.category eq requestData.query
        }.map {
            it[ProductEntity.images]?.split(",")?.toList()?.let { it1 ->
                Product(
                    it[ProductEntity.id], it[ProductEntity.title], it[ProductEntity.description],
                    it[ProductEntity.price], it[ProductEntity.discountPercentage], it[ProductEntity.rating],
                    it[ProductEntity.stock], it[ProductEntity.brand], it[ProductEntity.category],
                    it[ProductEntity.thumbnail], it1
                )
            }
        }
    }

    override suspend fun fetchAllProduct(): List<Product?> = dbQuery {
        ProductEntity.selectAll()
            .map {
                it[ProductEntity.images]?.split(",")?.toList()?.let { it1 ->
                    Product(
                        it[ProductEntity.id], it[ProductEntity.title], it[ProductEntity.description],
                        it[ProductEntity.price], it[ProductEntity.discountPercentage], it[ProductEntity.rating],
                        it[ProductEntity.stock], it[ProductEntity.brand], it[ProductEntity.category],
                        it[ProductEntity.thumbnail], it1
                    )
                }
            }
    }

    override suspend fun deleteProducts(requestData: RequestData): Int = dbQuery {
        ProductEntity.deleteWhere {
            this.category eq requestData.query
        }
    }

    override suspend fun updateProduct(productRequestData: ProductRequestData): Int = dbQuery {
        ProductEntity.update({ProductEntity.id eq productRequestData.id}) {
            productRequestData.title?.let { it1->
                it[title] = it1
            }
            productRequestData.description?.let { it1->
                it[description] = it1
            }
            productRequestData.price?.let { it1->
                it[price] = it1
            }
            productRequestData.discountPercentage?.let { it1->
                it[discountPercentage] =it1
            }
            productRequestData.rating?.let { it1->
                it[rating] = it1
            }
            productRequestData.stock?.let { it1->
                it[stock] = it1
            }
            productRequestData.brand?.let { it1->
                it[brand] = it1
            }
            productRequestData.category?.let { it1->
                it[category] = it1
            }
            productRequestData.thumbnail?.let { it1->
                it[thumbnail] = it1
            }
            productRequestData.images?.let { it1->
                it[images] = it1
            }

        }
    }

    override suspend fun checkProductIdExists(id: Int): Boolean {
        val checkIdExists = dbQuery {
            ProductEntity.select {
                ProductEntity.id eq id
            }.firstOrNull()
        }
        return checkIdExists != null
    }

    override suspend fun checkProductCategoryExists(requestData: RequestData): Boolean {
        val checkCategoryExists = dbQuery {
            ProductEntity.select {
                ProductEntity.category eq requestData.query
            }.firstOrNull()
        }
        return checkCategoryExists != null
    }
}
