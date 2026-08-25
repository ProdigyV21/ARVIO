package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class ProxyResponse extends a {
    public static final Parcelable.Creator<ProxyResponse> CREATOR = new zzb();
    public static final int STATUS_CODE_NO_CONNECTION = -1;
    public final byte[] body;
    public final int googlePlayServicesStatusCode;
    public final PendingIntent recoveryAction;
    public final int statusCode;
    final int zza;
    final Bundle zzb;

    public ProxyResponse(int i10, int i11, PendingIntent pendingIntent, int i12, Bundle bundle, byte[] bArr) {
        this.zza = i10;
        this.googlePlayServicesStatusCode = i11;
        this.statusCode = i12;
        this.zzb = bundle;
        this.body = bArr;
        this.recoveryAction = pendingIntent;
    }

    public static ProxyResponse createErrorProxyResponse(int i10, PendingIntent pendingIntent, int i11, Map<String, String> map, byte[] bArr) {
        return new ProxyResponse(1, i10, pendingIntent, i11, zza(map), bArr);
    }

    private static Bundle zza(Map map) {
        Bundle bundle = new Bundle();
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return bundle;
    }

    public Map<String, String> getHeaders() {
        if (this.zzb == null) {
            return Collections.EMPTY_MAP;
        }
        HashMap map = new HashMap();
        for (String str : this.zzb.keySet()) {
            map.put(str, this.zzb.getString(str));
        }
        return map;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int i11 = this.googlePlayServicesStatusCode;
        a.a.l0(parcel, 1, 4);
        parcel.writeInt(i11);
        a.a.f0(parcel, 2, this.recoveryAction, i10, false);
        int i12 = this.statusCode;
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(i12);
        a.a.Z(parcel, 4, this.zzb, false);
        a.a.a0(parcel, 5, this.body, false);
        int i13 = this.zza;
        a.a.l0(parcel, 1000, 4);
        parcel.writeInt(i13);
        a.a.n0(iM0, parcel);
    }

    public ProxyResponse(int i10, PendingIntent pendingIntent, int i11, Bundle bundle, byte[] bArr) {
        this(1, i10, pendingIntent, i11, bundle, bArr);
    }

    public ProxyResponse(int i10, Map<String, String> map, byte[] bArr) {
        this(1, 0, null, i10, zza(map), bArr);
    }
}
