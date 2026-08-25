package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.DpRect;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0003R\"\u0010\u0007\u001a\u00020\u00068\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR*\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R*\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R*\u0010\u0018\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R*\u0010\u001b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u0014R*\u0010\u001e\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0010\u001a\u0004\b\u001f\u0010\u0012\"\u0004\b \u0010\u0014R*\u0010!\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b!\u0010\u0010\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014R0\u0010%\u001a\u00020$2\u0006\u0010\u000e\u001a\u00020$8\u0016@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R0\u0010+\u001a\u00020$2\u0006\u0010\u000e\u001a\u00020$8\u0016@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b+\u0010&\u001a\u0004\b,\u0010(\"\u0004\b-\u0010*R*\u0010.\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b.\u0010\u0010\u001a\u0004\b/\u0010\u0012\"\u0004\b0\u0010\u0014R*\u00101\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b1\u0010\u0010\u001a\u0004\b2\u0010\u0012\"\u0004\b3\u0010\u0014R*\u00104\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b4\u0010\u0010\u001a\u0004\b5\u0010\u0012\"\u0004\b6\u0010\u0014R*\u00107\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b7\u0010\u0010\u001a\u0004\b8\u0010\u0012\"\u0004\b9\u0010\u0014R0\u0010;\u001a\u00020:2\u0006\u0010\u000e\u001a\u00020:8\u0016@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b;\u0010&\u001a\u0004\b<\u0010(\"\u0004\b=\u0010*R*\u0010?\u001a\u00020>2\u0006\u0010\u000e\u001a\u00020>8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR*\u0010F\u001a\u00020E2\u0006\u0010\u000e\u001a\u00020E8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR0\u0010M\u001a\u00020L2\u0006\u0010\u000e\u001a\u00020L8\u0016@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bM\u0010\b\u001a\u0004\bN\u0010\n\"\u0004\bO\u0010\fR(\u0010Q\u001a\u00020P8\u0016@\u0016X\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bQ\u0010&\u001a\u0004\bR\u0010(\"\u0004\bS\u0010*R\"\u0010U\u001a\u00020T8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bU\u0010V\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR.\u0010\\\u001a\u0004\u0018\u00010[2\b\u0010\u000e\u001a\u0004\u0018\u00010[8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b\\\u0010]\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u0014\u0010c\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bb\u0010\u0012R\u0014\u0010e\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bd\u0010\u0012\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006f"}, d2 = {"Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "<init>", "()V", "Lx6/t0;", "reset", "", "mutatedFields", "I", "getMutatedFields$ui_release", "()I", "setMutatedFields$ui_release", "(I)V", "", "value", "scaleX", "F", "getScaleX", "()F", "setScaleX", "(F)V", "scaleY", "getScaleY", "setScaleY", "alpha", "getAlpha", "setAlpha", "translationX", "getTranslationX", "setTranslationX", "translationY", "getTranslationY", "setTranslationY", "shadowElevation", "getShadowElevation", "setShadowElevation", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "J", "getAmbientShadowColor-0d7_KjU", "()J", "setAmbientShadowColor-8_81llA", "(J)V", "spotShadowColor", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "rotationX", "getRotationX", "setRotationX", "rotationY", "getRotationY", "setRotationY", "rotationZ", "getRotationZ", "setRotationZ", "cameraDistance", "getCameraDistance", "setCameraDistance", "Landroidx/compose/ui/graphics/TransformOrigin;", "transformOrigin", "getTransformOrigin-SzJe1aQ", "setTransformOrigin-__ExYCQ", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/ui/graphics/Shape;", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/graphics/Shape;)V", "", "clip", "Z", "getClip", "()Z", "setClip", "(Z)V", "Landroidx/compose/ui/graphics/CompositingStrategy;", "compositingStrategy", "getCompositingStrategy--NrFUSI", "setCompositingStrategy-aDBOjCE", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "getSize-NH-jbRc", "setSize-uvyYCjk", "Landroidx/compose/ui/unit/Density;", "graphicsDensity", "Landroidx/compose/ui/unit/Density;", "getGraphicsDensity$ui_release", "()Landroidx/compose/ui/unit/Density;", "setGraphicsDensity$ui_release", "(Landroidx/compose/ui/unit/Density;)V", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "Landroidx/compose/ui/graphics/RenderEffect;", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "getDensity", "density", "getFontScale", "fontScale", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ReusableGraphicsLayerScope implements GraphicsLayerScope {
    public static final int $stable = 0;
    private boolean clip;
    private int mutatedFields;
    private RenderEffect renderEffect;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float shadowElevation;
    private float translationX;
    private float translationY;
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float alpha = 1.0f;
    private long ambientShadowColor = GraphicsLayerScopeKt.getDefaultShadowColor();
    private long spotShadowColor = GraphicsLayerScopeKt.getDefaultShadowColor();
    private float cameraDistance = 8.0f;
    private long transformOrigin = TransformOrigin.INSTANCE.m3841getCenterSzJe1aQ();
    private Shape shape = RectangleShapeKt.getRectangleShape();
    private int compositingStrategy = CompositingStrategy.INSTANCE.m3561getAutoNrFUSI();
    private long size = Size.INSTANCE.m3313getUnspecifiedNHjbRc();
    private Density graphicsDensity = DensityKt.Density$default(1.0f, 0.0f, 2, null);

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getAlpha() {
        return this.alpha;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* JADX INFO: renamed from: getAmbientShadowColor-0d7_KjU, reason: from getter */
    public long getAmbientShadowColor() {
        return this.ambientShadowColor;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getCameraDistance() {
        return this.cameraDistance;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public boolean getClip() {
        return this.clip;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* JADX INFO: renamed from: getCompositingStrategy--NrFUSI, reason: from getter */
    public int getCompositingStrategy() {
        return this.compositingStrategy;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.graphicsDensity.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.graphicsDensity.getFontScale();
    }

    /* JADX INFO: renamed from: getGraphicsDensity$ui_release, reason: from getter */
    public final Density getGraphicsDensity() {
        return this.graphicsDensity;
    }

    /* JADX INFO: renamed from: getMutatedFields$ui_release, reason: from getter */
    public final int getMutatedFields() {
        return this.mutatedFields;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public RenderEffect getRenderEffect() {
        return this.renderEffect;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getRotationX() {
        return this.rotationX;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getRotationY() {
        return this.rotationY;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getRotationZ() {
        return this.rotationZ;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getScaleX() {
        return this.scaleX;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getScaleY() {
        return this.scaleY;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getShadowElevation() {
        return this.shadowElevation;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public Shape getShape() {
        return this.shape;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: from getter */
    public long getSize() {
        return this.size;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* JADX INFO: renamed from: getSpotShadowColor-0d7_KjU, reason: from getter */
    public long getSpotShadowColor() {
        return this.spotShadowColor;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* JADX INFO: renamed from: getTransformOrigin-SzJe1aQ, reason: from getter */
    public long getTransformOrigin() {
        return this.transformOrigin;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getTranslationX() {
        return this.translationX;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public float getTranslationY() {
        return this.translationY;
    }

    public final void reset() {
        setScaleX(1.0f);
        setScaleY(1.0f);
        setAlpha(1.0f);
        setTranslationX(0.0f);
        setTranslationY(0.0f);
        setShadowElevation(0.0f);
        mo3643setAmbientShadowColor8_81llA(GraphicsLayerScopeKt.getDefaultShadowColor());
        mo3645setSpotShadowColor8_81llA(GraphicsLayerScopeKt.getDefaultShadowColor());
        setRotationX(0.0f);
        setRotationY(0.0f);
        setRotationZ(0.0f);
        setCameraDistance(8.0f);
        mo3646setTransformOrigin__ExYCQ(TransformOrigin.INSTANCE.m3841getCenterSzJe1aQ());
        setShape(RectangleShapeKt.getRectangleShape());
        setClip(false);
        setRenderEffect(null);
        mo3644setCompositingStrategyaDBOjCE(CompositingStrategy.INSTANCE.m3561getAutoNrFUSI());
        m3762setSizeuvyYCjk(Size.INSTANCE.m3313getUnspecifiedNHjbRc());
        this.mutatedFields = 0;
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    public final /* synthetic */ int mo278roundToPxR2X_6o(long j10) {
        return androidx.compose.ui.unit.a.a(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public final /* synthetic */ int mo279roundToPx0680j_4(float f10) {
        return androidx.compose.ui.unit.a.b(this, f10);
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setAlpha(float f10) {
        if (this.alpha == f10) {
            return;
        }
        this.mutatedFields |= 4;
        this.alpha = f10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* JADX INFO: renamed from: setAmbientShadowColor-8_81llA */
    public void mo3643setAmbientShadowColor8_81llA(long j10) {
        if (Color.m3473equalsimpl0(this.ambientShadowColor, j10)) {
            return;
        }
        this.mutatedFields |= 64;
        this.ambientShadowColor = j10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setCameraDistance(float f10) {
        if (this.cameraDistance == f10) {
            return;
        }
        this.mutatedFields |= 2048;
        this.cameraDistance = f10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setClip(boolean z) {
        if (this.clip != z) {
            this.mutatedFields |= 16384;
            this.clip = z;
        }
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* JADX INFO: renamed from: setCompositingStrategy-aDBOjCE */
    public void mo3644setCompositingStrategyaDBOjCE(int i10) {
        if (CompositingStrategy.m3557equalsimpl0(this.compositingStrategy, i10)) {
            return;
        }
        this.mutatedFields |= 32768;
        this.compositingStrategy = i10;
    }

    public final void setGraphicsDensity$ui_release(Density density) {
        this.graphicsDensity = density;
    }

    public final void setMutatedFields$ui_release(int i10) {
        this.mutatedFields = i10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setRenderEffect(RenderEffect renderEffect) {
        if (p.a(this.renderEffect, renderEffect)) {
            return;
        }
        this.mutatedFields |= 131072;
        this.renderEffect = renderEffect;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setRotationX(float f10) {
        if (this.rotationX == f10) {
            return;
        }
        this.mutatedFields |= 256;
        this.rotationX = f10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setRotationY(float f10) {
        if (this.rotationY == f10) {
            return;
        }
        this.mutatedFields |= 512;
        this.rotationY = f10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setRotationZ(float f10) {
        if (this.rotationZ == f10) {
            return;
        }
        this.mutatedFields |= 1024;
        this.rotationZ = f10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setScaleX(float f10) {
        if (this.scaleX == f10) {
            return;
        }
        this.mutatedFields |= 1;
        this.scaleX = f10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setScaleY(float f10) {
        if (this.scaleY == f10) {
            return;
        }
        this.mutatedFields |= 2;
        this.scaleY = f10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setShadowElevation(float f10) {
        if (this.shadowElevation == f10) {
            return;
        }
        this.mutatedFields |= 32;
        this.shadowElevation = f10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setShape(Shape shape) {
        if (p.a(this.shape, shape)) {
            return;
        }
        this.mutatedFields |= 8192;
        this.shape = shape;
    }

    /* JADX INFO: renamed from: setSize-uvyYCjk, reason: not valid java name */
    public void m3762setSizeuvyYCjk(long j10) {
        this.size = j10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* JADX INFO: renamed from: setSpotShadowColor-8_81llA */
    public void mo3645setSpotShadowColor8_81llA(long j10) {
        if (Color.m3473equalsimpl0(this.spotShadowColor, j10)) {
            return;
        }
        this.mutatedFields |= 128;
        this.spotShadowColor = j10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    /* JADX INFO: renamed from: setTransformOrigin-__ExYCQ */
    public void mo3646setTransformOrigin__ExYCQ(long j10) {
        if (TransformOrigin.m3835equalsimpl0(this.transformOrigin, j10)) {
            return;
        }
        this.mutatedFields |= 4096;
        this.transformOrigin = j10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setTranslationX(float f10) {
        if (this.translationX == f10) {
            return;
        }
        this.mutatedFields |= 8;
        this.translationX = f10;
    }

    @Override // androidx.compose.ui.graphics.GraphicsLayerScope
    public void setTranslationY(float f10) {
        if (this.translationY == f10) {
            return;
        }
        this.mutatedFields |= 16;
        this.translationY = f10;
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public final /* synthetic */ float mo280toDpGaN1DYA(long j10) {
        return androidx.compose.ui.unit.b.a(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public final /* synthetic */ float mo281toDpu2uoSUM(float f10) {
        return androidx.compose.ui.unit.a.c(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public final /* synthetic */ long mo283toDpSizekrfVVM(long j10) {
        return androidx.compose.ui.unit.a.e(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public final /* synthetic */ float mo284toPxR2X_6o(long j10) {
        return androidx.compose.ui.unit.a.f(this, j10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public final /* synthetic */ float mo285toPx0680j_4(float f10) {
        return androidx.compose.ui.unit.a.g(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    public final /* synthetic */ Rect toRect(DpRect dpRect) {
        return androidx.compose.ui.unit.a.h(this, dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public final /* synthetic */ long mo286toSizeXkaWNTQ(long j10) {
        return androidx.compose.ui.unit.a.i(this, j10);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public final /* synthetic */ long mo287toSp0xMU5do(float f10) {
        return androidx.compose.ui.unit.b.b(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public final /* synthetic */ long mo288toSpkPz2Gy4(float f10) {
        return androidx.compose.ui.unit.a.j(this, f10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public final /* synthetic */ float mo282toDpu2uoSUM(int i10) {
        return androidx.compose.ui.unit.a.d(this, i10);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public final /* synthetic */ long mo289toSpkPz2Gy4(int i10) {
        return androidx.compose.ui.unit.a.k(this, i10);
    }
}
