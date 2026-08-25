package androidx.activity;

import android.window.BackEvent;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f919a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f921c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f922d;

    public c(BackEvent backEvent) {
        float fG = a.g(backEvent);
        float fH = a.h(backEvent);
        float fC = a.c(backEvent);
        int iE = a.e(backEvent);
        this.f919a = fG;
        this.f920b = fH;
        this.f921c = fC;
        this.f922d = iE;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("BackEventCompat{touchX=");
        sb2.append(this.f919a);
        sb2.append(", touchY=");
        sb2.append(this.f920b);
        sb2.append(", progress=");
        sb2.append(this.f921c);
        sb2.append(", swipeEdge=");
        return a0.c.o(sb2, this.f922d, '}');
    }
}
