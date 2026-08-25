package androidx.media3.exoplayer.source;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class l implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3768i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ProgressiveMediaPeriod f3769l;

    public /* synthetic */ l(ProgressiveMediaPeriod progressiveMediaPeriod, int i10) {
        this.f3768i = i10;
        this.f3769l = progressiveMediaPeriod;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3768i) {
            case 0:
                this.f3769l.lambda$onLengthKnown$2();
                break;
            case 1:
                this.f3769l.maybeFinishPrepare();
                break;
            default:
                this.f3769l.lambda$new$0();
                break;
        }
    }
}
