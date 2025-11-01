package Adapter

import Domain.CategoryModel
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Looper
import android.os.Looper.getMainLooper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.postDelayed
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.DashboardActivity
import com.example.workoutapp.ItemsListActivity
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ViewholderCategoryBinding
import java.util.logging.Handler

class CategoryAdapter(val items: MutableList<CategoryModel>):
        RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

            private lateinit var context: Context
            private var selectedPosition = -1
            private var lastSelectedPosition = -1

    inner class ViewHolder(val binding: ViewholderCategoryBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        context=parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val item=items[position]
        holder.binding.titleCat.text=item.title

        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            android.os.Handler(getMainLooper()).postDelayed({
                val intent = Intent(context, ItemsListActivity::class.java).apply {
                    putExtra("id", item.id.toString())
                    putExtra("title", item.title)
                }
                ContextCompat.startActivity(context,intent,null)
            }, 500)
        }
        // When button is clicked, changes colour. If not then stays white
        if (selectedPosition == position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.roundedtextbtn)
        } else {
            holder.binding.titleCat.setBackgroundResource(R.drawable.white_full_bg)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int = items.size
    }

