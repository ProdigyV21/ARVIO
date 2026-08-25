package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.compose.foundation.c;
import c4.e;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.cast.g4;
import com.google.android.gms.internal.cast.h4;
import com.google.android.gms.internal.cast.j4;
import com.google.android.gms.internal.cast.u0;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class MediaTrack extends a implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaTrack> CREATOR = new zzca();
    public static final String ROLE_ALTERNATE = "alternate";
    public static final String ROLE_CAPTION = "caption";
    public static final String ROLE_COMMENTARY = "commentary";
    public static final String ROLE_DESCRIPTION = "description";
    public static final String ROLE_DUB = "dub";
    public static final String ROLE_EMERGENCY = "emergency";
    public static final String ROLE_FORCED_SUBTITLE = "forced_subtitle";
    public static final String ROLE_MAIN = "main";
    public static final String ROLE_SIGN = "sign";
    public static final String ROLE_SUBTITLE = "subtitle";
    public static final String ROLE_SUPPLEMENTARY = "supplementary";
    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;
    String zza;
    private final long zzb;
    private final int zzc;
    private String zzd;
    private String zze;
    private final String zzf;
    private final String zzg;
    private final int zzh;
    private final List zzi;
    private final JSONObject zzj;

    public static class Builder {
        private final long zza;
        private final int zzb;
        private String zzc;
        private String zzd;
        private String zze;
        private String zzf;
        private int zzg = 0;
        private List zzh;
        private JSONObject zzi;

        public Builder(long j10, int i10) throws IllegalArgumentException {
            this.zza = j10;
            this.zzb = i10;
        }

        public MediaTrack build() {
            return new MediaTrack(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi);
        }

        public Builder setContentId(String str) {
            this.zzc = str;
            return this;
        }

        public Builder setContentType(String str) {
            this.zzd = str;
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzi = jSONObject;
            return this;
        }

        public Builder setLanguage(String str) {
            this.zzf = str;
            return this;
        }

        public Builder setName(String str) {
            this.zze = str;
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Builder setRoles(List<String> list) {
            List listH = list;
            if (list != 0) {
                h4 h4Var = j4.f13340l;
                if (list instanceof g4) {
                    j4 j4VarD = ((g4) list).d();
                    boolean zE = j4VarD.e();
                    listH = j4VarD;
                    if (zE) {
                        Object[] array = j4VarD.toArray(g4.f13259i);
                        listH = j4.h(array.length, array);
                    }
                } else {
                    Object[] array2 = list.toArray();
                    int length = array2.length;
                    for (int i10 = 0; i10 < length; i10++) {
                        u0.s(i10, array2[i10]);
                    }
                    listH = j4.h(length, array2);
                }
            }
            this.zzh = listH;
            return this;
        }

        public Builder setSubtype(int i10) throws IllegalArgumentException {
            if (i10 < -1 || i10 > 5) {
                throw new IllegalArgumentException(c.p(i10, "invalid subtype ", new StringBuilder(String.valueOf(i10).length() + 16)));
            }
            if (i10 != 0 && this.zzb != 1) {
                throw new IllegalArgumentException("subtypes are only valid for text tracks");
            }
            this.zzg = i10;
            return this;
        }

        public Builder setLanguage(Locale locale) {
            this.zzf = CastUtils.zzd(locale);
            return this;
        }
    }

    public MediaTrack(long j10, int i10, String str, String str2, String str3, String str4, int i11, List list, JSONObject jSONObject) {
        this.zzb = j10;
        this.zzc = i10;
        this.zzd = str;
        this.zze = str2;
        this.zzf = str3;
        this.zzg = str4;
        this.zzh = i11;
        this.zzi = list;
        this.zzj = jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaTrack)) {
            return false;
        }
        MediaTrack mediaTrack = (MediaTrack) obj;
        JSONObject jSONObject = this.zzj;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaTrack.zzj;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || e.a(jSONObject, jSONObject2)) && this.zzb == mediaTrack.zzb && this.zzc == mediaTrack.zzc && CastUtils.zza(this.zzd, mediaTrack.zzd) && CastUtils.zza(this.zze, mediaTrack.zze) && CastUtils.zza(this.zzf, mediaTrack.zzf) && CastUtils.zza(this.zzg, mediaTrack.zzg) && this.zzh == mediaTrack.zzh && CastUtils.zza(this.zzi, mediaTrack.zzi);
    }

    public String getContentId() {
        return this.zzd;
    }

    public String getContentType() {
        return this.zze;
    }

    public JSONObject getCustomData() {
        return this.zzj;
    }

    public long getId() {
        return this.zzb;
    }

    public String getLanguage() {
        return this.zzg;
    }

    public Locale getLanguageLocale() {
        String str = this.zzg;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Locale.forLanguageTag(str);
    }

    public String getName() {
        return this.zzf;
    }

    public List<String> getRoles() {
        return this.zzi;
    }

    public int getSubtype() {
        return this.zzh;
    }

    public int getType() {
        return this.zzc;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.zzb), Integer.valueOf(this.zzc), this.zzd, this.zze, this.zzf, this.zzg, Integer.valueOf(this.zzh), this.zzi, String.valueOf(this.zzj)});
    }

    public void setContentId(String str) {
        this.zzd = str;
    }

    public void setContentType(String str) {
        this.zze = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        JSONObject jSONObject = this.zzj;
        this.zza = jSONObject == null ? null : jSONObject.toString();
        int iM0 = a.a.m0(20293, parcel);
        long id = getId();
        a.a.l0(parcel, 2, 8);
        parcel.writeLong(id);
        int type = getType();
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(type);
        a.a.g0(parcel, 4, getContentId(), false);
        a.a.g0(parcel, 5, getContentType(), false);
        a.a.g0(parcel, 6, getName(), false);
        a.a.g0(parcel, 7, getLanguage(), false);
        int subtype = getSubtype();
        a.a.l0(parcel, 8, 4);
        parcel.writeInt(subtype);
        a.a.i0(parcel, getRoles(), 9);
        a.a.g0(parcel, 10, this.zza, false);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackId", this.zzb);
            int i10 = this.zzc;
            if (i10 == 1) {
                jSONObject.put(LinkHeader.Parameters.Type, "TEXT");
            } else if (i10 == 2) {
                jSONObject.put(LinkHeader.Parameters.Type, "AUDIO");
            } else if (i10 == 3) {
                jSONObject.put(LinkHeader.Parameters.Type, "VIDEO");
            }
            String str = this.zzd;
            if (str != null) {
                jSONObject.put("trackContentId", str);
            }
            String str2 = this.zze;
            if (str2 != null) {
                jSONObject.put("trackContentType", str2);
            }
            String str3 = this.zzf;
            if (str3 != null) {
                jSONObject.put(ContentDisposition.Parameters.Name, str3);
            }
            String str4 = this.zzg;
            if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("language", str4);
            }
            int i11 = this.zzh;
            if (i11 == 1) {
                jSONObject.put("subtype", "SUBTITLES");
            } else if (i11 == 2) {
                jSONObject.put("subtype", "CAPTIONS");
            } else if (i11 == 3) {
                jSONObject.put("subtype", "DESCRIPTIONS");
            } else if (i11 == 4) {
                jSONObject.put("subtype", "CHAPTERS");
            } else if (i11 == 5) {
                jSONObject.put("subtype", "METADATA");
            }
            List list = this.zzi;
            if (list != null) {
                jSONObject.put("roles", new JSONArray((Collection) list));
            }
            JSONObject jSONObject2 = this.zzj;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
