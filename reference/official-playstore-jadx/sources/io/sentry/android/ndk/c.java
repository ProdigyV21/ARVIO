package io.sentry.android.ndk;

import io.sentry.ndk.NativeScope;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class c implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16843i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ d f16844l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f16845m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f16846n;

    public /* synthetic */ c(d dVar, String str, String str2, int i10) {
        this.f16843i = i10;
        this.f16844l = dVar;
        this.f16845m = str;
        this.f16846n = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f16843i) {
            case 0:
                this.f16844l.f16848b.getClass();
                NativeScope.nativeSetExtra(this.f16845m, this.f16846n);
                break;
            default:
                this.f16844l.f16848b.getClass();
                NativeScope.nativeSetTag(this.f16845m, this.f16846n);
                break;
        }
    }
}
