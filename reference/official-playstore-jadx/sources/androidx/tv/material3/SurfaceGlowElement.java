package androidx.tv.material3;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/tv/material3/SurfaceGlowElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/tv/material3/g3;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SurfaceGlowElement extends ModifierNodeElement<g3> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Shape f5720i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f5721l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f5722m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final r7.l f5723n;

    public SurfaceGlowElement(Shape shape, float f10, long j10, r7.l lVar) {
        this.f5720i = shape;
        this.f5721l = f10;
        this.f5722m = j10;
        this.f5723n = lVar;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        g3 g3Var = new g3();
        g3Var.f6011i = this.f5720i;
        g3Var.f6012l = this.f5721l;
        g3Var.f6013m = this.f5722m;
        return g3Var;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final boolean equals(Object obj) {
        SurfaceGlowElement surfaceGlowElement = obj instanceof SurfaceGlowElement ? (SurfaceGlowElement) obj : null;
        return surfaceGlowElement != null && kotlin.jvm.internal.p.a(this.f5720i, surfaceGlowElement.f5720i) && this.f5721l == surfaceGlowElement.f5721l && Color.m3473equalsimpl0(this.f5722m, surfaceGlowElement.f5722m);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final int hashCode() {
        return Color.m3479hashCodeimpl(this.f5722m) + a0.c.b(this.f5721l, this.f5720i.hashCode() * 31, 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void inspectableProperties(InspectorInfo inspectorInfo) {
        this.f5723n.invoke(inspectorInfo);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        g3 g3Var = (g3) node;
        g3Var.f6011i = this.f5720i;
        g3Var.f6012l = this.f5721l;
        g3Var.f6013m = this.f5722m;
        if (g3Var.f6014n == null) {
            Paint Paint = AndroidPaint_androidKt.Paint();
            g3Var.f6014n = Paint;
            g3Var.f6015o = Paint.getInternalPaint();
        }
        g3Var.a();
    }
}
