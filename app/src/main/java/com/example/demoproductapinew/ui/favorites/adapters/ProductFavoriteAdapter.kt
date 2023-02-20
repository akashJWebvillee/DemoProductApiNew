package com.example.demoproductapinew.ui.favorites.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoproductapinew.databinding.ItemProductFavBinding
import com.example.demoproductapinew.ui.roomdb.Product

class ProductFavoriteAdapter : RecyclerView.Adapter<ProductFavoriteAdapter.ViewHolder>() {

    private var mItems = ArrayList<Product>()

    private lateinit var mListener: View.OnClickListener

    private lateinit var mContext: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        mContext = parent.context
        val binding =
            ItemProductFavBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    fun setOnClickListener(aListener: View.OnClickListener) {
        mListener = aListener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mItems[position], position)
    }

    fun setItems(aItem: ArrayList<Product>) {
        mItems = aItem
    }

    fun getItems() = mItems

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class ViewHolder(private val binding: ItemProductFavBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: Product, aPosition: Int) {

            with(binding) {

                clRoot.setOnClickListener(mListener)

                clRoot.tag = aPosition

                btnRemoveFromCart.setOnClickListener(mListener)

                btnRemoveFromCart.tag = aPosition

                Glide.with(mContext).load(data.imageUrl).into(ivImage)

                tvName.text = data.name

                tvPrice.text = "$" + data.price

            }

        }

    }

}