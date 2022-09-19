package br.com.nicolas.checkbitcoin.model

data class Coin(
    val image : String? = null,
    val name : String? = null,
    val symbol : String? = null,
    val price : Double? = null,
    val changeOneHour : String? = null,
    val changeOneDay : String? = null
)
