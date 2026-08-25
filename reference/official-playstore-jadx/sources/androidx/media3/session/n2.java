package androidx.media3.session;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n2 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4086i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSessionLegacyStub f4087l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PlayerWrapper f4088m;

    public /* synthetic */ n2(MediaSessionLegacyStub mediaSessionLegacyStub, PlayerWrapper playerWrapper, int i10) {
        this.f4086i = i10;
        this.f4087l = mediaSessionLegacyStub;
        this.f4088m = playerWrapper;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4086i) {
            case 0:
                this.f4087l.lambda$updateLegacySessionPlaybackStateAndQueue$25(this.f4088m);
                break;
            default:
                this.f4087l.lambda$updateLegacySessionPlaybackState$24(this.f4088m);
                break;
        }
    }
}
