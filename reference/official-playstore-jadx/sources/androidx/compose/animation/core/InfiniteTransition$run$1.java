package androidx.compose.animation.core;

import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.animation.core.InfiniteTransition$run$1", f = "InfiniteTransition.kt", l = {181, 205}, m = "invokeSuspend")
public final class InfiniteTransition$run$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableState<State<Long>> $toolingOverride;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ InfiniteTransition this$0;

    /* JADX INFO: renamed from: androidx.compose.animation.core.InfiniteTransition$run$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Long, t0> {
        final /* synthetic */ k0 $$this$LaunchedEffect;
        final /* synthetic */ h0 $durationScale;
        final /* synthetic */ MutableState<State<Long>> $toolingOverride;
        final /* synthetic */ InfiniteTransition this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MutableState<State<Long>> mutableState, InfiniteTransition infiniteTransition, h0 h0Var, k0 k0Var) {
            super(1);
            this.$toolingOverride = mutableState;
            this.this$0 = infiniteTransition;
            this.$durationScale = h0Var;
            this.$$this$LaunchedEffect = k0Var;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke(((Number) obj).longValue());
            return t0.f22605a;
        }

        public final void invoke(long j10) {
            State<Long> value = this.$toolingOverride.getValue();
            long jLongValue = value != null ? value.getValue().longValue() : j10;
            int i10 = 0;
            if (this.this$0.startTimeNanos == Long.MIN_VALUE || this.$durationScale.f19742i != SuspendAnimationKt.getDurationScale(this.$$this$LaunchedEffect.getCoroutineContext())) {
                this.this$0.startTimeNanos = j10;
                MutableVector mutableVector = this.this$0._animations;
                int size = mutableVector.getSize();
                if (size > 0) {
                    Object[] content = mutableVector.getContent();
                    int i11 = 0;
                    do {
                        ((InfiniteTransition.TransitionAnimationState) content[i11]).reset$animation_core_release();
                        i11++;
                    } while (i11 < size);
                }
                this.$durationScale.f19742i = SuspendAnimationKt.getDurationScale(this.$$this$LaunchedEffect.getCoroutineContext());
            }
            if (this.$durationScale.f19742i != 0.0f) {
                this.this$0.onFrame((long) ((jLongValue - this.this$0.startTimeNanos) / this.$durationScale.f19742i));
                return;
            }
            MutableVector mutableVector2 = this.this$0._animations;
            int size2 = mutableVector2.getSize();
            if (size2 > 0) {
                Object[] content2 = mutableVector2.getContent();
                do {
                    ((InfiniteTransition.TransitionAnimationState) content2[i10]).skipToEnd$animation_core_release();
                    i10++;
                } while (i10 < size2);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.InfiniteTransition$run$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements r7.a<Float> {
        final /* synthetic */ k0 $$this$LaunchedEffect;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(k0 k0Var) {
            super(0);
            this.$$this$LaunchedEffect = k0Var;
        }

        @Override // r7.a
        public final Float invoke() {
            return Float.valueOf(SuspendAnimationKt.getDurationScale(this.$$this$LaunchedEffect.getCoroutineContext()));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.animation.core.InfiniteTransition$run$1$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.animation.core.InfiniteTransition$run$1$3", f = "InfiniteTransition.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass3 extends j implements p<Float, d7.d<? super Boolean>, Object> {
        /* synthetic */ float F$0;
        int label;

        public AnonymousClass3(d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(dVar);
            anonymousClass3.F$0 = ((Number) obj).floatValue();
            return anonymousClass3;
        }

        public final Object invoke(float f10, d7.d<? super Boolean> dVar) {
            return ((AnonymousClass3) create(Float.valueOf(f10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return Boolean.valueOf(this.F$0 > 0.0f);
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).floatValue(), (d7.d<? super Boolean>) obj2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InfiniteTransition$run$1(MutableState<State<Long>> mutableState, InfiniteTransition infiniteTransition, d7.d<? super InfiniteTransition$run$1> dVar) {
        super(2, dVar);
        this.$toolingOverride = mutableState;
        this.this$0 = infiniteTransition;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        InfiniteTransition$run$1 infiniteTransition$run$1 = new InfiniteTransition$run$1(this.$toolingOverride, this.this$0, dVar);
        infiniteTransition$run$1.L$0 = obj;
        return infiniteTransition$run$1;
    }

    /* JADX WARN: Path cross not found for [B:11:0x003e, B:16:0x005b], limit reached: 18 */
    /* JADX WARN: Path cross not found for [B:16:0x005b, B:11:0x003e], limit reached: 18 */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054 A[PHI: r0 r8
      0x0054: PHI (r0v2 kotlin.jvm.internal.h0) = (r0v3 kotlin.jvm.internal.h0), (r0v5 kotlin.jvm.internal.h0) binds: [B:12:0x0051, B:9:0x0021] A[DONT_GENERATE, DONT_INLINE]
      0x0054: PHI (r8v3 ka.k0) = (r8v4 ka.k0), (r8v5 ka.k0) binds: [B:12:0x0051, B:9:0x0021] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0059 -> B:11:0x003e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0074 -> B:11:0x003e). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            int r0 = r7.label
            r1 = 2
            r2 = 1
            e7.a r3 = e7.a.f15033i
            if (r0 == 0) goto L2e
            if (r0 == r2) goto L21
            if (r0 != r1) goto L19
            java.lang.Object r0 = r7.L$1
            kotlin.jvm.internal.h0 r0 = (kotlin.jvm.internal.h0) r0
            java.lang.Object r4 = r7.L$0
            ka.k0 r4 = (ka.k0) r4
            k2.c.G(r8)
            r8 = r4
            goto L3e
        L19:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L21:
            java.lang.Object r0 = r7.L$1
            kotlin.jvm.internal.h0 r0 = (kotlin.jvm.internal.h0) r0
            java.lang.Object r4 = r7.L$0
            ka.k0 r4 = (ka.k0) r4
            k2.c.G(r8)
            r8 = r4
            goto L54
        L2e:
            k2.c.G(r8)
            java.lang.Object r8 = r7.L$0
            ka.k0 r8 = (ka.k0) r8
            kotlin.jvm.internal.h0 r0 = new kotlin.jvm.internal.h0
            r0.<init>()
            r4 = 1065353216(0x3f800000, float:1.0)
            r0.f19742i = r4
        L3e:
            androidx.compose.animation.core.InfiniteTransition$run$1$1 r4 = new androidx.compose.animation.core.InfiniteTransition$run$1$1
            androidx.compose.runtime.MutableState<androidx.compose.runtime.State<java.lang.Long>> r5 = r7.$toolingOverride
            androidx.compose.animation.core.InfiniteTransition r6 = r7.this$0
            r4.<init>(r5, r6, r0, r8)
            r7.L$0 = r8
            r7.L$1 = r0
            r7.label = r2
            java.lang.Object r4 = androidx.compose.animation.core.InfiniteAnimationPolicyKt.withInfiniteAnimationFrameNanos(r4, r7)
            if (r4 != r3) goto L54
            goto L76
        L54:
            float r4 = r0.f19742i
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L3e
            androidx.compose.animation.core.InfiniteTransition$run$1$2 r4 = new androidx.compose.animation.core.InfiniteTransition$run$1$2
            r4.<init>(r8)
            na.j r4 = androidx.compose.runtime.SnapshotStateKt.snapshotFlow(r4)
            androidx.compose.animation.core.InfiniteTransition$run$1$3 r5 = new androidx.compose.animation.core.InfiniteTransition$run$1$3
            r6 = 0
            r5.<init>(r6)
            r7.L$0 = r8
            r7.L$1 = r0
            r7.label = r1
            java.lang.Object r4 = na.y0.m(r4, r5, r7)
            if (r4 != r3) goto L3e
        L76:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.InfiniteTransition$run$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((InfiniteTransition$run$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
