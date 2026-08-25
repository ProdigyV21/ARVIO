package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\r\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH&ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fR \u0010\u000e\u001a\u00020\u00048\u0016X\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\u0082\u0001\u0002\u0013\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/graphics/Brush;", "", "<init>", "()V", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/graphics/Paint;", TtmlNode.TAG_P, "", "alpha", "Lx6/t0;", "applyTo-Pq9zytI", "(JLandroidx/compose/ui/graphics/Paint;F)V", "applyTo", "intrinsicSize", "J", "getIntrinsicSize-NH-jbRc", "()J", "Companion", "Landroidx/compose/ui/graphics/ShaderBrush;", "Landroidx/compose/ui/graphics/SolidColor;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class Brush {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long intrinsicSize;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0017\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\\\u0010\u0011\u001a\u00020\u000e2*\u0010\b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J>\u0010\u0011\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0014J>\u0010\u0019\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\\\u0010\u0019\u001a\u00020\u000e2*\u0010\b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u001aJ>\u0010\u001e\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u0018J\\\u0010\u001e\u001a\u00020\u000e2*\u0010\b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00062\b\b\u0002\u0010\u001c\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001aJ\\\u0010#\u001a\u00020\u000e2*\u0010\b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"J>\u0010#\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\b\b\u0002\u0010\u001f\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010$JH\u0010'\u001a\u00020\u000e2*\u0010\b\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004\"\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\b\b\u0002\u0010\u001f\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&J*\u0010'\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u00122\b\b\u0002\u0010\u001f\u001a\u00020\tH\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010(\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006)"}, d2 = {"Landroidx/compose/ui/graphics/Brush$Companion;", "", "<init>", "()V", "", "Lx6/x;", "", "Landroidx/compose/ui/graphics/Color;", "colorStops", "Landroidx/compose/ui/geometry/Offset;", TtmlNode.START, TtmlNode.END, "Landroidx/compose/ui/graphics/TileMode;", "tileMode", "Landroidx/compose/ui/graphics/Brush;", "linearGradient-mHitzGk", "([Lx6/x;JJI)Landroidx/compose/ui/graphics/Brush;", "linearGradient", "", "colors", "(Ljava/util/List;JJI)Landroidx/compose/ui/graphics/Brush;", "startX", "endX", "horizontalGradient-8A-3gB4", "(Ljava/util/List;FFI)Landroidx/compose/ui/graphics/Brush;", "horizontalGradient", "([Lx6/x;FFI)Landroidx/compose/ui/graphics/Brush;", "startY", "endY", "verticalGradient-8A-3gB4", "verticalGradient", TtmlNode.CENTER, "radius", "radialGradient-P_Vx-Ks", "([Lx6/x;JFI)Landroidx/compose/ui/graphics/Brush;", "radialGradient", "(Ljava/util/List;JFI)Landroidx/compose/ui/graphics/Brush;", "sweepGradient-Uv8p0NA", "([Lx6/x;J)Landroidx/compose/ui/graphics/Brush;", "sweepGradient", "(Ljava/util/List;J)Landroidx/compose/ui/graphics/Brush;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        /* JADX INFO: renamed from: horizontalGradient-8A-3gB4$default, reason: not valid java name */
        public static /* synthetic */ Brush m3427horizontalGradient8A3gB4$default(Companion companion, List list, float f10, float f11, int i10, int i11, Object obj) {
            if ((i11 & 2) != 0) {
                f10 = 0.0f;
            }
            if ((i11 & 4) != 0) {
                f11 = Float.POSITIVE_INFINITY;
            }
            if ((i11 & 8) != 0) {
                i10 = TileMode.INSTANCE.m3823getClamp3opZhB0();
            }
            return companion.m3437horizontalGradient8A3gB4((List<Color>) list, f10, f11, i10);
        }

        /* JADX INFO: renamed from: linearGradient-mHitzGk$default, reason: not valid java name */
        public static /* synthetic */ Brush m3430linearGradientmHitzGk$default(Companion companion, x[] xVarArr, long j10, long j11, int i10, int i11, Object obj) {
            if ((i11 & 2) != 0) {
                j10 = Offset.INSTANCE.m3252getZeroF1C5BW0();
            }
            long j12 = j10;
            if ((i11 & 4) != 0) {
                j11 = Offset.INSTANCE.m3250getInfiniteF1C5BW0();
            }
            long j13 = j11;
            if ((i11 & 8) != 0) {
                i10 = TileMode.INSTANCE.m3823getClamp3opZhB0();
            }
            return companion.m3440linearGradientmHitzGk(xVarArr, j12, j13, i10);
        }

        /* JADX INFO: renamed from: radialGradient-P_Vx-Ks$default, reason: not valid java name */
        public static /* synthetic */ Brush m3432radialGradientP_VxKs$default(Companion companion, x[] xVarArr, long j10, float f10, int i10, int i11, Object obj) {
            if ((i11 & 2) != 0) {
                j10 = Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
            }
            long j11 = j10;
            if ((i11 & 4) != 0) {
                f10 = Float.POSITIVE_INFINITY;
            }
            float f11 = f10;
            if ((i11 & 8) != 0) {
                i10 = TileMode.INSTANCE.m3823getClamp3opZhB0();
            }
            return companion.m3442radialGradientP_VxKs(xVarArr, j11, f11, i10);
        }

        /* JADX INFO: renamed from: sweepGradient-Uv8p0NA$default, reason: not valid java name */
        public static /* synthetic */ Brush m3434sweepGradientUv8p0NA$default(Companion companion, x[] xVarArr, long j10, int i10, Object obj) {
            if ((i10 & 2) != 0) {
                j10 = Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
            }
            return companion.m3444sweepGradientUv8p0NA(xVarArr, j10);
        }

        /* JADX INFO: renamed from: verticalGradient-8A-3gB4$default, reason: not valid java name */
        public static /* synthetic */ Brush m3435verticalGradient8A3gB4$default(Companion companion, List list, float f10, float f11, int i10, int i11, Object obj) {
            if ((i11 & 2) != 0) {
                f10 = 0.0f;
            }
            if ((i11 & 4) != 0) {
                f11 = Float.POSITIVE_INFINITY;
            }
            if ((i11 & 8) != 0) {
                i10 = TileMode.INSTANCE.m3823getClamp3opZhB0();
            }
            return companion.m3445verticalGradient8A3gB4((List<Color>) list, f10, f11, i10);
        }

        /* JADX INFO: renamed from: horizontalGradient-8A-3gB4, reason: not valid java name */
        public final Brush m3437horizontalGradient8A3gB4(List<Color> colors, float startX, float endX, int tileMode) {
            return m3439linearGradientmHitzGk(colors, OffsetKt.Offset(startX, 0.0f), OffsetKt.Offset(endX, 0.0f), tileMode);
        }

        /* JADX INFO: renamed from: linearGradient-mHitzGk, reason: not valid java name */
        public final Brush m3440linearGradientmHitzGk(x[] colorStops, long start, long end, int tileMode) {
            ArrayList arrayList = new ArrayList(colorStops.length);
            for (x xVar : colorStops) {
                arrayList.add(Color.m3462boximpl(((Color) xVar.f22609l).m3482unboximpl()));
            }
            ArrayList arrayList2 = new ArrayList(colorStops.length);
            for (x xVar2 : colorStops) {
                arrayList2.add(Float.valueOf(((Number) xVar2.f22608i).floatValue()));
            }
            return new LinearGradient(arrayList, arrayList2, start, end, tileMode, null);
        }

        /* JADX INFO: renamed from: radialGradient-P_Vx-Ks, reason: not valid java name */
        public final Brush m3442radialGradientP_VxKs(x[] colorStops, long center, float radius, int tileMode) {
            ArrayList arrayList = new ArrayList(colorStops.length);
            for (x xVar : colorStops) {
                arrayList.add(Color.m3462boximpl(((Color) xVar.f22609l).m3482unboximpl()));
            }
            ArrayList arrayList2 = new ArrayList(colorStops.length);
            for (x xVar2 : colorStops) {
                arrayList2.add(Float.valueOf(((Number) xVar2.f22608i).floatValue()));
            }
            return new RadialGradient(arrayList, arrayList2, center, radius, tileMode, null);
        }

        /* JADX INFO: renamed from: sweepGradient-Uv8p0NA, reason: not valid java name */
        public final Brush m3444sweepGradientUv8p0NA(x[] colorStops, long center) {
            ArrayList arrayList = new ArrayList(colorStops.length);
            for (x xVar : colorStops) {
                arrayList.add(Color.m3462boximpl(((Color) xVar.f22609l).m3482unboximpl()));
            }
            ArrayList arrayList2 = new ArrayList(colorStops.length);
            for (x xVar2 : colorStops) {
                arrayList2.add(Float.valueOf(((Number) xVar2.f22608i).floatValue()));
            }
            return new SweepGradient(center, arrayList, arrayList2, null);
        }

        /* JADX INFO: renamed from: verticalGradient-8A-3gB4, reason: not valid java name */
        public final Brush m3445verticalGradient8A3gB4(List<Color> colors, float startY, float endY, int tileMode) {
            return m3439linearGradientmHitzGk(colors, OffsetKt.Offset(0.0f, startY), OffsetKt.Offset(0.0f, endY), tileMode);
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: horizontalGradient-8A-3gB4, reason: not valid java name */
        public final Brush m3438horizontalGradient8A3gB4(x[] colorStops, float startX, float endX, int tileMode) {
            return m3440linearGradientmHitzGk((x[]) Arrays.copyOf(colorStops, colorStops.length), OffsetKt.Offset(startX, 0.0f), OffsetKt.Offset(endX, 0.0f), tileMode);
        }

        /* JADX INFO: renamed from: verticalGradient-8A-3gB4, reason: not valid java name */
        public final Brush m3446verticalGradient8A3gB4(x[] colorStops, float startY, float endY, int tileMode) {
            return m3440linearGradientmHitzGk((x[]) Arrays.copyOf(colorStops, colorStops.length), OffsetKt.Offset(0.0f, startY), OffsetKt.Offset(0.0f, endY), tileMode);
        }

        /* JADX INFO: renamed from: horizontalGradient-8A-3gB4$default, reason: not valid java name */
        public static /* synthetic */ Brush m3428horizontalGradient8A3gB4$default(Companion companion, x[] xVarArr, float f10, float f11, int i10, int i11, Object obj) {
            if ((i11 & 2) != 0) {
                f10 = 0.0f;
            }
            if ((i11 & 4) != 0) {
                f11 = Float.POSITIVE_INFINITY;
            }
            if ((i11 & 8) != 0) {
                i10 = TileMode.INSTANCE.m3823getClamp3opZhB0();
            }
            return companion.m3438horizontalGradient8A3gB4(xVarArr, f10, f11, i10);
        }

        /* JADX INFO: renamed from: sweepGradient-Uv8p0NA$default, reason: not valid java name */
        public static /* synthetic */ Brush m3433sweepGradientUv8p0NA$default(Companion companion, List list, long j10, int i10, Object obj) {
            if ((i10 & 2) != 0) {
                j10 = Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
            }
            return companion.m3443sweepGradientUv8p0NA((List<Color>) list, j10);
        }

        /* JADX INFO: renamed from: verticalGradient-8A-3gB4$default, reason: not valid java name */
        public static /* synthetic */ Brush m3436verticalGradient8A3gB4$default(Companion companion, x[] xVarArr, float f10, float f11, int i10, int i11, Object obj) {
            if ((i11 & 2) != 0) {
                f10 = 0.0f;
            }
            if ((i11 & 4) != 0) {
                f11 = Float.POSITIVE_INFINITY;
            }
            if ((i11 & 8) != 0) {
                i10 = TileMode.INSTANCE.m3823getClamp3opZhB0();
            }
            return companion.m3446verticalGradient8A3gB4(xVarArr, f10, f11, i10);
        }

        /* JADX INFO: renamed from: radialGradient-P_Vx-Ks$default, reason: not valid java name */
        public static /* synthetic */ Brush m3431radialGradientP_VxKs$default(Companion companion, List list, long j10, float f10, int i10, int i11, Object obj) {
            if ((i11 & 2) != 0) {
                j10 = Offset.INSTANCE.m3251getUnspecifiedF1C5BW0();
            }
            long j11 = j10;
            if ((i11 & 4) != 0) {
                f10 = Float.POSITIVE_INFINITY;
            }
            float f11 = f10;
            if ((i11 & 8) != 0) {
                i10 = TileMode.INSTANCE.m3823getClamp3opZhB0();
            }
            return companion.m3441radialGradientP_VxKs((List<Color>) list, j11, f11, i10);
        }

        /* JADX INFO: renamed from: linearGradient-mHitzGk$default, reason: not valid java name */
        public static /* synthetic */ Brush m3429linearGradientmHitzGk$default(Companion companion, List list, long j10, long j11, int i10, int i11, Object obj) {
            if ((i11 & 2) != 0) {
                j10 = Offset.INSTANCE.m3252getZeroF1C5BW0();
            }
            long j12 = j10;
            if ((i11 & 4) != 0) {
                j11 = Offset.INSTANCE.m3250getInfiniteF1C5BW0();
            }
            long j13 = j11;
            if ((i11 & 8) != 0) {
                i10 = TileMode.INSTANCE.m3823getClamp3opZhB0();
            }
            return companion.m3439linearGradientmHitzGk((List<Color>) list, j12, j13, i10);
        }

        /* JADX INFO: renamed from: linearGradient-mHitzGk, reason: not valid java name */
        public final Brush m3439linearGradientmHitzGk(List<Color> colors, long start, long end, int tileMode) {
            return new LinearGradient(colors, null, start, end, tileMode, null);
        }

        /* JADX INFO: renamed from: radialGradient-P_Vx-Ks, reason: not valid java name */
        public final Brush m3441radialGradientP_VxKs(List<Color> colors, long center, float radius, int tileMode) {
            return new RadialGradient(colors, null, center, radius, tileMode, null);
        }

        /* JADX INFO: renamed from: sweepGradient-Uv8p0NA, reason: not valid java name */
        public final Brush m3443sweepGradientUv8p0NA(List<Color> colors, long center) {
            return new SweepGradient(center, colors, null, null);
        }
    }

    public /* synthetic */ Brush(kotlin.jvm.internal.h hVar) {
        this();
    }

    /* JADX INFO: renamed from: applyTo-Pq9zytI, reason: not valid java name */
    public abstract void mo3425applyToPq9zytI(long size, Paint p10, float alpha);

    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc, reason: not valid java name and from getter */
    public long getIntrinsicSize() {
        return this.intrinsicSize;
    }

    private Brush() {
        this.intrinsicSize = Size.INSTANCE.m3313getUnspecifiedNHjbRc();
    }
}
