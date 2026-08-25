package com.arflix.tv.ui.screens.player;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9622i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PlayerViewModel f9623l;

    public /* synthetic */ l1(PlayerViewModel playerViewModel, int i10) {
        this.f9622i = i10;
        this.f9623l = playerViewModel;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9622i) {
            case 0:
                long jLongValue = ((Long) obj2).longValue();
                return PlayerScreenKt$PlayerScreen$13$1.invokeSuspend$lambda$0(this.f9623l, (byte[]) obj, jLongValue);
            default:
                return PlayerViewModel.translationManager$lambda$2$1(this.f9623l, ((Boolean) obj).booleanValue(), (String) obj2);
        }
    }
}
