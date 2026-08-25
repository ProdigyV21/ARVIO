package androidx.compose.ui.draw;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b%\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003BA\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0015J\u0016\u0010\u001d\u001a\u00020\u0006*\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u0016\u0010\u001f\u001a\u00020\u0006*\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001cJ&\u0010&\u001a\u00020#*\u00020 2\u0006\u0010\"\u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\u0017H\u0016ø\u0001\u0000¢\u0006\u0004\b$\u0010%J#\u0010+\u001a\u00020)*\u00020'2\u0006\u0010\"\u001a\u00020(2\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b+\u0010,J#\u0010-\u001a\u00020)*\u00020'2\u0006\u0010\"\u001a\u00020(2\u0006\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b-\u0010,J#\u0010/\u001a\u00020)*\u00020'2\u0006\u0010\"\u001a\u00020(2\u0006\u0010.\u001a\u00020)H\u0016¢\u0006\u0004\b/\u0010,J#\u00100\u001a\u00020)*\u00020'2\u0006\u0010\"\u001a\u00020(2\u0006\u0010.\u001a\u00020)H\u0016¢\u0006\u0004\b0\u0010,J\u0013\u00103\u001a\u000202*\u000201H\u0016¢\u0006\u0004\b3\u00104J\u000f\u00106\u001a\u000205H\u0016¢\u0006\u0004\b6\u00107R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\"\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010B\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR$\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010Q\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR\u0014\u0010W\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bV\u0010?R\u0014\u0010Y\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bX\u0010?\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006Z"}, d2 = {"Landroidx/compose/ui/draw/PainterNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/graphics/painter/Painter;", "painter", "", "sizeToIntrinsics", "Landroidx/compose/ui/Alignment;", "alignment", "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "", "alpha", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "<init>", "(Landroidx/compose/ui/graphics/painter/Painter;ZLandroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;)V", "Landroidx/compose/ui/geometry/Size;", "dstSize", "calculateScaledSize-E7KxVPU", "(J)J", "calculateScaledSize", "Landroidx/compose/ui/unit/Constraints;", "constraints", "modifyConstraints-ZezNO4M", "modifyConstraints", "hasSpecifiedAndFiniteWidth-uvyYCjk", "(J)Z", "hasSpecifiedAndFiniteWidth", "hasSpecifiedAndFiniteHeight-uvyYCjk", "hasSpecifiedAndFiniteHeight", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "", "height", "minIntrinsicWidth", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Landroidx/compose/ui/layout/IntrinsicMeasurable;I)I", "maxIntrinsicWidth", "width", "minIntrinsicHeight", "maxIntrinsicHeight", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "draw", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "", "toString", "()Ljava/lang/String;", "Landroidx/compose/ui/graphics/painter/Painter;", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "setPainter", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "Z", "getSizeToIntrinsics", "()Z", "setSizeToIntrinsics", "(Z)V", "Landroidx/compose/ui/Alignment;", "getAlignment", "()Landroidx/compose/ui/Alignment;", "setAlignment", "(Landroidx/compose/ui/Alignment;)V", "Landroidx/compose/ui/layout/ContentScale;", "getContentScale", "()Landroidx/compose/ui/layout/ContentScale;", "setContentScale", "(Landroidx/compose/ui/layout/ContentScale;)V", "F", "getAlpha", "()F", "setAlpha", "(F)V", "Landroidx/compose/ui/graphics/ColorFilter;", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "getUseIntrinsicSize", "useIntrinsicSize", "getShouldAutoInvalidate", "shouldAutoInvalidate", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PainterNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode {
    private Alignment alignment;
    private float alpha;
    private ColorFilter colorFilter;
    private ContentScale contentScale;
    private Painter painter;
    private boolean sizeToIntrinsics;

    public /* synthetic */ PainterNode(Painter painter, boolean z, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, h hVar) {
        this(painter, z, (i10 & 4) != 0 ? Alignment.INSTANCE.getCenter() : alignment, (i10 & 8) != 0 ? ContentScale.INSTANCE.getInside() : contentScale, (i10 & 16) != 0 ? 1.0f : f10, (i10 & 32) != 0 ? null : colorFilter);
    }

    /* JADX INFO: renamed from: calculateScaledSize-E7KxVPU, reason: not valid java name */
    private final long m3141calculateScaledSizeE7KxVPU(long dstSize) {
        if (!getUseIntrinsicSize()) {
            return dstSize;
        }
        long jSize = SizeKt.Size(!m3143hasSpecifiedAndFiniteWidthuvyYCjk(this.painter.getIntrinsicSize()) ? Size.m3305getWidthimpl(dstSize) : Size.m3305getWidthimpl(this.painter.getIntrinsicSize()), !m3142hasSpecifiedAndFiniteHeightuvyYCjk(this.painter.getIntrinsicSize()) ? Size.m3302getHeightimpl(dstSize) : Size.m3302getHeightimpl(this.painter.getIntrinsicSize()));
        return (Size.m3305getWidthimpl(dstSize) == 0.0f || Size.m3302getHeightimpl(dstSize) == 0.0f) ? Size.INSTANCE.m3314getZeroNHjbRc() : ScaleFactorKt.m4703timesUQTWf7w(jSize, this.contentScale.mo4622computeScaleFactorH7hwNQA(jSize, dstSize));
    }

    private final boolean getUseIntrinsicSize() {
        return this.sizeToIntrinsics && this.painter.getIntrinsicSize() != Size.INSTANCE.m3313getUnspecifiedNHjbRc();
    }

    /* JADX INFO: renamed from: hasSpecifiedAndFiniteHeight-uvyYCjk, reason: not valid java name */
    private final boolean m3142hasSpecifiedAndFiniteHeightuvyYCjk(long j10) {
        if (Size.m3301equalsimpl0(j10, Size.INSTANCE.m3313getUnspecifiedNHjbRc())) {
            return false;
        }
        float fM3302getHeightimpl = Size.m3302getHeightimpl(j10);
        return (Float.isInfinite(fM3302getHeightimpl) || Float.isNaN(fM3302getHeightimpl)) ? false : true;
    }

    /* JADX INFO: renamed from: hasSpecifiedAndFiniteWidth-uvyYCjk, reason: not valid java name */
    private final boolean m3143hasSpecifiedAndFiniteWidthuvyYCjk(long j10) {
        if (Size.m3301equalsimpl0(j10, Size.INSTANCE.m3313getUnspecifiedNHjbRc())) {
            return false;
        }
        float fM3305getWidthimpl = Size.m3305getWidthimpl(j10);
        return (Float.isInfinite(fM3305getWidthimpl) || Float.isNaN(fM3305getWidthimpl)) ? false : true;
    }

    /* JADX INFO: renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    private final long m3144modifyConstraintsZezNO4M(long constraints) {
        boolean z = false;
        boolean z5 = Constraints.m5642getHasBoundedWidthimpl(constraints) && Constraints.m5641getHasBoundedHeightimpl(constraints);
        if (Constraints.m5644getHasFixedWidthimpl(constraints) && Constraints.m5643getHasFixedHeightimpl(constraints)) {
            z = true;
        }
        if ((!getUseIntrinsicSize() && z5) || z) {
            return Constraints.m5637copyZbe2FdA$default(constraints, Constraints.m5646getMaxWidthimpl(constraints), 0, Constraints.m5645getMaxHeightimpl(constraints), 0, 10, null);
        }
        long jMo4010getIntrinsicSizeNHjbRc = this.painter.getIntrinsicSize();
        long jM3141calculateScaledSizeE7KxVPU = m3141calculateScaledSizeE7KxVPU(SizeKt.Size(ConstraintsKt.m5660constrainWidthK40F9xA(constraints, m3143hasSpecifiedAndFiniteWidthuvyYCjk(jMo4010getIntrinsicSizeNHjbRc) ? t7.a.M(Size.m3305getWidthimpl(jMo4010getIntrinsicSizeNHjbRc)) : Constraints.m5648getMinWidthimpl(constraints)), ConstraintsKt.m5659constrainHeightK40F9xA(constraints, m3142hasSpecifiedAndFiniteHeightuvyYCjk(jMo4010getIntrinsicSizeNHjbRc) ? t7.a.M(Size.m3302getHeightimpl(jMo4010getIntrinsicSizeNHjbRc)) : Constraints.m5647getMinHeightimpl(constraints))));
        return Constraints.m5637copyZbe2FdA$default(constraints, ConstraintsKt.m5660constrainWidthK40F9xA(constraints, t7.a.M(Size.m3305getWidthimpl(jM3141calculateScaledSizeE7KxVPU))), 0, ConstraintsKt.m5659constrainHeightK40F9xA(constraints, t7.a.M(Size.m3302getHeightimpl(jM3141calculateScaledSizeE7KxVPU))), 0, 10, null);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        long jMo4010getIntrinsicSizeNHjbRc = this.painter.getIntrinsicSize();
        long jSize = SizeKt.Size(m3143hasSpecifiedAndFiniteWidthuvyYCjk(jMo4010getIntrinsicSizeNHjbRc) ? Size.m3305getWidthimpl(jMo4010getIntrinsicSizeNHjbRc) : Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()), m3142hasSpecifiedAndFiniteHeightuvyYCjk(jMo4010getIntrinsicSizeNHjbRc) ? Size.m3302getHeightimpl(jMo4010getIntrinsicSizeNHjbRc) : Size.m3302getHeightimpl(contentDrawScope.mo3916getSizeNHjbRc()));
        long jM3314getZeroNHjbRc = (Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()) == 0.0f || Size.m3302getHeightimpl(contentDrawScope.mo3916getSizeNHjbRc()) == 0.0f) ? Size.INSTANCE.m3314getZeroNHjbRc() : ScaleFactorKt.m4703timesUQTWf7w(jSize, this.contentScale.mo4622computeScaleFactorH7hwNQA(jSize, contentDrawScope.mo3916getSizeNHjbRc()));
        long jMo3109alignKFBX0sM = this.alignment.mo3109alignKFBX0sM(IntSizeKt.IntSize(t7.a.M(Size.m3305getWidthimpl(jM3314getZeroNHjbRc)), t7.a.M(Size.m3302getHeightimpl(jM3314getZeroNHjbRc))), IntSizeKt.IntSize(t7.a.M(Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc())), t7.a.M(Size.m3302getHeightimpl(contentDrawScope.mo3916getSizeNHjbRc()))), contentDrawScope.getLayoutDirection());
        float fM5802getXimpl = IntOffset.m5802getXimpl(jMo3109alignKFBX0sM);
        float fM5803getYimpl = IntOffset.m5803getYimpl(jMo3109alignKFBX0sM);
        contentDrawScope.getDrawContext().getTransform().translate(fM5802getXimpl, fM5803getYimpl);
        this.painter.m4016drawx_KDEd0(contentDrawScope, jM3314getZeroNHjbRc, this.alpha, this.colorFilter);
        contentDrawScope.getDrawContext().getTransform().translate(-fM5802getXimpl, -fM5803getYimpl);
        contentDrawScope.drawContent();
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public final ContentScale getContentScale() {
        return this.contentScale;
    }

    public final Painter getPainter() {
        return this.painter;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    public final boolean getSizeToIntrinsics() {
        return this.sizeToIntrinsics;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        if (!getUseIntrinsicSize()) {
            return intrinsicMeasurable.maxIntrinsicHeight(i10);
        }
        long jM3144modifyConstraintsZezNO4M = m3144modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i10, 0, 0, 13, null));
        return Math.max(Constraints.m5647getMinHeightimpl(jM3144modifyConstraintsZezNO4M), intrinsicMeasurable.maxIntrinsicHeight(i10));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        if (!getUseIntrinsicSize()) {
            return intrinsicMeasurable.maxIntrinsicWidth(i10);
        }
        long jM3144modifyConstraintsZezNO4M = m3144modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i10, 7, null));
        return Math.max(Constraints.m5648getMinWidthimpl(jM3144modifyConstraintsZezNO4M), intrinsicMeasurable.maxIntrinsicWidth(i10));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(m3144modifyConstraintsZezNO4M(j10));
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new PainterNode$measure$1(placeableMo4631measureBRTryo0), 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        if (!getUseIntrinsicSize()) {
            return intrinsicMeasurable.minIntrinsicHeight(i10);
        }
        long jM3144modifyConstraintsZezNO4M = m3144modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i10, 0, 0, 13, null));
        return Math.max(Constraints.m5647getMinHeightimpl(jM3144modifyConstraintsZezNO4M), intrinsicMeasurable.minIntrinsicHeight(i10));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        if (!getUseIntrinsicSize()) {
            return intrinsicMeasurable.minIntrinsicWidth(i10);
        }
        long jM3144modifyConstraintsZezNO4M = m3144modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i10, 7, null));
        return Math.max(Constraints.m5648getMinWidthimpl(jM3144modifyConstraintsZezNO4M), intrinsicMeasurable.minIntrinsicWidth(i10));
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final /* synthetic */ void onMeasureResultChanged() {
        androidx.compose.ui.node.b.a(this);
    }

    public final void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public final void setAlpha(float f10) {
        this.alpha = f10;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
    }

    public final void setContentScale(ContentScale contentScale) {
        this.contentScale = contentScale;
    }

    public final void setPainter(Painter painter) {
        this.painter = painter;
    }

    public final void setSizeToIntrinsics(boolean z) {
        this.sizeToIntrinsics = z;
    }

    public String toString() {
        return "PainterModifier(painter=" + this.painter + ", sizeToIntrinsics=" + this.sizeToIntrinsics + ", alignment=" + this.alignment + ", alpha=" + this.alpha + ", colorFilter=" + this.colorFilter + ')';
    }

    public PainterNode(Painter painter, boolean z, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter) {
        this.painter = painter;
        this.sizeToIntrinsics = z;
        this.alignment = alignment;
        this.contentScale = contentScale;
        this.alpha = f10;
        this.colorFilter = colorFilter;
    }
}
