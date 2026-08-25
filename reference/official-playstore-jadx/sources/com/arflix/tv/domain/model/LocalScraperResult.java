package com.arflix.tv.domain.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u009b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010+\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010,\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010HÆ\u0003J¦\u0001\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u00100J\u0014\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00104\u001a\u00020\fHÖ\u0081\u0004J\n\u00105\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0015\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001f\u0010\u001dR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u001f\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00066"}, d2 = {"Lcom/arflix/tv/domain/model/LocalScraperResult;", "", LinkHeader.Parameters.Title, "", ContentDisposition.Parameters.Name, "url", "quality", ContentDisposition.Parameters.Size, "language", "provider", LinkHeader.Parameters.Type, "seeders", "", "peers", "infoHash", "headers", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Map;)V", "getTitle", "()Ljava/lang/String;", "getName", "getUrl", "getQuality", "getSize", "getLanguage", "getProvider", "getType", "getSeeders", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPeers", "getInfoHash", "getHeaders", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Map;)Lcom/arflix/tv/domain/model/LocalScraperResult;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LocalScraperResult {
    public static final int $stable = 0;
    private final Map<String, String> headers;
    private final String infoHash;
    private final String language;
    private final String name;
    private final Integer peers;
    private final String provider;
    private final String quality;
    private final Integer seeders;
    private final String size;
    private final String title;
    private final String type;
    private final String url;

    public LocalScraperResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Integer num, Integer num2, String str9, Map<String, String> map) {
        this.title = str;
        this.name = str2;
        this.url = str3;
        this.quality = str4;
        this.size = str5;
        this.language = str6;
        this.provider = str7;
        this.type = str8;
        this.seeders = num;
        this.peers = num2;
        this.infoHash = str9;
        this.headers = map;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LocalScraperResult copy$default(LocalScraperResult localScraperResult, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Integer num, Integer num2, String str9, Map map, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = localScraperResult.title;
        }
        if ((i10 & 2) != 0) {
            str2 = localScraperResult.name;
        }
        if ((i10 & 4) != 0) {
            str3 = localScraperResult.url;
        }
        if ((i10 & 8) != 0) {
            str4 = localScraperResult.quality;
        }
        if ((i10 & 16) != 0) {
            str5 = localScraperResult.size;
        }
        if ((i10 & 32) != 0) {
            str6 = localScraperResult.language;
        }
        if ((i10 & 64) != 0) {
            str7 = localScraperResult.provider;
        }
        if ((i10 & 128) != 0) {
            str8 = localScraperResult.type;
        }
        if ((i10 & 256) != 0) {
            num = localScraperResult.seeders;
        }
        if ((i10 & 512) != 0) {
            num2 = localScraperResult.peers;
        }
        if ((i10 & 1024) != 0) {
            str9 = localScraperResult.infoHash;
        }
        if ((i10 & 2048) != 0) {
            map = localScraperResult.headers;
        }
        String str10 = str9;
        Map map2 = map;
        Integer num3 = num;
        Integer num4 = num2;
        String str11 = str7;
        String str12 = str8;
        String str13 = str5;
        String str14 = str6;
        return localScraperResult.copy(str, str2, str3, str4, str13, str14, str11, str12, num3, num4, str10, map2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getPeers() {
        return this.peers;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getInfoHash() {
        return this.infoHash;
    }

    public final Map<String, String> component12() {
        return this.headers;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getQuality() {
        return this.quality;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getProvider() {
        return this.provider;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getSeeders() {
        return this.seeders;
    }

    public final LocalScraperResult copy(String title, String name, String url, String quality, String size, String language, String provider, String type, Integer seeders, Integer peers, String infoHash, Map<String, String> headers) {
        return new LocalScraperResult(title, name, url, quality, size, language, provider, type, seeders, peers, infoHash, headers);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocalScraperResult)) {
            return false;
        }
        LocalScraperResult localScraperResult = (LocalScraperResult) other;
        return p.a(this.title, localScraperResult.title) && p.a(this.name, localScraperResult.name) && p.a(this.url, localScraperResult.url) && p.a(this.quality, localScraperResult.quality) && p.a(this.size, localScraperResult.size) && p.a(this.language, localScraperResult.language) && p.a(this.provider, localScraperResult.provider) && p.a(this.type, localScraperResult.type) && p.a(this.seeders, localScraperResult.seeders) && p.a(this.peers, localScraperResult.peers) && p.a(this.infoHash, localScraperResult.infoHash) && p.a(this.headers, localScraperResult.headers);
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final String getInfoHash() {
        return this.infoHash;
    }

    public final String getLanguage() {
        return this.language;
    }

    public final String getName() {
        return this.name;
    }

    public final Integer getPeers() {
        return this.peers;
    }

    public final String getProvider() {
        return this.provider;
    }

    public final String getQuality() {
        return this.quality;
    }

    public final Integer getSeeders() {
        return this.seeders;
    }

    public final String getSize() {
        return this.size;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int iHashCode = this.title.hashCode() * 31;
        String str = this.name;
        int iC = c.c((iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31, this.url);
        String str2 = this.quality;
        int iHashCode2 = (iC + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.size;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.language;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.provider;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.type;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Integer num = this.seeders;
        int iHashCode7 = (iHashCode6 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.peers;
        int iHashCode8 = (iHashCode7 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str7 = this.infoHash;
        int iHashCode9 = (iHashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
        Map<String, String> map = this.headers;
        return iHashCode9 + (map != null ? map.hashCode() : 0);
    }

    public String toString() {
        String str = this.title;
        String str2 = this.name;
        String str3 = this.url;
        String str4 = this.quality;
        String str5 = this.size;
        String str6 = this.language;
        String str7 = this.provider;
        String str8 = this.type;
        Integer num = this.seeders;
        Integer num2 = this.peers;
        String str9 = this.infoHash;
        Map<String, String> map = this.headers;
        StringBuilder sbR = a2.r("LocalScraperResult(title=", str, ", name=", str2, ", url=");
        a.i(sbR, str3, ", quality=", str4, ", size=");
        a.i(sbR, str5, ", language=", str6, ", provider=");
        a.i(sbR, str7, ", type=", str8, ", seeders=");
        a2.z(sbR, num, ", peers=", num2, ", infoHash=");
        sbR.append(str9);
        sbR.append(", headers=");
        sbR.append(map);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ LocalScraperResult(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Integer num, Integer num2, String str9, Map map, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2, str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : str5, (i10 & 32) != 0 ? null : str6, (i10 & 64) != 0 ? null : str7, (i10 & 128) != 0 ? null : str8, (i10 & 256) != 0 ? null : num, (i10 & 512) != 0 ? null : num2, (i10 & 1024) != 0 ? null : str9, (i10 & 2048) != 0 ? null : map);
    }
}
