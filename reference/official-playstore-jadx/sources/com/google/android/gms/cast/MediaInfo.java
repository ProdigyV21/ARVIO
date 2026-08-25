package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.extractor.text.ttml.TtmlNode;
import c4.e;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.cast.h4;
import com.google.android.gms.internal.cast.j4;
import com.google.android.gms.internal.cast.p4;
import com.google.android.gms.internal.cast.u0;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import j$.util.Objects;
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
public class MediaInfo extends a implements ReflectedParcelable {
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    public static final long UNKNOWN_DURATION = -1;
    public static final long UNKNOWN_START_ABSOLUTE_TIME = -1;
    String zzb;
    private String zzc;
    private int zzd;
    private String zze;
    private MediaMetadata zzf;
    private long zzg;
    private List zzh;
    private TextTrackStyle zzi;
    private List zzj;
    private List zzk;
    private String zzl;
    private VastAdsRequest zzm;
    private long zzn;
    private String zzo;
    private String zzp;
    private String zzq;
    private String zzr;
    private JSONObject zzs;
    private final Writer zzt;
    public static final long zza = CastUtils.secToMillisec(-1L);
    public static final Parcelable.Creator<MediaInfo> CREATOR = new zzbr();

    public static class Builder {
        private String zza;
        private String zzc;
        private MediaMetadata zzd;
        private List zzf;
        private TextTrackStyle zzg;
        private String zzh;
        private List zzi;
        private List zzj;
        private String zzk;
        private VastAdsRequest zzl;
        private String zzm;
        private String zzn;
        private String zzo;
        private String zzp;
        private int zzb = -1;
        private long zze = -1;

        public Builder() {
        }

        public MediaInfo build() {
            return new MediaInfo(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, -1L, this.zzm, this.zzn, this.zzo, this.zzp);
        }

        public Builder setAdBreakClips(List<AdBreakClipInfo> list) {
            this.zzj = list;
            return this;
        }

        public Builder setAdBreaks(List<AdBreakInfo> list) {
            this.zzi = list;
            return this;
        }

        public Builder setAtvEntity(String str) {
            this.zzm = str;
            return this;
        }

        public Builder setContentType(String str) {
            this.zzc = str;
            return this;
        }

        public Builder setContentUrl(String str) {
            this.zzn = str;
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzh = jSONObject == null ? null : jSONObject.toString();
            return this;
        }

        public Builder setEntity(String str) {
            this.zzk = str;
            return this;
        }

        public Builder setHlsSegmentFormat(String str) {
            this.zzo = str;
            return this;
        }

        public Builder setHlsVideoSegmentFormat(String str) {
            this.zzp = str;
            return this;
        }

        public Builder setMediaTracks(List<MediaTrack> list) {
            this.zzf = list;
            return this;
        }

        public Builder setMetadata(MediaMetadata mediaMetadata) {
            this.zzd = mediaMetadata;
            return this;
        }

        public Builder setStreamDuration(long j10) {
            if (j10 < 0 && j10 != -1) {
                throw new IllegalArgumentException("Invalid stream duration");
            }
            this.zze = j10;
            return this;
        }

        public Builder setStreamType(int i10) {
            if (i10 < -1 || i10 > 2) {
                throw new IllegalArgumentException("invalid stream type");
            }
            this.zzb = i10;
            return this;
        }

        public Builder setTextTrackStyle(TextTrackStyle textTrackStyle) {
            this.zzg = textTrackStyle;
            return this;
        }

        public Builder setVmapAdsRequest(VastAdsRequest vastAdsRequest) {
            this.zzl = vastAdsRequest;
            return this;
        }

        public Builder(String str) {
            this.zza = str;
        }

        public Builder(String str, String str2) {
            this.zza = str;
            this.zzk = str2;
        }
    }

    public class Writer {
        final /* synthetic */ MediaInfo zza;

        public Writer(MediaInfo mediaInfo) {
            Objects.requireNonNull(mediaInfo);
            this.zza = mediaInfo;
        }

        public void setAdBreakClips(List<AdBreakClipInfo> list) {
            this.zza.zzk(list);
        }

        public void setAdBreaks(List<AdBreakInfo> list) {
            this.zza.zzj(list);
        }

        public void setContentId(String str) {
            this.zza.zzc(str);
        }

        public void setContentType(String str) {
            this.zza.zze(str);
        }

        public void setContentUrl(String str) {
            this.zza.zzo(str);
        }

