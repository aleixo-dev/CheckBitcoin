package br.com.nicolas.checkbitcoin.model.mapper

import br.com.nicolas.checkbitcoin.data.local.model.CoinEntity
import br.com.nicolas.checkbitcoin.model.Coin
import br.com.nicolas.checkbitcoin.model.CoinList
import br.com.nicolas.checkbitcoin.model.remote.RemoteCoin
import br.com.nicolas.checkbitcoin.model.remote.RemoteCoinList

fun RemoteCoinList.toDomain() = CoinList(
    coins = remoteCoins.map { it.toDomain() }
)

fun RemoteCoin.toDomain() = Coin(
    image = icon,
    name = name,
    symbol = symbol,
    price = price,
    changeOneDay = priceChange1d.toString(),
    changeOneHour = priceChange1h.toString()
)

fun List<CoinEntity>.toCoinList() : CoinList {
    val entities = mutableListOf<Coin>()
    for (entity in this){
        entities.add(entity.toCoin())
    }
    return CoinList(coins = entities)
}

fun CoinEntity.toCoin() = Coin(
    image = image,
    name = name,
    symbol = symbol,
    price = price,
    changeOneDay = changeOneDay.toString(),
    changeOneHour = changeOneHour.toString()
)

fun Coin.toEntity() = CoinEntity(
    image = image,
    name = name,
    symbol = symbol,
    price = price,
    changeOneDay = changeOneDay.toString(),
    changeOneHour = changeOneHour.toString()
)