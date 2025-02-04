package com.twopiradrian.auraluna.ui.utils

import androidx.compose.ui.unit.Dp

enum class ScreenType {
    Mobile, Foldable, Tablet
}

class ScreenUtils (
    private val screenType: ScreenType
) {

    fun resolveSize(
        mobile: Dp,
        foldable: Dp,
        tablet: Dp
    ): Dp {
        return when (this.screenType) {
            ScreenType.Mobile -> mobile
            ScreenType.Foldable -> foldable
            ScreenType.Tablet -> tablet
        }
    }

}