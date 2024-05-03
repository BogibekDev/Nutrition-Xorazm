package dev.bogibek.nutritionxorazm.models

data class AdviceResponse(
    val success: Boolean,
    val total: Double,
    val need: Double,
    val recommend: ArrayList<Recommend>,
    val message: String,
)

data class Recommend(
    val name: String,
    val ccal: Int,
)