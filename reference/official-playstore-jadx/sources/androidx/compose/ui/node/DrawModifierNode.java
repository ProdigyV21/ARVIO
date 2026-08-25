package androidx.compose.ui.node;

import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0013\u0010\u0004\u001a\u00020\u0003*\u00020\u0002H&¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/DelegatableNode;", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "draw", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "onMeasureResultChanged", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface DrawModifierNode extends DelegatableNode {
    void draw(ContentDrawScope contentDrawScope);

    void onMeasureResultChanged();
}
