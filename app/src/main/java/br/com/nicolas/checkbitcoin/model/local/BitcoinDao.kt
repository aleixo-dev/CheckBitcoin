package br.com.nicolas.checkbitcoin.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.nicolas.checkbitcoin.model.local.database.LocalCoin

@Dao
interface BitcoinDao {

    @Query("SELECT * FROM localcoin")
    fun getAllCoin(): List<LocalCoin>

    @Insert
    fun saveCoin(coin: LocalCoin)

    @Delete
    fun deleteCoin(coin: LocalCoin)

}