package androidx.media3.exoplayer;

import android.util.Pair;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.drm.KeyRequestInfo;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.collect.e1;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j0 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3674i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3675l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3676m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3677n;

    public /* synthetic */ j0(Object obj, Object obj2, Object obj3, int i10) {
        this.f3674i = i10;
        this.f3675l = obj;
        this.f3676m = obj2;
        this.f3677n = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3674i) {
            case 0:
                ((MediaPeriodQueue) this.f3675l).lambda$notifyQueueUpdate$0((e1) this.f3676m, (MediaSource.MediaPeriodId) this.f3677n);
                break;
            case 1:
                ((MediaSourceList.ForwardingEventListener) this.f3675l).lambda$onDrmKeysLoaded$7((Pair) this.f3676m, (KeyRequestInfo) this.f3677n);
                break;
            default:
                ((MediaSourceList.ForwardingEventListener) this.f3675l).lambda$onDrmSessionManagerError$8((Pair) this.f3676m, (Exception) this.f3677n);
                break;
        }
    }
}
