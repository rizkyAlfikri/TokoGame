package com.alfikri.rizky.dynamic_game_favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alfikri.rizky.dynamic_game_favorite.R
import com.alfikri.rizky.tokogame.model.GameFavoriteModel
import kotlinx.android.synthetic.main.item_game_favorite.view.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameFavoriteAdapter, v 0.1 10/8/2020 7:25 PM by Rizky Alfikri Rachmat
 */
class GameFavoriteAdapter :
    ListAdapter<GameFavoriteModel, GameFavoriteAdapter.GameFavoriteViewHolder>(
        gameItemDiffCallback
    ) {

    private var gameFavoriteListener: GameFavoriteListener? = null

    fun setFavoriteListener(listener: GameFavoriteListener) {
        this.gameFavoriteListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameFavoriteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_game_favorite, parent, false)

        return GameFavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameFavoriteViewHolder, position: Int) {
        holder.bindData(getItem(position), gameFavoriteListener)
    }

    inner class GameFavoriteViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        fun bindData(gameFavoriteModel: GameFavoriteModel, listener: GameFavoriteListener?) {
            itemView.apply {
                tv_name.text = gameFavoriteModel.name
                tv_genre.text = gameFavoriteModel.genre
                tv_price.text =
                    String.format(context.getString(R.string.dolar) + "${gameFavoriteModel.price}")
                rate_bar.rating = gameFavoriteModel.rating.toFloat()

                setOnClickListener {
                    listener?.onClickGameFavorite(gameFavoriteModel.gameId)
                }

                iv_favorite.setOnClickListener {
                    listener?.onClickDeleteFavorite(gameFavoriteModel)
                }
            }
        }
    }

    companion object {
        private val gameItemDiffCallback = object : DiffUtil.ItemCallback<GameFavoriteModel>() {
            override fun areItemsTheSame(
                oldItem: GameFavoriteModel,
                newItem: GameFavoriteModel
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: GameFavoriteModel,
                newItem: GameFavoriteModel
            ): Boolean {
                return oldItem.gameId == newItem.gameId
            }
        }
    }
}