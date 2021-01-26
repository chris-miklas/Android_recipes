package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import android.text.Html.fromHtml
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.recipe.*

// Displays data about one recipe.
class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe)

        recipeTitle.text = intent.getStringExtra("title")
        //recipeLink.text = "Link: <a href=" + intent.getStringExtra("link") + ">" + intent.getStringExtra("link") + "</a>"
        recipeLink.text = "Link: " + fromHtml("<a href='" + intent.getStringExtra("link") + "'>" + intent.getStringExtra("link") + "</a>")
        recipeIngredients.text = intent.getStringExtra("ingredients")

        recipeLink.movementMethod = LinkMovementMethod.getInstance();
    }
}