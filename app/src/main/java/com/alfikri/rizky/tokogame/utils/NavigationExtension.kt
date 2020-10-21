package com.alfikri.rizky.tokogame.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version NavigationExtension, v 0.1 10/16/2020 10:35 PM by Rizky Alfikri Rachmat
 */

fun NavController.navigateSingleTop(@IdRes resId: Int, args: Bundle? = null) {
    val hostDestId = graph.startDestination
    val navOptions = NavOptions.Builder()
        .setPopUpTo(hostDestId, false)
        .setLaunchSingleTop(true)
        .build()

    navigate(resId, args, navOptions)
}