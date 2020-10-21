package com.alfikri.rizky.dynamic_game_cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alfikri.rizky.dynamic_game_cart.R
import com.alfikri.rizky.dynamic_game_cart.adapter.GameCartAdapter.GameCartViewHolder
import com.alfikri.rizky.tokogame.model.GameCartModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_game_cart_list.view.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameCartAdapter, v 0.1 10/10/2020 10:57 AM by Rizky Alfikri Rachmat
 */
class GameCartAdapter : ListAdapter<GameCartModel, GameCartViewHolder>(
    gameItemDiffCallback
) {

    private var listener: GameCartListener? = null

    fun setListener(listener: GameCartListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GameCartViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_game_cart_list, parent, false)

        return GameCartViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameCartViewHolder, position: Int) {
        val gameCartModel = getItem(position)
        holder.bindData(gameCartModel, listener)
    }

    inner class GameCartViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        fun bindData(
            gameCartModel: GameCartModel,
            listener: GameCartListener?
        ) {
            with(itemView) {
                tv_name.text = gameCartModel.name
                tv_genre.text = gameCartModel.genre
                tv_price.text = String.format(context.getString(R.string.dolar) + "${gameCartModel.price}" )
                cb_list.isChecked = gameCartModel.isChecked

                Glide.with(context)
                    .load(gameCartModel.backgroundImage)
                    .apply(
                        RequestOptions()
                            .placeholder(com.alfikri.rizky.tokogame.R.color.colorMainWhite)
                            .error(com.alfikri.rizky.tokogame.R.drawable.ic_broken_image)
                    )
                    .into(iv_game)

                cb_list.setOnCheckedChangeListener { _, _ ->
                    gameCartModel.isChecked = !gameCartModel.isChecked
                    listener?.updateCheckedGame(gameCartModel)
                }

                tv_remove.setOnClickListener {
                    listener?.deleteCartGame(gameCartModel)
                }

                setOnClickListener {
                    listener?.toDetailGameActivity(gameCartModel.gameId)
                }
            }
        }
    }

    companion object {
        private val gameItemDiffCallback = object : DiffUtil.ItemCallback<GameCartModel>() {
            override fun areItemsTheSame(
                oldItem: GameCartModel,
                newItem: GameCartModel
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: GameCartModel,
                newItem: GameCartModel
            ): Boolean {
                return oldItem.gameId == newItem.gameId
            }
        }
    }
}