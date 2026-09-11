package com.arflix.tv.ui.screens.player

import android.app.Application
import android.view.View
import android.widget.FrameLayout
import com.arflix.tv.ui.screens.player.engine.exoplayer.FullViewportSubtitlePlayerView
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.R as Media3UiR
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], application = Application::class)
class FullViewportSubtitlePlayerViewLayoutTest {

    @Test
    fun subtitleLayerUsesLetterboxSpaceAndCanReturnToVideoFrame() {
        val context = RuntimeEnvironment.getApplication()
        val playerView = FullViewportSubtitlePlayerView(context)
        val contentFrame = playerView.findViewById<AspectRatioFrameLayout>(
            Media3UiR.id.exo_content_frame
        )
        val subtitleView = checkNotNull(playerView.subtitleView)

        contentFrame.setAspectRatio(2.4f)
        measureAndLayout(playerView, width = 1920, height = 1080)

        assertThat(playerView.isSubtitleLayerAttachedToFullViewport()).isTrue()
        assertThat(contentFrame.height).isAtLeast(799)
        assertThat(contentFrame.height).isAtMost(800)
        assertThat(subtitleView.height).isEqualTo(1080)

        playerView.setUseVideoFrameForSubtitles(true)
        measureAndLayout(playerView, width = 1920, height = 1080)

        assertThat(subtitleView.width).isEqualTo(contentFrame.width)
        assertThat(subtitleView.height).isEqualTo(contentFrame.height)

        playerView.setUseVideoFrameForSubtitles(false)
        val params = subtitleView.layoutParams as FrameLayout.LayoutParams
        assertThat(params.width).isEqualTo(FrameLayout.LayoutParams.MATCH_PARENT)
        assertThat(params.height).isEqualTo(FrameLayout.LayoutParams.MATCH_PARENT)
    }

    private fun measureAndLayout(view: View, width: Int, height: Int) {
        view.measure(
            View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
        )
        view.layout(0, 0, width, height)
    }
}
