package com.arflix.tv;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7732i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MainActivity f7733l;

    public /* synthetic */ f(MainActivity mainActivity, int i10) {
        this.f7732i = i10;
        this.f7733l = mainActivity;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f7732i) {
            case 0:
                return MainActivity.onCreate$lambda$4$18$0$1$0(this.f7733l);
            case 1:
                return MainActivity.onCreate$lambda$4$18$0$2$0(this.f7733l);
            case 2:
                return MainActivity.onCreate$lambda$4$18$0$3$0(this.f7733l);
            default:
                return MainActivity.onCreate$lambda$6(this.f7733l);
        }
    }
}
