package com.alfikri.rizky.tokogame.utils

import androidx.annotation.LayoutRes
import com.alfikri.rizky.tokogame.R


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version ConfirmationDialog, v 0.1 10/6/2020 10:44 PM by Rizky Alfikri Rachmat
 */
data class ConfirmationDialog(
    var title: String = "",
    var message: String  = ""
)

data class LoadingDialog(
    var title: String = "",
    var message: String = "",
    @LayoutRes var layout: Int = R.layout.dialog_install_layout
)