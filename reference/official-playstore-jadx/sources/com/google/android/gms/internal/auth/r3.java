package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class r3 extends t3 {
    @Override // com.google.android.gms.internal.auth.t3
    public final double a(long j10, Object obj) {
        return Double.longBitsToDouble(this.f13125a.getLong(obj, j10));
    }

    @Override // com.google.android.gms.internal.auth.t3
    public final float b(long j10, Object obj) {
        return Float.intBitsToFloat(this.f13125a.getInt(obj, j10));
    }

    @Override // com.google.android.gms.internal.auth.t3
    public final void c(Object obj, long j10, boolean z) {
        if (u3.f13133f) {
            u3.f(obj, j10, z);
        } else {
            u3.g(obj, j10, z);
        }
    }

    @Override // com.google.android.gms.internal.auth.t3
    public final void d(Object obj, long j10, double d4) {
        this.f13125a.putLong(obj, j10, Double.doubleToLongBits(d4));
    }

    @Override // com.google.android.gms.internal.auth.t3
    public final void e(Object obj, long j10, float f10) {
        this.f13125a.putInt(obj, j10, Float.floatToIntBits(f10));
    }

    @Override // com.google.android.gms.internal.auth.t3
    public final boolean f(long j10, Object obj) {
        return u3.f13133f ? u3.k(j10, obj) : u3.l(j10, obj);
    }
}
