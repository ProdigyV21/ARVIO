package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.offline.DownloadHelper;
import androidx.media3.exoplayer.source.preload.PreCacheHelper;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3562i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f3563l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3564m;

    public /* synthetic */ j(int i10, Object obj, boolean z) {
        this.f3562i = i10;
        this.f3564m = obj;
        this.f3563l = z;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        switch (this.f3562i) {
            case 0:
                ((AudioRendererEventListener.EventDispatcher) this.f3564m).lambda$skipSilenceEnabledChanged$7(this.f3563l);
                break;
            case 1:
                ((DownloadHelper) this.f3564m).lambda$onMediaPrepared$2(this.f3563l);
                break;
            default:
                ((PreCacheHelper) this.f3564m).lambda$release$2(this.f3563l);
                break;
        }
    }
}
