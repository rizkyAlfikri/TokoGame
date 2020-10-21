package com.alfikri.rizky.tokogame.adapter

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.alfikri.rizky.tokogame.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version BindingAdapter, v 0.1 9/23/2020 4:04 PM by Rizky Alfikri Rachmat
 */
@BindingAdapter("imagePath")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .apply(
            RequestOptions()
                .placeholder(R.color.colorMainWhite)
                .error(R.drawable.ic_broken_image)
        )
        .into(imageView)
}

@BindingAdapter("startValue")
fun setStartValue(ratingBar: RatingBar, start: Double) {
    ratingBar.rating = start.toFloat()
}

@BindingAdapter("setRating")
fun setRating(textView: TextView, ratting: Double?) {
    textView.text = String.format("${ratting}/5")
}

@BindingAdapter("setText")
fun setText(textView: TextView, data: String?) {
    if (data?.isNotEmpty() == true) {
        textView.text = data
    } else {
        textView.text = textView.context.getString(R.string.no_data)
    }
}

@Suppress("DEPRECATION")
@BindingAdapter("setDesc")
fun setDesc(textView: TextView, data: String?) {
    if (data?.isNotEmpty() == true) {
        val removeHtmlTagsResult = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(data, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(data).toString()
        }

        textView.text = removeHtmlTagsResult.trim()
    } else {
        textView.text = textView.context.getString(R.string.no_data)
    }
}

@BindingAdapter("setPlayTime")
fun setPlayTime(textView: TextView, data: String?) {
    if (data?.isNotEmpty() == true) {
        textView.text = String.format("$data ${textView.context.getString(R.string.hours)}")
    } else {
        textView.text = textView.context.getString(R.string.no_data)
    }
}

@BindingAdapter("setDate")
fun setDate(textView: TextView, date: String?) {
    try {
        if (date?.isBlank() != false) {
            textView.text = textView.context.getString(R.string.no_data)
        } else {
            val oldFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val newDate = oldFormat.parse(date)
            val newFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())

            textView.text = newDate?.let { newFormat.format(it) }.toString()
        }

    } catch (e: ParseException) {
        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        val localDate = sdf.parse(date.toString())
        val formattedSdf = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        textView.text =  localDate?.let { nonNullDate -> formattedSdf.format(nonNullDate) }.toString()
    }
}