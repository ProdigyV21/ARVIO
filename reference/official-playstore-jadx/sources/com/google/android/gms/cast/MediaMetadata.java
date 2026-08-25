package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.fragment.app.a2;
import com.google.android.gms.cast.internal.CastUtils;
import io.ktor.http.LinkHeader;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import r3.b;
import t3.a;

/* JADX INFO: loaded from: classes4.dex */
public class MediaMetadata extends a {
    public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
    public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
    public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
    public static final String KEY_BOOK_TITLE = "com.google.android.gms.cast.metadata.BOOK_TITLE";
    public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
    public static final String KEY_CHAPTER_NUMBER = "com.google.android.gms.cast.metadata.CHAPTER_NUMBER";
    public static final String KEY_CHAPTER_TITLE = "com.google.android.gms.cast.metadata.CHAPTER_TITLE";
    public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
    public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
    public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
    public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
    public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
    public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
    public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";
    public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
    public static final String KEY_QUEUE_ITEM_ID = "com.google.android.gms.cast.metadata.QUEUE_ITEM_ID";
    public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
    public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
    public static final String KEY_SECTION_DURATION = "com.google.android.gms.cast.metadata.SECTION_DURATION";
    public static final String KEY_SECTION_START_ABSOLUTE_TIME = "com.google.android.gms.cast.metadata.SECTION_START_ABSOLUTE_TIME";
    public static final String KEY_SECTION_START_TIME_IN_CONTAINER = "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_CONTAINER";
    public static final String KEY_SECTION_START_TIME_IN_MEDIA = "com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA";
    public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
    public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
    public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
    public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
    public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
    public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
    public static final int MEDIA_TYPE_AUDIOBOOK_CHAPTER = 5;
    public static final int MEDIA_TYPE_GENERIC = 0;
    public static final int MEDIA_TYPE_MOVIE = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
    public static final int MEDIA_TYPE_PHOTO = 4;
    public static final int MEDIA_TYPE_TV_SHOW = 2;
    public static final int MEDIA_TYPE_USER = 100;
    private static final zzbu zzc;
    final Bundle zza;
    private final List zzd;
    private int zze;
    private final Writer zzf;
    private static final String[] zzb = {"none", "String", "int", "double", "ISO-8601 date String", "Time in milliseconds as long"};
    public static final Parcelable.Creator<MediaMetadata> CREATOR = new zzbv();

    public class Writer {
        final /* synthetic */ MediaMetadata zza;

        public Writer(MediaMetadata mediaMetadata) {
            Objects.requireNonNull(mediaMetadata);
            this.zza = mediaMetadata;
        }

        public Object getRawValue(String str) {
            return this.zza.zza.get(str);
        }

        public void remove(String str) {
            this.zza.zza.remove(str);
        }

        public void setMediaType(int i10) {
            this.zza.zzc(i10);
        }
    }

