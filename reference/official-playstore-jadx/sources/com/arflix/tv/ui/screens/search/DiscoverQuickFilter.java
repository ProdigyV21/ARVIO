package com.arflix.tv.ui.screens.search;

import androidx.fragment.app.a2;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\f\b\u0082\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000e\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J>\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0015\u0010\rJ\u0010\u0010\u0017\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001a\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\rR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001e\u0010\rR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b\u0006\u0010\u0010R\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b!\u0010\u0012¨\u0006\""}, d2 = {"Lcom/arflix/tv/ui/screens/search/DiscoverQuickFilter;", "", "", "key", "label", "", "isSelected", "Lkotlin/Function0;", "Lx6/t0;", "onSelect", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLr7/a;)V", "component1", "()Ljava/lang/String;", "component2", "component3", "()Z", "component4", "()Lr7/a;", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLr7/a;)Lcom/arflix/tv/ui/screens/search/DiscoverQuickFilter;", "toString", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getKey", "getLabel", "Z", "Lr7/a;", "getOnSelect", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class DiscoverQuickFilter {
    private final boolean isSelected;
    private final String key;
    private final String label;
    private final r7.a<t0> onSelect;

    public DiscoverQuickFilter(String str, String str2, boolean z, r7.a<t0> aVar) {
        this.key = str;
        this.label = str2;
        this.isSelected = z;
        this.onSelect = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DiscoverQuickFilter copy$default(DiscoverQuickFilter discoverQuickFilter, String str, String str2, boolean z, r7.a aVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = discoverQuickFilter.key;
        }
        if ((i10 & 2) != 0) {
            str2 = discoverQuickFilter.label;
        }
        if ((i10 & 4) != 0) {
            z = discoverQuickFilter.isSelected;
        }
        if ((i10 & 8) != 0) {
            aVar = discoverQuickFilter.onSelect;
        }
        return discoverQuickFilter.copy(str, str2, z, aVar);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsSelected() {
        return this.isSelected;
    }

    public final r7.a<t0> component4() {
        return this.onSelect;
    }

    public final DiscoverQuickFilter copy(String key, String label, boolean isSelected, r7.a<t0> onSelect) {
        return new DiscoverQuickFilter(key, label, isSelected, onSelect);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DiscoverQuickFilter)) {
            return false;
        }
        DiscoverQuickFilter discoverQuickFilter = (DiscoverQuickFilter) other;
        return kotlin.jvm.internal.p.a(this.key, discoverQuickFilter.key) && kotlin.jvm.internal.p.a(this.label, discoverQuickFilter.label) && this.isSelected == discoverQuickFilter.isSelected && kotlin.jvm.internal.p.a(this.onSelect, discoverQuickFilter.onSelect);
    }

    public final String getKey() {
        return this.key;
    }

    public final String getLabel() {
        return this.label;
    }

    public final r7.a<t0> getOnSelect() {
        return this.onSelect;
    }

    public int hashCode() {
        return this.onSelect.hashCode() + ((androidx.compose.foundation.c.c(this.key.hashCode() * 31, 31, this.label) + (this.isSelected ? 1231 : 1237)) * 31);
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public String toString() {
        String str = this.key;
        String str2 = this.label;
        boolean z = this.isSelected;
        r7.a<t0> aVar = this.onSelect;
        StringBuilder sbR = a2.r("DiscoverQuickFilter(key=", str, ", label=", str2, ", isSelected=");
        sbR.append(z);
        sbR.append(", onSelect=");
        sbR.append(aVar);
        sbR.append(")");
        return sbR.toString();
    }
}
