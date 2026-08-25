package androidx.compose.foundation;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B7\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0003HÂ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u0012\u001a\u00020\u0005HÂ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0010J\u0010\u0010\u0013\u001a\u00020\u0003HÂ\u0003¢\u0006\u0004\b\u0013\u0010\u0010J\u0010\u0010\u0014\u001a\u00020\u0003HÂ\u0003¢\u0006\u0004\b\u0014\u0010\u0010J\u0010\u0010\u0015\u001a\u00020\tHÂ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0019\u001a\u00020\u000bHÂ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u001a\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001a\u0010\u001bJ\u0017\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0013\u0010!\u001a\u00020\u001d*\u00020 H\u0016¢\u0006\u0004\b!\u0010\"JO\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001ø\u0001\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010'\u001a\u00020&HÖ\u0001¢\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b)\u0010\u0010J\u001a\u0010-\u001a\u00020,2\b\u0010+\u001a\u0004\u0018\u00010*HÖ\u0003¢\u0006\u0004\b-\u0010.R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010/R\u001a\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\u0006\u0010/R\u0014\u0010\u0007\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010/R\u0014\u0010\b\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010/R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u00100R\u001a\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\n\u0004\b\f\u00101\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"Landroidx/compose/foundation/MarqueeModifierElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/MarqueeModifierNode;", "", "iterations", "Landroidx/compose/foundation/MarqueeAnimationMode;", "animationMode", "delayMillis", "initialDelayMillis", "Landroidx/compose/foundation/MarqueeSpacing;", "spacing", "Landroidx/compose/ui/unit/Dp;", "velocity", "<init>", "(IIIILandroidx/compose/foundation/MarqueeSpacing;FLkotlin/jvm/internal/h;)V", "component1", "()I", "component2-ZbEOnfQ", "component2", "component3", "component4", "component5", "()Landroidx/compose/foundation/MarqueeSpacing;", "component6-D9Ej5fM", "()F", "component6", "create", "()Landroidx/compose/foundation/MarqueeModifierNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/foundation/MarqueeModifierNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "copy-lWfNwf4", "(IIIILandroidx/compose/foundation/MarqueeSpacing;F)Landroidx/compose/foundation/MarqueeModifierElement;", "copy", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "Landroidx/compose/foundation/MarqueeSpacing;", "F", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class MarqueeModifierElement extends ModifierNodeElement<MarqueeModifierNode> {
    private final int animationMode;
    private final int delayMillis;
    private final int initialDelayMillis;
    private final int iterations;
    private final MarqueeSpacing spacing;
    private final float velocity;

    public /* synthetic */ MarqueeModifierElement(int i10, int i11, int i12, int i13, MarqueeSpacing marqueeSpacing, float f10, h hVar) {
        this(i10, i11, i12, i13, marqueeSpacing, f10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final int getIterations() {
        return this.iterations;
    }

    /* JADX INFO: renamed from: component2-ZbEOnfQ, reason: not valid java name and from getter */
    private final int getAnimationMode() {
        return this.animationMode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    private final int getDelayMillis() {
        return this.delayMillis;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    private final int getInitialDelayMillis() {
        return this.initialDelayMillis;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    private final MarqueeSpacing getSpacing() {
        return this.spacing;
    }

    /* JADX INFO: renamed from: component6-D9Ej5fM, reason: not valid java name and from getter */
    private final float getVelocity() {
        return this.velocity;
    }

    /* JADX INFO: renamed from: copy-lWfNwf4$default, reason: not valid java name */
    public static /* synthetic */ MarqueeModifierElement m267copylWfNwf4$default(MarqueeModifierElement marqueeModifierElement, int i10, int i11, int i12, int i13, MarqueeSpacing marqueeSpacing, float f10, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            i10 = marqueeModifierElement.iterations;
        }
        if ((i14 & 2) != 0) {
            i11 = marqueeModifierElement.animationMode;
        }
        if ((i14 & 4) != 0) {
            i12 = marqueeModifierElement.delayMillis;
        }
        if ((i14 & 8) != 0) {
            i13 = marqueeModifierElement.initialDelayMillis;
        }
        if ((i14 & 16) != 0) {
            marqueeSpacing = marqueeModifierElement.spacing;
        }
        if ((i14 & 32) != 0) {
            f10 = marqueeModifierElement.velocity;
        }
        MarqueeSpacing marqueeSpacing2 = marqueeSpacing;
        float f11 = f10;
        return marqueeModifierElement.m268copylWfNwf4(i10, i11, i12, i13, marqueeSpacing2, f11);
    }

    /* JADX INFO: renamed from: copy-lWfNwf4, reason: not valid java name */
    public final MarqueeModifierElement m268copylWfNwf4(int iterations, int animationMode, int delayMillis, int initialDelayMillis, MarqueeSpacing spacing, float velocity) {
        return new MarqueeModifierElement(iterations, animationMode, delayMillis, initialDelayMillis, spacing, velocity, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MarqueeModifierElement)) {
            return false;
        }
        MarqueeModifierElement marqueeModifierElement = (MarqueeModifierElement) other;
        return this.iterations == marqueeModifierElement.iterations && MarqueeAnimationMode.m257equalsimpl0(this.animationMode, marqueeModifierElement.animationMode) && this.delayMillis == marqueeModifierElement.delayMillis && this.initialDelayMillis == marqueeModifierElement.initialDelayMillis && p.a(this.spacing, marqueeModifierElement.spacing) && Dp.m5683equalsimpl0(this.velocity, marqueeModifierElement.velocity);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return Dp.m5684hashCodeimpl(this.velocity) + ((this.spacing.hashCode() + ((((((MarqueeAnimationMode.m258hashCodeimpl(this.animationMode) + (this.iterations * 31)) * 31) + this.delayMillis) * 31) + this.initialDelayMillis) * 31)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("basicMarquee");
        inspectorInfo.getProperties().set("iterations", Integer.valueOf(this.iterations));
        inspectorInfo.getProperties().set("animationMode", MarqueeAnimationMode.m254boximpl(this.animationMode));
        inspectorInfo.getProperties().set("delayMillis", Integer.valueOf(this.delayMillis));
        inspectorInfo.getProperties().set("initialDelayMillis", Integer.valueOf(this.initialDelayMillis));
        inspectorInfo.getProperties().set("spacing", this.spacing);
        inspectorInfo.getProperties().set("velocity", Dp.m5676boximpl(this.velocity));
    }

    public String toString() {
        return "MarqueeModifierElement(iterations=" + this.iterations + ", animationMode=" + ((Object) MarqueeAnimationMode.m259toStringimpl(this.animationMode)) + ", delayMillis=" + this.delayMillis + ", initialDelayMillis=" + this.initialDelayMillis + ", spacing=" + this.spacing + ", velocity=" + ((Object) Dp.m5689toStringimpl(this.velocity)) + ')';
    }

    private MarqueeModifierElement(int i10, int i11, int i12, int i13, MarqueeSpacing marqueeSpacing, float f10) {
        this.iterations = i10;
        this.animationMode = i11;
        this.delayMillis = i12;
        this.initialDelayMillis = i13;
        this.spacing = marqueeSpacing;
        this.velocity = f10;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public MarqueeModifierNode create() {
        return new MarqueeModifierNode(this.iterations, this.animationMode, this.delayMillis, this.initialDelayMillis, this.spacing, this.velocity, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(MarqueeModifierNode node) {
        node.m271updatelWfNwf4(this.iterations, this.animationMode, this.delayMillis, this.initialDelayMillis, this.spacing, this.velocity);
    }
}
