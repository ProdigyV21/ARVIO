package androidx.media3.common.util;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3420a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f3421b;

    public /* synthetic */ i(String str, int i10) {
        this.f3420a = i10;
        this.f3421b = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        switch (this.f3420a) {
            case 0:
                return Util.lambda$newSingleThreadExecutor$3(this.f3421b, runnable);
            default:
                return Util.lambda$newSingleThreadScheduledExecutor$4(this.f3421b, runnable);
        }
    }
}
