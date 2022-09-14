package br.com.nicolas.checkbitcoin.di

import br.com.nicolas.checkbitcoin.data.service.BitcoinService
import br.com.nicolas.checkbitcoin.utils.Constants
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val instance = module {
    single { providerService() }
}

private fun providerService(): BitcoinService {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BitcoinService::class.java)
}