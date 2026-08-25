package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: renamed from: androidx.compose.material3.TextFieldDefaults$indicatorLine-gv0btCI$$inlined$debugInspectorInfo$1, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "androidx/compose/ui/platform/InspectableValueKt$debugInspectorInfo$1", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TextFieldDefaults$indicatorLinegv0btCI$$inlined$debugInspectorInfo$1 extends r implements l<InspectorInfo, t0> {
    final /* synthetic */ TextFieldColors $colors$inlined;
    final /* synthetic */ boolean $enabled$inlined;
    final /* synthetic */ float $focusedIndicatorLineThickness$inlined;
    final /* synthetic */ InteractionSource $interactionSource$inlined;
    final /* synthetic */ boolean $isError$inlined;
    final /* synthetic */ float $unfocusedIndicatorLineThickness$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextFieldDefaults$indicatorLinegv0btCI$$inlined$debugInspectorInfo$1(boolean z, boolean z5, InteractionSource interactionSource, TextFieldColors textFieldColors, float f10, float f11) {
        super(1);
        this.$enabled$inlined = z;
        this.$isError$inlined = z5;
        this.$interactionSource$inlined = interactionSource;
        this.$colors$inlined = textFieldColors;
        this.$focusedIndicatorLineThickness$inlined = f10;
        this.$unfocusedIndicatorLineThickness$inlined = f11;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((InspectorInfo) obj);
        return t0.f22605a;
    }

    public final void invoke(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("indicatorLine");
        a0.c.f(this.$isError$inlined, a0.c.f(this.$enabled$inlined, inspectorInfo.getProperties(), "enabled", inspectorInfo), "isError", inspectorInfo).set("interactionSource", this.$interactionSource$inlined);
        inspectorInfo.getProperties().set("colors", this.$colors$inlined);
        a0.c.e(this.$focusedIndicatorLineThickness$inlined, inspectorInfo.getProperties(), "focusedIndicatorLineThickness", inspectorInfo).set("unfocusedIndicatorLineThickness", Dp.m5676boximpl(this.$unfocusedIndicatorLineThickness$inlined));
    }
}
