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


class MainActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    private lateinit var recipeAdapter: RecipeAdapter
    val BASE_URL = "http://www.recipepuppy.com/api/?i="
    val QUERY: String = "&q="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var url: String? = null

        recipeAdapter = RecipeAdapter(mutableListOf())
        rvRecipeItems.adapter = recipeAdapter
        rvRecipeItems.layoutManager = LinearLayoutManager(this)

        btnSearch.setOnClickListener{
            val ingredients = etIngredients.text.toString()
            if(ingredients.isNotEmpty()){

                //http://www.recipepuppy.com/?i=carrot,%2Bchicken,-potato

                url = BASE_URL + ingredients

                volleyRequest = Volley.newRequestQueue(this)
                getRecipe(url.toString())

                etIngredients.text.clear()
            }
        }
        btnDelete.setOnClickListener{
            recipeAdapter.deleteRecipes()
        }
    }
    private fun getRecipe(url: String): Unit {
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