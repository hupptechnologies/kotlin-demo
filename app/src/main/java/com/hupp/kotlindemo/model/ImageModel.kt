package com.hupp.kotlindemo.model

/**
 * Created by hupp on 16/10/18.
 */
class ImageModel {
    var name: String? = null
    var image: Int? = null
    var description: String? = null

    constructor(name: String, image: Int) {
        this.name = name
        this.image = image
    }
}