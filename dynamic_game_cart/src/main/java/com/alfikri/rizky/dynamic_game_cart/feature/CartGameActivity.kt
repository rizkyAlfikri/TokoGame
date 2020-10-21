package com.alfikri.rizky.dynamic_game_cart.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfikri.rizky.dynamic_game_cart.R
import com.alfikri.rizky.dynamic_game_cart.adapter.GameCartAdapter
import com.alfikri.rizky.dynamic_game_cart.adapter.GameCartListener
import com.alfikri.rizky.dynamic_game_cart.databinding.ActivityCartGameBinding
import com.alfikri.rizky.dynamic_game_cart.di.adapterCartModule
import com.alfikri.rizky.dynamic_game_cart.di.usecaseCartModule
import com.alfikri.rizky.dynamic_game_cart.di.viewModelCartModule
import com.alfikri.rizky.tokogame.R.drawable
import com.alfikri.rizky.tokogame.feature.detail.DetailGameActivity
import com.alfikri.rizky.tokogame.model.GameCartModel
import com.alfikri.rizky.tokogame.utils.showToastMessage
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_cart_game.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class CartGameActivity : AppCompatActivity(), GameCartListener {

    private lateinit var binding: ActivityCartGameBinding

    val cartViewModel: CartGameViewModel by viewModel()

    val cartAdapter: GameCartAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@CartGameActivity, R.layout.activity_cart_game)
        init()
    }

    private fun init() {
        injectModule()
        initRecycerView()
        setAdapterListener()
        onClickBackArrow()
        loadGameCartData()
        onClickPurchase()
    }

    private fun initRecycerView() {
        binding.rvGameCart.apply {
            layoutManager = LinearLayoutManager(this@CartGameActivity)
            adapter = cartAdapter
        }
    }

    private fun injectModule() {
        loadKoinModules(
            listOf(
                usecaseCartModule,
                adapterCartModule,
                viewModelCartModule
            )
        )
    }

    private fun setAdapterListener() {
        cartAdapter.setListener(this@CartGameActivity)
    }

    private fun onClickBackArrow() {
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun loadGameCartData() {
        cartViewModel.gameCarts.observe(this@CartGameActivity, Observer { gameCartModels ->
            cartAdapter.submitList(gameCartModels)
            loadDetailPurchaseData(gameCartModels)
        })
    }

    private fun loadDetailPurchaseData(gameCartModels: List<GameCartModel>) {
        setButtonState(gameCartModels)
        binding.tvTotalItem.text = gameCartModels.filter { it.isChecked }.size.toString()
        binding.tvTotalPrice.text =
            String.format("${getString(R.string.dolar)} ${gameCartModels.calculatePriceCheckedGame()}")
    }

    private fun onClickPurchase() {
        binding.btnPurchase.setOnClickListener {
            showToastMessage(this@CartGameActivity, getString(R.string.coming_soon))
        }
    }

    private fun insertCartGame(gameCartModel: GameCartModel) {
        cartViewModel.insertCartGame(gameCartModel)
    }

    private fun showSnackbar(gameCartModel: GameCartModel) {
        Snackbar.make(
            binding.rvGameCart,
            getString(R.string.success_remove_cart),
            Snackbar.LENGTH_SHORT
        ).setAction(getString(R.string.undo)) {
            insertCartGame(gameCartModel)
        }.show()
    }

    private fun List<GameCartModel>.calculatePriceCheckedGame(): Int {
        return this.filter { it.isChecked }.sumBy { it.price }
    }

    private fun setButtonState(gameCartModel: List<GameCartModel>) {
        val isEnable = gameCartModel.count { it.isChecked } >= 1
        if (isEnable) {
            btn_purchase.background =
                ContextCompat.getDrawable(this@CartGameActivity, drawable.bg_rounded_fill_accent)
            btn_purchase.isEnabled = true
        } else {
            btn_purchase.background =
                ContextCompat.getDrawable(
                    this@CartGameActivity,
                    drawable.bg_rounded_inactive_button
                )
            btn_purchase.isEnabled = false
        }
    }

    override fun updateCheckedGame(gameCartModel: GameCartModel) {
        cartViewModel.updateCheckedGame(gameCartModel)
    }

    override fun deleteCartGame(gameCartModel: GameCartModel) {
        cartViewModel.deleteCartGame(gameCartModel.gameId)
        showSnackbar(gameCartModel)
    }

    override fun toDetailGameActivity(idGame: Int) {
        val toDetailActivityIntent = Intent(this@CartGameActivity, DetailGameActivity::class.java)
        toDetailActivityIntent.putExtra(DetailGameActivity.EXTRA_GAME, idGame)
        startActivity(toDetailActivityIntent)
    }
}