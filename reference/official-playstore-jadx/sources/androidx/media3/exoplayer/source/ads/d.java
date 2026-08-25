package androidx.media3.exoplayer.source.ads;

import androidx.media3.common.Timeline;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ads.AdsMediaSource;
import com.google.common.collect.l1;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3748i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3749l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3750m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3751n;

    public /* synthetic */ d(Object obj, Object obj2, Object obj3, int i10) {
        this.f3748i = i10;
        this.f3749l = obj;
        this.f3750m = obj2;
        this.f3751n = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3748i) {
            case 0:
                ((AdsMediaSource.AdPrepareListener) this.f3749l).lambda$onPrepareError$1((MediaSource.MediaPeriodId) this.f3750m, (IOException) this.f3751n);
                break;
            default:
                ((ServerSideAdInsertionMediaSource) this.f3749l).lambda$setAdPlaybackStates$0((l1) this.f3750m, (Timeline) this.f3751n);
                break;
        }
    }
}
