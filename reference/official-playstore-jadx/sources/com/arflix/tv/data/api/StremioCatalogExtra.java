package com.arflix.tv.data.api;

import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0003J6\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0004\u0010\fR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/StremioCatalogExtra;", "", ContentDisposition.Parameters.Name, "", "isRequired", "", "options", "", "<init>", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getOptions", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;)Lcom/arflix/tv/data/api/StremioCatalogExtra;", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StremioCatalogExtra {
    public static final int $stable = 0;
    private final Boolean isRequired;
    private final String name;
    private final List<String> options;

    public StremioCatalogExtra(String str, Boolean bool, List<String> list) {
        this.name = str;
        this.isRequired = bool;
        this.options = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StremioCatalogExtra copy$default(StremioCatalogExtra stremioCatalogExtra, String str, Boolean bool, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = stremioCatalogExtra.name;
        }
        if ((i10 & 2) != 0) {
            bool = stremioCatalogExtra.isRequired;
        }
        if ((i10 & 4) != 0) {
            list = stremioCatalogExtra.options;
        }
        return stremioCatalogExtra.copy(str, bool, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getIsRequired() {
        return this.isRequired;
    }

    public final List<String> component3() {
        return this.options;
    }

    public final StremioCatalogExtra copy(String name, Boolean isRequired, List<String> options) {
        return new StremioCatalogExtra(name, isRequired, options);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StremioCatalogExtra)) {
            return false;
        }
        StremioCatalogExtra stremioCatalogExtra = (StremioCatalogExtra) other;
        return p.a(this.name, stremioCatalogExtra.name) && p.a(this.isRequired, stremioCatalogExtra.isRequired) && p.a(this.options, stremioCatalogExtra.options);
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getOptions() {
        return this.options;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        Boolean bool = this.isRequired;
        int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        List<String> list = this.options;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    public final Boolean isRequired() {
        return this.isRequired;
    }

    public String toString() {
        String str = this.name;
        Boolean bool = this.isRequired;
        List<String> list = this.options;
        StringBuilder sb2 = new StringBuilder("StremioCatalogExtra(name=");
        sb2.append(str);
        sb2.append(", isRequired=");
        sb2.append(bool);
        sb2.append(", options=");
        return a0.c.q(sb2, list, ")");
    }

    public /* synthetic */ StremioCatalogExtra(String str, Boolean bool, List list, int i10, kotlin.jvm.internal.h hVar) {
        this(str, (i10 & 2) != 0 ? null : bool, (i10 & 4) != 0 ? null : list);
    }
}
