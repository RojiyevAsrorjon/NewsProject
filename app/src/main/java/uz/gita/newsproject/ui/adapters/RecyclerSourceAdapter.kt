package uz.gita.newsproject.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.newsproject.R
import uz.gita.newsproject.data.models.responses.SourcesItem

class RecyclerSourceAdapter : ListAdapter<SourcesItem, RecyclerSourceAdapter.ViewHolder>(DiffItem) {
    private var listener : ((String) -> Unit)? = null
    private var favouriteButtonListener : ((SourcesItem) -> Unit)? = null
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = itemView.findViewById<TextView>(R.id.sourceName)
        private val def = itemView.findViewById<TextView>(R.id.definitionSource)
        init {
            itemView.setOnClickListener {
                val string = getItem(absoluteAdapterPosition).url
                listener?.invoke(string)
            }
            itemView.findViewById<ImageButton>(R.id.favouriteButton).setOnClickListener {
                favouriteButtonListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }
        fun bind() {
            val data = getItem(absoluteAdapterPosition)
            name.text = data.name
            def.text = data.description

        }
    }

    object DiffItem : DiffUtil.ItemCallback<SourcesItem>(){
        override fun areItemsTheSame(oldItem: SourcesItem, newItem: SourcesItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SourcesItem, newItem: SourcesItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sources, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()

    fun setListener(block : (String) -> Unit){
        listener = block
    }

    fun setFavouriteButtonListener(block: (SourcesItem) -> Unit) {
        favouriteButtonListener = block
    }
}