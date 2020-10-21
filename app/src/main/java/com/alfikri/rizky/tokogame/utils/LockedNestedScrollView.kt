package com.alfikri.rizky.tokogame.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version LockedNestedScrollView, v 0.1 10/5/2020 9:19 PM by Rizky Alfikri Rachmat
 */
internal class LockedNestedScrollView : NestedScrollView {
    private var isScrollable = true

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isScrollable && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return isScrollable && super.onInterceptTouchEvent(ev)
    }

    fun setScrollable(state: Boolean) {
        isScrollable = state
    }
}