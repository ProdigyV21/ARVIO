package androidx.compose.foundation;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0012\u001a\u00020\u000e*\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0016\u0010\u0016\u001a\u00020\u0003HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ1\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001f\u001a\u00020\u001eHÖ\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010\"\u001a\u00020!HÖ\u0001¢\u0006\u0004\b\"\u0010#J\u001a\u0010'\u001a\u00020&2\b\u0010%\u001a\u0004\u0018\u00010$HÖ\u0003¢\u0006\u0004\b'\u0010(R\u001d\u0010\u0004\u001a\u00020\u00038\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0004\u0010)\u001a\u0004\b*\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010+\u001a\u0004\b,\u0010\u0018R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010-\u001a\u0004\b.\u0010\u001a\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006/"}, d2 = {"Landroidx/compose/foundation/BorderModifierNodeElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/BorderModifierNode;", "Landroidx/compose/ui/unit/Dp;", "width", "Landroidx/compose/ui/graphics/Brush;", "brush", "Landroidx/compose/ui/graphics/Shape;", "shape", "<init>", "(FLandroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Shape;Lkotlin/jvm/internal/h;)V", "create", "()Landroidx/compose/foundation/BorderModifierNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/foundation/BorderModifierNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "component1-D9Ej5fM", "()F", "component1", "component2", "()Landroidx/compose/ui/graphics/Brush;", "component3", "()Landroidx/compose/ui/graphics/Shape;", "copy-8Feqmps", "(FLandroidx/compose/ui/graphics/Brush;Landroidx/compose/ui/graphics/Shape;)Landroidx/compose/foundation/BorderModifierNodeElement;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "F", "getWidth-D9Ej5fM", "Landroidx/compose/ui/graphics/Brush;", "getBrush", "Landroidx/compose/ui/graphics/Shape;", "getShape", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class BorderModifierNodeElement extends ModifierNodeElement<BorderModifierNode> {
    public static final int $stable = 0;
    private final Brush brush;
    private final Shape shape;
    private final float width;

    public /* synthetic */ BorderModifierNodeElement(float f10, Brush brush, Shape shape, h hVar) {
        this(f10, brush, shape);
    }

    /* JADX INFO: renamed from: copy-8Feqmps$default, reason: not valid java name */
    public static /* synthetic */ BorderModifierNodeElement m200copy8Feqmps$default(BorderModifierNodeElement borderModifierNodeElement, float f10, Brush brush, Shape shape, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = borderModifierNodeElement.width;
        }
        if ((i10 & 2) != 0) {
            brush = borderModifierNodeElement.brush;
        }
        if ((i10 & 4) != 0) {
            shape = borderModifierNodeElement.shape;
        }
        return borderModifierNodeElement.m202copy8Feqmps(f10, brush, shape);
    }

    /* JADX INFO: renamed from: component1-D9Ej5fM, reason: not valid java name and from getter */
    public final float getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Brush getBrush() {
        return this.brush;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Shape getShape() {
        return this.shape;
    }

    /* JADX INFO: renamed from: copy-8Feqmps, reason: not valid java name */
    public final BorderModifierNodeElement m202copy8Feqmps(float width, Brush brush, Shape shape) {
        return new BorderModifierNodeElement(width, brush, shape, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BorderModifierNodeElement)) {
            return false;
        }
        BorderModifierNodeElement borderModifierNodeElement = (BorderModifierNodeElement) other;
        return Dp.m5683equalsimpl0(this.width, borderModifierNodeElement.width) && p.a(this.brush, borderModifierNodeElement.brush) && p.a(this.shape, borderModifierNodeElement.shape);
    }

    public final Brush getBrush() {
        return this.brush;
    }

    public final Shape getShape() {
        return this.shape;
    }

    /* JADX INFO: renamed from: getWidth-D9Ej5fM, reason: not valid java name */
    public final float m203getWidthD9Ej5fM() {
        return this.width;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return this.shape.hashCode() + ((this.brush.hashCode() + (Dp.m5684hashCodeimpl(this.width) * 31)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("border");
        inspectorInfo.getProperties().set("width", Dp.m5676boximpl(this.width));
        if (this.brush instanceof SolidColor) {
            inspectorInfo.getProperties().set(TtmlNode.ATTR_TTS_COLOR, Color.m3462boximpl(((SolidColor) this.brush).getValue()));
            inspectorInfo.setValue(Color.m3462boximpl(((SolidColor) this.brush).getValue()));
        } else {
            inspectorInfo.getProperties().set("brush", this.brush);
        }
        inspectorInfo.getProperties().set("shape", this.shape);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("BorderModifierNodeElement(width=");
        a0.c.v(this.width, sb2, ", brush=");
        sb2.append(this.brush);
        sb2.append(", shape=");
        sb2.append(this.shape);
        sb2.append(')');
        return sb2.toString();
    }

    private BorderModifierNodeElement(float f10, Brush brush, Shape shape) {
        this.width = f10;
        this.brush = brush;
        this.shape = shape;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public BorderModifierNode create() {
        return new BorderModifierNode(this.width, this.brush, this.shape, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(BorderModifierNode node) {
        node.m199setWidth0680j_4(this.width);
        node.setBrush(this.brush);
        node.setShape(this.shape);
    }
}
