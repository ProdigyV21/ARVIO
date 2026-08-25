package androidx.compose.ui.platform;

import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasHolder;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.Fields;
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
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \u0080\u00012\u00020\u00012\u00020\u00022\u00020\u0003:\u0004\u0080\u0001\u0081\u0001B9\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\f¢\u0006\u0004\b\u000e\u0010\u000fJ'\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u001a\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001bH\u0016ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010$\u001a\u00020\n2\u0006\u0010!\u001a\u00020 H\u0016ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u001a\u0010'\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020%H\u0016ø\u0001\u0000¢\u0006\u0004\b&\u0010#J\u0017\u0010)\u001a\u00020\n2\u0006\u0010(\u001a\u00020\tH\u0016¢\u0006\u0004\b)\u0010*J\u0017\u0010,\u001a\u00020\n2\u0006\u0010(\u001a\u00020+H\u0014¢\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\nH\u0016¢\u0006\u0004\b.\u0010/J7\u00106\u001a\u00020\n2\u0006\u00100\u001a\u00020\u00182\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u0002012\u0006\u00104\u001a\u0002012\u0006\u00105\u001a\u000201H\u0014¢\u0006\u0004\b6\u00107J\u000f\u00108\u001a\u00020\nH\u0016¢\u0006\u0004\b8\u0010/J\u000f\u00109\u001a\u00020\nH\u0016¢\u0006\u0004\b9\u0010/J\u000f\u0010:\u001a\u00020\nH\u0016¢\u0006\u0004\b:\u0010/J\"\u0010?\u001a\u00020\u001b2\u0006\u0010;\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b=\u0010>J\u001f\u0010B\u001a\u00020\n2\u0006\u0010A\u001a\u00020@2\u0006\u0010<\u001a\u00020\u0018H\u0016¢\u0006\u0004\bB\u0010CJ1\u0010D\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0016¢\u0006\u0004\bD\u0010EJ\u001a\u0010J\u001a\u00020\n2\u0006\u0010G\u001a\u00020FH\u0016ø\u0001\u0000¢\u0006\u0004\bH\u0010IJ\u001a\u0010L\u001a\u00020\n2\u0006\u0010G\u001a\u00020FH\u0016ø\u0001\u0000¢\u0006\u0004\bK\u0010IJ\u000f\u0010M\u001a\u00020\nH\u0002¢\u0006\u0004\bM\u0010/J\u000f\u0010N\u001a\u00020\nH\u0002¢\u0006\u0004\bN\u0010/R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010O\u001a\u0004\bP\u0010QR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010R\u001a\u0004\bS\u0010TR$\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010UR\u001e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010VR\u0014\u0010X\u001a\u00020W8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010YR\u0016\u0010Z\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010[R\u0018\u0010]\u001a\u0004\u0018\u00010\\8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b]\u0010^R*\u0010`\u001a\u00020\u00182\u0006\u0010_\u001a\u00020\u00188\u0006@BX\u0086\u000e¢\u0006\u0012\n\u0004\b`\u0010[\u001a\u0004\b`\u0010\u001a\"\u0004\ba\u0010bR\u0016\u0010c\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bc\u0010[R\u0014\u0010e\u001a\u00020d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010fR\u001a\u0010h\u001a\b\u0012\u0004\u0012\u00020\u00010g8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010iR\u001c\u0010k\u001a\u00020j8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bk\u0010lR\u0016\u0010m\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bm\u0010[R\u001a\u0010o\u001a\u00020n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bo\u0010l\u001a\u0004\bp\u0010qR\u0016\u0010r\u001a\u0002018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\br\u0010sR\u0014\u0010u\u001a\u00020n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bt\u0010qR$\u0010{\u001a\u00020v2\u0006\u0010_\u001a\u00020v8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR\u0016\u0010\u007f\u001a\u0004\u0018\u00010|8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b}\u0010~\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0082\u0001"}, d2 = {"Landroidx/compose/ui/platform/ViewLayer;", "Landroid/view/View;", "Landroidx/compose/ui/node/OwnedLayer;", "Landroidx/compose/ui/layout/GraphicLayerInfo;", "Landroidx/compose/ui/platform/AndroidComposeView;", "ownerView", "Landroidx/compose/ui/platform/DrawChildContainer;", TtmlNode.RUBY_CONTAINER, "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/Canvas;", "Lx6/t0;", "drawBlock", "Lkotlin/Function0;", "invalidateParentLayer", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;Landroidx/compose/ui/platform/DrawChildContainer;Lr7/l;Lr7/a;)V", "Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "scope", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Landroidx/compose/ui/unit/Density;", "density", "updateLayerProperties", "(Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;)V", "", "hasOverlappingRendering", "()Z", "Landroidx/compose/ui/geometry/Offset;", "position", "isInLayer-k-4lQ0M", "(J)Z", "isInLayer", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "resize-ozmzZPI", "(J)V", "resize", "Landroidx/compose/ui/unit/IntOffset;", "move--gyyYBs", "move", "canvas", "drawLayer", "(Landroidx/compose/ui/graphics/Canvas;)V", "Landroid/graphics/Canvas;", "dispatchDraw", "(Landroid/graphics/Canvas;)V", "invalidate", "()V", "changed", "", CmcdData.STREAM_TYPE_LIVE, "t", "r", "b", "onLayout", "(ZIIII)V", "destroy", "updateDisplayList", "forceLayout", "point", "inverse", "mapOffset-8S9VItk", "(JZ)J", "mapOffset", "Landroidx/compose/ui/geometry/MutableRect;", "rect", "mapBounds", "(Landroidx/compose/ui/geometry/MutableRect;Z)V", "reuseLayer", "(Lr7/l;Lr7/a;)V", "Landroidx/compose/ui/graphics/Matrix;", "matrix", "transform-58bKbWc", "([F)V", "transform", "inverseTransform-58bKbWc", "inverseTransform", "updateOutlineResolver", "resetClipBounds", "Landroidx/compose/ui/platform/AndroidComposeView;", "getOwnerView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "Landroidx/compose/ui/platform/DrawChildContainer;", "getContainer", "()Landroidx/compose/ui/platform/DrawChildContainer;", "Lr7/l;", "Lr7/a;", "Landroidx/compose/ui/platform/OutlineResolver;", "outlineResolver", "Landroidx/compose/ui/platform/OutlineResolver;", "clipToBounds", "Z", "Landroid/graphics/Rect;", "clipBoundsCache", "Landroid/graphics/Rect;", "value", "isInvalidated", "setInvalidated", "(Z)V", "drawnWithZ", "Landroidx/compose/ui/graphics/CanvasHolder;", "canvasHolder", "Landroidx/compose/ui/graphics/CanvasHolder;", "Landroidx/compose/ui/platform/LayerMatrixCache;", "matrixCache", "Landroidx/compose/ui/platform/LayerMatrixCache;", "Landroidx/compose/ui/graphics/TransformOrigin;", "mTransformOrigin", "J", "mHasOverlappingRendering", "", "layerId", "getLayerId", "()J", "mutatedFields", "I", "getOwnerViewId", "ownerViewId", "", "getCameraDistancePx", "()F", "setCameraDistancePx", "(F)V", "cameraDistancePx", "Landroidx/compose/ui/graphics/Path;", "getManualClipPath", "()Landroidx/compose/ui/graphics/Path;", "manualClipPath", "Companion", "UniqueDrawingIdApi29", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ViewLayer extends View implements OwnedLayer, GraphicLayerInfo {
    private static boolean hasRetrievedMethod;
    private static Field recreateDisplayList;
    private static boolean shouldUseDispatchDraw;
    private static Method updateDisplayListIfDirtyMethod;
    private final CanvasHolder canvasHolder;
    private Rect clipBoundsCache;
    private boolean clipToBounds;
    private final DrawChildContainer container;
    private r7.l<? super Canvas, t0> drawBlock;
    private boolean drawnWithZ;
    private r7.a<t0> invalidateParentLayer;
    private boolean isInvalidated;
    private final long layerId;
    private boolean mHasOverlappingRendering;
    private long mTransformOrigin;
    private final LayerMatrixCache<View> matrixCache;
    private int mutatedFields;
    private final OutlineResolver outlineResolver;
    private final AndroidComposeView ownerView;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final r7.p<View, Matrix, t0> getMatrix = ViewLayer$Companion$getMatrix$1.INSTANCE;
    private static final ViewOutlineProvider OutlineProvider = new ViewOutlineProvider() { // from class: androidx.compose.ui.platform.ViewLayer$Companion$OutlineProvider$1
        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            outline.set(((ViewLayer) view).outlineResolver.getOutline());
        }
    };

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR$\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R*\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8\u0006@@X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013\"\u0004\b\u0016\u0010\u0017R&\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00060\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!¨\u0006\""}, d2 = {"Landroidx/compose/ui/platform/ViewLayer$Companion;", "", "<init>", "()V", "Landroid/view/View;", "view", "Lx6/t0;", "updateDisplayList", "(Landroid/view/View;)V", "Landroid/view/ViewOutlineProvider;", "OutlineProvider", "Landroid/view/ViewOutlineProvider;", "getOutlineProvider", "()Landroid/view/ViewOutlineProvider;", "", "<set-?>", "hasRetrievedMethod", "Z", "getHasRetrievedMethod", "()Z", "shouldUseDispatchDraw", "getShouldUseDispatchDraw", "setShouldUseDispatchDraw$ui_release", "(Z)V", "Lkotlin/Function2;", "Landroid/graphics/Matrix;", "getMatrix", "Lr7/p;", "Ljava/lang/reflect/Field;", "recreateDisplayList", "Ljava/lang/reflect/Field;", "Ljava/lang/reflect/Method;", "updateDisplayListIfDirtyMethod", "Ljava/lang/reflect/Method;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        public final boolean getHasRetrievedMethod() {
            return ViewLayer.hasRetrievedMethod;
        }

        public final ViewOutlineProvider getOutlineProvider() {
            return ViewLayer.OutlineProvider;
        }

        public final boolean getShouldUseDispatchDraw() {
            return ViewLayer.shouldUseDispatchDraw;
        }

        public final void setShouldUseDispatchDraw$ui_release(boolean z) {
            ViewLayer.shouldUseDispatchDraw = z;
        }

        public final void updateDisplayList(View view) {
            try {
                if (!getHasRetrievedMethod()) {
                    ViewLayer.hasRetrievedMethod = true;
                    if (Build.VERSION.SDK_INT < 28) {
                        ViewLayer.updateDisplayListIfDirtyMethod = View.class.getDeclaredMethod("updateDisplayListIfDirty", null);
                        ViewLayer.recreateDisplayList = View.class.getDeclaredField("mRecreateDisplayList");
                    } else {
                        ViewLayer.updateDisplayListIfDirtyMethod = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass()).invoke(View.class, "updateDisplayListIfDirty", new Class[0]);
                        ViewLayer.recreateDisplayList = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(View.class, "mRecreateDisplayList");
                    }
                    Method method = ViewLayer.updateDisplayListIfDirtyMethod;
                    if (method != null) {
                        method.setAccessible(true);
                    }
                    Field field = ViewLayer.recreateDisplayList;
                    if (field != null) {
                        field.setAccessible(true);
                    }
                }
                Field field2 = ViewLayer.recreateDisplayList;
                if (field2 != null) {
                    field2.setBoolean(view, true);
                }
                Method method2 = ViewLayer.updateDisplayListIfDirtyMethod;
                if (method2 != null) {
                    method2.invoke(view, null);
                }
            } catch (Throwable unused) {
                setShouldUseDispatchDraw$ui_release(true);
            }
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/platform/ViewLayer$UniqueDrawingIdApi29;", "", "()V", "getUniqueDrawingId", "", "view", "Landroid/view/View;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class UniqueDrawingIdApi29 {
        public static final UniqueDrawingIdApi29 INSTANCE = new UniqueDrawingIdApi29();

        private UniqueDrawingIdApi29() {
        }

        @q7.n
        public static final long getUniqueDrawingId(View view) {
            return view.getUniqueDrawingId();
        }
    }

    public ViewLayer(AndroidComposeView androidComposeView, DrawChildContainer drawChildContainer, r7.l<? super Canvas, t0> lVar, r7.a<t0> aVar) {
        super(androidComposeView.getContext());
        this.ownerView = androidComposeView;
        this.container = drawChildContainer;
        this.drawBlock = lVar;
        this.invalidateParentLayer = aVar;
        this.outlineResolver = new OutlineResolver(androidComposeView.getDensity());
        this.canvasHolder = new CanvasHolder();
        this.matrixCache = new LayerMatrixCache<>(getMatrix);
        this.mTransformOrigin = TransformOrigin.INSTANCE.m3841getCenterSzJe1aQ();
        this.mHasOverlappingRendering = true;
        setWillNotDraw(false);
        drawChildContainer.addView(this);
        this.layerId = View.generateViewId();
    }

    private final Path getManualClipPath() {
        if (!getClipToOutline() || this.outlineResolver.getOutlineClipSupported()) {
            return null;
        }
        return this.outlineResolver.getClipPath();
    }

    private final void resetClipBounds() {
        Rect rect;
        if (this.clipToBounds) {
            Rect rect2 = this.clipBoundsCache;
            if (rect2 == null) {
                this.clipBoundsCache = new Rect(0, 0, getWidth(), getHeight());
            } else {
                rect2.set(0, 0, getWidth(), getHeight());
            }
            rect = this.clipBoundsCache;
        } else {
            rect = null;
        }
        setClipBounds(rect);
    }

    private final void setInvalidated(boolean z) {
        if (z != this.isInvalidated) {
            this.isInvalidated = z;
            this.ownerView.notifyLayerIsDirty$ui_release(this, z);
        }
    }

    private final void updateOutlineResolver() {
        setOutlineProvider(this.outlineResolver.getOutline() != null ? OutlineProvider : null);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void destroy() {
        setInvalidated(false);
        this.ownerView.requestClearInvalidObservations();
        this.drawBlock = null;
        this.invalidateParentLayer = null;
        this.ownerView.recycle$ui_release(this);
        this.container.removeViewInLayout(this);
    }

    @Override // android.view.View
    public void dispatchDraw(android.graphics.Canvas canvas) {
        boolean z;
        CanvasHolder canvasHolder = this.canvasHolder;
        android.graphics.Canvas internalCanvas = canvasHolder.getAndroidCanvas().getInternalCanvas();
        canvasHolder.getAndroidCanvas().setInternalCanvas(canvas);
        AndroidCanvas androidCanvas = canvasHolder.getAndroidCanvas();
        if (getManualClipPath() == null && canvas.isHardwareAccelerated()) {
            z = false;
        } else {
            androidCanvas.save();
            this.outlineResolver.clipToOutline(androidCanvas);
            z = true;
        }
        r7.l<? super Canvas, t0> lVar = this.drawBlock;
        if (lVar != null) {
            lVar.invoke(androidCanvas);
        }
        if (z) {
            androidCanvas.restore();
        }
        canvasHolder.getAndroidCanvas().setInternalCanvas(internalCanvas);
        setInvalidated(false);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void drawLayer(Canvas canvas) {
        boolean z = getElevation() > 0.0f;
        this.drawnWithZ = z;
        if (z) {
            canvas.enableZ();
        }
        this.container.drawChild$ui_release(canvas, this, getDrawingTime());
        if (this.drawnWithZ) {
            canvas.disableZ();
        }
    }

    @Override // android.view.View
    public void forceLayout() {
    }

    public final float getCameraDistancePx() {
        return getCameraDistance() / getResources().getDisplayMetrics().densityDpi;
    }

    public final DrawChildContainer getContainer() {
        return this.container;
    }

    @Override // androidx.compose.ui.layout.GraphicLayerInfo
    public long getLayerId() {
        return this.layerId;
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

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return this.mHasOverlappingRendering;
    }

    @Override // android.view.View, androidx.compose.ui.node.OwnedLayer
    public void invalidate() {
        if (this.isInvalidated) {
            return;
        }
        setInvalidated(true);
        super.invalidate();
        this.ownerView.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: inverseTransform-58bKbWc */
    public void mo4890inverseTransform58bKbWc(float[] matrix) {
        float[] fArrM4971calculateInverseMatrixbWbORWo = this.matrixCache.m4971calculateInverseMatrixbWbORWo(this);
        if (fArrM4971calculateInverseMatrixbWbORWo != null) {
            androidx.compose.ui.graphics.Matrix.m3701timesAssign58bKbWc(matrix, fArrM4971calculateInverseMatrixbWbORWo);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: isInLayer-k-4lQ0M */
    public boolean mo4891isInLayerk4lQ0M(long position) {
        float fM3236getXimpl = Offset.m3236getXimpl(position);
        float fM3237getYimpl = Offset.m3237getYimpl(position);
        if (this.clipToBounds) {
            return 0.0f <= fM3236getXimpl && fM3236getXimpl < ((float) getWidth()) && 0.0f <= fM3237getYimpl && fM3237getYimpl < ((float) getHeight());
        }
        if (getClipToOutline()) {
            return this.outlineResolver.m4994isInOutlinek4lQ0M(position);
        }
        return true;
    }

    /* JADX INFO: renamed from: isInvalidated, reason: from getter */
    public final boolean getIsInvalidated() {
        return this.isInvalidated;
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void mapBounds(MutableRect rect, boolean inverse) {
        if (!inverse) {
            androidx.compose.ui.graphics.Matrix.m3692mapimpl(this.matrixCache.m4972calculateMatrixGrdbGEg(this), rect);
            return;
        }
        float[] fArrM4971calculateInverseMatrixbWbORWo = this.matrixCache.m4971calculateInverseMatrixbWbORWo(this);
        if (fArrM4971calculateInverseMatrixbWbORWo != null) {
            androidx.compose.ui.graphics.Matrix.m3692mapimpl(fArrM4971calculateInverseMatrixbWbORWo, rect);
        } else {
            rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: mapOffset-8S9VItk */
    public long mo4892mapOffset8S9VItk(long point, boolean inverse) {
        if (!inverse) {
            return androidx.compose.ui.graphics.Matrix.m3690mapMKHz9U(this.matrixCache.m4972calculateMatrixGrdbGEg(this), point);
        }
        float[] fArrM4971calculateInverseMatrixbWbORWo = this.matrixCache.m4971calculateInverseMatrixbWbORWo(this);
        return fArrM4971calculateInverseMatrixbWbORWo != null ? androidx.compose.ui.graphics.Matrix.m3690mapMKHz9U(fArrM4971calculateInverseMatrixbWbORWo, point) : Offset.INSTANCE.m3250getInfiniteF1C5BW0();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: move--gyyYBs */
    public void mo4893movegyyYBs(long position) {
        int iM5802getXimpl = IntOffset.m5802getXimpl(position);
        if (iM5802getXimpl != getLeft()) {
            offsetLeftAndRight(iM5802getXimpl - getLeft());
            this.matrixCache.invalidate();
        }
        int iM5803getYimpl = IntOffset.m5803getYimpl(position);
        if (iM5803getYimpl != getTop()) {
            offsetTopAndBottom(iM5803getYimpl - getTop());
            this.matrixCache.invalidate();
        }
    }

    @Override // android.view.View
    public void onLayout(boolean changed, int l10, int t2, int r4, int b10) {
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: resize-ozmzZPI */
    public void mo4894resizeozmzZPI(long size) {
        int iM5844getWidthimpl = IntSize.m5844getWidthimpl(size);
        int iM5843getHeightimpl = IntSize.m5843getHeightimpl(size);
        if (iM5844getWidthimpl == getWidth() && iM5843getHeightimpl == getHeight()) {
            return;
        }
        float f10 = iM5844getWidthimpl;
        setPivotX(TransformOrigin.m3836getPivotFractionXimpl(this.mTransformOrigin) * f10);
        float f11 = iM5843getHeightimpl;
        setPivotY(TransformOrigin.m3837getPivotFractionYimpl(this.mTransformOrigin) * f11);
        this.outlineResolver.m4995updateuvyYCjk(SizeKt.Size(f10, f11));
        updateOutlineResolver();
        layout(getLeft(), getTop(), getLeft() + iM5844getWidthimpl, getTop() + iM5843getHeightimpl);
        resetClipBounds();
        this.matrixCache.invalidate();
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void reuseLayer(r7.l<? super Canvas, t0> drawBlock, r7.a<t0> invalidateParentLayer) {
        this.container.addView(this);
        this.clipToBounds = false;
        this.drawnWithZ = false;
        this.mTransformOrigin = TransformOrigin.INSTANCE.m3841getCenterSzJe1aQ();
        this.drawBlock = drawBlock;
        this.invalidateParentLayer = invalidateParentLayer;
    }

    public final void setCameraDistancePx(float f10) {
        setCameraDistance(f10 * getResources().getDisplayMetrics().densityDpi);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    /* JADX INFO: renamed from: transform-58bKbWc */
    public void mo4895transform58bKbWc(float[] matrix) {
        androidx.compose.ui.graphics.Matrix.m3701timesAssign58bKbWc(matrix, this.matrixCache.m4972calculateMatrixGrdbGEg(this));
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateDisplayList() {
        if (!this.isInvalidated || shouldUseDispatchDraw) {
            return;
        }
        INSTANCE.updateDisplayList(this);
        setInvalidated(false);
    }

    @Override // androidx.compose.ui.node.OwnedLayer
    public void updateLayerProperties(ReusableGraphicsLayerScope scope, LayoutDirection layoutDirection, Density density) {
        r7.a<t0> aVar;
        int mutatedFields$ui_release = scope.getMutatedFields() | this.mutatedFields;
        if ((mutatedFields$ui_release & 4096) != 0) {
            long jMo3642getTransformOriginSzJe1aQ = scope.getTransformOrigin();
            this.mTransformOrigin = jMo3642getTransformOriginSzJe1aQ;
            setPivotX(TransformOrigin.m3836getPivotFractionXimpl(jMo3642getTransformOriginSzJe1aQ) * getWidth());
            setPivotY(TransformOrigin.m3837getPivotFractionYimpl(this.mTransformOrigin) * getHeight());
        }
        if ((mutatedFields$ui_release & 1) != 0) {
            setScaleX(scope.getScaleX());
        }
        if ((mutatedFields$ui_release & 2) != 0) {
            setScaleY(scope.getScaleY());
        }
        if ((mutatedFields$ui_release & 4) != 0) {
            setAlpha(scope.getAlpha());
        }
        if ((mutatedFields$ui_release & 8) != 0) {
            setTranslationX(scope.getTranslationX());
        }
        if ((mutatedFields$ui_release & 16) != 0) {
            setTranslationY(scope.getTranslationY());
        }
        if ((mutatedFields$ui_release & 32) != 0) {
            setElevation(scope.getShadowElevation());
        }
        if ((mutatedFields$ui_release & 1024) != 0) {
            setRotation(scope.getRotationZ());
        }
        if ((mutatedFields$ui_release & 256) != 0) {
            setRotationX(scope.getRotationX());
        }
        if ((mutatedFields$ui_release & 512) != 0) {
            setRotationY(scope.getRotationY());
        }
        if ((mutatedFields$ui_release & 2048) != 0) {
            setCameraDistancePx(scope.getCameraDistance());
        }
        boolean z = false;
        boolean z5 = getManualClipPath() != null;
        boolean z10 = scope.getClip() && scope.getShape() != RectangleShapeKt.getRectangleShape();
        if ((mutatedFields$ui_release & 24576) != 0) {
            this.clipToBounds = scope.getClip() && scope.getShape() == RectangleShapeKt.getRectangleShape();
            resetClipBounds();
            setClipToOutline(z10);
        }
        boolean zUpdate = this.outlineResolver.update(scope.getShape(), scope.getAlpha(), z10, scope.getShadowElevation(), layoutDirection, density);
        if (this.outlineResolver.getCacheIsDirty()) {
            updateOutlineResolver();
        }
        boolean z11 = getManualClipPath() != null;
        if (z5 != z11 || (z11 && zUpdate)) {
            invalidate();
        }
        if (!this.drawnWithZ && getElevation() > 0.0f && (aVar = this.invalidateParentLayer) != null) {
            aVar.invoke();
        }
        if ((mutatedFields$ui_release & Fields.MatrixAffectingFields) != 0) {
            this.matrixCache.invalidate();
        }
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 28) {
            if ((mutatedFields$ui_release & 64) != 0) {
                ViewLayerVerificationHelper28.INSTANCE.setOutlineAmbientShadowColor(this, ColorKt.m3526toArgb8_81llA(scope.getAmbientShadowColor()));
            }
            if ((mutatedFields$ui_release & 128) != 0) {
                ViewLayerVerificationHelper28.INSTANCE.setOutlineSpotShadowColor(this, ColorKt.m3526toArgb8_81llA(scope.getSpotShadowColor()));
            }
        }
        if (i10 >= 31 && (131072 & mutatedFields$ui_release) != 0) {
            ViewLayerVerificationHelper31.INSTANCE.setRenderEffect(this, scope.getRenderEffect());
        }
        if ((32768 & mutatedFields$ui_release) != 0) {
            int iMo3639getCompositingStrategyNrFUSI = scope.getCompositingStrategy();
            CompositingStrategy.Companion companion = CompositingStrategy.INSTANCE;
            if (CompositingStrategy.m3557equalsimpl0(iMo3639getCompositingStrategyNrFUSI, companion.m3563getOffscreenNrFUSI())) {
                setLayerType(2, null);
            } else if (CompositingStrategy.m3557equalsimpl0(iMo3639getCompositingStrategyNrFUSI, companion.m3562getModulateAlphaNrFUSI())) {
                setLayerType(0, null);
                this.mHasOverlappingRendering = z;
            } else {
                setLayerType(0, null);
            }
            z = true;
            this.mHasOverlappingRendering = z;
        }
        this.mutatedFields = scope.getMutatedFields();
    }
}
