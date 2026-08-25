package androidx.compose.ui.text;

import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/text/ParagraphInfo;", "paragraphInfo", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/text/ParagraphInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class MultiParagraph$fillBoundingBoxes$1 extends r implements l<ParagraphInfo, t0> {
    final /* synthetic */ float[] $array;
    final /* synthetic */ i0 $currentArrayStart;
    final /* synthetic */ h0 $currentHeight;
    final /* synthetic */ long $range;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultiParagraph$fillBoundingBoxes$1(long j10, float[] fArr, i0 i0Var, h0 h0Var) {
        super(1);
        this.$range = j10;
        this.$array = fArr;
        this.$currentArrayStart = i0Var;
        this.$currentHeight = h0Var;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ParagraphInfo) obj);
        return t0.f22605a;
    }

    public final void invoke(ParagraphInfo paragraphInfo) {
        long j10 = this.$range;
        float[] fArr = this.$array;
        i0 i0Var = this.$currentArrayStart;
        h0 h0Var = this.$currentHeight;
        long jTextRange = TextRangeKt.TextRange(paragraphInfo.toLocalIndex(paragraphInfo.getStartIndex() > TextRange.m5190getMinimpl(j10) ? paragraphInfo.getStartIndex() : TextRange.m5190getMinimpl(j10)), paragraphInfo.toLocalIndex(paragraphInfo.getEndIndex() < TextRange.m5189getMaximpl(j10) ? paragraphInfo.getEndIndex() : TextRange.m5189getMaximpl(j10)));
        paragraphInfo.getParagraph().mo5041fillBoundingBoxes8ffj60Q(jTextRange, fArr, i0Var.f19744i);
        int iM5188getLengthimpl = (TextRange.m5188getLengthimpl(jTextRange) * 4) + i0Var.f19744i;
        for (int i10 = i0Var.f19744i; i10 < iM5188getLengthimpl; i10 += 4) {
            int i11 = i10 + 1;
            float f10 = fArr[i11];
            float f11 = h0Var.f19742i;
            fArr[i11] = f10 + f11;
            int i12 = i10 + 3;
            fArr[i12] = fArr[i12] + f11;
        }
        i0Var.f19744i = iM5188getLengthimpl;
        h0Var.f19742i = paragraphInfo.getParagraph().getHeight() + h0Var.f19742i;
    }
}
