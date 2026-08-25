package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.ColorKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 #2\u00020\u0001:\u0001#B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J%\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u0016J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J%\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u0019J=\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u0001H\u0010ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\"R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\t\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/Oklab;", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", ContentDisposition.Parameters.Name, "", TtmlNode.ATTR_ID, "", "(Ljava/lang/String;I)V", "isWideGamut", "", "()Z", "fromXyz", "", "v", "getMaxValue", "", "component", "getMinValue", "toXy", "", "v0", "v1", "v2", "toXy$ui_graphics_release", "toXyz", "toZ", "toZ$ui_graphics_release", "xyzaToColor", "Landroidx/compose/ui/graphics/Color;", "x", "y", "z", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "colorSpace", "xyzaToColor-JlNiLsg$ui_graphics_release", "(FFFFLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Oklab extends ColorSpace {
    private static final float[] InverseM1;
    private static final float[] InverseM2;
    private static final float[] M1;
    private static final float[] M2;

    static {
        float[] transform = Adaptation.INSTANCE.getBradford().getTransform();
        Illuminant illuminant = Illuminant.INSTANCE;
        float[] fArrMul3x3 = ColorSpaceKt.mul3x3(new float[]{0.818933f, 0.032984544f, 0.0482003f, 0.36186674f, 0.9293119f, 0.26436627f, -0.12885971f, 0.03614564f, 0.6338517f}, ColorSpaceKt.chromaticAdaptation(transform, illuminant.getD50().toXyz$ui_graphics_release(), illuminant.getD65().toXyz$ui_graphics_release()));
        M1 = fArrMul3x3;
        float[] fArr = {0.21045426f, 1.9779985f, 0.025904037f, 0.7936178f, -2.4285922f, 0.78277177f, -0.004072047f, 0.4505937f, -0.80867577f};
        M2 = fArr;
        InverseM1 = ColorSpaceKt.inverse3x3(fArrMul3x3);
        InverseM2 = ColorSpaceKt.inverse3x3(fArr);
    }

    public Oklab(String str, int i10) {
        super(str, ColorModel.INSTANCE.m3863getLabxdoWZVw(), i10, null);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float[] fromXyz(float[] v2) {
        ColorSpaceKt.mul3x3Float3(M1, v2);
        double d4 = 0.33333334f;
        v2[0] = Math.signum(v2[0]) * ((float) Math.pow(Math.abs(v2[0]), d4));
        v2[1] = Math.signum(v2[1]) * ((float) Math.pow(Math.abs(v2[1]), d4));
        v2[2] = Math.signum(v2[2]) * ((float) Math.pow(Math.abs(v2[2]), d4));
        ColorSpaceKt.mul3x3Float3(M2, v2);
        return v2;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float getMaxValue(int component) {
        return component == 0 ? 1.0f : 0.5f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float getMinValue(int component) {
        return component == 0 ? 0.0f : -0.5f;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: isWideGamut */
    public boolean getIsWideGamut() {
        return true;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public long toXy$ui_graphics_release(float v0, float v12, float v2) {
        float fM = qb.d.m(v0, 0.0f, 1.0f);
        float fM2 = qb.d.m(v12, -0.5f, 0.5f);
        float fM3 = qb.d.m(v2, -0.5f, 0.5f);
        float[] fArr = InverseM2;
        float fMul3x3Float3_0 = ColorSpaceKt.mul3x3Float3_0(fArr, fM, fM2, fM3);
        float fMul3x3Float3_1 = ColorSpaceKt.mul3x3Float3_1(fArr, fM, fM2, fM3);
        float fMul3x3Float3_2 = ColorSpaceKt.mul3x3Float3_2(fArr, fM, fM2, fM3);
        float f10 = fMul3x3Float3_0 * fMul3x3Float3_0 * fMul3x3Float3_0;
        float f11 = fMul3x3Float3_1 * fMul3x3Float3_1 * fMul3x3Float3_1;
        float f12 = fMul3x3Float3_2 * fMul3x3Float3_2 * fMul3x3Float3_2;
        float[] fArr2 = InverseM1;
        float fMul3x3Float3_02 = ColorSpaceKt.mul3x3Float3_0(fArr2, f10, f11, f12);
        float fMul3x3Float3_12 = ColorSpaceKt.mul3x3Float3_1(fArr2, f10, f11, f12);
        return (((long) Float.floatToRawIntBits(fMul3x3Float3_02)) << 32) | (((long) Float.floatToRawIntBits(fMul3x3Float3_12)) & 4294967295L);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float[] toXyz(float[] v2) {
        v2[0] = qb.d.m(v2[0], 0.0f, 1.0f);
        v2[1] = qb.d.m(v2[1], -0.5f, 0.5f);
        v2[2] = qb.d.m(v2[2], -0.5f, 0.5f);
        ColorSpaceKt.mul3x3Float3(InverseM2, v2);
        float f10 = v2[0];
        v2[0] = f10 * f10 * f10;
        float f11 = v2[1];
        v2[1] = f11 * f11 * f11;
        float f12 = v2[2];
        v2[2] = f12 * f12 * f12;
        ColorSpaceKt.mul3x3Float3(InverseM1, v2);
        return v2;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public float toZ$ui_graphics_release(float v0, float v12, float v2) {
        float fM = qb.d.m(v0, 0.0f, 1.0f);
        float fM2 = qb.d.m(v12, -0.5f, 0.5f);
        float fM3 = qb.d.m(v2, -0.5f, 0.5f);
        float[] fArr = InverseM2;
        float fMul3x3Float3_0 = ColorSpaceKt.mul3x3Float3_0(fArr, fM, fM2, fM3);
        float fMul3x3Float3_1 = ColorSpaceKt.mul3x3Float3_1(fArr, fM, fM2, fM3);
        float fMul3x3Float3_2 = ColorSpaceKt.mul3x3Float3_2(fArr, fM, fM2, fM3);
        float f10 = fMul3x3Float3_2 * fMul3x3Float3_2 * fMul3x3Float3_2;
        return ColorSpaceKt.mul3x3Float3_2(InverseM1, fMul3x3Float3_0 * fMul3x3Float3_0 * fMul3x3Float3_0, fMul3x3Float3_1 * fMul3x3Float3_1 * fMul3x3Float3_1, f10);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* JADX INFO: renamed from: xyzaToColor-JlNiLsg$ui_graphics_release */
    public long mo3867xyzaToColorJlNiLsg$ui_graphics_release(float x, float y10, float z, float a10, ColorSpace colorSpace) {
        float[] fArr = M1;
        float fMul3x3Float3_0 = ColorSpaceKt.mul3x3Float3_0(fArr, x, y10, z);
        float fMul3x3Float3_1 = ColorSpaceKt.mul3x3Float3_1(fArr, x, y10, z);
        float fMul3x3Float3_2 = ColorSpaceKt.mul3x3Float3_2(fArr, x, y10, z);
        double d4 = 0.33333334f;
        float fSignum = Math.signum(fMul3x3Float3_0) * ((float) Math.pow(Math.abs(fMul3x3Float3_0), d4));
        float fSignum2 = Math.signum(fMul3x3Float3_1) * ((float) Math.pow(Math.abs(fMul3x3Float3_1), d4));
        float fSignum3 = Math.signum(fMul3x3Float3_2) * ((float) Math.pow(Math.abs(fMul3x3Float3_2), d4));
        float[] fArr2 = M2;
        return ColorKt.Color(ColorSpaceKt.mul3x3Float3_0(fArr2, fSignum, fSignum2, fSignum3), ColorSpaceKt.mul3x3Float3_1(fArr2, fSignum, fSignum2, fSignum3), ColorSpaceKt.mul3x3Float3_2(fArr2, fSignum, fSignum2, fSignum3), a10, colorSpace);
    }
}
