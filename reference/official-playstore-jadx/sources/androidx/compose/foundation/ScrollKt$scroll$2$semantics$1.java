package androidx.compose.foundation;

import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ScrollKt$scroll$2$semantics$1 extends r implements l<SemanticsPropertyReceiver, t0> {
    final /* synthetic */ k0 $coroutineScope;
    final /* synthetic */ boolean $isScrollable;
    final /* synthetic */ boolean $isVertical;
    final /* synthetic */ boolean $reverseScrolling;
    final /* synthetic */ ScrollState $state;

    /* JADX INFO: renamed from: androidx.compose.foundation.ScrollKt$scroll$2$semantics$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "x", "", "y", "invoke", "(FF)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Float, Float, Boolean> {
        final /* synthetic */ k0 $coroutineScope;
        final /* synthetic */ boolean $isVertical;
        final /* synthetic */ ScrollState $state;

        /* JADX INFO: renamed from: androidx.compose.foundation.ScrollKt$scroll$2$semantics$1$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @e(c = "androidx.compose.foundation.ScrollKt$scroll$2$semantics$1$1$1", f = "Scroll.kt", l = {293, 295}, m = "invokeSuspend")
        public static final class C00131 extends j implements p<k0, d<? super t0>, Object> {
            final /* synthetic */ boolean $isVertical;
            final /* synthetic */ ScrollState $state;
            final /* synthetic */ float $x;
            final /* synthetic */ float $y;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00131(boolean z, ScrollState scrollState, float f10, float f11, d<? super C00131> dVar) {
                super(2, dVar);
                this.$isVertical = z;
                this.$state = scrollState;
                this.$y = f10;
                this.$x = f11;
            }

            @Override // f7.a
            public final d<t0> create(Object obj, d<?> dVar) {
                return new C00131(this.$isVertical, this.$state, this.$y, this.$x, dVar);
            }

            /* JADX WARN: Code restructure failed: missing block: B:13:0x0033, code lost:
            
                if (androidx.compose.foundation.gestures.ScrollExtensionsKt.animateScrollBy$default(r3, r4, null, r9, 2, null) == r0) goto L17;
             */
            /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
            
                if (androidx.compose.foundation.gestures.ScrollExtensionsKt.animateScrollBy$default(r1, r2, null, r9, 2, null) == r0) goto L17;
             */
            /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
            
                return r0;
             */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                /*
                    r9 = this;
                    int r0 = r9.label
                    r1 = 2
                    r2 = 1
                    if (r0 == 0) goto L1b
                    if (r0 == r2) goto L16
                    if (r0 != r1) goto Le
                    k2.c.G(r10)
                    goto L48
                Le:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r0)
                    throw r10
                L16:
                    k2.c.G(r10)
                    r4 = r9
                    goto L48
                L1b:
                    k2.c.G(r10)
                    boolean r10 = r9.$isVertical
                    e7.a r0 = e7.a.f15033i
                    if (r10 == 0) goto L36
                    androidx.compose.foundation.ScrollState r3 = r9.$state
                    float r4 = r9.$y
                    r9.label = r2
                    r5 = 0
                    r7 = 2
                    r8 = 0
                    r6 = r9
                    java.lang.Object r10 = androidx.compose.foundation.gestures.ScrollExtensionsKt.animateScrollBy$default(r3, r4, r5, r6, r7, r8)
                    r4 = r6
                    if (r10 != r0) goto L48
                    goto L47
                L36:
                    r4 = r9
                    r10 = r1
                    androidx.compose.foundation.ScrollState r1 = r4.$state
                    float r2 = r4.$x
                    r4.label = r10
                    r3 = 0
                    r5 = 2
                    r6 = 0
                    java.lang.Object r10 = androidx.compose.foundation.gestures.ScrollExtensionsKt.animateScrollBy$default(r1, r2, r3, r4, r5, r6)
                    if (r10 != r0) goto L48
                L47:
                    return r0
                L48:
                    x6.t0 r10 = x6.t0.f22605a
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.ScrollKt$scroll$2$semantics$1.AnonymousClass1.C00131.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d<? super t0> dVar) {
                return ((C00131) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(k0 k0Var, boolean z, ScrollState scrollState) {
            super(2);
            this.$coroutineScope = k0Var;
            this.$isVertical = z;
            this.$state = scrollState;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).floatValue(), ((Number) obj2).floatValue());
        }

        public final Boolean invoke(float f10, float f11) {
            m0.p(this.$coroutineScope, null, 0, new C00131(this.$isVertical, this.$state, f11, f10, null), 3);
            return Boolean.TRUE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollKt$scroll$2$semantics$1(boolean z, boolean z5, boolean z10, ScrollState scrollState, k0 k0Var) {
        super(1);
        this.$reverseScrolling = z;
        this.$isVertical = z5;
        this.$isScrollable = z10;
        this.$state = scrollState;
        this.$coroutineScope = k0Var;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SemanticsPropertyReceiver) obj);
        return t0.f22605a;
    }

    public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        ScrollAxisRange scrollAxisRange = new ScrollAxisRange(new ScrollKt$scroll$2$semantics$1$accessibilityScrollState$1(this.$state), new ScrollKt$scroll$2$semantics$1$accessibilityScrollState$2(this.$state), this.$reverseScrolling);
        if (this.$isVertical) {
            SemanticsPropertiesKt.setVerticalScrollAxisRange(semanticsPropertyReceiver, scrollAxisRange);
        } else {
            SemanticsPropertiesKt.setHorizontalScrollAxisRange(semanticsPropertyReceiver, scrollAxisRange);
        }
        if (this.$isScrollable) {
            SemanticsPropertiesKt.scrollBy$default(semanticsPropertyReceiver, null, new AnonymousClass1(this.$coroutineScope, this.$isVertical, this.$state), 1, null);
        }
    }
}
