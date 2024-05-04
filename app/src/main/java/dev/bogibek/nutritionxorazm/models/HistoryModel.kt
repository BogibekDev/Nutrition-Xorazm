package dev.bogibek.nutritionxorazm.models

data class HistoryModel(
    val total: Double,
    val data: ArrayList<MyHistory>,
    val success: Boolean,
    val message: String
)

data class MyHistory(
    val date: String,
    val products: ArrayList<ProductsItem>
)

data class ProductsItem(
    val total: Double,
    val name: String
)


