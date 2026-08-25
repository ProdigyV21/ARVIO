package androidx.media3.exoplayer.trackselection;

import androidx.media3.common.Tracks;
import androidx.media3.exoplayer.RendererConfiguration;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class TrackSelectorResult {
    public final Object info;
    public final int length;
    public final RendererConfiguration[] rendererConfigurations;
    public final ExoTrackSelection[] selections;
    public final Tracks tracks;

    @Deprecated
    public TrackSelectorResult(RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr, Object obj) {
        this(rendererConfigurationArr, exoTrackSelectionArr, Tracks.EMPTY, obj);
    }

    public boolean isEquivalent(TrackSelectorResult trackSelectorResult) {
        if (trackSelectorResult == null || trackSelectorResult.selections.length != this.selections.length) {
            return false;
        }
        for (int i10 = 0; i10 < this.selections.length; i10++) {
            if (!isEquivalent(trackSelectorResult, i10)) {
                return false;
            }
        }
        return true;
    }

    public boolean isRendererEnabled(int i10) {
        return this.rendererConfigurations[i10] != null;
    }

    public TrackSelectorResult(RendererConfiguration[] rendererConfigurationArr, ExoTrackSelection[] exoTrackSelectionArr, Tracks tracks, Object obj) {
        ac.b.j(rendererConfigurationArr.length == exoTrackSelectionArr.length);
        this.rendererConfigurations = rendererConfigurationArr;
        this.selections = (ExoTrackSelection[]) exoTrackSelectionArr.clone();
        this.tracks = tracks;
        this.info = obj;
        this.length = rendererConfigurationArr.length;
    }

    public boolean isEquivalent(TrackSelectorResult trackSelectorResult, int i10) {
        return trackSelectorResult != null && Objects.equals(this.rendererConfigurations[i10], trackSelectorResult.rendererConfigurations[i10]) && Objects.equals(this.selections[i10], trackSelectorResult.selections[i10]);
    }
}
