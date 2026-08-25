package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.Timeline;
import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.preload.PreCacheHelper;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3810i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3811l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3812m;

    public /* synthetic */ o(Object obj, Object obj2, int i10) {
        this.f3810i = i10;
        this.f3811l = obj;
        this.f3812m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3810i) {
            case 0:
                ((PreCacheHelper.DownloadCallback) this.f3811l).lambda$notifyListeners$6((Consumer) this.f3812m);
                break;
            default:
                ((PreloadMediaSource) this.f3811l).lambda$onChildSourceInfoRefreshed$2((Timeline) this.f3812m);
                break;
        }
    }
}
