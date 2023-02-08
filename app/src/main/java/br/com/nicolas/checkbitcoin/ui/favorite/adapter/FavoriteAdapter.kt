package br.com.nicolas.checkbitcoin.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nicolas.checkbitcoin.R
import br.com.nicolas.checkbitcoin.databinding.FavoriteItemBinding
import br.com.nicolas.checkbitcoin.model.Coin
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FavoriteAdapter(
    private val coins: List<Coin>
) : RecyclerView.Adapter<FavoriteAdapter.MainViewHolder>() {

    private val myFavoriteCoins : List<Coin>
        get() = coins.sortedBy { it.name }

    inner class MainViewHolder(private val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(coin: Coin) {
            loadImage(coin.image ?: return)
            binding.textViewFavoriteCoinName.text = coin.name
        }

        private fun loadImage(url: String) {
            Glide.with(binding.imageViewFavoriteCoin.context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_baseline_broken_image)
                .into(binding.imageViewFavoriteCoin)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = FavoriteItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(view)
    }

    override fun getItemCount() = myFavoriteCoins.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(myFavoriteCoins[position])
    }
}