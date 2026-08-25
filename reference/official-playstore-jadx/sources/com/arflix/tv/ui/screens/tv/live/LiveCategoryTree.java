package com.arflix.tv.ui.screens.tv.live;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u0017J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003JA\u0010\u001d\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004J\n\u0010#\u001a\u00020\u0017HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006$"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "", "top", "", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategory;", "global", "Lcom/arflix/tv/ui/screens/tv/live/LiveSection;", "countries", "adult", "hidden", "<init>", "(Ljava/util/List;Lcom/arflix/tv/ui/screens/tv/live/LiveSection;Lcom/arflix/tv/ui/screens/tv/live/LiveSection;Lcom/arflix/tv/ui/screens/tv/live/LiveSection;Lcom/arflix/tv/ui/screens/tv/live/LiveSection;)V", "getTop", "()Ljava/util/List;", "getGlobal", "()Lcom/arflix/tv/ui/screens/tv/live/LiveSection;", "getCountries", "getAdult", "getHidden", "allSections", "getAllSections", "byId", TtmlNode.ATTR_ID, "", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LiveCategoryTree {
    public static final int $stable = 8;
    private final LiveSection adult;
    private final List<LiveSection> allSections;
    private final LiveSection countries;
    private final LiveSection global;
    private final LiveSection hidden;
    private final List<LiveCategory> top;

    public LiveCategoryTree(List<LiveCategory> list, LiveSection liveSection, LiveSection liveSection2, LiveSection liveSection3, LiveSection liveSection4) {
        this.top = list;
        this.global = liveSection;
        this.countries = liveSection2;
        this.adult = liveSection3;
        this.hidden = liveSection4;
        this.allSections = t7.a.E(liveSection, liveSection2, liveSection3);
    }

    private static final LiveCategory byId$findIn(String str, LiveCategory liveCategory) {
        if (kotlin.jvm.internal.p.a(liveCategory.getId(), str)) {
            return liveCategory;
        }
        Iterator<T> it = liveCategory.getChildren().iterator();
        while (it.hasNext()) {
            LiveCategory liveCategoryById$findIn = byId$findIn(str, (LiveCategory) it.next());
            if (liveCategoryById$findIn != null) {
                return liveCategoryById$findIn;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LiveCategoryTree copy$default(LiveCategoryTree liveCategoryTree, List list, LiveSection liveSection, LiveSection liveSection2, LiveSection liveSection3, LiveSection liveSection4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = liveCategoryTree.top;
        }
        if ((i10 & 2) != 0) {
            liveSection = liveCategoryTree.global;
        }
        if ((i10 & 4) != 0) {
            liveSection2 = liveCategoryTree.countries;
        }
        if ((i10 & 8) != 0) {
            liveSection3 = liveCategoryTree.adult;
        }
        if ((i10 & 16) != 0) {
            liveSection4 = liveCategoryTree.hidden;
        }
        LiveSection liveSection5 = liveSection4;
        LiveSection liveSection6 = liveSection2;
        return liveCategoryTree.copy(list, liveSection, liveSection6, liveSection3, liveSection5);
    }

    public final LiveCategory byId(String id) {
        Iterator<T> it = this.top.iterator();
        while (it.hasNext()) {
            LiveCategory liveCategoryById$findIn = byId$findIn(id, (LiveCategory) it.next());
            if (liveCategoryById$findIn != null) {
                return liveCategoryById$findIn;
            }
        }
        Iterator<LiveSection> it2 = this.allSections.iterator();
        while (it2.hasNext()) {
            Iterator<LiveCategory> it3 = it2.next().getCategories().iterator();
            while (it3.hasNext()) {
                LiveCategory liveCategoryById$findIn2 = byId$findIn(id, it3.next());
                if (liveCategoryById$findIn2 != null) {
                    return liveCategoryById$findIn2;
                }
            }
        }
        return null;
    }

    public final List<LiveCategory> component1() {
        return this.top;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final LiveSection getGlobal() {
        return this.global;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final LiveSection getCountries() {
        return this.countries;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final LiveSection getAdult() {
        return this.adult;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final LiveSection getHidden() {
        return this.hidden;
    }

    public final LiveCategoryTree copy(List<LiveCategory> top, LiveSection global, LiveSection countries, LiveSection adult, LiveSection hidden) {
        return new LiveCategoryTree(top, global, countries, adult, hidden);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiveCategoryTree)) {
            return false;
        }
        LiveCategoryTree liveCategoryTree = (LiveCategoryTree) other;
        return kotlin.jvm.internal.p.a(this.top, liveCategoryTree.top) && kotlin.jvm.internal.p.a(this.global, liveCategoryTree.global) && kotlin.jvm.internal.p.a(this.countries, liveCategoryTree.countries) && kotlin.jvm.internal.p.a(this.adult, liveCategoryTree.adult) && kotlin.jvm.internal.p.a(this.hidden, liveCategoryTree.hidden);
    }

    public final LiveSection getAdult() {
        return this.adult;
    }

    public final List<LiveSection> getAllSections() {
        return this.allSections;
    }

    public final LiveSection getCountries() {
        return this.countries;
    }

    public final LiveSection getGlobal() {
        return this.global;
    }

    public final LiveSection getHidden() {
        return this.hidden;
    }

    public final List<LiveCategory> getTop() {
        return this.top;
    }

    public int hashCode() {
        return this.hidden.hashCode() + ((this.adult.hashCode() + ((this.countries.hashCode() + ((this.global.hashCode() + (this.top.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "LiveCategoryTree(top=" + this.top + ", global=" + this.global + ", countries=" + this.countries + ", adult=" + this.adult + ", hidden=" + this.hidden + ")";
    }

    public /* synthetic */ LiveCategoryTree(List list, LiveSection liveSection, LiveSection liveSection2, LiveSection liveSection3, LiveSection liveSection4, int i10, kotlin.jvm.internal.h hVar) {
        this(list, liveSection, liveSection2, liveSection3, (i10 & 16) != 0 ? new LiveSection("hidden", "HIDDEN", kotlin.collections.z.f19728i) : liveSection4);
    }
}
