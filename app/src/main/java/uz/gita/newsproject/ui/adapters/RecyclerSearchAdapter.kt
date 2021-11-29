package uz.gita.newsproject.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.newsproject.R
import uz.gita.newsproject.data.models.responses.ArticlesItem

class RecyclerSearchAdapter : ListAdapter<ArticlesItem, RecyclerSearchAdapter.ViewHolder>(DiffItem) {
    private var favouriteListener :((ArticlesItem) -> Unit)? = null
    object DiffItem : DiffUtil.ItemCallback<ArticlesItem>() {
        override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem.source.id == newItem.source.id
        }

        override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
            return oldItem == newItem
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageNews = view.findViewById<ImageView>(R.id.imageNews)
        val title : TextView = view.findViewById<TextView>(R.id.titleNews)
        fun bind() {
            val data = getItem(absoluteAdapterPosition)
            Glide.with(itemView)
                .load(data.urlToImage)
                .centerCrop()
                .placeholder(R.color.white)
                .into(imageNews)

            title.text = data.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
}