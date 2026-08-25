package androidx.credentials.playservices;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements com.google.android.gms.tasks.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2398i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ HiddenActivity f2399l;

    public /* synthetic */ e(HiddenActivity hiddenActivity, int i10) {
        this.f2398i = i10;
        this.f2399l = hiddenActivity;
    }

    @Override // com.google.android.gms.tasks.d
    public final void onFailure(Exception exc) {
        switch (this.f2398i) {
            case 0:
                HiddenActivity.handleCreatePublicKeyCredential$lambda$2$lambda$1(this.f2399l, exc);
                break;
            case 1:
                HiddenActivity.handleCreatePassword$lambda$14$lambda$13(this.f2399l, exc);
                break;
            case 2:
                HiddenActivity.handleGetSignInIntent$lambda$6$lambda$5(this.f2399l, exc);
                break;
            default:
                HiddenActivity.handleBeginSignIn$lambda$10$lambda$9(this.f2399l, exc);
                break;
        }
    }
}
