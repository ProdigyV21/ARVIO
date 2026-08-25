package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003J7\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/api/KitsuMapping;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Type, "attributes", "Lcom/arflix/tv/data/api/KitsuMappingAttributes;", "relationships", "Lcom/arflix/tv/data/api/KitsuMappingRelationships;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/KitsuMappingAttributes;Lcom/arflix/tv/data/api/KitsuMappingRelationships;)V", "getId", "()Ljava/lang/String;", "getType", "getAttributes", "()Lcom/arflix/tv/data/api/KitsuMappingAttributes;", "getRelationships", "()Lcom/arflix/tv/data/api/KitsuMappingRelationships;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class KitsuMapping {
    public static final int $stable = 0;
    private final KitsuMappingAttributes attributes;
    private final String id;
    private final KitsuMappingRelationships relationships;
    private final String type;

    public KitsuMapping(String str, String str2, KitsuMappingAttributes kitsuMappingAttributes, KitsuMappingRelationships kitsuMappingRelationships) {
        this.id = str;
        this.type = str2;
        this.attributes = kitsuMappingAttributes;
        this.relationships = kitsuMappingRelationships;
    }

    public static /* synthetic */ KitsuMapping copy$default(KitsuMapping kitsuMapping, String str, String str2, KitsuMappingAttributes kitsuMappingAttributes, KitsuMappingRelationships kitsuMappingRelationships, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = kitsuMapping.id;
        }
        if ((i10 & 2) != 0) {
            str2 = kitsuMapping.type;
        }
        if ((i10 & 4) != 0) {
            kitsuMappingAttributes = kitsuMapping.attributes;
        }
        if ((i10 & 8) != 0) {
            kitsuMappingRelationships = kitsuMapping.relationships;
        }
        return kitsuMapping.copy(str, str2, kitsuMappingAttributes, kitsuMappingRelationships);
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
    public final KitsuMappingAttributes getAttributes() {
        return this.attributes;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final KitsuMappingRelationships getRelationships() {
        return this.relationships;
    }

    public final KitsuMapping copy(String id, String type, KitsuMappingAttributes attributes, KitsuMappingRelationships relationships) {
        return new KitsuMapping(id, type, attributes, relationships);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KitsuMapping)) {
            return false;
        }
        KitsuMapping kitsuMapping = (KitsuMapping) other;
        return p.a(this.id, kitsuMapping.id) && p.a(this.type, kitsuMapping.type) && p.a(this.attributes, kitsuMapping.attributes) && p.a(this.relationships, kitsuMapping.relationships);
    }

    public final KitsuMappingAttributes getAttributes() {
        return this.attributes;
    }

    public final String getId() {
        return this.id;
    }

    public final KitsuMappingRelationships getRelationships() {
        return this.relationships;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        String str = this.type;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        KitsuMappingAttributes kitsuMappingAttributes = this.attributes;
        int iHashCode3 = (iHashCode2 + (kitsuMappingAttributes == null ? 0 : kitsuMappingAttributes.hashCode())) * 31;
        KitsuMappingRelationships kitsuMappingRelationships = this.relationships;
        return iHashCode3 + (kitsuMappingRelationships != null ? kitsuMappingRelationships.hashCode() : 0);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.type;
        KitsuMappingAttributes kitsuMappingAttributes = this.attributes;
        KitsuMappingRelationships kitsuMappingRelationships = this.relationships;
        StringBuilder sbR = a2.r("KitsuMapping(id=", str, ", type=", str2, ", attributes=");
        sbR.append(kitsuMappingAttributes);
        sbR.append(", relationships=");
        sbR.append(kitsuMappingRelationships);
        sbR.append(")");
        return sbR.toString();
    }
}
