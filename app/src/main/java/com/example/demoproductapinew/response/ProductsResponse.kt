package com.example.demoproductapinew.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.Objects

@Parcelize
data class ProductsResponse(

    @SerializedName("products")
    val products: List<ProductsItem?>? = null
) : Parcelable

@Parcelize
data class Messages(

    @SerializedName("sash")
    val sash: Sash? = null,

    @SerializedName("promotionalMessage")
    val promotionalMessage: String? = null,

    @SerializedName("secondaryMessage")
    val secondaryMessage: String? = null
) : Parcelable

@Parcelize
data class PriceItem(

    @SerializedName("message")
    val message: String? = null,

    @SerializedName("value")
    val value: Double? = null,

    @SerializedName("isOfferPrice")
    val isOfferPrice: Boolean? = null
) : Parcelable

@Parcelize
data class ProductsItem(

    @SerializedName("isInTrolley")
    val isInTrolley: Boolean? = null,

    var isFavorite: Boolean = false,

    @SerializedName("isInWishlist")
    val isInWishlist: Boolean? = null,

    @SerializedName("purchaseTypes")
    val purchaseTypes: List<PurchaseTypesItem?>? = null,

    @SerializedName("isDeliveryOnly")
    val isDeliveryOnly: Boolean? = null,

    @SerializedName("saleUnitPrice")
    val saleUnitPrice: Double? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("ratingCount")
    val ratingCount: Double? = null,

    @SerializedName("totalReviewCount")
    val totalReviewCount: Int? = null,

    @SerializedName("badges")
    val badges: List<String?>? = null,

    @SerializedName("isAddToCartEnable")
    val isAddToCartEnable: Boolean? = null,

    @SerializedName("isDirectFromSupplier")
    val isDirectFromSupplier: Boolean? = null,

    @SerializedName("price")
    val price: List<PriceItem?>? = null,

    @SerializedName("isFindMeEnable")
    val isFindMeEnable: Boolean? = null,

    @SerializedName("imageURL")
    val imageURL: String? = null,

    @SerializedName("messages")
    val messages: Messages? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("brand")
    val brand: String? = null,

    @SerializedName("addToCartButtonText")
    val addToCartButtonText: String? = null,

    @SerializedName("citrusId")
    val citrusId: String? = null
) : Parcelable

@Parcelize
data class Sash(
    val any: Map<String, String>? = null
) : Parcelable

@Parcelize
data class PurchaseTypesItem(

    @SerializedName("unitPrice")
    val unitPrice: Double? = null,

    @SerializedName("minQtyLimit")
    val minQtyLimit: Int? = null,

    @SerializedName("displayName")
    val displayName: String? = null,

    @SerializedName("maxQtyLimit")
    val maxQtyLimit: Int? = null,

    @SerializedName("cartQty")
    val cartQty: Int? = null,

    @SerializedName("purchaseType")
    val purchaseType: String? = null
) : Parcelable
