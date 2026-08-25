package com.google.android.gms.auth.api.accounttransfer;

import a0.c;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.server.response.a;
import com.google.android.gms.internal.auth.n0;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import t.e;

/* JADX INFO: loaded from: classes4.dex */
public final class zzs extends n0 {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();
    private static final e zzb;
    final int zza;
    private List zzc;
    private List zzd;
    private List zze;
    private List zzf;
    private List zzg;

    static {
        e eVar = new e(0);
        zzb = eVar;
        eVar.put("registered", a.d(2, "registered"));
        eVar.put("in_progress", a.d(3, "in_progress"));
        eVar.put("success", a.d(4, "success"));
        eVar.put("failed", a.d(5, "failed"));
        eVar.put("escrowed", a.d(6, "escrowed"));
    }

    public zzs() {
        this.zza = 1;
    }

    @Override // com.google.android.gms.common.server.response.c
    public final Map getFieldMappings() {
        return zzb;
    }

    @Override // com.google.android.gms.common.server.response.c
    public final Object getFieldValue(a aVar) {
        switch (aVar.f12953q) {
            case 1:
                return Integer.valueOf(this.zza);
            case 2:
                return this.zzc;
            case 3:
                return this.zzd;
            case 4:
                return this.zze;
            case 5:
                return this.zzf;
            case 6:
                return this.zzg;
            default:
                throw new IllegalStateException(c.i(aVar.f12953q, "Unknown SafeParcelable id="));
        }
    }

    @Override // com.google.android.gms.common.server.response.c
    public final boolean isFieldSet(a aVar) {
        return true;
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setStringsInternal(a aVar, String str, ArrayList arrayList) {
        int i10 = aVar.f12953q;
        if (i10 == 2) {
            this.zzc = arrayList;
            return;
        }
        if (i10 == 3) {
            this.zzd = arrayList;
            return;
        }
        if (i10 == 4) {
            this.zze = arrayList;
        } else if (i10 == 5) {
            this.zzf = arrayList;
        } else {
            if (i10 != 6) {
                throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string list.", Integer.valueOf(i10)));
            }
            this.zzg = arrayList;
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.zza;
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        a.a.i0(parcel, this.zzc, 2);
        a.a.i0(parcel, this.zzd, 3);
        a.a.i0(parcel, this.zze, 4);
        a.a.i0(parcel, this.zzf, 5);
        a.a.i0(parcel, this.zzg, 6);
        a.a.n0(iM0, parcel);
    }

    public zzs(int i10, List list, List list2, List list3, List list4, List list5) {
        this.zza = i10;
        this.zzc = list;
        this.zzd = list2;
        this.zze = list3;
        this.zzf = list4;
        this.zzg = list5;
    }
}
