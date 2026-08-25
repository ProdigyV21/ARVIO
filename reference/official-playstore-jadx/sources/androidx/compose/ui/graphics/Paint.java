package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\f\u0010B\u001a\u00060Cj\u0002`DH&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\tX¦\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u00020\u000fX¦\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u0015X¦\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\u00020\u001bX¦\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b\u001c\u0010\u000b\"\u0004\b\u001d\u0010\rR\u0018\u0010\u001e\u001a\u00020\u001fX¦\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u0004\u0018\u00010$X¦\u000e¢\u0006\f\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R \u0010)\u001a\n\u0018\u00010*j\u0004\u0018\u0001`+X¦\u000e¢\u0006\f\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001e\u00100\u001a\u000201X¦\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b2\u0010\u000b\"\u0004\b3\u0010\rR\u001e\u00104\u001a\u000205X¦\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b6\u0010\u000b\"\u0004\b7\u0010\rR\u0018\u00108\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b9\u0010\u0005\"\u0004\b:\u0010\u0007R\u0018\u0010;\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b<\u0010\u0005\"\u0004\b=\u0010\u0007R\u001e\u0010>\u001a\u00020?X¦\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b@\u0010\u000b\"\u0004\bA\u0010\rø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006EÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/graphics/Paint;", "", "alpha", "", "getAlpha", "()F", "setAlpha", "(F)V", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Color;", "getColor-0d7_KjU", "()J", "setColor-8_81llA", "(J)V", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "getFilterQuality-f-v9h1I", "setFilterQuality-vDHp3xo", "isAntiAlias", "", "()Z", "setAntiAlias", "(Z)V", "pathEffect", "Landroidx/compose/ui/graphics/PathEffect;", "getPathEffect", "()Landroidx/compose/ui/graphics/PathEffect;", "setPathEffect", "(Landroidx/compose/ui/graphics/PathEffect;)V", "shader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "getShader", "()Landroid/graphics/Shader;", "setShader", "(Landroid/graphics/Shader;)V", "strokeCap", "Landroidx/compose/ui/graphics/StrokeCap;", "getStrokeCap-KaPHkGw", "setStrokeCap-BeK7IIE", "strokeJoin", "Landroidx/compose/ui/graphics/StrokeJoin;", "getStrokeJoin-LxFBmk8", "setStrokeJoin-Ww9F2mQ", "strokeMiterLimit", "getStrokeMiterLimit", "setStrokeMiterLimit", "strokeWidth", "getStrokeWidth", "setStrokeWidth", "style", "Landroidx/compose/ui/graphics/PaintingStyle;", "getStyle-TiuSbCo", "setStyle-k9PVt8s", "asFrameworkPaint", "Landroid/graphics/Paint;", "Landroidx/compose/ui/graphics/NativePaint;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Paint {
    android.graphics.Paint asFrameworkPaint();

    float getAlpha();

    /* JADX INFO: renamed from: getBlendMode-0nO6VwU */
    int mo3350getBlendMode0nO6VwU();

    /* JADX INFO: renamed from: getColor-0d7_KjU */
    long mo3351getColor0d7_KjU();

    ColorFilter getColorFilter();

    /* JADX INFO: renamed from: getFilterQuality-f-v9h1I */
    int mo3352getFilterQualityfv9h1I();

    PathEffect getPathEffect();

    Shader getShader();

    /* JADX INFO: renamed from: getStrokeCap-KaPHkGw */
    int mo3353getStrokeCapKaPHkGw();

    /* JADX INFO: renamed from: getStrokeJoin-LxFBmk8 */
    int mo3354getStrokeJoinLxFBmk8();

    float getStrokeMiterLimit();

    float getStrokeWidth();

    /* JADX INFO: renamed from: getStyle-TiuSbCo */
    int mo3355getStyleTiuSbCo();

    boolean isAntiAlias();

    void setAlpha(float f10);

    void setAntiAlias(boolean z);

    /* JADX INFO: renamed from: setBlendMode-s9anfk8 */
    void mo3356setBlendModes9anfk8(int i10);

    /* JADX INFO: renamed from: setColor-8_81llA */
    void mo3357setColor8_81llA(long j10);

    void setColorFilter(ColorFilter colorFilter);

    /* JADX INFO: renamed from: setFilterQuality-vDHp3xo */
    void mo3358setFilterQualityvDHp3xo(int i10);

    void setPathEffect(PathEffect pathEffect);

    void setShader(Shader shader);

    /* JADX INFO: renamed from: setStrokeCap-BeK7IIE */
    void mo3359setStrokeCapBeK7IIE(int i10);

    /* JADX INFO: renamed from: setStrokeJoin-Ww9F2mQ */
    void mo3360setStrokeJoinWw9F2mQ(int i10);

    void setStrokeMiterLimit(float f10);

    void setStrokeWidth(float f10);

    /* JADX INFO: renamed from: setStyle-k9PVt8s */
    void mo3361setStylek9PVt8s(int i10);
}
