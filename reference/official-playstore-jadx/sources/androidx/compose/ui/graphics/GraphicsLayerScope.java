package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R*\u0010\b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8V@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0005\"\u0004\b\u0010\u0010\u0007R \u0010\u0011\u001a\u00020\u00128fX¦\u000e¢\u0006\u0012\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R*\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8V@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR(\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\u001f\u001a\u0004\u0018\u00010 8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0018\u0010&\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b'\u0010\u0005\"\u0004\b(\u0010\u0007R\u0018\u0010)\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b*\u0010\u0005\"\u0004\b+\u0010\u0007R\u0018\u0010,\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b-\u0010\u0005\"\u0004\b.\u0010\u0007R\u0018\u0010/\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b0\u0010\u0005\"\u0004\b1\u0010\u0007R\u0018\u00102\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b3\u0010\u0005\"\u0004\b4\u0010\u0007R\u0018\u00105\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b6\u0010\u0005\"\u0004\b7\u0010\u0007R\u0018\u00108\u001a\u000209X¦\u000e¢\u0006\f\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010>\u001a\u00020?8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b@\u0010\u000bR*\u0010A\u001a\u00020\t2\u0006\u0010A\u001a\u00020\t8V@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\bB\u0010\u000b\"\u0004\bC\u0010\rR\u001e\u0010D\u001a\u00020EX¦\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\u001a\u0004\bF\u0010\u000b\"\u0004\bG\u0010\rR\u0018\u0010H\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\bI\u0010\u0005\"\u0004\bJ\u0010\u0007R\u0018\u0010K\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\bL\u0010\u0005\"\u0004\bM\u0010\u0007ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006NÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Landroidx/compose/ui/unit/Density;", "alpha", "", "getAlpha", "()F", "setAlpha", "(F)V", "ambientShadowColor", "Landroidx/compose/ui/graphics/Color;", "getAmbientShadowColor-0d7_KjU", "()J", "setAmbientShadowColor-8_81llA", "(J)V", "cameraDistance", "getCameraDistance", "setCameraDistance", "clip", "", "getClip$annotations", "()V", "getClip", "()Z", "setClip", "(Z)V", "compositingStrategy", "Landroidx/compose/ui/graphics/CompositingStrategy;", "getCompositingStrategy--NrFUSI", "()I", "setCompositingStrategy-aDBOjCE", "(I)V", "<anonymous parameter 0>", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "scaleX", "getScaleX", "setScaleX", "scaleY", "getScaleY", "setScaleY", "shadowElevation", "getShadowElevation", "setShadowElevation", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/graphics/Shape;)V", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "transformOrigin", "Landroidx/compose/ui/graphics/TransformOrigin;", "getTransformOrigin-SzJe1aQ", "setTransformOrigin-__ExYCQ", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface GraphicsLayerScope extends Density {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: getAmbientShadowColor-0d7_KjU, reason: not valid java name */
        public static long m3647getAmbientShadowColor0d7_KjU(GraphicsLayerScope graphicsLayerScope) {
            return f.a(graphicsLayerScope);
        }

        public static /* synthetic */ void getClip$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: getCompositingStrategy--NrFUSI, reason: not valid java name */
        public static int m3648getCompositingStrategyNrFUSI(GraphicsLayerScope graphicsLayerScope) {
            return f.b(graphicsLayerScope);
        }

        @Deprecated
        public static RenderEffect getRenderEffect(GraphicsLayerScope graphicsLayerScope) {
            return f.c(graphicsLayerScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public static long m3649getSizeNHjbRc(GraphicsLayerScope graphicsLayerScope) {
            return f.d(graphicsLayerScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: getSpotShadowColor-0d7_KjU, reason: not valid java name */
        public static long m3650getSpotShadowColor0d7_KjU(GraphicsLayerScope graphicsLayerScope) {
            return f.e(graphicsLayerScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m3651roundToPxR2X_6o(GraphicsLayerScope graphicsLayerScope, long j10) {
            return androidx.compose.ui.unit.a.a(graphicsLayerScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m3652roundToPx0680j_4(GraphicsLayerScope graphicsLayerScope, float f10) {
            return androidx.compose.ui.unit.a.b(graphicsLayerScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: setAmbientShadowColor-8_81llA, reason: not valid java name */
        public static void m3653setAmbientShadowColor8_81llA(GraphicsLayerScope graphicsLayerScope, long j10) {
            f.f(graphicsLayerScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: setCompositingStrategy-aDBOjCE, reason: not valid java name */
        public static void m3654setCompositingStrategyaDBOjCE(GraphicsLayerScope graphicsLayerScope, int i10) {
            f.g(graphicsLayerScope, i10);
        }

        @Deprecated
        public static void setRenderEffect(GraphicsLayerScope graphicsLayerScope, RenderEffect renderEffect) {
            f.h(graphicsLayerScope, renderEffect);
        }

        @Deprecated
        /* JADX INFO: renamed from: setSpotShadowColor-8_81llA, reason: not valid java name */
        public static void m3655setSpotShadowColor8_81llA(GraphicsLayerScope graphicsLayerScope, long j10) {
            f.i(graphicsLayerScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m3656toDpGaN1DYA(GraphicsLayerScope graphicsLayerScope, long j10) {
            return androidx.compose.ui.unit.b.a(graphicsLayerScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m3657toDpu2uoSUM(GraphicsLayerScope graphicsLayerScope, float f10) {
            return androidx.compose.ui.unit.a.c(graphicsLayerScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m3659toDpSizekrfVVM(GraphicsLayerScope graphicsLayerScope, long j10) {
            return androidx.compose.ui.unit.a.e(graphicsLayerScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m3660toPxR2X_6o(GraphicsLayerScope graphicsLayerScope, long j10) {
            return androidx.compose.ui.unit.a.f(graphicsLayerScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m3661toPx0680j_4(GraphicsLayerScope graphicsLayerScope, float f10) {
            return androidx.compose.ui.unit.a.g(graphicsLayerScope, f10);
        }

        @Deprecated
        public static Rect toRect(GraphicsLayerScope graphicsLayerScope, DpRect dpRect) {
            return androidx.compose.ui.unit.a.h(graphicsLayerScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m3662toSizeXkaWNTQ(GraphicsLayerScope graphicsLayerScope, long j10) {
            return androidx.compose.ui.unit.a.i(graphicsLayerScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m3663toSp0xMU5do(GraphicsLayerScope graphicsLayerScope, float f10) {
            return androidx.compose.ui.unit.b.b(graphicsLayerScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m3664toSpkPz2Gy4(GraphicsLayerScope graphicsLayerScope, float f10) {
            return androidx.compose.ui.unit.a.j(graphicsLayerScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m3658toDpu2uoSUM(GraphicsLayerScope graphicsLayerScope, int i10) {
            return androidx.compose.ui.unit.a.d(graphicsLayerScope, i10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m3665toSpkPz2Gy4(GraphicsLayerScope graphicsLayerScope, int i10) {
            return androidx.compose.ui.unit.a.k(graphicsLayerScope, i10);
        }
    }

    float getAlpha();

    /* JADX INFO: renamed from: getAmbientShadowColor-0d7_KjU, reason: not valid java name */
    long mo3638getAmbientShadowColor0d7_KjU();

    float getCameraDistance();

    boolean getClip();

    /* JADX INFO: renamed from: getCompositingStrategy--NrFUSI, reason: not valid java name */
    int mo3639getCompositingStrategyNrFUSI();

    RenderEffect getRenderEffect();

    float getRotationX();

    float getRotationY();

    float getRotationZ();

    float getScaleX();

    float getScaleY();

    float getShadowElevation();

    Shape getShape();

    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
    long mo3640getSizeNHjbRc();

    /* JADX INFO: renamed from: getSpotShadowColor-0d7_KjU, reason: not valid java name */
    long mo3641getSpotShadowColor0d7_KjU();

    /* JADX INFO: renamed from: getTransformOrigin-SzJe1aQ, reason: not valid java name */
    long mo3642getTransformOriginSzJe1aQ();

    float getTranslationX();

    float getTranslationY();

    void setAlpha(float f10);

    /* JADX INFO: renamed from: setAmbientShadowColor-8_81llA, reason: not valid java name */
    void mo3643setAmbientShadowColor8_81llA(long j10);

    void setCameraDistance(float f10);

    void setClip(boolean z);

    /* JADX INFO: renamed from: setCompositingStrategy-aDBOjCE, reason: not valid java name */
    void mo3644setCompositingStrategyaDBOjCE(int i10);

    void setRenderEffect(RenderEffect renderEffect);

    void setRotationX(float f10);

    void setRotationY(float f10);

    void setRotationZ(float f10);

    void setScaleX(float f10);

    void setScaleY(float f10);

    void setShadowElevation(float f10);

    void setShape(Shape shape);

    /* JADX INFO: renamed from: setSpotShadowColor-8_81llA, reason: not valid java name */
    void mo3645setSpotShadowColor8_81llA(long j10);

    /* JADX INFO: renamed from: setTransformOrigin-__ExYCQ, reason: not valid java name */
    void mo3646setTransformOrigin__ExYCQ(long j10);

    void setTranslationX(float f10);

    void setTranslationY(float f10);
}
