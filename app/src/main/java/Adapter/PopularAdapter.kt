package Adapter

import Domain.ItemsModel
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.workoutapp.databinding.ViewholderPopularBinding
class PopularAdapter (val items: List<ItemsModel>):
RecyclerView.Adapter<PopularAdapter.Viewholder>(){

    lateinit var context: Context
    class Viewholder(val binding: ViewholderPopularBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapter.Viewholder {
        context=parent.context
        val binding= ViewholderPopularBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }



    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.subtitleTxt.text = items[position].description

        Glide.with(context)
            .load(items[position].url)
            .into(holder.binding.picture)

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = items.size

}