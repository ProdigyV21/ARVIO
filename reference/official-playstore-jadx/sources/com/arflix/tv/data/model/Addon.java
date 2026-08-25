package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\bHÆ\u0003J\t\u0010-\u001a\u00020\bHÆ\u0003J\t\u0010.\u001a\u00020\u000bHÆ\u0003J\t\u0010/\u001a\u00020\rHÆ\u0003J\t\u00100\u001a\u00020\u000fHÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0093\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u00106\u001a\u00020\b2\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00108\u001a\u000209HÖ\u0081\u0004J\n\u0010:\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001cR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u001cR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0018¨\u0006;"}, d2 = {"Lcom/arflix/tv/data/model/Addon;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "version", MediaTrack.ROLE_DESCRIPTION, "isInstalled", "", "isEnabled", LinkHeader.Parameters.Type, "Lcom/arflix/tv/data/model/AddonType;", "runtimeKind", "Lcom/arflix/tv/data/model/RuntimeKind;", "installSource", "Lcom/arflix/tv/data/model/AddonInstallSource;", "url", "logo", "manifest", "Lcom/arflix/tv/data/model/AddonManifest;", "transportUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLcom/arflix/tv/data/model/AddonType;Lcom/arflix/tv/data/model/RuntimeKind;Lcom/arflix/tv/data/model/AddonInstallSource;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/AddonManifest;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getVersion", "getDescription", "()Z", "getType", "()Lcom/arflix/tv/data/model/AddonType;", "getRuntimeKind", "()Lcom/arflix/tv/data/model/RuntimeKind;", "getInstallSource", "()Lcom/arflix/tv/data/model/AddonInstallSource;", "getUrl", "getLogo", "getManifest", "()Lcom/arflix/tv/data/model/AddonManifest;", "getTransportUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Addon {
    public static final int $stable = 0;
    private final String description;
    private final String id;
    private final AddonInstallSource installSource;
    private final boolean isEnabled;
    private final boolean isInstalled;
    private final String logo;
    private final AddonManifest manifest;
    private final String name;
    private final RuntimeKind runtimeKind;
    private final String transportUrl;
    private final AddonType type;
    private final String url;
    private final String version;

    public Addon(String str, String str2, String str3, String str4, boolean z, boolean z5, AddonType addonType, RuntimeKind runtimeKind, AddonInstallSource addonInstallSource, String str5, String str6, AddonManifest addonManifest, String str7) {
        this.id = str;
        this.name = str2;
        this.version = str3;
        this.description = str4;
        this.isInstalled = z;
        this.isEnabled = z5;
        this.type = addonType;
        this.runtimeKind = runtimeKind;
        this.installSource = addonInstallSource;
        this.url = str5;
        this.logo = str6;
        this.manifest = addonManifest;
        this.transportUrl = str7;
    }

    public static /* synthetic */ Addon copy$default(Addon addon, String str, String str2, String str3, String str4, boolean z, boolean z5, AddonType addonType, RuntimeKind runtimeKind, AddonInstallSource addonInstallSource, String str5, String str6, AddonManifest addonManifest, String str7, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = addon.id;
        }
        return addon.copy(str, (i10 & 2) != 0 ? addon.name : str2, (i10 & 4) != 0 ? addon.version : str3, (i10 & 8) != 0 ? addon.description : str4, (i10 & 16) != 0 ? addon.isInstalled : z, (i10 & 32) != 0 ? addon.isEnabled : z5, (i10 & 64) != 0 ? addon.type : addonType, (i10 & 128) != 0 ? addon.runtimeKind : runtimeKind, (i10 & 256) != 0 ? addon.installSource : addonInstallSource, (i10 & 512) != 0 ? addon.url : str5, (i10 & 1024) != 0 ? addon.logo : str6, (i10 & 2048) != 0 ? addon.manifest : addonManifest, (i10 & 4096) != 0 ? addon.transportUrl : str7);
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
    public final boolean getIsInstalled() {
        return this.isInstalled;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsEnabled() {
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

    public final Addon copy(String id, String name, String version, String description, boolean isInstalled, boolean isEnabled, AddonType type, RuntimeKind runtimeKind, AddonInstallSource installSource, String url, String logo, AddonManifest manifest, String transportUrl) {
        return new Addon(id, name, version, description, isInstalled, isEnabled, type, runtimeKind, installSource, url, logo, manifest, transportUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Addon)) {
            return false;
        }
        Addon addon = (Addon) other;
        return p.a(this.id, addon.id) && p.a(this.name, addon.name) && p.a(this.version, addon.version) && p.a(this.description, addon.description) && this.isInstalled == addon.isInstalled && this.isEnabled == addon.isEnabled && this.type == addon.type && this.runtimeKind == addon.runtimeKind && this.installSource == addon.installSource && p.a(this.url, addon.url) && p.a(this.logo, addon.logo) && p.a(this.manifest, addon.manifest) && p.a(this.transportUrl, addon.transportUrl);
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
        int iHashCode = (this.installSource.hashCode() + ((this.runtimeKind.hashCode() + ((this.type.hashCode() + ((((c.c(c.c(c.c(this.id.hashCode() * 31, 31, this.name), 31, this.version), 31, this.description) + (this.isInstalled ? 1231 : 1237)) * 31) + (this.isEnabled ? 1231 : 1237)) * 31)) * 31)) * 31)) * 31;
        String str = this.url;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.logo;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        AddonManifest addonManifest = this.manifest;
        int iHashCode4 = (iHashCode3 + (addonManifest == null ? 0 : addonManifest.hashCode())) * 31;
        String str3 = this.transportUrl;
        return iHashCode4 + (str3 != null ? str3.hashCode() : 0);
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final boolean isInstalled() {
        return this.isInstalled;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.version;
        String str4 = this.description;
        boolean z = this.isInstalled;
        boolean z5 = this.isEnabled;
        AddonType addonType = this.type;
        RuntimeKind runtimeKind = this.runtimeKind;
        AddonInstallSource addonInstallSource = this.installSource;
        String str5 = this.url;
        String str6 = this.logo;
        AddonManifest addonManifest = this.manifest;
        String str7 = this.transportUrl;
        StringBuilder sbR = a2.r("Addon(id=", str, ", name=", str2, ", version=");
        a.i(sbR, str3, ", description=", str4, ", isInstalled=");
        f.h(sbR, z, ", isEnabled=", z5, ", type=");
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

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public /* synthetic */ Addon(java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, boolean r21, boolean r22, com.arflix.tv.data.model.AddonType r23, com.arflix.tv.data.model.RuntimeKind r24, com.arflix.tv.data.model.AddonInstallSource r25, java.lang.String r26, java.lang.String r27, com.arflix.tv.data.model.AddonManifest r28, java.lang.String r29, int r30, kotlin.jvm.internal.h r31) {
        /*
            r16 = this;
            r0 = r30
            r1 = r0 & 32
            if (r1 == 0) goto L9
            r1 = 1
            r8 = r1
            goto Lb
        L9:
            r8 = r22
        Lb:
            r1 = r0 & 128(0x80, float:1.8E-43)
            if (r1 == 0) goto L13
            com.arflix.tv.data.model.RuntimeKind r1 = com.arflix.tv.data.model.RuntimeKind.STREMIO
            r10 = r1
            goto L15
        L13:
            r10 = r24
        L15:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L1d
            com.arflix.tv.data.model.AddonInstallSource r1 = com.arflix.tv.data.model.AddonInstallSource.DIRECT_URL
            r11 = r1
            goto L1f
        L1d:
            r11 = r25
        L1f:
            r1 = r0 & 512(0x200, float:7.17E-43)
            r2 = 0
            if (r1 == 0) goto L26
            r12 = r2
            goto L28
        L26:
            r12 = r26
        L28:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L2e
            r13 = r2
            goto L30
        L2e:
            r13 = r27
        L30:
            r1 = r0 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L36
            r14 = r2
            goto L38
        L36:
            r14 = r28
        L38:
            r0 = r0 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L4c
            r15 = r2
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r9 = r23
            r2 = r16
            goto L5c
        L4c:
            r15 = r29
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r9 = r23
        L5c:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.model.Addon.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, com.arflix.tv.data.model.AddonType, com.arflix.tv.data.model.RuntimeKind, com.arflix.tv.data.model.AddonInstallSource, java.lang.String, java.lang.String, com.arflix.tv.data.model.AddonManifest, java.lang.String, int, kotlin.jvm.internal.h):void");
    }
}
