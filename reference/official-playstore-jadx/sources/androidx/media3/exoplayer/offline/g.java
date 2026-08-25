package androidx.media3.exoplayer.offline;

import androidx.media3.exoplayer.offline.DownloadHelper;
import androidx.media3.exoplayer.offline.DownloadService;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3722i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3723l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3724m;

    public /* synthetic */ g(Object obj, Object obj2, int i10) {
        this.f3722i = i10;
        this.f3723l = obj;
        this.f3724m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3722i) {
            case 0:
                ((DownloadService.DownloadManagerHelper) this.f3723l).lambda$attachService$0((DownloadService) this.f3724m);
                break;
            case 1:
                ((DownloadHelper) this.f3723l).lambda$onMediaPreparationFailed$3((IOException) this.f3724m);
                break;
            default:
                ((DownloadHelper) this.f3723l).lambda$prepare$1((DownloadHelper.Callback) this.f3724m);
                break;
        }
    }
}
