package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.source.MediaSource;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3778i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ BasePreloadManager f3779l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaSource f3780m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ com.google.common.base.r f3781n;

    public /* synthetic */ a(BasePreloadManager basePreloadManager, MediaSource mediaSource, com.google.common.base.r rVar, int i10) {
        this.f3778i = i10;
        this.f3779l = basePreloadManager;
        this.f3780m = mediaSource;
        this.f3781n = rVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3778i) {
            case 0:
                this.f3779l.lambda$onSkipped$8(this.f3780m, this.f3781n);
                break;
            default:
                this.f3779l.lambda$onCompleted$1(this.f3780m, this.f3781n);
                break;
        }
    }
}
