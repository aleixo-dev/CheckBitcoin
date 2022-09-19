package br.com.nicolas.checkbitcoin.data.repository

import br.com.nicolas.checkbitcoin.data.service.BitcoinService
import br.com.nicolas.checkbitcoin.model.CoinList
import br.com.nicolas.checkbitcoin.model.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BitcoinRepository(
    private val apiService: BitcoinService
) : BitcoinDataSource {

    override fun getBitcoins(): Flow<CoinList> = flow {
        val response = apiService.getCoins()
        emit(response.toDomain())
    }
}