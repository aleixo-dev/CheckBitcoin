package br.com.nicolas.checkbitcoin.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.com.nicolas.checkbitcoin.R
import br.com.nicolas.checkbitcoin.databinding.FragmentFavoriteBinding
import br.com.nicolas.checkbitcoin.model.Coin
import br.com.nicolas.checkbitcoin.model.CoinList
import br.com.nicolas.checkbitcoin.ui.favorite.adapter.FavoriteAdapter
import br.com.nicolas.checkbitcoin.utils.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private val binding by viewBinding(FragmentFavoriteBinding::bind)
    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var mainAdapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData()
        viewModel.state.observe(viewLifecycleOwner) {
            println("aleixo list: $it ")
            setupRecyclerView(it.coins)

        }
    }

    private fun setupRecyclerView(coinList: List<Coin>) {
        binding.recyclerViewFavorite.apply {
            setHasFixedSize(true)
            adapter = FavoriteAdapter(coinList)
        }
    }
}