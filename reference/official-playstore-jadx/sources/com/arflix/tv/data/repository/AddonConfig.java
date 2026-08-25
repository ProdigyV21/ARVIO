package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.AddonType;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010\u0019\u001a\u00020\t2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/data/repository/AddonConfig;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, "baseUrl", LinkHeader.Parameters.Type, "Lcom/arflix/tv/data/model/AddonType;", "isEnabled", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/AddonType;Z)V", "getId", "()Ljava/lang/String;", "getName", "getBaseUrl", "getType", "()Lcom/arflix/tv/data/model/AddonType;", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AddonConfig {
    public static final int $stable = 0;
    private final String baseUrl;
    private final String id;
    private final boolean isEnabled;
    private final String name;
    private final AddonType type;

    public AddonConfig(String str, String str2, String str3, AddonType addonType, boolean z) {
        this.id = str;
        this.name = str2;
        this.baseUrl = str3;
        this.type = addonType;
        this.isEnabled = z;
    }

    public static /* synthetic */ AddonConfig copy$default(AddonConfig addonConfig, String str, String str2, String str3, AddonType addonType, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = addonConfig.id;
        }
        if ((i10 & 2) != 0) {
            str2 = addonConfig.name;
        }
        if ((i10 & 4) != 0) {
            str3 = addonConfig.baseUrl;
        }
        if ((i10 & 8) != 0) {
            addonType = addonConfig.type;
        }
        if ((i10 & 16) != 0) {
            z = addonConfig.isEnabled;
        }
        boolean z5 = z;
        String str4 = str3;
        return addonConfig.copy(str, str2, str4, addonType, z5);
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
    public final String getBaseUrl() {
        return this.baseUrl;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final AddonType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    public final AddonConfig copy(String id, String name, String baseUrl, AddonType type, boolean isEnabled) {
        return new AddonConfig(id, name, baseUrl, type, isEnabled);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonConfig)) {
            return false;
        }
        AddonConfig addonConfig = (AddonConfig) other;
        return kotlin.jvm.internal.p.a(this.id, addonConfig.id) && kotlin.jvm.internal.p.a(this.name, addonConfig.name) && kotlin.jvm.internal.p.a(this.baseUrl, addonConfig.baseUrl) && this.type == addonConfig.type && this.isEnabled == addonConfig.isEnabled;
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final AddonType getType() {
        return this.type;
    }

    public int hashCode() {
        return ((this.type.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.name), 31, this.baseUrl)) * 31) + (this.isEnabled ? 1231 : 1237);
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.name;
        String str3 = this.baseUrl;
        AddonType addonType = this.type;
        boolean z = this.isEnabled;
        StringBuilder sbR = a2.r("AddonConfig(id=", str, ", name=", str2, ", baseUrl=");
        sbR.append(str3);
        sbR.append(", type=");
        sbR.append(addonType);
        sbR.append(", isEnabled=");
        return a0.c.m(")", z, sbR);
    }

    public /* synthetic */ AddonConfig(String str, String str2, String str3, AddonType addonType, boolean z, int i10, kotlin.jvm.internal.h hVar) {
        this(str, str2, str3, addonType, (i10 & 16) != 0 ? true : z);
    }
}
