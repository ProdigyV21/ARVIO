package androidx.tv.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.node.DrawModifierNode;

/* JADX INFO: loaded from: classes3.dex */
public final class e3 extends Modifier.Node implements DrawModifierNode {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Shape f5942i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public d f5943l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public u3 f5944m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public q2 f5945n;

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(ContentDrawScope contentDrawScope) {
        ContentDrawScope contentDrawScope2;
        contentDrawScope.drawContent();
        d dVar = this.f5943l;
        BorderStroke borderStroke = dVar.f5890a;
        Shape shape = kotlin.jvm.internal.p.a(dVar.f5892c, j2.j.f19117a) ? this.f5942i : this.f5943l.f5892c;
        if (this.f5944m == null) {
            contentDrawScope2 = contentDrawScope;
            this.f5944m = new u3(shape, contentDrawScope.mo3916getSizeNHjbRc(), contentDrawScope.getLayoutDirection(), contentDrawScope2);
        } else {
            contentDrawScope2 = contentDrawScope;
        }
        if (this.f5945n == null) {
            float fMo285toPx0680j_4 = contentDrawScope2.mo285toPx0680j_4(borderStroke.getWidth());
            q2 q2Var = new q2();
            q2Var.f6332a = fMo285toPx0680j_4;
            this.f5945n = q2Var;
        }
        float f10 = -contentDrawScope2.mo285toPx0680j_4(this.f5943l.f5891b);
        contentDrawScope2.getDrawContext().getTransform().inset(f10, f10, f10, f10);
        Outline outlineC = this.f5944m.c(shape, contentDrawScope2.mo3916getSizeNHjbRc(), contentDrawScope2.getLayoutDirection(), contentDrawScope2);
        q2 q2Var2 = this.f5945n;
        float fMo285toPx0680j_42 = contentDrawScope2.mo285toPx0680j_4(borderStroke.getWidth());
        if (q2Var2.f6333b == null || q2Var2.f6332a != fMo285toPx0680j_42) {
            q2Var2.f6332a = fMo285toPx0680j_42;
            q2Var2.f6333b = new Stroke(q2Var2.f6332a, 0.0f, StrokeCap.INSTANCE.m3804getRoundKaPHkGw(), 0, null, 26, null);
        }
        OutlineKt.m3710drawOutlinehn5TExg$default(contentDrawScope, outlineC, borderStroke.getBrush(), 1.0f, q2Var2.f6333b, null, 0, 48, null);
        float f11 = -f10;
        contentDrawScope.getDrawContext().getTransform().inset(f11, f11, f11, f11);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final /* synthetic */ void onMeasureResultChanged() {
        androidx.compose.ui.node.b.a(this);
    }
}
