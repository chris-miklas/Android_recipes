package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.recipe_list.*

// Fetches and shows a list of recipes based on the url passed to it by SearchActivity.
class RecipeListActivity : AppCompatActivity() {
    var volleyRequest: RequestQueue? = null
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_list)

        recipeAdapter = RecipeAdapter(mutableListOf())
        rvRecipeItems.adapter = recipeAdapter
        rvRecipeItems.layoutManager = LinearLayoutManager(this)
        volleyRequest = Volley.newRequestQueue(this)

        getRecipe(intent.getStringExtra("url"))
    }

    private fun getRecipe(url: String?): Unit {
        val recipeRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response: JSONObject ->
                    try {
                        val resultArray = response.getJSONArray("results")
                        for (i in 0 until resultArray.length()) {

                            var recipeObj = resultArray.getJSONObject(i)
                            var title = recipeObj.getString("title")
                            var link = recipeObj.getString("href")
                            var thumbnail = recipeObj.getString("thumbnail")
                            var ingredients = recipeObj.getString("ingredients")

                            Log.d("RESULTS ===>", title)
                            var recipe = Recipe()
                            recipe.title = title
                            recipe.link = link
                            recipe.thumbnail = thumbnail
                            recipe.ingredients = "Ingredients: $ingredients"

                            recipeAdapter.addRecipe(recipe)

                        }
                        recipeAdapter!!.notifyDataSetChanged()

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                },
                { error: VolleyError? ->
                    try {
                        Log.d("Error", error.toString())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                })
        volleyRequest!!.add(recipeRequest)
    }
}