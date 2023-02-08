package br.com.nicolas.checkbitcoin.data.remote

import androidx.room.Delete
import br.com.nicolas.checkbitcoin.data.local.model.CoinEntity
import br.com.nicolas.checkbitcoin.model.Coin
import br.com.nicolas.checkbitcoin.model.CoinList
import kotlinx.coroutines.flow.Flow

interface BitcoinDataSource {

    fun getBitcoins() : Flow<CoinList>
    fun getAllCoinInDatabase() : Flow<CoinList>
    suspend fun saveCoin(coin: Coin)
    fun deleteCoin(delete: Coin) : Flow<Unit>

}