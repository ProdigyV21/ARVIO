package com.arflix.tv.data.model;

import a0.c;
import io.ktor.http.ContentDisposition;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0003J/\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00052\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/model/AddonCatalogExtra;", "Ljava/io/Serializable;", ContentDisposition.Parameters.Name, "", "isRequired", "", "options", "", "<init>", "(Ljava/lang/String;ZLjava/util/List;)V", "getName", "()Ljava/lang/String;", "()Z", "getOptions", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AddonCatalogExtra implements Serializable {
    public static final int $stable = 0;
    private final boolean isRequired;
    private final String name;
    private final List<String> options;

    public AddonCatalogExtra(String str, boolean z, List<String> list) {
        this.name = str;
        this.isRequired = z;
        this.options = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AddonCatalogExtra copy$default(AddonCatalogExtra addonCatalogExtra, String str, boolean z, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = addonCatalogExtra.name;
        }
        if ((i10 & 2) != 0) {
            z = addonCatalogExtra.isRequired;
        }
        if ((i10 & 4) != 0) {
            list = addonCatalogExtra.options;
        }
        return addonCatalogExtra.copy(str, z, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsRequired() {
        return this.isRequired;
    }

    public final List<String> component3() {
        return this.options;
    }

    public final AddonCatalogExtra copy(String name, boolean isRequired, List<String> options) {
        return new AddonCatalogExtra(name, isRequired, options);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddonCatalogExtra)) {
            return false;
        }
        AddonCatalogExtra addonCatalogExtra = (AddonCatalogExtra) other;
        return p.a(this.name, addonCatalogExtra.name) && this.isRequired == addonCatalogExtra.isRequired && p.a(this.options, addonCatalogExtra.options);
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getOptions() {
        return this.options;
    }

    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + (this.isRequired ? 1231 : 1237)) * 31;
        List<String> list = this.options;
        return iHashCode + (list == null ? 0 : list.hashCode());
    }

    public final boolean isRequired() {
        return this.isRequired;
    }

    public String toString() {
        String str = this.name;
        boolean z = this.isRequired;
        List<String> list = this.options;
        StringBuilder sb2 = new StringBuilder("AddonCatalogExtra(name=");
        sb2.append(str);
        sb2.append(", isRequired=");
        sb2.append(z);
        sb2.append(", options=");
        return c.q(sb2, list, ")");
    }

    public /* synthetic */ AddonCatalogExtra(String str, boolean z, List list, int i10, h hVar) {
        this(str, (i10 & 2) != 0 ? false : z, (i10 & 4) != 0 ? null : list);
    }
}
