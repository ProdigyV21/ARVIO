package androidx.compose.foundation.gestures;

import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.MotionDurationScale;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.modifier.ModifierLocalKt;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import d7.j;
import io.ktor.http.ContentDisposition;
import ka.k0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u009b\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0004*\u0001;\u001aO\u0010\f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0004\b\f\u0010\r\u001ac\u0010\f\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007¢\u0006\u0004\b\f\u0010\u0012\u001a\u0014\u0010\u0015\u001a\u00020\u0014*\u00020\u0013H\u0082@¢\u0006\u0004\b\u0015\u0010\u0016\" \u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a\"J\u0010%\u001a8\b\u0001\u0012\u0004\u0012\u00020\u001c\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!\u0012\u0006\u0012\u0004\u0018\u00010#0\u001b¢\u0006\u0002\b$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&\"\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)\" \u0010+\u001a\b\u0012\u0004\u0012\u00020\u00050*8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u001a\u0010/\u001a\u00020\b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0014\u00104\u001a\u0002038\u0002X\u0082T¢\u0006\u0006\n\u0004\b4\u00105\"\u001a\u00107\u001a\u0002068\u0000X\u0080\u0004¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=¨\u0006>"}, d2 = {"Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/gestures/ScrollableState;", "state", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "", "enabled", "reverseDirection", "Landroidx/compose/foundation/gestures/FlingBehavior;", "flingBehavior", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "scrollable", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/gestures/Orientation;ZZLandroidx/compose/foundation/gestures/FlingBehavior;Landroidx/compose/foundation/interaction/MutableInteractionSource;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/foundation/OverscrollEffect;", "overscrollEffect", "Landroidx/compose/foundation/gestures/BringIntoViewSpec;", "bringIntoViewSpec", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/gestures/ScrollableState;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/OverscrollEffect;ZZLandroidx/compose/foundation/gestures/FlingBehavior;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/gestures/BringIntoViewSpec;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Landroidx/compose/ui/input/pointer/PointerEvent;", "awaitScrollEvent", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Ld7/d;)Ljava/lang/Object;", "Lkotlin/Function1;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "CanDragCalculation", "Lr7/l;", "Lkotlin/Function3;", "Lka/k0;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/y;", ContentDisposition.Parameters.Name, "startedPosition", "Ld7/d;", "Lx6/t0;", "", "Lx6/n;", "NoOpOnDragStarted", "Lr7/q;", "Landroidx/compose/foundation/gestures/ScrollScope;", "NoOpScrollScope", "Landroidx/compose/foundation/gestures/ScrollScope;", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "ModifierLocalScrollableContainer", "Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "getModifierLocalScrollableContainer", "()Landroidx/compose/ui/modifier/ProvidableModifierLocal;", "NoOpFlingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "getNoOpFlingBehavior", "()Landroidx/compose/foundation/gestures/FlingBehavior;", "", "DefaultScrollMotionDurationScaleFactor", "F", "Landroidx/compose/ui/MotionDurationScale;", "DefaultScrollMotionDurationScale", "Landroidx/compose/ui/MotionDurationScale;", "getDefaultScrollMotionDurationScale", "()Landroidx/compose/ui/MotionDurationScale;", "androidx/compose/foundation/gestures/ScrollableKt$UnityDensity$1", "UnityDensity", "Landroidx/compose/foundation/gestures/ScrollableKt$UnityDensity$1;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScrollableKt {
    private static final float DefaultScrollMotionDurationScaleFactor = 1.0f;
    private static final l<PointerInputChange, Boolean> CanDragCalculation = ScrollableKt$CanDragCalculation$1.INSTANCE;
    private static final q<k0, Offset, d7.d<? super t0>, Object> NoOpOnDragStarted = new ScrollableKt$NoOpOnDragStarted$1(null);
    private static final ScrollScope NoOpScrollScope = new ScrollScope() { // from class: androidx.compose.foundation.gestures.ScrollableKt$NoOpScrollScope$1
        @Override // androidx.compose.foundation.gestures.ScrollScope
        public float scrollBy(float pixels) {
            return pixels;
        }
    };
    private static final ProvidableModifierLocal<Boolean> ModifierLocalScrollableContainer = ModifierLocalKt.modifierLocalOf(ScrollableKt$ModifierLocalScrollableContainer$1.INSTANCE);
    private static final FlingBehavior NoOpFlingBehavior = new FlingBehavior() { // from class: androidx.compose.foundation.gestures.ScrollableKt$NoOpFlingBehavior$1
        @Override // androidx.compose.foundation.gestures.FlingBehavior
        public Object performFling(ScrollScope scrollScope, float f10, d7.d<? super Float> dVar) {
            return new Float(0.0f);
        }
    };
    private static final MotionDurationScale DefaultScrollMotionDurationScale = new MotionDurationScale() { // from class: androidx.compose.foundation.gestures.ScrollableKt$DefaultScrollMotionDurationScale$1
        @Override // androidx.compose.ui.MotionDurationScale, d7.j
        public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
            return (R) MotionDurationScale.DefaultImpls.fold(this, r4, pVar);
        }

        @Override // androidx.compose.ui.MotionDurationScale, d7.j
        public <E extends j.a> E get(j.b<E> bVar) {
            return (E) MotionDurationScale.DefaultImpls.get(this, bVar);
        }

        @Override // androidx.compose.ui.MotionDurationScale, d7.j.a
        public final /* synthetic */ j.b getKey() {
            return androidx.compose.ui.c.a(this);
        }

        @Override // androidx.compose.ui.MotionDurationScale
        public float getScaleFactor() {
            return 1.0f;
        }

        @Override // androidx.compose.ui.MotionDurationScale, d7.j
        public j minusKey(j.b<?> bVar) {
            return MotionDurationScale.DefaultImpls.minusKey(this, bVar);
        }

        @Override // androidx.compose.ui.MotionDurationScale, d7.j
        public j plus(j jVar) {
            return MotionDurationScale.DefaultImpls.plus(this, jVar);
        }
    };
    private static final ScrollableKt$UnityDensity$1 UnityDensity = new Density() { // from class: androidx.compose.foundation.gestures.ScrollableKt$UnityDensity$1
        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return 1.0f;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public float getFontScale() {
            return 1.0f;
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
    };

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ScrollableKt$awaitScrollEvent$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.foundation.gestures.ScrollableKt", f = "Scrollable.kt", l = {701}, m = "awaitScrollEvent")
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ScrollableKt.awaitScrollEvent(null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0041 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0054 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003f -> B:18:0x0042). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitScrollEvent(androidx.compose.ui.input.pointer.AwaitPointerEventScope r4, d7.d<? super androidx.compose.ui.input.pointer.PointerEvent> r5) {
        /*
            boolean r0 = r5 instanceof androidx.compose.foundation.gestures.ScrollableKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            androidx.compose.foundation.gestures.ScrollableKt$awaitScrollEvent$1 r0 = (androidx.compose.foundation.gestures.ScrollableKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.ScrollableKt$awaitScrollEvent$1 r0 = new androidx.compose.foundation.gestures.ScrollableKt$awaitScrollEvent$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r4 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r4 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r4
            k2.c.G(r5)
            goto L42
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            k2.c.G(r5)
        L34:
            r0.L$0 = r4
            r0.label = r2
            r5 = 0
            java.lang.Object r5 = androidx.compose.ui.input.pointer.a.t(r4, r5, r0, r2, r5)
            e7.a r1 = e7.a.f15033i
            if (r5 != r1) goto L42
            return r1
        L42:
            androidx.compose.ui.input.pointer.PointerEvent r5 = (androidx.compose.ui.input.pointer.PointerEvent) r5
            int r1 = r5.getType()
            androidx.compose.ui.input.pointer.PointerEventType$Companion r3 = androidx.compose.ui.input.pointer.PointerEventType.INSTANCE
            int r3 = r3.m4476getScroll7fucELk()
            boolean r1 = androidx.compose.ui.input.pointer.PointerEventType.m4467equalsimpl0(r1, r3)
            if (r1 == 0) goto L34
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.ScrollableKt.awaitScrollEvent(androidx.compose.ui.input.pointer.AwaitPointerEventScope, d7.d):java.lang.Object");
    }

    public static final MotionDurationScale getDefaultScrollMotionDurationScale() {
        return DefaultScrollMotionDurationScale;
    }

    public static final ProvidableModifierLocal<Boolean> getModifierLocalScrollableContainer() {
        return ModifierLocalScrollableContainer;
    }

    public static final FlingBehavior getNoOpFlingBehavior() {
        return NoOpFlingBehavior;
    }

    public static final Modifier scrollable(Modifier modifier, ScrollableState scrollableState, Orientation orientation, boolean z, boolean z5, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource) {
        return scrollable$default(modifier, scrollableState, orientation, null, z, z5, flingBehavior, mutableInteractionSource, null, 128, null);
    }

    public static /* synthetic */ Modifier scrollable$default(Modifier modifier, ScrollableState scrollableState, Orientation orientation, boolean z, boolean z5, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            z = true;
        }
        boolean z10 = z;
        if ((i10 & 8) != 0) {
            z5 = false;
        }
        return scrollable(modifier, scrollableState, orientation, z10, z5, (i10 & 16) != 0 ? null : flingBehavior, (i10 & 32) != 0 ? null : mutableInteractionSource);
    }

    public static final Modifier scrollable(Modifier modifier, ScrollableState scrollableState, Orientation orientation, OverscrollEffect overscrollEffect, boolean z, boolean z5, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, BringIntoViewSpec bringIntoViewSpec) {
        return modifier.then(new ScrollableElement(scrollableState, orientation, overscrollEffect, z, z5, flingBehavior, mutableInteractionSource, bringIntoViewSpec));
    }

    public static /* synthetic */ Modifier scrollable$default(Modifier modifier, ScrollableState scrollableState, Orientation orientation, OverscrollEffect overscrollEffect, boolean z, boolean z5, FlingBehavior flingBehavior, MutableInteractionSource mutableInteractionSource, BringIntoViewSpec bringIntoViewSpec, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            z = true;
        }
        return scrollable(modifier, scrollableState, orientation, overscrollEffect, z, (i10 & 16) != 0 ? false : z5, (i10 & 32) != 0 ? null : flingBehavior, (i10 & 64) != 0 ? null : mutableInteractionSource, (i10 & 128) != 0 ? ScrollableDefaults.INSTANCE.bringIntoViewSpec() : bringIntoViewSpec);
    }
}
