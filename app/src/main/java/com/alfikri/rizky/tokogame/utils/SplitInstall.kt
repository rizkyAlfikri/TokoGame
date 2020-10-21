package com.alfikri.rizky.tokogame.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import timber.log.Timber


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version SplitInstall, v 0.1 10/6/2020 10:43 PM by Rizky Alfikri Rachmat
 */
class SplitInstall(
    private val context: Context,
    private val confirmationDialog: ConfirmationDialog,
    private val loadingDialog: LoadingDialog
) {

    private val tag = SplitInstall::class.java.simpleName

    private val splitManager = SplitInstallManagerFactory.create(context)

    private var moduleName = ""

    private var onActionFinished: () -> Unit = { /* do nothing */ }

    private var downloadSize = ""

    fun loadModule(moduleName: String, splitFunc: SplitInstall.() -> Unit): SplitInstall {
        this.moduleName = moduleName
        this.splitFunc()
        load()
        return this
    }

    fun onActionFinished(onActionFinished: () -> Unit) {
        this.onActionFinished = onActionFinished
    }

    private fun load() {
        if (moduleName.isEmpty()) {
            throw IllegalArgumentException("Feature name not provided")
        }

        val isInstalled = isModuleInstalled(moduleName)
        Timber.i(tag, "load $moduleName - is installed = $isInstalled")

        if (isInstalled) {
            this.onActionFinished()
        } else {
            showInstallConfirmationDialog()
        }
    }

    private fun showInstallConfirmationDialog() {
        with(confirmationDialog) {
            context.dialog(title, message) {
                positiveButton { downloadModule() }
                negativeButton { /* Do nothing */ }
            }.show()
        }
    }

    @SuppressLint("SwitchIntDef")
    private fun downloadModule() {
        Timber.d(tag, "Download module = [$moduleName]")

        val request = SplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()

        val dialog = getDownloadingDialog(context)

        splitManager.registerListener { sessionState ->
            Timber.d(tag, "${sessionState.status()}")

            when (sessionState.status()) {
                SplitInstallSessionStatus.PENDING -> {
                    downloadSize = "${sessionState.totalBytesToDownload()} bytes"
                    Timber.d(tag, "Download Size = $downloadSize")
                    dialog.setMessage("${loadingDialog.message} \n Download Size = $downloadSize")
                    dialog.show()
                }
                SplitInstallSessionStatus.INSTALLED -> onModuleInstalled(dialog, this.onActionFinished)
            }
        }

        splitManager.startInstall(request)
    }

    private fun getDownloadingDialog(context: Context) = with(loadingDialog) {
        context.dialog(title) {
            view(layout)
        }.setCancelable(false).create()
    }

    private fun onModuleInstalled(dialog: AlertDialog, onModuleReady: () -> Unit) {
        Timber.d(tag, "onModuleInstalled")
        dialog.dismiss()
        onModuleReady()
    }

    private fun isModuleInstalled(moduleName: String): Boolean {
        return splitManager.installedModules.contains(moduleName)
    }

    private fun Context.dialog(
        title: String = "",
        message: String = "",
        builder: AlertDialog.Builder.() -> Unit
    ): AlertDialog.Builder {
        return AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            builder()
        }
    }

    private fun AlertDialog.Builder.positiveButton(
        title: String = "Download",
        function: AlertDialog.Builder.() -> Unit
    ) {
        setPositiveButton(title) { _: DialogInterface?, _: Int -> function() }
    }

    private fun AlertDialog.Builder.negativeButton(
        title: String = "No Thanks",
        function: AlertDialog.Builder.() -> Unit
    ) {
        setNegativeButton(title) { _: DialogInterface?, _: Int -> function() }
    }

    private fun AlertDialog.Builder.view(@LayoutRes layout: Int) {
        setView(layout)
    }
}