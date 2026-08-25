package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.Recomposer;
import androidx.compose.ui.platform.WindowRecomposer_androidKt;
import androidx.lifecycle.y;
import ka.k0;
import ka.m0;
import ka.o2;
import ka.v1;
import kotlin.Metadata;
import na.h1;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1", f = "WindowRecomposer.android.kt", l = {394}, m = "invokeSuspend")
public final class WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Recomposer $recomposer;
    final /* synthetic */ WindowRecomposer_androidKt.AnonymousClass2 $self;
    final /* synthetic */ y $source;
    final /* synthetic */ kotlin.jvm.internal.k0 $systemDurationScaleSettingConsumer;
    final /* synthetic */ View $this_createLifecycleAwareWindowRecomposer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1(kotlin.jvm.internal.k0 k0Var, Recomposer recomposer, y yVar, WindowRecomposer_androidKt.AnonymousClass2 anonymousClass2, View view, d7.d<? super WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1> dVar) {
        super(2, dVar);
        this.$systemDurationScaleSettingConsumer = k0Var;
        this.$recomposer = recomposer;
        this.$source = yVar;
        this.$self = anonymousClass2;
        this.$this_createLifecycleAwareWindowRecomposer = view;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1 = new WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1(this.$systemDurationScaleSettingConsumer, this.$recomposer, this.$source, this.$self, this.$this_createLifecycleAwareWindowRecomposer, dVar);
        windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1.L$0 = obj;
        return windowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [int] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v13, types: [ka.v1] */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2, types: [ka.v1] */
    /* JADX WARN: Type inference failed for: r0v5 */
    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        o2 o2VarP;
        ?? r02 = this.label;
        try {
            if (r02 == 0) {
                k2.c.G(obj);
                k0 k0Var = (k0) this.L$0;
                try {
                    MotionDurationScaleImpl motionDurationScaleImpl = (MotionDurationScaleImpl) this.$systemDurationScaleSettingConsumer.f19746i;
                    if (motionDurationScaleImpl != null) {
                        h1 animationScaleFlowFor = WindowRecomposer_androidKt.getAnimationScaleFlowFor(this.$this_createLifecycleAwareWindowRecomposer.getContext().getApplicationContext());
                        motionDurationScaleImpl.setScaleFactor(((Number) animationScaleFlowFor.getValue()).floatValue());
                        o2VarP = m0.p(k0Var, null, 0, new WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1$1$1(animationScaleFlowFor, motionDurationScaleImpl, null), 3);
                    } else {
                        o2VarP = null;
                    }
                    Recomposer recomposer = this.$recomposer;
                    this.L$0 = o2VarP;
                    this.label = 1;
                    Object objRunRecomposeAndApplyChanges = recomposer.runRecomposeAndApplyChanges(this);
                    e7.a aVar = e7.a.f15033i;
                    r02 = o2VarP;
                    if (objRunRecomposeAndApplyChanges == aVar) {
                        return aVar;
                    }
                } catch (Throwable th) {
                    th = th;
                    r02 = 0;
                    if (r02 != 0) {
                        r02.cancel(null);
                    }
                    this.$source.getLifecycle().removeObserver(this.$self);
                    throw th;
                }
            } else {
                if (r02 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                v1 v1Var = (v1) this.L$0;
                k2.c.G(obj);
                r02 = v1Var;
            }
            if (r02 != 0) {
                r02.cancel(null);
            }
            this.$source.getLifecycle().removeObserver(this.$self);
            return t0.f22605a;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2$onStateChanged$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
