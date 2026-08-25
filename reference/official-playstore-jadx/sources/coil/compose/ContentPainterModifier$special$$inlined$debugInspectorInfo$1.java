package coil.compose;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "androidx/compose/ui/platform/InspectableValueKt$debugInspectorInfo$1", "<anonymous>"}, k = 3, mv = {1, 9, 0})
public final class ContentPainterModifier$special$$inlined$debugInspectorInfo$1 extends r implements l<InspectorInfo, t0> {
    final /* synthetic */ Alignment $alignment$inlined;
    final /* synthetic */ float $alpha$inlined;
    final /* synthetic */ ColorFilter $colorFilter$inlined;
    final /* synthetic */ ContentScale $contentScale$inlined;
    final /* synthetic */ Painter $painter$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentPainterModifier$special$$inlined$debugInspectorInfo$1(Painter painter, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter) {
        super(1);
        this.$painter$inlined = painter;
        this.$alignment$inlined = alignment;
        this.$contentScale$inlined = contentScale;
        this.$alpha$inlined = f10;
        this.$colorFilter$inlined = colorFilter;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((InspectorInfo) obj);
        return t0.f22605a;
    }

    public final void invoke(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("content");
        inspectorInfo.getProperties().set("painter", this.$painter$inlined);
        inspectorInfo.getProperties().set("alignment", this.$alignment$inlined);
        inspectorInfo.getProperties().set("contentScale", this.$contentScale$inlined);
        inspectorInfo.getProperties().set("alpha", Float.valueOf(this.$alpha$inlined));
        inspectorInfo.getProperties().set("colorFilter", this.$colorFilter$inlined);
    }
}
