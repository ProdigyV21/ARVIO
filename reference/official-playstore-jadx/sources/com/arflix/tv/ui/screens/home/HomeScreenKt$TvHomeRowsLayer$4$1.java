package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.Category;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$TvHomeRowsLayer$4$1", f = "HomeScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$TvHomeRowsLayer$4$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<Category> $categories;
    final /* synthetic */ HomeFocusState $focusState;
    final /* synthetic */ MutableState<String> $focusedCategoryId$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$TvHomeRowsLayer$4$1(List<Category> list, HomeFocusState homeFocusState, MutableState<String> mutableState, d7.d<? super HomeScreenKt$TvHomeRowsLayer$4$1> dVar) {
        super(2, dVar);
        this.$categories = list;
        this.$focusState = homeFocusState;
        this.$focusedCategoryId$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$TvHomeRowsLayer$4$1(this.$categories, this.$focusState, this.$focusedCategoryId$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        String strTvHomeRowsLayer_GVfq81o$lambda$3 = HomeScreenKt.TvHomeRowsLayer_GVfq81o$lambda$3(this.$focusedCategoryId$delegate);
        t0 t0Var = t0.f22605a;
        if (strTvHomeRowsLayer_GVfq81o$lambda$3 == null) {
            return t0Var;
        }
        Iterator<Category> it = this.$categories.iterator();
        int i10 = 0;
        while (true) {
            if (!it.hasNext()) {
                i10 = -1;
                break;
            }
            if (kotlin.jvm.internal.p.a(it.next().getId(), strTvHomeRowsLayer_GVfq81o$lambda$3)) {
                break;
            }
            i10++;
        }
        if (i10 >= 0 && i10 != this.$focusState.getCurrentRowIndex()) {
            this.$focusState.setCurrentRowIndex(i10);
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$TvHomeRowsLayer$4$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
