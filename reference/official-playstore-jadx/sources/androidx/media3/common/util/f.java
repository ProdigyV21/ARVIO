package androidx.media3.common.util;

import android.content.Context;
import androidx.media3.common.util.NetworkTypeObserver;
import com.google.common.base.m;
import com.google.common.util.concurrent.d1;
import com.google.common.util.concurrent.q1;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3416i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3417l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3418m;

    public /* synthetic */ f(Object obj, Object obj2, int i10) {
        this.f3416i = i10;
        this.f3417l = obj;
        this.f3418m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3416i) {
            case 0:
                ((NetworkTypeObserver.Receiver) this.f3417l).lambda$onReceive$0((Context) this.f3418m);
                break;
            case 1:
                ((BackgroundThreadStateHandler) this.f3417l).lambda$updateStateAsync$1((m) this.f3418m);
                break;
            case 2:
                ((NetworkTypeObserver) this.f3417l).lambda$new$0((Context) this.f3418m);
                break;
            default:
                Util.lambda$transformFutureAsync$1((q1) this.f3417l, (d1) this.f3418m);
                break;
        }
    }
}
