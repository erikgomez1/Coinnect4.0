package com.example.coinnect.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coinnect.Cryptocurrency
import com.example.coinnect.R
import com.example.coinnect.databinding.ItemCryptoBinding



class CryptoAdapter(
    private var cryptoList: List<Cryptocurrency>,
    private val onClickListener: (Cryptocurrency) -> Unit,
    private val onFavoriteClick: (Cryptocurrency, Int) -> Unit
) : RecyclerView.Adapter<CryptoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CryptoViewHolder(
            layoutInflater.inflate(R.layout.item_crypto, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val cryptoItem = cryptoList[position]
        holder.render(cryptoItem, onClickListener, onFavoriteClick)
    }

    override fun getItemCount(): Int = cryptoList.size

    // Method to update the crypto list
    fun updateCryptoList(newList: List<Cryptocurrency>) {
        cryptoList = newList
        notifyDataSetChanged()  // Refresh the entire list
    }

    // Method to update a single item in the list
    fun updateItem(position: Int) {
        notifyItemChanged(position)  // Notify that only the item at 'position' has changed
    }
}

