package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.ColorKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b!\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b#\u0018\u0000 m2\u00020\u0001:\u0001mB]\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014BE\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0004\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0004\b\u0013\u0010\u0018B]\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u0013\u0010\u0019B%\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0004\u0012\u0006\u0010\u001a\u001a\u00020\u000f¢\u0006\u0004\b\u0013\u0010\u001bB-\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\u001a\u001a\u00020\u000f¢\u0006\u0004\b\u0013\u0010\u001cB1\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\u001a\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u001dB%\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0015\u001a\u00020\u0004\u0012\u0006\u0010\u001e\u001a\u00020\u0017¢\u0006\u0004\b\u0013\u0010\u001fB-\b\u0016\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\u001e\u001a\u00020\u0017¢\u0006\u0004\b\u0013\u0010 BA\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\u001e\u001a\u00020\u0017\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010!B!\b\u0010\u0012\u0006\u0010\"\u001a\u00020\u0000\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0013\u0010#J\u000f\u0010$\u001a\u00020\u0004H\u0007¢\u0006\u0004\b$\u0010%J\u000f\u0010&\u001a\u00020\u0004H\u0007¢\u0006\u0004\b&\u0010%J\u000f\u0010'\u001a\u00020\u0004H\u0007¢\u0006\u0004\b'\u0010%J\u0019\u0010$\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b$\u0010(J\u0019\u0010&\u001a\u00020\u00042\b\b\u0001\u0010\b\u001a\u00020\u0004H\u0007¢\u0006\u0004\b&\u0010(J\u0019\u0010'\u001a\u00020\u00042\b\b\u0001\u0010)\u001a\u00020\u0004H\u0007¢\u0006\u0004\b'\u0010(J\u0017\u0010+\u001a\u00020\f2\u0006\u0010*\u001a\u00020\u0011H\u0016¢\u0006\u0004\b+\u0010,J\u0017\u0010-\u001a\u00020\f2\u0006\u0010*\u001a\u00020\u0011H\u0016¢\u0006\u0004\b-\u0010,J'\u00101\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\fH\u0007¢\u0006\u0004\b1\u00102J\u0019\u00101\u001a\u00020\u00042\b\b\u0001\u00103\u001a\u00020\u0004H\u0007¢\u0006\u0004\b1\u0010(J'\u00104\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\fH\u0007¢\u0006\u0004\b4\u00102J\u0019\u00104\u001a\u00020\u00042\b\b\u0001\u00103\u001a\u00020\u0004H\u0007¢\u0006\u0004\b4\u0010(J\u0017\u00105\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0004H\u0016¢\u0006\u0004\b5\u0010(J'\u0010<\u001a\u0002092\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\fH\u0010¢\u0006\u0004\b:\u0010;J'\u0010?\u001a\u00020\f2\u0006\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\fH\u0010¢\u0006\u0004\b=\u0010>J=\u0010G\u001a\u00020D2\u0006\u0010@\u001a\u00020\f2\u0006\u0010A\u001a\u00020\f2\u0006\u0010B\u001a\u00020\f2\u0006\u0010C\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u0001H\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bE\u0010FJ\u0017\u0010H\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0004H\u0016¢\u0006\u0004\bH\u0010(J\u001a\u0010L\u001a\u00020K2\b\u0010J\u001a\u0004\u0018\u00010IH\u0096\u0002¢\u0006\u0004\bL\u0010MJ\u000f\u0010N\u001a\u00020\u0011H\u0016¢\u0006\u0004\bN\u0010OR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010P\u001a\u0004\bQ\u0010RR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010SR\u0014\u0010\u000e\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010SR\u0019\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010T\u001a\u0004\bU\u0010VR\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0005\u0010W\u001a\u0004\bX\u0010%R\u001a\u0010\b\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\b\u0010W\u001a\u0004\bY\u0010%R\u001a\u0010)\u001a\u00020\u00048\u0000X\u0080\u0004¢\u0006\f\n\u0004\b)\u0010W\u001a\u0004\bZ\u0010%R\u001a\u0010[\u001a\u00020\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010^R#\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u00168\u0006¢\u0006\f\n\u0004\b\n\u0010_\u001a\u0004\b`\u0010aR\u001a\u0010b\u001a\u00020\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bb\u0010\\\u001a\u0004\bc\u0010^R\u001a\u0010d\u001a\u00020\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bd\u0010\\\u001a\u0004\be\u0010^R#\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u00168\u0006¢\u0006\f\n\u0004\b\u000b\u0010_\u001a\u0004\bf\u0010aR\u001a\u0010g\u001a\u00020\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bg\u0010\\\u001a\u0004\bh\u0010^R\u001a\u0010i\u001a\u00020K8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bi\u0010j\u001a\u0004\bi\u0010kR\u001a\u0010l\u001a\u00020K8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bl\u0010j\u001a\u0004\bl\u0010k\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006n"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Rgb;", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "", ContentDisposition.Parameters.Name, "", "primaries", "Landroidx/compose/ui/graphics/colorspace/WhitePoint;", "whitePoint", "transform", "Landroidx/compose/ui/graphics/colorspace/DoubleFunction;", "oetf", "eotf", "", "min", "max", "Landroidx/compose/ui/graphics/colorspace/TransferParameters;", "transferParameters", "", TtmlNode.ATTR_ID, "<init>", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;[FLandroidx/compose/ui/graphics/colorspace/DoubleFunction;Landroidx/compose/ui/graphics/colorspace/DoubleFunction;FFLandroidx/compose/ui/graphics/colorspace/TransferParameters;I)V", "toXYZ", "Lkotlin/Function1;", "", "(Ljava/lang/String;[FLr7/l;Lr7/l;)V", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;Lr7/l;Lr7/l;FF)V", "function", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/TransferParameters;)V", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;Landroidx/compose/ui/graphics/colorspace/TransferParameters;)V", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;Landroidx/compose/ui/graphics/colorspace/TransferParameters;I)V", "gamma", "(Ljava/lang/String;[FD)V", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;D)V", "(Ljava/lang/String;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;DFFI)V", "colorSpace", "(Landroidx/compose/ui/graphics/colorspace/Rgb;[FLandroidx/compose/ui/graphics/colorspace/WhitePoint;)V", "getPrimaries", "()[F", "getTransform", "getInverseTransform", "([F)[F", "inverseTransform", "component", "getMinValue", "(I)F", "getMaxValue", "r", "g", "b", "toLinear", "(FFF)[F", "v", "fromLinear", "toXyz", "v0", "v1", "v2", "", "toXy$ui_graphics_release", "(FFF)J", "toXy", "toZ$ui_graphics_release", "(FFF)F", "toZ", "x", "y", "z", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "Landroidx/compose/ui/graphics/Color;", "xyzaToColor-JlNiLsg$ui_graphics_release", "(FFFFLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "xyzaToColor", "fromXyz", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "Landroidx/compose/ui/graphics/colorspace/WhitePoint;", "getWhitePoint", "()Landroidx/compose/ui/graphics/colorspace/WhitePoint;", "F", "Landroidx/compose/ui/graphics/colorspace/TransferParameters;", "getTransferParameters", "()Landroidx/compose/ui/graphics/colorspace/TransferParameters;", "[F", "getPrimaries$ui_graphics_release", "getTransform$ui_graphics_release", "getInverseTransform$ui_graphics_release", "oetfOrig", "Landroidx/compose/ui/graphics/colorspace/DoubleFunction;", "getOetfOrig$ui_graphics_release", "()Landroidx/compose/ui/graphics/colorspace/DoubleFunction;", "Lr7/l;", "getOetf", "()Lr7/l;", "oetfFunc", "getOetfFunc$ui_graphics_release", "eotfOrig", "getEotfOrig$ui_graphics_release", "getEotf", "eotfFunc", "getEotfFunc$ui_graphics_release", "isWideGamut", "Z", "()Z", "isSrgb", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Rgb extends ColorSpace {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DoubleFunction DoubleIdentity = new androidx.compose.ui.graphics.c(13);
    private final l<Double, Double> eotf;
    private final DoubleFunction eotfFunc;
    private final DoubleFunction eotfOrig;
    private final float[] inverseTransform;
    private final boolean isSrgb;
    private final boolean isWideGamut;
    private final float max;
    private final float min;
    private final l<Double, Double> oetf;
    private final DoubleFunction oetfFunc;
    private final DoubleFunction oetfOrig;
    private final float[] primaries;
    private final TransferParameters transferParameters;
    private final float[] transform;
    private final WhitePoint whitePoint;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u0015\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\bH\u0002J\u0018\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0013H\u0002J\u0018\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\bH\u0002J(\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J@\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020$H\u0002J \u0010%\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u0006H\u0002J\u0010\u0010&\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Rgb$Companion;", "", "()V", "DoubleIdentity", "Landroidx/compose/ui/graphics/colorspace/DoubleFunction;", "area", "", "primaries", "", "compare", "", "point", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "computePrimaries", "toXYZ", "computePrimaries$ui_graphics_release", "computeWhitePoint", "Landroidx/compose/ui/graphics/colorspace/WhitePoint;", "computeXYZMatrix", "whitePoint", "contains", "p1", "p2", "cross", "ax", "ay", "bx", "by", "isSrgb", "OETF", "EOTF", "min", "max", TtmlNode.ATTR_ID, "", "isWideGamut", "xyPrimaries", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private final float area(float[] primaries) {
            float f10 = primaries[0];
            float f11 = primaries[1];
            float f12 = primaries[2];
            float f13 = primaries[3];
            float f14 = primaries[4];
            float f15 = primaries[5];
            float f16 = (((((f12 * f15) + ((f11 * f14) + (f10 * f13))) - (f13 * f14)) - (f11 * f12)) - (f10 * f15)) * 0.5f;
            return f16 < 0.0f ? -f16 : f16;
        }

        private final boolean compare(double point, DoubleFunction a10, DoubleFunction b10) {
            return Math.abs(a10.invoke(point) - b10.invoke(point)) <= 0.001d;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final WhitePoint computeWhitePoint(float[] toXYZ) {
            float[] fArrMul3x3Float3 = ColorSpaceKt.mul3x3Float3(toXYZ, new float[]{1.0f, 1.0f, 1.0f});
            float f10 = fArrMul3x3Float3[0];
            float f11 = fArrMul3x3Float3[1];
            float f12 = f10 + f11 + fArrMul3x3Float3[2];
            return new WhitePoint(f10 / f12, f11 / f12);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float[] computeXYZMatrix(float[] primaries, WhitePoint whitePoint) {
            float f10 = primaries[0];
            float f11 = primaries[1];
            float f12 = primaries[2];
            float f13 = primaries[3];
            float f14 = primaries[4];
            float f15 = primaries[5];
            float x = whitePoint.getX();
            float y10 = whitePoint.getY();
            float f16 = 1;
            float f17 = (f16 - f10) / f11;
            float f18 = (f16 - f12) / f13;
            float f19 = (f16 - f14) / f15;
            float f20 = (f16 - x) / y10;
            float f21 = f10 / f11;
            float f22 = (f12 / f13) - f21;
            float f23 = (x / y10) - f21;
            float f24 = f18 - f17;
            float f25 = (f14 / f15) - f21;
            float f26 = (((f20 - f17) * f22) - (f23 * f24)) / (((f19 - f17) * f22) - (f24 * f25));
            float f27 = (f23 - (f25 * f26)) / f22;
            float f28 = (1.0f - f27) - f26;
            float f29 = f28 / f11;
            float f30 = f27 / f13;
            float f31 = f26 / f15;
            return new float[]{f29 * f10, f28, ((1.0f - f10) - f11) * f29, f30 * f12, f27, ((1.0f - f12) - f13) * f30, f31 * f14, f26, ((1.0f - f14) - f15) * f31};
        }

        private final boolean contains(float[] p12, float[] p22) {
            float f10 = p12[0];
            float f11 = p22[0];
            float f12 = p12[1];
            float f13 = p22[1];
            float f14 = p12[2] - p22[2];
            float f15 = p12[3] - p22[3];
            float f16 = p12[4];
            float f17 = p22[4];
            float f18 = p12[5];
            float f19 = p22[5];
            float[] fArr = {f10 - f11, f12 - f13, f14, f15, f16 - f17, f18 - f19};
            return cross(fArr[0], fArr[1], f11 - f17, f13 - f19) >= 0.0f && cross(p22[0] - p22[2], p22[1] - p22[3], fArr[0], fArr[1]) >= 0.0f && cross(fArr[2], fArr[3], p22[2] - p22[0], p22[3] - p22[1]) >= 0.0f && cross(p22[2] - p22[4], p22[3] - p22[5], fArr[2], fArr[3]) >= 0.0f && cross(fArr[4], fArr[5], p22[4] - p22[2], p22[5] - p22[3]) >= 0.0f && cross(p22[4] - p22[0], p22[5] - p22[1], fArr[4], fArr[5]) >= 0.0f;
        }

        private final float cross(float ax, float ay, float bx, float by) {
            return (ax * by) - (ay * bx);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isSrgb(float[] primaries, WhitePoint whitePoint, DoubleFunction OETF, DoubleFunction EOTF, float min, float max, int id) {
            if (id == 0) {
                return true;
            }
            ColorSpaces colorSpaces = ColorSpaces.INSTANCE;
            if (!ColorSpaceKt.compare(primaries, colorSpaces.getSrgbPrimaries$ui_graphics_release()) || !ColorSpaceKt.compare(whitePoint, Illuminant.INSTANCE.getD65()) || min != 0.0f || max != 1.0f) {
                return false;
            }
            Rgb srgb = colorSpaces.getSrgb();
            for (double d4 = 0.0d; d4 <= 1.0d; d4 += 0.00392156862745098d) {
                if (!compare(d4, OETF, srgb.getOetfOrig()) || !compare(d4, EOTF, srgb.getEotfOrig())) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isWideGamut(float[] primaries, float min, float max) {
            float fArea = area(primaries);
            ColorSpaces colorSpaces = ColorSpaces.INSTANCE;
            if (fArea / area(colorSpaces.getNtsc1953Primaries$ui_graphics_release()) <= 0.9f || !contains(primaries, colorSpaces.getSrgbPrimaries$ui_graphics_release())) {
                return min < 0.0f && max > 1.0f;
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float[] xyPrimaries(float[] primaries) {
            float[] fArr = new float[6];
            if (primaries.length != 9) {
                r.Q(primaries, fArr, 6);
                return fArr;
            }
            float f10 = primaries[0];
            float f11 = primaries[1];
            float f12 = f10 + f11 + primaries[2];
            fArr[0] = f10 / f12;
            fArr[1] = f11 / f12;
            float f13 = primaries[3];
            float f14 = primaries[4];
            float f15 = f13 + f14 + primaries[5];
            fArr[2] = f13 / f15;
            fArr[3] = f14 / f15;
            float f16 = primaries[6];
            float f17 = primaries[7];
            float f18 = f16 + f17 + primaries[8];
            fArr[4] = f16 / f18;
            fArr[5] = f17 / f18;
            return fArr;
        }

        public final float[] computePrimaries$ui_graphics_release(float[] toXYZ) {
            float[] fArrMul3x3Float3 = ColorSpaceKt.mul3x3Float3(toXYZ, new float[]{1.0f, 0.0f, 0.0f});
            float[] fArrMul3x3Float32 = ColorSpaceKt.mul3x3Float3(toXYZ, new float[]{0.0f, 1.0f, 0.0f});
            float[] fArrMul3x3Float33 = ColorSpaceKt.mul3x3Float3(toXYZ, new float[]{0.0f, 0.0f, 1.0f});
            float f10 = fArrMul3x3Float3[0];
            float f11 = fArrMul3x3Float3[1];
            float f12 = f10 + f11 + fArrMul3x3Float3[2];
            float f13 = fArrMul3x3Float32[0];
            float f14 = fArrMul3x3Float32[1];
            float f15 = f13 + f14 + fArrMul3x3Float32[2];
            float f16 = fArrMul3x3Float33[0];
            float f17 = fArrMul3x3Float33[1];
            float f18 = f16 + f17 + fArrMul3x3Float33[2];
            return new float[]{f10 / f12, f11 / f12, f13 / f15, f14 / f15, f16 / f18, f17 / f18};
        }

        private Companion() {
        }
    }

    public Rgb(String str, float[] fArr, WhitePoint whitePoint, float[] fArr2, DoubleFunction doubleFunction, DoubleFunction doubleFunction2, float f10, float f11, TransferParameters transferParameters, int i10) {
        super(str, ColorModel.INSTANCE.m3864getRgbxdoWZVw(), i10, null);
        this.whitePoint = whitePoint;
        this.min = f10;
        this.max = f11;
        this.transferParameters = transferParameters;
        this.oetfOrig = doubleFunction;
        this.oetf = new Rgb$oetf$1(this);
        final int i11 = 0;
        this.oetfFunc = new DoubleFunction(this) { // from class: androidx.compose.ui.graphics.colorspace.a

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Rgb f1899b;

            {
                this.f1899b = this;
            }

            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d4) {
                switch (i11) {
                    case 0:
                        return Rgb.oetfFunc$lambda$0(this.f1899b, d4);
                    default:
                        return Rgb.eotfFunc$lambda$1(this.f1899b, d4);
                }
            }
        };
        this.eotfOrig = doubleFunction2;
        this.eotf = new Rgb$eotf$1(this);
        final int i12 = 1;
        this.eotfFunc = new DoubleFunction(this) { // from class: androidx.compose.ui.graphics.colorspace.a

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Rgb f1899b;

            {
                this.f1899b = this;
            }

            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d4) {
                switch (i12) {
                    case 0:
                        return Rgb.oetfFunc$lambda$0(this.f1899b, d4);
                    default:
                        return Rgb.eotfFunc$lambda$1(this.f1899b, d4);
                }
            }
        };
        if (fArr.length != 6 && fArr.length != 9) {
            throw new IllegalArgumentException("The color space's primaries must be defined as an array of 6 floats in xyY or 9 floats in XYZ");
        }
        if (f10 >= f11) {
            throw new IllegalArgumentException("Invalid range: min=" + f10 + ", max=" + f11 + "; min must be strictly < max");
        }
        Companion companion = INSTANCE;
        float[] fArrXyPrimaries = companion.xyPrimaries(fArr);
        this.primaries = fArrXyPrimaries;
        if (fArr2 == null) {
            this.transform = companion.computeXYZMatrix(fArrXyPrimaries, whitePoint);
        } else {
            if (fArr2.length != 9) {
                throw new IllegalArgumentException("Transform must have 9 entries! Has " + fArr2.length);
            }
            this.transform = fArr2;
        }
        this.inverseTransform = ColorSpaceKt.inverse3x3(this.transform);
        this.isWideGamut = companion.isWideGamut(fArrXyPrimaries, f10, f11);
        this.isSrgb = companion.isSrgb(fArrXyPrimaries, whitePoint, doubleFunction, doubleFunction2, f10, f11, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double DoubleIdentity$lambda$12(double d4) {
        return d4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double _init_$lambda$10(double d4, double d10) {
        if (d10 < 0.0d) {
            d10 = 0.0d;
        }
        return Math.pow(d10, 1.0d / d4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double _init_$lambda$11(double d4, double d10) {
        if (d10 < 0.0d) {
            d10 = 0.0d;
        }
        return Math.pow(d10, d4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double _init_$lambda$2(l lVar, double d4) {
        return ((Number) lVar.invoke(Double.valueOf(d4))).doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double _init_$lambda$3(l lVar, double d4) {
        return ((Number) lVar.invoke(Double.valueOf(d4))).doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double _init_$lambda$4(l lVar, double d4) {
        return ((Number) lVar.invoke(Double.valueOf(d4))).doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double _init_$lambda$5(l lVar, double d4) {
        return ((Number) lVar.invoke(Double.valueOf(d4))).doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double _init_$lambda$6(TransferParameters transferParameters, double d4) {
        return ColorSpaceKt.rcpResponse(d4, transferParameters.getA(), transferParameters.getB(), transferParameters.getC(), transferParameters.getD(), transferParameters.getGamma());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double _init_$lambda$7(TransferParameters transferParameters, double d4) {
        return ColorSpaceKt.rcpResponse(d4, transferParameters.getA(), transferParameters.getB(), transferParameters.getC(), transferParameters.getD(), transferParameters.getE(), transferParameters.getF(), transferParameters.getGamma());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double _init_$lambda$8(TransferParameters transferParameters, double d4) {
        return ColorSpaceKt.response(d4, transferParameters.getA(), transferParameters.getB(), transferParameters.getC(), transferParameters.getD(), transferParameters.getGamma());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double _init_$lambda$9(TransferParameters transferParameters, double d4) {
        return ColorSpaceKt.response(d4, transferParameters.getA(), transferParameters.getB(), transferParameters.getC(), transferParameters.getD(), transferParameters.getE(), transferParameters.getF(), transferParameters.getGamma());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double eotfFunc$lambda$1(Rgb rgb, double d4) {
        return rgb.eotfOrig.invoke(qb.d.l(d4, rgb.min, rgb.max));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double oetfFunc$lambda$0(Rgb rgb, double d4) {
        return qb.d.l(rgb.oetfOrig.invoke(d4), rgb.min, rgb.max);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || Rgb.class != other.getClass() || !super.equals(other)) {
            return false;
        }
        Rgb rgb = (Rgb) other;
        if (Float.compare(rgb.min, this.min) != 0 || Float.compare(rgb.max, this.max) != 0 || !p.a(this.whitePoint, rgb.whitePoint) || !Arrays.equals(this.primaries, rgb.primaries)) {
            return false;
        }
        TransferParameters transferParameters = this.transferParameters;
        if (transferParameters != null) {
            return p.a(transferParameters, rgb.transferParameters);
        }
        if (rgb.transferParameters == null) {
            return true;
        }
        if (p.a(this.oetfOrig, rgb.oetfOrig)) {
            return p.a(this.eotfOrig, rgb.eotfOrig);
        }
        return false;
    }

    public final float[] fromLinear(float r4, float g10, float b10) {
        return fromLinear(new float[]{r4, g10, b10});
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float[] fromXyz(float[] v2) {
        ColorSpaceKt.mul3x3Float3(this.inverseTransform, v2);
        v2[0] = (float) this.oetfFunc.invoke(v2[0]);
        v2[1] = (float) this.oetfFunc.invoke(v2[1]);
        v2[2] = (float) this.oetfFunc.invoke(v2[2]);
        return v2;
    }

    public final l<Double, Double> getEotf() {
        return this.eotf;
    }

    /* JADX INFO: renamed from: getEotfFunc$ui_graphics_release, reason: from getter */
    public final DoubleFunction getEotfFunc() {
        return this.eotfFunc;
    }

    /* JADX INFO: renamed from: getEotfOrig$ui_graphics_release, reason: from getter */
    public final DoubleFunction getEotfOrig() {
        return this.eotfOrig;
    }

    public final float[] getInverseTransform() {
        float[] fArr = this.inverseTransform;
        return Arrays.copyOf(fArr, fArr.length);
    }

    /* JADX INFO: renamed from: getInverseTransform$ui_graphics_release, reason: from getter */
    public final float[] getInverseTransform() {
        return this.inverseTransform;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float getMaxValue(int component) {
        return this.max;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float getMinValue(int component) {
        return this.min;
    }

    public final l<Double, Double> getOetf() {
        return this.oetf;
    }

    /* JADX INFO: renamed from: getOetfFunc$ui_graphics_release, reason: from getter */
    public final DoubleFunction getOetfFunc() {
        return this.oetfFunc;
    }

    /* JADX INFO: renamed from: getOetfOrig$ui_graphics_release, reason: from getter */
    public final DoubleFunction getOetfOrig() {
        return this.oetfOrig;
    }

    public final float[] getPrimaries() {
        float[] fArr = this.primaries;
        return Arrays.copyOf(fArr, fArr.length);
    }

    /* JADX INFO: renamed from: getPrimaries$ui_graphics_release, reason: from getter */
    public final float[] getPrimaries() {
        return this.primaries;
    }

    public final TransferParameters getTransferParameters() {
        return this.transferParameters;
    }

    public final float[] getTransform() {
        float[] fArr = this.transform;
        return Arrays.copyOf(fArr, fArr.length);
    }

    /* JADX INFO: renamed from: getTransform$ui_graphics_release, reason: from getter */
    public final float[] getTransform() {
        return this.transform;
    }

    public final WhitePoint getWhitePoint() {
        return this.whitePoint;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public int hashCode() {
        int iHashCode = (Arrays.hashCode(this.primaries) + ((this.whitePoint.hashCode() + (super.hashCode() * 31)) * 31)) * 31;
        float f10 = this.min;
        int iFloatToIntBits = (iHashCode + (f10 == 0.0f ? 0 : Float.floatToIntBits(f10))) * 31;
        float f11 = this.max;
        int iFloatToIntBits2 = (iFloatToIntBits + (f11 == 0.0f ? 0 : Float.floatToIntBits(f11))) * 31;
        TransferParameters transferParameters = this.transferParameters;
        int iHashCode2 = iFloatToIntBits2 + (transferParameters != null ? transferParameters.hashCode() : 0);
        if (this.transferParameters == null) {
            return this.eotfOrig.hashCode() + ((this.oetfOrig.hashCode() + (iHashCode2 * 31)) * 31);
        }
        return iHashCode2;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: isSrgb, reason: from getter */
    public boolean getIsSrgb() {
        return this.isSrgb;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: isWideGamut, reason: from getter */
    public boolean getIsWideGamut() {
        return this.isWideGamut;
    }

    public final float[] toLinear(float r4, float g10, float b10) {
        return toLinear(new float[]{r4, g10, b10});
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public long toXy$ui_graphics_release(float v0, float v12, float v2) {
        float fInvoke = (float) this.eotfFunc.invoke(v0);
        float fInvoke2 = (float) this.eotfFunc.invoke(v12);
        float fInvoke3 = (float) this.eotfFunc.invoke(v2);
        return (((long) Float.floatToRawIntBits(ColorSpaceKt.mul3x3Float3_0(this.transform, fInvoke, fInvoke2, fInvoke3))) << 32) | (((long) Float.floatToRawIntBits(ColorSpaceKt.mul3x3Float3_1(this.transform, fInvoke, fInvoke2, fInvoke3))) & 4294967295L);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float[] toXyz(float[] v2) {
        v2[0] = (float) this.eotfFunc.invoke(v2[0]);
        v2[1] = (float) this.eotfFunc.invoke(v2[1]);
        v2[2] = (float) this.eotfFunc.invoke(v2[2]);
        return ColorSpaceKt.mul3x3Float3(this.transform, v2);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float toZ$ui_graphics_release(float v0, float v12, float v2) {
        return ColorSpaceKt.mul3x3Float3_2(this.transform, (float) this.eotfFunc.invoke(v0), (float) this.eotfFunc.invoke(v12), (float) this.eotfFunc.invoke(v2));
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: xyzaToColor-JlNiLsg$ui_graphics_release */
    public long mo3867xyzaToColorJlNiLsg$ui_graphics_release(float x, float y10, float z, float a10, ColorSpace colorSpace) {
        return ColorKt.Color((float) this.oetfFunc.invoke(ColorSpaceKt.mul3x3Float3_0(this.inverseTransform, x, y10, z)), (float) this.oetfFunc.invoke(ColorSpaceKt.mul3x3Float3_1(this.inverseTransform, x, y10, z)), (float) this.oetfFunc.invoke(ColorSpaceKt.mul3x3Float3_2(this.inverseTransform, x, y10, z)), a10, colorSpace);
    }

    public final float[] fromLinear(float[] v2) {
        v2[0] = (float) this.oetfFunc.invoke(v2[0]);
        v2[1] = (float) this.oetfFunc.invoke(v2[1]);
        v2[2] = (float) this.oetfFunc.invoke(v2[2]);
        return v2;
    }

    public final float[] getInverseTransform(float[] inverseTransform) {
        r.Q(this.inverseTransform, inverseTransform, 14);
        return inverseTransform;
    }

    public final float[] getPrimaries(float[] primaries) {
        r.Q(this.primaries, primaries, 14);
        return primaries;
    }

    public final float[] getTransform(float[] transform) {
        r.Q(this.transform, transform, 14);
        return transform;
    }

    public final float[] toLinear(float[] v2) {
        v2[0] = (float) this.eotfFunc.invoke(v2[0]);
        v2[1] = (float) this.eotfFunc.invoke(v2[1]);
        v2[2] = (float) this.eotfFunc.invoke(v2[2]);
        return v2;
    }

    public Rgb(String str, float[] fArr, final l<? super Double, Double> lVar, final l<? super Double, Double> lVar2) {
        Companion companion = INSTANCE;
        float[] fArrComputePrimaries$ui_graphics_release = companion.computePrimaries$ui_graphics_release(fArr);
        WhitePoint whitePointComputeWhitePoint = companion.computeWhitePoint(fArr);
        final int i10 = 0;
        DoubleFunction doubleFunction = new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.c
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d4) {
                switch (i10) {
                    case 0:
                        return Rgb._init_$lambda$2(lVar, d4);
                    case 1:
                        return Rgb._init_$lambda$3(lVar, d4);
                    case 2:
                        return Rgb._init_$lambda$4(lVar, d4);
                    default:
                        return Rgb._init_$lambda$5(lVar, d4);
                }
            }
        };
        final int i11 = 1;
        this(str, fArrComputePrimaries$ui_graphics_release, whitePointComputeWhitePoint, null, doubleFunction, new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.c
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d4) {
                switch (i11) {
                    case 0:
                        return Rgb._init_$lambda$2(lVar2, d4);
                    case 1:
                        return Rgb._init_$lambda$3(lVar2, d4);
                    case 2:
                        return Rgb._init_$lambda$4(lVar2, d4);
                    default:
                        return Rgb._init_$lambda$5(lVar2, d4);
                }
            }
        }, 0.0f, 1.0f, null, -1);
    }

    public Rgb(String str, float[] fArr, WhitePoint whitePoint, final l<? super Double, Double> lVar, final l<? super Double, Double> lVar2, float f10, float f11) {
        final int i10 = 2;
        DoubleFunction doubleFunction = new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.c
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d4) {
                switch (i10) {
                    case 0:
                        return Rgb._init_$lambda$2(lVar, d4);
                    case 1:
                        return Rgb._init_$lambda$3(lVar, d4);
                    case 2:
                        return Rgb._init_$lambda$4(lVar, d4);
                    default:
                        return Rgb._init_$lambda$5(lVar, d4);
                }
            }
        };
        final int i11 = 3;
        this(str, fArr, whitePoint, null, doubleFunction, new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.c
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d4) {
                switch (i11) {
                    case 0:
                        return Rgb._init_$lambda$2(lVar2, d4);
                    case 1:
                        return Rgb._init_$lambda$3(lVar2, d4);
                    case 2:
                        return Rgb._init_$lambda$4(lVar2, d4);
                    default:
                        return Rgb._init_$lambda$5(lVar2, d4);
                }
            }
        }, f10, f11, null, -1);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Rgb(String str, float[] fArr, TransferParameters transferParameters) {
        Companion companion = INSTANCE;
        this(str, companion.computePrimaries$ui_graphics_release(fArr), companion.computeWhitePoint(fArr), transferParameters, -1);
    }

    public Rgb(String str, float[] fArr, WhitePoint whitePoint, TransferParameters transferParameters) {
        this(str, fArr, whitePoint, transferParameters, -1);
    }

    public Rgb(String str, float[] fArr, WhitePoint whitePoint, final TransferParameters transferParameters, int i10) {
        DoubleFunction doubleFunction;
        DoubleFunction doubleFunction2;
        if (transferParameters.getE() == 0.0d && transferParameters.getF() == 0.0d) {
            final int i11 = 3;
            doubleFunction = new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.b
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d4) {
                    switch (i11) {
                        case 0:
                            return Rgb._init_$lambda$7(transferParameters, d4);
                        case 1:
                            return Rgb._init_$lambda$8(transferParameters, d4);
                        case 2:
                            return Rgb._init_$lambda$9(transferParameters, d4);
                        default:
                            return Rgb._init_$lambda$6(transferParameters, d4);
                    }
                }
            };
        } else {
            final int i12 = 0;
            doubleFunction = new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.b
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d4) {
                    switch (i12) {
                        case 0:
                            return Rgb._init_$lambda$7(transferParameters, d4);
                        case 1:
                            return Rgb._init_$lambda$8(transferParameters, d4);
                        case 2:
                            return Rgb._init_$lambda$9(transferParameters, d4);
                        default:
                            return Rgb._init_$lambda$6(transferParameters, d4);
                    }
                }
            };
        }
        DoubleFunction doubleFunction3 = doubleFunction;
        if (transferParameters.getE() == 0.0d && transferParameters.getF() == 0.0d) {
            final int i13 = 1;
            doubleFunction2 = new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.b
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d4) {
                    switch (i13) {
                        case 0:
                            return Rgb._init_$lambda$7(transferParameters, d4);
                        case 1:
                            return Rgb._init_$lambda$8(transferParameters, d4);
                        case 2:
                            return Rgb._init_$lambda$9(transferParameters, d4);
                        default:
                            return Rgb._init_$lambda$6(transferParameters, d4);
                    }
                }
            };
        } else {
            final int i14 = 2;
            doubleFunction2 = new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.b
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d4) {
                    switch (i14) {
                        case 0:
                            return Rgb._init_$lambda$7(transferParameters, d4);
                        case 1:
                            return Rgb._init_$lambda$8(transferParameters, d4);
                        case 2:
                            return Rgb._init_$lambda$9(transferParameters, d4);
                        default:
                            return Rgb._init_$lambda$6(transferParameters, d4);
                    }
                }
            };
        }
        this(str, fArr, whitePoint, null, doubleFunction3, doubleFunction2, 0.0f, 1.0f, transferParameters, i10);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Rgb(String str, float[] fArr, double d4) {
        Companion companion = INSTANCE;
        this(str, companion.computePrimaries$ui_graphics_release(fArr), companion.computeWhitePoint(fArr), d4, 0.0f, 1.0f, -1);
    }

    public Rgb(String str, float[] fArr, WhitePoint whitePoint, double d4) {
        this(str, fArr, whitePoint, d4, 0.0f, 1.0f, -1);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Rgb(String str, float[] fArr, WhitePoint whitePoint, final double d4, float f10, float f11, int i10) {
        DoubleFunction doubleFunction;
        DoubleFunction doubleFunction2;
        if (d4 == 1.0d) {
            doubleFunction = DoubleIdentity;
        } else {
            final int i11 = 0;
            doubleFunction = new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.d
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d10) {
                    switch (i11) {
                        case 0:
                            return Rgb._init_$lambda$10(d4, d10);
                        default:
                            return Rgb._init_$lambda$11(d4, d10);
                    }
                }
            };
        }
        DoubleFunction doubleFunction3 = doubleFunction;
        if (d4 == 1.0d) {
            doubleFunction2 = DoubleIdentity;
        } else {
            final int i12 = 1;
            doubleFunction2 = new DoubleFunction() { // from class: androidx.compose.ui.graphics.colorspace.d
                @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
                public final double invoke(double d10) {
                    switch (i12) {
                        case 0:
                            return Rgb._init_$lambda$10(d4, d10);
                        default:
                            return Rgb._init_$lambda$11(d4, d10);
                    }
                }
            };
        }
        this(str, fArr, whitePoint, null, doubleFunction3, doubleFunction2, f10, f11, new TransferParameters(d4, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 96, null), i10);
    }

    public Rgb(Rgb rgb, float[] fArr, WhitePoint whitePoint) {
        this(rgb.getName(), rgb.primaries, whitePoint, fArr, rgb.oetfOrig, rgb.eotfOrig, rgb.min, rgb.max, rgb.transferParameters, -1);
    }
}
