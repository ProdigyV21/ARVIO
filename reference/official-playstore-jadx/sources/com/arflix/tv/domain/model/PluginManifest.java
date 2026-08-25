package com.arflix.tv.domain.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import com.google.android.gms.cast.MediaTrack;
import com.squareup.moshi.m;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@m(generateAdapter = true)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0004\b\u000b\u0010\fJ\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J\u0011\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003JY\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013¨\u0006#"}, d2 = {"Lcom/arflix/tv/domain/model/PluginManifest;", "", ContentDisposition.Parameters.Name, "", "version", MediaTrack.ROLE_DESCRIPTION, "author", "scrapers", "", "Lcom/arflix/tv/domain/model/ScraperManifestInfo;", "providers", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getVersion", "getDescription", "getAuthor", "getScrapers", "()Ljava/util/List;", "getProviders", "getActiveScrapers", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PluginManifest {
    public static final int $stable = 0;
    private final String author;
    private final String description;
    private final String name;
    private final List<ScraperManifestInfo> providers;
    private final List<ScraperManifestInfo> scrapers;
    private final String version;

    public PluginManifest(String str, String str2, String str3, String str4, List<ScraperManifestInfo> list, List<ScraperManifestInfo> list2) {
        this.name = str;
        this.version = str2;
        this.description = str3;
        this.author = str4;
        this.scrapers = list;
        this.providers = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PluginManifest copy$default(PluginManifest pluginManifest, String str, String str2, String str3, String str4, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = pluginManifest.name;
        }
        if ((i10 & 2) != 0) {
            str2 = pluginManifest.version;
        }
        if ((i10 & 4) != 0) {
            str3 = pluginManifest.description;
        }
        if ((i10 & 8) != 0) {
            str4 = pluginManifest.author;
        }
        if ((i10 & 16) != 0) {
            list = pluginManifest.scrapers;
        }
        if ((i10 & 32) != 0) {
            list2 = pluginManifest.providers;
        }
        List list3 = list;
        List list4 = list2;
        return pluginManifest.copy(str, str2, str3, str4, list3, list4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAuthor() {
        return this.author;
    }

    public final List<ScraperManifestInfo> component5() {
        return this.scrapers;
    }

    public final List<ScraperManifestInfo> component6() {
        return this.providers;
    }

    public final PluginManifest copy(String name, String version, String description, String author, List<ScraperManifestInfo> scrapers, List<ScraperManifestInfo> providers) {
        return new PluginManifest(name, version, description, author, scrapers, providers);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PluginManifest)) {
            return false;
        }
        PluginManifest pluginManifest = (PluginManifest) other;
        return p.a(this.name, pluginManifest.name) && p.a(this.version, pluginManifest.version) && p.a(this.description, pluginManifest.description) && p.a(this.author, pluginManifest.author) && p.a(this.scrapers, pluginManifest.scrapers) && p.a(this.providers, pluginManifest.providers);
    }

    public final List<ScraperManifestInfo> getActiveScrapers() {
        List<ScraperManifestInfo> list = this.scrapers;
        if (list != null) {
            return list;
        }
        List<ScraperManifestInfo> list2 = this.providers;
        return list2 == null ? z.f19728i : list2;
    }

    public final String getAuthor() {
        return this.author;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getName() {
        return this.name;
    }

    public final List<ScraperManifestInfo> getProviders() {
        return this.providers;
    }

    public final List<ScraperManifestInfo> getScrapers() {
        return this.scrapers;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int iC = c.c(this.name.hashCode() * 31, 31, this.version);
        String str = this.description;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.author;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<ScraperManifestInfo> list = this.scrapers;
        int iHashCode3 = (iHashCode2 + (list == null ? 0 : list.hashCode())) * 31;
        List<ScraperManifestInfo> list2 = this.providers;
        return iHashCode3 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        String str = this.name;
        String str2 = this.version;
        String str3 = this.description;
        String str4 = this.author;
        List<ScraperManifestInfo> list = this.scrapers;
        List<ScraperManifestInfo> list2 = this.providers;
        StringBuilder sbR = a2.r("PluginManifest(name=", str, ", version=", str2, ", description=");
        a.i(sbR, str3, ", author=", str4, ", scrapers=");
        sbR.append(list);
        sbR.append(", providers=");
        sbR.append(list2);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ PluginManifest(String str, String str2, String str3, String str4, List list, List list2, int i10, h hVar) {
        this(str, str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : list, (i10 & 32) != 0 ? null : list2);
    }
}
