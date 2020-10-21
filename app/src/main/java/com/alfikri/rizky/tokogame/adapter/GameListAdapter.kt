package com.alfikri.rizky.tokogame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alfikri.rizky.tokogame.adapter.GameListAdapter.GameListViewHolder
import com.alfikri.rizky.tokogame.databinding.ItemGameListBinding
import com.alfikri.rizky.tokogame.model.GameModel
import com.alfikri.rizky.tokogame.utils.toList


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameListAdapter, v 0.1 9/23/2020 8:03 PM by Rizky Alfikri Rachmat
 */
class GameListAdapter(private val platformAdapter: PlatformAdapter) :
    RecyclerView.Adapter<GameListViewHolder>() {

    private val gameList = mutableListOf<GameModel>()

    private var listener: ((Int) -> Unit)? = null

    fun setGameData(list: List<GameModel>) {
        gameList.addAll(list)
        notifyItemInserted(gameList.size + list.size)
    }

    fun clearData() {
        gameList.clear()
        notifyDataSetChanged()
    }

    fun setListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val binding =
            ItemGameListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.bindData(gameList[position], listener)
    }

    override fun getItemCount(): Int = gameList.size

    inner class GameListViewHolder(private val binding: ItemGameListBinding) :
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
}