package com.arflix.tv.ui.screens.home;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeViewModel$preloadLogosForCategory$targetJob$1", f = "HomeViewModel.kt", l = {4302, 4337}, m = "invokeSuspend", v = 2)
public final class HomeViewModel$preloadLogosForCategory$targetJob$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ int $categoryIndex;
    final /* synthetic */ boolean $prioritizeVisible;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ HomeViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeViewModel$preloadLogosForCategory$targetJob$1(boolean z, HomeViewModel homeViewModel, int i10, d7.d<? super HomeViewModel$preloadLogosForCategory$targetJob$1> dVar) {
        super(2, dVar);
        this.$prioritizeVisible = z;
        this.this$0 = homeViewModel;
        this.$categoryIndex = i10;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        HomeViewModel$preloadLogosForCategory$targetJob$1 homeViewModel$preloadLogosForCategory$targetJob$1 = new HomeViewModel$preloadLogosForCategory$targetJob$1(this.$prioritizeVisible, this.this$0, this.$categoryIndex, dVar);
        homeViewModel$preloadLogosForCategory$targetJob$1.L$0 = obj;
        return homeViewModel$preloadLogosForCategory$targetJob$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x016f, code lost:
    
        if (r0 == r8) goto L68;
     */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01a4 A[Catch: all -> 0x0030, TryCatch #0 {all -> 0x0030, blocks: (B:7:0x0026, B:69:0x0172, B:71:0x0182, B:73:0x018a, B:74:0x0191, B:75:0x01a0, B:77:0x01a4, B:79:0x01b3, B:80:0x01c4, B:82:0x01ca, B:84:0x01d6, B:86:0x01dc, B:87:0x01e0, B:78:0x01ad, B:13:0x003b, B:29:0x006d, B:31:0x0081, B:34:0x0089, B:41:0x00b2, B:43:0x00b6, B:45:0x00c3, B:46:0x00d6, B:48:0x00dc, B:51:0x00ec, B:54:0x00f3, B:56:0x0117, B:58:0x0121, B:60:0x0127, B:62:0x012f, B:63:0x0140, B:65:0x0146, B:66:0x015d, B:44:0x00bd, B:16:0x0042, B:18:0x0046, B:26:0x0061, B:22:0x0054), top: B:99:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01ad A[Catch: all -> 0x0030, TryCatch #0 {all -> 0x0030, blocks: (B:7:0x0026, B:69:0x0172, B:71:0x0182, B:73:0x018a, B:74:0x0191, B:75:0x01a0, B:77:0x01a4, B:79:0x01b3, B:80:0x01c4, B:82:0x01ca, B:84:0x01d6, B:86:0x01dc, B:87:0x01e0, B:78:0x01ad, B:13:0x003b, B:29:0x006d, B:31:0x0081, B:34:0x0089, B:41:0x00b2, B:43:0x00b6, B:45:0x00c3, B:46:0x00d6, B:48:0x00dc, B:51:0x00ec, B:54:0x00f3, B:56:0x0117, B:58:0x0121, B:60:0x0127, B:62:0x012f, B:63:0x0140, B:65:0x0146, B:66:0x015d, B:44:0x00bd, B:16:0x0042, B:18:0x0046, B:26:0x0061, B:22:0x0054), top: B:99:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ca A[Catch: all -> 0x0030, TryCatch #0 {all -> 0x0030, blocks: (B:7:0x0026, B:69:0x0172, B:71:0x0182, B:73:0x018a, B:74:0x0191, B:75:0x01a0, B:77:0x01a4, B:79:0x01b3, B:80:0x01c4, B:82:0x01ca, B:84:0x01d6, B:86:0x01dc, B:87:0x01e0, B:78:0x01ad, B:13:0x003b, B:29:0x006d, B:31:0x0081, B:34:0x0089, B:41:0x00b2, B:43:0x00b6, B:45:0x00c3, B:46:0x00d6, B:48:0x00dc, B:51:0x00ec, B:54:0x00f3, B:56:0x0117, B:58:0x0121, B:60:0x0127, B:62:0x012f, B:63:0x0140, B:65:0x0146, B:66:0x015d, B:44:0x00bd, B:16:0x0042, B:18:0x0046, B:26:0x0061, B:22:0x0054), top: B:99:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01e9  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            Method dump skipped, instruction units count: 545
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeViewModel$preloadLogosForCategory$targetJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeViewModel$preloadLogosForCategory$targetJob$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
