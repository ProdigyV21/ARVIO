package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.source.MediaLoadData;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3683i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f3684l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Pair f3685m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f3686n;

    public /* synthetic */ l0(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, MediaLoadData mediaLoadData, int i10) {
        this.f3683i = i10;
        this.f3684l = forwardingEventListener;
        this.f3685m = pair;
        this.f3686n = mediaLoadData;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3683i) {
            case 0:
                this.f3684l.lambda$onUpstreamDiscarded$4(this.f3685m, this.f3686n);
                break;
            default:
                this.f3684l.lambda$onDownstreamFormatChanged$5(this.f3685m, this.f3686n);
                break;
        }
    }
}
