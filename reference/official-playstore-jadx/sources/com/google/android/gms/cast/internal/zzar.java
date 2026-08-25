package com.google.android.gms.cast.internal;

import android.os.SystemClock;
import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import com.arflix.tv.BuildConfig;
import com.google.android.gms.cast.AdBreakStatus;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLiveSeekableRange;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.internal.media.MediaCommon;
import com.google.android.gms.common.internal.t;
import io.ktor.http.LinkHeader;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class zzar extends zzd {
    public static final String zzb;
    final zzav zzc;
    final zzav zzd;
    final zzav zze;
    final zzav zzf;
    final zzav zzg;
    final zzav zzh;
    final zzav zzi;
    final zzav zzj;
    final zzav zzk;
    final zzav zzl;
    final zzav zzm;
    final zzav zzn;
    final zzav zzo;
    final zzav zzp;
    final zzav zzq;
    final zzav zzr;
    final zzav zzs;
    final zzav zzt;
    final zzav zzu;
    private long zzv;
    private MediaStatus zzw;
    private Long zzx;
    private zzao zzy;
    private int zzz;

    static {
        int i10 = CastUtils.zza;
        zzb = "urn:x-cast:com.google.cast.media";
    }

    public zzar(String str) {
        super(zzb, "MediaControlChannel", null);
        this.zzz = -1;
        zzav zzavVar = new zzav(86400000L, "load");
        this.zzc = zzavVar;
        zzav zzavVar2 = new zzav(86400000L, "pause");
        this.zzd = zzavVar2;
        zzav zzavVar3 = new zzav(86400000L, BuildConfig.FLAVOR);
        this.zze = zzavVar3;
        zzav zzavVar4 = new zzav(86400000L, "stop");
        this.zzf = zzavVar4;
        zzav zzavVar5 = new zzav(10000L, "seek");
        this.zzg = zzavVar5;
        zzav zzavVar6 = new zzav(86400000L, "volume");
        this.zzh = zzavVar6;
        zzav zzavVar7 = new zzav(86400000L, "mute");
        this.zzi = zzavVar7;
        zzav zzavVar8 = new zzav(86400000L, "status");
        this.zzj = zzavVar8;
        zzav zzavVar9 = new zzav(86400000L, "activeTracks");
        this.zzk = zzavVar9;
        zzav zzavVar10 = new zzav(86400000L, "trackStyle");
        this.zzl = zzavVar10;
        zzav zzavVar11 = new zzav(86400000L, "queueInsert");
        this.zzm = zzavVar11;
        zzav zzavVar12 = new zzav(86400000L, "queueUpdate");
        this.zzn = zzavVar12;
        zzav zzavVar13 = new zzav(86400000L, "queueRemove");
        this.zzo = zzavVar13;
        zzav zzavVar14 = new zzav(86400000L, "queueReorder");
        this.zzp = zzavVar14;
        zzav zzavVar15 = new zzav(86400000L, "queueFetchItemIds");
        this.zzq = zzavVar15;
        zzav zzavVar16 = new zzav(86400000L, "queueFetchItemRange");
        this.zzs = zzavVar16;
        this.zzr = new zzav(86400000L, "queueFetchItems");
        zzav zzavVar17 = new zzav(86400000L, "setPlaybackRate");
        this.zzt = zzavVar17;
        zzav zzavVar18 = new zzav(86400000L, "skipAd");
        this.zzu = zzavVar18;
        zzc(zzavVar);
        zzc(zzavVar2);
        zzc(zzavVar3);
        zzc(zzavVar4);
        zzc(zzavVar5);
        zzc(zzavVar6);
        zzc(zzavVar7);
        zzc(zzavVar8);
        zzc(zzavVar9);
        zzc(zzavVar10);
        zzc(zzavVar11);
        zzc(zzavVar12);
        zzc(zzavVar13);
        zzc(zzavVar14);
        zzc(zzavVar15);
        zzc(zzavVar16);
        zzc(zzavVar16);
        zzc(zzavVar17);
        zzc(zzavVar18);
        zzZ();
    }

    private final long zzR(double d4, long j10, long j11) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.zzv;
        if (jElapsedRealtime < 0) {
            jElapsedRealtime = 0;
        }
        if (jElapsedRealtime == 0) {
            return j10;
        }
        long j12 = j10 + ((long) (jElapsedRealtime * d4));
        if (j11 > 0 && j12 > j11) {
            return j11;
        }
        if (j12 >= 0) {
            return j12;
        }
        return 0L;
    }

    private final boolean zzS() {
        return this.zzz != -1;
    }

    private final void zzT(JSONObject jSONObject, String str) {
        if (jSONObject.has("sequenceNumber")) {
            this.zzz = jSONObject.optInt("sequenceNumber", -1);
        } else {
            this.zza.w(str.concat(" message is missing a sequence number."), new Object[0]);
        }
    }

    private static int[] zzU(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        int[] iArr = new int[jSONArray.length()];
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            iArr[i10] = jSONArray.getInt(i10);
        }
        return iArr;
    }

    private final void zzV() {
        zzao zzaoVar = this.zzy;
        if (zzaoVar != null) {
            zzaoVar.zza();
        }
    }

    private final void zzW() {
        zzao zzaoVar = this.zzy;
        if (zzaoVar != null) {
            zzaoVar.zzb();
        }
    }

    private final void zzX() {
        zzao zzaoVar = this.zzy;
        if (zzaoVar != null) {
            zzaoVar.zzc();
        }
    }

    private final void zzY() {
        zzao zzaoVar = this.zzy;
        if (zzaoVar != null) {
            zzaoVar.zzd();
        }
    }

    private final void zzZ() {
        this.zzv = 0L;
        this.zzw = null;
        Iterator it = zzb().iterator();
        while (it.hasNext()) {
            ((zzav) it.next()).zze(2002);
        }
    }

    private static zzaq zzaa(JSONObject jSONObject) {
        MediaError mediaErrorZza = MediaError.zza(jSONObject);
        zzaq zzaqVar = new zzaq();
        int i10 = CastUtils.zza;
        zzaqVar.zza = jSONObject.has("customData") ? jSONObject.optJSONObject("customData") : null;
        zzaqVar.zzb = mediaErrorZza;
        return zzaqVar;
    }

    public final MediaStatus zzA() {
        return this.zzw;
    }

    public final MediaInfo zzB() {
        MediaStatus mediaStatus = this.zzw;
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getMediaInfo();
    }

    public final long zzC(zzat zzatVar, MediaQueueItem[] mediaQueueItemArr, int i10, int i11, long j10, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException {
        int length;
        String strZza;
        if (mediaQueueItemArr == null || (length = mediaQueueItemArr.length) == 0) {
            throw new IllegalArgumentException("items must not be null or empty.");
        }
        if (i10 < 0 || i10 >= length) {
            throw new IllegalArgumentException(c.p(i10, "Invalid startIndex: ", new StringBuilder(String.valueOf(i10).length() + 20)));
        }
        if (j10 != -1 && j10 < 0) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(j10).length() + 34);
            sb2.append("playPosition can not be negative: ");
            sb2.append(j10);
            throw new IllegalArgumentException(sb2.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        this.zzc.zza(jZzg, zzatVar);
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "QUEUE_LOAD");
            JSONArray jSONArray = new JSONArray();
            for (int i12 = 0; i12 < mediaQueueItemArr.length; i12++) {
                jSONArray.put(i12, mediaQueueItemArr[i12].toJson());
            }
            jSONObject2.put("items", jSONArray);
            strZza = MediaCommon.zza(Integer.valueOf(i11));
        } catch (JSONException unused) {
        }
        if (strZza == null) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(i11).length() + 21);
            sb3.append("Invalid repeat mode: ");
            sb3.append(i11);
            throw new IllegalArgumentException(sb3.toString());
        }
        jSONObject2.put("repeatMode", strZza);
        jSONObject2.put("startIndex", i10);
        if (j10 != -1) {
            jSONObject2.put("currentTime", CastUtils.millisecToSec(j10));
        }
        if (jSONObject != null) {
            jSONObject2.put("customData", jSONObject);
        }
        if (zzS()) {
            jSONObject2.put("sequenceNumber", this.zzz);
        }
        zzf(jSONObject2.toString(), jZzg, null);
        return jZzg;
    }

    public final long zzD(zzat zzatVar, MediaQueueItem[] mediaQueueItemArr, int i10, int i11, int i12, long j10, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException, zzap {
        if (mediaQueueItemArr == null || mediaQueueItemArr.length == 0) {
            throw new IllegalArgumentException("itemsToInsert must not be null or empty.");
        }
        if (j10 != -1 && j10 < 0) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(j10).length() + 34);
            sb2.append("playPosition can not be negative: ");
            sb2.append(j10);
            throw new IllegalArgumentException(sb2.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "QUEUE_INSERT");
            jSONObject2.put("mediaSessionId", zzM());
            JSONArray jSONArray = new JSONArray();
            for (int i13 = 0; i13 < mediaQueueItemArr.length; i13++) {
                jSONArray.put(i13, mediaQueueItemArr[i13].toJson());
            }
            jSONObject2.put("items", jSONArray);
            if (i10 != 0) {
                jSONObject2.put("insertBefore", i10);
            }
            if (i12 != -1) {
                jSONObject2.put("currentItemIndex", 0);
            }
            if (j10 != -1) {
                jSONObject2.put("currentTime", CastUtils.millisecToSec(j10));
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzS()) {
                jSONObject2.put("sequenceNumber", this.zzz);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject2.toString(), jZzg, null);
        this.zzm.zza(jZzg, new zzan(this, zzatVar));
        return jZzg;
    }

    public final long zzE(zzat zzatVar, int i10, long j10, MediaQueueItem[] mediaQueueItemArr, int i11, Boolean bool, Integer num, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException, zzap {
        if (j10 != -1 && j10 < 0) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(j10).length() + 33);
            sb2.append("playPosition cannot be negative: ");
            sb2.append(j10);
            throw new IllegalArgumentException(sb2.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "QUEUE_UPDATE");
            jSONObject2.put("mediaSessionId", zzM());
            if (i10 != 0) {
                jSONObject2.put("currentItemId", i10);
            }
            if (i11 != 0) {
                jSONObject2.put("jump", i11);
            }
            if (mediaQueueItemArr != null && mediaQueueItemArr.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int i12 = 0; i12 < mediaQueueItemArr.length; i12++) {
                    jSONArray.put(i12, mediaQueueItemArr[i12].toJson());
                }
                jSONObject2.put("items", jSONArray);
            }
            if (bool != null) {
                jSONObject2.put("shuffle", bool);
            }
            String strZza = MediaCommon.zza(num);
            if (strZza != null) {
                jSONObject2.put("repeatMode", strZza);
            }
            if (j10 != -1) {
                jSONObject2.put("currentTime", CastUtils.millisecToSec(j10));
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzS()) {
                jSONObject2.put("sequenceNumber", this.zzz);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject2.toString(), jZzg, null);
        this.zzn.zza(jZzg, new zzan(this, zzatVar));
        return jZzg;
    }

    public final long zzF(zzat zzatVar, int[] iArr, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException, zzap {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToRemove must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "QUEUE_REMOVE");
            jSONObject2.put("mediaSessionId", zzM());
            JSONArray jSONArray = new JSONArray();
            for (int i10 = 0; i10 < iArr.length; i10++) {
                jSONArray.put(i10, iArr[i10]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzS()) {
                jSONObject2.put("sequenceNumber", this.zzz);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject2.toString(), jZzg, null);
        this.zzo.zza(jZzg, new zzan(this, zzatVar));
        return jZzg;
    }

    public final long zzG(zzat zzatVar, int[] iArr, int i10, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException, zzap {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToReorder must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "QUEUE_REORDER");
            jSONObject2.put("mediaSessionId", zzM());
            JSONArray jSONArray = new JSONArray();
            for (int i11 = 0; i11 < iArr.length; i11++) {
                jSONArray.put(i11, iArr[i11]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (i10 != 0) {
                jSONObject2.put("insertBefore", i10);
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
            if (zzS()) {
                jSONObject2.put("sequenceNumber", this.zzz);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject2.toString(), jZzg, null);
        this.zzp.zza(jZzg, new zzan(this, zzatVar));
        return jZzg;
    }

    public final long zzH(zzat zzatVar) throws IllegalStateException, zzap {
        JSONObject jSONObject = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject.put("requestId", jZzg);
            jSONObject.put(LinkHeader.Parameters.Type, "QUEUE_GET_ITEM_IDS");
            jSONObject.put("mediaSessionId", zzM());
        } catch (JSONException unused) {
        }
        zzf(jSONObject.toString(), jZzg, null);
        this.zzq.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzI(zzat zzatVar, int i10, int i11, int i12) throws IllegalArgumentException, zzap {
        if (i11 > 0 && i12 == 0) {
            i12 = 0;
        } else if (i11 != 0 || i12 <= 0) {
            throw new IllegalArgumentException("Exactly one of nextCount and prevCount must be positive and the other must be zero");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject.put("requestId", jZzg);
            jSONObject.put(LinkHeader.Parameters.Type, "QUEUE_GET_ITEM_RANGE");
            jSONObject.put("mediaSessionId", zzM());
            jSONObject.put("itemId", i10);
            if (i11 > 0) {
                jSONObject.put("nextCount", i11);
            }
            if (i12 > 0) {
                jSONObject.put("prevCount", i12);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject.toString(), jZzg, null);
        this.zzs.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzJ(zzat zzatVar, int[] iArr) throws IllegalArgumentException, zzap {
        JSONObject jSONObject = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject.put("requestId", jZzg);
            jSONObject.put(LinkHeader.Parameters.Type, "QUEUE_GET_ITEMS");
            jSONObject.put("mediaSessionId", zzM());
            JSONArray jSONArray = new JSONArray();
            for (int i10 : iArr) {
                jSONArray.put(i10);
            }
            jSONObject.put("itemIds", jSONArray);
        } catch (JSONException unused) {
        }
        zzf(jSONObject.toString(), jZzg, null);
        this.zzr.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzK(String str, List list) throws IllegalStateException {
        long jZzg = zzg();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("requestId", jZzg);
            jSONObject.put(LinkHeader.Parameters.Type, "PRECACHE");
            jSONObject.put("precacheData", str);
        } catch (JSONException unused) {
        }
        zzf(jSONObject.toString(), jZzg, null);
        return jZzg;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009a A[Catch: JSONException -> 0x0055, TryCatch #0 {JSONException -> 0x0055, blocks: (B:3:0x000f, B:9:0x0039, B:11:0x0045, B:13:0x004f, B:19:0x0060, B:21:0x006d, B:23:0x007f, B:34:0x009a, B:37:0x009f, B:39:0x00b3, B:41:0x00b7, B:43:0x00c3, B:45:0x00c7, B:46:0x00ce, B:48:0x00d2, B:49:0x00d8, B:51:0x00dc, B:52:0x00df, B:54:0x00e3, B:55:0x00e6, B:57:0x00ea, B:58:0x00ed, B:60:0x00f1, B:62:0x00fb, B:63:0x00fe, B:65:0x0102, B:67:0x010b, B:69:0x011d, B:70:0x0125, B:72:0x012b, B:38:0x00a4, B:27:0x0088, B:29:0x0090, B:68:0x010f, B:76:0x013d, B:77:0x014c, B:79:0x0152, B:83:0x0168, B:85:0x0174, B:87:0x0188, B:92:0x0199, B:96:0x01a7, B:98:0x01bc, B:100:0x01d5, B:104:0x01e3, B:108:0x01f1, B:112:0x01ff, B:113:0x0207, B:115:0x020d, B:116:0x021b, B:118:0x021f, B:122:0x0231, B:126:0x0243, B:127:0x0252, B:129:0x0258, B:133:0x0270, B:136:0x027d, B:137:0x028a, B:139:0x0290, B:140:0x02a2, B:144:0x02b0), top: B:148:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a4 A[Catch: JSONException -> 0x0055, TryCatch #0 {JSONException -> 0x0055, blocks: (B:3:0x000f, B:9:0x0039, B:11:0x0045, B:13:0x004f, B:19:0x0060, B:21:0x006d, B:23:0x007f, B:34:0x009a, B:37:0x009f, B:39:0x00b3, B:41:0x00b7, B:43:0x00c3, B:45:0x00c7, B:46:0x00ce, B:48:0x00d2, B:49:0x00d8, B:51:0x00dc, B:52:0x00df, B:54:0x00e3, B:55:0x00e6, B:57:0x00ea, B:58:0x00ed, B:60:0x00f1, B:62:0x00fb, B:63:0x00fe, B:65:0x0102, B:67:0x010b, B:69:0x011d, B:70:0x0125, B:72:0x012b, B:38:0x00a4, B:27:0x0088, B:29:0x0090, B:68:0x010f, B:76:0x013d, B:77:0x014c, B:79:0x0152, B:83:0x0168, B:85:0x0174, B:87:0x0188, B:92:0x0199, B:96:0x01a7, B:98:0x01bc, B:100:0x01d5, B:104:0x01e3, B:108:0x01f1, B:112:0x01ff, B:113:0x0207, B:115:0x020d, B:116:0x021b, B:118:0x021f, B:122:0x0231, B:126:0x0243, B:127:0x0252, B:129:0x0258, B:133:0x0270, B:136:0x027d, B:137:0x028a, B:139:0x0290, B:140:0x02a2, B:144:0x02b0), top: B:148:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b7 A[Catch: JSONException -> 0x0055, TryCatch #0 {JSONException -> 0x0055, blocks: (B:3:0x000f, B:9:0x0039, B:11:0x0045, B:13:0x004f, B:19:0x0060, B:21:0x006d, B:23:0x007f, B:34:0x009a, B:37:0x009f, B:39:0x00b3, B:41:0x00b7, B:43:0x00c3, B:45:0x00c7, B:46:0x00ce, B:48:0x00d2, B:49:0x00d8, B:51:0x00dc, B:52:0x00df, B:54:0x00e3, B:55:0x00e6, B:57:0x00ea, B:58:0x00ed, B:60:0x00f1, B:62:0x00fb, B:63:0x00fe, B:65:0x0102, B:67:0x010b, B:69:0x011d, B:70:0x0125, B:72:0x012b, B:38:0x00a4, B:27:0x0088, B:29:0x0090, B:68:0x010f, B:76:0x013d, B:77:0x014c, B:79:0x0152, B:83:0x0168, B:85:0x0174, B:87:0x0188, B:92:0x0199, B:96:0x01a7, B:98:0x01bc, B:100:0x01d5, B:104:0x01e3, B:108:0x01f1, B:112:0x01ff, B:113:0x0207, B:115:0x020d, B:116:0x021b, B:118:0x021f, B:122:0x0231, B:126:0x0243, B:127:0x0252, B:129:0x0258, B:133:0x0270, B:136:0x027d, B:137:0x028a, B:139:0x0290, B:140:0x02a2, B:144:0x02b0), top: B:148:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c7 A[Catch: JSONException -> 0x0055, TryCatch #0 {JSONException -> 0x0055, blocks: (B:3:0x000f, B:9:0x0039, B:11:0x0045, B:13:0x004f, B:19:0x0060, B:21:0x006d, B:23:0x007f, B:34:0x009a, B:37:0x009f, B:39:0x00b3, B:41:0x00b7, B:43:0x00c3, B:45:0x00c7, B:46:0x00ce, B:48:0x00d2, B:49:0x00d8, B:51:0x00dc, B:52:0x00df, B:54:0x00e3, B:55:0x00e6, B:57:0x00ea, B:58:0x00ed, B:60:0x00f1, B:62:0x00fb, B:63:0x00fe, B:65:0x0102, B:67:0x010b, B:69:0x011d, B:70:0x0125, B:72:0x012b, B:38:0x00a4, B:27:0x0088, B:29:0x0090, B:68:0x010f, B:76:0x013d, B:77:0x014c, B:79:0x0152, B:83:0x0168, B:85:0x0174, B:87:0x0188, B:92:0x0199, B:96:0x01a7, B:98:0x01bc, B:100:0x01d5, B:104:0x01e3, B:108:0x01f1, B:112:0x01ff, B:113:0x0207, B:115:0x020d, B:116:0x021b, B:118:0x021f, B:122:0x0231, B:126:0x0243, B:127:0x0252, B:129:0x0258, B:133:0x0270, B:136:0x027d, B:137:0x028a, B:139:0x0290, B:140:0x02a2, B:144:0x02b0), top: B:148:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d2 A[Catch: JSONException -> 0x0055, TryCatch #0 {JSONException -> 0x0055, blocks: (B:3:0x000f, B:9:0x0039, B:11:0x0045, B:13:0x004f, B:19:0x0060, B:21:0x006d, B:23:0x007f, B:34:0x009a, B:37:0x009f, B:39:0x00b3, B:41:0x00b7, B:43:0x00c3, B:45:0x00c7, B:46:0x00ce, B:48:0x00d2, B:49:0x00d8, B:51:0x00dc, B:52:0x00df, B:54:0x00e3, B:55:0x00e6, B:57:0x00ea, B:58:0x00ed, B:60:0x00f1, B:62:0x00fb, B:63:0x00fe, B:65:0x0102, B:67:0x010b, B:69:0x011d, B:70:0x0125, B:72:0x012b, B:38:0x00a4, B:27:0x0088, B:29:0x0090, B:68:0x010f, B:76:0x013d, B:77:0x014c, B:79:0x0152, B:83:0x0168, B:85:0x0174, B:87:0x0188, B:92:0x0199, B:96:0x01a7, B:98:0x01bc, B:100:0x01d5, B:104:0x01e3, B:108:0x01f1, B:112:0x01ff, B:113:0x0207, B:115:0x020d, B:116:0x021b, B:118:0x021f, B:122:0x0231, B:126:0x0243, B:127:0x0252, B:129:0x0258, B:133:0x0270, B:136:0x027d, B:137:0x028a, B:139:0x0290, B:140:0x02a2, B:144:0x02b0), top: B:148:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00dc A[Catch: JSONException -> 0x0055, TryCatch #0 {JSONException -> 0x0055, blocks: (B:3:0x000f, B:9:0x0039, B:11:0x0045, B:13:0x004f, B:19:0x0060, B:21:0x006d, B:23:0x007f, B:34:0x009a, B:37:0x009f, B:39:0x00b3, B:41:0x00b7, B:43:0x00c3, B:45:0x00c7, B:46:0x00ce, B:48:0x00d2, B:49:0x00d8, B:51:0x00dc, B:52:0x00df, B:54:0x00e3, B:55:0x00e6, B:57:0x00ea, B:58:0x00ed, B:60:0x00f1, B:62:0x00fb, B:63:0x00fe, B:65:0x0102, B:67:0x010b, B:69:0x011d, B:70:0x0125, B:72:0x012b, B:38:0x00a4, B:27:0x0088, B:29:0x0090, B:68:0x010f, B:76:0x013d, B:77:0x014c, B:79:0x0152, B:83:0x0168, B:85:0x0174, B:87:0x0188, B:92:0x0199, B:96:0x01a7, B:98:0x01bc, B:100:0x01d5, B:104:0x01e3, B:108:0x01f1, B:112:0x01ff, B:113:0x0207, B:115:0x020d, B:116:0x021b, B:118:0x021f, B:122:0x0231, B:126:0x0243, B:127:0x0252, B:129:0x0258, B:133:0x0270, B:136:0x027d, B:137:0x028a, B:139:0x0290, B:140:0x02a2, B:144:0x02b0), top: B:148:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00e3 A[Catch: JSONException -> 0x0055, TryCatch #0 {JSONException -> 0x0055, blocks: (B:3:0x000f, B:9:0x0039, B:11:0x0045, B:13:0x004f, B:19:0x0060, B:21:0x006d, B:23:0x007f, B:34:0x009a, B:37:0x009f, B:39:0x00b3, B:41:0x00b7, B:43:0x00c3, B:45:0x00c7, B:46:0x00ce, B:48:0x00d2, B:49:0x00d8, B:51:0x00dc, B:52:0x00df, B:54:0x00e3, B:55:0x00e6, B:57:0x00ea, B:58:0x00ed, B:60:0x00f1, B:62:0x00fb, B:63:0x00fe, B:65:0x0102, B:67:0x010b, B:69:0x011d, B:70:0x0125, B:72:0x012b, B:38:0x00a4, B:27:0x0088, B:29:0x0090, B:68:0x010f, B:76:0x013d, B:77:0x014c, B:79:0x0152, B:83:0x0168, B:85:0x0174, B:87:0x0188, B:92:0x0199, B:96:0x01a7, B:98:0x01bc, B:100:0x01d5, B:104:0x01e3, B:108:0x01f1, B:112:0x01ff, B:113:0x0207, B:115:0x020d, B:116:0x021b, B:118:0x021f, B:122:0x0231, B:126:0x0243, B:127:0x0252, B:129:0x0258, B:133:0x0270, B:136:0x027d, B:137:0x028a, B:139:0x0290, B:140:0x02a2, B:144:0x02b0), top: B:148:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ea A[Catch: JSONException -> 0x0055, TryCatch #0 {JSONException -> 0x0055, blocks: (B:3:0x000f, B:9:0x0039, B:11:0x0045, B:13:0x004f, B:19:0x0060, B:21:0x006d, B:23:0x007f, B:34:0x009a, B:37:0x009f, B:39:0x00b3, B:41:0x00b7, B:43:0x00c3, B:45:0x00c7, B:46:0x00ce, B:48:0x00d2, B:49:0x00d8, B:51:0x00dc, B:52:0x00df, B:54:0x00e3, B:55:0x00e6, B:57:0x00ea, B:58:0x00ed, B:60:0x00f1, B:62:0x00fb, B:63:0x00fe, B:65:0x0102, B:67:0x010b, B:69:0x011d, B:70:0x0125, B:72:0x012b, B:38:0x00a4, B:27:0x0088, B:29:0x0090, B:68:0x010f, B:76:0x013d, B:77:0x014c, B:79:0x0152, B:83:0x0168, B:85:0x0174, B:87:0x0188, B:92:0x0199, B:96:0x01a7, B:98:0x01bc, B:100:0x01d5, B:104:0x01e3, B:108:0x01f1, B:112:0x01ff, B:113:0x0207, B:115:0x020d, B:116:0x021b, B:118:0x021f, B:122:0x0231, B:126:0x0243, B:127:0x0252, B:129:0x0258, B:133:0x0270, B:136:0x027d, B:137:0x028a, B:139:0x0290, B:140:0x02a2, B:144:0x02b0), top: B:148:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f1 A[Catch: JSONException -> 0x0055, TryCatch #0 {JSONException -> 0x0055, blocks: (B:3:0x000f, B:9:0x0039, B:11:0x0045, B:13:0x004f, B:19:0x0060, B:21:0x006d, B:23:0x007f, B:34:0x009a, B:37:0x009f, B:39:0x00b3, B:41:0x00b7, B:43:0x00c3, B:45:0x00c7, B:46:0x00ce, B:48:0x00d2, B:49:0x00d8, B:51:0x00dc, B:52:0x00df, B:54:0x00e3, B:55:0x00e6, B:57:0x00ea, B:58:0x00ed, B:60:0x00f1, B:62:0x00fb, B:63:0x00fe, B:65:0x0102, B:67:0x010b, B:69:0x011d, B:70:0x0125, B:72:0x012b, B:38:0x00a4, B:27:0x0088, B:29:0x0090, B:68:0x010f, B:76:0x013d, B:77:0x014c, B:79:0x0152, B:83:0x0168, B:85:0x0174, B:87:0x0188, B:92:0x0199, B:96:0x01a7, B:98:0x01bc, B:100:0x01d5, B:104:0x01e3, B:108:0x01f1, B:112:0x01ff, B:113:0x0207, B:115:0x020d, B:116:0x021b, B:118:0x021f, B:122:0x0231, B:126:0x0243, B:127:0x0252, B:129:0x0258, B:133:0x0270, B:136:0x027d, B:137:0x028a, B:139:0x0290, B:140:0x02a2, B:144:0x02b0), top: B:148:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0102 A[Catch: JSONException -> 0x0055, TryCatch #0 {JSONException -> 0x0055, blocks: (B:3:0x000f, B:9:0x0039, B:11:0x0045, B:13:0x004f, B:19:0x0060, B:21:0x006d, B:23:0x007f, B:34:0x009a, B:37:0x009f, B:39:0x00b3, B:41:0x00b7, B:43:0x00c3, B:45:0x00c7, B:46:0x00ce, B:48:0x00d2, B:49:0x00d8, B:51:0x00dc, B:52:0x00df, B:54:0x00e3, B:55:0x00e6, B:57:0x00ea, B:58:0x00ed, B:60:0x00f1, B:62:0x00fb, B:63:0x00fe, B:65:0x0102, B:67:0x010b, B:69:0x011d, B:70:0x0125, B:72:0x012b, B:38:0x00a4, B:27:0x0088, B:29:0x0090, B:68:0x010f, B:76:0x013d, B:77:0x014c, B:79:0x0152, B:83:0x0168, B:85:0x0174, B:87:0x0188, B:92:0x0199, B:96:0x01a7, B:98:0x01bc, B:100:0x01d5, B:104:0x01e3, B:108:0x01f1, B:112:0x01ff, B:113:0x0207, B:115:0x020d, B:116:0x021b, B:118:0x021f, B:122:0x0231, B:126:0x0243, B:127:0x0252, B:129:0x0258, B:133:0x0270, B:136:0x027d, B:137:0x028a, B:139:0x0290, B:140:0x02a2, B:144:0x02b0), top: B:148:0x000f }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0109  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzL(java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 776
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.internal.zzar.zzL(java.lang.String):void");
    }

    public final long zzM() throws zzap {
        MediaStatus mediaStatus = this.zzw;
        if (mediaStatus != null) {
            return mediaStatus.zza();
        }
        throw new zzap();
    }

    public final void zzN(long j10, int i10) {
        Iterator it = zzb().iterator();
        while (it.hasNext()) {
            ((zzav) it.next()).zzd(j10, i10, null);
        }
    }

    public final /* synthetic */ void zzO(Long l10) {
        this.zzx = null;
    }

    public final /* synthetic */ zzao zzP() {
        return this.zzy;
    }

    public final /* synthetic */ int zzQ() {
        return this.zzz;
    }

    @Override // com.google.android.gms.cast.internal.zzq
    public final void zzh() {
        zza();
        zzZ();
    }

    public final void zzi(zzao zzaoVar) {
        this.zzy = zzaoVar;
    }

    public final long zzj(zzat zzatVar, MediaLoadRequestData mediaLoadRequestData) throws IllegalStateException, IllegalArgumentException {
        if (mediaLoadRequestData.getMediaInfo() == null && mediaLoadRequestData.getQueueData() == null) {
            throw new IllegalArgumentException("MediaInfo and MediaQueueData should not be both null");
        }
        JSONObject json = mediaLoadRequestData.toJson();
        if (json == null) {
            throw new IllegalArgumentException("Failed to jsonify the load request due to malformed request");
        }
        long jZzg = zzg();
        try {
            json.put("requestId", jZzg);
            json.put(LinkHeader.Parameters.Type, "LOAD");
        } catch (JSONException unused) {
        }
        zzf(json.toString(), jZzg, null);
        this.zzc.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzk(zzat zzatVar, JSONObject jSONObject) throws IllegalStateException, zzap {
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "PAUSE");
            jSONObject2.put("mediaSessionId", zzM());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject2.toString(), jZzg, null);
        this.zzd.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzl(zzat zzatVar, JSONObject jSONObject) throws IllegalStateException, zzap {
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "STOP");
            jSONObject2.put("mediaSessionId", zzM());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject2.toString(), jZzg, null);
        this.zzf.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzm(zzat zzatVar, JSONObject jSONObject) throws IllegalStateException, zzap {
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "PLAY");
            jSONObject2.put("mediaSessionId", zzM());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject2.toString(), jZzg, null);
        this.zze.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzn(zzat zzatVar, MediaSeekOptions mediaSeekOptions) throws IllegalStateException, zzap {
        JSONObject jSONObject = new JSONObject();
        long jZzg = zzg();
        long position = mediaSeekOptions.isSeekToInfinite() ? 4294967296000L : mediaSeekOptions.getPosition();
        try {
            jSONObject.put("requestId", jZzg);
            jSONObject.put(LinkHeader.Parameters.Type, "SEEK");
            jSONObject.put("mediaSessionId", zzM());
            jSONObject.put("currentTime", CastUtils.millisecToSec(position));
            if (mediaSeekOptions.getResumeState() == 1) {
                jSONObject.put("resumeState", "PLAYBACK_START");
            } else if (mediaSeekOptions.getResumeState() == 2) {
                jSONObject.put("resumeState", "PLAYBACK_PAUSE");
            }
            if (mediaSeekOptions.getCustomData() != null) {
                jSONObject.put("customData", mediaSeekOptions.getCustomData());
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject.toString(), jZzg, null);
        this.zzx = Long.valueOf(position);
        this.zzg.zza(jZzg, new zzam(this, zzatVar));
        return jZzg;
    }

    public final long zzo(zzat zzatVar) throws IllegalStateException, zzap {
        JSONObject jSONObject = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject.put("requestId", jZzg);
            jSONObject.put(LinkHeader.Parameters.Type, "SKIP_AD");
            jSONObject.put("mediaSessionId", zzM());
        } catch (JSONException e5) {
            Logger logger = this.zza;
            Locale locale = Locale.ROOT;
            logger.w(d.C("Error creating SkipAd message: ", e5.getMessage()), new Object[0]);
        }
        zzf(jSONObject.toString(), jZzg, null);
        this.zzu.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzp(zzat zzatVar, double d4, JSONObject jSONObject) throws IllegalStateException, IllegalArgumentException, zzap {
        if (Double.isInfinite(d4) || Double.isNaN(d4)) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(d4).length() + 17);
            sb2.append("Volume cannot be ");
            sb2.append(d4);
            throw new IllegalArgumentException(sb2.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzM());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("level", d4);
            jSONObject2.put("volume", jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject2.toString(), jZzg, null);
        this.zzh.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzq(zzat zzatVar, boolean z, JSONObject jSONObject) throws IllegalStateException, zzap {
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzM());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("muted", z);
            jSONObject2.put("volume", jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject2.toString(), jZzg, null);
        this.zzi.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzr(zzat zzatVar, double d4, JSONObject jSONObject) throws IllegalStateException, zzap {
        if (this.zzw == null) {
            throw new zzap();
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject2.put("requestId", jZzg);
            jSONObject2.put(LinkHeader.Parameters.Type, "SET_PLAYBACK_RATE");
            jSONObject2.put("playbackRate", d4);
            t.j(this.zzw, "mediaStatus should not be null");
            jSONObject2.put("mediaSessionId", this.zzw.zza());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject2.toString(), jZzg, null);
        this.zzt.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzs(zzat zzatVar) throws IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject.put("requestId", jZzg);
            jSONObject.put(LinkHeader.Parameters.Type, "GET_STATUS");
            MediaStatus mediaStatus = this.zzw;
            if (mediaStatus != null) {
                jSONObject.put("mediaSessionId", mediaStatus.zza());
            }
        } catch (JSONException unused) {
        }
        zzf(jSONObject.toString(), jZzg, null);
        this.zzj.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzt(zzat zzatVar, long[] jArr) throws IllegalStateException, zzap {
        if (jArr == null) {
            throw new IllegalArgumentException("trackIds cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject.put("requestId", jZzg);
            jSONObject.put(LinkHeader.Parameters.Type, "EDIT_TRACKS_INFO");
            jSONObject.put("mediaSessionId", zzM());
            JSONArray jSONArray = new JSONArray();
            for (int i10 = 0; i10 < jArr.length; i10++) {
                jSONArray.put(i10, jArr[i10]);
            }
            jSONObject.put("activeTrackIds", jSONArray);
        } catch (JSONException unused) {
        }
        zzf(jSONObject.toString(), jZzg, null);
        this.zzk.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzu(zzat zzatVar, TextTrackStyle textTrackStyle) throws IllegalStateException, zzap {
        if (textTrackStyle == null) {
            throw new IllegalArgumentException("trackStyle cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzg = zzg();
        try {
            jSONObject.put("requestId", jZzg);
            jSONObject.put(LinkHeader.Parameters.Type, "EDIT_TRACKS_INFO");
            jSONObject.put("textTrackStyle", textTrackStyle.zza());
            jSONObject.put("mediaSessionId", zzM());
        } catch (JSONException unused) {
        }
        zzf(jSONObject.toString(), jZzg, null);
        this.zzl.zza(jZzg, zzatVar);
        return jZzg;
    }

    public final long zzv() {
        MediaStatus mediaStatus;
        MediaInfo mediaInfoZzB = zzB();
        if (mediaInfoZzB == null || (mediaStatus = this.zzw) == null) {
            return 0L;
        }
        Long l10 = this.zzx;
        if (l10 == null) {
            if (this.zzv == 0) {
                return 0L;
            }
            double playbackRate = mediaStatus.getPlaybackRate();
            long streamPosition = mediaStatus.getStreamPosition();
            return (playbackRate == 0.0d || mediaStatus.getPlayerState() != 2) ? streamPosition : zzR(playbackRate, streamPosition, mediaInfoZzB.getStreamDuration());
        }
        if (l10.equals(4294967296000L)) {
            if (this.zzw.getLiveSeekableRange() != null) {
                return Math.min(l10.longValue(), zzx());
            }
            if (zzz() >= 0) {
                return Math.min(l10.longValue(), zzz());
            }
        }
        return l10.longValue();
    }

    public final long zzw() {
        MediaLiveSeekableRange liveSeekableRange;
        MediaStatus mediaStatus = this.zzw;
        if (mediaStatus == null || (liveSeekableRange = mediaStatus.getLiveSeekableRange()) == null) {
            return 0L;
        }
        long startTime = liveSeekableRange.getStartTime();
        if (liveSeekableRange.isMovingWindow()) {
            startTime = zzR(1.0d, startTime, -1L);
        }
        return liveSeekableRange.isLiveDone() ? Math.min(startTime, liveSeekableRange.getEndTime()) : startTime;
    }

    public final long zzx() {
        MediaLiveSeekableRange liveSeekableRange;
        MediaStatus mediaStatus = this.zzw;
        if (mediaStatus == null || (liveSeekableRange = mediaStatus.getLiveSeekableRange()) == null) {
            return 0L;
        }
        long endTime = liveSeekableRange.getEndTime();
        return !liveSeekableRange.isLiveDone() ? zzR(1.0d, endTime, -1L) : endTime;
    }

    public final long zzy() {
        MediaStatus mediaStatus;
        AdBreakStatus adBreakStatus;
        if (this.zzv == 0 || (mediaStatus = this.zzw) == null || (adBreakStatus = mediaStatus.getAdBreakStatus()) == null) {
            return 0L;
        }
        double playbackRate = mediaStatus.getPlaybackRate();
        if (playbackRate == 0.0d) {
            playbackRate = 1.0d;
        }
        return zzR(mediaStatus.getPlayerState() != 2 ? 0.0d : playbackRate, adBreakStatus.getCurrentBreakClipTimeInMs(), 0L);
    }

    public final long zzz() {
        MediaInfo mediaInfoZzB = zzB();
        if (mediaInfoZzB != null) {
            return mediaInfoZzB.getStreamDuration();
        }
        return 0L;
    }
}
