package androidx.media3.exoplayer.source.preload;

import android.content.Context;
import androidx.media3.exoplayer.source.preload.DefaultPreloadManager;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import com.google.common.base.c0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class h implements c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3795i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Context f3796l;

    public /* synthetic */ h(Context context, int i10) {
        this.f3795i = i10;
        this.f3796l = context;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3795i) {
            case 0:
                return DefaultPreloadManager.MediaSourceFactorySupplier.lambda$new$0(this.f3796l);
            case 1:
                return DefaultBandwidthMeter.getSingletonInstance(this.f3796l);
            default:
                return DefaultPreloadManager.Builder.lambda$new$1(this.f3796l);
        }
    }
}
