package com.arflix.tv.ui.screens.player;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.media3.ui.PlayerView;
import androidx.media3.ui.R;
import androidx.media3.ui.SubtitleView;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000f\u001a\u00020\tH\u0000¢\u0006\u0004\b\r\u0010\u000eR\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u00020\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/ui/screens/player/FullViewportSubtitlePlayerView;", "Landroidx/media3/ui/PlayerView;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lx6/t0;", "applySubtitleViewport", "()V", "", "enabled", "setUseVideoFrameForSubtitles", "(Z)V", "isSubtitleLayerAttachedToFullViewport$app_playRelease", "()Z", "isSubtitleLayerAttachedToFullViewport", "Landroid/view/View;", "contentFrame", "Landroid/view/View;", "Landroidx/media3/ui/SubtitleView;", "subtitleLayer", "Landroidx/media3/ui/SubtitleView;", "useVideoFrame", "Z", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FullViewportSubtitlePlayerView extends PlayerView {
    public static final int $stable = 8;
    private final View contentFrame;
    private final SubtitleView subtitleLayer;
    private boolean useVideoFrame;

    public FullViewportSubtitlePlayerView(Context context) {
        super(context);
        View viewFindViewById = findViewById(R.id.exo_content_frame);
        this.contentFrame = viewFindViewById;
        SubtitleView subtitleView = getSubtitleView();
        this.subtitleLayer = subtitleView;
        if (subtitleView != null) {
            ViewParent parent = subtitleView.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(subtitleView);
            }
            addView(subtitleView, new FrameLayout.LayoutParams(-1, -1, 17));
        }
        if (viewFindViewById != null) {
            viewFindViewById.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.arflix.tv.ui.screens.player.a
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17) {
                    FullViewportSubtitlePlayerView._init_$lambda$1(this.f9376a, view, i10, i11, i12, i13, i14, i15, i16, i17);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$1(FullViewportSubtitlePlayerView fullViewportSubtitlePlayerView, View view, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17) {
        if (fullViewportSubtitlePlayerView.useVideoFrame) {
            fullViewportSubtitlePlayerView.applySubtitleViewport();
        }
    }

    private final void applySubtitleViewport() {
        int width;
        int height;
        SubtitleView subtitleView = this.subtitleLayer;
        if (subtitleView == null) {
            return;
        }
        View view = this.contentFrame;
        if (this.useVideoFrame) {
            width = view != null ? view.getWidth() : 0;
            height = view != null ? view.getHeight() : 0;
            if (width <= 0 || height <= 0) {
                return;
            }
        } else {
            width = -1;
            height = -1;
        }
        ViewGroup.LayoutParams layoutParams = subtitleView.getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 != null && layoutParams2.width == width && layoutParams2.height == height && layoutParams2.gravity == 17 && layoutParams2.leftMargin == 0 && layoutParams2.topMargin == 0 && layoutParams2.rightMargin == 0 && layoutParams2.bottomMargin == 0) {
            return;
        }
        subtitleView.setLayoutParams(new FrameLayout.LayoutParams(width, height, 17));
    }

    public final boolean isSubtitleLayerAttachedToFullViewport$app_playRelease() {
        SubtitleView subtitleView = this.subtitleLayer;
        return (subtitleView != null ? subtitleView.getParent() : null) == this;
    }

    public final void setUseVideoFrameForSubtitles(boolean enabled) {
        if (this.useVideoFrame == enabled) {
            return;
        }
        this.useVideoFrame = enabled;
        applySubtitleViewport();
    }
}
