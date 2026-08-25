package com.google.android.gms.auth.api.accounttransfer;

import a0.c;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.server.response.a;
import com.google.android.gms.internal.auth.n0;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import t.f;

/* JADX INFO: loaded from: classes4.dex */
public final class zzw extends n0 {
    public static final Parcelable.Creator<zzw> CREATOR = new zzx();
    private static final HashMap zzc;
    final Set zza;
    final int zzb;
    private String zzd;
    private int zze;
    private byte[] zzf;
    private PendingIntent zzg;
    private DeviceMetaData zzh;

    static {
        HashMap map = new HashMap();
        zzc = map;
        map.put("accountType", new a(7, false, 7, false, "accountType", 2, null));
        map.put("status", new a(0, false, 0, false, "status", 3, null));
        map.put("transferBytes", new a(8, false, 8, false, "transferBytes", 4, null));
    }

    public zzw() {
        this.zza = new f(3);
        this.zzb = 1;
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
            return Integer.valueOf(this.zze);
        }
        if (i10 == 4) {
            return this.zzf;
        }
        throw new IllegalStateException(c.i(aVar.f12953q, "Unknown SafeParcelable id="));
    }

    @Override // com.google.android.gms.common.server.response.c
    public final boolean isFieldSet(a aVar) {
        return this.zza.contains(Integer.valueOf(aVar.f12953q));
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setDecodedBytesInternal(a aVar, String str, byte[] bArr) {
        int i10 = aVar.f12953q;
        if (i10 != 4) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i10, "Field with id=", " is not known to be a byte array."));
        }
        this.zzf = bArr;
        this.zza.add(Integer.valueOf(i10));
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setIntegerInternal(a aVar, String str, int i10) {
        int i11 = aVar.f12953q;
        if (i11 != 3) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.o(i11, "Field with id=", " is not known to be an int."));
        }
        this.zze = i10;
        this.zza.add(Integer.valueOf(i11));
    }

    @Override // com.google.android.gms.common.server.response.c
    public final void setStringInternal(a aVar, String str, String str2) {
        int i10 = aVar.f12953q;
        if (i10 != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", Integer.valueOf(i10)));
        }
        this.zzd = str2;
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
            a.a.g0(parcel, 2, this.zzd, true);
        }
        if (set.contains(3)) {
            int i12 = this.zze;
            a.a.l0(parcel, 3, 4);
            parcel.writeInt(i12);
        }
        if (set.contains(4)) {
            a.a.a0(parcel, 4, this.zzf, true);
        }
        if (set.contains(5)) {
            a.a.f0(parcel, 5, this.zzg, i10, true);
        }
        if (set.contains(6)) {
            a.a.f0(parcel, 6, this.zzh, i10, true);
        }
        a.a.n0(iM0, parcel);
    }

    public zzw(Set set, int i10, String str, int i11, byte[] bArr, PendingIntent pendingIntent, DeviceMetaData deviceMetaData) {
        this.zza = set;
        this.zzb = i10;
        this.zzd = str;
        this.zze = i11;
        this.zzf = bArr;
        this.zzg = pendingIntent;
        this.zzh = deviceMetaData;
    }
}
