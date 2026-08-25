package androidx.compose.foundation;

import android.view.View;
import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B\u009b\u0001\u0012\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n\u0012\u001b\b\u0002\u0010\f\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\n\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\r\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u0092\u0001\u0010\u001f\u001a\u00020\u000e2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n2\u0019\u0010\f\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\n2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00122\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00072\u0006\u0010\u001a\u001a\u00020\u0019ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010 \u001a\u00020\u000eH\u0016¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\"\u0010!J\u000f\u0010#\u001a\u00020\u000eH\u0016¢\u0006\u0004\b#\u0010!J\u0013\u0010%\u001a\u00020\u000e*\u00020$H\u0016¢\u0006\u0004\b%\u0010&J\u0017\u0010)\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020'H\u0016¢\u0006\u0004\b)\u0010*J\u0013\u0010,\u001a\u00020\u000e*\u00020+H\u0016¢\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\u000eH\u0002¢\u0006\u0004\b.\u0010!J\u000f\u0010/\u001a\u00020\u000eH\u0002¢\u0006\u0004\b/\u0010!J\u000f\u00100\u001a\u00020\u000eH\u0002¢\u0006\u0004\b0\u0010!R3\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R5\u0010\f\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u00101\u001a\u0004\b6\u00103\"\u0004\b7\u00105R0\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u00101\u001a\u0004\b8\u00103\"\u0004\b9\u00105R\"\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010:\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\"\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR(\u0010\u0014\u001a\u00020\r8\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0014\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR(\u0010\u0016\u001a\u00020\u00158\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0016\u0010:\u001a\u0004\bI\u0010<\"\u0004\bJ\u0010>R(\u0010\u0017\u001a\u00020\u00158\u0006@\u0006X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b\u0017\u0010:\u001a\u0004\bK\u0010<\"\u0004\bL\u0010>R\"\u0010\u0018\u001a\u00020\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010?\u001a\u0004\bM\u0010A\"\u0004\bN\u0010CR\"\u0010\u001a\u001a\u00020\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u0018\u0010U\u001a\u0004\u0018\u00010T8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u0018\u0010W\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010XR\u0018\u0010Z\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010[R1\u0010a\u001a\u00020\t2\u0006\u0010\\\u001a\u00020\t8B@BX\u0082\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\b]\u0010^\u001a\u0004\b_\u0010F\"\u0004\b`\u0010HR\u001c\u0010b\u001a\u00020\t8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bb\u0010DR\u001e\u0010d\u001a\u0004\u0018\u00010c8\u0002@\u0002X\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\bd\u0010e\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006f"}, d2 = {"Landroidx/compose/foundation/MagnifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/n;", "sourceCenter", "magnifierCenter", "Landroidx/compose/ui/unit/DpSize;", "Lx6/t0;", "onSizeChanged", "", "zoom", "", "useTextDefault", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/Dp;", "cornerRadius", "elevation", "clippingEnabled", "Landroidx/compose/foundation/PlatformMagnifierFactory;", "platformMagnifierFactory", "<init>", "(Lr7/l;Lr7/l;Lr7/l;FZJFFZLandroidx/compose/foundation/PlatformMagnifierFactory;Lkotlin/jvm/internal/h;)V", "update-5F03MCQ", "(Lr7/l;Lr7/l;FZJFFZLr7/l;Landroidx/compose/foundation/PlatformMagnifierFactory;)V", "update", "onAttach", "()V", "onDetach", "onObservedReadsChanged", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "draw", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "onGloballyPositioned", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "applySemantics", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "recreateMagnifier", "updateMagnifier", "updateSizeIfNecessary", "Lr7/l;", "getSourceCenter", "()Lr7/l;", "setSourceCenter", "(Lr7/l;)V", "getMagnifierCenter", "setMagnifierCenter", "getOnSizeChanged", "setOnSizeChanged", "F", "getZoom", "()F", "setZoom", "(F)V", "Z", "getUseTextDefault", "()Z", "setUseTextDefault", "(Z)V", "J", "getSize-MYxV2XQ", "()J", "setSize-EaSLcWc", "(J)V", "getCornerRadius-D9Ej5fM", "setCornerRadius-0680j_4", "getElevation-D9Ej5fM", "setElevation-0680j_4", "getClippingEnabled", "setClippingEnabled", "Landroidx/compose/foundation/PlatformMagnifierFactory;", "getPlatformMagnifierFactory", "()Landroidx/compose/foundation/PlatformMagnifierFactory;", "setPlatformMagnifierFactory", "(Landroidx/compose/foundation/PlatformMagnifierFactory;)V", "Landroid/view/View;", "view", "Landroid/view/View;", "density", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/foundation/PlatformMagnifier;", "magnifier", "Landroidx/compose/foundation/PlatformMagnifier;", "<set-?>", "anchorPositionInRoot$delegate", "Landroidx/compose/runtime/MutableState;", "getAnchorPositionInRoot-F1C5BW0", "setAnchorPositionInRoot-k-4lQ0M", "anchorPositionInRoot", "sourceCenterInRoot", "Landroidx/compose/ui/unit/IntSize;", "previousSize", "Landroidx/compose/ui/unit/IntSize;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MagnifierNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, GlobalPositionAwareModifierNode, DrawModifierNode, SemanticsModifierNode, ObserverModifierNode {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: anchorPositionInRoot$delegate, reason: from kotlin metadata */
    private final MutableState anchorPositionInRoot;
    private boolean clippingEnabled;
    private float cornerRadius;
    private Density density;
    private float elevation;
    private PlatformMagnifier magnifier;
    private l<? super Density, Offset> magnifierCenter;
    private l<? super DpSize, t0> onSizeChanged;
    private PlatformMagnifierFactory platformMagnifierFactory;
    private IntSize previousSize;
    private long size;
    private l<? super Density, Offset> sourceCenter;
    private long sourceCenterInRoot;
    private boolean useTextDefault;
    private View view;
    private float zoom;

    /* JADX INFO: renamed from: androidx.compose.foundation.MagnifierNode$applySemantics$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/geometry/Offset;", "invoke-F1C5BW0", "()J"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements r7.a<Offset> {
        public AnonymousClass1() {
            super(0);
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            return Offset.m3225boximpl(m248invokeF1C5BW0());
        }

        /* JADX INFO: renamed from: invoke-F1C5BW0, reason: not valid java name */
        public final long m248invokeF1C5BW0() {
            return MagnifierNode.this.sourceCenterInRoot;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.MagnifierNode$draw$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.MagnifierNode$draw$1", f = "Magnifier.android.kt", l = {447}, m = "invokeSuspend")
    public static final class C02921 extends j implements p<k0, d<? super t0>, Object> {
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.MagnifierNode$draw$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C00111 extends r implements l<Long, t0> {
            public static final C00111 INSTANCE = new C00111();

            public C00111() {
                super(1);
            }

            public final void invoke(long j10) {
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke(((Number) obj).longValue());
                return t0.f22605a;
            }
        }

        public C02921(d<? super C02921> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return MagnifierNode.this.new C02921(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                C00111 c00111 = C00111.INSTANCE;
                this.label = 1;
                Object objWithFrameMillis = MonotonicFrameClockKt.withFrameMillis(c00111, this);
                e7.a aVar = e7.a.f15033i;
                if (objWithFrameMillis == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            PlatformMagnifier platformMagnifier = MagnifierNode.this.magnifier;
            if (platformMagnifier != null) {
                platformMagnifier.updateContent();
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((C02921) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.MagnifierNode$onObservedReadsChanged$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C02931 extends r implements r7.a<t0> {
        public C02931() {
            super(0);
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m249invoke();
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m249invoke() {
            View view = MagnifierNode.this.view;
            View view2 = (View) CompositionLocalConsumerModifierNodeKt.currentValueOf(MagnifierNode.this, AndroidCompositionLocals_androidKt.getLocalView());
            MagnifierNode.this.view = view2;
            Density density = MagnifierNode.this.density;
            Density density2 = (Density) CompositionLocalConsumerModifierNodeKt.currentValueOf(MagnifierNode.this, CompositionLocalsKt.getLocalDensity());
            MagnifierNode.this.density = density2;
            if (MagnifierNode.this.magnifier == null || !kotlin.jvm.internal.p.a(view2, view) || !kotlin.jvm.internal.p.a(density2, density)) {
                MagnifierNode.this.recreateMagnifier();
            }
            MagnifierNode.this.updateMagnifier();
        }
    }

    public /* synthetic */ MagnifierNode(l lVar, l lVar2, l lVar3, float f10, boolean z, long j10, float f11, float f12, boolean z5, PlatformMagnifierFactory platformMagnifierFactory, h hVar) {
        this(lVar, lVar2, lVar3, f10, z, j10, f11, f12, z5, platformMagnifierFactory);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: getAnchorPositionInRoot-F1C5BW0, reason: not valid java name */
    private final long m239getAnchorPositionInRootF1C5BW0() {
        return ((Offset) this.anchorPositionInRoot.getValue()).getPackedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recreateMagnifier() {
        Density density;
        PlatformMagnifier platformMagnifier = this.magnifier;
        if (platformMagnifier != null) {
            platformMagnifier.dismiss();
        }
        View view = this.view;
        if (view == null || (density = this.density) == null) {
            return;
        }
        this.magnifier = this.platformMagnifierFactory.mo275createnHHXs2Y(view, this.useTextDefault, this.size, this.cornerRadius, this.elevation, this.clippingEnabled, density, this.zoom);
        updateSizeIfNecessary();
    }

    /* JADX INFO: renamed from: setAnchorPositionInRoot-k-4lQ0M, reason: not valid java name */
    private final void m240setAnchorPositionInRootk4lQ0M(long j10) {
        this.anchorPositionInRoot.setValue(Offset.m3225boximpl(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateMagnifier() {
        /*
            r6 = this;
            androidx.compose.foundation.PlatformMagnifier r0 = r6.magnifier
            if (r0 != 0) goto L5
            goto L9
        L5:
            androidx.compose.ui.unit.Density r1 = r6.density
            if (r1 != 0) goto La
        L9:
            return
        La:
            r7.l<? super androidx.compose.ui.unit.Density, androidx.compose.ui.geometry.Offset> r2 = r6.sourceCenter
            java.lang.Object r2 = r2.invoke(r1)
            androidx.compose.ui.geometry.Offset r2 = (androidx.compose.ui.geometry.Offset) r2
            long r2 = r2.getPackedValue()
            long r4 = r6.m239getAnchorPositionInRootF1C5BW0()
            boolean r4 = androidx.compose.ui.geometry.OffsetKt.m3255isSpecifiedk4lQ0M(r4)
            if (r4 == 0) goto L2f
            boolean r4 = androidx.compose.ui.geometry.OffsetKt.m3255isSpecifiedk4lQ0M(r2)
            if (r4 == 0) goto L2f
            long r4 = r6.m239getAnchorPositionInRootF1C5BW0()
            long r2 = androidx.compose.ui.geometry.Offset.m3241plusMKHz9U(r4, r2)
            goto L35
        L2f:
            androidx.compose.ui.geometry.Offset$Companion r2 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r2 = r2.m3251getUnspecifiedF1C5BW0()
        L35:
            r6.sourceCenterInRoot = r2
            boolean r2 = androidx.compose.ui.geometry.OffsetKt.m3255isSpecifiedk4lQ0M(r2)
            if (r2 == 0) goto L7d
            r7.l<? super androidx.compose.ui.unit.Density, androidx.compose.ui.geometry.Offset> r2 = r6.magnifierCenter
            if (r2 == 0) goto L6b
            java.lang.Object r1 = r2.invoke(r1)
            androidx.compose.ui.geometry.Offset r1 = (androidx.compose.ui.geometry.Offset) r1
            long r1 = r1.getPackedValue()
            androidx.compose.ui.geometry.Offset r1 = androidx.compose.ui.geometry.Offset.m3225boximpl(r1)
            long r2 = r1.getPackedValue()
            boolean r2 = androidx.compose.ui.geometry.OffsetKt.m3255isSpecifiedk4lQ0M(r2)
            if (r2 == 0) goto L5a
            goto L5b
        L5a:
            r1 = 0
        L5b:
            if (r1 == 0) goto L6b
            long r1 = r1.getPackedValue()
            long r3 = r6.m239getAnchorPositionInRootF1C5BW0()
            long r1 = androidx.compose.ui.geometry.Offset.m3241plusMKHz9U(r3, r1)
        L69:
            r3 = r1
            goto L72
        L6b:
            androidx.compose.ui.geometry.Offset$Companion r1 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r1 = r1.m3251getUnspecifiedF1C5BW0()
            goto L69
        L72:
            long r1 = r6.sourceCenterInRoot
            float r5 = r6.zoom
            r0.mo274updateWko1d7g(r1, r3, r5)
            r6.updateSizeIfNecessary()
            return
        L7d:
            r0.dismiss()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.MagnifierNode.updateMagnifier():void");
    }

    private final void updateSizeIfNecessary() {
        Density density;
        PlatformMagnifier platformMagnifier = this.magnifier;
        if (platformMagnifier == null || (density = this.density) == null || IntSize.m5841equalsimpl(platformMagnifier.mo273getSizeYbymL2g(), this.previousSize)) {
            return;
        }
        l<? super DpSize, t0> lVar = this.onSizeChanged;
        if (lVar != null) {
            lVar.invoke(DpSize.m5764boximpl(density.mo283toDpSizekrfVVM(IntSizeKt.m5854toSizeozmzZPI(platformMagnifier.mo273getSizeYbymL2g()))));
        }
        this.previousSize = IntSize.m5836boximpl(platformMagnifier.mo273getSizeYbymL2g());
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        semanticsPropertyReceiver.set(Magnifier_androidKt.getMagnifierPositionInRoot(), new AnonymousClass1());
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        m0.p(getCoroutineScope(), null, 0, new C02921(null), 3);
    }

    public final boolean getClippingEnabled() {
        return this.clippingEnabled;
    }

    /* JADX INFO: renamed from: getCornerRadius-D9Ej5fM, reason: not valid java name and from getter */
    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: not valid java name and from getter */
    public final float getElevation() {
        return this.elevation;
    }

    public final l<Density, Offset> getMagnifierCenter() {
        return this.magnifierCenter;
    }

    public final l<DpSize, t0> getOnSizeChanged() {
        return this.onSizeChanged;
    }

    public final PlatformMagnifierFactory getPlatformMagnifierFactory() {
        return this.platformMagnifierFactory;
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    /* JADX INFO: renamed from: getShouldClearDescendantSemantics */
    public final /* synthetic */ boolean getIsClearingSemantics() {
        return androidx.compose.ui.node.h.a(this);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final /* synthetic */ boolean getShouldMergeDescendantSemantics() {
        return androidx.compose.ui.node.h.b(this);
    }

    /* JADX INFO: renamed from: getSize-MYxV2XQ, reason: not valid java name and from getter */
    public final long getSize() {
        return this.size;
    }

    public final l<Density, Offset> getSourceCenter() {
        return this.sourceCenter;
    }

    public final boolean getUseTextDefault() {
        return this.useTextDefault;
    }

    public final float getZoom() {
        return this.zoom;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        onObservedReadsChanged();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        PlatformMagnifier platformMagnifier = this.magnifier;
        if (platformMagnifier != null) {
            platformMagnifier.dismiss();
        }
        this.magnifier = null;
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        m240setAnchorPositionInRootk4lQ0M(LayoutCoordinatesKt.positionInRoot(coordinates));
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final /* synthetic */ void onMeasureResultChanged() {
        androidx.compose.ui.node.b.a(this);
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        ObserverModifierNodeKt.observeReads(this, new C02931());
    }

    public final void setClippingEnabled(boolean z) {
        this.clippingEnabled = z;
    }

    /* JADX INFO: renamed from: setCornerRadius-0680j_4, reason: not valid java name */
    public final void m244setCornerRadius0680j_4(float f10) {
        this.cornerRadius = f10;
    }

    /* JADX INFO: renamed from: setElevation-0680j_4, reason: not valid java name */
    public final void m245setElevation0680j_4(float f10) {
        this.elevation = f10;
    }

    public final void setMagnifierCenter(l<? super Density, Offset> lVar) {
        this.magnifierCenter = lVar;
    }

    public final void setOnSizeChanged(l<? super DpSize, t0> lVar) {
        this.onSizeChanged = lVar;
    }

    public final void setPlatformMagnifierFactory(PlatformMagnifierFactory platformMagnifierFactory) {
        this.platformMagnifierFactory = platformMagnifierFactory;
    }

    /* JADX INFO: renamed from: setSize-EaSLcWc, reason: not valid java name */
    public final void m246setSizeEaSLcWc(long j10) {
        this.size = j10;
    }

    public final void setSourceCenter(l<? super Density, Offset> lVar) {
        this.sourceCenter = lVar;
    }

    public final void setUseTextDefault(boolean z) {
        this.useTextDefault = z;
    }

    public final void setZoom(float f10) {
        this.zoom = f10;
    }

    /* JADX INFO: renamed from: update-5F03MCQ, reason: not valid java name */
    public final void m247update5F03MCQ(l<? super Density, Offset> sourceCenter, l<? super Density, Offset> magnifierCenter, float zoom, boolean useTextDefault, long size, float cornerRadius, float elevation, boolean clippingEnabled, l<? super DpSize, t0> onSizeChanged, PlatformMagnifierFactory platformMagnifierFactory) {
        float f10 = this.zoom;
        long j10 = this.size;
        float f11 = this.cornerRadius;
        float f12 = this.elevation;
        boolean z = this.clippingEnabled;
        PlatformMagnifierFactory platformMagnifierFactory2 = this.platformMagnifierFactory;
        this.sourceCenter = sourceCenter;
        this.magnifierCenter = magnifierCenter;
        this.zoom = zoom;
        this.useTextDefault = useTextDefault;
        this.size = size;
        this.cornerRadius = cornerRadius;
        this.elevation = elevation;
        this.clippingEnabled = clippingEnabled;
        this.onSizeChanged = onSizeChanged;
        this.platformMagnifierFactory = platformMagnifierFactory;
        if (this.magnifier == null || ((zoom != f10 && !platformMagnifierFactory.getCanUpdateZoom()) || !DpSize.m5773equalsimpl0(size, j10) || !Dp.m5683equalsimpl0(cornerRadius, f11) || !Dp.m5683equalsimpl0(elevation, f12) || clippingEnabled != z || !kotlin.jvm.internal.p.a(platformMagnifierFactory, platformMagnifierFactory2))) {
            recreateMagnifier();
        }
        updateMagnifier();
    }

    public /* synthetic */ MagnifierNode(l lVar, l lVar2, l lVar3, float f10, boolean z, long j10, float f11, float f12, boolean z5, PlatformMagnifierFactory platformMagnifierFactory, int i10, h hVar) {
        this(lVar, (i10 & 2) != 0 ? null : lVar2, (i10 & 4) != 0 ? null : lVar3, (i10 & 8) != 0 ? Float.NaN : f10, (i10 & 16) != 0 ? false : z, (i10 & 32) != 0 ? DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ() : j10, (i10 & 64) != 0 ? Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM() : f11, (i10 & 128) != 0 ? Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM() : f12, (i10 & 256) != 0 ? true : z5, (i10 & 512) != 0 ? PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform() : platformMagnifierFactory, null);
    }

    private MagnifierNode(l<? super Density, Offset> lVar, l<? super Density, Offset> lVar2, l<? super DpSize, t0> lVar3, float f10, boolean z, long j10, float f11, float f12, boolean z5, PlatformMagnifierFactory platformMagnifierFactory) {
        this.sourceCenter = lVar;
        this.magnifierCenter = lVar2;
        this.onSizeChanged = lVar3;
        this.zoom = f10;
        this.useTextDefault = z;
        this.size = j10;
        this.cornerRadius = f11;
        this.elevation = f12;
        this.clippingEnabled = z5;
        this.platformMagnifierFactory = platformMagnifierFactory;
        Offset.Companion companion = Offset.INSTANCE;
        this.anchorPositionInRoot = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3225boximpl(companion.m3251getUnspecifiedF1C5BW0()), null, 2, null);
        this.sourceCenterInRoot = companion.m3251getUnspecifiedF1C5BW0();
    }
}
