package br.com.nicolas.checkbitcoin.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins_entity")
data class CoinEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "image") var image: String? = null,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "symbol") var symbol: String? = null,
    @ColumnInfo(name = "price") var price: Double? = null,
    @ColumnInfo(name = "change_one_hour") var changeOneHour: String? = null,
    @ColumnInfo(name = "change_one_day") var changeOneDay: String? = null
)
