package androidx.compose.foundation;

import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/draw/DrawResult;", "Landroidx/compose/ui/draw/CacheDrawScope;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class BorderModifierNode$drawWithCacheModifierNode$1 extends r implements l<CacheDrawScope, DrawResult> {
    final /* synthetic */ BorderModifierNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BorderModifierNode$drawWithCacheModifierNode$1(BorderModifierNode borderModifierNode) {
        super(1);
        this.this$0 = borderModifierNode;
    }

    @Override // r7.l
    public final DrawResult invoke(CacheDrawScope cacheDrawScope) {
        float width = this.this$0.getWidth();
        cacheDrawScope.getClass();
        if (androidx.compose.ui.unit.a.g(cacheDrawScope, width) < 0.0f || Size.m3304getMinDimensionimpl(cacheDrawScope.m3140getSizeNHjbRc()) <= 0.0f) {
            return BorderKt.drawContentWithoutBorder(cacheDrawScope);
        }
        float f10 = 2;
        float fMin = Math.min(Dp.m5683equalsimpl0(this.this$0.getWidth(), Dp.INSTANCE.m5696getHairlineD9Ej5fM()) ? 1.0f : (float) Math.ceil(androidx.compose.ui.unit.a.g(cacheDrawScope, this.this$0.getWidth())), (float) Math.ceil(Size.m3304getMinDimensionimpl(cacheDrawScope.m3140getSizeNHjbRc()) / f10));
        float f11 = fMin / f10;
        long jOffset = OffsetKt.Offset(f11, f11);
        long jSize = SizeKt.Size(Size.m3305getWidthimpl(cacheDrawScope.m3140getSizeNHjbRc()) - fMin, Size.m3302getHeightimpl(cacheDrawScope.m3140getSizeNHjbRc()) - fMin);
        boolean z = f10 * fMin > Size.m3304getMinDimensionimpl(cacheDrawScope.m3140getSizeNHjbRc());
        Outline outlineMo230createOutlinePq9zytI = this.this$0.getShape().mo230createOutlinePq9zytI(cacheDrawScope.m3140getSizeNHjbRc(), cacheDrawScope.getLayoutDirection(), cacheDrawScope);
        if (outlineMo230createOutlinePq9zytI instanceof Outline.Generic) {
            BorderModifierNode borderModifierNode = this.this$0;
            return borderModifierNode.drawGenericBorder(cacheDrawScope, borderModifierNode.getBrush(), (Outline.Generic) outlineMo230createOutlinePq9zytI, z, fMin);
        }
        if (outlineMo230createOutlinePq9zytI instanceof Outline.Rounded) {
            BorderModifierNode borderModifierNode2 = this.this$0;
            return borderModifierNode2.m197drawRoundRectBorderJqoCqck(cacheDrawScope, borderModifierNode2.getBrush(), (Outline.Rounded) outlineMo230createOutlinePq9zytI, jOffset, jSize, z, fMin);
        }
        if (outlineMo230createOutlinePq9zytI instanceof Outline.Rectangle) {
            return BorderKt.m194drawRectBorderNsqcLGU(cacheDrawScope, this.this$0.getBrush(), jOffset, jSize, z, fMin);
        }
        throw new NoWhenBranchMatchedException();
    }
}
