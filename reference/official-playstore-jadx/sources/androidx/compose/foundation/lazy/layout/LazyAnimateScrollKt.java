package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import f7.e;
import f7.j;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.k0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001e\u0010\u0004\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u0082\b¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001b\u0010\n\u001a\u00020\t*\u00020\u00062\u0006\u0010\b\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a4\u0010\u0010\u001a\u00020\u0003*\u00020\u00062\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH\u0080@¢\u0006\u0004\b\u0010\u0010\u0011\"\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014\"\u0014\u0010\u0015\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014\"\u0014\u0010\u0016\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0014\"\u0014\u0010\u0017\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lkotlin/Function0;", "", "generateMsg", "Lx6/t0;", "debugLog", "(Lr7/a;)V", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateScrollScope;", "", "index", "", "isItemVisible", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateScrollScope;I)Z", "scrollOffset", "numOfItemsForTeleport", "Landroidx/compose/ui/unit/Density;", "density", "animateScrollToItem", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateScrollScope;IIILandroidx/compose/ui/unit/Density;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/unit/Dp;", "TargetDistance", "F", "BoundDistance", "MinimumDistance", "DEBUG", "Z", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyAnimateScrollKt {
    private static final boolean DEBUG = false;
    private static final float TargetDistance = Dp.m5678constructorimpl(2500);
    private static final float BoundDistance = Dp.m5678constructorimpl(1500);
    private static final float MinimumDistance = Dp.m5678constructorimpl(50);

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt$animateScrollToItem$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt$animateScrollToItem$2", f = "LazyAnimateScroll.kt", l = {182, 280}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements p<ScrollScope, d7.d<? super t0>, Object> {
        final /* synthetic */ Density $density;
        final /* synthetic */ int $index;
        final /* synthetic */ int $numOfItemsForTeleport;
        final /* synthetic */ int $scrollOffset;
        final /* synthetic */ LazyLayoutAnimateScrollScope $this_animateScrollToItem;
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt$animateScrollToItem$2$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass3 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
            final /* synthetic */ ScrollScope $$this$scroll;
            final /* synthetic */ k0 $anim;
            final /* synthetic */ float $boundDistancePx;
            final /* synthetic */ boolean $forward;
            final /* synthetic */ int $index;
            final /* synthetic */ f0 $loop;
            final /* synthetic */ i0 $loops;
            final /* synthetic */ int $numOfItemsForTeleport;
            final /* synthetic */ h0 $prevValue;
            final /* synthetic */ int $scrollOffset;
            final /* synthetic */ float $target;
            final /* synthetic */ LazyLayoutAnimateScrollScope $this_animateScrollToItem;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i10, float f10, h0 h0Var, ScrollScope scrollScope, f0 f0Var, boolean z, float f11, i0 i0Var, int i11, int i12, k0 k0Var) {
                super(1);
                this.$this_animateScrollToItem = lazyLayoutAnimateScrollScope;
                this.$index = i10;
                this.$target = f10;
                this.$prevValue = h0Var;
                this.$$this$scroll = scrollScope;
                this.$loop = f0Var;
                this.$forward = z;
                this.$boundDistancePx = f11;
                this.$loops = i0Var;
                this.$numOfItemsForTeleport = i11;
                this.$scrollOffset = i12;
                this.$anim = k0Var;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((AnimationScope<Float, AnimationVector1D>) obj);
                return t0.f22605a;
            }

            /* JADX WARN: Removed duplicated region for block: B:19:0x005e  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x00d0  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0022 A[PHI: r2
              0x0022: PHI (r2v19 float) = (r2v3 float), (r2v20 float) binds: [B:10:0x0032, B:7:0x0020] A[DONT_GENERATE, DONT_INLINE]] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke(androidx.compose.animation.core.AnimationScope<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r8) {
                /*
                    Method dump skipped, instruction units count: 280
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt.AnonymousClass2.AnonymousClass3.invoke(androidx.compose.animation.core.AnimationScope):void");
            }
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt$animateScrollToItem$2$5, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass5 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
            final /* synthetic */ ScrollScope $$this$scroll;
            final /* synthetic */ h0 $prevValue;
            final /* synthetic */ float $target;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(float f10, h0 h0Var, ScrollScope scrollScope) {
                super(1);
                this.$target = f10;
                this.$prevValue = h0Var;
                this.$$this$scroll = scrollScope;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((AnimationScope<Float, AnimationVector1D>) obj);
                return t0.f22605a;
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0018 A[PHI: r0
              0x0018: PHI (r0v8 float) = (r0v4 float), (r0v11 float) binds: [B:11:0x002c, B:5:0x0015] A[DONT_GENERATE, DONT_INLINE]] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke(androidx.compose.animation.core.AnimationScope<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r4) {
                /*
                    r3 = this;
                    float r0 = r3.$target
                    r1 = 0
                    int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                    if (r2 <= 0) goto L1a
                    java.lang.Object r0 = r4.getValue()
                    java.lang.Number r0 = (java.lang.Number) r0
                    float r0 = r0.floatValue()
                    float r1 = r3.$target
                    int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                    if (r2 <= 0) goto L18
                    goto L2e
                L18:
                    r1 = r0
                    goto L2e
                L1a:
                    int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                    if (r0 >= 0) goto L2e
                    java.lang.Object r0 = r4.getValue()
                    java.lang.Number r0 = (java.lang.Number) r0
                    float r0 = r0.floatValue()
                    float r1 = r3.$target
                    int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                    if (r2 >= 0) goto L18
                L2e:
                    kotlin.jvm.internal.h0 r0 = r3.$prevValue
                    float r0 = r0.f19742i
                    float r0 = r1 - r0
                    androidx.compose.foundation.gestures.ScrollScope r2 = r3.$$this$scroll
                    float r2 = r2.scrollBy(r0)
                    int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                    if (r2 != 0) goto L4d
                    java.lang.Object r2 = r4.getValue()
                    java.lang.Number r2 = (java.lang.Number) r2
                    float r2 = r2.floatValue()
                    int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                    if (r1 != 0) goto L4d
                    goto L50
                L4d:
                    r4.cancelAnimation()
                L50:
                    kotlin.jvm.internal.h0 r4 = r3.$prevValue
                    float r1 = r4.f19742i
                    float r1 = r1 + r0
                    r4.f19742i = r1
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt.AnonymousClass2.AnonymousClass5.invoke(androidx.compose.animation.core.AnimationScope):void");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(int i10, Density density, LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i11, int i12, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$index = i10;
            this.$density = density;
            this.$this_animateScrollToItem = lazyLayoutAnimateScrollScope;
            this.$scrollOffset = i11;
            this.$numOfItemsForTeleport = i12;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$isOvershot(boolean z, LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i10, int i11) {
            if (z) {
                if (lazyLayoutAnimateScrollScope.getFirstVisibleItemIndex() > i10) {
                    return true;
                }
                return lazyLayoutAnimateScrollScope.getFirstVisibleItemIndex() == i10 && lazyLayoutAnimateScrollScope.getFirstVisibleItemScrollOffset() > i11;
            }
            if (lazyLayoutAnimateScrollScope.getFirstVisibleItemIndex() < i10) {
                return true;
            }
            return lazyLayoutAnimateScrollScope.getFirstVisibleItemIndex() == i10 && lazyLayoutAnimateScrollScope.getFirstVisibleItemScrollOffset() < i11;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$index, this.$density, this.$this_animateScrollToItem, this.$scrollOffset, this.$numOfItemsForTeleport, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(ScrollScope scrollScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(scrollScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x00c8 A[Catch: ItemFoundInScroll -> 0x00ef, TryCatch #4 {ItemFoundInScroll -> 0x00ef, blocks: (B:27:0x00c4, B:29:0x00c8, B:31:0x00d0, B:33:0x00e2, B:43:0x00fb, B:47:0x0134), top: B:95:0x00c4 }] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00e2 A[Catch: ItemFoundInScroll -> 0x00ef, TryCatch #4 {ItemFoundInScroll -> 0x00ef, blocks: (B:27:0x00c4, B:29:0x00c8, B:31:0x00d0, B:33:0x00e2, B:43:0x00fb, B:47:0x0134), top: B:95:0x00c4 }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00f6  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x013f  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x0142  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0188  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x01f7  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x01fb  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x021d  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0188 -> B:97:0x0190). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r36) {
            /*
                Method dump skipped, instruction units count: 572
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final Object animateScrollToItem(LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i10, int i11, int i12, Density density, d7.d<? super t0> dVar) {
        Object objScroll = lazyLayoutAnimateScrollScope.scroll(new AnonymousClass2(i10, density, lazyLayoutAnimateScrollScope, i11, i12, null), dVar);
        return objScroll == e7.a.f15033i ? objScroll : t0.f22605a;
    }

    public static final boolean isItemVisible(LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i10) {
        return i10 <= lazyLayoutAnimateScrollScope.getLastVisibleItemIndex() && lazyLayoutAnimateScrollScope.getFirstVisibleItemIndex() <= i10;
    }

    private static final void debugLog(r7.a<String> aVar) {
    }
}
