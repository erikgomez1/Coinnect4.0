package com.example.coinnect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinnect.Cryptocurrency
import com.example.coinnect.R
import com.example.coinnect.databinding.ItemCryptoBinding

class CryptoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemCryptoBinding.bind(view)

    fun render(
        cryptoItem: Cryptocurrency,
        onClickListener: (Cryptocurrency) -> Unit,
        onFavoriteClick: (Cryptocurrency, Int) -> Unit
    ) {
        binding.tvCryptoName.text = cryptoItem.cryptoName
        binding.tvCryptoPrice.text = cryptoItem.cryptoPrice.toString()

        Glide.with(binding.ivCrypto.context)
            .load(cryptoItem.imageUrl)
            .into(binding.ivCrypto)


        if (cryptoItem.isFavorite) {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_notfilld)
        }

        itemView.setOnClickListener {
            onClickListener(cryptoItem)
        }

        binding.btnFavorite.setOnClickListener {
            onFavoriteClick(cryptoItem, adapterPosition)
        }
    }
}


