package com.arflix.tv.ui.components;

import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J4\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0003\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0016\u0010\u000eJ\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\u000eR\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b \u0010\u0010¨\u0006!"}, d2 = {"Lcom/arflix/tv/ui/components/MenuItem;", "", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "", "labelRes", "Lkotlin/Function0;", "Lx6/t0;", "action", "<init>", "(Landroidx/compose/ui/graphics/vector/ImageVector;ILr7/a;)V", "component1", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "component2", "()I", "component3", "()Lr7/a;", "copy", "(Landroidx/compose/ui/graphics/vector/ImageVector;ILr7/a;)Lcom/arflix/tv/ui/components/MenuItem;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/ui/graphics/vector/ImageVector;", "getIcon", "I", "getLabelRes", "Lr7/a;", "getAction", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class MenuItem {
    private final r7.a<x6.t0> action;
    private final ImageVector icon;
    private final int labelRes;

    public MenuItem(ImageVector imageVector, int i10, r7.a<x6.t0> aVar) {
        this.icon = imageVector;
        this.labelRes = i10;
        this.action = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MenuItem copy$default(MenuItem menuItem, ImageVector imageVector, int i10, r7.a aVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            imageVector = menuItem.icon;
        }
        if ((i11 & 2) != 0) {
            i10 = menuItem.labelRes;
        }
        if ((i11 & 4) != 0) {
            aVar = menuItem.action;
        }
        return menuItem.copy(imageVector, i10, aVar);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ImageVector getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getLabelRes() {
        return this.labelRes;
    }

    public final r7.a<x6.t0> component3() {
        return this.action;
    }

    public final MenuItem copy(ImageVector icon, int labelRes, r7.a<x6.t0> action) {
        return new MenuItem(icon, labelRes, action);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MenuItem)) {
            return false;
        }
        MenuItem menuItem = (MenuItem) other;
        return kotlin.jvm.internal.p.a(this.icon, menuItem.icon) && this.labelRes == menuItem.labelRes && kotlin.jvm.internal.p.a(this.action, menuItem.action);
    }

    public final r7.a<x6.t0> getAction() {
        return this.action;
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final int getLabelRes() {
        return this.labelRes;
    }

    public int hashCode() {
        return this.action.hashCode() + (((this.icon.hashCode() * 31) + this.labelRes) * 31);
    }

    public String toString() {
        return "MenuItem(icon=" + this.icon + ", labelRes=" + this.labelRes + ", action=" + this.action + ")";
    }
}
