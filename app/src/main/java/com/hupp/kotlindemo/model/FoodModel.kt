package com.hupp.kotlindemo.model

import android.media.Image

/**
 * Created by hupp on 16/10/18.
 */
class FoodModel {
    var name: String? = null
    var image: Int? = null
    var description: String? = null
    var rating: Float? = null
    var amount: Float? = null
    var imageList: List<Int>? = null

    constructor(name: String, description: String, image: Int, rating: Float, amount: Float, imageList: List<Int>) {
        this.name = name
        this.description = description
        this.image = image
        this.rating = rating
        this.amount = amount
        this.imageList = imageList
    }
}