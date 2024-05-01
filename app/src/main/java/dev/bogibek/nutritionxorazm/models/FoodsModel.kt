package dev.bogibek.nutritionxorazm.models

data class FoodsModel(
    val date:String? = null,
    val name:String,
    val calorie:String,
    val foods:ArrayList<FoodsModel>
)