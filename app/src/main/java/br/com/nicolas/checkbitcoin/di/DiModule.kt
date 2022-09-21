package br.com.nicolas.checkbitcoin.di

import androidx.room.Room
import br.com.nicolas.checkbitcoin.data.repository.BitcoinDataSource
import br.com.nicolas.checkbitcoin.data.repository.BitcoinRepository
import br.com.nicolas.checkbitcoin.data.service.BitcoinService
import br.com.nicolas.checkbitcoin.model.local.database.BitcoinDatabase
import br.com.nicolas.checkbitcoin.ui.home.HomeViewModel
import br.com.nicolas.checkbitcoin.utils.Constants
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val instance = module {

    single { providerService() }

    single<BitcoinDataSource> {
        BitcoinRepository(
            apiService = get()
        )
    }

    single {
        Room.databaseBuilder(
            get(),
            BitcoinDatabase::class.java,
            "bitcoin_database"
        ).build()
        get<BitcoinDatabase>().bitcoinDao()
    }

    viewModel {
        HomeViewModel(
            dataSource = get()
        )
    }
}

private fun providerService(): BitcoinService {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BitcoinService::class.java)
}