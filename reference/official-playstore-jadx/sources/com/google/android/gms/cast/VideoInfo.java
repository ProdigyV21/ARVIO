package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.internal.Logger;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class VideoInfo extends a {
    public static final int HDR_TYPE_DV = 3;
    public static final int HDR_TYPE_HDR = 4;
    public static final int HDR_TYPE_HDR10 = 2;
    public static final int HDR_TYPE_SDR = 1;
    public static final int HDR_TYPE_UNKNOWN = 0;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private static final Logger zza = new Logger("VideoInfo");
    public static final Parcelable.Creator<VideoInfo> CREATOR = new zzdh();

    public static class Builder {
        private int zza;
        private int zzb;
        private int zzc;

        public VideoInfo build() {
            return new VideoInfo(this.zza, this.zzb, this.zzc);
        }

        public Builder setHdrType(int i10) {
            this.zzc = i10;
            return this;
        }

        public Builder setHeight(int i10) {
            this.zzb = i10;
            return this;
        }

        public Builder setWidth(int i10) {
            this.zza = i10;
            return this;
        }
    }

    public VideoInfo(int i10, int i11, int i12) {
        this.zzb = i10;
        this.zzc = i11;
        this.zzd = i12;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004b A[Catch: JSONException -> 0x006a, TRY_ENTER, TryCatch #0 {JSONException -> 0x006a, blocks: (B:6:0x0005, B:29:0x0057, B:28:0x004b), top: B:34:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.android.gms.cast.VideoInfo zzb(org.json.JSONObject r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L4
            return r0
        L4:
            r1 = 0
            java.lang.String r2 = "hdrType"
            java.lang.String r2 = r6.getString(r2)     // Catch: org.json.JSONException -> L6a
            int r3 = r2.hashCode()     // Catch: org.json.JSONException -> L6a
            r4 = 3218(0xc92, float:4.51E-42)
            r5 = 1
            if (r3 == r4) goto L41
            r4 = 103158(0x192f6, float:1.44555E-40)
            if (r3 == r4) goto L37
            r4 = 113729(0x1bc41, float:1.59368E-40)
            if (r3 == r4) goto L2e
            r4 = 99136405(0x5e8b395, float:2.1883143E-35)
            if (r3 == r4) goto L24
            goto L4b
        L24:
            java.lang.String r3 = "hdr10"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L4b
            r5 = 2
            goto L57
        L2e:
            java.lang.String r3 = "sdr"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L4b
            goto L57
        L37:
            java.lang.String r3 = "hdr"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L4b
            r5 = 4
            goto L57
        L41:
            java.lang.String r3 = "dv"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L4b
            r5 = 3
            goto L57
        L4b:
            com.google.android.gms.cast.internal.Logger r3 = com.google.android.gms.cast.VideoInfo.zza     // Catch: org.json.JSONException -> L6a
            java.lang.String r4 = "Unknown HDR type: %s"
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: org.json.JSONException -> L6a
            r5[r1] = r2     // Catch: org.json.JSONException -> L6a
            r3.d(r4, r5)     // Catch: org.json.JSONException -> L6a
            r5 = r1
        L57:
            com.google.android.gms.cast.VideoInfo r2 = new com.google.android.gms.cast.VideoInfo     // Catch: org.json.JSONException -> L6a
            java.lang.String r3 = "width"
            int r3 = r6.getInt(r3)     // Catch: org.json.JSONException -> L6a
            java.lang.String r4 = "height"
            int r6 = r6.getInt(r4)     // Catch: org.json.JSONException -> L6a
            r2.<init>(r3, r6, r5)     // Catch: org.json.JSONException -> L6a
            return r2
        L6a:
            r6 = move-exception
            com.google.android.gms.cast.internal.Logger r2 = com.google.android.gms.cast.VideoInfo.zza
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = "Error while creating a VideoInfo instance from JSON"
            r2.d(r6, r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.VideoInfo.zzb(org.json.JSONObject):com.google.android.gms.cast.VideoInfo");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoInfo)) {
            return false;
        }
        VideoInfo videoInfo = (VideoInfo) obj;
        return this.zzc == videoInfo.getHeight() && this.zzb == videoInfo.getWidth() && this.zzd == videoInfo.getHdrType();
    }

    public int getHdrType() {
        return this.zzd;
    }

    public int getHeight() {
        return this.zzc;
    }

    public int getWidth() {
        return this.zzb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzc), Integer.valueOf(this.zzb), Integer.valueOf(this.zzd)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int width = getWidth();
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(width);
        int height = getHeight();
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(height);
        int hdrType = getHdrType();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(hdrType);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("width", this.zzb);
            jSONObject.put("height", this.zzc);
            int i10 = this.zzd;
            jSONObject.put("hdrType", i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? null : "hdr" : "dv" : "hdr10" : "sdr");
            return jSONObject;
        } catch (JSONException unused) {
            zza.e("Failed to transform VideoInfo into Json", new Object[0]);
            return new JSONObject();
        }
    }
}
