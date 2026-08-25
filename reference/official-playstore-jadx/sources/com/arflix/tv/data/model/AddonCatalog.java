package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0003J\u0011\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007HÆ\u0003JK\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004J\n\u0010\u001f\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/model/AddonCatalog;", "Ljava/io/Serializable;", LinkHeader.Parameters.Type, "", TtmlNode.ATTR_ID, ContentDisposition.Parameters.Name, "genres", "", "extra", "Lcom/arflix/tv/data/model/AddonCatalogExtra;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getType", "()Ljava/lang/String;", "getId", "getName", "getGenres", "()Ljava/util/List;", "getExtra", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AddonCatalog implements Serializable {
    public static final int $stable = 0;
    private final List<AddonCatalogExtra> extra;
    private final List<String> genres;
    private final String id;
    private final String name;
    private final String type;

    public AddonCatalog(String str, String str2, String str3, List<String> list, List<AddonCatalogExtra> list2) {
        this.type = str;
        this.id = str2;
        this.name = str3;
        this.genres = list;
        this.extra = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AddonCatalog copy$default(AddonCatalog addonCatalog, String str, String str2, String str3, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = addonCatalog.type;
        }
        if ((i10 & 2) != 0) {
            str2 = addonCatalog.id;
        }
        if ((i10 & 4) != 0) {
            str3 = addonCatalog.name;
        }
        if ((i10 & 8) != 0) {
            list = addonCatalog.genres;
        }
        if ((i10 & 16) != 0) {
            list2 = addonCatalog.extra;
        }
        List list3 = list2;
        String str4 = str3;
        return addonCatalog.copy(str, str2, str4, list, list3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<String> component4() {
        return this.genres;
    }

    public final List<AddonCatalogExtra> component5() {
        return this.extra;
    }

    public final AddonCatalog copy(String type, String id, String name, List<String> genres, List<AddonCatalogExtra> extra) {
        return new AddonCatalog(type, id, name, genres, extra);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonCatalog)) {
            return false;
        }
        AddonCatalog addonCatalog = (AddonCatalog) other;
        return p.a(this.type, addonCatalog.type) && p.a(this.id, addonCatalog.id) && p.a(this.name, addonCatalog.name) && p.a(this.genres, addonCatalog.genres) && p.a(this.extra, addonCatalog.extra);
    }

    public final List<AddonCatalogExtra> getExtra() {
        return this.extra;
    }

    public final List<String> getGenres() {
        return this.genres;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int iC = c.c(c.c(this.type.hashCode() * 31, 31, this.id), 31, this.name);
        List<String> list = this.genres;
        int iHashCode = (iC + (list == null ? 0 : list.hashCode())) * 31;
        List<AddonCatalogExtra> list2 = this.extra;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        String str = this.type;
        String str2 = this.id;
        String str3 = this.name;
        List<String> list = this.genres;
        List<AddonCatalogExtra> list2 = this.extra;
        StringBuilder sbR = a2.r("AddonCatalog(type=", str, ", id=", str2, ", name=");
        sbR.append(str3);
        sbR.append(", genres=");
        sbR.append(list);
        sbR.append(", extra=");
        return a0.c.q(sbR, list2, ")");
    }

    public /* synthetic */ AddonCatalog(String str, String str2, String str3, List list, List list2, int i10, h hVar) {
        this(str, str2, (i10 & 4) != 0 ? "" : str3, (i10 & 8) != 0 ? null : list, (i10 & 16) != 0 ? null : list2);
    }
}
