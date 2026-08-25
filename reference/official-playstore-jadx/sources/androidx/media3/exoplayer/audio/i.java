package androidx.media3.exoplayer.audio;

import androidx.media3.exoplayer.audio.AudioRendererEventListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3559i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ AudioRendererEventListener.EventDispatcher f3560l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Exception f3561m;

    public /* synthetic */ i(AudioRendererEventListener.EventDispatcher eventDispatcher, Exception exc, int i10) {
        this.f3559i = i10;
        this.f3560l = eventDispatcher;
        this.f3561m = exc;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3559i) {
            case 0:
                this.f3560l.lambda$audioCodecError$9(this.f3561m);
                break;
            default:
                this.f3560l.lambda$audioSinkError$8(this.f3561m);
                break;
        }
    }
}
