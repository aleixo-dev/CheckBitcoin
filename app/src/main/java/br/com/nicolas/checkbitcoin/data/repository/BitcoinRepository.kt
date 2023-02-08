package br.com.nicolas.checkbitcoin.data.repository

import androidx.room.Delete
import br.com.nicolas.checkbitcoin.data.local.CoinDao
import br.com.nicolas.checkbitcoin.data.local.model.CoinEntity
import br.com.nicolas.checkbitcoin.data.remote.BitcoinDataSource
import br.com.nicolas.checkbitcoin.data.service.BitcoinService
import br.com.nicolas.checkbitcoin.model.Coin
import br.com.nicolas.checkbitcoin.model.CoinList
import br.com.nicolas.checkbitcoin.model.mapper.toCoin
import br.com.nicolas.checkbitcoin.model.mapper.toCoinList
import br.com.nicolas.checkbitcoin.model.mapper.toDomain
import br.com.nicolas.checkbitcoin.model.mapper.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BitcoinRepository(
    private val apiService: BitcoinService,
    private val coinDao: CoinDao
) : BitcoinDataSource {

    override fun getBitcoins(): Flow<CoinList> = flow {
        val response = apiService.getCoins()
        emit(response.toDomain())
    }.flowOn(Dispatchers.IO)

    override fun getAllCoinInDatabase(): Flow<CoinList> = flow {
        val result = coinDao.getAll()
        emit(result.toCoinList())
    }.flowOn(Dispatchers.IO)

    override suspend fun saveCoin(coin: Coin) {
        println("aleixo: $coin")
        coinDao.insertCoin(coin.toEntity())
    }

    override fun deleteCoin(delete: Coin): Flow<Unit> = flow<Unit> {
        TODO("Not yet implemented")
    }.flowOn(Dispatchers.IO)
}