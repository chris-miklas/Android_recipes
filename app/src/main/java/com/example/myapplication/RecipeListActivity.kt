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
import kotlinx.android.synthetic.main.recipe_list.btnNextPage


// Fetches and shows a list of recipes based on the url passed to it by another activity.
class RecipeListActivity : AppCompatActivity() {
    var volleyRequest: RequestQueue? = null
    var lastPage = false
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_list)

        recipeAdapter = RecipeAdapter(mutableListOf())
        rvRecipeItems.adapter = recipeAdapter
        rvRecipeItems.layoutManager = LinearLayoutManager(this)
        volleyRequest = Volley.newRequestQueue(this)

        var page = 1
        val url = intent.getStringExtra("url")

        btnNextPage.setOnClickListener{
            if(!lastPage){
                page += 1
                recipeAdapter.deleteRecipes()
                getRecipe("$url&p=$page")
            }

        }
        btnPrevPage.setOnClickListener{
            if(page > 1) {
                page -= 1
                recipeAdapter.deleteRecipes()
                getRecipe("$url&p=$page")
            }

        }

        getRecipe("$url&p=$page")
    }

    private fun getRecipe(url: String?){
        val recipeRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response: JSONObject ->
                    try {
                        val resultArray = response.getJSONArray("results")
                        lastPage = resultArray.length() == 0
                        for (i in 0 until resultArray.length()) {

                            val recipeObj = resultArray.getJSONObject(i)
                            val title = recipeObj.getString("title")
                            val link = recipeObj.getString("href")
                            val thumbnail = recipeObj.getString("thumbnail")
                            val ingredients = recipeObj.getString("ingredients")

                            Log.d("RESULTS ===>", title)
                            val recipe = Recipe()
                            recipe.title = title
                            recipe.link = link
                            recipe.thumbnail = thumbnail
                            recipe.ingredients = "Ingredients: $ingredients"

                            recipeAdapter.addRecipe(recipe)

                        }
                        recipeAdapter.notifyDataSetChanged()

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