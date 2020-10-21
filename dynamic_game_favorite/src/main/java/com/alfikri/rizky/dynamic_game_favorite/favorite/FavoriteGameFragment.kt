package com.alfikri.rizky.dynamic_game_favorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfikri.rizky.dynamic_game_favorite.R
import com.alfikri.rizky.dynamic_game_favorite.adapter.GameFavoriteAdapter
import com.alfikri.rizky.dynamic_game_favorite.adapter.GameFavoriteListener
import com.alfikri.rizky.dynamic_game_favorite.databinding.FragmentGameFavoriteBinding
import com.alfikri.rizky.dynamic_game_favorite.di.adapterFavoriteModule
import com.alfikri.rizky.dynamic_game_favorite.di.usecaseFavoriteModule
import com.alfikri.rizky.dynamic_game_favorite.di.viewmodelFavoriteModule
import com.alfikri.rizky.tokogame.feature.detail.DetailGameActivity
import com.alfikri.rizky.tokogame.model.GameFavoriteModel
import com.alfikri.rizky.tokogame.utils.invisible
import com.alfikri.rizky.tokogame.utils.visible
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteGameFragment : Fragment(), GameFavoriteListener {

    private lateinit var binding: FragmentGameFavoriteBinding

    val gameFavoriteAdapter: GameFavoriteAdapter by inject()
    val favoriteGameViewModel: FavoriteGameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_game_favorite, container, false)
        binding.lifecycleOwner = this@FavoriteGameFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setAdapterListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadGameFavorite()
    }

    private fun initKoin() {
        loadKoinModules(
            listOf(
                usecaseFavoriteModule,
                adapterFavoriteModule,
                viewmodelFavoriteModule
            )
        )
    }

    private fun initRecyclerView() {
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = gameFavoriteAdapter
        }
    }

    private fun setAdapterListener() {
        gameFavoriteAdapter.setFavoriteListener(this@FavoriteGameFragment)
    }

    private fun loadGameFavorite() {
        favoriteGameViewModel.gameInCart.observe(viewLifecycleOwner, Observer { source ->
            if (source.isNotEmpty()) {
                gameFavoriteAdapter.submitList(source)
                showEmptyDataToView(false)
            } else {
                showEmptyDataToView(true)
            }
        })
    }

    private fun showEmptyDataToView(state: Boolean) {
        if (state) {
            binding.rvFavorite.invisible()
            binding.tvNoData.visible()
        } else {
            binding.rvFavorite.visible()
            binding.tvNoData.invisible()
        }
    }

    override fun onClickGameFavorite(idGame: Int) {
        val toDetailActivityIntent = Intent(context, DetailGameActivity::class.java)
        toDetailActivityIntent.putExtra(DetailGameActivity.EXTRA_GAME, idGame)
        startActivity(toDetailActivityIntent)
    }

    override fun onClickDeleteFavorite(gameFavoriteModel: GameFavoriteModel) {
        favoriteGameViewModel.deleteFavoriteGame(gameFavoriteModel.gameId)
        showSnackbar(gameFavoriteModel)
    }

    private fun showSnackbar(gameFavoriteModel: GameFavoriteModel) {
        Snackbar.make(
            binding.root,
            getString(R.string.success_remove_favorite),
            Snackbar.LENGTH_SHORT
        ).setAction(getString(R.string.undo)) { addGameToFavorite(gameFavoriteModel) }.show()
    }

    private fun addGameToFavorite(gameFavoriteModel: GameFavoriteModel) {
        favoriteGameViewModel.insertFavoriteGame(gameFavoriteModel)
    }
}