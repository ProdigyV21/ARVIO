package com.arflix.tv.ui.screens.tv.live;

import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001j\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003`\u0004*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "<anonymous>", "(Lka/k0;)Ljava/util/HashMap;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$17$1$index$1", f = "LiveTvScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class LiveTvScreenKt$LiveTvScreen$17$1$index$1 extends f7.j implements r7.p<ka.k0, d7.d<? super HashMap<String, Integer>>, Object> {
    final /* synthetic */ List<EnrichedChannel> $collapsed;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveTvScreenKt$LiveTvScreen$17$1$index$1(List<EnrichedChannel> list, d7.d<? super LiveTvScreenKt$LiveTvScreen$17$1$index$1> dVar) {
        super(2, dVar);
        this.$collapsed = list;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new LiveTvScreenKt$LiveTvScreen$17$1$index$1(this.$collapsed, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        HashMap map = new HashMap(this.$collapsed.size());
        int i10 = 0;
        for (Object obj2 : this.$collapsed) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            map.put(((EnrichedChannel) obj2).getId(), new Integer(i10));
            i10 = i11;
        }
        return map;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super HashMap<String, Integer>> dVar) {
        return ((LiveTvScreenKt$LiveTvScreen$17$1$index$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
