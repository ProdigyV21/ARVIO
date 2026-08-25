package com.arflix.tv.ui.screens.home;

import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.SnapshotStateKt;
import com.arflix.tv.data.model.Category;
import kotlin.Metadata;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$MobileHomeRowsLayer$6$1$4$1$1", f = "HomeScreen.kt", l = {2841}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$MobileHomeRowsLayer$6$1$4$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Category $category;
    final /* synthetic */ r7.p<String, Integer, t0> $onCategoryVisiblePosition;
    final /* synthetic */ LazyListState $rowState;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeScreenKt$MobileHomeRowsLayer$6$1$4$1$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "lastVisible", "Lx6/t0;", "<anonymous>", "(I)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$MobileHomeRowsLayer$6$1$4$1$1$2", f = "HomeScreen.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<Integer, d7.d<? super t0>, Object> {
        final /* synthetic */ Category $category;
        final /* synthetic */ r7.p<String, Integer, t0> $onCategoryVisiblePosition;
        /* synthetic */ int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(r7.p<? super String, ? super Integer, t0> pVar, Category category, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$onCategoryVisiblePosition = pVar;
            this.$category = category;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$onCategoryVisiblePosition, this.$category, dVar);
            anonymousClass2.I$0 = ((Number) obj).intValue();
            return anonymousClass2;
        }

        public final Object invoke(int i10, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.I$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (i10 >= 0) {
                this.$onCategoryVisiblePosition.invoke(this.$category.getId(), new Integer(i10));
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), (d7.d<? super t0>) obj2);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public HomeScreenKt$MobileHomeRowsLayer$6$1$4$1$1(LazyListState lazyListState, r7.p<? super String, ? super Integer, t0> pVar, Category category, d7.d<? super HomeScreenKt$MobileHomeRowsLayer$6$1$4$1$1> dVar) {
        super(2, dVar);
        this.$rowState = lazyListState;
        this.$onCategoryVisiblePosition = pVar;
        this.$category = category;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$MobileHomeRowsLayer$6$1$4$1$1(this.$rowState, this.$onCategoryVisiblePosition, this.$category, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            final LazyListState lazyListState = this.$rowState;
            na.j jVarH = y0.h(SnapshotStateKt.snapshotFlow(new r7.a<Integer>() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$MobileHomeRowsLayer$6$1$4$1$1.1
                @Override // r7.a
                public final Integer invoke() {
                    LazyListItemInfo lazyListItemInfo = (LazyListItemInfo) kotlin.collections.x.y0(lazyListState.getLayoutInfo().getVisibleItemsInfo());
                    return Integer.valueOf(lazyListItemInfo != null ? lazyListItemInfo.getIndex() : -1);
                }
            }));
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$onCategoryVisiblePosition, this.$category, null);
            this.label = 1;
            Object objG = y0.g(jVarH, anonymousClass2, this);
            e7.a aVar = e7.a.f15033i;
            if (objG == aVar) {
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
        return ((HomeScreenKt$MobileHomeRowsLayer$6$1$4$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
