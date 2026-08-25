package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.server.response.a;
import com.google.android.gms.common.server.response.c;
import com.google.android.gms.internal.auth.n0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class zzo extends n0 {
    public static final Parcelable.Creator<zzo> CREATOR = new zzp();
    private static final HashMap zzc;
    final Set zza;
    final int zzb;
    private ArrayList zzd;
    private int zze;
    private zzs zzf;

    static {
        HashMap map = new HashMap();
        zzc = map;
        map.put("authenticatorData", new a(11, true, 11, true, "authenticatorData", 2, zzu.class));
        map.put("progress", new a(11, false, 11, false, "progress", 4, zzs.class));
    }

    public zzo() {
        this.zza = new HashSet(1);
        this.zzb = 1;
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void addConcreteTypeArrayInternal(a aVar, String str, ArrayList arrayList) {
        int i10 = aVar.f12953q;
        if (i10 != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known ConcreteTypeArray type. Found %s", Integer.valueOf(i10), arrayList.getClass().getCanonicalName()));
        }
        this.zzd = arrayList;
        this.zza.add(Integer.valueOf(i10));
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void addConcreteTypeInternal(a aVar, String str, c cVar) {
        int i10 = aVar.f12953q;
        if (i10 != 4) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", Integer.valueOf(i10), cVar.getClass().getCanonicalName()));
        }
        this.zzf = (zzs) cVar;
        this.zza.add(Integer.valueOf(i10));
    }

    @Override // com.google.android.gms.common.server.response.c
    public final /* synthetic */ Map getFieldMappings() {
        return zzc;
    }

    @Override // com.google.android.gms.common.server.response.c
    public final Object getFieldValue(a aVar) {
        int i10 = aVar.f12953q;
        if (i10 == 1) {
            return Integer.valueOf(this.zzb);
        }
        if (i10 == 2) {
            return this.zzd;
        }
        if (i10 == 4) {
            return this.zzf;
        }
        throw new IllegalStateException(a0.c.i(aVar.f12953q, "Unknown SafeParcelable id="));
    }

    @Override // com.google.android.gms.common.server.response.c
    public final boolean isFieldSet(a aVar) {
        return this.zza.contains(Integer.valueOf(aVar.f12953q));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        Set set = this.zza;
        if (set.contains(1)) {
            int i11 = this.zzb;
            a.a.l0(parcel, 1, 4);
            parcel.writeInt(i11);
        }
        if (set.contains(2)) {
            a.a.k0(parcel, 2, this.zzd, true);
        }
        if (set.contains(3)) {
            int i12 = this.zze;
            a.a.l0(parcel, 3, 4);
            parcel.writeInt(i12);
        }
        if (set.contains(4)) {
            a.a.f0(parcel, 4, this.zzf, i10, true);
        }
        a.a.n0(iM0, parcel);
    }

    public zzo(Set set, int i10, ArrayList arrayList, int i11, zzs zzsVar) {
        this.zza = set;
        this.zzb = i10;
        this.zzd = arrayList;
        this.zze = i11;
        this.zzf = zzsVar;
    }
}
