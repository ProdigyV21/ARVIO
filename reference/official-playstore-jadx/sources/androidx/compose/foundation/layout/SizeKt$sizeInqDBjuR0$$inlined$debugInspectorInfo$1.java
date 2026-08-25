package androidx.compose.foundation.layout;

import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: renamed from: androidx.compose.foundation.layout.SizeKt$sizeIn-qDBjuR0$$inlined$debugInspectorInfo$1, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "androidx/compose/ui/platform/InspectableValueKt$debugInspectorInfo$1", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SizeKt$sizeInqDBjuR0$$inlined$debugInspectorInfo$1 extends r implements l<InspectorInfo, t0> {
    final /* synthetic */ float $maxHeight$inlined;
    final /* synthetic */ float $maxWidth$inlined;
    final /* synthetic */ float $minHeight$inlined;
    final /* synthetic */ float $minWidth$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SizeKt$sizeInqDBjuR0$$inlined$debugInspectorInfo$1(float f10, float f11, float f12, float f13) {
        super(1);
        this.$minWidth$inlined = f10;
        this.$minHeight$inlined = f11;
        this.$maxWidth$inlined = f12;
        this.$maxHeight$inlined = f13;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((InspectorInfo) obj);
        return t0.f22605a;
    }

    public final void invoke(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("sizeIn");
        a0.c.e(this.$maxWidth$inlined, a0.c.e(this.$minHeight$inlined, a0.c.e(this.$minWidth$inlined, inspectorInfo.getProperties(), "minWidth", inspectorInfo), "minHeight", inspectorInfo), "maxWidth", inspectorInfo).set("maxHeight", Dp.m5676boximpl(this.$maxHeight$inlined));
    }
}
