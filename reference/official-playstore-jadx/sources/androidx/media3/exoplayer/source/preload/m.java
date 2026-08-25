package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.preload.PreCacheHelper;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3804i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PreCacheHelper.DownloadCallback f3805l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ IOException f3806m;

    public /* synthetic */ m(PreCacheHelper.DownloadCallback downloadCallback, IOException iOException, int i10) {
        this.f3804i = i10;
        this.f3805l = downloadCallback;
        this.f3806m = iOException;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object obj) {
        switch (this.f3804i) {
            case 0:
                this.f3805l.lambda$onDownloadStopped$2(this.f3806m, (PreCacheHelper.Listener) obj);
                break;
            default:
                this.f3805l.lambda$onPrepareError$1(this.f3806m, (PreCacheHelper.Listener) obj);
                break;
        }
    }
}
