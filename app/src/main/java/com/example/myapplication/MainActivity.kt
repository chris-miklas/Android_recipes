package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject
import android.content.Intent

// This activity starts when the app is opened. It contains 2 buttons - Search and Browse.
// Search allows for custom searching where the user can specify which ingredients are necessary and
// which are forbidden. The Browse button allows the user to just browse all the recipes.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var url: String? = null

        btnSearch.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java);
            startActivity(intent);
        }
        btnBrowse.setOnClickListener{

        }
    }
}