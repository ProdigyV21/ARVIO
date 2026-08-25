package com.google.android.gms.internal.auth;

/* JADX INFO: loaded from: classes4.dex */
public final class h3 implements t2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final w2 f13042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f13043b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f13044c;

    public h3(x3 x3Var, Object[] objArr) {
        this.f13042a = x3Var;
        this.f13043b = objArr;
        char cCharAt = "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a".charAt(0);
        if (cCharAt < 55296) {
            this.f13044c = cCharAt;
            return;
        }
        int i10 = cCharAt & 8191;
        int i11 = 1;
        int i12 = 13;
        while (true) {
            int i13 = i11 + 1;
            char cCharAt2 = "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a".charAt(i11);
            if (cCharAt2 < 55296) {
                this.f13044c = i10 | (cCharAt2 << i12);
                return;
            } else {
                i10 |= (cCharAt2 & 8191) << i12;
                i12 += 13;
                i11 = i13;
            }
        }
    }

    @Override // com.google.android.gms.internal.auth.t2
    public final w2 zza() {
        return this.f13042a;
    }

    @Override // com.google.android.gms.internal.auth.t2
    public final boolean zzb() {
        return (this.f13044c & 2) == 2;
    }

    @Override // com.google.android.gms.internal.auth.t2
    public final int zzc() {
        return (this.f13044c & 1) != 0 ? 1 : 2;
    }
}
