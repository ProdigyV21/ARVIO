package androidx.core.view;

import android.view.WindowInsetsAnimation;

/* JADX INFO: loaded from: classes3.dex */
public final class r2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final androidx.core.graphics.c f2309a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final androidx.core.graphics.c f2310b;

    public r2(androidx.core.graphics.c cVar, androidx.core.graphics.c cVar2) {
        this.f2309a = cVar;
        this.f2310b = cVar2;
    }

    public final String toString() {
        return "Bounds{lower=" + this.f2309a + " upper=" + this.f2310b + "}";
    }

    public r2(WindowInsetsAnimation.Bounds bounds) {
        this.f2309a = z2.e(bounds);
        this.f2310b = z2.d(bounds);
    }
}
