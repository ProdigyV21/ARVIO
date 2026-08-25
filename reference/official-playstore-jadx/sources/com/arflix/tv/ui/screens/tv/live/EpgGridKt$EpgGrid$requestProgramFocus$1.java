package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.ui.focus.FocusRequester;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$requestProgramFocus$1", f = "EpgGrid.kt", l = {193}, m = "invokeSuspend", v = 2)
public final class EpgGridKt$EpgGrid$requestProgramFocus$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ LazyListState $channelListState;
    final /* synthetic */ List<FocusRequester> $requesters;
    final /* synthetic */ int $rowIdx;
    final /* synthetic */ int $safeTargetIdx;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EpgGridKt$EpgGrid$requestProgramFocus$1(LazyListState lazyListState, int i10, List<FocusRequester> list, int i11, d7.d<? super EpgGridKt$EpgGrid$requestProgramFocus$1> dVar) {
        super(2, dVar);
        this.$channelListState = lazyListState;
        this.$rowIdx = i10;
        this.$requesters = list;
        this.$safeTargetIdx = i11;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        EpgGridKt$EpgGrid$requestProgramFocus$1 epgGridKt$EpgGrid$requestProgramFocus$1 = new EpgGridKt$EpgGrid$requestProgramFocus$1(this.$channelListState, this.$rowIdx, this.$requesters, this.$safeTargetIdx, dVar);
        epgGridKt$EpgGrid$requestProgramFocus$1.L$0 = obj;
        return epgGridKt$EpgGrid$requestProgramFocus$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        EpgGridKt$EpgGrid$requestProgramFocus$1 epgGridKt$EpgGrid$requestProgramFocus$1;
        ka.k0 k0Var = (ka.k0) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            LazyListState lazyListState = this.$channelListState;
            int i11 = this.$rowIdx;
            this.L$0 = k0Var;
            this.label = 1;
            epgGridKt$EpgGrid$requestProgramFocus$1 = this;
            Object objScrollToItem$default = LazyListState.scrollToItem$default(lazyListState, i11, 0, epgGridKt$EpgGrid$requestProgramFocus$1, 2, null);
            e7.a aVar = e7.a.f15033i;
            if (objScrollToItem$default == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            epgGridKt$EpgGrid$requestProgramFocus$1 = this;
        }
        try {
            epgGridKt$EpgGrid$requestProgramFocus$1.$requesters.get(epgGridKt$EpgGrid$requestProgramFocus$1.$safeTargetIdx).requestFocus();
        } catch (Throwable unused) {
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((EpgGridKt$EpgGrid$requestProgramFocus$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
