package androidx.compose.foundation;

import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.DpSize;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: renamed from: androidx.compose.foundation.Magnifier_androidKt$magnifier-jPUL71Q$$inlined$debugInspectorInfo$1, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "androidx/compose/ui/platform/InspectableValueKt$debugInspectorInfo$1", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Magnifier_androidKt$magnifierjPUL71Q$$inlined$debugInspectorInfo$1 extends r implements l<InspectorInfo, t0> {
    final /* synthetic */ boolean $clippingEnabled$inlined;
    final /* synthetic */ float $cornerRadius$inlined;
    final /* synthetic */ float $elevation$inlined;
    final /* synthetic */ l $magnifierCenter$inlined;
    final /* synthetic */ long $size$inlined;
    final /* synthetic */ l $sourceCenter$inlined;
    final /* synthetic */ float $zoom$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Magnifier_androidKt$magnifierjPUL71Q$$inlined$debugInspectorInfo$1(l lVar, l lVar2, float f10, long j10, float f11, float f12, boolean z) {
        super(1);
        this.$sourceCenter$inlined = lVar;
        this.$magnifierCenter$inlined = lVar2;
        this.$zoom$inlined = f10;
        this.$size$inlined = j10;
        this.$cornerRadius$inlined = f11;
        this.$elevation$inlined = f12;
        this.$clippingEnabled$inlined = z;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((InspectorInfo) obj);
        return t0.f22605a;
    }

    public final void invoke(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("magnifier (not supported)");
        inspectorInfo.getProperties().set("sourceCenter", this.$sourceCenter$inlined);
        inspectorInfo.getProperties().set("magnifierCenter", this.$magnifierCenter$inlined);
        inspectorInfo.getProperties().set("zoom", Float.valueOf(this.$zoom$inlined));
        inspectorInfo.getProperties().set(ContentDisposition.Parameters.Size, DpSize.m5764boximpl(this.$size$inlined));
        a0.c.e(this.$elevation$inlined, a0.c.e(this.$cornerRadius$inlined, inspectorInfo.getProperties(), "cornerRadius", inspectorInfo), "elevation", inspectorInfo).set("clippingEnabled", Boolean.valueOf(this.$clippingEnabled$inlined));
    }
}