        public void setCustomData(JSONObject jSONObject) {
            this.zza.zzr(jSONObject);
        }

        public void setEntity(String str) {
            this.zza.zzl(str);
        }

        public void setHlsSegmentFormat(String str) {
            this.zza.zzp(str);
        }

        public void setHlsVideoSegmentFormat(String str) {
            this.zza.zzq(str);
        }

        public void setMediaTracks(List<MediaTrack> list) {
            this.zza.zzh(list);
        }

        public void setMetadata(MediaMetadata mediaMetadata) {
            this.zza.zzf(mediaMetadata);
        }

        public void setStartAbsoluteTime(long j10) {
            if (j10 < 0 && j10 != -1) {
                throw new IllegalArgumentException("Invalid start absolute time");
            }
            this.zza.zzn(j10);
        }

        public void setStreamDuration(long j10) {
            if (j10 < 0 && j10 != -1) {
                throw new IllegalArgumentException("Invalid stream duration");
            }
            this.zza.zzg(j10);
        }

        public void setStreamType(int i10) {
            if (i10 < -1 || i10 > 2) {
                throw new IllegalArgumentException("invalid stream type");
            }
            this.zza.zzd(i10);
        }

        public void setTextTrackStyle(TextTrackStyle textTrackStyle) {
            this.zza.zzi(textTrackStyle);
        }

        public void setVmapAdsRequest(VastAdsRequest vastAdsRequest) {
            this.zza.zzm(vastAdsRequest);
        }
    }

