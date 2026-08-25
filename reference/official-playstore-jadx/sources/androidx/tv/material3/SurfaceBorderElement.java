package androidx.tv.material3;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/tv/material3/SurfaceBorderElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/tv/material3/e3;", "tv-material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SurfaceBorderElement extends ModifierNodeElement<e3> {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Shape f5714i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d f5715l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final r7.l f5716m;

    public SurfaceBorderElement(Shape shape, d dVar, r7.l lVar) {
        this.f5714i = shape;
        this.f5715l = dVar;
        this.f5716m = lVar;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        e3 e3Var = new e3();
        e3Var.f5942i = this.f5714i;
        e3Var.f5943l = this.f5715l;
        return e3Var;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final boolean equals(Object obj) {
        SurfaceBorderElement surfaceBorderElement = obj instanceof SurfaceBorderElement ? (SurfaceBorderElement) obj : null;
        return surfaceBorderElement != null && kotlin.jvm.internal.p.a(this.f5714i, surfaceBorderElement.f5714i) && kotlin.jvm.internal.p.a(this.f5715l, surfaceBorderElement.f5715l);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final int hashCode() {
        return this.f5715l.hashCode() + (this.f5714i.hashCode() * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void inspectableProperties(InspectorInfo inspectorInfo) {
        this.f5716m.invoke(inspectorInfo);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        e3 e3Var = (e3) node;
        e3Var.f5942i = this.f5714i;
        e3Var.f5943l = this.f5715l;
    }
}
