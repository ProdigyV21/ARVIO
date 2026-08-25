package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f13198e = new c(new int[0], new Object[0], false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int[] f13199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f13200b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f13201c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f13202d;

    public c(int[] iArr, Object[] objArr, boolean z) {
        this.f13199a = iArr;
        this.f13200b = objArr;
        this.f13202d = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof c)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return 506991;
    }
}
