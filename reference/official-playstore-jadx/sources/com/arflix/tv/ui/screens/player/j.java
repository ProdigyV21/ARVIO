package com.arflix.tv.ui.screens.player;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9573i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PlayerViewModel f9574l;

    public /* synthetic */ j(PlayerViewModel playerViewModel, int i10) {
        this.f9573i = i10;
        this.f9574l = playerViewModel;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9573i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$11$3$0(this.f9574l);
            case 1:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$11$4$0(this.f9574l);
            case 2:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$11$5$0(this.f9574l);
            case 3:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$7$0$0(this.f9574l);
            case 4:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$19$0$0$1$0$0(this.f9574l);
            case 5:
                return this.f9574l.aiApiKey;
            case 6:
                return this.f9574l.aiModel;
            default:
                return this.f9574l.aiApiKey;
        }
    }
}