    static {
        zzbu zzbuVar = new zzbu();
        zzbuVar.zza(KEY_CREATION_DATE, "creationDateTime", 4);
        zzbuVar.zza(KEY_RELEASE_DATE, "releaseDate", 4);
        zzbuVar.zza(KEY_BROADCAST_DATE, "originalAirdate", 4);
        zzbuVar.zza(KEY_TITLE, LinkHeader.Parameters.Title, 1);
        zzbuVar.zza(KEY_SUBTITLE, MediaTrack.ROLE_SUBTITLE, 1);
        zzbuVar.zza(KEY_ARTIST, "artist", 1);
        zzbuVar.zza(KEY_ALBUM_ARTIST, "albumArtist", 1);
        zzbuVar.zza(KEY_ALBUM_TITLE, "albumName", 1);
        zzbuVar.zza(KEY_COMPOSER, "composer", 1);
        zzbuVar.zza(KEY_DISC_NUMBER, "discNumber", 2);
        zzbuVar.zza(KEY_TRACK_NUMBER, "trackNumber", 2);
        zzbuVar.zza(KEY_SEASON_NUMBER, "season", 2);
        zzbuVar.zza(KEY_EPISODE_NUMBER, "episode", 2);
        zzbuVar.zza(KEY_SERIES_TITLE, "seriesTitle", 1);
        zzbuVar.zza(KEY_STUDIO, "studio", 1);
        zzbuVar.zza(KEY_WIDTH, "width", 2);
        zzbuVar.zza(KEY_HEIGHT, "height", 2);
        zzbuVar.zza(KEY_LOCATION_NAME, "location", 1);
        zzbuVar.zza(KEY_LOCATION_LATITUDE, "latitude", 3);
        zzbuVar.zza(KEY_LOCATION_LONGITUDE, "longitude", 3);
        zzbuVar.zza(KEY_SECTION_DURATION, "sectionDuration", 5);
        zzbuVar.zza(KEY_SECTION_START_TIME_IN_MEDIA, "sectionStartTimeInMedia", 5);
        zzbuVar.zza(KEY_SECTION_START_ABSOLUTE_TIME, "sectionStartAbsoluteTime", 5);
        zzbuVar.zza(KEY_SECTION_START_TIME_IN_CONTAINER, "sectionStartTimeInContainer", 5);
        zzbuVar.zza(KEY_QUEUE_ITEM_ID, "queueItemId", 2);
        zzbuVar.zza(KEY_BOOK_TITLE, "bookTitle", 1);
        zzbuVar.zza(KEY_CHAPTER_NUMBER, "chapterNumber", 2);
        zzbuVar.zza(KEY_CHAPTER_TITLE, "chapterTitle", 1);
        zzc = zzbuVar;
    }

    public MediaMetadata(List list, Bundle bundle, int i10) {
        this.zzf = new Writer(this);
        this.zzd = list;
        this.zza = bundle;
        this.zze = i10;
    }

