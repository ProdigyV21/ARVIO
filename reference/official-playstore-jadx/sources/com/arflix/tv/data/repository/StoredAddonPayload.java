package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.AddonInstallSource;
import com.arflix.tv.data.model.AddonManifest;
import com.arflix.tv.data.model.AddonType;
import com.arflix.tv.data.model.RuntimeKind;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B£\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0010\u0010.\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u000b\u0010/\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jª\u0001\u00106\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00107J\u0014\u00108\u001a\u00020\b2\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010:\u001a\u00020;HÖ\u0081\u0004J\n\u0010<\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u0007\u0010\u001cR\u0015\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\t\u0010\u001cR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018¨\u0006="}, d2 = {"Lcom/arflix/tv/data/repository/StoredAddonPayload;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "version", MediaTrack.ROLE_DESCRIPTION, "isInstalled", "", "isEnabled", LinkHeader.Parameters.Type, "Lcom/arflix/tv/data/model/AddonType;", "runtimeKind", "Lcom/arflix/tv/data/model/RuntimeKind;", "installSource", "Lcom/arflix/tv/data/model/AddonInstallSource;", "url", "logo", "manifest", "Lcom/arflix/tv/data/model/AddonManifest;", "transportUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/arflix/tv/data/model/AddonType;Lcom/arflix/tv/data/model/RuntimeKind;Lcom/arflix/tv/data/model/AddonInstallSource;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/AddonManifest;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getVersion", "getDescription", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getType", "()Lcom/arflix/tv/data/model/AddonType;", "getRuntimeKind", "()Lcom/arflix/tv/data/model/RuntimeKind;", "getInstallSource", "()Lcom/arflix/tv/data/model/AddonInstallSource;", "getUrl", "getLogo", "getManifest", "()Lcom/arflix/tv/data/model/AddonManifest;", "getTransportUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/arflix/tv/data/model/AddonType;Lcom/arflix/tv/data/model/RuntimeKind;Lcom/arflix/tv/data/model/AddonInstallSource;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/AddonManifest;Ljava/lang/String;)Lcom/arflix/tv/data/repository/StoredAddonPayload;", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class StoredAddonPayload {
    private final String description;
    private final String id;
    private final AddonInstallSource installSource;
    private final Boolean isEnabled;
    private final Boolean isInstalled;
    private final String logo;
    private final AddonManifest manifest;
    private final String name;
    private final RuntimeKind runtimeKind;
    private final String transportUrl;
    private final AddonType type;
    private final String url;
    private final String version;

    public StoredAddonPayload() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    public static /* synthetic */ StoredAddonPayload copy$default(StoredAddonPayload storedAddonPayload, String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, AddonType addonType, RuntimeKind runtimeKind, AddonInstallSource addonInstallSource, String str5, String str6, AddonManifest addonManifest, String str7, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = storedAddonPayload.id;
        }
        return storedAddonPayload.copy(str, (i10 & 2) != 0 ? storedAddonPayload.name : str2, (i10 & 4) != 0 ? storedAddonPayload.version : str3, (i10 & 8) != 0 ? storedAddonPayload.description : str4, (i10 & 16) != 0 ? storedAddonPayload.isInstalled : bool, (i10 & 32) != 0 ? storedAddonPayload.isEnabled : bool2, (i10 & 64) != 0 ? storedAddonPayload.type : addonType, (i10 & 128) != 0 ? storedAddonPayload.runtimeKind : runtimeKind, (i10 & 256) != 0 ? storedAddonPayload.installSource : addonInstallSource, (i10 & 512) != 0 ? storedAddonPayload.url : str5, (i10 & 1024) != 0 ? storedAddonPayload.logo : str6, (i10 & 2048) != 0 ? storedAddonPayload.manifest : addonManifest, (i10 & 4096) != 0 ? storedAddonPayload.transportUrl : str7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getLogo() {
        return this.logo;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final AddonManifest getManifest() {
        return this.manifest;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getTransportUrl() {
        return this.transportUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Boolean getIsInstalled() {
        return this.isInstalled;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Boolean getIsEnabled() {
        return this.isEnabled;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final AddonType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final RuntimeKind getRuntimeKind() {
        return this.runtimeKind;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final AddonInstallSource getInstallSource() {
        return this.installSource;
    }

    public final StoredAddonPayload copy(String id, String name, String version, String description, Boolean isInstalled, Boolean isEnabled, AddonType type, RuntimeKind runtimeKind, AddonInstallSource installSource, String url, String logo, AddonManifest manifest, String transportUrl) {
        return new StoredAddonPayload(id, name, version, description, isInstalled, isEnabled, type, runtimeKind, installSource, url, logo, manifest, transportUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StoredAddonPayload)) {
            return false;
        }
        StoredAddonPayload storedAddonPayload = (StoredAddonPayload) other;
        return kotlin.jvm.internal.p.a(this.id, storedAddonPayload.id) && kotlin.jvm.internal.p.a(this.name, storedAddonPayload.name) && kotlin.jvm.internal.p.a(this.version, storedAddonPayload.version) && kotlin.jvm.internal.p.a(this.description, storedAddonPayload.description) && kotlin.jvm.internal.p.a(this.isInstalled, storedAddonPayload.isInstalled) && kotlin.jvm.internal.p.a(this.isEnabled, storedAddonPayload.isEnabled) && this.type == storedAddonPayload.type && this.runtimeKind == storedAddonPayload.runtimeKind && this.installSource == storedAddonPayload.installSource && kotlin.jvm.internal.p.a(this.url, storedAddonPayload.url) && kotlin.jvm.internal.p.a(this.logo, storedAddonPayload.logo) && kotlin.jvm.internal.p.a(this.manifest, storedAddonPayload.manifest) && kotlin.jvm.internal.p.a(this.transportUrl, storedAddonPayload.transportUrl);
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getId() {
        return this.id;
    }

    public final AddonInstallSource getInstallSource() {
        return this.installSource;
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

    public final RuntimeKind getRuntimeKind() {
        return this.runtimeKind;
    }

    public final String getTransportUrl() {
        return this.transportUrl;
    }

    public final AddonType getType() {
        return this.type;
    }

    public final String getUrl() {
        return this.url;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.version;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.description;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Boolean bool = this.isInstalled;
        int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.isEnabled;
        int iHashCode6 = (iHashCode5 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        AddonType addonType = this.type;
        int iHashCode7 = (iHashCode6 + (addonType == null ? 0 : addonType.hashCode())) * 31;
        RuntimeKind runtimeKind = this.runtimeKind;
        int iHashCode8 = (iHashCode7 + (runtimeKind == null ? 0 : runtimeKind.hashCode())) * 31;
        AddonInstallSource addonInstallSource = this.installSource;
        int iHashCode9 = (iHashCode8 + (addonInstallSource == null ? 0 : addonInstallSource.hashCode())) * 31;
        String str5 = this.url;
        int iHashCode10 = (iHashCode9 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.logo;
        int iHashCode11 = (iHashCode10 + (str6 == null ? 0 : str6.hashCode())) * 31;
        AddonManifest addonManifest = this.manifest;
        int iHashCode12 = (iHashCode11 + (addonManifest == null ? 0 : addonManifest.hashCode())) * 31;
        String str7 = this.transportUrl;
        return iHashCode12 + (str7 != null ? str7.hashCode() : 0);
    }

    public final Boolean isEnabled() {
        return this.isEnabled;
    }

    public final Boolean isInstalled() {
        return this.isInstalled;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.version;
        String str4 = this.description;
        Boolean bool = this.isInstalled;
        Boolean bool2 = this.isEnabled;
        AddonType addonType = this.type;
        RuntimeKind runtimeKind = this.runtimeKind;
        AddonInstallSource addonInstallSource = this.installSource;
        String str5 = this.url;
        String str6 = this.logo;
        AddonManifest addonManifest = this.manifest;
        String str7 = this.transportUrl;
        StringBuilder sbR = a2.r("StoredAddonPayload(id=", str, ", name=", str2, ", version=");
        y.a.i(sbR, str3, ", description=", str4, ", isInstalled=");
        sbR.append(bool);
        sbR.append(", isEnabled=");
        sbR.append(bool2);
        sbR.append(", type=");
        sbR.append(addonType);
        sbR.append(", runtimeKind=");
        sbR.append(runtimeKind);
        sbR.append(", installSource=");
        sbR.append(addonInstallSource);
        sbR.append(", url=");
        sbR.append(str5);
        sbR.append(", logo=");
        sbR.append(str6);
        sbR.append(", manifest=");
        sbR.append(addonManifest);
        sbR.append(", transportUrl=");
        return a0.c.p(sbR, str7, ")");
    }

    public StoredAddonPayload(String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, AddonType addonType, RuntimeKind runtimeKind, AddonInstallSource addonInstallSource, String str5, String str6, AddonManifest addonManifest, String str7) {
        this.id = str;
        this.name = str2;
        this.version = str3;
        this.description = str4;
        this.isInstalled = bool;
        this.isEnabled = bool2;
        this.type = addonType;
        this.runtimeKind = runtimeKind;
        this.installSource = addonInstallSource;
        this.url = str5;
        this.logo = str6;
        this.manifest = addonManifest;
        this.transportUrl = str7;
    }

    public /* synthetic */ StoredAddonPayload(String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, AddonType addonType, RuntimeKind runtimeKind, AddonInstallSource addonInstallSource, String str5, String str6, AddonManifest addonManifest, String str7, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : bool, (i10 & 32) != 0 ? null : bool2, (i10 & 64) != 0 ? null : addonType, (i10 & 128) != 0 ? null : runtimeKind, (i10 & 256) != 0 ? null : addonInstallSource, (i10 & 512) != 0 ? null : str5, (i10 & 1024) != 0 ? null : str6, (i10 & 2048) != 0 ? null : addonManifest, (i10 & 4096) != 0 ? null : str7);
    }
}
