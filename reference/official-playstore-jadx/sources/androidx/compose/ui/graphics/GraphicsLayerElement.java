package androidx.compose.ui.graphics;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u001b\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0091\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b!\u0010\"J\u0013\u0010$\u001a\u00020 *\u00020#H\u0016¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b(\u0010'J\u0010\u0010)\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b)\u0010'J\u0010\u0010*\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b*\u0010'J\u0010\u0010+\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b+\u0010'J\u0010\u0010,\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b,\u0010'J\u0010\u0010-\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b-\u0010'J\u0010\u0010.\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b.\u0010'J\u0010\u0010/\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b/\u0010'J\u0010\u00100\u001a\u00020\u0003HÆ\u0003¢\u0006\u0004\b0\u0010'J\u0016\u00103\u001a\u00020\u000eHÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b1\u00102J\u0010\u00104\u001a\u00020\u0010HÆ\u0003¢\u0006\u0004\b4\u00105J\u0010\u00106\u001a\u00020\u0012HÆ\u0003¢\u0006\u0004\b6\u00107J\u0012\u00108\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0004\b8\u00109J\u0016\u0010;\u001a\u00020\u0016HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b:\u00102J\u0016\u0010=\u001a\u00020\u0016HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b<\u00102J\u0016\u0010@\u001a\u00020\u0019HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b>\u0010?J¿\u0001\u0010C\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0013\u001a\u00020\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u0019HÆ\u0001ø\u0001\u0001¢\u0006\u0004\bA\u0010BJ\u0010\u0010E\u001a\u00020DHÖ\u0001¢\u0006\u0004\bE\u0010FJ\u0010\u0010H\u001a\u00020GHÖ\u0001¢\u0006\u0004\bH\u0010?J\u001a\u0010K\u001a\u00020\u00122\b\u0010J\u001a\u0004\u0018\u00010IHÖ\u0003¢\u0006\u0004\bK\u0010LR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010M\u001a\u0004\bN\u0010'R\u0017\u0010\u0005\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010M\u001a\u0004\bO\u0010'R\u0017\u0010\u0006\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0006\u0010M\u001a\u0004\bP\u0010'R\u0017\u0010\u0007\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0007\u0010M\u001a\u0004\bQ\u0010'R\u0017\u0010\b\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\b\u0010M\u001a\u0004\bR\u0010'R\u0017\u0010\t\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\t\u0010M\u001a\u0004\bS\u0010'R\u0017\u0010\n\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\n\u0010M\u001a\u0004\bT\u0010'R\u0017\u0010\u000b\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u000b\u0010M\u001a\u0004\bU\u0010'R\u0017\u0010\f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010M\u001a\u0004\bV\u0010'R\u0017\u0010\r\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\r\u0010M\u001a\u0004\bW\u0010'R\u001d\u0010\u000f\u001a\u00020\u000e8\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u000f\u0010X\u001a\u0004\bY\u00102R\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010Z\u001a\u0004\b[\u00105R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\\\u001a\u0004\b]\u00107R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010^\u001a\u0004\b_\u00109R\u001d\u0010\u0017\u001a\u00020\u00168\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0017\u0010X\u001a\u0004\b`\u00102R\u001d\u0010\u0018\u001a\u00020\u00168\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0018\u0010X\u001a\u0004\ba\u00102R\u001d\u0010\u001a\u001a\u00020\u00198\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u001a\u0010b\u001a\u0004\bc\u0010?\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006d"}, d2 = {"Landroidx/compose/ui/graphics/GraphicsLayerElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/graphics/SimpleGraphicsLayerModifier;", "", "scaleX", "scaleY", "alpha", "translationX", "translationY", "shadowElevation", "rotationX", "rotationY", "rotationZ", "cameraDistance", "Landroidx/compose/ui/graphics/TransformOrigin;", "transformOrigin", "Landroidx/compose/ui/graphics/Shape;", "shape", "", "clip", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "spotShadowColor", "Landroidx/compose/ui/graphics/CompositingStrategy;", "compositingStrategy", "<init>", "(FFFFFFFFFFJLandroidx/compose/ui/graphics/Shape;ZLandroidx/compose/ui/graphics/RenderEffect;JJILkotlin/jvm/internal/h;)V", "create", "()Landroidx/compose/ui/graphics/SimpleGraphicsLayerModifier;", "node", "Lx6/t0;", "update", "(Landroidx/compose/ui/graphics/SimpleGraphicsLayerModifier;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "component1", "()F", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11-SzJe1aQ", "()J", "component11", "component12", "()Landroidx/compose/ui/graphics/Shape;", "component13", "()Z", "component14", "()Landroidx/compose/ui/graphics/RenderEffect;", "component15-0d7_KjU", "component15", "component16-0d7_KjU", "component16", "component17--NrFUSI", "()I", "component17", "copy-JVvOYNQ", "(FFFFFFFFFFJLandroidx/compose/ui/graphics/Shape;ZLandroidx/compose/ui/graphics/RenderEffect;JJI)Landroidx/compose/ui/graphics/GraphicsLayerElement;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "F", "getScaleX", "getScaleY", "getAlpha", "getTranslationX", "getTranslationY", "getShadowElevation", "getRotationX", "getRotationY", "getRotationZ", "getCameraDistance", "J", "getTransformOrigin-SzJe1aQ", "Landroidx/compose/ui/graphics/Shape;", "getShape", "Z", "getClip", "Landroidx/compose/ui/graphics/RenderEffect;", "getRenderEffect", "getAmbientShadowColor-0d7_KjU", "getSpotShadowColor-0d7_KjU", "I", "getCompositingStrategy--NrFUSI", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class GraphicsLayerElement extends ModifierNodeElement<SimpleGraphicsLayerModifier> {
    private final float alpha;
    private final long ambientShadowColor;
    private final float cameraDistance;
    private final boolean clip;
    private final int compositingStrategy;
    private final RenderEffect renderEffect;
    private final float rotationX;
    private final float rotationY;
    private final float rotationZ;
    private final float scaleX;
    private final float scaleY;
    private final float shadowElevation;
    private final Shape shape;
    private final long spotShadowColor;
    private final long transformOrigin;
    private final float translationX;
    private final float translationY;

    public /* synthetic */ GraphicsLayerElement(float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, long j10, Shape shape, boolean z, RenderEffect renderEffect, long j11, long j12, int i10, kotlin.jvm.internal.h hVar) {
        this(f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, j10, shape, z, renderEffect, j11, j12, i10);
    }

    /* JADX INFO: renamed from: copy-JVvOYNQ$default, reason: not valid java name */
    public static /* synthetic */ GraphicsLayerElement m3620copyJVvOYNQ$default(GraphicsLayerElement graphicsLayerElement, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, long j10, Shape shape, boolean z, RenderEffect renderEffect, long j11, long j12, int i10, int i11, Object obj) {
        int i12;
        long j13;
        float f20 = (i11 & 1) != 0 ? graphicsLayerElement.scaleX : f10;
        float f21 = (i11 & 2) != 0 ? graphicsLayerElement.scaleY : f11;
        float f22 = (i11 & 4) != 0 ? graphicsLayerElement.alpha : f12;
        float f23 = (i11 & 8) != 0 ? graphicsLayerElement.translationX : f13;
        float f24 = (i11 & 16) != 0 ? graphicsLayerElement.translationY : f14;
        float f25 = (i11 & 32) != 0 ? graphicsLayerElement.shadowElevation : f15;
        float f26 = (i11 & 64) != 0 ? graphicsLayerElement.rotationX : f16;
        float f27 = (i11 & 128) != 0 ? graphicsLayerElement.rotationY : f17;
        float f28 = (i11 & 256) != 0 ? graphicsLayerElement.rotationZ : f18;
        float f29 = (i11 & 512) != 0 ? graphicsLayerElement.cameraDistance : f19;
        long j14 = (i11 & 1024) != 0 ? graphicsLayerElement.transformOrigin : j10;
        Shape shape2 = (i11 & 2048) != 0 ? graphicsLayerElement.shape : shape;
        boolean z5 = (i11 & 4096) != 0 ? graphicsLayerElement.clip : z;
        float f30 = f20;
        RenderEffect renderEffect2 = (i11 & 8192) != 0 ? graphicsLayerElement.renderEffect : renderEffect;
        long j15 = (i11 & 16384) != 0 ? graphicsLayerElement.ambientShadowColor : j11;
        long j16 = (i11 & 32768) != 0 ? graphicsLayerElement.spotShadowColor : j12;
        if ((i11 & 65536) != 0) {
            j13 = j16;
            i12 = graphicsLayerElement.compositingStrategy;
        } else {
            i12 = i10;
            j13 = j16;
        }
        return graphicsLayerElement.m3625copyJVvOYNQ(f30, f21, f22, f23, f24, f25, f26, f27, f28, f29, j14, shape2, z5, renderEffect2, j15, j13, i12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getScaleX() {
        return this.scaleX;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final float getCameraDistance() {
        return this.cameraDistance;
    }

    /* JADX INFO: renamed from: component11-SzJe1aQ, reason: not valid java name and from getter */
    public final long getTransformOrigin() {
        return this.transformOrigin;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Shape getShape() {
        return this.shape;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getClip() {
        return this.clip;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final RenderEffect getRenderEffect() {
        return this.renderEffect;
    }

    /* JADX INFO: renamed from: component15-0d7_KjU, reason: not valid java name and from getter */
    public final long getAmbientShadowColor() {
        return this.ambientShadowColor;
    }

    /* JADX INFO: renamed from: component16-0d7_KjU, reason: not valid java name and from getter */
    public final long getSpotShadowColor() {
        return this.spotShadowColor;
    }

    /* JADX INFO: renamed from: component17--NrFUSI, reason: not valid java name and from getter */
    public final int getCompositingStrategy() {
        return this.compositingStrategy;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getScaleY() {
        return this.scaleY;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getAlpha() {
        return this.alpha;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getTranslationX() {
        return this.translationX;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getTranslationY() {
        return this.translationY;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final float getShadowElevation() {
        return this.shadowElevation;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getRotationX() {
        return this.rotationX;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final float getRotationY() {
        return this.rotationY;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final float getRotationZ() {
        return this.rotationZ;
    }

    /* JADX INFO: renamed from: copy-JVvOYNQ, reason: not valid java name */
    public final GraphicsLayerElement m3625copyJVvOYNQ(float scaleX, float scaleY, float alpha, float translationX, float translationY, float shadowElevation, float rotationX, float rotationY, float rotationZ, float cameraDistance, long transformOrigin, Shape shape, boolean clip, RenderEffect renderEffect, long ambientShadowColor, long spotShadowColor, int compositingStrategy) {
        return new GraphicsLayerElement(scaleX, scaleY, alpha, translationX, translationY, shadowElevation, rotationX, rotationY, rotationZ, cameraDistance, transformOrigin, shape, clip, renderEffect, ambientShadowColor, spotShadowColor, compositingStrategy, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GraphicsLayerElement)) {
            return false;
        }
        GraphicsLayerElement graphicsLayerElement = (GraphicsLayerElement) other;
        return Float.compare(this.scaleX, graphicsLayerElement.scaleX) == 0 && Float.compare(this.scaleY, graphicsLayerElement.scaleY) == 0 && Float.compare(this.alpha, graphicsLayerElement.alpha) == 0 && Float.compare(this.translationX, graphicsLayerElement.translationX) == 0 && Float.compare(this.translationY, graphicsLayerElement.translationY) == 0 && Float.compare(this.shadowElevation, graphicsLayerElement.shadowElevation) == 0 && Float.compare(this.rotationX, graphicsLayerElement.rotationX) == 0 && Float.compare(this.rotationY, graphicsLayerElement.rotationY) == 0 && Float.compare(this.rotationZ, graphicsLayerElement.rotationZ) == 0 && Float.compare(this.cameraDistance, graphicsLayerElement.cameraDistance) == 0 && TransformOrigin.m3835equalsimpl0(this.transformOrigin, graphicsLayerElement.transformOrigin) && p.a(this.shape, graphicsLayerElement.shape) && this.clip == graphicsLayerElement.clip && p.a(this.renderEffect, graphicsLayerElement.renderEffect) && Color.m3473equalsimpl0(this.ambientShadowColor, graphicsLayerElement.ambientShadowColor) && Color.m3473equalsimpl0(this.spotShadowColor, graphicsLayerElement.spotShadowColor) && CompositingStrategy.m3557equalsimpl0(this.compositingStrategy, graphicsLayerElement.compositingStrategy);
    }

    public final float getAlpha() {
        return this.alpha;
    }

    /* JADX INFO: renamed from: getAmbientShadowColor-0d7_KjU, reason: not valid java name */
    public final long m3626getAmbientShadowColor0d7_KjU() {
        return this.ambientShadowColor;
    }

    public final float getCameraDistance() {
        return this.cameraDistance;
    }

    public final boolean getClip() {
        return this.clip;
    }

    /* JADX INFO: renamed from: getCompositingStrategy--NrFUSI, reason: not valid java name */
    public final int m3627getCompositingStrategyNrFUSI() {
        return this.compositingStrategy;
    }

    public final RenderEffect getRenderEffect() {
        return this.renderEffect;
    }

    public final float getRotationX() {
        return this.rotationX;
    }

    public final float getRotationY() {
        return this.rotationY;
    }

    public final float getRotationZ() {
        return this.rotationZ;
    }

    public final float getScaleX() {
        return this.scaleX;
    }

    public final float getScaleY() {
        return this.scaleY;
    }

    public final float getShadowElevation() {
        return this.shadowElevation;
    }

    public final Shape getShape() {
        return this.shape;
    }

    /* JADX INFO: renamed from: getSpotShadowColor-0d7_KjU, reason: not valid java name */
    public final long m3628getSpotShadowColor0d7_KjU() {
        return this.spotShadowColor;
    }

    /* JADX INFO: renamed from: getTransformOrigin-SzJe1aQ, reason: not valid java name */
    public final long m3629getTransformOriginSzJe1aQ() {
        return this.transformOrigin;
    }

    public final float getTranslationX() {
        return this.translationX;
    }

    public final float getTranslationY() {
        return this.translationY;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        int iC = (androidx.compose.material3.d.c(this.shape, (TransformOrigin.m3838hashCodeimpl(this.transformOrigin) + a0.c.b(this.cameraDistance, a0.c.b(this.rotationZ, a0.c.b(this.rotationY, a0.c.b(this.rotationX, a0.c.b(this.shadowElevation, a0.c.b(this.translationY, a0.c.b(this.translationX, a0.c.b(this.alpha, a0.c.b(this.scaleY, Float.floatToIntBits(this.scaleX) * 31, 31), 31), 31), 31), 31), 31), 31), 31), 31)) * 31, 31) + (this.clip ? 1231 : 1237)) * 31;
        RenderEffect renderEffect = this.renderEffect;
        return CompositingStrategy.m3558hashCodeimpl(this.compositingStrategy) + androidx.compose.foundation.c.a(androidx.compose.foundation.c.a((iC + (renderEffect == null ? 0 : renderEffect.hashCode())) * 31, 31, this.ambientShadowColor), 31, this.spotShadowColor);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("graphicsLayer");
        inspectorInfo.getProperties().set("scaleX", Float.valueOf(this.scaleX));
        inspectorInfo.getProperties().set("scaleY", Float.valueOf(this.scaleY));
        inspectorInfo.getProperties().set("alpha", Float.valueOf(this.alpha));
        inspectorInfo.getProperties().set("translationX", Float.valueOf(this.translationX));
        inspectorInfo.getProperties().set("translationY", Float.valueOf(this.translationY));
        inspectorInfo.getProperties().set("shadowElevation", Float.valueOf(this.shadowElevation));
        inspectorInfo.getProperties().set("rotationX", Float.valueOf(this.rotationX));
        inspectorInfo.getProperties().set("rotationY", Float.valueOf(this.rotationY));
        inspectorInfo.getProperties().set("rotationZ", Float.valueOf(this.rotationZ));
        inspectorInfo.getProperties().set("cameraDistance", Float.valueOf(this.cameraDistance));
        inspectorInfo.getProperties().set("transformOrigin", TransformOrigin.m3828boximpl(this.transformOrigin));
        inspectorInfo.getProperties().set("shape", this.shape);
        a0.c.f(this.clip, inspectorInfo.getProperties(), "clip", inspectorInfo).set("renderEffect", this.renderEffect);
        inspectorInfo.getProperties().set("ambientShadowColor", Color.m3462boximpl(this.ambientShadowColor));
        inspectorInfo.getProperties().set("spotShadowColor", Color.m3462boximpl(this.spotShadowColor));
        inspectorInfo.getProperties().set("compositingStrategy", CompositingStrategy.m3554boximpl(this.compositingStrategy));
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("GraphicsLayerElement(scaleX=");
        sb2.append(this.scaleX);
        sb2.append(", scaleY=");
        sb2.append(this.scaleY);
        sb2.append(", alpha=");
        sb2.append(this.alpha);
        sb2.append(", translationX=");
        sb2.append(this.translationX);
        sb2.append(", translationY=");
        sb2.append(this.translationY);
        sb2.append(", shadowElevation=");
        sb2.append(this.shadowElevation);
        sb2.append(", rotationX=");
        sb2.append(this.rotationX);
        sb2.append(", rotationY=");
        sb2.append(this.rotationY);
        sb2.append(", rotationZ=");
        sb2.append(this.rotationZ);
        sb2.append(", cameraDistance=");
        sb2.append(this.cameraDistance);
        sb2.append(", transformOrigin=");
        sb2.append((Object) TransformOrigin.m3839toStringimpl(this.transformOrigin));
        sb2.append(", shape=");
        sb2.append(this.shape);
        sb2.append(", clip=");
        sb2.append(this.clip);
        sb2.append(", renderEffect=");
        sb2.append(this.renderEffect);
        sb2.append(", ambientShadowColor=");
        androidx.compose.foundation.c.x(this.ambientShadowColor, ", spotShadowColor=", sb2);
        androidx.compose.foundation.c.x(this.spotShadowColor, ", compositingStrategy=", sb2);
        sb2.append((Object) CompositingStrategy.m3559toStringimpl(this.compositingStrategy));
        sb2.append(')');
        return sb2.toString();
    }

    private GraphicsLayerElement(float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, long j10, Shape shape, boolean z, RenderEffect renderEffect, long j11, long j12, int i10) {
        this.scaleX = f10;
        this.scaleY = f11;
        this.alpha = f12;
        this.translationX = f13;
        this.translationY = f14;
        this.shadowElevation = f15;
        this.rotationX = f16;
        this.rotationY = f17;
        this.rotationZ = f18;
        this.cameraDistance = f19;
        this.transformOrigin = j10;
        this.shape = shape;
        this.clip = z;
        this.renderEffect = renderEffect;
        this.ambientShadowColor = j11;
        this.spotShadowColor = j12;
        this.compositingStrategy = i10;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public SimpleGraphicsLayerModifier create() {
        return new SimpleGraphicsLayerModifier(this.scaleX, this.scaleY, this.alpha, this.translationX, this.translationY, this.shadowElevation, this.rotationX, this.rotationY, this.rotationZ, this.cameraDistance, this.transformOrigin, this.shape, this.clip, this.renderEffect, this.ambientShadowColor, this.spotShadowColor, this.compositingStrategy, null);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(SimpleGraphicsLayerModifier node) {
        node.setScaleX(this.scaleX);
        node.setScaleY(this.scaleY);
        node.setAlpha(this.alpha);
        node.setTranslationX(this.translationX);
        node.setTranslationY(this.translationY);
        node.setShadowElevation(this.shadowElevation);
        node.setRotationX(this.rotationX);
        node.setRotationY(this.rotationY);
        node.setRotationZ(this.rotationZ);
        node.setCameraDistance(this.cameraDistance);
        node.m3784setTransformOrigin__ExYCQ(this.transformOrigin);
        node.setShape(this.shape);
        node.setClip(this.clip);
        node.setRenderEffect(this.renderEffect);
        node.m3781setAmbientShadowColor8_81llA(this.ambientShadowColor);
        node.m3783setSpotShadowColor8_81llA(this.spotShadowColor);
        node.m3782setCompositingStrategyaDBOjCE(this.compositingStrategy);
        node.invalidateLayerBlock();
    }
}
