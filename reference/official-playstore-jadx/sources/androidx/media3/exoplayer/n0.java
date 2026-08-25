package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3708i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f3709l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Pair f3710m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ LoadEventInfo f3711n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f3712o;

    public /* synthetic */ n0(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i10) {
        this.f3708i = i10;
        this.f3709l = forwardingEventListener;
        this.f3710m = pair;
        this.f3711n = loadEventInfo;
        this.f3712o = mediaLoadData;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3708i) {
            case 0:
                this.f3709l.lambda$onLoadCanceled$2(this.f3710m, this.f3711n, this.f3712o);
                break;
            default:
                this.f3709l.lambda$onLoadCompleted$1(this.f3710m, this.f3711n, this.f3712o);
                break;
        }
    }
}
