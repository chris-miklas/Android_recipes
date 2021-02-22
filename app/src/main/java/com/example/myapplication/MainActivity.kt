package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent

// This activity starts when the app is opened. It contains 2 buttons - Search and Browse.
// The Browse button allows the user to just browse all the recipes.
// The Search button opens another activity which allows the user to either search by name or by ingredients.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch.setOnClickListener{
            val intent = Intent(this, SearchChoiceActivity::class.java);
            startActivity(intent);
        }

        btnBrowse.setOnClickListener{
            val intent = Intent(this, RecipeListActivity::class.java)
            intent.putExtra("url", "http://www.recipepuppy.com/api/?i=")
            startActivity(intent)
        }
    }
}