package com.alfikri.rizky.tokogame.viewholder

import com.alfikri.rizky.tokogame.R
import com.alfikri.rizky.tokogame.model.GameScreenshotModel
import com.alfikri.rizky.tokogame.utils.GameTypeViewTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_screenshot.view.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameScreenshotViewHolder, v 0.1 9/28/2020 8:17 PM by Rizky Alfikri Rachmat
 */
class GameScreenshotViewHolder(private val gameScreenshot: GameScreenshotModel?, private val typeView: String): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        if (typeView == GameTypeViewTarget.DATA_VIEW) {
            showScrennshotData(viewHolder)
        }
    }

    override fun getLayout(): Int {
        return if (typeView == GameTypeViewTarget.DATA_VIEW) {
            R.layout.item_screenshot
        } else {
            R.layout.item_screenshot_info
        }
    }

    private fun showScrennshotData(viewHolder: ViewHolder) {
        viewHolder.itemView.apply {
            Glide.with(context)
                .load(gameScreenshot?.imagePath)
                .apply(
                    RequestOptions()
                        .placeholder(R.color.colorMainWhite)
                        .error(R.drawable.ic_broken_image)
                )
                .into(iv_screenshoot)
        }
    }
}