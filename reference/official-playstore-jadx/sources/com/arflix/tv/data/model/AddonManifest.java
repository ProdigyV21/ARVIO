package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u008f\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\f0\nHÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000e0\nHÆ\u0003J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u0097\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001J\u0014\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u000101HÖ\u0083\u0004J\n\u00102\u001a\u000203HÖ\u0081\u0004J\n\u00104\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00065"}, d2 = {"Lcom/arflix/tv/data/model/AddonManifest;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "version", MediaTrack.ROLE_DESCRIPTION, "logo", "background", "types", "", "resources", "Lcom/arflix/tv/data/model/AddonResource;", "catalogs", "Lcom/arflix/tv/data/model/AddonCatalog;", "idPrefixes", "behaviorHints", "Lcom/arflix/tv/data/model/AddonBehaviorHints;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/arflix/tv/data/model/AddonBehaviorHints;)V", "getId", "()Ljava/lang/String;", "getName", "getVersion", "getDescription", "getLogo", "getBackground", "getTypes", "()Ljava/util/List;", "getResources", "getCatalogs", "getIdPrefixes", "getBehaviorHints", "()Lcom/arflix/tv/data/model/AddonBehaviorHints;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AddonManifest implements Serializable {
    public static final int $stable = 0;
    private final String background;
    private final AddonBehaviorHints behaviorHints;
    private final List<AddonCatalog> catalogs;
    private final String description;
    private final String id;
    private final List<String> idPrefixes;
    private final String logo;
    private final String name;
    private final List<AddonResource> resources;
    private final List<String> types;
    private final String version;

    public AddonManifest(String str, String str2, String str3, String str4, String str5, String str6, List<String> list, List<AddonResource> list2, List<AddonCatalog> list3, List<String> list4, AddonBehaviorHints addonBehaviorHints) {
        this.id = str;
        this.name = str2;
        this.version = str3;
        this.description = str4;
        this.logo = str5;
        this.background = str6;
        this.types = list;
        this.resources = list2;
        this.catalogs = list3;
        this.idPrefixes = list4;
        this.behaviorHints = addonBehaviorHints;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AddonManifest copy$default(AddonManifest addonManifest, String str, String str2, String str3, String str4, String str5, String str6, List list, List list2, List list3, List list4, AddonBehaviorHints addonBehaviorHints, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = addonManifest.id;
        }
        if ((i10 & 2) != 0) {
            str2 = addonManifest.name;
        }
        if ((i10 & 4) != 0) {
            str3 = addonManifest.version;
        }
        if ((i10 & 8) != 0) {
            str4 = addonManifest.description;
        }
        if ((i10 & 16) != 0) {
            str5 = addonManifest.logo;
        }
        if ((i10 & 32) != 0) {
            str6 = addonManifest.background;
        }
        if ((i10 & 64) != 0) {
            list = addonManifest.types;
        }
        if ((i10 & 128) != 0) {
            list2 = addonManifest.resources;
        }
        if ((i10 & 256) != 0) {
            list3 = addonManifest.catalogs;
        }
        if ((i10 & 512) != 0) {
            list4 = addonManifest.idPrefixes;
        }
        if ((i10 & 1024) != 0) {
            addonBehaviorHints = addonManifest.behaviorHints;
        }
        List list5 = list4;
        AddonBehaviorHints addonBehaviorHints2 = addonBehaviorHints;
        List list6 = list2;
        List list7 = list3;
        String str7 = str6;
        List list8 = list;
        String str8 = str5;
        String str9 = str3;
        return addonManifest.copy(str, str2, str9, str4, str8, str7, list8, list6, list7, list5, addonBehaviorHints2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final List<String> component10() {
        return this.idPrefixes;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final AddonBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
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
    public final String getLogo() {
        return this.logo;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getBackground() {
        return this.background;
    }

    public final List<String> component7() {
        return this.types;
    }

    public final List<AddonResource> component8() {
        return this.resources;
    }

    public final List<AddonCatalog> component9() {
        return this.catalogs;
    }

    public final AddonManifest copy(String id, String name, String version, String description, String logo, String background, List<String> types, List<AddonResource> resources, List<AddonCatalog> catalogs, List<String> idPrefixes, AddonBehaviorHints behaviorHints) {
        return new AddonManifest(id, name, version, description, logo, background, types, resources, catalogs, idPrefixes, behaviorHints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonManifest)) {
            return false;
        }
        AddonManifest addonManifest = (AddonManifest) other;
        return p.a(this.id, addonManifest.id) && p.a(this.name, addonManifest.name) && p.a(this.version, addonManifest.version) && p.a(this.description, addonManifest.description) && p.a(this.logo, addonManifest.logo) && p.a(this.background, addonManifest.background) && p.a(this.types, addonManifest.types) && p.a(this.resources, addonManifest.resources) && p.a(this.catalogs, addonManifest.catalogs) && p.a(this.idPrefixes, addonManifest.idPrefixes) && p.a(this.behaviorHints, addonManifest.behaviorHints);
    }

    public final String getBackground() {
        return this.background;
    }

    public final AddonBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
    }

    public final List<AddonCatalog> getCatalogs() {
        return this.catalogs;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getId() {
        return this.id;
    }

    public final List<String> getIdPrefixes() {
        return this.idPrefixes;
    }

    public final String getLogo() {
        return this.logo;
    }

    public final String getName() {
        return this.name;
    }

    public final List<AddonResource> getResources() {
        return this.resources;
    }

    public final List<String> getTypes() {
        return this.types;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int iC = c.c(c.c(c.c(this.id.hashCode() * 31, 31, this.name), 31, this.version), 31, this.description);
        String str = this.logo;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.background;
        int iE = d.e(this.catalogs, d.e(this.resources, d.e(this.types, (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31, 31), 31), 31);
        List<String> list = this.idPrefixes;
        int iHashCode2 = (iE + (list == null ? 0 : list.hashCode())) * 31;
        AddonBehaviorHints addonBehaviorHints = this.behaviorHints;
        return iHashCode2 + (addonBehaviorHints != null ? addonBehaviorHints.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.version;
        String str4 = this.description;
        String str5 = this.logo;
        String str6 = this.background;
        List<String> list = this.types;
        List<AddonResource> list2 = this.resources;
        List<AddonCatalog> list3 = this.catalogs;
        List<String> list4 = this.idPrefixes;
        AddonBehaviorHints addonBehaviorHints = this.behaviorHints;
        StringBuilder sbR = a2.r("AddonManifest(id=", str, ", name=", str2, ", version=");
        a.i(sbR, str3, ", description=", str4, ", logo=");
        a.i(sbR, str5, ", background=", str6, ", types=");
        a2.A(sbR, list, ", resources=", list2, ", catalogs=");
        a2.A(sbR, list3, ", idPrefixes=", list4, ", behaviorHints=");
        sbR.append(addonBehaviorHints);
        sbR.append(")");
        return sbR.toString();
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public /* synthetic */ AddonManifest(java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.util.List r21, java.util.List r22, java.util.List r23, java.util.List r24, com.arflix.tv.data.model.AddonBehaviorHints r25, int r26, kotlin.jvm.internal.h r27) {
        /*
            r14 = this;
            r0 = r26
            r1 = r0 & 8
            if (r1 == 0) goto La
            java.lang.String r1 = ""
            r6 = r1
            goto Lc
        La:
            r6 = r18
        Lc:
            r1 = r0 & 16
            r2 = 0
            if (r1 == 0) goto L13
            r7 = r2
            goto L15
        L13:
            r7 = r19
        L15:
            r1 = r0 & 32
            if (r1 == 0) goto L1b
            r8 = r2
            goto L1d
        L1b:
            r8 = r20
        L1d:
            r1 = r0 & 64
            kotlin.collections.z r3 = kotlin.collections.z.f19728i
            if (r1 == 0) goto L25
            r9 = r3
            goto L27
        L25:
            r9 = r21
        L27:
            r1 = r0 & 128(0x80, float:1.8E-43)
            if (r1 == 0) goto L2d
            r10 = r3
            goto L2f
        L2d:
            r10 = r22
        L2f:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L35
            r11 = r3
            goto L37
        L35:
            r11 = r23
        L37:
            r1 = r0 & 512(0x200, float:7.17E-43)
            if (r1 == 0) goto L3d
            r12 = r2
            goto L3f
        L3d:
            r12 = r24
        L3f:
            r0 = r0 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L4b
            r13 = r2
            r3 = r15
            r4 = r16
            r5 = r17
            r2 = r14
            goto L53
        L4b:
            r13 = r25
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
        L53:
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.model.AddonManifest.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List, java.util.List, java.util.List, com.arflix.tv.data.model.AddonBehaviorHints, int, kotlin.jvm.internal.h):void");
    }
}
