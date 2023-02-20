package com.example.demoproductapinew.ui.products

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.demoproductapinew.R
import com.example.demoproductapinew.databinding.ActivityProductDetailsBinding
import com.example.demoproductapinew.response.ProductsItem

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding

    private var mData: ProductsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        mData = intent.getParcelableExtra("data")!!

        binding.tvName.text = mData?.title

        Glide.with(this).load(mData?.imageURL).into(binding.ivImage)

        binding.tvPrice.text = "$" + mData?.price?.get(0)?.value.toString()

        if(mData?.isFavorite!!) {
            binding.favoriteIcon.setImageResource(R.drawable.fav_red)
        } else {
            binding.favoriteIcon.setImageResource(R.drawable.fav_grey)
        }

        binding.favoriteIcon.setOnClickListener {

            if(mData?.isFavorite!!) {
                mData?.isFavorite = false
                binding.favoriteIcon.setImageResource(R.drawable.fav_grey)
            } else {
                mData?.isFavorite = true
                binding.favoriteIcon.setImageResource(R.drawable.fav_red)
            }

        }

    }

}