    public MediaInfo(String str, int i10, String str2, MediaMetadata mediaMetadata, long j10, List list, TextTrackStyle textTrackStyle, String str3, List list2, List list3, String str4, VastAdsRequest vastAdsRequest, long j11, String str5, String str6, String str7, String str8) {
        this.zzt = new Writer(this);
        this.zzc = str;
        this.zzd = i10;
        this.zze = str2;
        this.zzf = mediaMetadata;
        this.zzg = j10;
        this.zzh = list;
        this.zzi = textTrackStyle;
        this.zzb = str3;
        if (str3 != null) {
            try {
                this.zzs = new JSONObject(this.zzb);
            } catch (JSONException unused) {
                this.zzs = null;
                this.zzb = null;
            }
        } else {
            this.zzs = null;
        }
        this.zzj = list2;
        this.zzk = list3;
        this.zzl = str4;
        this.zzm = vastAdsRequest;
        this.zzn = j11;
        this.zzo = str5;
        this.zzp = str6;
        this.zzq = str7;
        this.zzr = str8;
        if (this.zzc == null && str6 == null && str4 == null) {
            throw new IllegalArgumentException("Either contentID or contentUrl or entity should be set");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) obj;
        JSONObject jSONObject = this.zzs;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaInfo.zzs;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || e.a(jSONObject, jSONObject2)) && CastUtils.zza(this.zzc, mediaInfo.zzc) && this.zzd == mediaInfo.zzd && CastUtils.zza(this.zze, mediaInfo.zze) && CastUtils.zza(this.zzf, mediaInfo.zzf) && this.zzg == mediaInfo.zzg && CastUtils.zza(this.zzh, mediaInfo.zzh) && CastUtils.zza(this.zzi, mediaInfo.zzi) && CastUtils.zza(this.zzj, mediaInfo.zzj) && CastUtils.zza(this.zzk, mediaInfo.zzk) && CastUtils.zza(this.zzl, mediaInfo.zzl) && CastUtils.zza(this.zzm, mediaInfo.zzm) && this.zzn == mediaInfo.zzn && CastUtils.zza(this.zzo, mediaInfo.zzo) && CastUtils.zza(this.zzp, mediaInfo.zzp) && CastUtils.zza(this.zzq, mediaInfo.zzq) && CastUtils.zza(this.zzr, mediaInfo.zzr);
    }

    public List<AdBreakClipInfo> getAdBreakClips() {
        List list = this.zzk;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public List<AdBreakInfo> getAdBreaks() {
        List list = this.zzj;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public String getContentId() {
        String str = this.zzc;
        return str == null ? "" : str;
    }

    public String getContentType() {
        return this.zze;
    }

    public String getContentUrl() {
        return this.zzp;
    }

    public JSONObject getCustomData() {
        return this.zzs;
    }

    public String getEntity() {
        return this.zzl;
    }

    public String getHlsSegmentFormat() {
        return this.zzq;
    }

    public String getHlsVideoSegmentFormat() {
        return this.zzr;
    }

    public List<MediaTrack> getMediaTracks() {
        return this.zzh;
    }

    public MediaMetadata getMetadata() {
        return this.zzf;
    }

    public long getStartAbsoluteTime() {
        return this.zzn;
    }

    public long getStreamDuration() {
        return this.zzg;
    }

    public int getStreamType() {
        return this.zzd;
    }

    public TextTrackStyle getTextTrackStyle() {
        return this.zzi;
    }

    public VastAdsRequest getVmapAdsRequest() {
        return this.zzm;
    }

    public Writer getWriter() {
        return this.zzt;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzc, Integer.valueOf(this.zzd), this.zze, this.zzf, Long.valueOf(this.zzg), String.valueOf(this.zzs), this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, Long.valueOf(this.zzn), this.zzo, this.zzq, this.zzr});
    }

    public void setTextTrackStyle(TextTrackStyle textTrackStyle) {
        this.zzi = textTrackStyle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        JSONObject jSONObject = this.zzs;
        this.zzb = jSONObject == null ? null : jSONObject.toString();
        int iM0 = a.a.m0(20293, parcel);
        a.a.g0(parcel, 2, getContentId(), false);
        int streamType = getStreamType();
        a.a.l0(parcel, 3, 4);
        parcel.writeInt(streamType);
        a.a.g0(parcel, 4, getContentType(), false);
        a.a.f0(parcel, 5, getMetadata(), i10, false);
        long streamDuration = getStreamDuration();
        a.a.l0(parcel, 6, 8);
        parcel.writeLong(streamDuration);
        a.a.k0(parcel, 7, getMediaTracks(), false);
        a.a.f0(parcel, 8, getTextTrackStyle(), i10, false);
        a.a.g0(parcel, 9, this.zzb, false);
        a.a.k0(parcel, 10, getAdBreaks(), false);
        a.a.k0(parcel, 11, getAdBreakClips(), false);
        a.a.g0(parcel, 12, getEntity(), false);
        a.a.f0(parcel, 13, getVmapAdsRequest(), i10, false);
        long startAbsoluteTime = getStartAbsoluteTime();
        a.a.l0(parcel, 14, 8);
        parcel.writeLong(startAbsoluteTime);
        a.a.g0(parcel, 15, this.zzo, false);
        a.a.g0(parcel, 16, getContentUrl(), false);
        a.a.g0(parcel, 17, getHlsSegmentFormat(), false);
        a.a.g0(parcel, 18, getHlsVideoSegmentFormat(), false);
        a.a.n0(iM0, parcel);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00ae A[LOOP:0: B:5:0x0022->B:27:0x00ae, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0196 A[LOOP:2: B:33:0x00d6->B:62:0x0196, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x019d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zza(org.json.JSONObject r26) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 424
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.MediaInfo.zza(org.json.JSONObject):void");
    }

    public final JSONObject zzb() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentId", this.zzc);
            jSONObject.putOpt("contentUrl", this.zzp);
            int i10 = this.zzd;
            jSONObject.put("streamType", i10 != 1 ? i10 != 2 ? "NONE" : "LIVE" : "BUFFERED");
            String str = this.zze;
            if (str != null) {
                jSONObject.put("contentType", str);
            }
            MediaMetadata mediaMetadata = this.zzf;
            if (mediaMetadata != null) {
                jSONObject.put(TtmlNode.TAG_METADATA, mediaMetadata.zza());
            }
            long j10 = this.zzg;
            if (j10 <= -1) {
                jSONObject.put("duration", JSONObject.NULL);
            } else {
                jSONObject.put("duration", CastUtils.millisecToSec(j10));
            }
            if (this.zzh != null) {
                JSONArray jSONArray = new JSONArray();
                Iterator it = this.zzh.iterator();
                while (it.hasNext()) {
                    jSONArray.put(((MediaTrack) it.next()).zza());
                }
                jSONObject.put("tracks", jSONArray);
            }
            TextTrackStyle textTrackStyle = this.zzi;
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.zza());
            }
            JSONObject jSONObject2 = this.zzs;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
            String str2 = this.zzl;
            if (str2 != null) {
                jSONObject.put("entity", str2);
            }
            if (this.zzj != null) {
                JSONArray jSONArray2 = new JSONArray();
                Iterator it2 = this.zzj.iterator();
                while (it2.hasNext()) {
                    jSONArray2.put(((AdBreakInfo) it2.next()).zza());
                }
                jSONObject.put("breaks", jSONArray2);
            }
            if (this.zzk != null) {
                JSONArray jSONArray3 = new JSONArray();
                Iterator it3 = this.zzk.iterator();
                while (it3.hasNext()) {
                    jSONArray3.put(((AdBreakClipInfo) it3.next()).zza());
                }
                jSONObject.put("breakClips", jSONArray3);
            }
            VastAdsRequest vastAdsRequest = this.zzm;
            if (vastAdsRequest != null) {
                jSONObject.put("vmapAdsRequest", vastAdsRequest.zza());
            }
            long j11 = this.zzn;
            if (j11 != -1) {
                jSONObject.put("startAbsoluteTime", CastUtils.millisecToSec(j11));
            }
            jSONObject.putOpt("atvEntity", this.zzo);
            String str3 = this.zzq;
            if (str3 != null) {
                jSONObject.put("hlsSegmentFormat", str3);
            }
            String str4 = this.zzr;
            if (str4 != null) {
                jSONObject.put("hlsVideoSegmentFormat", str4);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final /* synthetic */ void zzc(String str) {
        this.zzc = str;
    }

    public final /* synthetic */ void zzd(int i10) {
        this.zzd = i10;
    }

    public final /* synthetic */ void zze(String str) {
        this.zze = str;
    }

    public final /* synthetic */ void zzf(MediaMetadata mediaMetadata) {
        this.zzf = mediaMetadata;
    }

    public final /* synthetic */ void zzg(long j10) {
        this.zzg = j10;
    }

    public final /* synthetic */ void zzh(List list) {
        this.zzh = list;
    }

    public final /* synthetic */ void zzi(TextTrackStyle textTrackStyle) {
        this.zzi = textTrackStyle;
    }

    public final /* synthetic */ void zzj(List list) {
        this.zzj = list;
    }

    public final /* synthetic */ void zzk(List list) {
        this.zzk = list;
    }

    public final /* synthetic */ void zzl(String str) {
        this.zzl = str;
    }

    public final /* synthetic */ void zzm(VastAdsRequest vastAdsRequest) {
        this.zzm = vastAdsRequest;
    }

    public final /* synthetic */ void zzn(long j10) {
        this.zzn = j10;
    }

    public final /* synthetic */ void zzo(String str) {
        this.zzp = str;
    }

    public final /* synthetic */ void zzp(String str) {
        this.zzq = str;
    }

    public final /* synthetic */ void zzq(String str) {
        this.zzr = str;
    }

    public final /* synthetic */ void zzr(JSONObject jSONObject) {
        this.zzs = jSONObject;
    }

    public MediaInfo(JSONObject jSONObject) throws JSONException {
        double d4;
        int i10;
        int i11;
        double d10;
        p4 p4VarH;
        this(jSONObject.optString("contentId"), -1, null, null, -1L, null, null, null, null, null, null, null, -1L, null, null, null, null);
        String strOptString = jSONObject.optString("streamType", "NONE");
        int i12 = 2;
        int i13 = 1;
        int i14 = 0;
        if ("NONE".equals(strOptString)) {
            this.zzd = 0;
        } else if ("BUFFERED".equals(strOptString)) {
            this.zzd = 1;
        } else if ("LIVE".equals(strOptString)) {
            this.zzd = 2;
        } else {
            this.zzd = -1;
        }
        this.zze = CastUtils.optStringOrNull(jSONObject, "contentType");
        if (jSONObject.has(TtmlNode.TAG_METADATA)) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(TtmlNode.TAG_METADATA);
            MediaMetadata mediaMetadata = new MediaMetadata(jSONObject2.getInt("metadataType"));
            this.zzf = mediaMetadata;
            mediaMetadata.zzb(jSONObject2);
        }
        this.zzg = -1L;
        double d11 = 0.0d;
        if (this.zzd != 2 && jSONObject.has("duration") && !jSONObject.isNull("duration")) {
            double dOptDouble = jSONObject.optDouble("duration", 0.0d);
            if (!Double.isNaN(dOptDouble) && !Double.isInfinite(dOptDouble) && dOptDouble >= 0.0d) {
                this.zzg = CastUtils.secToMillisec(dOptDouble);
            }
        }
        if (jSONObject.has("tracks")) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("tracks");
            int i15 = 0;
            while (i15 < jSONArray.length()) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i15);
                String str = MediaTrack.ROLE_ALTERNATE;
                long j10 = jSONObject3.getLong("trackId");
                String strOptString2 = jSONObject3.optString(LinkHeader.Parameters.Type);
                int i16 = 3;
                if ("TEXT".equals(strOptString2)) {
                    i10 = 3;
                    i16 = i13;
                } else if ("AUDIO".equals(strOptString2)) {
                    i10 = 3;
                    i16 = i12;
                } else if ("VIDEO".equals(strOptString2)) {
                    i10 = 3;
                } else {
                    i10 = 3;
                    i16 = i14;
                }
                String strOptStringOrNull = CastUtils.optStringOrNull(jSONObject3, "trackContentId");
                String strOptStringOrNull2 = CastUtils.optStringOrNull(jSONObject3, "trackContentType");
                String strOptStringOrNull3 = CastUtils.optStringOrNull(jSONObject3, ContentDisposition.Parameters.Name);
                String strOptStringOrNull4 = CastUtils.optStringOrNull(jSONObject3, "language");
                if (jSONObject3.has("subtype")) {
                    String string = jSONObject3.getString("subtype");
                    if ("SUBTITLES".equals(string)) {
                        i11 = i13;
                    } else {
                        i11 = "CAPTIONS".equals(string) ? 2 : "DESCRIPTIONS".equals(string) ? i10 : "CHAPTERS".equals(string) ? 4 : "METADATA".equals(string) ? 5 : -1;
                    }
                } else {
                    i11 = i14;
                }
                if (jSONObject3.has("roles")) {
                    h4 h4Var = j4.f13340l;
                    Object[] objArrCopyOf = new Object[4];
                    JSONArray jSONArray2 = jSONObject3.getJSONArray("roles");
                    int i17 = i14;
                    int i18 = i17;
                    while (i17 < jSONArray2.length()) {
                        String strOptString3 = jSONArray2.optString(i17);
                        strOptString3.getClass();
                        int length = objArrCopyOf.length;
                        double d12 = d11;
                        int i19 = i18 + 1;
                        int iF = u0.f(length, i19);
                        if (iF > length) {
                            objArrCopyOf = Arrays.copyOf(objArrCopyOf, iF);
                        }
                        objArrCopyOf[i18] = strOptString3;
                        i17++;
                        i18 = i19;
                        d11 = d12;
                    }
                    d10 = d11;
                    p4VarH = j4.h(i18, objArrCopyOf);
                } else {
                    d10 = d11;
                    p4VarH = null;
                }
                arrayList.add(new MediaTrack(j10, i16, strOptStringOrNull, strOptStringOrNull2, strOptStringOrNull3, strOptStringOrNull4, i11, p4VarH, jSONObject3.optJSONObject("customData")));
                i15++;
                d11 = d10;
                i12 = 2;
                i13 = 1;
                i14 = 0;
            }
            d4 = d11;
            this.zzh = new ArrayList(arrayList);
        } else {
            d4 = 0.0d;
            this.zzh = null;
        }
        if (jSONObject.has("textTrackStyle")) {
            JSONObject jSONObject4 = jSONObject.getJSONObject("textTrackStyle");
            TextTrackStyle textTrackStyle = new TextTrackStyle();
            textTrackStyle.fromJson(jSONObject4);
            this.zzi = textTrackStyle;
        } else {
            this.zzi = null;
        }
        zza(jSONObject);
        this.zzs = jSONObject.optJSONObject("customData");
        this.zzl = CastUtils.optStringOrNull(jSONObject, "entity");
        this.zzo = CastUtils.optStringOrNull(jSONObject, "atvEntity");
        this.zzm = VastAdsRequest.fromJson(jSONObject.optJSONObject("vmapAdsRequest"));
        if (jSONObject.has("startAbsoluteTime") && !jSONObject.isNull("startAbsoluteTime")) {
            double dOptDouble2 = jSONObject.optDouble("startAbsoluteTime");
            if (!Double.isNaN(dOptDouble2) && !Double.isInfinite(dOptDouble2) && dOptDouble2 >= d4) {
                this.zzn = CastUtils.secToMillisec(dOptDouble2);
            }
        }
        if (jSONObject.has("contentUrl")) {
            this.zzp = jSONObject.optString("contentUrl");
        }
        this.zzq = CastUtils.optStringOrNull(jSONObject, "hlsSegmentFormat");
        this.zzr = CastUtils.optStringOrNull(jSONObject, "hlsVideoSegmentFormat");
    }
}
