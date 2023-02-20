package com.example.demoproductapinew.ui.products.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoproductapinew.R
import com.example.demoproductapinew.databinding.ItemProductsBinding
import com.example.demoproductapinew.response.ProductsItem

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var mItems = emptyList<ProductsItem>()

    private lateinit var mListener: View.OnClickListener

    private lateinit var mContext: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsAdapter.ViewHolder {

        mContext = parent.context
        val binding =
            ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    fun setOnClickListener(aListener: View.OnClickListener) {
        mListener = aListener
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        holder.bind(mItems[position], position)
    }

    fun setItems(aItem: ArrayList<ProductsItem>) {
        mItems = aItem
    }

    fun getItems() = mItems

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class ViewHolder(private val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(data: ProductsItem, aPosition: Int) {

            with(binding) {

                clRoot.setOnClickListener(mListener)

                clRoot.tag = aPosition

                ivFavIcon.setOnClickListener(mListener)

                ivFavIcon.tag = aPosition

                Glide.with(mContext).load(data.imageURL).into(ivImage)

                tvName.text = data.title

                tvPrice.text = "$" + data.price?.get(0)?.value.toString()

                if(data.isFavorite) {
                    ivFavIcon.setImageResource(R.drawable.fav_red)
                } else {
                    ivFavIcon.setImageResource(R.drawable.fav_grey)
                }

            }

        }

    }

}