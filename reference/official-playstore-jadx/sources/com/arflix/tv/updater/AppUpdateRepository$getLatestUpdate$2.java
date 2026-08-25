package com.arflix.tv.updater;

import d7.d;
import f7.e;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.d0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lcom/arflix/tv/updater/AppUpdate;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@e(c = "com.arflix.tv.updater.AppUpdateRepository$getLatestUpdate$2", f = "AppUpdateRepository.kt", l = {}, m = "invokeSuspend", v = 2)
public final class AppUpdateRepository$getLatestUpdate$2 extends j implements p<k0, d<? super d0>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AppUpdateRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppUpdateRepository$getLatestUpdate$2(AppUpdateRepository appUpdateRepository, d<? super AppUpdateRepository$getLatestUpdate$2> dVar) {
        super(2, dVar);
        this.this$0 = appUpdateRepository;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        AppUpdateRepository$getLatestUpdate$2 appUpdateRepository$getLatestUpdate$2 = new AppUpdateRepository$getLatestUpdate$2(this.this$0, dVar);
        appUpdateRepository$getLatestUpdate$2.L$0 = obj;
        return appUpdateRepository$getLatestUpdate$2;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x009d A[Catch: all -> 0x0049, TryCatch #2 {all -> 0x0049, blocks: (B:6:0x0039, B:8:0x003f, B:10:0x0044, B:18:0x0053, B:20:0x0061, B:22:0x0067, B:24:0x006d, B:26:0x0073, B:41:0x0091, B:43:0x009d, B:45:0x00a3, B:52:0x00b0, B:56:0x00b9, B:61:0x00d6, B:62:0x00e9, B:33:0x0080, B:35:0x0086, B:63:0x00ea, B:64:0x00fd, B:65:0x00fe, B:66:0x0111, B:67:0x0112, B:68:0x0125, B:69:0x0126, B:70:0x0146), top: B:83:0x0039, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d6 A[Catch: all -> 0x0049, TRY_ENTER, TryCatch #2 {all -> 0x0049, blocks: (B:6:0x0039, B:8:0x003f, B:10:0x0044, B:18:0x0053, B:20:0x0061, B:22:0x0067, B:24:0x006d, B:26:0x0073, B:41:0x0091, B:43:0x009d, B:45:0x00a3, B:52:0x00b0, B:56:0x00b9, B:61:0x00d6, B:62:0x00e9, B:33:0x0080, B:35:0x0086, B:63:0x00ea, B:64:0x00fd, B:65:0x00fe, B:66:0x0111, B:67:0x0112, B:68:0x0125, B:69:0x0126, B:70:0x0146), top: B:83:0x0039, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ea A[Catch: all -> 0x0049, TryCatch #2 {all -> 0x0049, blocks: (B:6:0x0039, B:8:0x003f, B:10:0x0044, B:18:0x0053, B:20:0x0061, B:22:0x0067, B:24:0x006d, B:26:0x0073, B:41:0x0091, B:43:0x009d, B:45:0x00a3, B:52:0x00b0, B:56:0x00b9, B:61:0x00d6, B:62:0x00e9, B:33:0x0080, B:35:0x0086, B:63:0x00ea, B:64:0x00fd, B:65:0x00fe, B:66:0x0111, B:67:0x0112, B:68:0x0125, B:69:0x0126, B:70:0x0146), top: B:83:0x0039, outer: #0 }] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 352
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.updater.AppUpdateRepository$getLatestUpdate$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d<? super d0> dVar) {
        return ((AppUpdateRepository$getLatestUpdate$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
