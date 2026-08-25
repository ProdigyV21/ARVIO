package com.arflix.tv.ui.screens.home;

import com.arflix.tv.data.model.Category;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$sportsHomeRows$3$1", f = "HomeViewModel.kt", l = {201}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$sportsHomeRows$3$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ na.j<List<Category>> $flow;
    final /* synthetic */ na.q0<List<Category>> $state;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HomeViewModel$sportsHomeRows$3$1(na.j<? extends List<Category>> jVar, na.q0<List<Category>> q0Var, d7.d<? super HomeViewModel$sportsHomeRows$3$1> dVar) {
        super(2, dVar);
        this.$flow = jVar;
        this.$state = q0Var;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeViewModel$sportsHomeRows$3$1(this.$flow, this.$state, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            na.j<List<Category>> jVar = this.$flow;
            final na.q0<List<Category>> q0Var = this.$state;
            na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.home.HomeViewModel$sportsHomeRows$3$1.1
                @Override // na.k
                public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                    return emit((List<Category>) obj2, (d7.d<? super t0>) dVar);
                }

                public final Object emit(List<Category> list, d7.d<? super t0> dVar) {
                    q0Var.setValue(list);
                    return t0.f22605a;
                }
            };
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
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeViewModel$sportsHomeRows$3$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
