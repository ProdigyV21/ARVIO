package androidx.compose.material3;

import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.FloatingActionButtonElevation$animateElevation$1$1", f = "FloatingActionButton.kt", l = {509}, m = "invokeSuspend")
public final class FloatingActionButtonElevation$animateElevation$1$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ FloatingActionButtonElevationAnimatable $animatable;
    int label;
    final /* synthetic */ FloatingActionButtonElevation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FloatingActionButtonElevation$animateElevation$1$1(FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable, FloatingActionButtonElevation floatingActionButtonElevation, d7.d<? super FloatingActionButtonElevation$animateElevation$1$1> dVar) {
        super(2, dVar);
        this.$animatable = floatingActionButtonElevationAnimatable;
        this.this$0 = floatingActionButtonElevation;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new FloatingActionButtonElevation$animateElevation$1$1(this.$animatable, this.this$0, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable = this.$animatable;
            float f10 = this.this$0.defaultElevation;
            float f11 = this.this$0.pressedElevation;
            float f12 = this.this$0.hoveredElevation;
            float f13 = this.this$0.focusedElevation;
            this.label = 1;
            Object objM1586updateElevationlDy3nrA = floatingActionButtonElevationAnimatable.m1586updateElevationlDy3nrA(f10, f11, f12, f13, this);
            e7.a aVar = e7.a.f15033i;
            if (objM1586updateElevationlDy3nrA == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((FloatingActionButtonElevation$animateElevation$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
