package com.arflix.tv.data.api;

import android.content.Emoji;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.exoplayer.MediaPeriodQueue;
import com.google.android.gms.cast.MediaTrack;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.n0;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.jvm.internal.p;
import kotlin.text.k;
import kotlin.text.m;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0015\b\u0087\b\u0018\u00002\u00020\u0001B¯\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\u0006\u0010(\u001a\u00020\u0003J\u0006\u0010)\u001a\u00020\u0003J\u0006\u0010*\u001a\u00020\u0003J\u0006\u0010+\u001a\u00020\u0003J\u0010\u0010,\u001a\u00020\u00032\u0006\u0010-\u001a\u00020.H\u0002J\r\u0010/\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u001dJ\u0006\u00100\u001a\u000201J\b\u00102\u001a\u0004\u0018\u00010\u0003J\u0006\u00103\u001a\u000201J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rHÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u0011\u0010>\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011HÆ\u0003J\u0011\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011HÆ\u0003J¶\u0001\u0010@\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00112\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u0010AJ\u0014\u0010B\u001a\u0002012\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010D\u001a\u00020\tHÖ\u0081\u0004J\n\u0010E\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R$\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0019\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0019\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&¨\u0006F"}, d2 = {"Lcom/arflix/tv/data/api/StremioStream;", "", ContentDisposition.Parameters.Name, "", LinkHeader.Parameters.Title, MediaTrack.ROLE_DESCRIPTION, "url", "infoHash", "fileIdx", "", "ytId", "externalUrl", "headers", "", "behaviorHints", "Lcom/arflix/tv/data/api/StreamBehaviorHints;", "sources", "", "subtitles", "Lcom/arflix/tv/data/api/StremioSubtitle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/arflix/tv/data/api/StreamBehaviorHints;Ljava/util/List;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getTitle", "getDescription", "getUrl", "getInfoHash", "getFileIdx", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getYtId", "getExternalUrl", "getHeaders", "()Ljava/util/Map;", "getBehaviorHints", "()Lcom/arflix/tv/data/api/StreamBehaviorHints;", "getSources", "()Ljava/util/List;", "getSubtitles", "getQuality", "getSourceName", "getTorrentName", "getSize", "formatBytes", "bytes", "", "getSeeders", "hasPlayableLink", "", "getStreamUrl", "isDirectStreamingUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lcom/arflix/tv/data/api/StreamBehaviorHints;Ljava/util/List;Ljava/util/List;)Lcom/arflix/tv/data/api/StremioStream;", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StremioStream {
    public static final int $stable = 8;
    private final StreamBehaviorHints behaviorHints;
    private final String description;
    private final String externalUrl;
    private final Integer fileIdx;

    @SerializedName("headers")
    private final Map<String, String> headers;
    private final String infoHash;
    private final String name;
    private final List<String> sources;
    private final List<StremioSubtitle> subtitles;
    private final String title;
    private final String url;
    private final String ytId;

    public StremioStream() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, 4095, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StremioStream copy$default(StremioStream stremioStream, String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, Map map, StreamBehaviorHints streamBehaviorHints, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = stremioStream.name;
        }
        if ((i10 & 2) != 0) {
            str2 = stremioStream.title;
        }
        if ((i10 & 4) != 0) {
            str3 = stremioStream.description;
        }
        if ((i10 & 8) != 0) {
            str4 = stremioStream.url;
        }
        if ((i10 & 16) != 0) {
            str5 = stremioStream.infoHash;
        }
        if ((i10 & 32) != 0) {
            num = stremioStream.fileIdx;
        }
        if ((i10 & 64) != 0) {
            str6 = stremioStream.ytId;
        }
        if ((i10 & 128) != 0) {
            str7 = stremioStream.externalUrl;
        }
        if ((i10 & 256) != 0) {
            map = stremioStream.headers;
        }
        if ((i10 & 512) != 0) {
            streamBehaviorHints = stremioStream.behaviorHints;
        }
        if ((i10 & 1024) != 0) {
            list = stremioStream.sources;
        }
        if ((i10 & 2048) != 0) {
            list2 = stremioStream.subtitles;
        }
        List list3 = list;
        List list4 = list2;
        Map map2 = map;
        StreamBehaviorHints streamBehaviorHints2 = streamBehaviorHints;
        String str8 = str6;
        String str9 = str7;
        String str10 = str5;
        Integer num2 = num;
        return stremioStream.copy(str, str2, str3, str4, str10, num2, str8, str9, map2, streamBehaviorHints2, list3, list4);
    }

    private final String formatBytes(long bytes) {
        if (bytes >= MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US) {
            return String.format("%.2f TB", Arrays.copyOf(new Object[]{Double.valueOf(bytes / 1.0E12d)}, 1));
        }
        if (bytes >= C.NANOS_PER_SECOND) {
            return String.format("%.2f GB", Arrays.copyOf(new Object[]{Double.valueOf(bytes / 1.0E9d)}, 1));
        }
        if (bytes >= 1000000) {
            return String.format("%.1f MB", Arrays.copyOf(new Object[]{Double.valueOf(bytes / 1000000.0d)}, 1));
        }
        if (bytes >= 1000) {
            return String.format("%.0f KB", Arrays.copyOf(new Object[]{Double.valueOf(bytes / 1000.0d)}, 1));
        }
        return bytes + " B";
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final StreamBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
    }

    public final List<String> component11() {
        return this.sources;
    }

    public final List<StremioSubtitle> component12() {
        return this.subtitles;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getInfoHash() {
        return this.infoHash;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getFileIdx() {
        return this.fileIdx;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getYtId() {
        return this.ytId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getExternalUrl() {
        return this.externalUrl;
    }

    public final Map<String, String> component9() {
        return this.headers;
    }

    public final StremioStream copy(String name, String title, String description, String url, String infoHash, Integer fileIdx, String ytId, String externalUrl, Map<String, String> headers, StreamBehaviorHints behaviorHints, List<String> sources, List<StremioSubtitle> subtitles) {
        return new StremioStream(name, title, description, url, infoHash, fileIdx, ytId, externalUrl, headers, behaviorHints, sources, subtitles);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StremioStream)) {
            return false;
        }
        StremioStream stremioStream = (StremioStream) other;
        return p.a(this.name, stremioStream.name) && p.a(this.title, stremioStream.title) && p.a(this.description, stremioStream.description) && p.a(this.url, stremioStream.url) && p.a(this.infoHash, stremioStream.infoHash) && p.a(this.fileIdx, stremioStream.fileIdx) && p.a(this.ytId, stremioStream.ytId) && p.a(this.externalUrl, stremioStream.externalUrl) && p.a(this.headers, stremioStream.headers) && p.a(this.behaviorHints, stremioStream.behaviorHints) && p.a(this.sources, stremioStream.sources) && p.a(this.subtitles, stremioStream.subtitles);
    }

    public final StreamBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getExternalUrl() {
        return this.externalUrl;
    }

    public final Integer getFileIdx() {
        return this.fileIdx;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final String getInfoHash() {
        return this.infoHash;
    }

    public final String getName() {
        return this.name;
    }

    public final String getQuality() {
        String string;
        String strU0 = x.u0(r.X(new String[]{this.name, this.title, this.description}), " ", null, null, null, 62);
        if (o.T(strU0, "2160p", true) || o.T(strU0, "4K", true)) {
            return "4K";
        }
        if (o.T(strU0, "1080p", true)) {
            return "1080p";
        }
        if (o.T(strU0, "720p", true)) {
            return "720p";
        }
        if (o.T(strU0, "480p", true)) {
            return "480p";
        }
        String str = this.title;
        if (str == null && (str = this.name) == null) {
            str = "";
        }
        String str2 = (String) x.p0(1, o.y0(str, new String[]{"\n"}, 0, 6));
        if (str2 == null || (string = o.L0(str2).toString()) == null) {
            return "Unknown";
        }
        if (o.h0(string)) {
            string = null;
        }
        return string == null ? "Unknown" : string;
    }

    public final Integer getSeeders() {
        List listB;
        String str;
        m seeder_regex = StreamApiRegexes.INSTANCE.getSEEDER_REGEX();
        String str2 = this.title;
        if (str2 == null) {
            str2 = "";
        }
        k kVarB = m.b(seeder_regex, str2);
        if (kVarB == null || (listB = kVarB.b()) == null || (str = (String) x.p0(1, listB)) == null) {
            return null;
        }
        return u.R(str);
    }

    public final String getSize() {
        List listB;
        String str;
        Long videoSize;
        StreamBehaviorHints streamBehaviorHints = this.behaviorHints;
        if (streamBehaviorHints != null && (videoSize = streamBehaviorHints.getVideoSize()) != null) {
            long jLongValue = videoSize.longValue();
            if (jLongValue > 0) {
                return formatBytes(jLongValue);
            }
        }
        for (String str2 : r.X(new String[]{this.title, this.name, this.description})) {
            StreamApiRegexes streamApiRegexes = StreamApiRegexes.INSTANCE;
            k kVarB = m.b(streamApiRegexes.getEMOJI_SIZE_REGEX(), str2);
            if (kVarB != null && (listB = kVarB.b()) != null && (str = (String) x.p0(1, listB)) != null) {
                return str;
            }
            k kVarB2 = m.b(streamApiRegexes.getPLAIN_SIZE_REGEX(), str2);
            if (kVarB2 != null) {
                return androidx.compose.foundation.c.t((String) ((n0) kVarB2.b()).get(1), " ", ((String) ((n0) kVarB2.b()).get(2)).toUpperCase(Locale.ROOT));
            }
        }
        return "";
    }

    public final String getSourceName() {
        String string;
        String str = this.title;
        if (str == null && (str = this.name) == null) {
            str = "";
        }
        String str2 = (String) x.p0(0, o.y0(str, new String[]{"\n"}, 0, 6));
        return (str2 == null || (string = o.L0(str2).toString()) == null) ? "Unknown" : string;
    }

    public final List<String> getSources() {
        return this.sources;
    }

    public final String getStreamUrl() {
        String str = this.url;
        return str == null ? this.externalUrl : str;
    }

    public final List<StremioSubtitle> getSubtitles() {
        return this.subtitles;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getTorrentName() {
        String string;
        String string2;
        String filename;
        StreamBehaviorHints streamBehaviorHints = this.behaviorHints;
        String str = null;
        if (streamBehaviorHints != null && (filename = streamBehaviorHints.getFilename()) != null) {
            if (o.h0(filename)) {
                filename = null;
            }
            if (filename != null) {
                return filename;
            }
        }
        String str2 = this.description;
        if (str2 != null) {
            String str3 = (String) x.o0(o.y0(str2, new String[]{"\n"}, 0, 6));
            if (str3 == null || (string2 = o.L0(str3).toString()) == null) {
                string2 = "";
            }
            if (!o.h0(string2) && (o.T(string2, ".mkv", true) || o.T(string2, ".mp4", true) || o.T(string2, ".avi", true) || StreamApiRegexes.INSTANCE.getQUALITY_TAGS_REGEX().e(string2))) {
                return string2;
            }
        }
        String str4 = this.title;
        String str5 = (str4 == null && (str4 = this.name) == null) ? "" : str4;
        List listY0 = o.y0(str5, new String[]{"\n"}, 0, 6);
        int size = listY0.size() - 1;
        if (size >= 0) {
            while (true) {
                int i10 = size - 1;
                String string3 = o.L0((String) listY0.get(size)).toString();
                if (!o.h0(string3) && o.T(string3, ".", false) && !o.T(string3, "👤", false) && !o.T(string3, Emoji.FLOPPY, false) && !o.T(string3, "⚙️", false) && !o.T(string3, "🔗", false)) {
                    return string3;
                }
                if (i10 < 0) {
                    break;
                }
                size = i10;
            }
        }
        String str6 = (String) x.p0(2, listY0);
        if (str6 != null) {
            if (o.h0(str6)) {
                str6 = null;
            }
            if (str6 != null && (string = o.L0(str6).toString()) != null) {
                return string;
            }
        }
        String str7 = (String) x.p0(1, listY0);
        if (str7 != null) {
            if (!o.h0(str7) && !o.T(str7, "👤", false)) {
                str = str7;
            }
            if (str != null) {
                return o.L0(str).toString();
            }
        }
        String string4 = o.L0(str5).toString();
        return o.h0(string4) ? "Unknown" : string4;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getYtId() {
        return this.ytId;
    }

    public final boolean hasPlayableLink() {
        return (this.url == null && this.infoHash == null && this.ytId == null && this.externalUrl == null) ? false : true;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.title;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.description;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.url;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.infoHash;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Integer num = this.fileIdx;
        int iHashCode6 = (iHashCode5 + (num == null ? 0 : num.hashCode())) * 31;
        String str6 = this.ytId;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.externalUrl;
        int iHashCode8 = (iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Map<String, String> map = this.headers;
        int iHashCode9 = (iHashCode8 + (map == null ? 0 : map.hashCode())) * 31;
        StreamBehaviorHints streamBehaviorHints = this.behaviorHints;
        int iHashCode10 = (iHashCode9 + (streamBehaviorHints == null ? 0 : streamBehaviorHints.hashCode())) * 31;
        List<String> list = this.sources;
        int iHashCode11 = (iHashCode10 + (list == null ? 0 : list.hashCode())) * 31;
        List<StremioSubtitle> list2 = this.subtitles;
        return iHashCode11 + (list2 != null ? list2.hashCode() : 0);
    }

    public final boolean isDirectStreamingUrl() {
        String streamUrl = getStreamUrl();
        if (streamUrl == null) {
            return false;
        }
        List listE = t7.a.E(".mp4", ".mkv", ".webm", ".avi", ".mov", ".m3u8", ".mpd", "googlevideo.com", "youtube.com", "youtu.be", "cloudflare", "akamaized", "fastly");
        if (listE.isEmpty()) {
            return false;
        }
        Iterator it = listE.iterator();
        while (it.hasNext()) {
            if (o.T(streamUrl, (String) it.next(), true)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String str = this.name;
        String str2 = this.title;
        String str3 = this.description;
        String str4 = this.url;
        String str5 = this.infoHash;
        Integer num = this.fileIdx;
        String str6 = this.ytId;
        String str7 = this.externalUrl;
        Map<String, String> map = this.headers;
        StreamBehaviorHints streamBehaviorHints = this.behaviorHints;
        List<String> list = this.sources;
        List<StremioSubtitle> list2 = this.subtitles;
        StringBuilder sbR = a2.r("StremioStream(name=", str, ", title=", str2, ", description=");
        y.a.i(sbR, str3, ", url=", str4, ", infoHash=");
        sbR.append(str5);
        sbR.append(", fileIdx=");
        sbR.append(num);
        sbR.append(", ytId=");
        y.a.i(sbR, str6, ", externalUrl=", str7, ", headers=");
        sbR.append(map);
        sbR.append(", behaviorHints=");
        sbR.append(streamBehaviorHints);
        sbR.append(", sources=");
        sbR.append(list);
        sbR.append(", subtitles=");
        sbR.append(list2);
        sbR.append(")");
        return sbR.toString();
    }

    public StremioStream(String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, Map<String, String> map, StreamBehaviorHints streamBehaviorHints, List<String> list, List<StremioSubtitle> list2) {
        this.name = str;
        this.title = str2;
        this.description = str3;
        this.url = str4;
        this.infoHash = str5;
        this.fileIdx = num;
        this.ytId = str6;
        this.externalUrl = str7;
        this.headers = map;
        this.behaviorHints = streamBehaviorHints;
        this.sources = list;
        this.subtitles = list2;
    }

    public /* synthetic */ StremioStream(String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, Map map, StreamBehaviorHints streamBehaviorHints, List list, List list2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : str5, (i10 & 32) != 0 ? null : num, (i10 & 64) != 0 ? null : str6, (i10 & 128) != 0 ? null : str7, (i10 & 256) != 0 ? null : map, (i10 & 512) != 0 ? null : streamBehaviorHints, (i10 & 1024) != 0 ? null : list, (i10 & 2048) != 0 ? null : list2);
    }
}
