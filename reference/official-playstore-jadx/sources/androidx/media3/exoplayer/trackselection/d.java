package androidx.media3.exoplayer.trackselection;

import android.os.Bundle;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import com.google.common.base.m;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3836a;

    public /* synthetic */ d(int i10) {
        this.f3836a = i10;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        switch (this.f3836a) {
            case 0:
                return ((TrackGroupArray) obj).toBundle();
            case 1:
                return ((DefaultTrackSelector.SelectionOverride) obj).toBundle();
            case 2:
                return TrackGroupArray.fromBundle((Bundle) obj);
            default:
                return DefaultTrackSelector.SelectionOverride.fromBundle((Bundle) obj);
        }
    }
}
