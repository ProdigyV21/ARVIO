package com.arflix.tv.ui.components;

import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/ui/components/BottomBarItem;", "", "labelRes", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "route", "", "<init>", "(ILandroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;)V", "getLabelRes", "()I", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "getRoute", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class BottomBarItem {
    public static final int $stable = 0;
    private final ImageVector icon;
    private final int labelRes;
    private final String route;

    public BottomBarItem(int i10, ImageVector imageVector, String str) {
        this.labelRes = i10;
        this.icon = imageVector;
        this.route = str;
    }

    public static /* synthetic */ BottomBarItem copy$default(BottomBarItem bottomBarItem, int i10, ImageVector imageVector, String str, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = bottomBarItem.labelRes;
        }
        if ((i11 & 2) != 0) {
            imageVector = bottomBarItem.icon;
        }
        if ((i11 & 4) != 0) {
            str = bottomBarItem.route;
        }
        return bottomBarItem.copy(i10, imageVector, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getLabelRes() {
        return this.labelRes;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ImageVector getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getRoute() {
        return this.route;
    }

    public final BottomBarItem copy(int labelRes, ImageVector icon, String route) {
        return new BottomBarItem(labelRes, icon, route);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BottomBarItem)) {
            return false;
        }
        BottomBarItem bottomBarItem = (BottomBarItem) other;
        return this.labelRes == bottomBarItem.labelRes && kotlin.jvm.internal.p.a(this.icon, bottomBarItem.icon) && kotlin.jvm.internal.p.a(this.route, bottomBarItem.route);
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final int getLabelRes() {
        return this.labelRes;
    }

    public final String getRoute() {
        return this.route;
    }

    public int hashCode() {
        return this.route.hashCode() + ((this.icon.hashCode() + (this.labelRes * 31)) * 31);
    }

    public String toString() {
        int i10 = this.labelRes;
        ImageVector imageVector = this.icon;
        String str = this.route;
        StringBuilder sb2 = new StringBuilder("BottomBarItem(labelRes=");
        sb2.append(i10);
        sb2.append(", icon=");
        sb2.append(imageVector);
        sb2.append(", route=");
        return a0.c.p(sb2, str, ")");
    }
}
