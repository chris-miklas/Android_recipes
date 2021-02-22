package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.search_choice.*

// Displays two buttons which offer the user two options for searching - either by name or by ingredients.
class SearchChoiceActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_choice)

        btnName.setOnClickListener{
            val intent = Intent(this, SearchNameActivity::class.java);
            startActivity(intent);
        }

        btnIngredients.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java);
            startActivity(intent);
        }
    }
}