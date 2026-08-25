package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class OutlinedTextFieldKt$outlineCutout$1 extends r implements l<ContentDrawScope, t0> {
    final /* synthetic */ long $labelSize;
    final /* synthetic */ PaddingValues $paddingValues;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OutlinedTextFieldKt$outlineCutout$1(long j10, PaddingValues paddingValues) {
        super(1);
        this.$labelSize = j10;
        this.$paddingValues = paddingValues;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ContentDrawScope) obj);
        return t0.f22605a;
    }

    public final void invoke(ContentDrawScope contentDrawScope) {
        float fM3305getWidthimpl = Size.m3305getWidthimpl(this.$labelSize);
        if (fM3305getWidthimpl <= 0.0f) {
            contentDrawScope.drawContent();
            return;
        }
        float fMo285toPx0680j_4 = contentDrawScope.mo285toPx0680j_4(OutlinedTextFieldKt.OutlinedTextFieldInnerPadding);
        float fMo285toPx0680j_42 = contentDrawScope.mo285toPx0680j_4(this.$paddingValues.mo480calculateLeftPaddingu2uoSUM(contentDrawScope.getLayoutDirection())) - fMo285toPx0680j_4;
        float f10 = 2;
        float fM3305getWidthimpl2 = (fMo285toPx0680j_4 * f10) + fM3305getWidthimpl + fMo285toPx0680j_42;
        LayoutDirection layoutDirection = contentDrawScope.getLayoutDirection();
        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
        float fM3305getWidthimpl3 = iArr[layoutDirection.ordinal()] == 1 ? Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()) - fM3305getWidthimpl2 : fMo285toPx0680j_42 < 0.0f ? 0.0f : fMo285toPx0680j_42;
        if (iArr[contentDrawScope.getLayoutDirection().ordinal()] == 1) {
            fM3305getWidthimpl2 = Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()) - (fMo285toPx0680j_42 >= 0.0f ? fMo285toPx0680j_42 : 0.0f);
        }
        float fM3302getHeightimpl = Size.m3302getHeightimpl(this.$labelSize);
        float f11 = (-fM3302getHeightimpl) / f10;
        float f12 = fM3302getHeightimpl / f10;
        int iM3460getDifferencertfAjoo = ClipOp.INSTANCE.m3460getDifferencertfAjoo();
        DrawContext drawContext = contentDrawScope.getDrawContext();
        long jMo3922getSizeNHjbRc = drawContext.mo3922getSizeNHjbRc();
        drawContext.getCanvas().save();
        drawContext.getTransform().mo3925clipRectN_I0leg(fM3305getWidthimpl3, f11, fM3305getWidthimpl2, f12, iM3460getDifferencertfAjoo);
        contentDrawScope.drawContent();
        drawContext.getCanvas().restore();
        drawContext.mo3923setSizeuvyYCjk(jMo3922getSizeNHjbRc);
    }
}
