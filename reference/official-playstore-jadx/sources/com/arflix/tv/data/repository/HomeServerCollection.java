package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerCollection;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, LinkHeader.Parameters.Type, "enabled", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getId", "()Ljava/lang/String;", "getName", "getType", "getEnabled", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomeServerCollection {
    public static final int $stable = 0;
    private final boolean enabled;
    private final String id;
    private final String name;
    private final String type;

    public HomeServerCollection() {
        this(null, null, null, false, 15, null);
    }

    public static /* synthetic */ HomeServerCollection copy$default(HomeServerCollection homeServerCollection, String str, String str2, String str3, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = homeServerCollection.id;
        }
        if ((i10 & 2) != 0) {
            str2 = homeServerCollection.name;
        }
        if ((i10 & 4) != 0) {
            str3 = homeServerCollection.type;
        }
        if ((i10 & 8) != 0) {
            z = homeServerCollection.enabled;
        }
        return homeServerCollection.copy(str, str2, str3, z);
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
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getEnabled() {
        return this.enabled;
    }

    public final HomeServerCollection copy(String id, String name, String type, boolean enabled) {
        return new HomeServerCollection(id, name, type, enabled);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeServerCollection)) {
            return false;
        }
        HomeServerCollection homeServerCollection = (HomeServerCollection) other;
        return kotlin.jvm.internal.p.a(this.id, homeServerCollection.id) && kotlin.jvm.internal.p.a(this.name, homeServerCollection.name) && kotlin.jvm.internal.p.a(this.type, homeServerCollection.type) && this.enabled == homeServerCollection.enabled;
    }

    public final boolean getEnabled() {
        return this.enabled;
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
        return androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.name), 31, this.type) + (this.enabled ? 1231 : 1237);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.type;
        boolean z = this.enabled;
        StringBuilder sbR = a2.r("HomeServerCollection(id=", str, ", name=", str2, ", type=");
        sbR.append(str3);
        sbR.append(", enabled=");
        sbR.append(z);
        sbR.append(")");
        return sbR.toString();
    }

    public HomeServerCollection(String str, String str2, String str3, boolean z) {
        this.id = str;
        this.name = str2;
        this.type = str3;
        this.enabled = z;
    }

    public /* synthetic */ HomeServerCollection(String str, String str2, String str3, boolean z, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? "" : str3, (i10 & 8) != 0 ? true : z);
    }
}
