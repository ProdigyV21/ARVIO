package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.Category;
import java.util.List;
import kotlin.Metadata;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$18$1", f = "HomeScreen.kt", l = {870}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeScreen$18$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ boolean $allowHomeBackgroundWork;
    final /* synthetic */ HomeFocusState $focusState;
    final /* synthetic */ State<List<Category>> $latestDisplayCategories$delegate;
    final /* synthetic */ HomeViewModel $viewModel;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$18$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u00042\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lx6/j0;", "", "", "<destruct>", "Lx6/t0;", "<anonymous>", "(Lx6/j0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$18$1$2", f = "HomeScreen.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<x6.j0, d7.d<? super t0>, Object> {
        final /* synthetic */ State<List<Category>> $latestDisplayCategories$delegate;
        final /* synthetic */ HomeViewModel $viewModel;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(HomeViewModel homeViewModel, State<? extends List<Category>> state, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$viewModel = homeViewModel;
            this.$latestDisplayCategories$delegate = state;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$viewModel, this.$latestDisplayCategories$delegate, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Category category;
            x6.j0 j0Var = (x6.j0) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            int iIntValue = ((Number) j0Var.f22587i).intValue();
            int iIntValue2 = ((Number) j0Var.f22588l).intValue();
            boolean zBooleanValue = ((Boolean) j0Var.f22589m).booleanValue();
            t0 t0Var = t0.f22605a;
            if (zBooleanValue || (category = (Category) kotlin.collections.x.p0(iIntValue, HomeScreenKt.HomeScreen$lambda$19(this.$latestDisplayCategories$delegate))) == null) {
                return t0Var;
            }
            this.$viewModel.maybeLoadNextPageForCategory(category.getId(), iIntValue2);
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(x6.j0 j0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(j0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HomeScreenKt$HomeScreen$18$1(boolean z, HomeFocusState homeFocusState, HomeViewModel homeViewModel, State<? extends List<Category>> state, d7.d<? super HomeScreenKt$HomeScreen$18$1> dVar) {
        super(2, dVar);
        this.$allowHomeBackgroundWork = z;
        this.$focusState = homeFocusState;
        this.$viewModel = homeViewModel;
        this.$latestDisplayCategories$delegate = state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.j0 invokeSuspend$lambda$0(HomeFocusState homeFocusState) {
        return new x6.j0(Integer.valueOf(homeFocusState.getCurrentRowIndex()), Integer.valueOf(homeFocusState.getCurrentItemIndex()), Boolean.valueOf(homeFocusState.isSidebarFocused()));
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeScreen$18$1(this.$allowHomeBackgroundWork, this.$focusState, this.$viewModel, this.$latestDisplayCategories$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return t0Var;
        }
        k2.c.G(obj);
        if (this.$allowHomeBackgroundWork) {
            na.j jVarH = y0.h(SnapshotStateKt.snapshotFlow(new r0(this.$focusState, 2)));
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$viewModel, this.$latestDisplayCategories$delegate, null);
            this.label = 1;
            Object objG = y0.g(jVarH, anonymousClass2, this);
            e7.a aVar = e7.a.f15033i;
            if (objG == aVar) {
                return aVar;
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeScreen$18$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
