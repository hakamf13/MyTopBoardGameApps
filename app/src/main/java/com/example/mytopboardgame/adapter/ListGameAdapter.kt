package com.example.mytopboardgame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mytopboardgame.data.Game
import com.example.mytopboardgame.databinding.ItemRowGameBinding

@Suppress("DEPRECATION")
class ListGameAdapter(private val listGame: ArrayList<Game>) : RecyclerView.Adapter<ListGameAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowGameBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listGame.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (gameName, gameDescription, gameCover) = listGame[position]
        Glide.with(holder.itemView.context)
            .load(gameCover)
            .circleCrop()
            .into(holder.binding.ivGameCover)
        holder.binding.apply {
            tvGameName.text = gameName
            tvGameDescription.text = gameDescription
        }
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listGame[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Game)
    }
}