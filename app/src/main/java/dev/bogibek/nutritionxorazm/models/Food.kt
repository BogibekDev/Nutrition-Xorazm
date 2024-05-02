package dev.bogibek.nutritionxorazm.models

data class FoodListResponse(
    val foods: ArrayList<Product>,
    val salads: ArrayList<Product>,
    val desserts: ArrayList<Product>,
    val drinks: ArrayList<Product>,
    val fruits: ArrayList<Product>,
)

data class Product(
    val id: Long,
    val name: String,
    val photo: String,
    val category: String,
    val dose: String,
    val cps: Double,
    val description: String,
)