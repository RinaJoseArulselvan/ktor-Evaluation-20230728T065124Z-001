package com.example.data

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    private fun hikari():HikariDataSource{

        val config = HikariConfig().apply {
            jdbcUrl         = "jdbc:postgresql://localhost:5432/books"
            driverClassName = "org.postgresql.Driver"
            username        = "postgres"
            password        = "rina2002"
            maximumPoolSize = 10
        }
        return HikariDataSource(config)
    }

    fun init (){
        val database = Database.connect(hikari())
        transaction(database) {
            SchemaUtils.create(ProductEntity)
        }
    }


    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

}