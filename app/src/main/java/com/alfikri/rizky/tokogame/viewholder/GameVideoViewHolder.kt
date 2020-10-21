package com.alfikri.rizky.tokogame.viewholder

import com.alfikri.rizky.tokogame.R
import com.alfikri.rizky.tokogame.model.GameVideoModel
import com.alfikri.rizky.tokogame.utils.GameTypeViewTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_videos.view.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameVideoHolder, v 0.1 9/28/2020 8:37 PM by Rizky Alfikri Rachmat
 */
class GameVideoViewHolder(
    private val gameVideo: GameVideoModel?,
    private val typeView: String,
    private val action: (String) -> Unit
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        if (typeView == GameTypeViewTarget.DATA_VIEW) {
            showVideoData(viewHolder)
        }
    }

    override fun getLayout(): Int {
        return if (typeView == GameTypeViewTarget.DATA_VIEW) {
            R.layout.item_videos
        } else {
            R.layout.item_videos_more
        }
    }

    private fun showVideoData(viewHolder: ViewHolder) {
        viewHolder.itemView.apply {
            Glide.with(context)
                .load(gameVideo?.imagePath)
                .apply(
                    RequestOptions()
                        .placeholder(R.color.colorMainWhite)
                        .error(R.drawable.ic_broken_image)
                )
                .into(iv_videos)

            setOnClickListener { gameVideo?.externalUrl?.let { key -> action(key) } }
        }
    }
}