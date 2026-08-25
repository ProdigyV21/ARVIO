package androidx.compose.ui.draw;

import a0.c;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: renamed from: androidx.compose.ui.draw.ShadowKt$shadow-s4CzXII$$inlined$debugInspectorInfo$1, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "androidx/compose/ui/platform/InspectableValueKt$debugInspectorInfo$1", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ShadowKt$shadows4CzXII$$inlined$debugInspectorInfo$1 extends r implements l<InspectorInfo, t0> {
    final /* synthetic */ long $ambientColor$inlined;
    final /* synthetic */ boolean $clip$inlined;
    final /* synthetic */ float $elevation$inlined;
    final /* synthetic */ Shape $shape$inlined;
    final /* synthetic */ long $spotColor$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShadowKt$shadows4CzXII$$inlined$debugInspectorInfo$1(float f10, Shape shape, boolean z, long j10, long j11) {
        super(1);
        this.$elevation$inlined = f10;
        this.$shape$inlined = shape;
        this.$clip$inlined = z;
        this.$ambientColor$inlined = j10;
        this.$spotColor$inlined = j11;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((InspectorInfo) obj);
        return t0.f22605a;
    }

    public final void invoke(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("shadow");
        c.e(this.$elevation$inlined, inspectorInfo.getProperties(), "elevation", inspectorInfo).set("shape", this.$shape$inlined);
        c.f(this.$clip$inlined, inspectorInfo.getProperties(), "clip", inspectorInfo).set("ambientColor", Color.m3462boximpl(this.$ambientColor$inlined));
        inspectorInfo.getProperties().set("spotColor", Color.m3462boximpl(this.$spotColor$inlined));
    }
}
