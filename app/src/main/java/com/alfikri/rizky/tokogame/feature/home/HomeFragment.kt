package com.alfikri.rizky.tokogame.feature.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.alfikri.rizky.domain.Resource
import com.alfikri.rizky.tokogame.R
import com.alfikri.rizky.tokogame.adapter.GameListAdapter
import com.alfikri.rizky.tokogame.adapter.GamePromoAdapter
import com.alfikri.rizky.tokogame.databinding.FragmentHomeBinding
import com.alfikri.rizky.tokogame.feature.detail.DetailGameActivity
import com.alfikri.rizky.tokogame.mapper.GameModelMapper
import com.alfikri.rizky.tokogame.utils.*
import com.google.android.material.transition.MaterialContainerTransform
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val compositeDisposable = CompositeDisposable()

    private var isProgressBarNeedToShow = false

    private var page = 1

    val homeViewModel: HomeViewModel by viewModel()
    val gameListAdapter: GameListAdapter by inject()
    val gamePromoAdapter: GamePromoAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this@HomeFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadGamePromo()
        loadGameList()
        sharedElementEnterTransition = MaterialContainerTransform()
        onClickButtonState()
        setListenerAdapter()
    }

    private fun initRecyclerView() {
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvGamePromo)

        binding.rvGamePromo.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = gamePromoAdapter
        }

        binding.rvGameList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = gameListAdapter
            gameListAdapter.clearData()
        }

        binding.rvGameList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.rvGameList.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    updateGameStoreFromPage()
                }
            }
        })
    }

    private fun setListenerAdapter() {
        gameListAdapter.setListener { idGame ->
            toDetailActivity(idGame)
        }

        gamePromoAdapter.setListener { idGame ->
            toDetailActivity(idGame)
        }
    }

    private fun loadGamePromo() {
        homeViewModel.gamePromo.observe(viewLifecycleOwner, Observer { source ->
            when (source) {
                is Resource.Loading -> {
                    binding.shimmerGamePromoContainer.showShimmer()
                }

                is Resource.Success -> {
                    val gameModel = GameModelMapper.transfromFromGame(source.data)
                    binding.shimmerGamePromoContainer.hideShimmer()
                    gamePromoAdapter.submitList(gameModel)
                }

                is Resource.Failed -> {
                    binding.shimmerGamePromoContainer.hideShimmer()
                    showToastMessage(context, source.message)
                }
            }
        })
    }

    private fun loadGameList() {
        homeViewModel.gameStores.observe(viewLifecycleOwner, Observer { source ->
            when (source) {
                is Resource.Loading -> {
                    if (isProgressBarNeedToShow) {
                        binding.progressBar.visible()
                    } else {
                        binding.shimmerGameListContainer.showShimmer()
                    }
                }

                is Resource.Success -> {
                    val gameModel = GameModelMapper.transfromFromGame(source.data)
                    binding.progressBar.gone()
                    binding.shimmerGameListContainer.hideShimmer()
                    gameModel?.let { gameListAdapter.setGameData(gameModel) }
                }

                is Resource.Failed -> {
                    binding.progressBar.gone()
                    binding.shimmerGameListContainer.hideShimmer()
                    showToastMessage(context, source.message)
                }
            }
        })
    }

    private fun updateGameStoreFromPage() {
        homeViewModel.updateGameStoreFromPage(++page)
        isProgressBarNeedToShow = true
    }

    private fun updateGameStore(sortBy: String) {
        page = 1
        isProgressBarNeedToShow = false
        gameListAdapter.clearData()
        homeViewModel.updateGameStore(sortBy, page)
    }


    private fun onClickButtonState() {
        val buttonPopular = RxView.clicks(binding.btnPopular).map { GameSortingTarget.ADDED }
        val buttonRate = RxView.clicks(binding.btnRate).map { GameSortingTarget.RATING }
        val buttonReleased = RxView.clicks(binding.btnRelease).map { GameSortingTarget.RELEASED }

        val observableButtonState =
            Observable.merge(buttonPopular, buttonRate, buttonReleased).subscribe { sortBy ->
                when (sortBy) {
                    GameSortingTarget.ADDED -> {
                        updateGameStore(GameSortingTarget.ADDED)
                        setButtonState(binding.btnPopular, binding.btnRate, binding.btnRelease)
                    }
                    GameSortingTarget.RATING -> {
                        updateGameStore(GameSortingTarget.RATING)
                        setButtonState(binding.btnRate, binding.btnPopular, binding.btnRelease)
                    }
                    GameSortingTarget.RELEASED -> {
                        updateGameStore(GameSortingTarget.RELEASED)
                        setButtonState(binding.btnRelease, binding.btnRate, binding.btnPopular)
                    }
                }
            }
        compositeDisposable.add(observableButtonState)
    }

    private fun setButtonState(
        activeButton: Button,
        deavtiveButton1: Button,
        deavtiveButton2: Button
    ) {
        activeButton.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded_active_button)
        deavtiveButton1.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded_button)
        deavtiveButton2.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_rounded_button)
    }

    private fun toDetailActivity(idGame: Int) {
        val toDetailActivityIntent = Intent(context, DetailGameActivity::class.java)
        toDetailActivityIntent.putExtra(DetailGameActivity.EXTRA_GAME, idGame)
        startActivity(toDetailActivityIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}