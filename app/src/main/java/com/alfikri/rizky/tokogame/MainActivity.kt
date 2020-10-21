package com.alfikri.rizky.tokogame

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.alfikri.rizky.tokogame.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private var tvBadge: TextView? = null
    private var flBadge: FrameLayout? = null
    private var drawerSelectedItem = 0

    val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
        updateDrawerMenu()
    }

    private fun initComponent() {
        val host =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
                ?: return

        navController = host.navController
        setupActionBar()

        nav_view.setNavigationItemSelectedListener { item -> navigate(item) }
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

        navController.addOnDestinationChangedListener { _, destination, _ -> onNavigate(destination) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val shouldHandle = NavigationUI.onNavDestinationSelected(item, navController)
        return shouldHandle || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val menuItem = menu.findItem(R.id.action_cart)
        tvBadge = menuItem.actionView.findViewById(R.id.tv_cart_badge)
        flBadge = menuItem.actionView.findViewById(R.id.fl_badge)
        loadGameInCart()
        return true
    }

    override fun onSupportNavigateUp(): Boolean =
        NavigationUI.navigateUp(navController, drawer_layout)

    override fun onBackPressed() {
        if (drawer_layout.isOpen) {
            drawer_layout.close()
        } else {
            super.onBackPressed()
        }
    }

    private fun onNavigate(dest: NavDestination) {
        val title = dest.label.toString()
        updateToolbarTitle(title)
        hideKeyboard()
    }

    private fun updateToolbarTitle(title: String) {
        toolbar.title = title
    }

    private fun updateDrawerMenu() {
        val menu = nav_view.menu
        menu.clear()

        menu.add(1, GAME_STORE_MENU, Menu.NONE, R.string.main_store)
        menu.add(1, GAME_WISHLIST_MENU, Menu.NONE, R.string.wishlist)
        menu.add(1, GAME_SETTING_MENU, Menu.NONE, R.string.setting)
        nav_view.setCheckedItem(drawerSelectedItem)
    }

    private fun navigate(item: MenuItem): Boolean {
        drawerSelectedItem = item.itemId
        when (drawerSelectedItem) {
            GAME_STORE_MENU -> navController.navigateSingleTop(R.id.mobile_navigation)
            GAME_WISHLIST_MENU -> installOnDemandGameFavoriteFeature()
            GAME_SETTING_MENU -> navController.navigateSingleTop(R.id.nav_setting)
            else -> navController.navigateSingleTop(R.id.mobile_navigation)
        }

        drawer_layout.close()
        return true
    }

    private fun loadGameInCart() {
        mainViewModel.gameInCart.observe(this@MainActivity, Observer { games ->
            tvBadge?.text = games.size.toString()
            onClickCartGame()
        })
    }

    private fun onClickCartGame() {
        flBadge?.setOnClickListener {
            installOnDemandGameCartFeature()
        }
    }

    private fun installOnDemandGameFavoriteFeature() {
        val confirmationDialog = ConfirmationDialog(
            getString(R.string.split_confirmation_install_title),
            getString(R.string.split_confirmation_install_description)

        )
        val loadingDialog = LoadingDialog(
            getString(R.string.split_downloading_title),
            getString(R.string.split_downloading_description)

        )

        SplitInstall(this@MainActivity, confirmationDialog, loadingDialog)
            .loadModule(GAME_FAVORITE_FEATURE) {
                onActionFinished { findNavController(R.id.nav_host_fragment).navigate(R.id.nav_wishlist) }
            }
    }

    private fun installOnDemandGameCartFeature() {
        val confirmationDialog = ConfirmationDialog(
            getString(R.string.split_confirmation_install_title),
            getString(R.string.split_confirmation_install_description)

        )
        val loadingDialog = LoadingDialog(
            getString(R.string.split_downloading_title),
            getString(R.string.split_downloading_description)

        )

        SplitInstall(this@MainActivity, confirmationDialog, loadingDialog)
            .loadModule(GAME_CART_FEATURE) {
                onActionFinished { findNavController(R.id.nav_host_fragment).navigate(R.id.cartGameActivity) }
            }
    }

    companion object {
        private const val GAME_CART_FEATURE = "dynamic_game_cart"
        private const val GAME_FAVORITE_FEATURE = "dynamic_game_favorite"

        private const val GAME_STORE_MENU = 0
        private const val GAME_WISHLIST_MENU = -1
        private const val GAME_SETTING_MENU = -2
    }
}