package f4;

import android.os.Parcel;

/* JADX INFO: loaded from: classes4.dex */
public final class l extends o4.a {
    public final com.google.android.gms.dynamic.b f0(com.google.android.gms.dynamic.d dVar, int i10) {
        Parcel parcelC = c();
        p4.g.b(parcelC, dVar);
        parcelC.writeString("com.google.android.gms.cast.framework.dynamite");
        parcelC.writeInt(i10);
        Parcel parcelA = a(2, parcelC);
        com.google.android.gms.dynamic.b bVarF0 = com.google.android.gms.dynamic.d.f0(parcelA.readStrongBinder());
        parcelA.recycle();
        return bVarF0;
    }

    public final com.google.android.gms.dynamic.b g0(com.google.android.gms.dynamic.d dVar, int i10) {
        Parcel parcelC = c();
        p4.g.b(parcelC, dVar);
        parcelC.writeString("com.google.android.gms.cast.framework.dynamite");
        parcelC.writeInt(i10);
        Parcel parcelA = a(4, parcelC);
        com.google.android.gms.dynamic.b bVarF0 = com.google.android.gms.dynamic.d.f0(parcelA.readStrongBinder());
        parcelA.recycle();
        return bVarF0;
    }

    public final com.google.android.gms.dynamic.b h0(com.google.android.gms.dynamic.d dVar, boolean z, long j10) {
        Parcel parcelC = c();
        p4.g.b(parcelC, dVar);
        parcelC.writeString("com.google.android.gms.cast.framework.dynamite");
        parcelC.writeInt(z ? 1 : 0);
        parcelC.writeLong(j10);
        Parcel parcelA = a(7, parcelC);
        com.google.android.gms.dynamic.b bVarF0 = com.google.android.gms.dynamic.d.f0(parcelA.readStrongBinder());
        parcelA.recycle();
        return bVarF0;
    }

    public final com.google.android.gms.dynamic.b i0(com.google.android.gms.dynamic.d dVar, int i10, com.google.android.gms.dynamic.d dVar2) {
        Parcel parcelC = c();
        p4.g.b(parcelC, dVar);
        parcelC.writeString("com.google.android.gms.cast.framework.dynamite");
        parcelC.writeInt(i10);
        p4.g.b(parcelC, dVar2);
        Parcel parcelA = a(8, parcelC);
        com.google.android.gms.dynamic.b bVarF0 = com.google.android.gms.dynamic.d.f0(parcelA.readStrongBinder());
        parcelA.recycle();
        return bVarF0;
    }
}
