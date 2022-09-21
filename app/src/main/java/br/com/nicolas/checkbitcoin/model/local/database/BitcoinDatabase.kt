package br.com.nicolas.checkbitcoin.model.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.nicolas.checkbitcoin.model.local.BitcoinDao

@Database(entities = [LocalCoin::class], version = 1)
abstract class BitcoinDatabase : RoomDatabase() {
    abstract fun bitcoinDao(): BitcoinDao
}