package com.arflix.tv.domain.model;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import com.google.android.gms.cast.MediaTrack;
import com.squareup.moshi.m;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@m(generateAdapter = true)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003J\t\u0010)\u001a\u00020\u0007HÆ\u0003J\t\u0010*\u001a\u00020\u0007HÆ\u0003J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bHÆ\u0003J\u0011\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000bHÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010\"J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u009e\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00102J\u0014\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00106\u001a\u00020\u0007HÖ\u0081\u0004J\n\u00107\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0015¨\u00068"}, d2 = {"Lcom/arflix/tv/domain/model/ExternalPluginEntry;", "", ContentDisposition.Parameters.Name, "", "internalName", MediaTrack.ROLE_DESCRIPTION, "version", "", "apiVersion", "status", "authors", "", "tvTypes", "iconUrl", "url", "fileSize", "", "repositoryUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getInternalName", "getDescription", "getVersion", "()I", "getApiVersion", "getStatus", "getAuthors", "()Ljava/util/List;", "getTvTypes", "getIconUrl", "getUrl", "getFileSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRepositoryUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lcom/arflix/tv/domain/model/ExternalPluginEntry;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ExternalPluginEntry {
    public static final int $stable = 0;
    private final int apiVersion;
    private final List<String> authors;
    private final String description;
    private final Long fileSize;
    private final String iconUrl;
    private final String internalName;
    private final String name;
    private final String repositoryUrl;
    private final int status;
    private final List<String> tvTypes;
    private final String url;
    private final int version;

    public ExternalPluginEntry(String str, String str2, String str3, int i10, int i11, int i12, List<String> list, List<String> list2, String str4, String str5, Long l10, String str6) {
        this.name = str;
        this.internalName = str2;
        this.description = str3;
        this.version = i10;
        this.apiVersion = i11;
        this.status = i12;
        this.authors = list;
        this.tvTypes = list2;
        this.iconUrl = str4;
        this.url = str5;
        this.fileSize = l10;
        this.repositoryUrl = str6;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ExternalPluginEntry copy$default(ExternalPluginEntry externalPluginEntry, String str, String str2, String str3, int i10, int i11, int i12, List list, List list2, String str4, String str5, Long l10, String str6, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            str = externalPluginEntry.name;
        }
        if ((i13 & 2) != 0) {
            str2 = externalPluginEntry.internalName;
        }
        if ((i13 & 4) != 0) {
            str3 = externalPluginEntry.description;
        }
        if ((i13 & 8) != 0) {
            i10 = externalPluginEntry.version;
        }
        if ((i13 & 16) != 0) {
            i11 = externalPluginEntry.apiVersion;
        }
        if ((i13 & 32) != 0) {
            i12 = externalPluginEntry.status;
        }
        if ((i13 & 64) != 0) {
            list = externalPluginEntry.authors;
        }
        if ((i13 & 128) != 0) {
            list2 = externalPluginEntry.tvTypes;
        }
        if ((i13 & 256) != 0) {
            str4 = externalPluginEntry.iconUrl;
        }
        if ((i13 & 512) != 0) {
            str5 = externalPluginEntry.url;
        }
        if ((i13 & 1024) != 0) {
            l10 = externalPluginEntry.fileSize;
        }
        if ((i13 & 2048) != 0) {
            str6 = externalPluginEntry.repositoryUrl;
        }
        Long l11 = l10;
        String str7 = str6;
        String str8 = str4;
        String str9 = str5;
        List list3 = list;
        List list4 = list2;
        int i14 = i11;
        int i15 = i12;
        return externalPluginEntry.copy(str, str2, str3, i10, i14, i15, list3, list4, str8, str9, l11, str7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Long getFileSize() {
        return this.fileSize;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getRepositoryUrl() {
        return this.repositoryUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getInternalName() {
        return this.internalName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getApiVersion() {
        return this.apiVersion;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final List<String> component7() {
        return this.authors;
    }

    public final List<String> component8() {
        return this.tvTypes;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getIconUrl() {
        return this.iconUrl;
    }

    public final ExternalPluginEntry copy(String name, String internalName, String description, int version, int apiVersion, int status, List<String> authors, List<String> tvTypes, String iconUrl, String url, Long fileSize, String repositoryUrl) {
        return new ExternalPluginEntry(name, internalName, description, version, apiVersion, status, authors, tvTypes, iconUrl, url, fileSize, repositoryUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExternalPluginEntry)) {
            return false;
        }
        ExternalPluginEntry externalPluginEntry = (ExternalPluginEntry) other;
        return p.a(this.name, externalPluginEntry.name) && p.a(this.internalName, externalPluginEntry.internalName) && p.a(this.description, externalPluginEntry.description) && this.version == externalPluginEntry.version && this.apiVersion == externalPluginEntry.apiVersion && this.status == externalPluginEntry.status && p.a(this.authors, externalPluginEntry.authors) && p.a(this.tvTypes, externalPluginEntry.tvTypes) && p.a(this.iconUrl, externalPluginEntry.iconUrl) && p.a(this.url, externalPluginEntry.url) && p.a(this.fileSize, externalPluginEntry.fileSize) && p.a(this.repositoryUrl, externalPluginEntry.repositoryUrl);
    }

    public final int getApiVersion() {
        return this.apiVersion;
    }

    public final List<String> getAuthors() {
        return this.authors;
    }

    public final String getDescription() {
        return this.description;
    }

    public final Long getFileSize() {
        return this.fileSize;
    }

    public final String getIconUrl() {
        return this.iconUrl;
    }

    public final String getInternalName() {
        return this.internalName;
    }

    public final String getName() {
        return this.name;
    }

    public final String getRepositoryUrl() {
        return this.repositoryUrl;
    }

    public final int getStatus() {
        return this.status;
    }

    public final List<String> getTvTypes() {
        return this.tvTypes;
    }

    public final String getUrl() {
        return this.url;
    }

    public final int getVersion() {
        return this.version;
    }

    public int hashCode() {
        int iC = c.c(this.name.hashCode() * 31, 31, this.internalName);
        String str = this.description;
        int iHashCode = (((((((iC + (str == null ? 0 : str.hashCode())) * 31) + this.version) * 31) + this.apiVersion) * 31) + this.status) * 31;
        List<String> list = this.authors;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.tvTypes;
        int iHashCode3 = (iHashCode2 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str2 = this.iconUrl;
        int iC2 = c.c((iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31, 31, this.url);
        Long l10 = this.fileSize;
        int iHashCode4 = (iC2 + (l10 == null ? 0 : l10.hashCode())) * 31;
        String str3 = this.repositoryUrl;
        return iHashCode4 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        String str = this.name;
        String str2 = this.internalName;
        String str3 = this.description;
        int i10 = this.version;
        int i11 = this.apiVersion;
        int i12 = this.status;
        List<String> list = this.authors;
        List<String> list2 = this.tvTypes;
        String str4 = this.iconUrl;
        String str5 = this.url;
        Long l10 = this.fileSize;
        String str6 = this.repositoryUrl;
        StringBuilder sbR = a2.r("ExternalPluginEntry(name=", str, ", internalName=", str2, ", description=");
        d.A(sbR, str3, ", version=", i10, ", apiVersion=");
        a2.y(sbR, i11, ", status=", i12, ", authors=");
        a2.A(sbR, list, ", tvTypes=", list2, ", iconUrl=");
        a.i(sbR, str4, ", url=", str5, ", fileSize=");
        sbR.append(l10);
        sbR.append(", repositoryUrl=");
        sbR.append(str6);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ ExternalPluginEntry(String str, String str2, String str3, int i10, int i11, int i12, List list, List list2, String str4, String str5, Long l10, String str6, int i13, h hVar) {
        this(str, str2, (i13 & 4) != 0 ? null : str3, (i13 & 8) != 0 ? 1 : i10, (i13 & 16) != 0 ? 1 : i11, (i13 & 32) != 0 ? 1 : i12, (i13 & 64) != 0 ? null : list, (i13 & 128) != 0 ? null : list2, (i13 & 256) != 0 ? null : str4, str5, (i13 & 1024) != 0 ? null : l10, (i13 & 2048) != 0 ? null : str6);
    }
}
