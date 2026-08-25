package com.arflix.tv.ui.screens.collections;

import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.tv.foundation.lazy.grid.b1;
import androidx.tv.foundation.lazy.grid.p0;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.x;
import na.y0;
import x6.j0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionItemsGrid$1$1", f = "CollectionDetailsScreen.kt", l = {898}, m = "invokeSuspend", v = 2)
public final class CollectionDetailsScreenKt$CollectionItemsGrid$1$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ b1 $gridState;
    final /* synthetic */ State<Integer> $latestGridColumns$delegate;
    final /* synthetic */ State<List<MediaItem>> $latestItems$delegate;
    final /* synthetic */ State<r7.a<t0>> $latestOnNearEnd$delegate;
    final /* synthetic */ State<r7.l<List<MediaItem>, t0>> $latestOnVisibleItemsChanged$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CollectionDetailsScreenKt$CollectionItemsGrid$1$1(b1 b1Var, State<? extends r7.a<t0>> state, State<? extends List<MediaItem>> state2, State<Integer> state3, State<? extends r7.l<? super List<MediaItem>, t0>> state4, d7.d<? super CollectionDetailsScreenKt$CollectionItemsGrid$1$1> dVar) {
        super(2, dVar);
        this.$gridState = b1Var;
        this.$latestOnNearEnd$delegate = state;
        this.$latestItems$delegate = state2;
        this.$latestGridColumns$delegate = state3;
        this.$latestOnVisibleItemsChanged$delegate = state4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final j0 invokeSuspend$lambda$0(b1 b1Var) {
        androidx.tv.foundation.lazy.grid.t0 t0Var = (androidx.tv.foundation.lazy.grid.t0) b1Var.f5154b.getValue();
        p0 p0Var = (p0) x.y0(t0Var.getVisibleItemsInfo());
        return new j0(Integer.valueOf(p0Var != null ? p0Var.getIndex() : 0), Integer.valueOf(t0Var.getTotalItemsCount()), ga.r.U(ga.r.I(new ga.t(new ga.p(t0Var.getVisibleItemsInfo(), 3), new q(0)), new q(1))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int invokeSuspend$lambda$0$0(p0 p0Var) {
        return p0Var.getIndex() - 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$0$1(int i10) {
        return i10 >= 0;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new CollectionDetailsScreenKt$CollectionItemsGrid$1$1(this.$gridState, this.$latestOnNearEnd$delegate, this.$latestItems$delegate, this.$latestGridColumns$delegate, this.$latestOnVisibleItemsChanged$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            na.j jVarH = y0.h(SnapshotStateKt.snapshotFlow(new p(this.$gridState, 0)));
            final State<r7.a<t0>> state = this.$latestOnNearEnd$delegate;
            final State<List<MediaItem>> state2 = this.$latestItems$delegate;
            final State<Integer> state3 = this.$latestGridColumns$delegate;
            final State<r7.l<List<MediaItem>, t0>> state4 = this.$latestOnVisibleItemsChanged$delegate;
            na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt$CollectionItemsGrid$1$1.2
                public final Object emit(j0 j0Var, d7.d<? super t0> dVar) {
                    int iIntValue = ((Number) j0Var.f22587i).intValue();
                    int iIntValue2 = ((Number) j0Var.f22588l).intValue();
                    List list = (List) j0Var.f22589m;
                    if (iIntValue2 > 12 && iIntValue >= iIntValue2 - 3) {
                        CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$3(state).invoke();
                    }
                    if (!list.isEmpty()) {
                        Integer num = (Integer) x.C0(list);
                        int iIntValue3 = num != null ? num.intValue() : 0;
                        int i11 = iIntValue3 >= 0 ? iIntValue3 : 0;
                        List listCollectionItemsGrid__F44eQ8$lambda$0 = CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$0(state2);
                        Integer num2 = (Integer) x.z0(list);
                        int iCollectionItemsGrid__F44eQ8$lambda$1 = CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$1(state3) + (num2 != null ? num2.intValue() : i11);
                        int iW = t7.a.w(listCollectionItemsGrid__F44eQ8$lambda$0);
                        if (iCollectionItemsGrid__F44eQ8$lambda$1 > iW) {
                            iCollectionItemsGrid__F44eQ8$lambda$1 = iW;
                        }
                        if (i11 <= iCollectionItemsGrid__F44eQ8$lambda$1) {
                            CollectionDetailsScreenKt.CollectionItemsGrid__F44eQ8$lambda$2(state4).invoke(listCollectionItemsGrid__F44eQ8$lambda$0.subList(i11, iCollectionItemsGrid__F44eQ8$lambda$1 + 1));
                        }
                    }
                    return t0.f22605a;
                }

                @Override // na.k
                public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                    return emit((j0) obj2, (d7.d<? super t0>) dVar);
                }
            };
            this.label = 1;
            Object objCollect = jVarH.collect(kVar, this);
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
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((CollectionDetailsScreenKt$CollectionItemsGrid$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
