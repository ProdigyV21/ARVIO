package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import com.arflix.tv.data.model.AddonManifest;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JG\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006!"}, d2 = {"Lcom/arflix/tv/data/repository/HttpLocalScraperInstallCandidate;", "", ContentDisposition.Parameters.Name, "", "version", MediaTrack.ROLE_DESCRIPTION, "logo", "manifest", "Lcom/arflix/tv/data/model/AddonManifest;", "transportUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/AddonManifest;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getVersion", "getDescription", "getLogo", "getManifest", "()Lcom/arflix/tv/data/model/AddonManifest;", "getTransportUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HttpLocalScraperInstallCandidate {
    public static final int $stable = 0;
    private final String description;
    private final String logo;
    private final AddonManifest manifest;
    private final String name;
    private final String transportUrl;
    private final String version;

    public HttpLocalScraperInstallCandidate(String str, String str2, String str3, String str4, AddonManifest addonManifest, String str5) {
        this.name = str;
        this.version = str2;
        this.description = str3;
        this.logo = str4;
        this.manifest = addonManifest;
        this.transportUrl = str5;
    }

    public static /* synthetic */ HttpLocalScraperInstallCandidate copy$default(HttpLocalScraperInstallCandidate httpLocalScraperInstallCandidate, String str, String str2, String str3, String str4, AddonManifest addonManifest, String str5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = httpLocalScraperInstallCandidate.name;
        }
        if ((i10 & 2) != 0) {
            str2 = httpLocalScraperInstallCandidate.version;
        }
        if ((i10 & 4) != 0) {
            str3 = httpLocalScraperInstallCandidate.description;
        }
        if ((i10 & 8) != 0) {
            str4 = httpLocalScraperInstallCandidate.logo;
        }
        if ((i10 & 16) != 0) {
            addonManifest = httpLocalScraperInstallCandidate.manifest;
        }
        if ((i10 & 32) != 0) {
            str5 = httpLocalScraperInstallCandidate.transportUrl;
        }
        AddonManifest addonManifest2 = addonManifest;
        String str6 = str5;
        return httpLocalScraperInstallCandidate.copy(str, str2, str3, str4, addonManifest2, str6);
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
    public final String getLogo() {
        return this.logo;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final AddonManifest getManifest() {
        return this.manifest;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getTransportUrl() {
        return this.transportUrl;
    }

    public final HttpLocalScraperInstallCandidate copy(String name, String version, String description, String logo, AddonManifest manifest, String transportUrl) {
        return new HttpLocalScraperInstallCandidate(name, version, description, logo, manifest, transportUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HttpLocalScraperInstallCandidate)) {
            return false;
        }
        HttpLocalScraperInstallCandidate httpLocalScraperInstallCandidate = (HttpLocalScraperInstallCandidate) other;
        return kotlin.jvm.internal.p.a(this.name, httpLocalScraperInstallCandidate.name) && kotlin.jvm.internal.p.a(this.version, httpLocalScraperInstallCandidate.version) && kotlin.jvm.internal.p.a(this.description, httpLocalScraperInstallCandidate.description) && kotlin.jvm.internal.p.a(this.logo, httpLocalScraperInstallCandidate.logo) && kotlin.jvm.internal.p.a(this.manifest, httpLocalScraperInstallCandidate.manifest) && kotlin.jvm.internal.p.a(this.transportUrl, httpLocalScraperInstallCandidate.transportUrl);
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getLogo() {
        return this.logo;
    }

    public final AddonManifest getManifest() {
        return this.manifest;
    }

    public final String getName() {
        return this.name;
    }

    public final String getTransportUrl() {
        return this.transportUrl;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.name.hashCode() * 31, 31, this.version), 31, this.description);
        String str = this.logo;
        return this.transportUrl.hashCode() + ((this.manifest.hashCode() + ((iC + (str == null ? 0 : str.hashCode())) * 31)) * 31);
    }

    public String toString() {
        String str = this.name;
        String str2 = this.version;
        String str3 = this.description;
        String str4 = this.logo;
        AddonManifest addonManifest = this.manifest;
        String str5 = this.transportUrl;
        StringBuilder sbR = a2.r("HttpLocalScraperInstallCandidate(name=", str, ", version=", str2, ", description=");
        y.a.i(sbR, str3, ", logo=", str4, ", manifest=");
        sbR.append(addonManifest);
        sbR.append(", transportUrl=");
        sbR.append(str5);
        sbR.append(")");
        return sbR.toString();
    }
}
