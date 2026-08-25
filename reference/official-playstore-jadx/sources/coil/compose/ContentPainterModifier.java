package coil.compose;

import a0.c;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.b;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactor;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import r7.l;
import t7.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0080\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B1\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0013J\u0010\u0010\u0019\u001a\u00020\u0004HÂ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0006HÂ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\bHÂ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\nHÂ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0012\u0010!\u001a\u0004\u0018\u00010\fHÂ\u0003¢\u0006\u0004\b!\u0010\"J)\u0010)\u001a\u00020&*\u00020#2\u0006\u0010%\u001a\u00020$2\u0006\u0010\u0016\u001a\u00020\u0015H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(J#\u0010.\u001a\u00020,*\u00020*2\u0006\u0010%\u001a\u00020+2\u0006\u0010-\u001a\u00020,H\u0016¢\u0006\u0004\b.\u0010/J#\u00100\u001a\u00020,*\u00020*2\u0006\u0010%\u001a\u00020+2\u0006\u0010-\u001a\u00020,H\u0016¢\u0006\u0004\b0\u0010/J#\u00102\u001a\u00020,*\u00020*2\u0006\u0010%\u001a\u00020+2\u0006\u00101\u001a\u00020,H\u0016¢\u0006\u0004\b2\u0010/J#\u00103\u001a\u00020,*\u00020*2\u0006\u0010%\u001a\u00020+2\u0006\u00101\u001a\u00020,H\u0016¢\u0006\u0004\b3\u0010/J\u0013\u00106\u001a\u000205*\u000204H\u0016¢\u0006\u0004\b6\u00107JD\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0004\b8\u00109J\u0010\u0010;\u001a\u00020:HÖ\u0001¢\u0006\u0004\b;\u0010<J\u0010\u0010=\u001a\u00020,HÖ\u0001¢\u0006\u0004\b=\u0010>J\u001a\u0010B\u001a\u00020A2\b\u0010@\u001a\u0004\u0018\u00010?HÖ\u0003¢\u0006\u0004\bB\u0010CR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010DR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010ER\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010FR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010GR\u0016\u0010\r\u001a\u0004\u0018\u00010\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010H\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006I"}, d2 = {"Lcoil/compose/ContentPainterModifier;", "Landroidx/compose/ui/layout/LayoutModifier;", "Landroidx/compose/ui/draw/DrawModifier;", "Landroidx/compose/ui/platform/InspectorValueInfo;", "Landroidx/compose/ui/graphics/painter/Painter;", "painter", "Landroidx/compose/ui/Alignment;", "alignment", "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "", "alpha", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "<init>", "(Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;)V", "Landroidx/compose/ui/geometry/Size;", "dstSize", "calculateScaledSize-E7KxVPU", "(J)J", "calculateScaledSize", "Landroidx/compose/ui/unit/Constraints;", "constraints", "modifyConstraints-ZezNO4M", "modifyConstraints", "component1", "()Landroidx/compose/ui/graphics/painter/Painter;", "component2", "()Landroidx/compose/ui/Alignment;", "component3", "()Landroidx/compose/ui/layout/ContentScale;", "component4", "()F", "component5", "()Landroidx/compose/ui/graphics/ColorFilter;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "", "height", "minIntrinsicWidth", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;Landroidx/compose/ui/layout/IntrinsicMeasurable;I)I", "maxIntrinsicWidth", "width", "minIntrinsicHeight", "maxIntrinsicHeight", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "draw", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "copy", "(Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;)Lcoil/compose/ContentPainterModifier;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/ui/graphics/painter/Painter;", "Landroidx/compose/ui/Alignment;", "Landroidx/compose/ui/layout/ContentScale;", "F", "Landroidx/compose/ui/graphics/ColorFilter;", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final /* data */ class ContentPainterModifier extends InspectorValueInfo implements LayoutModifier, DrawModifier {
    private final Alignment alignment;
    private final float alpha;
    private final ColorFilter colorFilter;
    private final ContentScale contentScale;
    private final Painter painter;

    public ContentPainterModifier(Painter painter, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter) {
        Painter painter2;
        Alignment alignment2;
        ContentScale contentScale2;
        float f11;
        ColorFilter colorFilter2;
        l noInspectorInfo;
        if (InspectableValueKt.isDebugInspectorInfoEnabled()) {
            painter2 = painter;
            alignment2 = alignment;
            contentScale2 = contentScale;
            f11 = f10;
            colorFilter2 = colorFilter;
            noInspectorInfo = new ContentPainterModifier$special$$inlined$debugInspectorInfo$1(painter2, alignment2, contentScale2, f11, colorFilter2);
        } else {
            painter2 = painter;
            alignment2 = alignment;
            contentScale2 = contentScale;
            f11 = f10;
            colorFilter2 = colorFilter;
            noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        }
        super(noInspectorInfo);
        this.painter = painter2;
        this.alignment = alignment2;
        this.contentScale = contentScale2;
        this.alpha = f11;
        this.colorFilter = colorFilter2;
    }

    /* JADX INFO: renamed from: calculateScaledSize-E7KxVPU, reason: not valid java name */
    private final long m6040calculateScaledSizeE7KxVPU(long dstSize) {
        if (Size.m3307isEmptyimpl(dstSize)) {
            return Size.INSTANCE.m3314getZeroNHjbRc();
        }
        long intrinsicSize = this.painter.getIntrinsicSize();
        if (intrinsicSize == Size.INSTANCE.m3313getUnspecifiedNHjbRc()) {
            return dstSize;
        }
        float fM3305getWidthimpl = Size.m3305getWidthimpl(intrinsicSize);
        if (Float.isInfinite(fM3305getWidthimpl) || Float.isNaN(fM3305getWidthimpl)) {
            fM3305getWidthimpl = Size.m3305getWidthimpl(dstSize);
        }
        float fM3302getHeightimpl = Size.m3302getHeightimpl(intrinsicSize);
        if (Float.isInfinite(fM3302getHeightimpl) || Float.isNaN(fM3302getHeightimpl)) {
            fM3302getHeightimpl = Size.m3302getHeightimpl(dstSize);
        }
        long jSize = SizeKt.Size(fM3305getWidthimpl, fM3302getHeightimpl);
        long jMo4622computeScaleFactorH7hwNQA = this.contentScale.mo4622computeScaleFactorH7hwNQA(jSize, dstSize);
        float fM4688getScaleXimpl = ScaleFactor.m4688getScaleXimpl(jMo4622computeScaleFactorH7hwNQA);
        if (Float.isInfinite(fM4688getScaleXimpl) || Float.isNaN(fM4688getScaleXimpl)) {
            return dstSize;
        }
        float fM4689getScaleYimpl = ScaleFactor.m4689getScaleYimpl(jMo4622computeScaleFactorH7hwNQA);
        return (Float.isInfinite(fM4689getScaleYimpl) || Float.isNaN(fM4689getScaleYimpl)) ? dstSize : ScaleFactorKt.m4704timesmw2e94(jMo4622computeScaleFactorH7hwNQA, jSize);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final Painter getPainter() {
        return this.painter;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final Alignment getAlignment() {
        return this.alignment;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    private final ContentScale getContentScale() {
        return this.contentScale;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    private final float getAlpha() {
        return this.alpha;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    private final ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public static /* synthetic */ ContentPainterModifier copy$default(ContentPainterModifier contentPainterModifier, Painter painter, Alignment alignment, ContentScale contentScale, float f10, ColorFilter colorFilter, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            painter = contentPainterModifier.painter;
        }
        if ((i10 & 2) != 0) {
            alignment = contentPainterModifier.alignment;
        }
        if ((i10 & 4) != 0) {
            contentScale = contentPainterModifier.contentScale;
        }
        if ((i10 & 8) != 0) {
            f10 = contentPainterModifier.alpha;
        }
        if ((i10 & 16) != 0) {
            colorFilter = contentPainterModifier.colorFilter;
        }
        ColorFilter colorFilter2 = colorFilter;
        ContentScale contentScale2 = contentScale;
        return contentPainterModifier.copy(painter, alignment, contentScale2, f10, colorFilter2);
    }

    /* JADX INFO: renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    private final long m6041modifyConstraintsZezNO4M(long constraints) {
        float fM5648getMinWidthimpl;
        int iM5647getMinHeightimpl;
        float fM6061constrainHeightK40F9xA;
        boolean zM5644getHasFixedWidthimpl = Constraints.m5644getHasFixedWidthimpl(constraints);
        boolean zM5643getHasFixedHeightimpl = Constraints.m5643getHasFixedHeightimpl(constraints);
        if (!zM5644getHasFixedWidthimpl || !zM5643getHasFixedHeightimpl) {
            boolean z = Constraints.m5642getHasBoundedWidthimpl(constraints) && Constraints.m5641getHasBoundedHeightimpl(constraints);
            long intrinsicSize = this.painter.getIntrinsicSize();
            if (intrinsicSize != Size.INSTANCE.m3313getUnspecifiedNHjbRc()) {
                if (z && (zM5644getHasFixedWidthimpl || zM5643getHasFixedHeightimpl)) {
                    fM5648getMinWidthimpl = Constraints.m5646getMaxWidthimpl(constraints);
                    iM5647getMinHeightimpl = Constraints.m5645getMaxHeightimpl(constraints);
                } else {
                    float fM3305getWidthimpl = Size.m3305getWidthimpl(intrinsicSize);
                    float fM3302getHeightimpl = Size.m3302getHeightimpl(intrinsicSize);
                    fM5648getMinWidthimpl = (Float.isInfinite(fM3305getWidthimpl) || Float.isNaN(fM3305getWidthimpl)) ? Constraints.m5648getMinWidthimpl(constraints) : UtilsKt.m6062constrainWidthK40F9xA(constraints, fM3305getWidthimpl);
                    if (!Float.isInfinite(fM3302getHeightimpl) && !Float.isNaN(fM3302getHeightimpl)) {
                        fM6061constrainHeightK40F9xA = UtilsKt.m6061constrainHeightK40F9xA(constraints, fM3302getHeightimpl);
                        long jM6040calculateScaledSizeE7KxVPU = m6040calculateScaledSizeE7KxVPU(SizeKt.Size(fM5648getMinWidthimpl, fM6061constrainHeightK40F9xA));
                        return Constraints.m5637copyZbe2FdA$default(constraints, ConstraintsKt.m5660constrainWidthK40F9xA(constraints, a.M(Size.m3305getWidthimpl(jM6040calculateScaledSizeE7KxVPU))), 0, ConstraintsKt.m5659constrainHeightK40F9xA(constraints, a.M(Size.m3302getHeightimpl(jM6040calculateScaledSizeE7KxVPU))), 0, 10, null);
                    }
                    iM5647getMinHeightimpl = Constraints.m5647getMinHeightimpl(constraints);
                }
                fM6061constrainHeightK40F9xA = iM5647getMinHeightimpl;
                long jM6040calculateScaledSizeE7KxVPU2 = m6040calculateScaledSizeE7KxVPU(SizeKt.Size(fM5648getMinWidthimpl, fM6061constrainHeightK40F9xA));
                return Constraints.m5637copyZbe2FdA$default(constraints, ConstraintsKt.m5660constrainWidthK40F9xA(constraints, a.M(Size.m3305getWidthimpl(jM6040calculateScaledSizeE7KxVPU2))), 0, ConstraintsKt.m5659constrainHeightK40F9xA(constraints, a.M(Size.m3302getHeightimpl(jM6040calculateScaledSizeE7KxVPU2))), 0, 10, null);
            }
            if (z) {
                return Constraints.m5637copyZbe2FdA$default(constraints, Constraints.m5646getMaxWidthimpl(constraints), 0, Constraints.m5645getMaxHeightimpl(constraints), 0, 10, null);
            }
        }
        return constraints;
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ boolean all(l lVar) {
        return b.a(this, lVar);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ boolean any(l lVar) {
        return b.b(this, lVar);
    }

    public final ContentPainterModifier copy(Painter painter, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter) {
        return new ContentPainterModifier(painter, alignment, contentScale, alpha, colorFilter);
    }

    @Override // androidx.compose.ui.draw.DrawModifier
    public void draw(ContentDrawScope contentDrawScope) {
        long jM6040calculateScaledSizeE7KxVPU = m6040calculateScaledSizeE7KxVPU(contentDrawScope.mo3916getSizeNHjbRc());
        long jMo3109alignKFBX0sM = this.alignment.mo3109alignKFBX0sM(UtilsKt.m6063toIntSizeuvyYCjk(jM6040calculateScaledSizeE7KxVPU), UtilsKt.m6063toIntSizeuvyYCjk(contentDrawScope.mo3916getSizeNHjbRc()), contentDrawScope.getLayoutDirection());
        float fM5794component1impl = IntOffset.m5794component1impl(jMo3109alignKFBX0sM);
        float fM5795component2impl = IntOffset.m5795component2impl(jMo3109alignKFBX0sM);
        contentDrawScope.getDrawContext().getTransform().translate(fM5794component1impl, fM5795component2impl);
        this.painter.m4016drawx_KDEd0(contentDrawScope, jM6040calculateScaledSizeE7KxVPU, this.alpha, this.colorFilter);
        contentDrawScope.getDrawContext().getTransform().translate(-fM5794component1impl, -fM5795component2impl);
        contentDrawScope.drawContent();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContentPainterModifier)) {
            return false;
        }
        ContentPainterModifier contentPainterModifier = (ContentPainterModifier) other;
        return p.a(this.painter, contentPainterModifier.painter) && p.a(this.alignment, contentPainterModifier.alignment) && p.a(this.contentScale, contentPainterModifier.contentScale) && Float.compare(this.alpha, contentPainterModifier.alpha) == 0 && p.a(this.colorFilter, contentPainterModifier.colorFilter);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ Object foldIn(Object obj, r7.p pVar) {
        return b.c(this, obj, pVar);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ Object foldOut(Object obj, r7.p pVar) {
        return b.d(this, obj, pVar);
    }

    public int hashCode() {
        int iB = c.b(this.alpha, (this.contentScale.hashCode() + ((this.alignment.hashCode() + (this.painter.hashCode() * 31)) * 31)) * 31, 31);
        ColorFilter colorFilter = this.colorFilter;
        return iB + (colorFilter == null ? 0 : colorFilter.hashCode());
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        if (this.painter.getIntrinsicSize() == Size.INSTANCE.m3313getUnspecifiedNHjbRc()) {
            return intrinsicMeasurable.maxIntrinsicHeight(i10);
        }
        int iMaxIntrinsicHeight = intrinsicMeasurable.maxIntrinsicHeight(Constraints.m5646getMaxWidthimpl(m6041modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i10, 0, 0, 13, null))));
        return Math.max(a.M(Size.m3302getHeightimpl(m6040calculateScaledSizeE7KxVPU(SizeKt.Size(i10, iMaxIntrinsicHeight)))), iMaxIntrinsicHeight);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        if (this.painter.getIntrinsicSize() == Size.INSTANCE.m3313getUnspecifiedNHjbRc()) {
            return intrinsicMeasurable.maxIntrinsicWidth(i10);
        }
        int iMaxIntrinsicWidth = intrinsicMeasurable.maxIntrinsicWidth(Constraints.m5645getMaxHeightimpl(m6041modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i10, 7, null))));
        return Math.max(a.M(Size.m3305getWidthimpl(m6040calculateScaledSizeE7KxVPU(SizeKt.Size(iMaxIntrinsicWidth, i10)))), iMaxIntrinsicWidth);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo29measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(m6041modifyConstraintsZezNO4M(j10));
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new ContentPainterModifier$measure$1(placeableMo4631measureBRTryo0), 4, null);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        if (this.painter.getIntrinsicSize() == Size.INSTANCE.m3313getUnspecifiedNHjbRc()) {
            return intrinsicMeasurable.minIntrinsicHeight(i10);
        }
        int iMinIntrinsicHeight = intrinsicMeasurable.minIntrinsicHeight(Constraints.m5646getMaxWidthimpl(m6041modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i10, 0, 0, 13, null))));
        return Math.max(a.M(Size.m3302getHeightimpl(m6040calculateScaledSizeE7KxVPU(SizeKt.Size(i10, iMinIntrinsicHeight)))), iMinIntrinsicHeight);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        if (this.painter.getIntrinsicSize() == Size.INSTANCE.m3313getUnspecifiedNHjbRc()) {
            return intrinsicMeasurable.minIntrinsicWidth(i10);
        }
        int iMinIntrinsicWidth = intrinsicMeasurable.minIntrinsicWidth(Constraints.m5645getMaxHeightimpl(m6041modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i10, 7, null))));
        return Math.max(a.M(Size.m3305getWidthimpl(m6040calculateScaledSizeE7KxVPU(SizeKt.Size(iMinIntrinsicWidth, i10)))), iMinIntrinsicWidth);
    }

    @Override // androidx.compose.ui.Modifier
    public final /* synthetic */ Modifier then(Modifier modifier) {
        return androidx.compose.ui.a.a(this, modifier);
    }

    public String toString() {
        return "ContentPainterModifier(painter=" + this.painter + ", alignment=" + this.alignment + ", contentScale=" + this.contentScale + ", alpha=" + this.alpha + ", colorFilter=" + this.colorFilter + ')';
    }
}
