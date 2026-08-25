package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÆ\u0003J\u0011\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\nHÆ\u0003J\u0011\u0010)\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\nHÆ\u0003J\u0011\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u009f\u0001\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\n2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001J\u0014\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00100\u001a\u000201HÖ\u0081\u0004J\n\u00102\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0019\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00063"}, d2 = {"Lcom/arflix/tv/data/api/StremioManifestResponse;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "version", MediaTrack.ROLE_DESCRIPTION, "logo", "background", "types", "", "resources", "catalogs", "Lcom/arflix/tv/data/api/StremioCatalog;", "idPrefixes", "behaviorHints", "Lcom/arflix/tv/data/api/StremioAddonBehaviorHints;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/arflix/tv/data/api/StremioAddonBehaviorHints;)V", "getId", "()Ljava/lang/String;", "getName", "getVersion", "getDescription", "getLogo", "getBackground", "getTypes", "()Ljava/util/List;", "getResources", "getCatalogs", "getIdPrefixes", "getBehaviorHints", "()Lcom/arflix/tv/data/api/StremioAddonBehaviorHints;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StremioManifestResponse {
    public static final int $stable = 8;
    private final String background;
    private final StremioAddonBehaviorHints behaviorHints;
    private final List<StremioCatalog> catalogs;
    private final String description;
    private final String id;
    private final List<String> idPrefixes;
    private final String logo;
    private final String name;
    private final List<Object> resources;
    private final List<String> types;
    private final String version;

    public StremioManifestResponse(String str, String str2, String str3, String str4, String str5, String str6, List<String> list, List<? extends Object> list2, List<StremioCatalog> list3, List<String> list4, StremioAddonBehaviorHints stremioAddonBehaviorHints) {
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
        this.behaviorHints = stremioAddonBehaviorHints;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StremioManifestResponse copy$default(StremioManifestResponse stremioManifestResponse, String str, String str2, String str3, String str4, String str5, String str6, List list, List list2, List list3, List list4, StremioAddonBehaviorHints stremioAddonBehaviorHints, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = stremioManifestResponse.id;
        }
        if ((i10 & 2) != 0) {
            str2 = stremioManifestResponse.name;
        }
        if ((i10 & 4) != 0) {
            str3 = stremioManifestResponse.version;
        }
        if ((i10 & 8) != 0) {
            str4 = stremioManifestResponse.description;
        }
        if ((i10 & 16) != 0) {
            str5 = stremioManifestResponse.logo;
        }
        if ((i10 & 32) != 0) {
            str6 = stremioManifestResponse.background;
        }
        if ((i10 & 64) != 0) {
            list = stremioManifestResponse.types;
        }
        if ((i10 & 128) != 0) {
            list2 = stremioManifestResponse.resources;
        }
        if ((i10 & 256) != 0) {
            list3 = stremioManifestResponse.catalogs;
        }
        if ((i10 & 512) != 0) {
            list4 = stremioManifestResponse.idPrefixes;
        }
        if ((i10 & 1024) != 0) {
            stremioAddonBehaviorHints = stremioManifestResponse.behaviorHints;
        }
        List list5 = list4;
        StremioAddonBehaviorHints stremioAddonBehaviorHints2 = stremioAddonBehaviorHints;
        List list6 = list2;
        List list7 = list3;
        String str7 = str6;
        List list8 = list;
        String str8 = str5;
        String str9 = str3;
        return stremioManifestResponse.copy(str, str2, str9, str4, str8, str7, list8, list6, list7, list5, stremioAddonBehaviorHints2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final List<String> component10() {
        return this.idPrefixes;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final StremioAddonBehaviorHints getBehaviorHints() {
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

    public final List<Object> component8() {
        return this.resources;
    }

    public final List<StremioCatalog> component9() {
        return this.catalogs;
    }

    public final StremioManifestResponse copy(String id, String name, String version, String description, String logo, String background, List<String> types, List<? extends Object> resources, List<StremioCatalog> catalogs, List<String> idPrefixes, StremioAddonBehaviorHints behaviorHints) {
        return new StremioManifestResponse(id, name, version, description, logo, background, types, resources, catalogs, idPrefixes, behaviorHints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StremioManifestResponse)) {
            return false;
        }
        StremioManifestResponse stremioManifestResponse = (StremioManifestResponse) other;
        return p.a(this.id, stremioManifestResponse.id) && p.a(this.name, stremioManifestResponse.name) && p.a(this.version, stremioManifestResponse.version) && p.a(this.description, stremioManifestResponse.description) && p.a(this.logo, stremioManifestResponse.logo) && p.a(this.background, stremioManifestResponse.background) && p.a(this.types, stremioManifestResponse.types) && p.a(this.resources, stremioManifestResponse.resources) && p.a(this.catalogs, stremioManifestResponse.catalogs) && p.a(this.idPrefixes, stremioManifestResponse.idPrefixes) && p.a(this.behaviorHints, stremioManifestResponse.behaviorHints);
    }

    public final String getBackground() {
        return this.background;
    }

    public final StremioAddonBehaviorHints getBehaviorHints() {
        return this.behaviorHints;
    }

    public final List<StremioCatalog> getCatalogs() {
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

    public final List<Object> getResources() {
        return this.resources;
    }

    public final List<String> getTypes() {
        return this.types;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.name), 31, this.version);
        String str = this.description;
        int iHashCode = (iC + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.logo;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.background;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        List<String> list = this.types;
        int iHashCode4 = (iHashCode3 + (list == null ? 0 : list.hashCode())) * 31;
        List<Object> list2 = this.resources;
        int iHashCode5 = (iHashCode4 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<StremioCatalog> list3 = this.catalogs;
        int iHashCode6 = (iHashCode5 + (list3 == null ? 0 : list3.hashCode())) * 31;
        List<String> list4 = this.idPrefixes;
        int iHashCode7 = (iHashCode6 + (list4 == null ? 0 : list4.hashCode())) * 31;
        StremioAddonBehaviorHints stremioAddonBehaviorHints = this.behaviorHints;
        return iHashCode7 + (stremioAddonBehaviorHints != null ? stremioAddonBehaviorHints.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.version;
        String str4 = this.description;
        String str5 = this.logo;
        String str6 = this.background;
        List<String> list = this.types;
        List<Object> list2 = this.resources;
        List<StremioCatalog> list3 = this.catalogs;
        List<String> list4 = this.idPrefixes;
        StremioAddonBehaviorHints stremioAddonBehaviorHints = this.behaviorHints;
        StringBuilder sbR = a2.r("StremioManifestResponse(id=", str, ", name=", str2, ", version=");
        y.a.i(sbR, str3, ", description=", str4, ", logo=");
        y.a.i(sbR, str5, ", background=", str6, ", types=");
        a2.A(sbR, list, ", resources=", list2, ", catalogs=");
        a2.A(sbR, list3, ", idPrefixes=", list4, ", behaviorHints=");
        sbR.append(stremioAddonBehaviorHints);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ StremioManifestResponse(String str, String str2, String str3, String str4, String str5, String str6, List list, List list2, List list3, List list4, StremioAddonBehaviorHints stremioAddonBehaviorHints, int i10, kotlin.jvm.internal.h hVar) {
        this(str, str2, str3, (i10 & 8) != 0 ? null : str4, (i10 & 16) != 0 ? null : str5, (i10 & 32) != 0 ? null : str6, (i10 & 64) != 0 ? null : list, (i10 & 128) != 0 ? null : list2, (i10 & 256) != 0 ? null : list3, (i10 & 512) != 0 ? null : list4, (i10 & 1024) != 0 ? null : stremioAddonBehaviorHints);
    }
}
