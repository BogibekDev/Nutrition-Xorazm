package dev.bogibek.nutritionxorazm.models

import com.google.gson.annotations.SerializedName

data class HistoryModel(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class ProductsItem(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class DataItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem>? = null
)
