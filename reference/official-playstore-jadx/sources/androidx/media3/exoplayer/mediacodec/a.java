package androidx.media3.exoplayer.mediacodec;

import androidx.media3.exoplayer.FormatHolder;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3695i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3696l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3697m;

    public /* synthetic */ a(Object obj, Object obj2, int i10) {
        this.f3695i = i10;
        this.f3696l = obj;
        this.f3697m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3695i) {
            case 0:
                ((AsynchronousMediaCodecAdapter) this.f3696l).lambda$useInputBuffer$0((Runnable) this.f3697m);
                break;
            default:
                ((MediaCodecRenderer) this.f3696l).lambda$feedInputBuffer$0((FormatHolder) this.f3697m);
                break;
        }
    }
}
