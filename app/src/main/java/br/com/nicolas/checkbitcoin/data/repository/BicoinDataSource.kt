package br.com.nicolas.checkbitcoin.data.repository

import br.com.nicolas.checkbitcoin.model.remote.RemoteCoinList
import kotlinx.coroutines.flow.Flow

interface BitcoinDataSource {

    fun getBitcoins() : Flow<RemoteCoinList>

}