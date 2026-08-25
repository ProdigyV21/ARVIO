package com.google.android.gms.internal.cast;

import android.os.Parcel;

/* JADX INFO: loaded from: classes4.dex */
public abstract class e3 extends e0 implements f3 {
    public e3() {
        super("com.google.android.gms.cast.remote_display.ICastRemoteDisplaySessionCallbacks");
    }

    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) {
        if (i10 != 1) {
            return false;
        }
        int i12 = parcel.readInt();
        com.google.android.gms.common.api.j jVar = (com.google.android.gms.common.api.j) a1.b(parcel, com.google.android.gms.common.api.j.CREATOR);
        a1.e(parcel);
        s(i12, jVar);
        return true;
    }
}
