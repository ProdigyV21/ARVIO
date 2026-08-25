package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J+\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/api/KitsuIncludedItem;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Type, "attributes", "Lcom/arflix/tv/data/api/KitsuAnimeAttributes;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/KitsuAnimeAttributes;)V", "getId", "()Ljava/lang/String;", "getType", "getAttributes", "()Lcom/arflix/tv/data/api/KitsuAnimeAttributes;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class KitsuIncludedItem {
    public static final int $stable = 0;
    private final KitsuAnimeAttributes attributes;
    private final String id;
    private final String type;

    public KitsuIncludedItem(String str, String str2, KitsuAnimeAttributes kitsuAnimeAttributes) {
        this.id = str;
        this.type = str2;
        this.attributes = kitsuAnimeAttributes;
    }

    public static /* synthetic */ KitsuIncludedItem copy$default(KitsuIncludedItem kitsuIncludedItem, String str, String str2, KitsuAnimeAttributes kitsuAnimeAttributes, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = kitsuIncludedItem.id;
        }
        if ((i10 & 2) != 0) {
            str2 = kitsuIncludedItem.type;
        }
        if ((i10 & 4) != 0) {
            kitsuAnimeAttributes = kitsuIncludedItem.attributes;
        }
        return kitsuIncludedItem.copy(str, str2, kitsuAnimeAttributes);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final KitsuAnimeAttributes getAttributes() {
        return this.attributes;
    }

    public final KitsuIncludedItem copy(String id, String type, KitsuAnimeAttributes attributes) {
        return new KitsuIncludedItem(id, type, attributes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KitsuIncludedItem)) {
            return false;
        }
        KitsuIncludedItem kitsuIncludedItem = (KitsuIncludedItem) other;
        return p.a(this.id, kitsuIncludedItem.id) && p.a(this.type, kitsuIncludedItem.type) && p.a(this.attributes, kitsuIncludedItem.attributes);
    }

    public final KitsuAnimeAttributes getAttributes() {
        return this.attributes;
    }

    public final String getId() {
        return this.id;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        String str = this.type;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        KitsuAnimeAttributes kitsuAnimeAttributes = this.attributes;
        return iHashCode2 + (kitsuAnimeAttributes != null ? kitsuAnimeAttributes.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.type;
        KitsuAnimeAttributes kitsuAnimeAttributes = this.attributes;
        StringBuilder sbR = a2.r("KitsuIncludedItem(id=", str, ", type=", str2, ", attributes=");
        sbR.append(kitsuAnimeAttributes);
        sbR.append(")");
        return sbR.toString();
    }
}
