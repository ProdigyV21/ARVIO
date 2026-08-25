package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.extractor.SeekMap;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3770i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3771l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3772m;

    public /* synthetic */ m(Object obj, Object obj2, int i10) {
        this.f3770i = i10;
        this.f3771l = obj;
        this.f3772m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3770i) {
            case 0:
                ((ProgressiveMediaPeriod) this.f3771l).lambda$seekMap$1((SeekMap) this.f3772m);
                break;
            default:
                ((Consumer) this.f3771l).accept((MediaSourceEventListener) this.f3772m);
                break;
        }
    }
}
