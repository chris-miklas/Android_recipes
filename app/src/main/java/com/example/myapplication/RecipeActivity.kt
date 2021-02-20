package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import android.text.Html.fromHtml
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
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
        val url = intent.getStringExtra("thumbnail")
        if(!TextUtils.isEmpty(url)) {
            Picasso.get()
                    .load(intent.getStringExtra("thumbnail"))
                    .resize(237,173)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .error(android.R.drawable.ic_menu_report_image)
                    .into(ivRecipeThumbnail)
        }else{
            Picasso.get().load(R.drawable.background).into(ivRecipeThumbnail)
        }

        recipeLink.movementMethod = LinkMovementMethod.getInstance();
    }
}