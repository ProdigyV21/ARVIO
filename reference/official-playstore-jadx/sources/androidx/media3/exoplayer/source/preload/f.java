package androidx.media3.exoplayer.source.preload;

import android.content.Context;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelector;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements TrackSelector.InvalidationListener, TrackSelector.Factory {
    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.Factory
    public TrackSelector createTrackSelector(Context context) {
        return new DefaultTrackSelector(context);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
    public /* synthetic */ void onRendererCapabilitiesChanged(Renderer renderer) {
        androidx.media3.exoplayer.trackselection.f.a(this, renderer);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
    public void onTrackSelectionsInvalidated() {
        DefaultPreloadManager.lambda$new$0();
    }
}
