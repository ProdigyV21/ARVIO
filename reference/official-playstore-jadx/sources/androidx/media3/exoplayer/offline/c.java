package androidx.media3.exoplayer.offline;

import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.trackselection.TrackSelector;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class c implements TrackSelector.InvalidationListener {
    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
    public final /* synthetic */ void onRendererCapabilitiesChanged(Renderer renderer) {
        androidx.media3.exoplayer.trackselection.f.a(this, renderer);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
    public final void onTrackSelectionsInvalidated() {
        DownloadHelper.lambda$new$0();
    }
}
