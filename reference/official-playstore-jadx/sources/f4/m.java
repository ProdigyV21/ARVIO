package f4;

import android.os.Parcel;

/* JADX INFO: loaded from: classes4.dex */
public final class m extends o4.a {
    public final com.google.android.gms.dynamic.b f0(com.google.android.gms.dynamic.d dVar, int i10, com.google.android.gms.dynamic.d dVar2) {
        Parcel parcelC = c();
        p4.g.b(parcelC, dVar);
        parcelC.writeString("com.google.android.gms.cast.framework.dynamite");
        parcelC.writeInt(i10);
        p4.g.b(parcelC, dVar2);
        Parcel parcelA = a(2, parcelC);
        com.google.android.gms.dynamic.b bVarF0 = com.google.android.gms.dynamic.d.f0(parcelA.readStrongBinder());
        parcelA.recycle();
        return bVarF0;
    }

    public final com.google.android.gms.dynamic.b g0(com.google.android.gms.dynamic.d dVar, int i10, com.google.android.gms.dynamic.d dVar2) {
        Parcel parcelC = c();
        p4.g.b(parcelC, dVar);
        parcelC.writeString("com.google.android.gms.cast.framework.dynamite");
        parcelC.writeInt(i10);
        p4.g.b(parcelC, dVar2);
        Parcel parcelA = a(3, parcelC);
        com.google.android.gms.dynamic.b bVarF0 = com.google.android.gms.dynamic.d.f0(parcelA.readStrongBinder());
        parcelA.recycle();
        return bVarF0;
    }
}
