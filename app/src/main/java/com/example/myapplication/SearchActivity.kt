package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.search_activity.*

// This activity allows the user to enter ingredients and then puts together a url
// with the given ingredients inserted which it then passes to RecipeListActivity.
class SearchActivity : AppCompatActivity() {
    val BASE_URL = "http://www.recipepuppy.com/api/?i="
    var ingredients: String = ""
    var prefix: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        ingredients = ""

        btnNext.setOnClickListener{
            val newIngredients = etIngredients.text.toString()

            if (prefix == "") {
                prefixIngredients(newIngredients)
                etIngredients.text.clear()
                prefix = "%2B"
                stepText.text = "Necessary ingredients"
            }
            else if (prefix == "%2B") {
                prefixIngredients(newIngredients)
                etIngredients.text.clear()
                prefix = "-"
                stepText.text = "Forbidden ingredients"
                btnNext.text = "Search"
            }
            else {
                prefixIngredients(newIngredients)
                etIngredients.text.clear()
                ingredients = ingredients.dropLast(1)

                val intent = Intent(this, RecipeListActivity::class.java)
                intent.putExtra("url", BASE_URL + ingredients)
                startActivity(intent)
            }
        }
    }

    // Reset the activity when another activity comes to the front.
    override fun onPause() {
        super.onPause()
        ingredients = ""
        prefix = ""
        stepText.text = "Nice-to-have ingredients"
        btnNext.text = "Next"
    }

    private fun prefixIngredients(newIngredients: String) {
        newIngredients.split(',', ' ').forEach {
            if (it != "") {
                ingredients += "$prefix$it,"
            }
        }
    }
}