    public static int getTypeForKey(String str) throws IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("null and empty keys are not allowed");
        }
        return zzc.zzd(str);
    }

    public static void throwIfWrongType(String str, int i10) throws IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("null and empty keys are not allowed");
        }
        int iZzd = zzc.zzd(str);
        if (iZzd == i10 || iZzd == 0) {
            return;
        }
        String str2 = zzb[i10];
        throw new IllegalArgumentException(a2.n(new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(str2).length()), "Value for ", str, " must be a ", str2));
    }

    private final boolean zzd(Bundle bundle, Bundle bundle2) {
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !zzd((Bundle) obj, (Bundle) obj2)) {
                return false;
            }
            if (obj == null) {
                if (obj2 != null || !bundle2.containsKey(str)) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    public void addImage(b bVar) {
        this.zzd.add(bVar);
    }

    public void clear() {
        this.zza.clear();
        this.zzd.clear();
    }

    public void clearImages() {
        this.zzd.clear();
    }

    public boolean containsKey(String str) {
        return this.zza.containsKey(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        return zzd(this.zza, mediaMetadata.zza) && this.zzd.equals(mediaMetadata.zzd);
    }

    public Calendar getDate(String str) {
        throwIfWrongType(str, 4);
        String string = this.zza.getString(str);
        if (string != null) {
            return com.google.android.gms.cast.internal.media.zza.zzd(string);
        }
        return null;
    }

    public String getDateAsString(String str) {
        throwIfWrongType(str, 4);
        return this.zza.getString(str);
    }

    public double getDouble(String str) {
        throwIfWrongType(str, 3);
        return this.zza.getDouble(str);
    }

    public List<b> getImages() {
        return this.zzd;
    }

    public int getInt(String str) {
        throwIfWrongType(str, 2);
        return this.zza.getInt(str);
    }

    public int getMediaType() {
        return this.zze;
    }

    public String getString(String str) {
        throwIfWrongType(str, 1);
        return this.zza.getString(str);
    }

    public long getTimeMillis(String str) {
        throwIfWrongType(str, 5);
        return this.zza.getLong(str);
    }

    public Writer getWriter() {
        return this.zzf;
    }

    public boolean hasImages() {
        List list = this.zzd;
        return (list == null || list.isEmpty()) ? false : true;
    }

    public int hashCode() {
        Bundle bundle = this.zza;
        int iHashCode = 17;
        if (bundle != null) {
            Iterator<String> it = bundle.keySet().iterator();
            while (it.hasNext()) {
                Object obj = bundle.get(it.next());
                iHashCode = (iHashCode * 31) + (obj != null ? obj.hashCode() : 0);
            }
        }
        return this.zzd.hashCode() + (iHashCode * 31);
    }

    public Set<String> keySet() {
        return this.zza.keySet();
    }

    public void putDate(String str, Calendar calendar) {
        throwIfWrongType(str, 4);
        this.zza.putString(str, com.google.android.gms.cast.internal.media.zza.zzc(calendar));
    }

    public void putDouble(String str, double d4) {
        throwIfWrongType(str, 3);
        this.zza.putDouble(str, d4);
    }

    public void putInt(String str, int i10) {
        throwIfWrongType(str, 2);
        this.zza.putInt(str, i10);
    }

    public void putString(String str, String str2) {
        throwIfWrongType(str, 1);
        this.zza.putString(str, str2);
    }

    public void putTimeMillis(String str, long j10) {
        throwIfWrongType(str, 5);
        this.zza.putLong(str, j10);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        int iM0 = a.a.m0(20293, parcel);
        a.a.k0(parcel, 2, getImages(), false);
        a.a.Z(parcel, 3, this.zza, false);
        int mediaType = getMediaType();
        a.a.l0(parcel, 4, 4);
        parcel.writeInt(mediaType);
        a.a.n0(iM0, parcel);
    }

    public final JSONObject zza() {
        zzbu zzbuVar;
        String strZzb;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("metadataType", this.zze);
        } catch (JSONException unused) {
        }
        JSONArray jSONArrayZzb = com.google.android.gms.cast.internal.media.zza.zzb(this.zzd);
        if (jSONArrayZzb.length() != 0) {
            try {
                jSONObject.put("images", jSONArrayZzb);
            } catch (JSONException unused2) {
            }
        }
        ArrayList<String> arrayList = new ArrayList();
        int i10 = this.zze;
        if (i10 == 0) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i10 == 1) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i10 == 2) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE);
        } else if (i10 == 3) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ARTIST, KEY_ALBUM_TITLE, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE);
        } else if (i10 == 4) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE);
        } else if (i10 == 5) {
            Collections.addAll(arrayList, KEY_CHAPTER_TITLE, KEY_CHAPTER_NUMBER, KEY_TITLE, KEY_BOOK_TITLE, KEY_SUBTITLE);
        }
        Collections.addAll(arrayList, KEY_SECTION_DURATION, KEY_SECTION_START_TIME_IN_MEDIA, KEY_SECTION_START_ABSOLUTE_TIME, KEY_SECTION_START_TIME_IN_CONTAINER, KEY_QUEUE_ITEM_ID);
        try {
            for (String str : arrayList) {
                if (str != null) {
                    Bundle bundle = this.zza;
                    if (bundle.containsKey(str) && (strZzb = (zzbuVar = zzc).zzb(str)) != null) {
                        int iZzd = zzbuVar.zzd(str);
                        if (iZzd != 1) {
                            if (iZzd == 2) {
                                jSONObject.put(strZzb, bundle.getInt(str));
                            } else if (iZzd == 3) {
                                jSONObject.put(strZzb, bundle.getDouble(str));
                            } else if (iZzd != 4) {
                                if (iZzd == 5) {
                                    jSONObject.put(strZzb, CastUtils.millisecToSec(bundle.getLong(str)));
                                }
                            }
                        }
                        jSONObject.put(strZzb, bundle.getString(str));
                    }
                }
            }
            Bundle bundle2 = this.zza;
            for (String str2 : bundle2.keySet()) {
                if (!str2.startsWith("com.google.")) {
                    Object obj = bundle2.get(str2);
                    if (obj instanceof String) {
                        jSONObject.put(str2, obj);
                    } else if (obj instanceof Integer) {
                        jSONObject.put(str2, obj);
                    } else if (obj instanceof Double) {
                        jSONObject.put(str2, obj);
                    }
                }
            }
        } catch (JSONException unused3) {
        }
        return jSONObject;
    }

    public final void zzb(JSONObject jSONObject) {
        clear();
        this.zze = 0;
        try {
            this.zze = jSONObject.getInt("metadataType");
        } catch (JSONException unused) {
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("images");
        if (jSONArrayOptJSONArray != null) {
            com.google.android.gms.cast.internal.media.zza.zza(this.zzd, jSONArrayOptJSONArray);
        }
        ArrayList arrayList = new ArrayList();
        int i10 = this.zze;
        if (i10 == 0) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ARTIST, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i10 == 1) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_STUDIO, KEY_SUBTITLE, KEY_RELEASE_DATE);
        } else if (i10 == 2) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_SERIES_TITLE, KEY_SEASON_NUMBER, KEY_EPISODE_NUMBER, KEY_BROADCAST_DATE);
        } else if (i10 == 3) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ALBUM_TITLE, KEY_ARTIST, KEY_ALBUM_ARTIST, KEY_COMPOSER, KEY_TRACK_NUMBER, KEY_DISC_NUMBER, KEY_RELEASE_DATE);
        } else if (i10 == 4) {
            Collections.addAll(arrayList, KEY_TITLE, KEY_ARTIST, KEY_LOCATION_NAME, KEY_LOCATION_LATITUDE, KEY_LOCATION_LONGITUDE, KEY_WIDTH, KEY_HEIGHT, KEY_CREATION_DATE);
        } else if (i10 == 5) {
            Collections.addAll(arrayList, KEY_CHAPTER_TITLE, KEY_CHAPTER_NUMBER, KEY_TITLE, KEY_BOOK_TITLE, KEY_SUBTITLE);
        }
        Collections.addAll(arrayList, KEY_SECTION_DURATION, KEY_SECTION_START_TIME_IN_MEDIA, KEY_SECTION_START_ABSOLUTE_TIME, KEY_SECTION_START_TIME_IN_CONTAINER, KEY_QUEUE_ITEM_ID);
        HashSet hashSet = new HashSet(arrayList);
        try {
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next != null && !"metadataType".equals(next)) {
                    zzbu zzbuVar = zzc;
                    String strZzc = zzbuVar.zzc(next);
                    if (strZzc == null) {
                        Object obj = jSONObject.get(next);
                        if (obj instanceof String) {
                            this.zza.putString(next, (String) obj);
                        } else if (obj instanceof Integer) {
                            this.zza.putInt(next, ((Integer) obj).intValue());
                        } else if (obj instanceof Double) {
                            this.zza.putDouble(next, ((Double) obj).doubleValue());
                        }
                    } else if (hashSet.contains(strZzc)) {
                        try {
                            Object obj2 = jSONObject.get(next);
                            if (obj2 != null) {
                                int iZzd = zzbuVar.zzd(strZzc);
                                if (iZzd != 1) {
                                    if (iZzd != 2) {
                                        if (iZzd == 3) {
                                            double dOptDouble = jSONObject.optDouble(next);
                                            if (!Double.isNaN(dOptDouble)) {
                                                this.zza.putDouble(strZzc, dOptDouble);
                                            }
                                        } else if (iZzd != 4) {
                                            if (iZzd == 5) {
                                                this.zza.putLong(strZzc, CastUtils.secToMillisec(jSONObject.optLong(next)));
                                            }
                                        } else if (obj2 instanceof String) {
                                            String str = (String) obj2;
                                            if (com.google.android.gms.cast.internal.media.zza.zzd(str) != null) {
                                                this.zza.putString(strZzc, str);
                                            }
                                        }
                                    } else if (obj2 instanceof Integer) {
                                        this.zza.putInt(strZzc, ((Integer) obj2).intValue());
                                    }
                                } else if (obj2 instanceof String) {
                                    this.zza.putString(strZzc, (String) obj2);
                                }
                            }
                        } catch (JSONException unused2) {
                        }
                    }
                }
            }
        } catch (JSONException unused3) {
        }
    }

    public final /* synthetic */ void zzc(int i10) {
        this.zze = i10;
    }

    public MediaMetadata() {
        this(0);
    }

    public MediaMetadata(int i10) {
        this(new ArrayList(), new Bundle(), i10);
    }
}
