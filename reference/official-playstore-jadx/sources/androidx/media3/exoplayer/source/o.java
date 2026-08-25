package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.source.SingleSampleMediaSource;
import com.google.common.base.c0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o implements c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3774i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3775l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3776m;

    public /* synthetic */ o(Object obj, Object obj2, int i10) {
        this.f3774i = i10;
        this.f3775l = obj;
        this.f3776m = obj2;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3774i) {
            case 0:
                return ProgressiveMediaSource.Factory.lambda$setDownloadExecutor$1((c0) this.f3775l, (Consumer) this.f3776m);
            case 1:
                return SingleSampleMediaSource.Factory.lambda$setDownloadExecutor$0((c0) this.f3775l, (Consumer) this.f3776m);
            default:
                return ((DefaultMediaSourceFactory.DelegateFactoryLoader) this.f3775l).lambda$loadSupplier$4((DataSource.Factory) this.f3776m);
        }
    }
}
