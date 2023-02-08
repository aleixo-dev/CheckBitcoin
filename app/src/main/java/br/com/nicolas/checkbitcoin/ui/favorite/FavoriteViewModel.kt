package br.com.nicolas.checkbitcoin.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.checkbitcoin.data.remote.BitcoinDataSource
import br.com.nicolas.checkbitcoin.model.CoinList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val bitcoinDataSource: BitcoinDataSource
) : ViewModel() {

    private val _state = MutableLiveData<CoinList>()
    val state : LiveData<CoinList> get() = _state

    fun loadData() = viewModelScope.launch {
        bitcoinDataSource.getAllCoinInDatabase()
            .onStart {  }
            .catch {  }
            .collect {
            _state.value = it
        }
    }

}