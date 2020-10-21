package com.alfikri.rizky.tokogame.viewholder

import com.alfikri.rizky.tokogame.R
import com.alfikri.rizky.tokogame.model.GameModel
import com.alfikri.rizky.tokogame.utils.GameTypeViewTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_game_promo.view.*

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameSimilarVisualy, v 0.1 9/28/2020 8:38 PM by Rizky Alfikri Rachmat
 */
class GameSimilarVisualyViewHolder(private val game: GameModel?, private val typeView: String,  private val action: (Int) -> Unit): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        if (typeView == GameTypeViewTarget.DATA_VIEW) {
            showGameSimilarData(viewHolder)
        }
    }

    override fun getLayout(): Int {
        return if (typeView == GameTypeViewTarget.DATA_VIEW) {
            R.layout.item_game_promo
        } else {
            R.layout.item_game_similar_more
        }
    }

    private fun showGameSimilarData(viewHolder: ViewHolder) {
        viewHolder.itemView.apply {
            tv_name.text = game?.name
            tv_genre.text = game?.genre
            rate_bar.rating = game?.rating?.toFloat() ?: 0F
            Glide.with(context)
                .load(game?.backgroundImage)
                .apply(
                    RequestOptions()
                        .placeholder(R.color.colorMainWhite)
                        .error(R.drawable.ic_broken_image)
                )
                .into(iv_game)

            setOnClickListener { game?.let { data -> action(data.gameId) } }
        }
    }

}