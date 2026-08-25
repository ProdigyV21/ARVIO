package com.arflix.tv.ui.screens.home;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9292i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ HomeFocusState f9293l;

    public /* synthetic */ r0(HomeFocusState homeFocusState, int i10) {
        this.f9292i = i10;
        this.f9293l = homeFocusState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9292i) {
            case 0:
                return HomeScreenKt$HomeScreen$14$1.invokeSuspend$lambda$0(this.f9293l);
            case 1:
                return HomeScreenKt$HomeScreen$16$1.invokeSuspend$lambda$0(this.f9293l);
            case 2:
                return HomeScreenKt$HomeScreen$18$1.invokeSuspend$lambda$0(this.f9293l);
            default:
                return Long.valueOf(this.f9293l.getLastNavEventTime());
        }
    }
}
