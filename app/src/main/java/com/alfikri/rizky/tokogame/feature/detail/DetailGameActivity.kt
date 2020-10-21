package com.alfikri.rizky.tokogame.feature.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfikri.rizky.domain.Resource
import com.alfikri.rizky.domain.model.Game
import com.alfikri.rizky.tokogame.R
import com.alfikri.rizky.tokogame.databinding.ActivityGameDetailBinding
import com.alfikri.rizky.tokogame.mapper.GameModelMapper
import com.alfikri.rizky.tokogame.model.GameDetailModel
import com.alfikri.rizky.tokogame.utils.*
import com.alfikri.rizky.tokogame.viewholder.GameAchievmentViewHolder
import com.alfikri.rizky.tokogame.viewholder.GameScreenshotViewHolder
import com.alfikri.rizky.tokogame.viewholder.GameSimilarVisualyViewHolder
import com.alfikri.rizky.tokogame.viewholder.GameVideoViewHolder
import com.google.android.flexbox.FlexboxLayout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.*

class DetailGameActivity : AppCompatActivity() {

    val detailGameViewModel: DetailGameViewModel by inject()

    private val gameAchievmentAdapter: GroupAdapter<ViewHolder> by lazy { GroupAdapter<ViewHolder>() }

    private val gameScreenshotAdapter: GroupAdapter<ViewHolder> by lazy { GroupAdapter<ViewHolder>() }

    private val gameVideoAdapter: GroupAdapter<ViewHolder> by lazy { GroupAdapter<ViewHolder>() }

    private val gameGameSimilarAdapter: GroupAdapter<ViewHolder> by lazy { GroupAdapter<ViewHolder>() }

    private lateinit var binding: ActivityGameDetailBinding

    private var game: Game? = null

    private var menuItem: Menu? = null

    private var isFavorite: Boolean = false

    companion object {
        private val TAG = DetailGameActivity::class.java.simpleName
        private const val MAX_DISPLAYED_LIST_DATA = 4
        private const val VND_YOUTUBE_URL = "vnd.youtube:"
        private const val YOUTUBE_URL = "http://www.youtube.com/watch?v="
        const val EXTRA_GAME = "extra_game"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this@DetailGameActivity, R.layout.activity_game_detail)
        this@DetailGameActivity.makeStatusBarTransparent()
        initView()

