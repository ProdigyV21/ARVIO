package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003JK\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/repository/IptvPlaylistEntry;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "m3uUrl", "epgUrl", "enabled", "", "epgUrls", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;)V", "getId", "()Ljava/lang/String;", "getName", "getM3uUrl", "getEpgUrl", "getEnabled", "()Z", "getEpgUrls", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class IptvPlaylistEntry {
    public static final int $stable = 0;
    private final boolean enabled;
    private final String epgUrl;
    private final List<String> epgUrls;
    private final String id;
    private final String m3uUrl;
    private final String name;

    public IptvPlaylistEntry(String str, String str2, String str3, String str4, boolean z, List<String> list) {
        this.id = str;
        this.name = str2;
        this.m3uUrl = str3;
        this.epgUrl = str4;
        this.enabled = z;
        this.epgUrls = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IptvPlaylistEntry copy$default(IptvPlaylistEntry iptvPlaylistEntry, String str, String str2, String str3, String str4, boolean z, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = iptvPlaylistEntry.id;
        }
        if ((i10 & 2) != 0) {
            str2 = iptvPlaylistEntry.name;
        }
        if ((i10 & 4) != 0) {
            str3 = iptvPlaylistEntry.m3uUrl;
        }
        if ((i10 & 8) != 0) {
            str4 = iptvPlaylistEntry.epgUrl;
        }
        if ((i10 & 16) != 0) {
            z = iptvPlaylistEntry.enabled;
        }
        if ((i10 & 32) != 0) {
            list = iptvPlaylistEntry.epgUrls;
        }
        boolean z5 = z;
        List list2 = list;
        return iptvPlaylistEntry.copy(str, str2, str3, str4, z5, list2);
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
    public final String getM3uUrl() {
        return this.m3uUrl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getEpgUrl() {
        return this.epgUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    public final List<String> component6() {
        return this.epgUrls;
    }

    public final IptvPlaylistEntry copy(String id, String name, String m3uUrl, String epgUrl, boolean enabled, List<String> epgUrls) {
        return new IptvPlaylistEntry(id, name, m3uUrl, epgUrl, enabled, epgUrls);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IptvPlaylistEntry)) {
            return false;
        }
        IptvPlaylistEntry iptvPlaylistEntry = (IptvPlaylistEntry) other;
        return kotlin.jvm.internal.p.a(this.id, iptvPlaylistEntry.id) && kotlin.jvm.internal.p.a(this.name, iptvPlaylistEntry.name) && kotlin.jvm.internal.p.a(this.m3uUrl, iptvPlaylistEntry.m3uUrl) && kotlin.jvm.internal.p.a(this.epgUrl, iptvPlaylistEntry.epgUrl) && this.enabled == iptvPlaylistEntry.enabled && kotlin.jvm.internal.p.a(this.epgUrls, iptvPlaylistEntry.epgUrls);
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final String getEpgUrl() {
        return this.epgUrl;
    }

    public final List<String> getEpgUrls() {
        return this.epgUrls;
    }

    public final String getId() {
        return this.id;
    }

    public final String getM3uUrl() {
        return this.m3uUrl;
    }

    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return this.epgUrls.hashCode() + ((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.name), 31, this.m3uUrl), 31, this.epgUrl) + (this.enabled ? 1231 : 1237)) * 31);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.m3uUrl;
        String str4 = this.epgUrl;
        boolean z = this.enabled;
        List<String> list = this.epgUrls;
        StringBuilder sbR = a2.r("IptvPlaylistEntry(id=", str, ", name=", str2, ", m3uUrl=");
        y.a.i(sbR, str3, ", epgUrl=", str4, ", enabled=");
        sbR.append(z);
        sbR.append(", epgUrls=");
        sbR.append(list);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ IptvPlaylistEntry(String str, String str2, String str3, String str4, boolean z, List list, int i10, kotlin.jvm.internal.h hVar) {
        this(str, str2, str3, (i10 & 8) != 0 ? "" : str4, (i10 & 16) != 0 ? true : z, (i10 & 32) != 0 ? kotlin.collections.z.f19728i : list);
    }
}
