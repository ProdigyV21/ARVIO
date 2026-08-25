package androidx.compose.foundation.pager;

import androidx.compose.foundation.ClipScrollableContainerKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.OverscrollKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import d7.d;
import f7.e;
import f7.h;
import f7.j;
import io.ktor.http.ContentDisposition;
import k2.c;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import kotlin.jvm.internal.z;
import kotlin.reflect.n;
import r7.l;
import r7.p;
import r7.r;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aÖ\u0001\u0010(\u001a\u00020\"2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132#\u0010\u001a\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00152\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u001d21\u0010%\u001a-\u0012\u0004\u0012\u00020 \u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\"0\u001f¢\u0006\u0002\b#¢\u0006\u0002\b$H\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001a\u0083\u0001\u0010,\u001a\b\u0012\u0004\u0012\u00020+0)2\u0006\u0010\u0003\u001a\u00020\u000221\u0010%\u001a-\u0012\u0004\u0012\u00020 \u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\"0\u001f¢\u0006\u0002\b#¢\u0006\u0002\b$2#\u0010\u001a\u001a\u001f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00152\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\r0)H\u0003¢\u0006\u0004\b,\u0010-\u001a\u001b\u0010.\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b.\u0010/\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00060"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/foundation/pager/PagerState;", "state", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "", "reverseLayout", "Landroidx/compose/foundation/gestures/Orientation;", "orientation", "Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "flingBehavior", "userScrollEnabled", "", "beyondBoundsPageCount", "Landroidx/compose/ui/unit/Dp;", "pageSpacing", "Landroidx/compose/foundation/pager/PageSize;", "pageSize", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "pageNestedScrollConnection", "Lkotlin/Function1;", "Lx6/y;", ContentDisposition.Parameters.Name, "index", "", "key", "Landroidx/compose/ui/Alignment$Horizontal;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "verticalAlignment", "Lkotlin/Function2;", "Landroidx/compose/foundation/pager/PagerScope;", "page", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "pageContent", "Pager-fs30GE4", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;ZIFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lr7/l;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Lr7/r;Landroidx/compose/runtime/Composer;III)V", "Pager", "Lkotlin/Function0;", "pageCount", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "rememberPagerItemProviderLambda", "(Landroidx/compose/foundation/pager/PagerState;Lr7/r;Lr7/l;Lr7/a;Landroidx/compose/runtime/Composer;I)Lr7/a;", "dragDirectionDetector", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;)Landroidx/compose/ui/Modifier;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutPagerKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1", f = "LazyLayoutPager.kt", l = {274}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<PointerInputScope, d<? super t0>, Object> {
        final /* synthetic */ PagerState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1", f = "LazyLayoutPager.kt", l = {275}, m = "invokeSuspend")
        public static final class C00341 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ PagerState $state;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
            @e(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1", f = "LazyLayoutPager.kt", l = {277, 281}, m = "invokeSuspend")
            public static final class C00351 extends h implements p<AwaitPointerEventScope, d<? super t0>, Object> {
                final /* synthetic */ PagerState $state;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00351(PagerState pagerState, d<? super C00351> dVar) {
                    super(2, dVar);
                    this.$state = pagerState;
                }

                @Override // f7.a
                public final d<t0> create(Object obj, d<?> dVar) {
                    C00351 c00351 = new C00351(this.$state, dVar);
                    c00351.L$0 = obj;
                    return c00351;
                }

                @Override // r7.p
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d<? super t0> dVar) {
                    return ((C00351) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
                }

                /* JADX WARN: Code restructure failed: missing block: B:11:0x003f, code lost:
                
                    if (r11 == r4) goto L17;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:16:0x0063, code lost:
                
                    if (r11 == r4) goto L17;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
                
                    return r4;
                 */
                /* JADX WARN: Removed duplicated region for block: B:15:0x0055  */
                /* JADX WARN: Removed duplicated region for block: B:25:0x008f  */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0063 -> B:18:0x0066). Please report as a decompilation issue!!! */
                @Override // f7.a
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r11) {
                    /*
                        r10 = this;
                        int r0 = r10.label
                        r1 = 2
                        r2 = 0
                        r3 = 1
                        e7.a r4 = e7.a.f15033i
                        if (r0 == 0) goto L2d
                        if (r0 == r3) goto L25
                        if (r0 != r1) goto L1d
                        java.lang.Object r0 = r10.L$2
                        androidx.compose.ui.input.pointer.PointerInputChange r0 = (androidx.compose.ui.input.pointer.PointerInputChange) r0
                        java.lang.Object r3 = r10.L$1
                        androidx.compose.ui.input.pointer.PointerInputChange r3 = (androidx.compose.ui.input.pointer.PointerInputChange) r3
                        java.lang.Object r5 = r10.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                        k2.c.G(r11)
                        goto L66
                    L1d:
                        java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r11.<init>(r0)
                        throw r11
                    L25:
                        java.lang.Object r0 = r10.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                        k2.c.G(r11)
                        goto L42
                    L2d:
                        k2.c.G(r11)
                        java.lang.Object r11 = r10.L$0
                        r0 = r11
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r0 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r0
                        androidx.compose.ui.input.pointer.PointerEventPass r11 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r10.L$0 = r0
                        r10.label = r3
                        java.lang.Object r11 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown(r0, r2, r11, r10)
                        if (r11 != r4) goto L42
                        goto L65
                    L42:
                        androidx.compose.ui.input.pointer.PointerInputChange r11 = (androidx.compose.ui.input.pointer.PointerInputChange) r11
                        androidx.compose.foundation.pager.PagerState r3 = r10.$state
                        androidx.compose.ui.geometry.Offset$Companion r5 = androidx.compose.ui.geometry.Offset.INSTANCE
                        long r5 = r5.m3252getZeroF1C5BW0()
                        r3.m767setUpDownDifferencek4lQ0M$foundation_release(r5)
                        r3 = 0
                        r5 = r0
                        r0 = r3
                        r3 = r11
                    L53:
                        if (r0 != 0) goto L8f
                        androidx.compose.ui.input.pointer.PointerEventPass r11 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r10.L$0 = r5
                        r10.L$1 = r3
                        r10.L$2 = r0
                        r10.label = r1
                        java.lang.Object r11 = r5.awaitPointerEvent(r11, r10)
                        if (r11 != r4) goto L66
                    L65:
                        return r4
                    L66:
                        androidx.compose.ui.input.pointer.PointerEvent r11 = (androidx.compose.ui.input.pointer.PointerEvent) r11
                        java.util.List r6 = r11.getChanges()
                        int r7 = r6.size()
                        r8 = r2
                    L71:
                        if (r8 >= r7) goto L83
                        java.lang.Object r9 = r6.get(r8)
                        androidx.compose.ui.input.pointer.PointerInputChange r9 = (androidx.compose.ui.input.pointer.PointerInputChange) r9
                        boolean r9 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUp(r9)
                        if (r9 != 0) goto L80
                        goto L53
                    L80:
                        int r8 = r8 + 1
                        goto L71
                    L83:
                        java.util.List r11 = r11.getChanges()
                        java.lang.Object r11 = r11.get(r2)
                        r0 = r11
                        androidx.compose.ui.input.pointer.PointerInputChange r0 = (androidx.compose.ui.input.pointer.PointerInputChange) r0
                        goto L53
                    L8f:
                        androidx.compose.foundation.pager.PagerState r11 = r10.$state
                        long r0 = r0.getPosition()
                        long r2 = r3.getPosition()
                        long r0 = androidx.compose.ui.geometry.Offset.m3240minusMKHz9U(r0, r2)
                        r11.m767setUpDownDifferencek4lQ0M$foundation_release(r0)
                        x6.t0 r11 = x6.t0.f22605a
                        return r11
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.LazyLayoutPagerKt.AnonymousClass1.C00341.C00351.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00341(PointerInputScope pointerInputScope, PagerState pagerState, d<? super C00341> dVar) {
                super(2, dVar);
                this.$$this$pointerInput = pointerInputScope;
                this.$state = pagerState;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C00341(this.$$this$pointerInput, this.$state, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    c.G(obj);
                    PointerInputScope pointerInputScope = this.$$this$pointerInput;
                    C00351 c00351 = new C00351(this.$state, null);
                    this.label = 1;
                    Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, c00351, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objAwaitEachGesture == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00341) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(PagerState pagerState, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$state = pagerState;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$state, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                C00341 c00341 = new C00341((PointerInputScope) this.L$0, this.$state, null);
                this.label = 1;
                Object objC = l0.c(c00341, this);
                e7.a aVar = e7.a.f15033i;
                if (objC == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: Pager-fs30GE4, reason: not valid java name */
    public static final void m750Pagerfs30GE4(Modifier modifier, PagerState pagerState, PaddingValues paddingValues, boolean z, Orientation orientation, SnapFlingBehavior snapFlingBehavior, boolean z5, int i10, float f10, PageSize pageSize, NestedScrollConnection nestedScrollConnection, l<? super Integer, ? extends Object> lVar, Alignment.Horizontal horizontal, Alignment.Vertical vertical, r<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, t0> rVar, Composer composer, int i11, int i12, int i13) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-301644943);
        int i14 = (i13 & 128) != 0 ? 0 : i10;
        float fM5678constructorimpl = (i13 & 256) != 0 ? Dp.m5678constructorimpl(0) : f10;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-301644943, i11, i12, "androidx.compose.foundation.pager.Pager (LazyLayoutPager.kt:95)");
        }
        if (i14 < 0) {
            throw new IllegalArgumentException(a0.c.i(i14, "beyondBoundsPageCount should be greater than or equal to 0, you selected ").toString());
        }
        ScrollableDefaults scrollableDefaults = ScrollableDefaults.INSTANCE;
        OverscrollEffect overscrollEffect = scrollableDefaults.overscrollEffect(composerStartRestartGroup, 6);
        composerStartRestartGroup.startReplaceableGroup(-735094232);
        boolean zChanged = composerStartRestartGroup.changed(pagerState);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new LazyLayoutPagerKt$Pager$pagerItemProvider$1$1(pagerState);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        int i15 = i11 >> 3;
        int i16 = i15 & 14;
        r7.a<PagerLazyLayoutItemProvider> aVarRememberPagerItemProviderLambda = rememberPagerItemProviderLambda(pagerState, rVar, lVar, (r7.a) objRememberedValue, composerStartRestartGroup, ((i12 << 3) & 896) | i16 | ((i12 >> 9) & 112));
        int i17 = i14;
        SnapPositionInLayout snapAlignmentStartToStart = PagerStateKt.getSnapAlignmentStartToStart();
        composerStartRestartGroup.startReplaceableGroup(-735093678);
        boolean zChanged2 = composerStartRestartGroup.changed(pagerState);
        Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new LazyLayoutPagerKt$Pager$measurePolicy$1$1(pagerState);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
        }
        composerStartRestartGroup.endReplaceableGroup();
        int i18 = i11 & 7168;
        int i19 = i11 >> 6;
        int i20 = i12 << 18;
        float f11 = fM5678constructorimpl;
        p<LazyLayoutMeasureScope, Constraints, MeasureResult> pVarM761rememberPagerMeasurePolicy121YqSk = PagerMeasurePolicyKt.m761rememberPagerMeasurePolicy121YqSk(aVarRememberPagerItemProviderLambda, pagerState, paddingValues, z, orientation, i17, f11, pageSize, horizontal, vertical, snapAlignmentStartToStart, (r7.a) objRememberedValue2, composerStartRestartGroup, (i11 & 65520) | (i19 & 458752) | (i19 & 3670016) | (i19 & 29360128) | (i20 & 234881024) | (i20 & 1879048192), 0);
        composerStartRestartGroup.startReplaceableGroup(511388516);
        boolean zChanged3 = composerStartRestartGroup.changed(snapFlingBehavior) | composerStartRestartGroup.changed(pagerState);
        Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new PagerWrapperFlingBehavior(snapFlingBehavior, pagerState);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
        }
        composerStartRestartGroup.endReplaceableGroup();
        PagerWrapperFlingBehavior pagerWrapperFlingBehavior = (PagerWrapperFlingBehavior) objRememberedValue3;
        LazyLayoutSemanticState lazyLayoutSemanticStateRememberPagerSemanticState = PagerSemanticsKt.rememberPagerSemanticState(pagerState, z, orientation == Orientation.Vertical, composerStartRestartGroup, i16 | (i19 & 112));
        composerStartRestartGroup.startReplaceableGroup(1157296644);
        boolean zChanged4 = composerStartRestartGroup.changed(pagerState);
        Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
        if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new PagerBringIntoViewSpec(pagerState);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
        }
        composerStartRestartGroup.endReplaceableGroup();
        LazyLayoutKt.LazyLayout(aVarRememberPagerItemProviderLambda, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(ScrollableKt.scrollable(OverscrollKt.overscroll(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(pagerState.getRemeasurementModifier()).then(pagerState.getAwaitLayoutModifier()), aVarRememberPagerItemProviderLambda, lazyLayoutSemanticStateRememberPagerSemanticState, orientation, z5, z, composerStartRestartGroup, ((i11 << 6) & 458752) | (i15 & 7168) | (i19 & 57344)), orientation), PagerBeyondBoundsModifierKt.rememberPagerBeyondBoundsState(pagerState, i17, composerStartRestartGroup, i16 | ((i11 >> 18) & 112)), pagerState.getBeyondBoundsInfo(), z, (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection()), orientation, z5, composerStartRestartGroup, (MutableVector.$stable << 6) | i18 | ((i11 << 3) & 458752) | (i11 & 3670016)), overscrollEffect), pagerState, orientation, overscrollEffect, z5, scrollableDefaults.reverseDirection((LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection()), orientation, z), pagerWrapperFlingBehavior, pagerState.getInternalInteractionSource(), (PagerBringIntoViewSpec) objRememberedValue4), pagerState), nestedScrollConnection, null, 2, null), pagerState.getPrefetchState(), pVarM761rememberPagerMeasurePolicy121YqSk, composerStartRestartGroup, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new LazyLayoutPagerKt$Pager$2(modifier, pagerState, paddingValues, z, orientation, snapFlingBehavior, z5, i17, f11, pageSize, nestedScrollConnection, lVar, horizontal, vertical, rVar, i11, i12, i13));
        }
    }

    private static final Modifier dragDirectionDetector(Modifier modifier, PagerState pagerState) {
        return modifier.then(SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, pagerState, new AnonymousClass1(pagerState, null)));
    }

    private static final r7.a<PagerLazyLayoutItemProvider> rememberPagerItemProviderLambda(PagerState pagerState, r<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, t0> rVar, l<? super Integer, ? extends Object> lVar, r7.a<Integer> aVar, Composer composer, int i10) {
        composer.startReplaceableGroup(-1372505274);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1372505274, i10, -1, "androidx.compose.foundation.pager.rememberPagerItemProviderLambda (LazyLayoutPager.kt:247)");
        }
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(rVar, composer, (i10 >> 3) & 14);
        State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(lVar, composer, (i10 >> 6) & 14);
        Object[] objArr = {pagerState, stateRememberUpdatedState, stateRememberUpdatedState2, aVar};
        composer.startReplaceableGroup(-568225417);
        boolean zChanged = false;
        for (int i11 = 0; i11 < 4; i11++) {
            zChanged |= composer.changed(objArr[i11]);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$itemProviderState$1(SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$intervalContentState$1(stateRememberUpdatedState, stateRememberUpdatedState2, aVar)), pagerState));
            objRememberedValue = new z(stateDerivedStateOf) { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$1
                @Override // kotlin.reflect.n
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        n nVar = (n) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return nVar;
    }
}
