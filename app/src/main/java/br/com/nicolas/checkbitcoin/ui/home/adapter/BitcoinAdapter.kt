package br.com.nicolas.checkbitcoin.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nicolas.checkbitcoin.R
import br.com.nicolas.checkbitcoin.databinding.BitcoinItemBinding
import br.com.nicolas.checkbitcoin.model.Coin
import br.com.nicolas.checkbitcoin.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.DecimalFormat

class BitcoinAdapter(
    private val coinList: List<Coin>,
    private val onClickFavorite: () -> Unit
) : RecyclerView.Adapter<BitcoinAdapter.MainViewHolder>() {

    inner class MainViewHolder(
        private val binding: BitcoinItemBinding,
        private val onClickFavorite: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Coin?) {
            coin?.let { coinFinal ->
                loadImage(coinFinal.image.toString())
                binding.apply {
                    textViewTitleCoin.text = coinFinal.name
                    textViewSymbolCoin.text = coinFinal.symbol
                    coinFinal.price?.let { formatterCoinPrice(it) }
                    isCoinPriceNegative(
                        coinFinal.changeOneDay.toString(),
                        coinFinal.changeOneHour.toString()
                    )
                    imageViewFavoriteIcon.setOnClickListener {
                        onClickFavorite.invoke()
                    }
                }
            }
        }

        private fun formatterCoinPrice(price: Double) {
            val decimalFormat = DecimalFormat("###,##0.0000")
            val result = decimalFormat.format(price) + Constants.SYMBOL_DOLLAR
            binding.textViewPriceCoin.text = result
        }

        @SuppressLint("SetTextI18n")
        private fun isCoinPriceNegative(priceCoinOneDay: String, priceCoinOneHour: String) =
            binding.apply {
                val colorTextOneDay = if (priceCoinOneDay.toDouble() < 0) {
                    imageViewArrowOneDay.setImageResource(R.drawable.ic_baseline_arrow_down)
                    R.color.red
                } else {
                    imageViewArrowOneDay.setImageResource(R.drawable.ic_baseline_arrow_up)
                    R.color.green
                }

                val colorTextOneHour = if (priceCoinOneHour.toDouble() < 0) {
                    imageViewArrowOneHour.setImageResource(R.drawable.ic_baseline_arrow_down)
                    R.color.red
                } else {
                    imageViewArrowOneHour.setImageResource(R.drawable.ic_baseline_arrow_up)
                    R.color.green
                }

                textViewPriceChangeOneDay.apply {
                    setTextColor(context.getColor(colorTextOneDay))
                    text = "1D: $priceCoinOneDay"
                }

                textViewPriceChangeOneHour.apply {
                    setTextColor(context.getColor(colorTextOneHour))
                    text = "1H: $priceCoinOneHour"
                }
            }

        private fun setupIconArrowUp() {

        }

        private fun loadImage(url: String) {
            Glide.with(binding.imageViewIconCoin.context)
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_baseline_broken_image)
                .into(binding.imageViewIconCoin)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = BitcoinItemBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
        return MainViewHolder(view, onClickFavorite)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(coinList[position])
    }

    override fun getItemCount() = coinList.size
}