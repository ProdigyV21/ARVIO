package com.arflix.tv.domain.model;

import androidx.fragment.app.a2;
import com.google.android.gms.cast.MediaTrack;
import com.squareup.moshi.m;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@m(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J9\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/domain/model/ExternalRepoManifest;", "", ContentDisposition.Parameters.Name, "", MediaTrack.ROLE_DESCRIPTION, "manifestVersion", "", "pluginLists", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/util/List;)V", "getName", "()Ljava/lang/String;", "getDescription", "getManifestVersion", "()I", "getPluginLists", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ExternalRepoManifest {
    public static final int $stable = 0;
    private final String description;
    private final int manifestVersion;
    private final String name;
    private final List<String> pluginLists;

    public ExternalRepoManifest(String str, String str2, int i10, List<String> list) {
        this.name = str;
        this.description = str2;
        this.manifestVersion = i10;
        this.pluginLists = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ExternalRepoManifest copy$default(ExternalRepoManifest externalRepoManifest, String str, String str2, int i10, List list, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = externalRepoManifest.name;
        }
        if ((i11 & 2) != 0) {
            str2 = externalRepoManifest.description;
        }
        if ((i11 & 4) != 0) {
            i10 = externalRepoManifest.manifestVersion;
        }
        if ((i11 & 8) != 0) {
            list = externalRepoManifest.pluginLists;
        }
        return externalRepoManifest.copy(str, str2, i10, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getManifestVersion() {
        return this.manifestVersion;
    }

    public final List<String> component4() {
        return this.pluginLists;
    }

    public final ExternalRepoManifest copy(String name, String description, int manifestVersion, List<String> pluginLists) {
        return new ExternalRepoManifest(name, description, manifestVersion, pluginLists);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExternalRepoManifest)) {
            return false;
        }
        ExternalRepoManifest externalRepoManifest = (ExternalRepoManifest) other;
        return p.a(this.name, externalRepoManifest.name) && p.a(this.description, externalRepoManifest.description) && this.manifestVersion == externalRepoManifest.manifestVersion && p.a(this.pluginLists, externalRepoManifest.pluginLists);
    }

    public final String getDescription() {
        return this.description;
    }

    public final int getManifestVersion() {
        return this.manifestVersion;
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getPluginLists() {
        return this.pluginLists;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        String str = this.description;
        return this.pluginLists.hashCode() + ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.manifestVersion) * 31);
    }

    public String toString() {
        String str = this.name;
        String str2 = this.description;
        int i10 = this.manifestVersion;
        List<String> list = this.pluginLists;
        StringBuilder sbR = a2.r("ExternalRepoManifest(name=", str, ", description=", str2, ", manifestVersion=");
        sbR.append(i10);
        sbR.append(", pluginLists=");
        sbR.append(list);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ ExternalRepoManifest(String str, String str2, int i10, List list, int i11, h hVar) {
        this(str, (i11 & 2) != 0 ? null : str2, (i11 & 4) != 0 ? 1 : i10, list);
    }
}
