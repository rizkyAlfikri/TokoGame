package com.alfikri.rizky.tokogame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alfikri.rizky.tokogame.adapter.GamePromoAdapter.GamePromoViewHolder
import com.alfikri.rizky.tokogame.databinding.ItemGamePromoBinding
import com.alfikri.rizky.tokogame.model.GameModel
import com.alfikri.rizky.tokogame.utils.toList


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GamePromoAdapter, v 0.1 9/23/2020 2:23 PM by Rizky Alfikri Rachmat
 */
class GamePromoAdapter(private val platformAdapter: PlatformAdapter) :
    ListAdapter<GameModel, GamePromoViewHolder>(gameItemDiffCallback) {

    private var listener: ((Int) -> Unit)? = null

    fun setListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamePromoViewHolder {
        val binding =
            ItemGamePromoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GamePromoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GamePromoViewHolder, position: Int) {
        holder.bindData(getItem(position), listener)
    }

    inner class GamePromoViewHolder(private val binding: ItemGamePromoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(game: GameModel, listener: ((Int) -> Unit)?) {
            platformAdapter.submitList(game.platform.toList())
            binding.gameModel = game
            binding.rvPlatform.apply {
                layoutManager =
                    LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, true)
                adapter = platformAdapter
            }

            binding.root.setOnClickListener {
                if (listener != null) {
                    listener(game.gameId)
                }
            }
        }
    }

    companion object {
        private val gameItemDiffCallback = object : DiffUtil.ItemCallback<GameModel>() {
            override fun areItemsTheSame(
                oldItem: GameModel,
                newItem: GameModel
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: GameModel,
                newItem: GameModel
            ): Boolean {
                return oldItem.gameId == newItem.gameId
            }
        }
    }
}