package com.arflix.tv.data.model;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BO\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003JW\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0083\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006#"}, d2 = {"Lcom/arflix/tv/data/model/CatalogPackManifest;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "author", "version", MediaTrack.ROLE_DESCRIPTION, "catalogs", "", "Lcom/arflix/tv/data/model/CatalogPackItem;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getName", "getAuthor", "getVersion", "getDescription", "getCatalogs", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CatalogPackManifest implements Serializable {
    public static final int $stable = 0;
    private final String author;
    private final List<CatalogPackItem> catalogs;
    private final String description;
    private final String id;
    private final String name;
    private final String version;

    public CatalogPackManifest(String str, String str2, String str3, String str4, String str5, List<CatalogPackItem> list) {
        this.id = str;
        this.name = str2;
        this.author = str3;
        this.version = str4;
        this.description = str5;
        this.catalogs = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CatalogPackManifest copy$default(CatalogPackManifest catalogPackManifest, String str, String str2, String str3, String str4, String str5, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = catalogPackManifest.id;
        }
        if ((i10 & 2) != 0) {
            str2 = catalogPackManifest.name;
        }
        if ((i10 & 4) != 0) {
            str3 = catalogPackManifest.author;
        }
        if ((i10 & 8) != 0) {
            str4 = catalogPackManifest.version;
        }
        if ((i10 & 16) != 0) {
            str5 = catalogPackManifest.description;
        }
        if ((i10 & 32) != 0) {
            list = catalogPackManifest.catalogs;
        }
        String str6 = str5;
        List list2 = list;
        return catalogPackManifest.copy(str, str2, str3, str4, str6, list2);
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
    public final String getAuthor() {
        return this.author;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    public final List<CatalogPackItem> component6() {
        return this.catalogs;
    }

    public final CatalogPackManifest copy(String id, String name, String author, String version, String description, List<CatalogPackItem> catalogs) {
        return new CatalogPackManifest(id, name, author, version, description, catalogs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CatalogPackManifest)) {
            return false;
        }
        CatalogPackManifest catalogPackManifest = (CatalogPackManifest) other;
        return p.a(this.id, catalogPackManifest.id) && p.a(this.name, catalogPackManifest.name) && p.a(this.author, catalogPackManifest.author) && p.a(this.version, catalogPackManifest.version) && p.a(this.description, catalogPackManifest.description) && p.a(this.catalogs, catalogPackManifest.catalogs);
    }

    public final String getAuthor() {
        return this.author;
    }

    public final List<CatalogPackItem> getCatalogs() {
        return this.catalogs;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.author;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.version;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.description;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        List<CatalogPackItem> list = this.catalogs;
        return iHashCode5 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.author;
        String str4 = this.version;
        String str5 = this.description;
        List<CatalogPackItem> list = this.catalogs;
        StringBuilder sbR = a2.r("CatalogPackManifest(id=", str, ", name=", str2, ", author=");
        a.i(sbR, str3, ", version=", str4, ", description=");
        sbR.append(str5);
        sbR.append(", catalogs=");
        sbR.append(list);
        sbR.append(")");
        return sbR.toString();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CatalogPackManifest(String str, String str2, String str3, String str4, String str5, List list, int i10, h hVar) {
        List list2;
        String str6;
        str3 = (i10 & 4) != 0 ? null : str3;
        str4 = (i10 & 8) != 0 ? null : str4;
        if ((i10 & 16) != 0) {
            list2 = list;
            str6 = null;
        } else {
            list2 = list;
            str6 = str5;
        }
        this(str, str2, str3, str4, str6, list2);
    }
}
