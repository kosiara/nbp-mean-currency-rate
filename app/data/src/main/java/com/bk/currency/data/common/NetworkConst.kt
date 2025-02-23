package com.bk.currency.data.common

object NetworkConst {
    const val BASE_URL = "https://api.nbp.pl/api/";
}

enum class NbpTableName {
    A, B;

    companion object {
        fun fromString(tbpName: String): NbpTableName {
            return when (tbpName) {
                "A" -> A
                "B" -> B
                else -> A
            }

        }
    }
}