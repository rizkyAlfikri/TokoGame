package com.alfikri.rizky.tokogame.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alfikri.rizky.tokogame.R
import com.alfikri.rizky.tokogame.utils.generatePlatformIcon
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_platform.view.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version PlatformAdapter, v 0.1 9/24/2020 3:30 PM by Rizky Alfikri Rachmat
 */
class PlatformAdapter : ListAdapter<String, PlatformViewHolder>(platformItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_platform, parent, false)
        return PlatformViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (super.getItemCount() > MAX_DISPLAYED_ICON) {
            MAX_DISPLAYED_ICON
        } else {
            super.getItemCount()
        }
    }

    override fun onBindViewHolder(holder: PlatformViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    companion object {
        private const val MAX_DISPLAYED_ICON = 7
        private val platformItemCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem.contentEquals(newItem)
            }
        }
    }
}

class PlatformViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(platform: String) {
        val platformIcon = platform.generatePlatformIcon(itemView.context)
        Glide.with(itemView.context)
            .load(platformIcon)
            .into(itemView.iv_platform)
    }
}
