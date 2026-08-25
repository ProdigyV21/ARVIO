package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J4\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0016\u0010\fJ\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\fR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\u000eR\u001d\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b \u0010\u0010¨\u0006!"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/CategoryMenuAction;", "", "", "labelRes", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "Lkotlin/Function0;", "Lx6/t0;", "onClick", "<init>", "(ILandroidx/compose/ui/graphics/vector/ImageVector;Lr7/a;)V", "component1", "()I", "component2", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "component3", "()Lr7/a;", "copy", "(ILandroidx/compose/ui/graphics/vector/ImageVector;Lr7/a;)Lcom/arflix/tv/ui/screens/tv/live/CategoryMenuAction;", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getLabelRes", "Landroidx/compose/ui/graphics/vector/ImageVector;", "getIcon", "Lr7/a;", "getOnClick", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class CategoryMenuAction {
    private final ImageVector icon;
    private final int labelRes;
    private final r7.a<x6.t0> onClick;

    public CategoryMenuAction(int i10, ImageVector imageVector, r7.a<x6.t0> aVar) {
        this.labelRes = i10;
        this.icon = imageVector;
        this.onClick = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CategoryMenuAction copy$default(CategoryMenuAction categoryMenuAction, int i10, ImageVector imageVector, r7.a aVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = categoryMenuAction.labelRes;
        }
        if ((i11 & 2) != 0) {
            imageVector = categoryMenuAction.icon;
        }
        if ((i11 & 4) != 0) {
            aVar = categoryMenuAction.onClick;
        }
        return categoryMenuAction.copy(i10, imageVector, aVar);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getLabelRes() {
        return this.labelRes;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ImageVector getIcon() {
        return this.icon;
    }

    public final r7.a<x6.t0> component3() {
        return this.onClick;
    }

    public final CategoryMenuAction copy(int labelRes, ImageVector icon, r7.a<x6.t0> onClick) {
        return new CategoryMenuAction(labelRes, icon, onClick);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CategoryMenuAction)) {
            return false;
        }
        CategoryMenuAction categoryMenuAction = (CategoryMenuAction) other;
        return this.labelRes == categoryMenuAction.labelRes && kotlin.jvm.internal.p.a(this.icon, categoryMenuAction.icon) && kotlin.jvm.internal.p.a(this.onClick, categoryMenuAction.onClick);
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final int getLabelRes() {
        return this.labelRes;
    }

    public final r7.a<x6.t0> getOnClick() {
        return this.onClick;
    }

    public int hashCode() {
        return this.onClick.hashCode() + ((this.icon.hashCode() + (this.labelRes * 31)) * 31);
    }

    public String toString() {
        return "CategoryMenuAction(labelRes=" + this.labelRes + ", icon=" + this.icon + ", onClick=" + this.onClick + ")";
    }
}
