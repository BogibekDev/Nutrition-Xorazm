package dev.bogibek.nutritionxorazm.models

data class User(
    val id: Long? = null,
    val username: String? = null,
    val password: String? = null,
    val height: Double? = null,
    val weight: Double? = null,
    val gender: String? = null,
    val plan: String? = "free",
)

data class UserRequest(
    val username: String? = null,
    val password: String? = null,
)
