package com.arflix.tv.data.api;

import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J7\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/api/StremioResourceDescriptor;", "", ContentDisposition.Parameters.Name, "", "types", "", "idPrefixes", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getTypes", "()Ljava/util/List;", "getIdPrefixes", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StremioResourceDescriptor {
    public static final int $stable = 0;
    private final List<String> idPrefixes;
    private final String name;
    private final List<String> types;

    public StremioResourceDescriptor(String str, List<String> list, List<String> list2) {
        this.name = str;
        this.types = list;
        this.idPrefixes = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StremioResourceDescriptor copy$default(StremioResourceDescriptor stremioResourceDescriptor, String str, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = stremioResourceDescriptor.name;
        }
        if ((i10 & 2) != 0) {
            list = stremioResourceDescriptor.types;
        }
        if ((i10 & 4) != 0) {
            list2 = stremioResourceDescriptor.idPrefixes;
        }
        return stremioResourceDescriptor.copy(str, list, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<String> component2() {
        return this.types;
    }

    public final List<String> component3() {
        return this.idPrefixes;
    }

    public final StremioResourceDescriptor copy(String name, List<String> types, List<String> idPrefixes) {
        return new StremioResourceDescriptor(name, types, idPrefixes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StremioResourceDescriptor)) {
            return false;
        }
        StremioResourceDescriptor stremioResourceDescriptor = (StremioResourceDescriptor) other;
        return p.a(this.name, stremioResourceDescriptor.name) && p.a(this.types, stremioResourceDescriptor.types) && p.a(this.idPrefixes, stremioResourceDescriptor.idPrefixes);
    }

    public final List<String> getIdPrefixes() {
        return this.idPrefixes;
    }

    public final String getName() {
        return this.name;
    }

    public final List<String> getTypes() {
        return this.types;
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        List<String> list = this.types;
        int iHashCode2 = (iHashCode + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.idPrefixes;
        return iHashCode2 + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        String str = this.name;
        List<String> list = this.types;
        List<String> list2 = this.idPrefixes;
        StringBuilder sb2 = new StringBuilder("StremioResourceDescriptor(name=");
        sb2.append(str);
        sb2.append(", types=");
        sb2.append(list);
        sb2.append(", idPrefixes=");
        return a0.c.q(sb2, list2, ")");
    }

    public /* synthetic */ StremioResourceDescriptor(String str, List list, List list2, int i10, kotlin.jvm.internal.h hVar) {
        this(str, (i10 & 2) != 0 ? null : list, (i10 & 4) != 0 ? null : list2);
    }
}
