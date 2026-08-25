package com.arflix.tv.ui.motion;

import androidx.activity.c;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.runtime.State;
import com.arflix.tv.ui.theme.AnimationConstants;
import d7.d;
import f7.e;
import f7.j;
import java.util.concurrent.CancellationException;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import na.k;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00070\u0004¢\u0006\u0002\b\u00022\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lna/j;", "Landroidx/activity/c;", "Lq7/o;", "events", "Lx6/t0;", "<anonymous>", "(Lna/j;)V"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.ui.motion.ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1", f = "ArvioPredictiveBack.kt", l = {74}, m = "invokeSuspend", v = 2)
public final class ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1 extends j implements p<na.j<c>, d<t0>, Object> {
    final /* synthetic */ Animatable<Float, AnimationVector1D> $anim;
    final /* synthetic */ int $cancelDurationMs;
    final /* synthetic */ State<r7.a<t0>> $commit$delegate;
    final /* synthetic */ int $commitDurationMs;
    final /* synthetic */ ArvioBackMotion $motion;
    final /* synthetic */ k0 $scope;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.motion.ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.motion.ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1$2", f = "ArvioPredictiveBack.kt", l = {AnimationConstants.STAGGER_SECTION, 81}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ Animatable<Float, AnimationVector1D> $anim;
        final /* synthetic */ int $commitDurationMs;
        final /* synthetic */ ArvioBackMotion $motion;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Animatable<Float, AnimationVector1D> animatable, int i10, ArvioBackMotion arvioBackMotion, d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$anim = animatable;
            this.$commitDurationMs = i10;
            this.$motion = arvioBackMotion;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass2(this.$anim, this.$commitDurationMs, this.$motion, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0053, code lost:
        
            if (r13.snapTo(r0, r12) == r3) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                r12 = this;
                int r0 = r12.label
                r1 = 1
                r2 = 2
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L1e
                if (r0 == r1) goto L19
                if (r0 != r2) goto L11
                k2.c.G(r13)
                r9 = r12
                goto L56
            L11:
                java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r13.<init>(r0)
                throw r13
            L19:
                k2.c.G(r13)
                r9 = r12
                goto L45
            L1e:
                k2.c.G(r13)
                androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r4 = r12.$anim
                java.lang.Float r5 = new java.lang.Float
                r13 = 1065353216(0x3f800000, float:1.0)
                r5.<init>(r13)
                int r13 = r12.$commitDurationMs
                androidx.compose.animation.core.Easing r0 = com.arflix.tv.ui.motion.ArvioPredictiveBackKt.getArvioStandardDecelerate()
                r6 = 0
                r7 = 0
                androidx.compose.animation.core.TweenSpec r6 = androidx.compose.animation.core.AnimationSpecKt.tween$default(r13, r7, r0, r2, r6)
                r12.label = r1
                r7 = 0
                r8 = 0
                r10 = 12
                r11 = 0
                r9 = r12
                java.lang.Object r13 = androidx.compose.animation.core.Animatable.animateTo$default(r4, r5, r6, r7, r8, r9, r10, r11)
                if (r13 != r3) goto L45
                goto L55
            L45:
                androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r13 = r9.$anim
                java.lang.Float r0 = new java.lang.Float
                r1 = 0
                r0.<init>(r1)
                r9.label = r2
                java.lang.Object r13 = r13.snapTo(r0, r12)
                if (r13 != r3) goto L56
            L55:
                return r3
            L56:
                com.arflix.tv.ui.motion.ArvioBackMotion r13 = r9.$motion
                r0 = 2143289344(0x7fc00000, float:NaN)
                r13.setTouchY$app_playRelease(r0)
                x6.t0 r13 = x6.t0.f22605a
                return r13
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.motion.ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.motion.ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.motion.ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1$3", f = "ArvioPredictiveBack.kt", l = {87}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass3 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ Animatable<Float, AnimationVector1D> $anim;
        final /* synthetic */ int $cancelDurationMs;
        final /* synthetic */ ArvioBackMotion $motion;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(Animatable<Float, AnimationVector1D> animatable, int i10, ArvioBackMotion arvioBackMotion, d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$anim = animatable;
            this.$cancelDurationMs = i10;
            this.$motion = arvioBackMotion;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return new AnonymousClass3(this.$anim, this.$cancelDurationMs, this.$motion, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            AnonymousClass3 anonymousClass3;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                Animatable<Float, AnimationVector1D> animatable = this.$anim;
                Float f10 = new Float(0.0f);
                TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(this.$cancelDurationMs, 0, ArvioPredictiveBackKt.getArvioStandardDecelerate(), 2, null);
                this.label = 1;
                anonymousClass3 = this;
                Object objAnimateTo$default = Animatable.animateTo$default(animatable, f10, tweenSpecTween$default, null, null, anonymousClass3, 12, null);
                e7.a aVar = e7.a.f15033i;
                if (objAnimateTo$default == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                anonymousClass3 = this;
            }
            anonymousClass3.$motion.setTouchY$app_playRelease(Float.NaN);
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1(k0 k0Var, ArvioBackMotion arvioBackMotion, Animatable<Float, AnimationVector1D> animatable, int i10, State<? extends r7.a<t0>> state, int i11, d<? super ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1> dVar) {
        super(2, dVar);
        this.$scope = k0Var;
        this.$motion = arvioBackMotion;
        this.$anim = animatable;
        this.$commitDurationMs = i10;
        this.$commit$delegate = state;
        this.$cancelDurationMs = i11;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1 arvioPredictiveBackKt$rememberArvioPredictiveBack$2$1 = new ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1(this.$scope, this.$motion, this.$anim, this.$commitDurationMs, this.$commit$delegate, this.$cancelDurationMs, dVar);
        arvioPredictiveBackKt$rememberArvioPredictiveBack$2$1.L$0 = obj;
        return arvioPredictiveBackKt$rememberArvioPredictiveBack$2$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        na.j jVar = (na.j) this.L$0;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                final ArvioBackMotion arvioBackMotion = this.$motion;
                final Animatable<Float, AnimationVector1D> animatable = this.$anim;
                k kVar = new k() { // from class: com.arflix.tv.ui.motion.ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                        return emit((c) obj2, (d<? super t0>) dVar);
                    }

                    public final Object emit(c cVar, d<? super t0> dVar) {
                        arvioBackMotion.setSwipeEdge$app_playRelease(cVar.f922d);
                        arvioBackMotion.setTouchY$app_playRelease(cVar.f920b);
                        Object objSnapTo = animatable.snapTo(new Float(cVar.f921c), dVar);
                        return objSnapTo == e7.a.f15033i ? objSnapTo : t0.f22605a;
                    }
                };
                this.L$0 = null;
                this.label = 1;
                Object objCollect = jVar.collect(kVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            m0.p(this.$scope, null, 0, new AnonymousClass2(this.$anim, this.$commitDurationMs, this.$motion, null), 3);
            ArvioPredictiveBackKt.rememberArvioPredictiveBack$lambda$2(this.$commit$delegate).invoke();
            return t0.f22605a;
        } catch (CancellationException e5) {
            m0.p(this.$scope, null, 0, new AnonymousClass3(this.$anim, this.$cancelDurationMs, this.$motion, null), 3);
            throw e5;
        }
    }

    @Override // r7.p
    public final Object invoke(na.j<c> jVar, d<t0> dVar) {
        return ((ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1) create(jVar, dVar)).invokeSuspend(t0.f22605a);
    }
}
