package com.alfikri.rizky.tokogame.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.alfikri.rizky.tokogame.R
import com.facebook.shimmer.ShimmerFrameLayout
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version Utils, v 0.1 9/23/2020 4:33 PM by Rizky Alfikri Rachmat
 */

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun ShimmerFrameLayout.showShimmer() {
    this.visible()
    this.startShimmerAnimation()
}

fun ShimmerFrameLayout.hideShimmer() {
    this.gone()
    this.stopShimmerAnimation()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Activity.makeStatusBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}

fun Date.format(format: String = "yyyy-MM-dd", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun Date.formatToEarlyYear(format: String = "yyyy", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return "${formatter.format(this)}-01-01"
}

fun getCurrentDateTime(): Date {
    return Calendar.getInstance().time
}

fun showToastMessage(context: Context?, message: String?) {
    if (message?.isNotEmpty() == true) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}

fun String.generatePlatformIcon(context: Context): Drawable? {
    return when (this) {
        PlatformTarget.PC ->
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_pc_platform
            )

        PlatformTarget.APPLE ->
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_apple_svg
            )

        PlatformTarget.LINUX ->
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_linux_platform
            )

        PlatformTarget.NINTENDO ->
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_nintendo_platform
            )

        PlatformTarget.PLAYSTATION ->
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_playstation_platform
            )

        PlatformTarget.XBOX ->
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_xbox_platform
            )

        PlatformTarget.ANDROID ->
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_android_platformsvg
            )

        else -> null
    }
}

fun String.toList(): List<String> {
    val platforms = mutableListOf<String>()
    platforms.clear()
    val tempResult = StringBuilder()
    this.trim().forEach {
        if (it.isLetter()) {
            tempResult.append(it)
        } else if (it == ',') {
            platforms.add(tempResult.toString())
            tempResult.clear()
        }
    }
    return platforms.toList()
}