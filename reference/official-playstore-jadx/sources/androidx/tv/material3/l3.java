package androidx.tv.material3;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableState;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.platform.InspectorInfo;

/* JADX INFO: loaded from: classes3.dex */
public final class l3 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6196i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f6197l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6198m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6199n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f6200o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f6201p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l3(Orientation orientation, ScrollableState scrollableState, boolean z, boolean z5, h2.b bVar) {
        super(1);
        this.f6199n = orientation;
        this.f6200o = scrollableState;
        this.f6197l = z;
        this.f6198m = z5;
        this.f6201p = bVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f6196i) {
            case 0:
                InspectorInfo inspectorInfo = (InspectorInfo) obj;
                inspectorInfo.setName("handleDPadEnter");
                a0.c.f(this.f6197l, inspectorInfo.getProperties(), "enabled", inspectorInfo).set("interactionSource", (MutableInteractionSource) this.f6199n);
                inspectorInfo.getProperties().set("onClick", (r7.a) this.f6200o);
                inspectorInfo.getProperties().set("onLongClick", (r7.a) this.f6201p);
                inspectorInfo.getProperties().set("selected", Boolean.valueOf(this.f6198m));
                break;
            default:
                InspectorInfo inspectorInfo2 = (InspectorInfo) obj;
                inspectorInfo2.setName("scrollableWithPivot");
                inspectorInfo2.getProperties().set("orientation", (Orientation) this.f6199n);
                inspectorInfo2.getProperties().set("state", (ScrollableState) this.f6200o);
                a0.c.f(this.f6198m, a0.c.f(this.f6197l, inspectorInfo2.getProperties(), "enabled", inspectorInfo2), "reverseDirection", inspectorInfo2).set("pivotOffsets", (h2.b) this.f6201p);
                break;
        }
        return x6.t0.f22605a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l3(boolean z, MutableInteractionSource mutableInteractionSource, r7.a aVar, r7.a aVar2, boolean z5) {
        super(1);
        this.f6197l = z;
        this.f6199n = mutableInteractionSource;
        this.f6200o = aVar;
        this.f6201p = aVar2;
        this.f6198m = z5;
    }
}
