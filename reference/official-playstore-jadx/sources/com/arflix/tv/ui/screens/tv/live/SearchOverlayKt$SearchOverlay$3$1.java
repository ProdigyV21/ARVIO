package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.IptvNowNext;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$3$1", f = "SearchOverlay.kt", l = {113, 124}, m = "invokeSuspend", v = 2)
public final class SearchOverlayKt$SearchOverlay$3$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ List<EnrichedChannel> $channels;
    final /* synthetic */ MutableState<String> $debounced$delegate;
    final /* synthetic */ GuideMatchLabels $guideLabels;
    final /* synthetic */ String $nowFormat;
    final /* synthetic */ Map<String, IptvNowNext> $nowNext;
    final /* synthetic */ MutableState<List<SearchResult>> $results$delegate;
    final /* synthetic */ r7.p<String, d7.d<? super List<EnrichedChannel>>, Object> $searchProvider;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$3$1$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/ui/screens/tv/live/SearchResult;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$3$1$4", f = "SearchOverlay.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass4 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends SearchResult>>, Object> {
        final /* synthetic */ List<EnrichedChannel> $channels;
        final /* synthetic */ GuideMatchLabels $guideLabels;
        final /* synthetic */ Map<String, IptvNowNext> $nowNext;
        final /* synthetic */ String $q;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(List<EnrichedChannel> list, Map<String, IptvNowNext> map, String str, GuideMatchLabels guideMatchLabels, d7.d<? super AnonymousClass4> dVar) {
            super(2, dVar);
            this.$channels = list;
            this.$nowNext = map;
            this.$q = str;
            this.$guideLabels = guideMatchLabels;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00b0  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x00bd  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00db  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00e8  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x00fd A[EDGE_INSN: B:58:0x00fd->B:59:0x00fe BREAK  A[LOOP:1: B:53:0x00df->B:97:?]] */
        /* JADX WARN: Removed duplicated region for block: B:95:0x00fd A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:98:0x00d2 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final x6.x invokeSuspend$lambda$0(java.util.Map r8, java.lang.String r9, com.arflix.tv.ui.screens.tv.live.GuideMatchLabels r10, com.arflix.tv.ui.screens.tv.live.EnrichedChannel r11) {
            /*
                Method dump skipped, instruction units count: 404
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$3$1.AnonymousClass4.invokeSuspend$lambda$0(java.util.Map, java.lang.String, com.arflix.tv.ui.screens.tv.live.GuideMatchLabels, com.arflix.tv.ui.screens.tv.live.EnrichedChannel):x6.x");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$lambda$1(x6.x xVar) {
            return ((Number) xVar.f22609l).intValue() > 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final SearchResult invokeSuspend$lambda$3(x6.x xVar) {
            return (SearchResult) xVar.f22608i;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass4(this.$channels, this.$nowNext, this.$q, this.$guideLabels, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return ga.r.U(ga.r.S(new ga.t(new ga.l(ga.r.I(new ga.t(new ga.p(this.$channels, 3), new c(this.$nowNext, this.$q, this.$guideLabels, 1)), new n2(3)), new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$3$1$4$invokeSuspend$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return com.google.common.util.concurrent.r0.e((Integer) ((x6.x) t10).f22609l, (Integer) ((x6.x) t2).f22609l);
                }
            }, 1), new n2(4)), 200));
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<SearchResult>> dVar) {
            return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SearchOverlayKt$SearchOverlay$3$1(List<EnrichedChannel> list, r7.p<? super String, ? super d7.d<? super List<EnrichedChannel>>, ? extends Object> pVar, MutableState<String> mutableState, Map<String, IptvNowNext> map, String str, MutableState<List<SearchResult>> mutableState2, GuideMatchLabels guideMatchLabels, d7.d<? super SearchOverlayKt$SearchOverlay$3$1> dVar) {
        super(2, dVar);
        this.$channels = list;
        this.$searchProvider = pVar;
        this.$debounced$delegate = mutableState;
        this.$nowNext = map;
        this.$nowFormat = str;
        this.$results$delegate = mutableState2;
        this.$guideLabels = guideMatchLabels;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SearchOverlayKt$SearchOverlay$3$1(this.$channels, this.$searchProvider, this.$debounced$delegate, this.$nowNext, this.$nowFormat, this.$results$delegate, this.$guideLabels, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00bc, code lost:
    
        if (r15 == r7) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x016b, code lost:
    
        if (r15 == r7) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x016d, code lost:
    
        return r7;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x014f  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.SearchOverlayKt$SearchOverlay$3$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((SearchOverlayKt$SearchOverlay$3$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
