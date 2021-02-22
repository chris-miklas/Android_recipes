package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.search_name.*

// Allows the user to enter a string which should be present in the recipe which it then uses to search.
class SearchNameActivity : AppCompatActivity()  {
    val BASE_URL = "http://www.recipepuppy.com/api/?i="
    val QUERY: String = "&q="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_name)

        btnSearchName.setOnClickListener{
            val intent = Intent(this, RecipeListActivity::class.java)
            intent.putExtra("url", BASE_URL + QUERY + etName.text.replace(Regex(" "), "+"))
            startActivity(intent)
        }
    }

    // Reset the activity when another activity comes to the front.
    override fun onPause() {
        super.onPause()
        etName.text.clear()
    }
}