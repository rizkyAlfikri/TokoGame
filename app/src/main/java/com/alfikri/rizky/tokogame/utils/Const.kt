package com.alfikri.rizky.tokogame.utils


/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version Const, v 0.1 9/23/2020 6:14 PM by Rizky Alfikri Rachmat
 */

class PlatformTarget {
    companion object {
        const val PC = "PC"
        const val APPLE = "Apple"
        const val LINUX = "Linux"
        const val NINTENDO = "Nintendo"
        const val PLAYSTATION = "Playstation"
        const val XBOX = "Xbox"
        const val ANDROID = "Android"
    }
}

class GameSortingTarget {
    companion object {
        const val ADDED = "-added"
        const val RATING = "-rating"
        const val RELEASED = "-released"
    }
}

class GameQueryTarget {
    companion object {
        const val PAGE = "page"
        const val DATES = "dates"
        const val GENRES = "genres"
        const val GENERAL_PLATFORM = "parent_platforms"
        const val ORDERING = "ordering"
        const val SPECIFIC_PLATFORM = "platforms"
    }
}

class GameRatingTarget {
    companion object{
        const val RECOMENDED = "recommended"
        const val EXCEPTIONAL = "exceptional"
        const val MEH = "meh"
        const val SKIP = "skip"
    }
}

class GameTypeViewTarget{
    companion object{
        const val MORE_VIEW = "more_view"
        const val DATA_VIEW = "data_view"
    }
}