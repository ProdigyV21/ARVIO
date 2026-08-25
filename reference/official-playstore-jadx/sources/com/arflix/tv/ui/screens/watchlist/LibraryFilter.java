package com.arflix.tv.ui.screens.watchlist;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\rR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/LibraryFilter;", "", "label", "", "isSort", "", "isSearch", "isRefresh", "iconOnly", "<init>", "(Ljava/lang/String;ZZZZ)V", "getLabel", "()Ljava/lang/String;", "()Z", "getIconOnly", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class LibraryFilter {
    private final boolean iconOnly;
    private final boolean isRefresh;
    private final boolean isSearch;
    private final boolean isSort;
    private final String label;

    public LibraryFilter(String str, boolean z, boolean z5, boolean z10, boolean z11) {
        this.label = str;
        this.isSort = z;
        this.isSearch = z5;
        this.isRefresh = z10;
        this.iconOnly = z11;
    }

    public static /* synthetic */ LibraryFilter copy$default(LibraryFilter libraryFilter, String str, boolean z, boolean z5, boolean z10, boolean z11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = libraryFilter.label;
        }
        if ((i10 & 2) != 0) {
            z = libraryFilter.isSort;
        }
        if ((i10 & 4) != 0) {
            z5 = libraryFilter.isSearch;
        }
        if ((i10 & 8) != 0) {
            z10 = libraryFilter.isRefresh;
        }
        if ((i10 & 16) != 0) {
            z11 = libraryFilter.iconOnly;
        }
        boolean z12 = z11;
        boolean z13 = z5;
        return libraryFilter.copy(str, z, z13, z10, z12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsSort() {
        return this.isSort;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsSearch() {
        return this.isSearch;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsRefresh() {
        return this.isRefresh;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIconOnly() {
        return this.iconOnly;
    }

    public final LibraryFilter copy(String label, boolean isSort, boolean isSearch, boolean isRefresh, boolean iconOnly) {
        return new LibraryFilter(label, isSort, isSearch, isRefresh, iconOnly);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryFilter)) {
            return false;
        }
        LibraryFilter libraryFilter = (LibraryFilter) other;
        return p.a(this.label, libraryFilter.label) && this.isSort == libraryFilter.isSort && this.isSearch == libraryFilter.isSearch && this.isRefresh == libraryFilter.isRefresh && this.iconOnly == libraryFilter.iconOnly;
    }

    public final boolean getIconOnly() {
        return this.iconOnly;
    }

    public final String getLabel() {
        return this.label;
    }

    public int hashCode() {
        return (((((((this.label.hashCode() * 31) + (this.isSort ? 1231 : 1237)) * 31) + (this.isSearch ? 1231 : 1237)) * 31) + (this.isRefresh ? 1231 : 1237)) * 31) + (this.iconOnly ? 1231 : 1237);
    }

    public final boolean isRefresh() {
        return this.isRefresh;
    }

    public final boolean isSearch() {
        return this.isSearch;
    }

    public final boolean isSort() {
        return this.isSort;
    }

    public String toString() {
        String str = this.label;
        boolean z = this.isSort;
        boolean z5 = this.isSearch;
        boolean z10 = this.isRefresh;
        boolean z11 = this.iconOnly;
        StringBuilder sb2 = new StringBuilder("LibraryFilter(label=");
        sb2.append(str);
        sb2.append(", isSort=");
        sb2.append(z);
        sb2.append(", isSearch=");
        v.f.h(sb2, z5, ", isRefresh=", z10, ", iconOnly=");
        return a0.c.m(")", z11, sb2);
    }

    public /* synthetic */ LibraryFilter(String str, boolean z, boolean z5, boolean z10, boolean z11, int i10, kotlin.jvm.internal.h hVar) {
        this(str, (i10 & 2) != 0 ? false : z, (i10 & 4) != 0 ? false : z5, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? false : z11);
    }
}
