package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public final class qb implements fb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ib f13566a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f13567b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object[] f13568c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f13569d;

    public qb(ib ibVar, String str, Object[] objArr) {
        this.f13566a = ibVar;
        this.f13567b = str;
        this.f13568c = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.f13569d = cCharAt;
            return;
        }
        int i10 = cCharAt & 8191;
        int i11 = 1;
        int i12 = 13;
        while (true) {
            int i13 = i11 + 1;
            char cCharAt2 = str.charAt(i11);
            if (cCharAt2 < 55296) {
                this.f13569d = i10 | (cCharAt2 << i12);
                return;
            } else {
                i10 |= (cCharAt2 & 8191) << i12;
                i12 += 13;
                i11 = i13;
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.fb
    public final boolean zza() {
        return (this.f13569d & 2) == 2;
    }

    @Override // com.google.android.gms.internal.cast.fb
    public final ib zzb() {
        return this.f13566a;
    }

    @Override // com.google.android.gms.internal.cast.fb
    public final int zzc() {
        int i10 = this.f13569d;
        if ((i10 & 1) != 0) {
            return 1;
        }
        return (i10 & 4) == 4 ? 3 : 2;
    }
}
