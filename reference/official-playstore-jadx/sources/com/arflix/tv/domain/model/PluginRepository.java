package com.arflix.tv.domain.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0087\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J\t\u0010$\u001a\u00020\fHÆ\u0003J\t\u0010%\u001a\u00020\u000eHÆ\u0003J[\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0014\u0010'\u001a\u00020\b2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010)\u001a\u00020\fHÖ\u0081\u0004J\n\u0010*\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006+"}, d2 = {"Lcom/arflix/tv/domain/model/PluginRepository;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "url", MediaTrack.ROLE_DESCRIPTION, "enabled", "", "lastUpdated", "", "scraperCount", "", LinkHeader.Parameters.Type, "Lcom/arflix/tv/domain/model/RepositoryType;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZJILcom/arflix/tv/domain/model/RepositoryType;)V", "getId", "()Ljava/lang/String;", "getName", "getUrl", "getDescription", "getEnabled", "()Z", "getLastUpdated", "()J", "getScraperCount", "()I", "getType", "()Lcom/arflix/tv/domain/model/RepositoryType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PluginRepository {
    public static final int $stable = 0;
    private final String description;
    private final boolean enabled;
    private final String id;
    private final long lastUpdated;
    private final String name;
    private final int scraperCount;
    private final RepositoryType type;
    private final String url;

    public PluginRepository(String str, String str2, String str3, String str4, boolean z, long j10, int i10, RepositoryType repositoryType) {
        this.id = str;
        this.name = str2;
        this.url = str3;
        this.description = str4;
        this.enabled = z;
        this.lastUpdated = j10;
        this.scraperCount = i10;
        this.type = repositoryType;
    }

    public static /* synthetic */ PluginRepository copy$default(PluginRepository pluginRepository, String str, String str2, String str3, String str4, boolean z, long j10, int i10, RepositoryType repositoryType, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = pluginRepository.id;
        }
        if ((i11 & 2) != 0) {
            str2 = pluginRepository.name;
        }
        if ((i11 & 4) != 0) {
            str3 = pluginRepository.url;
        }
        if ((i11 & 8) != 0) {
            str4 = pluginRepository.description;
        }
        if ((i11 & 16) != 0) {
            z = pluginRepository.enabled;
        }
        if ((i11 & 32) != 0) {
            j10 = pluginRepository.lastUpdated;
        }
        if ((i11 & 64) != 0) {
            i10 = pluginRepository.scraperCount;
        }
        if ((i11 & 128) != 0) {
            repositoryType = pluginRepository.type;
        }
        long j11 = j10;
        String str5 = str4;
        boolean z5 = z;
        String str6 = str3;
        return pluginRepository.copy(str, str2, str6, str5, z5, j11, i10, repositoryType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
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
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getLastUpdated() {
        return this.lastUpdated;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getScraperCount() {
        return this.scraperCount;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final RepositoryType getType() {
        return this.type;
    }

    public final PluginRepository copy(String id, String name, String url, String description, boolean enabled, long lastUpdated, int scraperCount, RepositoryType type) {
        return new PluginRepository(id, name, url, description, enabled, lastUpdated, scraperCount, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PluginRepository)) {
            return false;
        }
        PluginRepository pluginRepository = (PluginRepository) other;
        return p.a(this.id, pluginRepository.id) && p.a(this.name, pluginRepository.name) && p.a(this.url, pluginRepository.url) && p.a(this.description, pluginRepository.description) && this.enabled == pluginRepository.enabled && this.lastUpdated == pluginRepository.lastUpdated && this.scraperCount == pluginRepository.scraperCount && this.type == pluginRepository.type;
    }

    public final String getDescription() {
        return this.description;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final String getId() {
        return this.id;
    }

    public final long getLastUpdated() {
        return this.lastUpdated;
    }

    public final String getName() {
        return this.name;
    }

    public final int getScraperCount() {
        return this.scraperCount;
    }

    public final RepositoryType getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int iC = c.c(c.c(this.id.hashCode() * 31, 31, this.name), 31, this.url);
        String str = this.description;
        int iHashCode = (((iC + (str == null ? 0 : str.hashCode())) * 31) + (this.enabled ? 1231 : 1237)) * 31;
        long j10 = this.lastUpdated;
        return this.type.hashCode() + ((((iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31) + this.scraperCount) * 31);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.url;
        String str4 = this.description;
        boolean z = this.enabled;
        long j10 = this.lastUpdated;
        int i10 = this.scraperCount;
        RepositoryType repositoryType = this.type;
        StringBuilder sbR = a2.r("PluginRepository(id=", str, ", name=", str2, ", url=");
        a.i(sbR, str3, ", description=", str4, ", enabled=");
        sbR.append(z);
        sbR.append(", lastUpdated=");
        sbR.append(j10);
        sbR.append(", scraperCount=");
        sbR.append(i10);
        sbR.append(", type=");
        sbR.append(repositoryType);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ PluginRepository(String str, String str2, String str3, String str4, boolean z, long j10, int i10, RepositoryType repositoryType, int i11, h hVar) {
        this(str, str2, str3, (i11 & 8) != 0 ? null : str4, (i11 & 16) != 0 ? true : z, (i11 & 32) != 0 ? 0L : j10, (i11 & 64) != 0 ? 0 : i10, (i11 & 128) != 0 ? RepositoryType.NUVIO_JS : repositoryType);
    }
}
