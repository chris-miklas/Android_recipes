package com.example.myapplication

// A class that holds info about a single recipe.
class Recipe() {
    var title: String? = null
    var link: String? = null
    var ingredients: String? = null
    var thumbnail: String? = null

    constructor(title: String, link: String,
                ingredients: String, thumbnail: String): this() {
        this.title = title
        this.link = link
        this.ingredients = ingredients
        this.thumbnail = thumbnail
    }
}