        val gameId = intent.getIntExtra(EXTRA_GAME, 0)
        initViewModel(gameId)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_detail_menu, menu)
        menuItem = menu
        setFavoriteState()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite -> {
                if (isFavorite) removeGameFromFavorite() else addGameToFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        setDynamicallyTitleColor()
        initAchivementRecyclerView()
        initScreenshotRecyclerView()
        initVideosRecyclerView()
        initGameSimilarRecyclerView()
        onClickBackArrow()
        onClickAddGameToCart()
    }

    private fun initViewModel(idGame: Int) {
        setGameId(idGame)
        loadDetailGameData()
        loadGameAchivementData()
        loadGameScreenshotData()
        loadGameVideoData()
        loadGameSimilarVisualy()
        loadFavoriteGame()
    }

    private fun initAchivementRecyclerView() {
        binding.layoutDetailMultiView.rvAchievment.apply {
            layoutManager = LinearLayoutManager(this@DetailGameActivity)
            adapter = gameAchievmentAdapter
        }
    }

    private fun initScreenshotRecyclerView() {
        binding.layoutDetailMultiView.rvScreenshot.apply {
            layoutManager =
                LinearLayoutManager(this@DetailGameActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = gameScreenshotAdapter
        }
    }

    private fun initVideosRecyclerView() {
        binding.layoutDetailMultiView.rvVideo.apply {
            layoutManager =
                LinearLayoutManager(this@DetailGameActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = gameVideoAdapter
        }
    }

    private fun initGameSimilarRecyclerView() {
        binding.layoutDetailMultiView.rvGameSimilar.apply {
            layoutManager =
                LinearLayoutManager(this@DetailGameActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = gameGameSimilarAdapter
        }
    }

    private fun setDynamicallyTitleColor() {
        binding.collapeLayout.apply {
            setExpandedTitleColor(
                ContextCompat.getColor(
                    this@DetailGameActivity,
                    android.R.color.transparent
                )
            )

            setCollapsedTitleTextColor(
                ContextCompat.getColor(
                    this@DetailGameActivity,
                    R.color.colorMainWhite
                )
            )
        }
    }

    private fun onClickBackArrow() {
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun onClickAddGameToCart() {
        binding.layoutDetailBody.btnAddToChart.setOnClickListener {
            game?.let { game ->
                val gameCart = GameModelMapper.transformCartFromGame(game)
                val time = Calendar.getInstance().time.time
                gameCart.time = time
                detailGameViewModel.insertCartGame(gameCart)
                showToastMessage(this@DetailGameActivity, getString(R.string.success_add_cart))
            } ?: showToastMessage(this@DetailGameActivity, getString(R.string.failed_add_cart))
        }
    }

    private fun setGameId(idGame: Int) {
        detailGameViewModel.checkFavoriteGameFromDb(idGame)
        detailGameViewModel.setGameId(idGame)
    }

    private fun loadDetailGameData() {
        detailGameViewModel.gameDetail.observe(this@DetailGameActivity, Observer { source ->
            when (source) {
                is Resource.Loading -> {
                    showDetailView(false)
                    enableScrollable(false)
                }

                is Resource.Success -> {
                    source.data?.let { gameData ->
                        val gameDetail = GameModelMapper.transformFromDetail(gameData)
                        binding.gameDetail = gameDetail
                        binding.layoutDetailBody.gameDetail = gameDetail
                        binding.layoutDetailInfo.gameDetail = gameDetail
                        loadGameData(gameDetail)
                        loadRatingChartData(gameDetail)
                        loadGenreWithLogo(gameDetail.platform)
                        enableScrollable(true)
                    } ?: showToastMessage(this@DetailGameActivity, source.message)

                    showDetailView(true)
                }

                is Resource.Failed -> {
                    showDetailView(true)
                    showToastMessage(this@DetailGameActivity, source.message)
                }
            }
        })
    }

    private fun loadGameAchivementData() {
        detailGameViewModel.gameAchievment.observe(this@DetailGameActivity, Observer { source ->
            when (source) {
                is Resource.Loading -> {
                    // no operation
                }

                is Resource.Success -> {
                    val gameAchievments = GameModelMapper.transformFromAchievments(source.data)
                    gameAchievments?.forEachIndexed { index, data ->
                        if (index >= MAX_DISPLAYED_LIST_DATA) {
                            return@forEachIndexed
                        } else {
                            gameAchievmentAdapter.add(
                                GameAchievmentViewHolder(
                                    data,
                                    null,
                                    GameTypeViewTarget.DATA_VIEW
                                )
                            )
                        }
                    }

                    gameAchievmentAdapter.add(
                        GameAchievmentViewHolder(
                            null,
                            source.data?.size,
                            GameTypeViewTarget.MORE_VIEW
                        )
                    )
                }

                is Resource.Failed -> {
                    showToastMessage(this@DetailGameActivity, source.message)
                }
            }
        })
    }

    private fun loadGameScreenshotData() {
        detailGameViewModel.gameScreenshot.observe(this@DetailGameActivity, Observer { source ->
            when (source) {
                is Resource.Loading -> {
                    // no operation
                }

                is Resource.Success -> {
                    val gameScreenshot = GameModelMapper.transformFromScreenshot(source.data)
                    gameScreenshot?.forEachIndexed { index, data ->
                        if (index >= MAX_DISPLAYED_LIST_DATA) {
                            return@forEachIndexed
                        } else {
                            gameScreenshotAdapter.add(
                                GameScreenshotViewHolder(
                                    data,
                                    GameTypeViewTarget.DATA_VIEW
                                )
                            )
                        }
                    }

                    gameScreenshotAdapter.add(
                        GameScreenshotViewHolder(
                            null,
                            GameTypeViewTarget.MORE_VIEW
                        )
                    )
                }

                is Resource.Failed -> {
                    showToastMessage(this@DetailGameActivity, source.message)
                }
            }
        })
    }

    private fun loadGameVideoData() {
        detailGameViewModel.gameVideos.observe(this@DetailGameActivity, Observer { source ->
            when (source) {
                is Resource.Loading -> {
                    // no operation
                }

                is Resource.Success -> {
                    val gameVideo = GameModelMapper.transformFromVideo(source.data)
                    gameVideo?.forEachIndexed { index, data ->
                        if (index >= MAX_DISPLAYED_LIST_DATA) {
                            return@forEachIndexed
                        } else {
                            gameVideoAdapter.add(
                                GameVideoViewHolder(
                                    data,
                                    GameTypeViewTarget.DATA_VIEW
                                ) { key ->
                                    showGameVideo(key)
                                }
                            )
                        }
                    }

                    gameVideoAdapter.add(
                        GameVideoViewHolder(
                            null,
                            GameTypeViewTarget.MORE_VIEW
                        ) {
                            // no operation
                        })
                }

                is Resource.Failed -> {
                    showToastMessage(this@DetailGameActivity, source.message)
                }
            }
        })
    }

    private fun loadGameSimilarVisualy() {
        detailGameViewModel.gameSimilarVisualy.observe(this@DetailGameActivity, Observer { source ->
            when (source) {
                is Resource.Loading -> {
                    // no operation
                }

                is Resource.Success -> {
                    val gameModel = GameModelMapper.transfromFromGame(source.data)
                    gameModel?.forEachIndexed { index, data ->
                        if (index >= MAX_DISPLAYED_LIST_DATA) {
                            return@forEachIndexed
                        } else {
                            gameGameSimilarAdapter.add(
                                GameSimilarVisualyViewHolder(
                                    data,
                                    GameTypeViewTarget.DATA_VIEW
                                ) { game ->
                                    toDetailActivity(game)
                                }
                            )
                        }
                    }

                    gameGameSimilarAdapter.add(
                        GameSimilarVisualyViewHolder(
                            null,
                            GameTypeViewTarget.MORE_VIEW
                        ) {
                            // no operation
                        }
                    )
                }

                is Resource.Failed -> {
                    showToastMessage(this@DetailGameActivity, source.message)
                }
            }
        })
    }

    private fun loadFavoriteGame() {
        detailGameViewModel.gameFavorite.observe(this@DetailGameActivity, Observer { game ->
            isFavorite = game != null
            setFavoriteState()
        })
    }

    private fun loadGenreWithLogo(platformData: String) {
        val platforms = platformData.toList()
        binding.layoutDetailBody.flPlatform.removeAllViews()
        platforms.forEach { platform ->
            val tvPlatform = TextView(this@DetailGameActivity)
            tvPlatform.text = platform
            tvPlatform.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.toFloat())
            tvPlatform.gravity = Gravity.CENTER
            tvPlatform.setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.colorMainWhite,
                    theme
                )
            )

            tvPlatform.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_rounded_button, theme)

            val lpRight = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
            )

            val platformLogo = platform.generatePlatformIcon(this@DetailGameActivity)
            tvPlatform.layoutParams = lpRight
            val layoutParam = tvPlatform.layoutParams as FlexboxLayout.LayoutParams
            layoutParam.setMargins(16)
            tvPlatform.setPadding(36, 16, 36, 16)
            tvPlatform.setCompoundDrawablesWithIntrinsicBounds(platformLogo, null, null, null)
            tvPlatform.compoundDrawablePadding = 24
            tvPlatform.layoutParams = layoutParam
            binding.layoutDetailBody.flPlatform.addView(tvPlatform)
        }
    }

    private fun loadRatingChartData(gameDetailModel: GameDetailModel) {
        val rates = mutableListOf<SliceValue>()
        rates.clear()
        val ratingChartData = PieChartData(rates)

        gameDetailModel.rattingScore.forEach { rateMap ->
            when (rateMap.key) {
                GameRatingTarget.RECOMENDED -> {
                    binding.layoutDetailChart.tvRateRecomended.text = rateMap.value.toString()
                    rates.add(
                        SliceValue(
                            rateMap.value.toFloat(),
                            ContextCompat.getColor(this@DetailGameActivity, R.color.colorBlueChart)
                        )
                    )
                }
                GameRatingTarget.EXCEPTIONAL -> {
                    binding.layoutDetailChart.tvRateExceptional.text = rateMap.value.toString()
                    rates.add(
                        SliceValue(
                            rateMap.value.toFloat(),
                            ContextCompat.getColor(this@DetailGameActivity, R.color.colorGreenChart)
                        )
                    )
                }
                GameRatingTarget.MEH -> {
                    binding.layoutDetailChart.tvRateMeh.text = rateMap.value.toString()
                    rates.add(
                        SliceValue(
                            rateMap.value.toFloat(),
                            ContextCompat.getColor(this@DetailGameActivity, R.color.colorAccent)
                        )
                    )
                }
                GameRatingTarget.SKIP -> {
                    binding.layoutDetailChart.tvRateSkip.text = rateMap.value.toString()
                    rates.add(
                        SliceValue(
                            rateMap.value.toFloat(),
                            ContextCompat.getColor(this@DetailGameActivity, R.color.coloRedChart)
                        )
                    )
                }
            }
        }

        ratingChartData.apply {
            setHasCenterCircle(true).centerText1 =
                "${gameDetailModel.reviewCount} ${getString(R.string.vote)}"
            centerText1FontSize = 16
            centerText1Typeface = Typeface.DEFAULT_BOLD
            centerText1Color =
                ContextCompat.getColor(this@DetailGameActivity, R.color.colorMainWhite)
            binding.layoutDetailChart.chartRatting.pieChartData = this
        }
    }

    private fun toDetailActivity(idGame: Int) {
        val toDetailActivityIntent = Intent(this@DetailGameActivity, DetailGameActivity::class.java)
        toDetailActivityIntent.putExtra(EXTRA_GAME, idGame)
        startActivity(toDetailActivityIntent)
    }


    private fun showGameVideo(key: String) {
        val appVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse("$VND_YOUTUBE_URL$key"))
        val webVideoIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("$YOUTUBE_URL$key"))
        try {
            startActivity(appVideoIntent)
        } catch (exception: ActivityNotFoundException) {
            Timber.e(TAG, exception.message.toString())
            startActivity(webVideoIntent)
        }
    }

    private fun loadGameData(gameDetail: GameDetailModel) {
        game = GameModelMapper.transformFromDetail(gameDetail)
    }

    private fun setFavoriteState() {
        if (isFavorite) {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this@DetailGameActivity, R.drawable.ic_favorite)
        } else {
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this@DetailGameActivity, R.drawable.ic_favorite_border)
        }
    }

    private fun addGameToFavorite() {
        game?.let { gameFavorite ->
            detailGameViewModel.insertFavoriteGame(gameFavorite)
            showToastMessage(this@DetailGameActivity, getString(R.string.success_add_favorite))
        } ?: showToastMessage(this@DetailGameActivity, getString(R.string.failed_add_favorite))
    }

    private fun removeGameFromFavorite() {
        game?.let { gameFavorite ->
            detailGameViewModel.deleteFavoriteGame(gameFavorite.gameId)
            showToastMessage(this@DetailGameActivity, getString(R.string.success_remove_favorite))
        } ?: showToastMessage(this@DetailGameActivity, getString(R.string.failed_remove_favorite))
    }

    private fun enableScrollable(state: Boolean) {
        binding.nestedScroll.setScrollable(state)
    }

    private fun showDetailView(state: Boolean) {
        if (state) {
            binding.ivGame.visible()
            binding.layoutDetailBody.parentLayout.visible()
            binding.shimmerDetailImage.hideShimmer()
            binding.shimmerDetailBody.hideShimmer()
        } else {
            binding.shimmerDetailImage.showShimmer()
            binding.shimmerDetailBody.showShimmer()
            binding.ivGame.invisible()
            binding.layoutDetailBody.parentLayout.invisible()
        }
    }
}