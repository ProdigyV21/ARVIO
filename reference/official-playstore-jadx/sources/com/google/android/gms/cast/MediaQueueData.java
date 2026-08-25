package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaQueueContainerMetadata;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.cast.internal.media.MediaCommon;
import com.google.android.gms.common.internal.t;
import io.ktor.http.ContentDisposition;
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
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class MediaQueueData extends a {
    public static final Parcelable.Creator<MediaQueueData> CREATOR = new zzbx();
    public static final int MEDIA_QUEUE_TYPE_ALBUM = 1;
    public static final int MEDIA_QUEUE_TYPE_AUDIO_BOOK = 3;
    public static final int MEDIA_QUEUE_TYPE_GENERIC = 0;
    public static final int MEDIA_QUEUE_TYPE_LIVE_TV = 8;
    public static final int MEDIA_QUEUE_TYPE_MOVIE = 9;
    public static final int MEDIA_QUEUE_TYPE_PLAYLIST = 2;
    public static final int MEDIA_QUEUE_TYPE_PODCAST_SERIES = 5;
    public static final int MEDIA_QUEUE_TYPE_RADIO_STATION = 4;
    public static final int MEDIA_QUEUE_TYPE_TV_SERIES = 6;
    public static final int MEDIA_QUEUE_TYPE_VIDEO_PLAYLIST = 7;
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private MediaQueueContainerMetadata zze;
    private int zzf;
    private List zzg;
    private int zzh;
    private long zzi;
    private boolean zzj;

    public static class Builder {
        private final MediaQueueData zza;

        public Builder() {
            this.zza = new MediaQueueData(null);
        }

        public MediaQueueData build() {
            return new MediaQueueData(this.zza, null);
        }

        public Builder setContainerMetadata(MediaQueueContainerMetadata mediaQueueContainerMetadata) {
            this.zza.zzg(mediaQueueContainerMetadata);
            return this;
        }

        public Builder setEntity(String str) {
            this.zza.zzd(str);
            return this;
        }

        public Builder setItems(List<MediaQueueItem> list) {
            this.zza.zzh(list);
            return this;
        }

        public Builder setName(String str) {
            this.zza.zzf(str);
            return this;
        }

        public Builder setQueueId(String str) {
            this.zza.zzc(str);
            return this;
        }

        public Builder setQueueType(int i10) {
            this.zza.zze(i10);
            return this;
        }

        public Builder setRepeatMode(int i10) {
            this.zza.setRepeatMode(i10);
            return this;
        }

        public Builder setStartIndex(int i10) {
            this.zza.zzi(i10);
            return this;
        }

        public Builder setStartTime(long j10) {
            this.zza.zzj(j10);
            return this;
        }

        public final Builder zza(JSONObject jSONObject) {
            this.zza.zzk(jSONObject);
            return this;
        }

        public Builder(MediaQueueData mediaQueueData) {
            this.zza = new MediaQueueData(mediaQueueData, null);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaQueueType {
    }

    private MediaQueueData() {
        throw null;
    }

    private final void zzl() {
        this.zza = null;
        this.zzb = null;
        this.zzc = 0;
        this.zzd = null;
        this.zzf = 0;
        this.zzg = null;
        this.zzh = 0;
        this.zzi = -1L;
        this.zzj = false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueData)) {
            return false;
        }
        MediaQueueData mediaQueueData = (MediaQueueData) obj;
        return TextUtils.equals(this.zza, mediaQueueData.zza) && TextUtils.equals(this.zzb, mediaQueueData.zzb) && this.zzc == mediaQueueData.zzc && TextUtils.equals(this.zzd, mediaQueueData.zzd) && t.l(this.zze, mediaQueueData.zze) && this.zzf == mediaQueueData.zzf && t.l(this.zzg, mediaQueueData.zzg) && this.zzh == mediaQueueData.zzh && this.zzi == mediaQueueData.zzi && this.zzj == mediaQueueData.zzj;
    }

    public MediaQueueContainerMetadata getContainerMetadata() {
        return this.zze;
    }

    public String getEntity() {
        return this.zzb;
    }

    public List<MediaQueueItem> getItems() {
        List list = this.zzg;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public String getName() {
        return this.zzd;
    }

    public String getQueueId() {
        return this.zza;
    }

    public int getQueueType() {
        return this.zzc;
    }

    public int getRepeatMode() {
        return this.zzf;
    }

    public int getStartIndex() {
        return this.zzh;
    }

    public long getStartTime() {
        return this.zzi;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb, Integer.valueOf(this.zzc), this.zzd, this.zze, Integer.valueOf(this.zzf), this.zzg, Integer.valueOf(this.zzh), Long.valueOf(this.zzi), Boolean.valueOf(this.zzj)});
    }

    public void setRepeatMode(int i10) {
        this.zzf = i10;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, getQueueId(), false);
        a.a.g0(parcel, 3, getEntity(), false);
        int queueType = getQueueType();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(queueType);
        a.a.g0(parcel, 5, getName(), false);
        a.a.f0(parcel, 6, getContainerMetadata(), i10, false);
        int repeatMode = getRepeatMode();
        a.a.l0(parcel, 7, 4);
        parcel.writeInt(repeatMode);
        a.a.k0(parcel, 8, getItems(), false);
        int startIndex = getStartIndex();
        a.a.l0(parcel, 9, 4);
        parcel.writeInt(startIndex);
        long startTime = getStartTime();
        a.a.l0(parcel, 10, 8);
        parcel.writeLong(startTime);
        boolean z = this.zzj;
        a.a.l0(parcel, 11, 4);
        parcel.writeInt(z ? 1 : 0);
        a.a.n0(iM0, parcel);
    }

    public final boolean zza() {
        return this.zzj;
    }

    public final JSONObject zzb() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.zza)) {
                jSONObject.put(TtmlNode.ATTR_ID, this.zza);
            }
            if (!TextUtils.isEmpty(this.zzb)) {
                jSONObject.put("entity", this.zzb);
            }
            switch (this.zzc) {
                case 1:
                    jSONObject.put("queueType", "ALBUM");
                    break;
                case 2:
                    jSONObject.put("queueType", "PLAYLIST");
                    break;
                case 3:
                    jSONObject.put("queueType", "AUDIOBOOK");
                    break;
                case 4:
                    jSONObject.put("queueType", "RADIO_STATION");
                    break;
                case 5:
                    jSONObject.put("queueType", "PODCAST_SERIES");
                    break;
                case 6:
                    jSONObject.put("queueType", "TV_SERIES");
                    break;
                case 7:
                    jSONObject.put("queueType", "VIDEO_PLAYLIST");
                    break;
                case 8:
                    jSONObject.put("queueType", "LIVE_TV");
                    break;
                case 9:
                    jSONObject.put("queueType", "MOVIE");
                    break;
            }
            if (!TextUtils.isEmpty(this.zzd)) {
                jSONObject.put(ContentDisposition.Parameters.Name, this.zzd);
            }
            MediaQueueContainerMetadata mediaQueueContainerMetadata = this.zze;
            if (mediaQueueContainerMetadata != null) {
                jSONObject.put("containerMetadata", mediaQueueContainerMetadata.zzb());
            }
            String strZza = MediaCommon.zza(Integer.valueOf(this.zzf));
            if (strZza != null) {
                jSONObject.put("repeatMode", strZza);
            }
            List list = this.zzg;
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = this.zzg.iterator();
                while (it.hasNext()) {
                    jSONArray.put(((MediaQueueItem) it.next()).toJson());
                }
                jSONObject.put("items", jSONArray);
            }
            jSONObject.put("startIndex", this.zzh);
            long j10 = this.zzi;
            if (j10 != -1) {
                jSONObject.put("startTime", CastUtils.millisecToSec(j10));
            }
            jSONObject.put("shuffle", this.zzj);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final /* synthetic */ void zzc(String str) {
        this.zza = str;
    }

    public final /* synthetic */ void zzd(String str) {
        this.zzb = str;
    }

    public final /* synthetic */ void zze(int i10) {
        this.zzc = i10;
    }

    public final /* synthetic */ void zzf(String str) {
        this.zzd = str;
    }

    public final /* synthetic */ void zzg(MediaQueueContainerMetadata mediaQueueContainerMetadata) {
        this.zze = mediaQueueContainerMetadata;
    }

    public final /* synthetic */ void zzh(List list) {
        this.zzg = list == null ? null : new ArrayList(list);
    }

    public final /* synthetic */ void zzi(int i10) {
        this.zzh = i10;
    }

    public final /* synthetic */ void zzj(long j10) {
        this.zzi = j10;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final /* synthetic */ void zzk(JSONObject jSONObject) {
        int i10;
        zzl();
        if (jSONObject == null) {
            return;
        }
        this.zza = CastUtils.optStringOrNull(jSONObject, TtmlNode.ATTR_ID);
        this.zzb = CastUtils.optStringOrNull(jSONObject, "entity");
        String strOptString = jSONObject.optString("queueType");
        switch (strOptString.hashCode()) {
            case -1803151310:
                if (strOptString.equals("PODCAST_SERIES")) {
                    i10 = 5;
                    this.zzc = i10;
                }
                break;
            case -1758903120:
                if (strOptString.equals("RADIO_STATION")) {
                    i10 = 4;
                    this.zzc = i10;
                }
                break;
            case -1632865838:
                if (strOptString.equals("PLAYLIST")) {
                    i10 = 2;
                    this.zzc = i10;
                }
                break;
            case -1319760993:
                if (strOptString.equals("AUDIOBOOK")) {
                    i10 = 3;
                    this.zzc = i10;
                }
                break;
            case -1088524588:
                if (strOptString.equals("TV_SERIES")) {
                    i10 = 6;
                    this.zzc = i10;
                }
                break;
            case 62359119:
                if (strOptString.equals("ALBUM")) {
                    i10 = 1;
                    this.zzc = i10;
                }
                break;
            case 73549584:
                if (strOptString.equals("MOVIE")) {
                    i10 = 9;
                    this.zzc = i10;
                }
                break;
            case 393100598:
                if (strOptString.equals("VIDEO_PLAYLIST")) {
                    i10 = 7;
                    this.zzc = i10;
                }
                break;
            case 902303413:
                if (strOptString.equals("LIVE_TV")) {
                    i10 = 8;
                    this.zzc = i10;
                }
                break;
        }
        this.zzd = CastUtils.optStringOrNull(jSONObject, ContentDisposition.Parameters.Name);
        JSONObject jSONObjectOptJSONObject = jSONObject.has("containerMetadata") ? jSONObject.optJSONObject("containerMetadata") : null;
        if (jSONObjectOptJSONObject != null) {
            MediaQueueContainerMetadata.Builder builder = new MediaQueueContainerMetadata.Builder();
            builder.zza(jSONObjectOptJSONObject);
            this.zze = builder.build();
        }
        Integer numMediaRepeatModeFromString = MediaCommon.mediaRepeatModeFromString(jSONObject.optString("repeatMode"));
        if (numMediaRepeatModeFromString != null) {
            this.zzf = numMediaRepeatModeFromString.intValue();
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("items");
        if (jSONArrayOptJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            this.zzg = arrayList;
            for (int i11 = 0; i11 < jSONArrayOptJSONArray.length(); i11++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i11);
                if (jSONObjectOptJSONObject2 != null) {
                    try {
                        arrayList.add(new MediaQueueItem(jSONObjectOptJSONObject2));
                    } catch (JSONException unused) {
                    }
                }
            }
        }
        this.zzh = jSONObject.optInt("startIndex", this.zzh);
        if (jSONObject.has("startTime")) {
            this.zzi = CastUtils.secToMillisec(jSONObject.optDouble("startTime", this.zzi));
        }
        this.zzj = jSONObject.optBoolean("shuffle");
    }

    public MediaQueueData(MediaQueueData mediaQueueData, byte[] bArr) {
        this.zza = mediaQueueData.zza;
        this.zzb = mediaQueueData.zzb;
        this.zzc = mediaQueueData.zzc;
        this.zzd = mediaQueueData.zzd;
        this.zze = mediaQueueData.zze;
        this.zzf = mediaQueueData.zzf;
        this.zzg = mediaQueueData.zzg;
        this.zzh = mediaQueueData.zzh;
        this.zzi = mediaQueueData.zzi;
        this.zzj = mediaQueueData.zzj;
    }

    public MediaQueueData(String str, String str2, int i10, String str3, MediaQueueContainerMetadata mediaQueueContainerMetadata, int i11, List list, int i12, long j10, boolean z) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = i10;
        this.zzd = str3;
        this.zze = mediaQueueContainerMetadata;
        this.zzf = i11;
        this.zzg = list;
        this.zzh = i12;
        this.zzi = j10;
        this.zzj = z;
    }

    public MediaQueueData(byte[] bArr) {
        zzl();
    }
}
