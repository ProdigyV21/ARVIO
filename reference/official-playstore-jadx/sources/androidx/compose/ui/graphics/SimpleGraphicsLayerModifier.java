package androidx.compose.ui.graphics;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b6\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0093\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ&\u0010(\u001a\u00020%*\u00020 2\u0006\u0010\"\u001a\u00020!2\u0006\u0010$\u001a\u00020#H\u0016ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\u000f\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+R\"\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\"\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010,\u001a\u0004\b1\u0010.\"\u0004\b2\u00100R\"\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010,\u001a\u0004\b3\u0010.\"\u0004\b4\u00100R\"\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010,\u001a\u0004\b5\u0010.\"\u0004\b6\u00100R\"\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010,\u001a\u0004\b7\u0010.\"\u0004\b8\u00100R\"\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010,\u001a\u0004\b9\u0010.\"\u0004\b:\u00100R\"\u0010\n\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010,\u001a\u0004\b;\u0010.\"\u0004\b<\u00100R\"\u0010\u000b\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010,\u001a\u0004\b=\u0010.\"\u0004\b>\u00100R\"\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010,\u001a\u0004\b?\u0010.\"\u0004\b@\u00100R\"\u0010\r\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010,\u001a\u0004\bA\u0010.\"\u0004\bB\u00100R(\u0010\u000f\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u000f\u0010C\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\"\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010H\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010M\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR$\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010R\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR(\u0010\u0017\u001a\u00020\u00168\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0017\u0010C\u001a\u0004\bW\u0010E\"\u0004\bX\u0010GR(\u0010\u0018\u001a\u00020\u00168\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0018\u0010C\u001a\u0004\bY\u0010E\"\u0004\bZ\u0010GR(\u0010\u001a\u001a\u00020\u00198\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u001a\u0010[\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R'\u0010c\u001a\u0013\u0012\u0004\u0012\u00020a\u0012\u0004\u0012\u00020\u001d0`¢\u0006\u0002\bb8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bc\u0010dR\u0014\u0010f\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\be\u0010O\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006g"}, d2 = {"Landroidx/compose/ui/graphics/SimpleGraphicsLayerModifier;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "", "scaleX", "scaleY", "alpha", "translationX", "translationY", "shadowElevation", "rotationX", "rotationY", "rotationZ", "cameraDistance", "Landroidx/compose/ui/graphics/TransformOrigin;", "transformOrigin", "Landroidx/compose/ui/graphics/Shape;", "shape", "", "clip", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "Landroidx/compose/ui/graphics/Color;", "ambientShadowColor", "spotShadowColor", "Landroidx/compose/ui/graphics/CompositingStrategy;", "compositingStrategy", "<init>", "(FFFFFFFFFFJLandroidx/compose/ui/graphics/Shape;ZLandroidx/compose/ui/graphics/RenderEffect;JJILkotlin/jvm/internal/h;)V", "Lx6/t0;", "invalidateLayerBlock", "()V", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measurable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "measure", "", "toString", "()Ljava/lang/String;", "F", "getScaleX", "()F", "setScaleX", "(F)V", "getScaleY", "setScaleY", "getAlpha", "setAlpha", "getTranslationX", "setTranslationX", "getTranslationY", "setTranslationY", "getShadowElevation", "setShadowElevation", "getRotationX", "setRotationX", "getRotationY", "setRotationY", "getRotationZ", "setRotationZ", "getCameraDistance", "setCameraDistance", "J", "getTransformOrigin-SzJe1aQ", "()J", "setTransformOrigin-__ExYCQ", "(J)V", "Landroidx/compose/ui/graphics/Shape;", "getShape", "()Landroidx/compose/ui/graphics/Shape;", "setShape", "(Landroidx/compose/ui/graphics/Shape;)V", "Z", "getClip", "()Z", "setClip", "(Z)V", "Landroidx/compose/ui/graphics/RenderEffect;", "getRenderEffect", "()Landroidx/compose/ui/graphics/RenderEffect;", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "getAmbientShadowColor-0d7_KjU", "setAmbientShadowColor-8_81llA", "getSpotShadowColor-0d7_KjU", "setSpotShadowColor-8_81llA", "I", "getCompositingStrategy--NrFUSI", "()I", "setCompositingStrategy-aDBOjCE", "(I)V", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lx6/n;", "layerBlock", "Lr7/l;", "getShouldAutoInvalidate", "shouldAutoInvalidate", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SimpleGraphicsLayerModifier extends Modifier.Node implements LayoutModifierNode {
    private float alpha;
    private long ambientShadowColor;
    private float cameraDistance;
    private boolean clip;
    private int compositingStrategy;
    private l<? super GraphicsLayerScope, t0> layerBlock;
    private RenderEffect renderEffect;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float scaleX;
    private float scaleY;
    private float shadowElevation;
    private Shape shape;
    private long spotShadowColor;
    private long transformOrigin;
    private float translationX;
    private float translationY;

    public /* synthetic */ SimpleGraphicsLayerModifier(float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, long j10, Shape shape, boolean z, RenderEffect renderEffect, long j11, long j12, int i10, kotlin.jvm.internal.h hVar) {
        this(f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, j10, shape, z, renderEffect, j11, j12, i10);
    }

    public final float getAlpha() {
        return this.alpha;
    }

    /* JADX INFO: renamed from: getAmbientShadowColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getAmbientShadowColor() {
        return this.ambientShadowColor;
    }

    public final float getCameraDistance() {
        return this.cameraDistance;
    }

    public final boolean getClip() {
        return this.clip;
    }

    /* JADX INFO: renamed from: getCompositingStrategy--NrFUSI, reason: not valid java name and from getter */
    public final int getCompositingStrategy() {
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

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    /* JADX INFO: renamed from: getSpotShadowColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getSpotShadowColor() {
        return this.spotShadowColor;
    }

    /* JADX INFO: renamed from: getTransformOrigin-SzJe1aQ, reason: not valid java name and from getter */
    public final long getTransformOrigin() {
        return this.transformOrigin;
    }

    public final float getTranslationX() {
        return this.translationX;
    }

    public final float getTranslationY() {
        return this.translationY;
    }

    public final void invalidateLayerBlock() {
        NodeCoordinator wrapped = DelegatableNodeKt.m4726requireCoordinator64DMado(this, NodeKind.m4843constructorimpl(2)).getWrapped();
        if (wrapped != null) {
            wrapped.updateLayerBlock(this.layerBlock, true);
        }
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.a(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.b(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo55measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j10) {
        Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(j10);
        return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new SimpleGraphicsLayerModifier$measure$1(placeableMo4631measureBRTryo0, this), 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.c(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i10) {
        return LayoutModifierNode.CC.d(this, intrinsicMeasureScope, intrinsicMeasurable, i10);
    }

    public final void setAlpha(float f10) {
        this.alpha = f10;
    }

    /* JADX INFO: renamed from: setAmbientShadowColor-8_81llA, reason: not valid java name */
    public final void m3781setAmbientShadowColor8_81llA(long j10) {
        this.ambientShadowColor = j10;
    }

    public final void setCameraDistance(float f10) {
        this.cameraDistance = f10;
    }

    public final void setClip(boolean z) {
        this.clip = z;
    }

    /* JADX INFO: renamed from: setCompositingStrategy-aDBOjCE, reason: not valid java name */
    public final void m3782setCompositingStrategyaDBOjCE(int i10) {
        this.compositingStrategy = i10;
    }

    public final void setRenderEffect(RenderEffect renderEffect) {
        this.renderEffect = renderEffect;
    }

    public final void setRotationX(float f10) {
        this.rotationX = f10;
    }

    public final void setRotationY(float f10) {
        this.rotationY = f10;
    }

    public final void setRotationZ(float f10) {
        this.rotationZ = f10;
    }

    public final void setScaleX(float f10) {
        this.scaleX = f10;
    }

    public final void setScaleY(float f10) {
        this.scaleY = f10;
    }

    public final void setShadowElevation(float f10) {
        this.shadowElevation = f10;
    }

    public final void setShape(Shape shape) {
        this.shape = shape;
    }

    /* JADX INFO: renamed from: setSpotShadowColor-8_81llA, reason: not valid java name */
    public final void m3783setSpotShadowColor8_81llA(long j10) {
        this.spotShadowColor = j10;
    }

    /* JADX INFO: renamed from: setTransformOrigin-__ExYCQ, reason: not valid java name */
    public final void m3784setTransformOrigin__ExYCQ(long j10) {
        this.transformOrigin = j10;
    }

    public final void setTranslationX(float f10) {
        this.translationX = f10;
    }

    public final void setTranslationY(float f10) {
        this.translationY = f10;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("SimpleGraphicsLayerModifier(scaleX=");
        sb2.append(this.scaleX);
        sb2.append(", scaleY=");
        sb2.append(this.scaleY);
        sb2.append(", alpha = ");
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

    public /* synthetic */ SimpleGraphicsLayerModifier(float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, long j10, Shape shape, boolean z, RenderEffect renderEffect, long j11, long j12, int i10, int i11, kotlin.jvm.internal.h hVar) {
        this(f10, f11, f12, f13, f14, f15, f16, f17, f18, f19, j10, shape, z, renderEffect, j11, j12, (i11 & 65536) != 0 ? CompositingStrategy.INSTANCE.m3561getAutoNrFUSI() : i10, null);
    }

    private SimpleGraphicsLayerModifier(float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, long j10, Shape shape, boolean z, RenderEffect renderEffect, long j11, long j12, int i10) {
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
        this.layerBlock = new SimpleGraphicsLayerModifier$layerBlock$1(this);
    }
}
