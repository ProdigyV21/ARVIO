package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class x9 extends i9 implements Runnable, h9 {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Runnable f13687t;

    public x9(Runnable runnable) {
        super(14);
        runnable.getClass();
        this.f13687t = runnable;
    }

    @Override // com.google.android.gms.internal.cast.i9
    public final String F() {
        String string = this.f13687t.toString();
        return androidx.compose.material3.d.q(new StringBuilder(string.length() + 7), "task=[", string, "]");
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f13687t.run();
        } catch (Throwable th) {
            if (q9.f13558s.B(this, null, new f9(th))) {
                i9.H(this);
            }
            throw th;
        }
    }
}
