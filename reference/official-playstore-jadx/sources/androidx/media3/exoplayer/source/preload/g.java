package androidx.media3.exoplayer.source.preload;

import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.source.preload.DefaultPreloadManager;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.base.c0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g implements c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3793i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3794l;

    public /* synthetic */ g(Object obj, int i10) {
        this.f3793i = i10;
        this.f3794l = obj;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3793i) {
            case 0:
                return DefaultPreloadManager.Builder.lambda$setLoadControl$3((LoadControl) this.f3794l);
            case 1:
                return DefaultPreloadManager.Builder.lambda$setRenderersFactory$2((RenderersFactory) this.f3794l);
            default:
                return DefaultPreloadManager.Builder.lambda$setBandwidthMeter$4((BandwidthMeter) this.f3794l);
        }
    }
}
