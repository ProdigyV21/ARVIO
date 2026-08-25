package androidx.compose.foundation.layout;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0019\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BP\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u001a\u0010\u001b\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0096\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\f*\u00020\u000bH\u0016¢\u0006\u0004\b\u001d\u0010\u001eR(\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0004\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R(\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R(\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R(\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R(\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010/\u001a\u0004\b0\u00101\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00062"}, d2 = {"Landroidx/compose/foundation/layout/PaddingElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/layout/PaddingNode;", "Landroidx/compose/ui/unit/Dp;", TtmlNode.START, "top", TtmlNode.END, "bottom", "", "rtlAware", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "Lx6/n;", "inspectorInfo", "<init>", "(FFFFZLr7/l;Lkotlin/jvm/internal/h;)V", "create", "()Landroidx/compose/foundation/layout/PaddingNode;", "node", "update", "(Landroidx/compose/foundation/layout/PaddingNode;)V", "", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "F", "getStart-D9Ej5fM", "()F", "setStart-0680j_4", "(F)V", "getTop-D9Ej5fM", "setTop-0680j_4", "getEnd-D9Ej5fM", "setEnd-0680j_4", "getBottom-D9Ej5fM", "setBottom-0680j_4", "Z", "getRtlAware", "()Z", "setRtlAware", "(Z)V", "Lr7/l;", "getInspectorInfo", "()Lr7/l;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PaddingElement extends ModifierNodeElement<PaddingNode> {
    private float bottom;
    private float end;
    private final l<InspectorInfo, t0> inspectorInfo;
    private boolean rtlAware;
    private float start;
    private float top;

    public /* synthetic */ PaddingElement(float f10, float f11, float f12, float f13, boolean z, l lVar, h hVar) {
        this(f10, f11, f12, f13, z, lVar);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        PaddingElement paddingElement = other instanceof PaddingElement ? (PaddingElement) other : null;
        return paddingElement != null && Dp.m5683equalsimpl0(this.start, paddingElement.start) && Dp.m5683equalsimpl0(this.top, paddingElement.top) && Dp.m5683equalsimpl0(this.end, paddingElement.end) && Dp.m5683equalsimpl0(this.bottom, paddingElement.bottom) && this.rtlAware == paddingElement.rtlAware;
    }

    /* JADX INFO: renamed from: getBottom-D9Ej5fM, reason: not valid java name and from getter */
    public final float getBottom() {
        return this.bottom;
    }

    /* JADX INFO: renamed from: getEnd-D9Ej5fM, reason: not valid java name and from getter */
    public final float getEnd() {
        return this.end;
    }

    public final l<InspectorInfo, t0> getInspectorInfo() {
        return this.inspectorInfo;
    }

    public final boolean getRtlAware() {
        return this.rtlAware;
    }

    /* JADX INFO: renamed from: getStart-D9Ej5fM, reason: not valid java name and from getter */
    public final float getStart() {
        return this.start;
    }

    /* JADX INFO: renamed from: getTop-D9Ej5fM, reason: not valid java name and from getter */
    public final float getTop() {
        return this.top;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return a0.c.C(this.bottom, a0.c.C(this.end, a0.c.C(this.top, Dp.m5684hashCodeimpl(this.start) * 31, 31), 31), 31) + (this.rtlAware ? 1231 : 1237);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        this.inspectorInfo.invoke(inspectorInfo);
    }

    /* JADX INFO: renamed from: setBottom-0680j_4, reason: not valid java name */
    public final void m518setBottom0680j_4(float f10) {
        this.bottom = f10;
    }

    /* JADX INFO: renamed from: setEnd-0680j_4, reason: not valid java name */
    public final void m519setEnd0680j_4(float f10) {
        this.end = f10;
    }

    public final void setRtlAware(boolean z) {
        this.rtlAware = z;
    }

    /* JADX INFO: renamed from: setStart-0680j_4, reason: not valid java name */
    public final void m520setStart0680j_4(float f10) {
        this.start = f10;
    }

    /* JADX INFO: renamed from: setTop-0680j_4, reason: not valid java name */
    public final void m521setTop0680j_4(float f10) {
        this.top = f10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private PaddingElement(float f10, float f11, float f12, float f13, boolean z, l<? super InspectorInfo, t0> lVar) {
        this.start = f10;
        this.top = f11;
        this.end = f12;
        this.bottom = f13;
        this.rtlAware = z;
        this.inspectorInfo = lVar;
        if (f10 >= 0.0f || Dp.m5683equalsimpl0(f10, Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM())) {
            float f14 = this.top;
            if (f14 >= 0.0f || Dp.m5683equalsimpl0(f14, Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM())) {
                float f15 = this.end;
                if (f15 >= 0.0f || Dp.m5683equalsimpl0(f15, Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM())) {
                    float f16 = this.bottom;
                    if (f16 >= 0.0f || Dp.m5683equalsimpl0(f16, Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM())) {
                        return;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Padding must be non-negative");
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public PaddingNode create() {
        return new PaddingNode(this.start, this.top, this.end, this.bottom, this.rtlAware, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(PaddingNode node) {
        node.m540setStart0680j_4(this.start);
        node.m541setTop0680j_4(this.top);
        node.m539setEnd0680j_4(this.end);
        node.m538setBottom0680j_4(this.bottom);
        node.setRtlAware(this.rtlAware);
    }

    public /* synthetic */ PaddingElement(float f10, float f11, float f12, float f13, boolean z, l lVar, int i10, h hVar) {
        this((i10 & 1) != 0 ? Dp.m5678constructorimpl(0) : f10, (i10 & 2) != 0 ? Dp.m5678constructorimpl(0) : f11, (i10 & 4) != 0 ? Dp.m5678constructorimpl(0) : f12, (i10 & 8) != 0 ? Dp.m5678constructorimpl(0) : f13, z, lVar, null);
    }
}
