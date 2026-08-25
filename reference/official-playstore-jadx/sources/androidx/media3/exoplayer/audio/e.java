package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3546i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f3547l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3548m;

    public /* synthetic */ e(Object obj, int i10, int i11) {
        this.f3546i = i11;
        this.f3548m = obj;
        this.f3547l = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3546i) {
            case 0:
                ((AudioRendererEventListener.EventDispatcher) this.f3548m).lambda$audioSessionIdChanged$12(this.f3547l);
                break;
            default:
                ((i0.o) this.f3548m).onFontRetrievalFailed(this.f3547l);
                break;
        }
    }
}
