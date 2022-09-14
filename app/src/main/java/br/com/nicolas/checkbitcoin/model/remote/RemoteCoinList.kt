package br.com.nicolas.checkbitcoin.model.remote


import com.google.gson.annotations.SerializedName

data class RemoteCoinList(
    @SerializedName("coins")
    val remoteCoins: List<RemoteCoin>
)