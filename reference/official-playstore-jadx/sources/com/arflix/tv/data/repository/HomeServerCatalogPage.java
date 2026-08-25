package com.arflix.tv.data.repository;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerCatalogPage;", "", "items", "", "Lcom/arflix/tv/data/repository/HomeServerCatalogItem;", "hasMore", "", "<init>", "(Ljava/util/List;Z)V", "getItems", "()Ljava/util/List;", "getHasMore", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomeServerCatalogPage {
    public static final int $stable = 0;
    private final boolean hasMore;
    private final List<HomeServerCatalogItem> items;

    public HomeServerCatalogPage(List<HomeServerCatalogItem> list, boolean z) {
        this.items = list;
        this.hasMore = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HomeServerCatalogPage copy$default(HomeServerCatalogPage homeServerCatalogPage, List list, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = homeServerCatalogPage.items;
        }
        if ((i10 & 2) != 0) {
            z = homeServerCatalogPage.hasMore;
        }
        return homeServerCatalogPage.copy(list, z);
    }

    public final List<HomeServerCatalogItem> component1() {
        return this.items;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getHasMore() {
        return this.hasMore;
    }

    public final HomeServerCatalogPage copy(List<HomeServerCatalogItem> items, boolean hasMore) {
        return new HomeServerCatalogPage(items, hasMore);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeServerCatalogPage)) {
            return false;
        }
        HomeServerCatalogPage homeServerCatalogPage = (HomeServerCatalogPage) other;
        return kotlin.jvm.internal.p.a(this.items, homeServerCatalogPage.items) && this.hasMore == homeServerCatalogPage.hasMore;
    }

    public final boolean getHasMore() {
        return this.hasMore;
    }

    public final List<HomeServerCatalogItem> getItems() {
        return this.items;
    }

    public int hashCode() {
        return (this.items.hashCode() * 31) + (this.hasMore ? 1231 : 1237);
    }

    public String toString() {
        return "HomeServerCatalogPage(items=" + this.items + ", hasMore=" + this.hasMore + ")";
    }
}
