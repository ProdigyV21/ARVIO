package androidx.compose.material3;

import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "androidx/compose/ui/platform/InspectableValueKt$debugInspectorInfo$1", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SwipeToDismissAnchorsElement$inspectableProperties$$inlined$debugInspectorInfo$1 extends r implements l<InspectorInfo, t0> {
    final /* synthetic */ SwipeToDismissAnchorsElement this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwipeToDismissAnchorsElement$inspectableProperties$$inlined$debugInspectorInfo$1(SwipeToDismissAnchorsElement swipeToDismissAnchorsElement) {
        super(1);
        this.this$0 = swipeToDismissAnchorsElement;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((InspectorInfo) obj);
        return t0.f22605a;
    }

    public final void invoke(InspectorInfo inspectorInfo) {
        inspectorInfo.getProperties().set("state", this.this$0.state);
        a0.c.f(this.this$0.enableDismissFromStartToEnd, inspectorInfo.getProperties(), "enableDismissFromStartToEnd", inspectorInfo).set("enableDismissFromEndToStart", Boolean.valueOf(this.this$0.enableDismissFromEndToStart));
    }
}
