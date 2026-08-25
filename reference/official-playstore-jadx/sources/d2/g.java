package d2;

import a8.g0;
import android.content.Context;
import androidx.appcompat.widget.f0;
import x6.i0;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements c2.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f14673i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f14674l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f0 f14675m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f14676n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f14677o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final i0 f14678p = new i0(new g0(this, 16));

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f14679q;

    public g(Context context, String str, f0 f0Var, boolean z, boolean z5) {
        this.f14673i = context;
        this.f14674l = str;
        this.f14675m = f0Var;
        this.f14676n = z;
        this.f14677o = z5;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        i0 i0Var = this.f14678p;
        if (i0Var.isInitialized()) {
            ((f) i0Var.getValue()).close();
        }
    }

    @Override // c2.d
    public final c2.b getWritableDatabase() {
        return ((f) this.f14678p.getValue()).i(true);
    }

    @Override // c2.d
    public final void setWriteAheadLoggingEnabled(boolean z) {
        i0 i0Var = this.f14678p;
        if (i0Var.isInitialized()) {
            ((f) i0Var.getValue()).setWriteAheadLoggingEnabled(z);
        }
        this.f14679q = z;
    }
}
