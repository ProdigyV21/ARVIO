package androidx.media3.session;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class s2 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4149i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSessionService f4150l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaSession f4151m;

    public /* synthetic */ s2(MediaSessionService mediaSessionService, MediaSession mediaSession, int i10) {
        this.f4149i = i10;
        this.f4150l = mediaSessionService;
        this.f4151m = mediaSession;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4149i) {
            case 0:
                this.f4150l.lambda$removeSession$1(this.f4151m);
                break;
            default:
                this.f4150l.lambda$addSession$0(this.f4151m);
                break;
        }
    }
}
