package com.arflix.tv.data.repository;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/ContinueWatchingItem;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/ContinueWatchingItem;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.TraktRepository$hydrateTopCandidates$2$hydrationTasks$1$1", f = "TraktRepository.kt", l = {1944, 1954}, m = "invokeSuspend", v = 2)
public final class TraktRepository$hydrateTopCandidates$2$hydrationTasks$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super ContinueWatchingItem>, Object> {
    final /* synthetic */ ContinueWatchingCandidate $candidate;
    Object L$0;
    int label;
    final /* synthetic */ TraktRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TraktRepository$hydrateTopCandidates$2$hydrationTasks$1$1(ContinueWatchingCandidate continueWatchingCandidate, TraktRepository traktRepository, d7.d<? super TraktRepository$hydrateTopCandidates$2$hydrationTasks$1$1> dVar) {
        super(2, dVar);
        this.$candidate = continueWatchingCandidate;
        this.this$0 = traktRepository;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new TraktRepository$hydrateTopCandidates$2$hydrationTasks$1$1(this.$candidate, this.this$0, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0126, code lost:
    
        if (r0 == r15) goto L45;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0072 A[Catch: Exception -> 0x0022, TryCatch #0 {Exception -> 0x0022, blocks: (B:7:0x001a, B:46:0x0129, B:48:0x0131, B:53:0x0145, B:55:0x014b, B:57:0x0154, B:59:0x015a, B:60:0x015e, B:64:0x016b, B:66:0x018e, B:71:0x01ae, B:70:0x01a9, B:14:0x0031, B:23:0x006a, B:25:0x0072, B:27:0x007b, B:29:0x0081, B:30:0x0085, B:34:0x0092, B:36:0x00af, B:41:0x00c4, B:40:0x00bf, B:17:0x003b, B:20:0x004b, B:43:0x0109), top: B:79:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0081 A[Catch: Exception -> 0x0022, TryCatch #0 {Exception -> 0x0022, blocks: (B:7:0x001a, B:46:0x0129, B:48:0x0131, B:53:0x0145, B:55:0x014b, B:57:0x0154, B:59:0x015a, B:60:0x015e, B:64:0x016b, B:66:0x018e, B:71:0x01ae, B:70:0x01a9, B:14:0x0031, B:23:0x006a, B:25:0x0072, B:27:0x007b, B:29:0x0081, B:30:0x0085, B:34:0x0092, B:36:0x00af, B:41:0x00c4, B:40:0x00bf, B:17:0x003b, B:20:0x004b, B:43:0x0109), top: B:79:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bf A[Catch: Exception -> 0x0022, TryCatch #0 {Exception -> 0x0022, blocks: (B:7:0x001a, B:46:0x0129, B:48:0x0131, B:53:0x0145, B:55:0x014b, B:57:0x0154, B:59:0x015a, B:60:0x015e, B:64:0x016b, B:66:0x018e, B:71:0x01ae, B:70:0x01a9, B:14:0x0031, B:23:0x006a, B:25:0x0072, B:27:0x007b, B:29:0x0081, B:30:0x0085, B:34:0x0092, B:36:0x00af, B:41:0x00c4, B:40:0x00bf, B:17:0x003b, B:20:0x004b, B:43:0x0109), top: B:79:0x0010 }] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r48) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 558
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.TraktRepository$hydrateTopCandidates$2$hydrationTasks$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super ContinueWatchingItem> dVar) {
        return ((TraktRepository$hydrateTopCandidates$2$hydrationTasks$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
