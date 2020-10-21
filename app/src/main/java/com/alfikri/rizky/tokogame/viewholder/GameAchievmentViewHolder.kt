package com.alfikri.rizky.tokogame.viewholder

import com.alfikri.rizky.tokogame.R
import com.alfikri.rizky.tokogame.model.GameAchievmentModel
import com.alfikri.rizky.tokogame.utils.GameTypeViewTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_achievment.view.*
import kotlinx.android.synthetic.main.item_achievment.view.iv_achievment
import kotlinx.android.synthetic.main.item_achievment.view.tv_achievement_title
import kotlinx.android.synthetic.main.item_achievment_more.view.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameAchievmentViewHolder, v 0.1 9/28/2020 8:01 PM by Rizky Alfikri Rachmat
 */
class GameAchievmentViewHolder(
    private val gameAchievment: GameAchievmentModel?,
    private val totalAchievment: Int?,
    private val typeView: String
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        if (typeView == GameTypeViewTarget.DATA_VIEW) {
            showAchievmentData(viewHolder)
        } else {
            showAchievmentMoreData(viewHolder)
        }
    }

    override fun getLayout(): Int {
        return if (typeView == GameTypeViewTarget.DATA_VIEW) {
            R.layout.item_achievment
        } else {
            R.layout.item_achievment_more
        }
    }

    private fun showAchievmentData(viewHolder: ViewHolder) {
        viewHolder.itemView.apply {
            tv_achievement_title.text = gameAchievment?.name
            tv_percentage.text = gameAchievment?.percent
            tv_desc_achievment.text = gameAchievment?.description

            Glide.with(context)
                .load(gameAchievment?.imagePath)
                .apply(
                    RequestOptions()
                        .placeholder(R.color.colorMainWhite)
                        .error(R.drawable.ic_broken_image)
                )
                .into(iv_achievment)
        }
    }

    private fun showAchievmentMoreData(viewHolder: ViewHolder) {
        viewHolder.itemView.apply {
            tv_total_achievments.text =
                String.format("$totalAchievment ${context.getString(R.string.total_achievments)}")
        }
    }
}