package com.arflix.tv.ui.screens.tv.live;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J-\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveSection;", "", TtmlNode.ATTR_ID, "", "label", "categories", "", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategory;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getLabel", "getCategories", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LiveSection {
    public static final int $stable = 8;
    private final List<LiveCategory> categories;
    private final String id;
    private final String label;

    public LiveSection(String str, String str2, List<LiveCategory> list) {
        this.id = str;
        this.label = str2;
        this.categories = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LiveSection copy$default(LiveSection liveSection, String str, String str2, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = liveSection.id;
        }
        if ((i10 & 2) != 0) {
            str2 = liveSection.label;
        }
        if ((i10 & 4) != 0) {
            list = liveSection.categories;
        }
        return liveSection.copy(str, str2, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    public final List<LiveCategory> component3() {
        return this.categories;
    }

    public final LiveSection copy(String id, String label, List<LiveCategory> categories) {
        return new LiveSection(id, label, categories);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiveSection)) {
            return false;
        }
        LiveSection liveSection = (LiveSection) other;
        return kotlin.jvm.internal.p.a(this.id, liveSection.id) && kotlin.jvm.internal.p.a(this.label, liveSection.label) && kotlin.jvm.internal.p.a(this.categories, liveSection.categories);
    }

    public final List<LiveCategory> getCategories() {
        return this.categories;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }

    public int hashCode() {
        return this.categories.hashCode() + androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.label);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.label;
        return a0.c.q(androidx.fragment.app.a2.r("LiveSection(id=", str, ", label=", str2, ", categories="), this.categories, ")");
    }
}
