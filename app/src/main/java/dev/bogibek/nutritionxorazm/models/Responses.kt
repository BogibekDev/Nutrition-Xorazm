package dev.bogibek.nutritionxorazm.models

data class Responses<T>(
    val success: Boolean,
    val data: T,
    val message: String,
    val total: Double? = null
)
