package androidx.compose.material3;

import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Tooltip_androidKt$drawCaretWithPath$4 extends r implements l<ContentDrawScope, t0> {
    final /* synthetic */ LayoutCoordinates $anchorLayoutCoordinates;
    final /* synthetic */ long $containerColor;
    final /* synthetic */ Path $path;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Tooltip_androidKt$drawCaretWithPath$4(LayoutCoordinates layoutCoordinates, Path path, long j10) {
        super(1);
        this.$anchorLayoutCoordinates = layoutCoordinates;
        this.$path = path;
        this.$containerColor = j10;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ContentDrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(ContentDrawScope contentDrawScope) {
        if (this.$anchorLayoutCoordinates != null) {
            contentDrawScope.drawContent();
            androidx.compose.ui.graphics.drawscope.c.G(contentDrawScope, this.$path, this.$containerColor, 0.0f, null, null, 0, 60, null);
        }
    }
}
