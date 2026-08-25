package androidx.compose.ui.graphics;

import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.os.Build;
import androidx.compose.ui.geometry.Offset;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aL\u0010\u000e\u001a\u00060\nj\u0002`\u000b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\bH\u0000ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001aL\u0010\u0013\u001a\u00060\nj\u0002`\u000b2\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\bH\u0000ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a<\u0010\u0016\u001a\u00060\nj\u0002`\u000b2\u0006\u0010\u000f\u001a\u00020\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a.\u0010\u001d\u001a\u00060\nj\u0002`\u000b2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\bH\u0000ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001d\u0010\u001f\u001a\u00020\u001e2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0001¢\u0006\u0004\b\u001f\u0010 \u001a%\u0010#\u001a\u00020\"2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010!\u001a\u00020\u001eH\u0001¢\u0006\u0004\b#\u0010$\u001a7\u0010'\u001a\u0004\u0018\u00010&2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010!\u001a\u00020\u001eH\u0001¢\u0006\u0004\b'\u0010(\u001a-\u0010*\u001a\u00020)2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003H\u0002¢\u0006\u0004\b*\u0010+*\n\u0010,\"\u00020\n2\u00020\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006-"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "from", "to", "", "Landroidx/compose/ui/graphics/Color;", "colors", "", "colorStops", "Landroidx/compose/ui/graphics/TileMode;", "tileMode", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "ActualLinearGradientShader-VjE6UOU", "(JJLjava/util/List;Ljava/util/List;I)Landroid/graphics/Shader;", "ActualLinearGradientShader", TtmlNode.CENTER, "radius", "ActualRadialGradientShader-8uybcMk", "(JFLjava/util/List;Ljava/util/List;I)Landroid/graphics/Shader;", "ActualRadialGradientShader", "ActualSweepGradientShader-9KIMszo", "(JLjava/util/List;Ljava/util/List;)Landroid/graphics/Shader;", "ActualSweepGradientShader", "Landroidx/compose/ui/graphics/ImageBitmap;", "image", "tileModeX", "tileModeY", "ActualImageShader-F49vj9s", "(Landroidx/compose/ui/graphics/ImageBitmap;II)Landroid/graphics/Shader;", "ActualImageShader", "", "countTransparentColors", "(Ljava/util/List;)I", "numTransparentColors", "", "makeTransparentColors", "(Ljava/util/List;I)[I", "stops", "", "makeTransparentStops", "(Ljava/util/List;Ljava/util/List;I)[F", "Lx6/t0;", "validateColorStops", "(Ljava/util/List;Ljava/util/List;)V", "Shader", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidShader_androidKt {
    /* JADX INFO: renamed from: ActualImageShader-F49vj9s, reason: not valid java name */
    public static final Shader m3378ActualImageShaderF49vj9s(ImageBitmap imageBitmap, int i10, int i11) {
        return new BitmapShader(AndroidImageBitmap_androidKt.asAndroidBitmap(imageBitmap), AndroidTileMode_androidKt.m3383toAndroidTileMode0vamqd0(i10), AndroidTileMode_androidKt.m3383toAndroidTileMode0vamqd0(i11));
    }

    /* JADX INFO: renamed from: ActualLinearGradientShader-VjE6UOU, reason: not valid java name */
    public static final Shader m3379ActualLinearGradientShaderVjE6UOU(long j10, long j11, List<Color> list, List<Float> list2, int i10) {
        validateColorStops(list, list2);
        int iCountTransparentColors = countTransparentColors(list);
        return new android.graphics.LinearGradient(Offset.m3236getXimpl(j10), Offset.m3237getYimpl(j10), Offset.m3236getXimpl(j11), Offset.m3237getYimpl(j11), makeTransparentColors(list, iCountTransparentColors), makeTransparentStops(list2, list, iCountTransparentColors), AndroidTileMode_androidKt.m3383toAndroidTileMode0vamqd0(i10));
    }

    /* JADX INFO: renamed from: ActualRadialGradientShader-8uybcMk, reason: not valid java name */
    public static final Shader m3380ActualRadialGradientShader8uybcMk(long j10, float f10, List<Color> list, List<Float> list2, int i10) {
        validateColorStops(list, list2);
        int iCountTransparentColors = countTransparentColors(list);
        return new android.graphics.RadialGradient(Offset.m3236getXimpl(j10), Offset.m3237getYimpl(j10), f10, makeTransparentColors(list, iCountTransparentColors), makeTransparentStops(list2, list, iCountTransparentColors), AndroidTileMode_androidKt.m3383toAndroidTileMode0vamqd0(i10));
    }

    /* JADX INFO: renamed from: ActualSweepGradientShader-9KIMszo, reason: not valid java name */
    public static final Shader m3381ActualSweepGradientShader9KIMszo(long j10, List<Color> list, List<Float> list2) {
        validateColorStops(list, list2);
        int iCountTransparentColors = countTransparentColors(list);
        return new android.graphics.SweepGradient(Offset.m3236getXimpl(j10), Offset.m3237getYimpl(j10), makeTransparentColors(list, iCountTransparentColors), makeTransparentStops(list2, list, iCountTransparentColors));
    }

    public static final int countTransparentColors(List<Color> list) {
        int i10 = 0;
        if (Build.VERSION.SDK_INT >= 26) {
            return 0;
        }
        int iW = t7.a.w(list);
        for (int i11 = 1; i11 < iW; i11++) {
            if (Color.m3474getAlphaimpl(list.get(i11).m3482unboximpl()) == 0.0f) {
                i10++;
            }
        }
        return i10;
    }

    public static final int[] makeTransparentColors(List<Color> list, int i10) {
        int i11;
        int i12 = 0;
        if (Build.VERSION.SDK_INT >= 26) {
            int size = list.size();
            int[] iArr = new int[size];
            while (i12 < size) {
                iArr[i12] = ColorKt.m3526toArgb8_81llA(list.get(i12).m3482unboximpl());
                i12++;
            }
            return iArr;
        }
        int[] iArr2 = new int[list.size() + i10];
        int iW = t7.a.w(list);
        int size2 = list.size();
        int i13 = 0;
        while (i12 < size2) {
            long jM3482unboximpl = list.get(i12).m3482unboximpl();
            if (Color.m3474getAlphaimpl(jM3482unboximpl) == 0.0f) {
                if (i12 == 0) {
                    i11 = i13 + 1;
                    iArr2[i13] = ColorKt.m3526toArgb8_81llA(Color.m3471copywmQWz5c$default(list.get(1).m3482unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                } else if (i12 == iW) {
                    i11 = i13 + 1;
                    iArr2[i13] = ColorKt.m3526toArgb8_81llA(Color.m3471copywmQWz5c$default(list.get(i12 - 1).m3482unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                } else {
                    int i14 = i13 + 1;
                    iArr2[i13] = ColorKt.m3526toArgb8_81llA(Color.m3471copywmQWz5c$default(list.get(i12 - 1).m3482unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                    i13 += 2;
                    iArr2[i14] = ColorKt.m3526toArgb8_81llA(Color.m3471copywmQWz5c$default(list.get(i12 + 1).m3482unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                }
                i13 = i11;
            } else {
                iArr2[i13] = ColorKt.m3526toArgb8_81llA(jM3482unboximpl);
                i13++;
            }
            i12++;
        }
        return iArr2;
    }

    public static final float[] makeTransparentStops(List<Float> list, List<Color> list2, int i10) {
        int i11 = 0;
        if (i10 == 0) {
            if (list == null) {
                return null;
            }
            float[] fArr = new float[list.size()];
            Iterator<Float> it = list.iterator();
            while (it.hasNext()) {
                fArr[i11] = it.next().floatValue();
                i11++;
            }
            return fArr;
        }
        float[] fArr2 = new float[list2.size() + i10];
        fArr2[0] = list != null ? list.get(0).floatValue() : 0.0f;
        int iW = t7.a.w(list2);
        int i12 = 1;
        for (int i13 = 1; i13 < iW; i13++) {
            long jM3482unboximpl = list2.get(i13).m3482unboximpl();
            float fFloatValue = list != null ? list.get(i13).floatValue() : i13 / t7.a.w(list2);
            int i14 = i12 + 1;
            fArr2[i12] = fFloatValue;
            if (Color.m3474getAlphaimpl(jM3482unboximpl) == 0.0f) {
                i12 += 2;
                fArr2[i14] = fFloatValue;
            } else {
                i12 = i14;
            }
        }
        fArr2[i12] = list != null ? list.get(t7.a.w(list2)).floatValue() : 1.0f;
        return fArr2;
    }

    private static final void validateColorStops(List<Color> list, List<Float> list2) {
        if (list2 == null) {
            if (list.size() < 2) {
                throw new IllegalArgumentException("colors must have length of at least 2 if colorStops is omitted.");
            }
        } else if (list.size() != list2.size()) {
            throw new IllegalArgumentException("colors and colorStops arguments must have equal length.");
        }
    }
}
