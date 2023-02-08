package br.com.nicolas.checkbitcoin.data.local

import androidx.room.*
import br.com.nicolas.checkbitcoin.data.local.model.CoinEntity

@Dao
interface CoinDao {

    @Query("SELECT * FROM coins_entity")
    suspend fun getAll(): List<CoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoin(vararg coins: CoinEntity)

    @Delete
    suspend fun delete(coin: CoinEntity)
}