package androidx.core.view;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes3.dex */
public abstract class a3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f2197a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Interpolator f2198b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f2199c;

    public a3(Interpolator interpolator, long j10) {
        this.f2198b = interpolator;
        this.f2199c = j10;
    }

    public long a() {
        return this.f2199c;
    }

    public float b() {
        Interpolator interpolator = this.f2198b;
        return interpolator != null ? interpolator.getInterpolation(this.f2197a) : this.f2197a;
    }

    public void c(float f10) {
        this.f2197a = f10;
    }
}
