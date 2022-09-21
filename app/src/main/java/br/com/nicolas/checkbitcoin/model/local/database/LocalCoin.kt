package br.com.nicolas.checkbitcoin.model.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalCoin(
    @PrimaryKey val id : Int,
    @ColumnInfo(name = "coin_image") val image : String,
    @ColumnInfo(name = "coin_name") val name : String,
    @ColumnInfo(name = "coin_symbol") val symbol : String,
    @ColumnInfo(name = "coin_price") val price : Double,
    @ColumnInfo(name = "coin_price_one_day") val priceOneDay : Double,
    @ColumnInfo(name = "coin_price_one_hour") val priceOneHour : Double,
)
