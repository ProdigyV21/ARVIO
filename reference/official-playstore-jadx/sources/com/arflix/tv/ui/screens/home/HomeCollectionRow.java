package com.arflix.tv.ui.screens.home;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.CatalogConfig;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/ui/screens/home/HomeCollectionRow;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "items", "", "Lcom/arflix/tv/data/model/CatalogConfig;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getTitle", "getItems", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomeCollectionRow {
    public static final int $stable = 0;
    private final String id;
    private final List<CatalogConfig> items;
    private final String title;

    public HomeCollectionRow(String str, String str2, List<CatalogConfig> list) {
        this.id = str;
        this.title = str2;
        this.items = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HomeCollectionRow copy$default(HomeCollectionRow homeCollectionRow, String str, String str2, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = homeCollectionRow.id;
        }
        if ((i10 & 2) != 0) {
            str2 = homeCollectionRow.title;
        }
        if ((i10 & 4) != 0) {
            list = homeCollectionRow.items;
        }
        return homeCollectionRow.copy(str, str2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    public final List<CatalogConfig> component3() {
        return this.items;
    }

    public final HomeCollectionRow copy(String id, String title, List<CatalogConfig> items) {
        return new HomeCollectionRow(id, title, items);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeCollectionRow)) {
            return false;
        }
        HomeCollectionRow homeCollectionRow = (HomeCollectionRow) other;
        return kotlin.jvm.internal.p.a(this.id, homeCollectionRow.id) && kotlin.jvm.internal.p.a(this.title, homeCollectionRow.title) && kotlin.jvm.internal.p.a(this.items, homeCollectionRow.items);
    }

    public final String getId() {
        return this.id;
    }

    public final List<CatalogConfig> getItems() {
        return this.items;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.items.hashCode() + androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.title);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.title;
        return a0.c.q(a2.r("HomeCollectionRow(id=", str, ", title=", str2, ", items="), this.items, ")");
    }
}
