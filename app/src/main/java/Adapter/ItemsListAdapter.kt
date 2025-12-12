package Adapter

import Domain.ItemsModel
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.workoutapp.SpecificItemActivity
import com.example.workoutapp.databinding.ViewholderItemListBinding
import com.example.workoutapp.databinding.ViewholderPopularBinding
class ItemsListAdapter (val items: List<ItemsModel>):
RecyclerView.Adapter<ItemsListAdapter.Viewholder>(){

    lateinit var context: Context
    class Viewholder(val binding: ViewholderItemListBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsListAdapter.Viewholder {
        context=parent.context
        val binding= ViewholderItemListBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }



    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.titleTxt.text = items[position].title

        Glide.with(context)
            .load(items[position].url)
            .into(holder.binding.picture)

        // When user clicks on specific exercise, takes them to activity
        holder.itemView.setOnClickListener {
            val intent = Intent(context, SpecificItemActivity::class.java)
            intent.putExtra("object", items[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size

}