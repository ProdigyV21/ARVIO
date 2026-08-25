package androidx.media3.exoplayer;

import androidx.media3.exoplayer.DefaultSuitableOutputChecker;
import androidx.media3.exoplayer.StreamVolumeManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3454i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3455l;

    public /* synthetic */ a(Object obj, int i10) {
        this.f3454i = i10;
        this.f3455l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3454i) {
            case 0:
                ((DefaultSuitableOutputChecker.ImplApi23) this.f3455l).lambda$disable$2();
                break;
            case 1:
                ((DefaultSuitableOutputChecker.ImplApi35) this.f3455l).lambda$disable$2();
                break;
            case 2:
                ((ExoPlayerImpl) this.f3455l).lambda$new$3();
                break;
            case 3:
                ((StreamVolumeManager.VolumeChangeReceiver) this.f3455l).lambda$onReceive$0();
                break;
            default:
                ((MetadataRetrieverInternal) this.f3455l).lambda$close$0();
                break;
        }
    }
}
