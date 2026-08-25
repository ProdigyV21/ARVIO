package androidx.media3.session;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class e implements com.google.common.base.m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3958a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f3959b;

    public /* synthetic */ e(int i10, int i11) {
        this.f3958a = i11;
        this.f3959b = i10;
    }

    @Override // com.google.common.base.m
    public final Object apply(Object obj) {
        switch (this.f3958a) {
            case 0:
                return ConnectionState.lambda$fromBundle$0(this.f3959b, (Bundle) obj);
            case 1:
                return ConnectionState.lambda$fromBundle$1(this.f3959b, (Bundle) obj);
            case 2:
                return ConnectionState.lambda$fromBundle$2(this.f3959b, (Bundle) obj);
            case 3:
                return MediaControllerStub.lambda$onSetMediaButtonPreferences$4(this.f3959b, (Bundle) obj);
            default:
                return MediaControllerStub.lambda$onSetCustomLayout$2(this.f3959b, (Bundle) obj);
        }
    }
}
