package com.arflix.tv.ui.screens.home;

import androidx.fragment.app.a2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0083\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/ui/screens/home/HomeFocusedHeroSnapshot;", "", "rowIndex", "", "itemIndex", "focusedItemKey", "", "heroItemKey", "<init>", "(IILjava/lang/String;Ljava/lang/String;)V", "getRowIndex", "()I", "getItemIndex", "getFocusedItemKey", "()Ljava/lang/String;", "getHeroItemKey", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class HomeFocusedHeroSnapshot {
    private final String focusedItemKey;
    private final String heroItemKey;
    private final int itemIndex;
    private final int rowIndex;

    public HomeFocusedHeroSnapshot(int i10, int i11, String str, String str2) {
        this.rowIndex = i10;
        this.itemIndex = i11;
        this.focusedItemKey = str;
        this.heroItemKey = str2;
    }

    public static /* synthetic */ HomeFocusedHeroSnapshot copy$default(HomeFocusedHeroSnapshot homeFocusedHeroSnapshot, int i10, int i11, String str, String str2, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = homeFocusedHeroSnapshot.rowIndex;
        }
        if ((i12 & 2) != 0) {
            i11 = homeFocusedHeroSnapshot.itemIndex;
        }
        if ((i12 & 4) != 0) {
            str = homeFocusedHeroSnapshot.focusedItemKey;
        }
        if ((i12 & 8) != 0) {
            str2 = homeFocusedHeroSnapshot.heroItemKey;
        }
        return homeFocusedHeroSnapshot.copy(i10, i11, str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getRowIndex() {
        return this.rowIndex;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getItemIndex() {
        return this.itemIndex;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getFocusedItemKey() {
        return this.focusedItemKey;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getHeroItemKey() {
        return this.heroItemKey;
    }

    public final HomeFocusedHeroSnapshot copy(int rowIndex, int itemIndex, String focusedItemKey, String heroItemKey) {
        return new HomeFocusedHeroSnapshot(rowIndex, itemIndex, focusedItemKey, heroItemKey);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeFocusedHeroSnapshot)) {
            return false;
        }
        HomeFocusedHeroSnapshot homeFocusedHeroSnapshot = (HomeFocusedHeroSnapshot) other;
        return this.rowIndex == homeFocusedHeroSnapshot.rowIndex && this.itemIndex == homeFocusedHeroSnapshot.itemIndex && kotlin.jvm.internal.p.a(this.focusedItemKey, homeFocusedHeroSnapshot.focusedItemKey) && kotlin.jvm.internal.p.a(this.heroItemKey, homeFocusedHeroSnapshot.heroItemKey);
    }

    public final String getFocusedItemKey() {
        return this.focusedItemKey;
    }

    public final String getHeroItemKey() {
        return this.heroItemKey;
    }

    public final int getItemIndex() {
        return this.itemIndex;
    }

    public final int getRowIndex() {
        return this.rowIndex;
    }

    public int hashCode() {
        return this.heroItemKey.hashCode() + androidx.compose.foundation.c.c(((this.rowIndex * 31) + this.itemIndex) * 31, 31, this.focusedItemKey);
    }

    public String toString() {
        int i10 = this.rowIndex;
        int i11 = this.itemIndex;
        return a2.n(androidx.compose.foundation.c.v("HomeFocusedHeroSnapshot(rowIndex=", i10, ", itemIndex=", i11, ", focusedItemKey="), this.focusedItemKey, ", heroItemKey=", this.heroItemKey, ")");
    }
}
