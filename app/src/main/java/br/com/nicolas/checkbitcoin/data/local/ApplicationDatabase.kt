package br.com.nicolas.checkbitcoin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.nicolas.checkbitcoin.data.local.model.CoinEntity

@Database(entities = [CoinEntity::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}