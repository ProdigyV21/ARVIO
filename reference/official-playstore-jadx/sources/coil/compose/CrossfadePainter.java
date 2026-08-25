package coil.compose;

import android.os.SystemClock;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import qb.d;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\t\n\u0002\b\u0013\b\u0001\u0018\u00002\u00020\u0001B;\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u0010\u001a\u00020\rH\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ%\u0010\u0016\u001a\u00020\u0015*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J%\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\u0002ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u0013\u0010\u001d\u001a\u00020\u0015*\u00020\u0011H\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0013H\u0014¢\u0006\u0004\b\u001f\u0010 J\u0019\u0010#\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0014¢\u0006\u0004\b#\u0010$R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0002\u0010%R\u0016\u0010\u0003\u001a\u0004\u0018\u00010\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010%R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010&R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010'R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010(R\u0014\u0010\n\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010(R+\u00100\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0016\u00102\u001a\u0002018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u0010(R+\u0010;\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R/\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010)\u001a\u0004\u0018\u00010!8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001d\u0010C\u001a\u00020\r8VX\u0096\u0004ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\u001a\u0004\bB\u0010\u000f\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006D"}, d2 = {"Lcoil/compose/CrossfadePainter;", "Landroidx/compose/ui/graphics/painter/Painter;", TtmlNode.START, TtmlNode.END, "Landroidx/compose/ui/layout/ContentScale;", "contentScale", "", "durationMillis", "", "fadeStart", "preferExactIntrinsicSize", "<init>", "(Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/layout/ContentScale;IZZ)V", "Landroidx/compose/ui/geometry/Size;", "computeIntrinsicSize-NH-jbRc", "()J", "computeIntrinsicSize", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "painter", "", "alpha", "Lx6/t0;", "drawPainter", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/painter/Painter;F)V", "srcSize", "dstSize", "computeDrawSize-x8L_9b0", "(JJ)J", "computeDrawSize", "onDraw", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "applyAlpha", "(F)Z", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "applyColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)Z", "Landroidx/compose/ui/graphics/painter/Painter;", "Landroidx/compose/ui/layout/ContentScale;", "I", "Z", "<set-?>", "invalidateTick$delegate", "Landroidx/compose/runtime/MutableIntState;", "getInvalidateTick", "()I", "setInvalidateTick", "(I)V", "invalidateTick", "", "startTimeMillis", "J", "isDone", "maxAlpha$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getMaxAlpha", "()F", "setMaxAlpha", "(F)V", "maxAlpha", "colorFilter$delegate", "Landroidx/compose/runtime/MutableState;", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "getIntrinsicSize-NH-jbRc", "intrinsicSize", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class CrossfadePainter extends Painter {
    private final ContentScale contentScale;
    private final int durationMillis;
    private final Painter end;
    private final boolean fadeStart;
    private boolean isDone;
    private final boolean preferExactIntrinsicSize;
    private Painter start;

    /* JADX INFO: renamed from: invalidateTick$delegate, reason: from kotlin metadata */
    private final MutableIntState invalidateTick = SnapshotIntStateKt.mutableIntStateOf(0);
    private long startTimeMillis = -1;

    /* JADX INFO: renamed from: maxAlpha$delegate, reason: from kotlin metadata */
    private final MutableFloatState maxAlpha = PrimitiveSnapshotStateKt.mutableFloatStateOf(1.0f);

    /* JADX INFO: renamed from: colorFilter$delegate, reason: from kotlin metadata */
    private final MutableState colorFilter = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    public CrossfadePainter(Painter painter, Painter painter2, ContentScale contentScale, int i10, boolean z, boolean z5) {
        this.start = painter;
        this.end = painter2;
        this.contentScale = contentScale;
        this.durationMillis = i10;
        this.fadeStart = z;
        this.preferExactIntrinsicSize = z5;
    }

    /* JADX INFO: renamed from: computeDrawSize-x8L_9b0, reason: not valid java name */
    private final long m6042computeDrawSizex8L_9b0(long srcSize, long dstSize) {
        Size.Companion companion = Size.INSTANCE;
        return (srcSize == companion.m3313getUnspecifiedNHjbRc() || Size.m3307isEmptyimpl(srcSize) || dstSize == companion.m3313getUnspecifiedNHjbRc() || Size.m3307isEmptyimpl(dstSize)) ? dstSize : ScaleFactorKt.m4703timesUQTWf7w(srcSize, this.contentScale.mo4622computeScaleFactorH7hwNQA(srcSize, dstSize));
    }

    /* JADX INFO: renamed from: computeIntrinsicSize-NH-jbRc, reason: not valid java name */
    private final long m6043computeIntrinsicSizeNHjbRc() {
        Painter painter = this.start;
        long jMo4010getIntrinsicSizeNHjbRc = painter != null ? painter.getIntrinsicSize() : Size.INSTANCE.m3314getZeroNHjbRc();
        Painter painter2 = this.end;
        long jMo4010getIntrinsicSizeNHjbRc2 = painter2 != null ? painter2.getIntrinsicSize() : Size.INSTANCE.m3314getZeroNHjbRc();
        Size.Companion companion = Size.INSTANCE;
        boolean z = jMo4010getIntrinsicSizeNHjbRc != companion.m3313getUnspecifiedNHjbRc();
        boolean z5 = jMo4010getIntrinsicSizeNHjbRc2 != companion.m3313getUnspecifiedNHjbRc();
        if (z && z5) {
            return SizeKt.Size(Math.max(Size.m3305getWidthimpl(jMo4010getIntrinsicSizeNHjbRc), Size.m3305getWidthimpl(jMo4010getIntrinsicSizeNHjbRc2)), Math.max(Size.m3302getHeightimpl(jMo4010getIntrinsicSizeNHjbRc), Size.m3302getHeightimpl(jMo4010getIntrinsicSizeNHjbRc2)));
        }
        if (this.preferExactIntrinsicSize) {
            if (z) {
                return jMo4010getIntrinsicSizeNHjbRc;
            }
            if (z5) {
                return jMo4010getIntrinsicSizeNHjbRc2;
            }
        }
        return companion.m3313getUnspecifiedNHjbRc();
    }

    private final void drawPainter(DrawScope drawScope, Painter painter, float f10) {
        if (painter == null || f10 <= 0.0f) {
            return;
        }
        long jMo3916getSizeNHjbRc = drawScope.mo3916getSizeNHjbRc();
        long jM6042computeDrawSizex8L_9b0 = m6042computeDrawSizex8L_9b0(painter.getIntrinsicSize(), jMo3916getSizeNHjbRc);
        if (jMo3916getSizeNHjbRc == Size.INSTANCE.m3313getUnspecifiedNHjbRc() || Size.m3307isEmptyimpl(jMo3916getSizeNHjbRc)) {
            painter.m4016drawx_KDEd0(drawScope, jM6042computeDrawSizex8L_9b0, f10, getColorFilter());
            return;
        }
        float f11 = 2;
        float fM3305getWidthimpl = (Size.m3305getWidthimpl(jMo3916getSizeNHjbRc) - Size.m3305getWidthimpl(jM6042computeDrawSizex8L_9b0)) / f11;
        float fM3302getHeightimpl = (Size.m3302getHeightimpl(jMo3916getSizeNHjbRc) - Size.m3302getHeightimpl(jM6042computeDrawSizex8L_9b0)) / f11;
        drawScope.getDrawContext().getTransform().inset(fM3305getWidthimpl, fM3302getHeightimpl, fM3305getWidthimpl, fM3302getHeightimpl);
        painter.m4016drawx_KDEd0(drawScope, jM6042computeDrawSizex8L_9b0, f10, getColorFilter());
        DrawTransform transform = drawScope.getDrawContext().getTransform();
        float f12 = -fM3305getWidthimpl;
        float f13 = -fM3302getHeightimpl;
        transform.inset(f12, f13, f12, f13);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ColorFilter getColorFilter() {
        return (ColorFilter) this.colorFilter.getValue();
    }

    private final int getInvalidateTick() {
        return this.invalidateTick.getIntValue();
    }

    private final float getMaxAlpha() {
        return this.maxAlpha.getFloatValue();
    }

    private final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter.setValue(colorFilter);
    }

    private final void setInvalidateTick(int i10) {
        this.invalidateTick.setIntValue(i10);
    }

    private final void setMaxAlpha(float f10) {
        this.maxAlpha.setFloatValue(f10);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public boolean applyAlpha(float alpha) {
        setMaxAlpha(alpha);
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public boolean applyColorFilter(ColorFilter colorFilter) {
        setColorFilter(colorFilter);
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public long getIntrinsicSize() {
        return m6043computeIntrinsicSizeNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public void onDraw(DrawScope drawScope) {
        if (this.isDone) {
            drawPainter(drawScope, this.end, getMaxAlpha());
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.startTimeMillis == -1) {
            this.startTimeMillis = jUptimeMillis;
        }
        float f10 = (jUptimeMillis - this.startTimeMillis) / this.durationMillis;
        float fM = d.m(f10, 0.0f, 1.0f) * getMaxAlpha();
        float maxAlpha = this.fadeStart ? getMaxAlpha() - fM : getMaxAlpha();
        this.isDone = f10 >= 1.0f;
        drawPainter(drawScope, this.start, maxAlpha);
        drawPainter(drawScope, this.end, fM);
        if (this.isDone) {
            this.start = null;
        } else {
            setInvalidateTick(getInvalidateTick() + 1);
        }
    }
}
