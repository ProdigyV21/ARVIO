package com.google.android.gms.auth.api.proxy;

import a0.c;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Patterns;
import androidx.media3.common.C;
import com.google.android.gms.common.internal.t;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class ProxyRequest extends a {
    public static final int VERSION_CODE = 2;
    public final byte[] body;
    public final int httpMethod;
    public final long timeoutMillis;
    public final String url;
    final int zza;
    final Bundle zzb;
    public static final Parcelable.Creator<ProxyRequest> CREATOR = new zza();
    public static final int HTTP_METHOD_GET = 0;
    public static final int HTTP_METHOD_POST = 1;
    public static final int HTTP_METHOD_PUT = 2;
    public static final int HTTP_METHOD_DELETE = 3;
    public static final int HTTP_METHOD_HEAD = 4;
    public static final int HTTP_METHOD_OPTIONS = 5;
    public static final int HTTP_METHOD_TRACE = 6;
    public static final int HTTP_METHOD_PATCH = 7;
    public static final int LAST_CODE = 7;

    public static class Builder {
        private final String zza;
        private int zzb = ProxyRequest.HTTP_METHOD_GET;
        private long zzc = C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
        private byte[] zzd = new byte[0];
        private final Bundle zze = new Bundle();

        public Builder(String str) {
            t.f(str);
            if (!Patterns.WEB_URL.matcher(str).matches()) {
                throw new IllegalArgumentException(c.l("The supplied url [ ", str, "] is not match Patterns.WEB_URL!"));
            }
            this.zza = str;
        }

        public ProxyRequest build() {
            if (this.zzd == null) {
                this.zzd = new byte[0];
            }
            return new ProxyRequest(2, this.zza, this.zzb, this.zzc, this.zzd, this.zze);
        }

        public Builder putHeader(String str, String str2) {
            t.g(str, "Header name cannot be null or empty!");
            Bundle bundle = this.zze;
            if (str2 == null) {
                str2 = "";
            }
            bundle.putString(str, str2);
            return this;
        }

        public Builder setBody(byte[] bArr) {
            this.zzd = bArr;
            return this;
        }

        public Builder setHttpMethod(int i10) {
            boolean z = false;
            if (i10 >= 0 && i10 <= ProxyRequest.LAST_CODE) {
                z = true;
            }
            t.a("Unrecognized http method code.", z);
            this.zzb = i10;
            return this;
        }

        public Builder setTimeoutMillis(long j10) {
            t.a("The specified timeout must be non-negative.", j10 >= 0);
            this.zzc = j10;
            return this;
        }
    }

    public ProxyRequest(int i10, String str, int i11, long j10, byte[] bArr, Bundle bundle) {
        this.zza = i10;
        this.url = str;
        this.httpMethod = i11;
        this.timeoutMillis = j10;
        this.body = bArr;
        this.zzb = bundle;
    }

    public Map<String, String> getHeaderMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.zzb.size());
        for (String str : this.zzb.keySet()) {
            String string = this.zzb.getString(str);
            if (string == null) {
                string = "";
            }
            linkedHashMap.put(str, string);
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    public String toString() {
        return "ProxyRequest[ url: " + this.url + ", method: " + this.httpMethod + " ]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 1, this.url, false);
        int i11 = this.httpMethod;
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(i11);
        long j10 = this.timeoutMillis;
        a.a.l0(parcel, 3, 8);
        parcel.writeLong(j10);
        a.a.a0(parcel, 4, this.body, false);
        a.a.Z(parcel, 5, this.zzb, false);
        int i12 = this.zza;
        a.a.l0(parcel, 1000, 4);
        parcel.writeInt(i12);
        a.a.n0(iM0, parcel);
    }
}
