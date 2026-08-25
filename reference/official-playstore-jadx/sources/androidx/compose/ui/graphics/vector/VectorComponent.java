package androidx.compose.ui.graphics.vector;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapConfig;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.IntSizeKt;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\bJ#\u0010\u000e\u001a\u00020\u0006*\u00020\t2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u000e\u001a\u00020\u0006*\u00020\tH\u0016¢\u0006\u0004\b\u000e\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\"\u0010\u0017\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u0013\"\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R(\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\"8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R/\u00100\u001a\u0004\u0018\u00010\f2\b\u0010)\u001a\u0004\u0018\u00010\f8@@@X\u0080\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0018\u00101\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R1\u00109\u001a\u0002032\u0006\u0010)\u001a\u0002038@@@X\u0080\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b4\u0010+\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u0010:\u001a\u0002038\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b:\u0010;R\u0016\u0010<\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010=R\u0016\u0010>\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b>\u0010=R%\u0010A\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00060?¢\u0006\u0002\b@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010BR\u001a\u0010F\u001a\u00020C8@X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bD\u0010E\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006G"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorComponent;", "Landroidx/compose/ui/graphics/vector/VNode;", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "root", "<init>", "(Landroidx/compose/ui/graphics/vector/GroupComponent;)V", "Lx6/t0;", "doInvalidate", "()V", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "alpha", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "draw", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FLandroidx/compose/ui/graphics/ColorFilter;)V", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;)V", "", "toString", "()Ljava/lang/String;", "Landroidx/compose/ui/graphics/vector/GroupComponent;", "getRoot", "()Landroidx/compose/ui/graphics/vector/GroupComponent;", ContentDisposition.Parameters.Name, "Ljava/lang/String;", "getName", "setName", "(Ljava/lang/String;)V", "", "isDirty", "Z", "Landroidx/compose/ui/graphics/vector/DrawCache;", "cacheDrawScope", "Landroidx/compose/ui/graphics/vector/DrawCache;", "Lkotlin/Function0;", "invalidateCallback", "Lr7/a;", "getInvalidateCallback$ui_release", "()Lr7/a;", "setInvalidateCallback$ui_release", "(Lr7/a;)V", "<set-?>", "intrinsicColorFilter$delegate", "Landroidx/compose/runtime/MutableState;", "getIntrinsicColorFilter$ui_release", "()Landroidx/compose/ui/graphics/ColorFilter;", "setIntrinsicColorFilter$ui_release", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "intrinsicColorFilter", "tintFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "Landroidx/compose/ui/geometry/Size;", "viewportSize$delegate", "getViewportSize-NH-jbRc$ui_release", "()J", "setViewportSize-uvyYCjk$ui_release", "(J)V", "viewportSize", "previousDrawSize", "J", "rootScaleX", "F", "rootScaleY", "Lkotlin/Function1;", "Lx6/n;", "drawVectorBlock", "Lr7/l;", "Landroidx/compose/ui/graphics/ImageBitmapConfig;", "getCacheBitmapConfig-_sVssgQ$ui_release", "()I", "cacheBitmapConfig", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class VectorComponent extends VNode {
    public static final int $stable = 8;
    private final DrawCache cacheDrawScope;
    private final l<DrawScope, t0> drawVectorBlock;

    /* JADX INFO: renamed from: intrinsicColorFilter$delegate, reason: from kotlin metadata */
    private final MutableState intrinsicColorFilter;
    private r7.a<t0> invalidateCallback;
    private boolean isDirty;
    private String name;
    private long previousDrawSize;
    private final GroupComponent root;
    private float rootScaleX;
    private float rootScaleY;
    private ColorFilter tintFilter;

    /* JADX INFO: renamed from: viewportSize$delegate, reason: from kotlin metadata */
    private final MutableState viewportSize;

    /* JADX INFO: renamed from: androidx.compose.ui.graphics.vector.VectorComponent$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VNode;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/vector/VNode;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<VNode, t0> {
        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((VNode) obj);
            return t0.f22605a;
        }

        public final void invoke(VNode vNode) {
            VectorComponent.this.doInvalidate();
        }
    }

    public VectorComponent(GroupComponent groupComponent) {
        super(null);
        this.root = groupComponent;
        groupComponent.setInvalidateListener$ui_release(new AnonymousClass1());
        this.name = "";
        this.isDirty = true;
        this.cacheDrawScope = new DrawCache();
        this.invalidateCallback = VectorComponent$invalidateCallback$1.INSTANCE;
        this.intrinsicColorFilter = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        Size.Companion companion = Size.INSTANCE;
        this.viewportSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m3293boximpl(companion.m3314getZeroNHjbRc()), null, 2, null);
        this.previousDrawSize = companion.m3313getUnspecifiedNHjbRc();
        this.rootScaleX = 1.0f;
        this.rootScaleY = 1.0f;
        this.drawVectorBlock = new VectorComponent$drawVectorBlock$1(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doInvalidate() {
        this.isDirty = true;
        this.invalidateCallback.invoke();
    }

    public final void draw(DrawScope drawScope, float f10, ColorFilter colorFilter) {
        DrawScope drawScope2;
        int iM3673getAlpha8_sVssgQ = (this.root.getIsTintable() && this.root.getTintColor() != Color.INSTANCE.m3508getUnspecified0d7_KjU() && VectorKt.tintableWithAlphaMask(getIntrinsicColorFilter$ui_release()) && VectorKt.tintableWithAlphaMask(colorFilter)) ? ImageBitmapConfig.INSTANCE.m3673getAlpha8_sVssgQ() : ImageBitmapConfig.INSTANCE.m3674getArgb8888_sVssgQ();
        if (!this.isDirty && Size.m3301equalsimpl0(this.previousDrawSize, drawScope.mo3916getSizeNHjbRc()) && ImageBitmapConfig.m3669equalsimpl0(iM3673getAlpha8_sVssgQ, m4034getCacheBitmapConfig_sVssgQ$ui_release())) {
            drawScope2 = drawScope;
        } else {
            this.tintFilter = ImageBitmapConfig.m3669equalsimpl0(iM3673getAlpha8_sVssgQ, ImageBitmapConfig.INSTANCE.m3673getAlpha8_sVssgQ()) ? ColorFilter.Companion.m3513tintxETnrds$default(ColorFilter.INSTANCE, this.root.getTintColor(), 0, 2, null) : null;
            this.rootScaleX = Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()) / Size.m3305getWidthimpl(m4035getViewportSizeNHjbRc$ui_release());
            this.rootScaleY = Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc()) / Size.m3302getHeightimpl(m4035getViewportSizeNHjbRc$ui_release());
            drawScope2 = drawScope;
            this.cacheDrawScope.m4017drawCachedImageFqjB98A(iM3673getAlpha8_sVssgQ, IntSizeKt.IntSize((int) Math.ceil(Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc())), (int) Math.ceil(Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc()))), drawScope2, drawScope.getLayoutDirection(), this.drawVectorBlock);
            this.isDirty = false;
            this.previousDrawSize = drawScope2.mo3916getSizeNHjbRc();
        }
        if (colorFilter == null) {
            colorFilter = getIntrinsicColorFilter$ui_release() != null ? getIntrinsicColorFilter$ui_release() : this.tintFilter;
        }
        this.cacheDrawScope.drawInto(drawScope2, f10, colorFilter);
    }

    /* JADX INFO: renamed from: getCacheBitmapConfig-_sVssgQ$ui_release, reason: not valid java name */
    public final int m4034getCacheBitmapConfig_sVssgQ$ui_release() {
        ImageBitmap mCachedImage = this.cacheDrawScope.getMCachedImage();
        return mCachedImage != null ? mCachedImage.mo3345getConfig_sVssgQ() : ImageBitmapConfig.INSTANCE.m3674getArgb8888_sVssgQ();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final ColorFilter getIntrinsicColorFilter$ui_release() {
        return (ColorFilter) this.intrinsicColorFilter.getValue();
    }

    public final r7.a<t0> getInvalidateCallback$ui_release() {
        return this.invalidateCallback;
    }

    public final String getName() {
        return this.name;
    }

    public final GroupComponent getRoot() {
        return this.root;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getViewportSize-NH-jbRc$ui_release, reason: not valid java name */
    public final long m4035getViewportSizeNHjbRc$ui_release() {
        return ((Size) this.viewportSize.getValue()).getPackedValue();
    }

    public final void setIntrinsicColorFilter$ui_release(ColorFilter colorFilter) {
        this.intrinsicColorFilter.setValue(colorFilter);
    }

    public final void setInvalidateCallback$ui_release(r7.a<t0> aVar) {
        this.invalidateCallback = aVar;
    }

    public final void setName(String str) {
        this.name = str;
    }

    /* JADX INFO: renamed from: setViewportSize-uvyYCjk$ui_release, reason: not valid java name */
    public final void m4036setViewportSizeuvyYCjk$ui_release(long j10) {
        this.viewportSize.setValue(Size.m3293boximpl(j10));
    }

    public String toString() {
        return "Params: \tname: " + this.name + "\n\tviewportWidth: " + Size.m3305getWidthimpl(m4035getViewportSizeNHjbRc$ui_release()) + "\n\tviewportHeight: " + Size.m3302getHeightimpl(m4035getViewportSizeNHjbRc$ui_release()) + "\n";
    }

    @Override // androidx.compose.ui.graphics.vector.VNode
    public void draw(DrawScope drawScope) {
        draw(drawScope, 1.0f, null);
    }
}
