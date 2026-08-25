package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.t;
import io.ktor.http.LinkHeader;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import r3.b;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class MediaQueueContainerMetadata extends a {
    public static final Parcelable.Creator<MediaQueueContainerMetadata> CREATOR = new zzbw();
    public static final int MEDIA_QUEUE_CONTAINER_TYPE_AUDIO_BOOK = 1;
    public static final int MEDIA_QUEUE_CONTAINER_TYPE_GENERIC = 0;
    private int zza;
    private String zzb;
    private List zzc;
    private List zzd;
    private double zze;

    public static class Builder {
        private final MediaQueueContainerMetadata zza = new MediaQueueContainerMetadata(null);

        public MediaQueueContainerMetadata build() {
            return new MediaQueueContainerMetadata(this.zza, null);
        }

        public Builder setContainerDuration(double d4) {
            this.zza.zzf(d4);
            return this;
        }

        public Builder setContainerImages(List<b> list) {
            this.zza.zze(list);
            return this;
        }

        public Builder setContainerType(int i10) {
            this.zza.zzc(i10);
            return this;
        }

        public Builder setSections(List<MediaMetadata> list) {
            this.zza.zza(list);
            return this;
        }

        public Builder setTitle(String str) {
            this.zza.zzd(str);
            return this;
        }

        public final Builder zza(JSONObject jSONObject) {
            this.zza.zzg(jSONObject);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaQueueContainerType {
    }

    private MediaQueueContainerMetadata() {
        throw null;
    }

    private final void zzh() {
        this.zza = 0;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = 0.0d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueContainerMetadata)) {
            return false;
        }
        MediaQueueContainerMetadata mediaQueueContainerMetadata = (MediaQueueContainerMetadata) obj;
        return this.zza == mediaQueueContainerMetadata.zza && TextUtils.equals(this.zzb, mediaQueueContainerMetadata.zzb) && t.l(this.zzc, mediaQueueContainerMetadata.zzc) && t.l(this.zzd, mediaQueueContainerMetadata.zzd) && this.zze == mediaQueueContainerMetadata.zze;
    }

    public double getContainerDuration() {
        return this.zze;
    }

    public List<b> getContainerImages() {
        List list = this.zzd;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public int getContainerType() {
        return this.zza;
    }

    public List<MediaMetadata> getSections() {
        List list = this.zzc;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public String getTitle() {
        return this.zzb;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zza), this.zzb, this.zzc, this.zzd, Double.valueOf(this.zze)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        int containerType = getContainerType();
        a.a.l0(parcel, 2, 4);
        parcel.writeInt(containerType);
        a.a.g0(parcel, 3, getTitle(), false);
        a.a.k0(parcel, 4, getSections(), false);
        a.a.k0(parcel, 5, getContainerImages(), false);
        double containerDuration = getContainerDuration();
        a.a.l0(parcel, 6, 8);
        parcel.writeDouble(containerDuration);
        a.a.n0(iM0, parcel);
    }

    public final void zza(List list) {
        this.zzc = list == null ? null : new ArrayList(list);
    }

    public final JSONObject zzb() {
        JSONObject jSONObject = new JSONObject();
        try {
            int i10 = this.zza;
            if (i10 == 0) {
                jSONObject.put("containerType", "GENERIC_CONTAINER");
            } else if (i10 == 1) {
                jSONObject.put("containerType", "AUDIOBOOK_CONTAINER");
            }
            if (!TextUtils.isEmpty(this.zzb)) {
                jSONObject.put(LinkHeader.Parameters.Title, this.zzb);
            }
            List list = this.zzc;
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = this.zzc.iterator();
                while (it.hasNext()) {
                    jSONArray.put(((MediaMetadata) it.next()).zza());
                }
                jSONObject.put("sections", jSONArray);
            }
            List list2 = this.zzd;
            if (list2 != null && !list2.isEmpty()) {
                jSONObject.put("containerImages", com.google.android.gms.cast.internal.media.zza.zzb(this.zzd));
            }
            jSONObject.put("containerDuration", this.zze);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final /* synthetic */ void zzc(int i10) {
        this.zza = i10;
    }

    public final /* synthetic */ void zzd(String str) {
        this.zzb = str;
    }

    public final /* synthetic */ void zze(List list) {
        this.zzd = list == null ? null : new ArrayList(list);
    }

    public final /* synthetic */ void zzf(double d4) {
        this.zze = d4;
    }

    public final /* synthetic */ void zzg(JSONObject jSONObject) {
        zzh();
        String strOptString = jSONObject.optString("containerType", "");
        int iHashCode = strOptString.hashCode();
        if (iHashCode != 6924225) {
            if (iHashCode == 828666841 && strOptString.equals("GENERIC_CONTAINER")) {
                this.zza = 0;
            }
        } else if (strOptString.equals("AUDIOBOOK_CONTAINER")) {
            this.zza = 1;
        }
        this.zzb = CastUtils.optStringOrNull(jSONObject, LinkHeader.Parameters.Title);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("sections");
        if (jSONArrayOptJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            this.zzc = arrayList;
            for (int i10 = 0; i10 < jSONArrayOptJSONArray.length(); i10++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i10);
                if (jSONObjectOptJSONObject != null) {
                    MediaMetadata mediaMetadata = new MediaMetadata();
                    mediaMetadata.zzb(jSONObjectOptJSONObject);
                    arrayList.add(mediaMetadata);
                }
            }
        }
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("containerImages");
        if (jSONArrayOptJSONArray2 != null) {
            ArrayList arrayList2 = new ArrayList();
            this.zzd = arrayList2;
            com.google.android.gms.cast.internal.media.zza.zza(arrayList2, jSONArrayOptJSONArray2);
        }
        this.zze = jSONObject.optDouble("containerDuration", this.zze);
    }

    public MediaQueueContainerMetadata(int i10, String str, List list, List list2, double d4) {
        this.zza = i10;
        this.zzb = str;
        this.zzc = list;
        this.zzd = list2;
        this.zze = d4;
    }

    public MediaQueueContainerMetadata(MediaQueueContainerMetadata mediaQueueContainerMetadata, byte[] bArr) {
        this.zza = mediaQueueContainerMetadata.zza;
        this.zzb = mediaQueueContainerMetadata.zzb;
        this.zzc = mediaQueueContainerMetadata.zzc;
        this.zzd = mediaQueueContainerMetadata.zzd;
        this.zze = mediaQueueContainerMetadata.zze;
    }

    public MediaQueueContainerMetadata(byte[] bArr) {
        zzh();
    }
}
