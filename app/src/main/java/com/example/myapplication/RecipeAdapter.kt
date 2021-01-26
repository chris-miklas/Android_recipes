package com.example.myapplication


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recipe.view.*
import androidx.appcompat.app.AppCompatActivity


class RecipeAdapter(
    private val recipes: MutableList<Recipe>
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_recipe,
                        parent,
                        false
                )
        )
    }

    fun addRecipe(todo: Recipe){
        recipes.add(todo)
        notifyItemInserted(recipes.size - 1)
    }

    fun deleteRecipes(){
        recipes.clear()
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val curTodo = recipes[position]
        holder.itemView.apply {
            tvRecipeTitle.text = curTodo.title
        }
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val intent = Intent(holder.itemView.context, RecipeActivity::class.java)
            intent.putExtra("title", curTodo.title)
            intent.putExtra("link", curTodo.link)
            intent.putExtra("ingredients", curTodo.ingredients)
            holder.itemView.context.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}