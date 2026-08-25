package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Fields;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.layout.GraphicLayerInfo;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\b\u0001\u0018\u0000 e2\u00020\u00012\u00020\u0002:\u0002efB1\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0010\u0010\u0011J'\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010$\u001a\u00020\u00072\u0006\u0010!\u001a\u00020 H\u0016ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u001a\u0010'\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020%H\u0016ø\u0001\u0000¢\u0006\u0004\b&\u0010#J\u000f\u0010(\u001a\u00020\u0007H\u0016¢\u0006\u0004\b(\u0010\u000eJ\u0017\u0010)\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0006H\u0016¢\u0006\u0004\b)\u0010\u0011J\u000f\u0010*\u001a\u00020\u0007H\u0016¢\u0006\u0004\b*\u0010\u000eJ\u000f\u0010+\u001a\u00020\u0007H\u0016¢\u0006\u0004\b+\u0010\u000eJ\"\u00100\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u001cH\u0016ø\u0001\u0000¢\u0006\u0004\b.\u0010/J\u001f\u00103\u001a\u00020\u00072\u0006\u00102\u001a\u0002012\u0006\u0010-\u001a\u00020\u001cH\u0016¢\u0006\u0004\b3\u00104J1\u00105\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0016¢\u0006\u0004\b5\u00106J\u001a\u0010;\u001a\u00020\u00072\u0006\u00108\u001a\u000207H\u0016ø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001a\u0010=\u001a\u00020\u00072\u0006\u00108\u001a\u000207H\u0016ø\u0001\u0000¢\u0006\u0004\b<\u0010:R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010>\u001a\u0004\b?\u0010@R$\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00058\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010AR\u001e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010BR$\u0010D\u001a\u00020\u001c2\u0006\u0010C\u001a\u00020\u001c8\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\bD\u0010E\"\u0004\bF\u0010GR\u0014\u0010I\u001a\u00020H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010ER\u0016\u0010L\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bL\u0010ER\u0018\u0010N\u001a\u0004\u0018\u00010M8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010OR\u001a\u0010R\u001a\b\u0012\u0004\u0012\u00020Q0P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010U\u001a\u00020T8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bU\u0010VR\u001c\u0010X\u001a\u00020W8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bX\u0010YR\u0014\u0010Z\u001a\u00020Q8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010]\u001a\u00020\\8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b]\u0010^R\u0014\u0010b\u001a\u00020_8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u0014\u0010d\u001a\u00020_8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bc\u0010a\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006g"}, d2 = {"Landroidx/compose/ui/platform/RenderNodeLayer;", "Landroidx/compose/ui/node/OwnedLayer;", "Landroidx/compose/ui/layout/GraphicLayerInfo;", "Landroidx/compose/ui/platform/AndroidComposeView;", "ownerView", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/Canvas;", "Lx6/t0;", "drawBlock", "Lkotlin/Function0;", "invalidateParentLayer", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;Lr7/l;Lr7/a;)V", "triggerRepaint", "()V", "canvas", "clipRenderNode", "(Landroidx/compose/ui/graphics/Canvas;)V", "Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "scope", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/unit/Density;", "density", "updateLayerProperties", "(Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;)V", "Landroidx/compose/ui/geometry/Offset;", "position", "", "isInLayer-k-4lQ0M", "(J)Z", "isInLayer", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "resize-ozmzZPI", "(J)V", "resize", "Landroidx/compose/ui/unit/IntOffset;", "move--gyyYBs", "move", "invalidate", "drawLayer", "updateDisplayList", "destroy", "point", "inverse", "mapOffset-8S9VItk", "(JZ)J", "mapOffset", "Landroidx/compose/ui/geometry/MutableRect;", "rect", "mapBounds", "(Landroidx/compose/ui/geometry/MutableRect;Z)V", "reuseLayer", "(Lr7/l;Lr7/a;)V", "Landroidx/compose/ui/graphics/Matrix;", "matrix", "transform-58bKbWc", "([F)V", "transform", "inverseTransform-58bKbWc", "inverseTransform", "Landroidx/compose/ui/platform/AndroidComposeView;", "getOwnerView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "Lr7/l;", "Lr7/a;", "value", "isDirty", "Z", "setDirty", "(Z)V", "Landroidx/compose/ui/platform/OutlineResolver;", "outlineResolver", "Landroidx/compose/ui/platform/OutlineResolver;", "isDestroyed", "drawnWithZ", "Landroidx/compose/ui/graphics/Paint;", "softwareLayerPaint", "Landroidx/compose/ui/graphics/Paint;", "Landroidx/compose/ui/platform/LayerMatrixCache;", "Landroidx/compose/ui/platform/DeviceRenderNode;", "matrixCache", "Landroidx/compose/ui/platform/LayerMatrixCache;", "Landroidx/compose/ui/graphics/CanvasHolder;", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "Landroidx/compose/ui/graphics/TransformOrigin;", "transformOrigin", "J", "renderNode", "Landroidx/compose/ui/platform/DeviceRenderNode;", "", "mutatedFields", "I", "", "getLayerId", "()J", "layerId", "getOwnerViewId", "ownerViewId", "Companion", "UniqueDrawingIdApi29", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RenderNodeLayer implements OwnedLayer, GraphicLayerInfo {
    private r7.l<? super Canvas, t0> drawBlock;
    private boolean drawnWithZ;
    private r7.a<t0> invalidateParentLayer;
    private boolean isDestroyed;
    private boolean isDirty;
    private int mutatedFields;
    private final OutlineResolver outlineResolver;
    private final AndroidComposeView ownerView;
    private final DeviceRenderNode renderNode;
    private Paint softwareLayerPaint;
    public static final int $stable = 8;
    private static final r7.p<DeviceRenderNode, Matrix, t0> getMatrix = RenderNodeLayer$Companion$getMatrix$1.INSTANCE;
    private final LayerMatrixCache<DeviceRenderNode> matrixCache = new LayerMatrixCache<>(getMatrix);
    private final CanvasHolder canvasHolder = new CanvasHolder();
    private long transformOrigin = TransformOrigin.INSTANCE.m3841getCenterSzJe1aQ();

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/platform/RenderNodeLayer$UniqueDrawingIdApi29;", "", "()V", "getUniqueDrawingId", "", "view", "Landroid/view/View;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class UniqueDrawingIdApi29 {
        public static final UniqueDrawingIdApi29 INSTANCE = new UniqueDrawingIdApi29();

        private UniqueDrawingIdApi29() {
        }

        @q7.n
        public static final long getUniqueDrawingId(View view) {
            return view.getUniqueDrawingId();
        }
    }

    public RenderNodeLayer(AndroidComposeView androidComposeView, r7.l<? super Canvas, t0> lVar, r7.a<t0> aVar) {
        this.ownerView = androidComposeView;
        this.drawBlock = lVar;
        this.invalidateParentLayer = aVar;
        this.outlineResolver = new OutlineResolver(androidComposeView.getDensity());
        DeviceRenderNode renderNodeApi29 = Build.VERSION.SDK_INT >= 29 ? new RenderNodeApi29(androidComposeView) : new RenderNodeApi23(androidComposeView);
        renderNodeApi29.setHasOverlappingRendering(true);
        renderNodeApi29.setClipToBounds(false);
        this.renderNode = renderNodeApi29;
    }

    private final void clipRenderNode(Canvas canvas) {
        if (this.renderNode.getClipToOutline() || this.renderNode.getClipToBounds()) {
            this.outlineResolver.clipToOutline(canvas);
        }
    }

    private final void setDirty(boolean z) {
        if (z != this.isDirty) {
            this.isDirty = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    private final void triggerRepaint() {
        if (Build.VERSION.SDK_INT >= 26) {
            WrapperRenderNodeLayerHelperMethods.INSTANCE.onDescendantInvalidated(this.ownerView);
        } else {
            this.ownerView.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void destroy() {
        if (this.renderNode.getHasDisplayList()) {
            this.renderNode.discardDisplayList();
        }
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.isDestroyed = true;
        setDirty(false);
        this.ownerView.requestClearInvalidObservations();
        this.ownerView.recycle$ui_release(this);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void drawLayer(Canvas canvas) {
        android.graphics.Canvas nativeCanvas = AndroidCanvas_androidKt.getNativeCanvas(canvas);
        if (nativeCanvas.isHardwareAccelerated()) {
            updateDisplayList();
            boolean z = this.renderNode.getElevation() > 0.0f;
            this.drawnWithZ = z;
            if (z) {
                canvas.enableZ();
            }
            this.renderNode.drawInto(nativeCanvas);
            if (this.drawnWithZ) {
                canvas.disableZ();
                return;
            }
            return;
        }
        float left = this.renderNode.getLeft();
        float top = this.renderNode.getTop();
        float right = this.renderNode.getRight();
        float bottom = this.renderNode.getBottom();
        if (this.renderNode.getAlpha() < 1.0f) {
            Paint Paint = this.softwareLayerPaint;
            if (Paint == null) {
                Paint = AndroidPaint_androidKt.Paint();
                this.softwareLayerPaint = Paint;
            }
            Paint.setAlpha(this.renderNode.getAlpha());
            nativeCanvas.saveLayer(left, top, right, bottom, Paint.getInternalPaint());
        } else {
            canvas.save();
        }
        canvas.translate(left, top);
        canvas.mo3333concat58bKbWc(this.matrixCache.m4972calculateMatrixGrdbGEg(this.renderNode));
        clipRenderNode(canvas);
        r7.l<? super Canvas, t0> lVar = this.drawBlock;
        if (lVar != null) {
            lVar.invoke(canvas);
        }
        canvas.restore();
        setDirty(false);
    }

    @Override // androidx.compose.ui.layout.GraphicLayerInfo
    public long getLayerId() {
        return this.renderNode.getUniqueId();
    }

    public final AndroidComposeView getOwnerView() {
        return this.ownerView;
    }

    @Override // androidx.compose.ui.layout.GraphicLayerInfo
    public long getOwnerViewId() {
        if (Build.VERSION.SDK_INT >= 29) {
            return UniqueDrawingIdApi29.getUniqueDrawingId(this.ownerView);
        }
        return -1L;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void invalidate() {
        if (this.isDirty || this.isDestroyed) {
            return;
        }
        this.ownerView.invalidate();
        setDirty(true);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: inverseTransform-58bKbWc */
    public void mo4890inverseTransform58bKbWc(float[] matrix) {
        float[] fArrM4971calculateInverseMatrixbWbORWo = this.matrixCache.m4971calculateInverseMatrixbWbORWo(this.renderNode);
        if (fArrM4971calculateInverseMatrixbWbORWo != null) {
            androidx.compose.ui.graphics.Matrix.m3701timesAssign58bKbWc(matrix, fArrM4971calculateInverseMatrixbWbORWo);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: isInLayer-k-4lQ0M */
    public boolean mo4891isInLayerk4lQ0M(long position) {
        float fM3236getXimpl = Offset.m3236getXimpl(position);
        float fM3237getYimpl = Offset.m3237getYimpl(position);
        if (this.renderNode.getClipToBounds()) {
            return 0.0f <= fM3236getXimpl && fM3236getXimpl < ((float) this.renderNode.getWidth()) && 0.0f <= fM3237getYimpl && fM3237getYimpl < ((float) this.renderNode.getHeight());
        }
        if (this.renderNode.getClipToOutline()) {
            return this.outlineResolver.m4994isInOutlinek4lQ0M(position);
        }
        return true;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void mapBounds(MutableRect rect, boolean inverse) {
        if (!inverse) {
            androidx.compose.ui.graphics.Matrix.m3692mapimpl(this.matrixCache.m4972calculateMatrixGrdbGEg(this.renderNode), rect);
            return;
        }
        float[] fArrM4971calculateInverseMatrixbWbORWo = this.matrixCache.m4971calculateInverseMatrixbWbORWo(this.renderNode);
        if (fArrM4971calculateInverseMatrixbWbORWo == null) {
            rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        } else {
            androidx.compose.ui.graphics.Matrix.m3692mapimpl(fArrM4971calculateInverseMatrixbWbORWo, rect);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: mapOffset-8S9VItk */
    public long mo4892mapOffset8S9VItk(long point, boolean inverse) {
        if (!inverse) {
            return androidx.compose.ui.graphics.Matrix.m3690mapMKHz9U(this.matrixCache.m4972calculateMatrixGrdbGEg(this.renderNode), point);
        }
        float[] fArrM4971calculateInverseMatrixbWbORWo = this.matrixCache.m4971calculateInverseMatrixbWbORWo(this.renderNode);
        return fArrM4971calculateInverseMatrixbWbORWo != null ? androidx.compose.ui.graphics.Matrix.m3690mapMKHz9U(fArrM4971calculateInverseMatrixbWbORWo, point) : Offset.INSTANCE.m3250getInfiniteF1C5BW0();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: move--gyyYBs */
    public void mo4893movegyyYBs(long position) {
        int left = this.renderNode.getLeft();
        int top = this.renderNode.getTop();
        int iM5802getXimpl = IntOffset.m5802getXimpl(position);
        int iM5803getYimpl = IntOffset.m5803getYimpl(position);
        if (left == iM5802getXimpl && top == iM5803getYimpl) {
            return;
        }
        if (left != iM5802getXimpl) {
            this.renderNode.offsetLeftAndRight(iM5802getXimpl - left);
        }
        if (top != iM5803getYimpl) {
            this.renderNode.offsetTopAndBottom(iM5803getYimpl - top);
        }
        triggerRepaint();
        this.matrixCache.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: resize-ozmzZPI */
    public void mo4894resizeozmzZPI(long size) {
        int iM5844getWidthimpl = IntSize.m5844getWidthimpl(size);
        int iM5843getHeightimpl = IntSize.m5843getHeightimpl(size);
        float f10 = iM5844getWidthimpl;
        this.renderNode.setPivotX(TransformOrigin.m3836getPivotFractionXimpl(this.transformOrigin) * f10);
        float f11 = iM5843getHeightimpl;
        this.renderNode.setPivotY(TransformOrigin.m3837getPivotFractionYimpl(this.transformOrigin) * f11);
        DeviceRenderNode deviceRenderNode = this.renderNode;
        if (deviceRenderNode.setPosition(deviceRenderNode.getLeft(), this.renderNode.getTop(), this.renderNode.getLeft() + iM5844getWidthimpl, this.renderNode.getTop() + iM5843getHeightimpl)) {
            this.outlineResolver.m4995updateuvyYCjk(SizeKt.Size(f10, f11));
            this.renderNode.setOutline(this.outlineResolver.getOutline());
            invalidate();
            this.matrixCache.invalidate();
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void reuseLayer(r7.l<? super Canvas, t0> drawBlock, r7.a<t0> invalidateParentLayer) {
        setDirty(false);
        this.isDestroyed = false;
        this.drawnWithZ = false;
        this.transformOrigin = TransformOrigin.INSTANCE.m3841getCenterSzJe1aQ();
        this.drawBlock = drawBlock;
        this.invalidateParentLayer = invalidateParentLayer;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: transform-58bKbWc */
    public void mo4895transform58bKbWc(float[] matrix) {
        androidx.compose.ui.graphics.Matrix.m3701timesAssign58bKbWc(matrix, this.matrixCache.m4972calculateMatrixGrdbGEg(this.renderNode));
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateDisplayList() {
        if (this.isDirty || !this.renderNode.getHasDisplayList()) {
            Path clipPath = (!this.renderNode.getClipToOutline() || this.outlineResolver.getOutlineClipSupported()) ? null : this.outlineResolver.getClipPath();
            r7.l<? super Canvas, t0> lVar = this.drawBlock;
            if (lVar != null) {
                this.renderNode.record(this.canvasHolder, clipPath, lVar);
            }
            setDirty(false);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateLayerProperties(ReusableGraphicsLayerScope scope, LayoutDirection layoutDirection, Density density) {
        r7.a<t0> aVar;
        int mutatedFields = scope.getMutatedFields() | this.mutatedFields;
        int i10 = mutatedFields & 4096;
        if (i10 != 0) {
            this.transformOrigin = scope.getTransformOrigin();
        }
        boolean z = false;
        boolean z5 = this.renderNode.getClipToOutline() && !this.outlineResolver.getOutlineClipSupported();
        if ((mutatedFields & 1) != 0) {
            this.renderNode.setScaleX(scope.getScaleX());
        }
        if ((mutatedFields & 2) != 0) {
            this.renderNode.setScaleY(scope.getScaleY());
        }
        if ((mutatedFields & 4) != 0) {
            this.renderNode.setAlpha(scope.getAlpha());
        }
        if ((mutatedFields & 8) != 0) {
            this.renderNode.setTranslationX(scope.getTranslationX());
        }
        if ((mutatedFields & 16) != 0) {
            this.renderNode.setTranslationY(scope.getTranslationY());
        }
        if ((mutatedFields & 32) != 0) {
            this.renderNode.setElevation(scope.getShadowElevation());
        }
        if ((mutatedFields & 64) != 0) {
            this.renderNode.setAmbientShadowColor(ColorKt.m3526toArgb8_81llA(scope.getAmbientShadowColor()));
        }
        if ((mutatedFields & 128) != 0) {
            this.renderNode.setSpotShadowColor(ColorKt.m3526toArgb8_81llA(scope.getSpotShadowColor()));
        }
        if ((mutatedFields & 1024) != 0) {
            this.renderNode.setRotationZ(scope.getRotationZ());
        }
        if ((mutatedFields & 256) != 0) {
            this.renderNode.setRotationX(scope.getRotationX());
        }
        if ((mutatedFields & 512) != 0) {
            this.renderNode.setRotationY(scope.getRotationY());
        }
        if ((mutatedFields & 2048) != 0) {
            this.renderNode.setCameraDistance(scope.getCameraDistance());
        }
        if (i10 != 0) {
            this.renderNode.setPivotX(TransformOrigin.m3836getPivotFractionXimpl(this.transformOrigin) * this.renderNode.getWidth());
            this.renderNode.setPivotY(TransformOrigin.m3837getPivotFractionYimpl(this.transformOrigin) * this.renderNode.getHeight());
        }
        boolean z10 = scope.getClip() && scope.getShape() != RectangleShapeKt.getRectangleShape();
        if ((mutatedFields & 24576) != 0) {
            this.renderNode.setClipToOutline(z10);
            this.renderNode.setClipToBounds(scope.getClip() && scope.getShape() == RectangleShapeKt.getRectangleShape());
        }
        if ((131072 & mutatedFields) != 0) {
            this.renderNode.setRenderEffect(scope.getRenderEffect());
        }
        if ((32768 & mutatedFields) != 0) {
            this.renderNode.mo4955setCompositingStrategyaDBOjCE(scope.getCompositingStrategy());
        }
        boolean zUpdate = this.outlineResolver.update(scope.getShape(), scope.getAlpha(), z10, scope.getShadowElevation(), layoutDirection, density);
        if (this.outlineResolver.getCacheIsDirty()) {
            this.renderNode.setOutline(this.outlineResolver.getOutline());
        }
        if (z10 && !this.outlineResolver.getOutlineClipSupported()) {
            z = true;
        }
        if (z5 != z || (z && zUpdate)) {
            invalidate();
        } else {
            triggerRepaint();
        }
        if (!this.drawnWithZ && this.renderNode.getElevation() > 0.0f && (aVar = this.invalidateParentLayer) != null) {
            aVar.invoke();
        }
        if ((mutatedFields & Fields.MatrixAffectingFields) != 0) {
            this.matrixCache.invalidate();
        }
        this.mutatedFields = scope.getMutatedFields();
    }
}
