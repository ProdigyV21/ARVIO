package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class e extends g {
    @Override // com.google.android.gms.internal.cast.g
    public final void a(Object obj, long j10, byte b10) {
        if (h.f13272g) {
            h.c(obj, j10, b10);
        } else {
            h.d(obj, j10, b10);
        }
    }

    @Override // com.google.android.gms.internal.cast.g
    public final boolean b(long j10, Object obj) {
        return h.f13272g ? h.n(j10, obj) : h.o(j10, obj);
    }

    @Override // com.google.android.gms.internal.cast.g
    public final void c(Object obj, long j10, boolean z) {
        if (h.f13272g) {
            h.c(obj, j10, z ? (byte) 1 : (byte) 0);
        } else {
            h.d(obj, j10, z ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.cast.g
    public final float d(long j10, Object obj) {
        return Float.intBitsToFloat(this.f13253a.getInt(obj, j10));
    }

    @Override // com.google.android.gms.internal.cast.g
    public final void e(Object obj, long j10, float f10) {
        this.f13253a.putInt(obj, j10, Float.floatToIntBits(f10));
    }

    @Override // com.google.android.gms.internal.cast.g
    public final double f(long j10, Object obj) {
        return Double.longBitsToDouble(this.f13253a.getLong(obj, j10));
    }

    @Override // com.google.android.gms.internal.cast.g
    public final void g(Object obj, long j10, double d4) {
        this.f13253a.putLong(obj, j10, Double.doubleToLongBits(d4));
    }
}
