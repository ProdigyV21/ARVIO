package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.server.response.a;
import com.google.android.gms.common.server.response.c;
import com.google.android.gms.internal.auth.n0;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class zzu extends n0 {
    public static final Parcelable.Creator<zzu> CREATOR = new zzv();
    private static final HashMap zzc;
    final Set zza;
    final int zzb;
    private zzw zzd;
    private String zze;
    private String zzf;
    private String zzg;

    static {
        HashMap map = new HashMap();
        zzc = map;
        map.put("authenticatorInfo", new a(11, false, 11, false, "authenticatorInfo", 2, zzw.class));
        map.put("signature", new a(7, false, 7, false, "signature", 3, null));
        map.put("package", new a(7, false, 7, false, "package", 4, null));
    }

    public zzu() {
        this.zza = new HashSet(3);
        this.zzb = 1;
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void addConcreteTypeInternal(a aVar, String str, c cVar) {
        int i10 = aVar.f12953q;
        if (i10 != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not a known custom type. Found %s", Integer.valueOf(i10), cVar.getClass().getCanonicalName()));
        }
        this.zzd = (zzw) cVar;
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
        if (i10 == 3) {
            return this.zze;
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

    @Override // com.google.android.gms.common.server.response.c
    public final void setStringInternal(a aVar, String str, String str2) {
        int i10 = aVar.f12953q;
        if (i10 == 3) {
            this.zze = str2;
        } else {
            if (i10 != 4) {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", Integer.valueOf(i10)));
            }
            this.zzf = str2;
        }
        this.zza.add(Integer.valueOf(i10));
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
            a.a.f0(parcel, 2, this.zzd, i10, true);
        }
        if (set.contains(3)) {
            a.a.g0(parcel, 3, this.zze, true);
        }
        if (set.contains(4)) {
            a.a.g0(parcel, 4, this.zzf, true);
        }
        if (set.contains(5)) {
            a.a.g0(parcel, 5, this.zzg, true);
        }
        a.a.n0(iM0, parcel);
    }

    public zzu(Set set, int i10, zzw zzwVar, String str, String str2, String str3) {
        this.zza = set;
        this.zzb = i10;
        this.zzd = zzwVar;
        this.zze = str;
        this.zzf = str2;
        this.zzg = str3;
    }
}
