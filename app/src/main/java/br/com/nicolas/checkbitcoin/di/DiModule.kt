package br.com.nicolas.checkbitcoin.di

import android.app.Application
import androidx.room.Room
import br.com.nicolas.checkbitcoin.data.local.ApplicationDatabase
import br.com.nicolas.checkbitcoin.data.local.CoinDao
import br.com.nicolas.checkbitcoin.data.remote.BitcoinDataSource
import br.com.nicolas.checkbitcoin.data.repository.BitcoinRepository
import br.com.nicolas.checkbitcoin.data.service.BitcoinService
import br.com.nicolas.checkbitcoin.ui.favorite.FavoriteViewModel
import br.com.nicolas.checkbitcoin.ui.home.HomeViewModel
import br.com.nicolas.checkbitcoin.utils.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val instance = module {

    single { providerService() }
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }

    single<BitcoinDataSource> {
        BitcoinRepository(apiService = get(), coinDao = get())
    }

    viewModel {
        HomeViewModel(dataSource = get())
    }

    viewModel {
        FavoriteViewModel(bitcoinDataSource = get())
    }
}

private fun provideDatabase(application: Application): ApplicationDatabase {
    return Room.databaseBuilder(application, ApplicationDatabase::class.java, "coin_database")
        .fallbackToDestructiveMigration()
        .build()
}

private fun provideDao(database: ApplicationDatabase): CoinDao {
    return database.coinDao()
}

private fun providerService(): BitcoinService {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BitcoinService::class.java)
}