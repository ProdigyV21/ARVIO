package com.arflix.tv.ui.screens.tv.live;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/TouchCategoryRailItem;", "", TtmlNode.ATTR_ID, "", "label", "count", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;I)V", "getId", "()Ljava/lang/String;", "getLabel", "getCount", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class TouchCategoryRailItem {
    private final int count;
    private final String id;
    private final String label;

    public TouchCategoryRailItem(String str, String str2, int i10) {
        this.id = str;
        this.label = str2;
        this.count = i10;
    }

    public static /* synthetic */ TouchCategoryRailItem copy$default(TouchCategoryRailItem touchCategoryRailItem, String str, String str2, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = touchCategoryRailItem.id;
        }
        if ((i11 & 2) != 0) {
            str2 = touchCategoryRailItem.label;
        }
        if ((i11 & 4) != 0) {
            i10 = touchCategoryRailItem.count;
        }
        return touchCategoryRailItem.copy(str, str2, i10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getCount() {
        return this.count;
    }

    public final TouchCategoryRailItem copy(String id, String label, int count) {
        return new TouchCategoryRailItem(id, label, count);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TouchCategoryRailItem)) {
            return false;
        }
        TouchCategoryRailItem touchCategoryRailItem = (TouchCategoryRailItem) other;
        return kotlin.jvm.internal.p.a(this.id, touchCategoryRailItem.id) && kotlin.jvm.internal.p.a(this.label, touchCategoryRailItem.label) && this.count == touchCategoryRailItem.count;
    }

    public final int getCount() {
        return this.count;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }

    public int hashCode() {
        return androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.label) + this.count;
    }

    public String toString() {
        return androidx.compose.material3.d.j(this.count, ")", androidx.fragment.app.a2.r("TouchCategoryRailItem(id=", this.id, ", label=", this.label, ", count="));
    }
}
