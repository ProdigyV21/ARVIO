package androidx.core.provider;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes3.dex */
public final class f implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ String f2146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Context f2147c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ e f2148d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f2149e;

    public /* synthetic */ f(String str, Context context, e eVar, int i10, int i11) {
        this.f2145a = i11;
        this.f2146b = str;
        this.f2147c = context;
        this.f2148d = eVar;
        this.f2149e = i10;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f2145a) {
            case 0:
                return i.a(this.f2146b, this.f2147c, this.f2148d, this.f2149e);
            default:
                try {
                    return i.a(this.f2146b, this.f2147c, this.f2148d, this.f2149e);
                } catch (Throwable unused) {
                    return new h(-3);
                }
        }
    }
}
