package br.com.nicolas.checkbitcoin.data.repository

import br.com.nicolas.checkbitcoin.data.service.BitcoinService
import br.com.nicolas.checkbitcoin.model.remote.RemoteCoinList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BitcoinRepository(
    private val apiService: BitcoinService
) : BitcoinDataSource {

    override fun getBitcoins(): Flow<RemoteCoinList> = flow {
        emit(apiService.getCoins())
    }
}