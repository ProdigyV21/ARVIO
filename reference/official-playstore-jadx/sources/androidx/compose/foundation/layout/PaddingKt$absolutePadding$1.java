package androidx.compose.foundation.layout;

import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class PaddingKt$absolutePadding$1 extends r implements l<InspectorInfo, t0> {
    final /* synthetic */ float $bottom;
    final /* synthetic */ float $left;
    final /* synthetic */ float $right;
    final /* synthetic */ float $top;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PaddingKt$absolutePadding$1(float f10, float f11, float f12, float f13) {
        super(1);
        this.$left = f10;
        this.$top = f11;
        this.$right = f12;
        this.$bottom = f13;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((InspectorInfo) obj);
        return t0.f22605a;
    }

    public final void invoke(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("absolutePadding");
        a0.c.e(this.$right, a0.c.e(this.$top, a0.c.e(this.$left, inspectorInfo.getProperties(), TtmlNode.LEFT, inspectorInfo), "top", inspectorInfo), TtmlNode.RIGHT, inspectorInfo).set("bottom", Dp.m5676boximpl(this.$bottom));
    }
}
