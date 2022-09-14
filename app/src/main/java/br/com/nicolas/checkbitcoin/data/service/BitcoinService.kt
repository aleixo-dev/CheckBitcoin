package br.com.nicolas.checkbitcoin.data.service

import br.com.nicolas.checkbitcoin.model.remote.RemoteCoinList
import retrofit2.http.GET

interface BitcoinService {

    @GET("public/v1/coins")
    suspend fun getCoins() : RemoteCoinList

}