package androidx.media3.session;

import androidx.media3.session.MediaSessionLegacyStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class r2 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4133i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ PlayerWrapper f4134l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4135m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f4136n;

    public /* synthetic */ r2(int i10, int i11, int i12, PlayerWrapper playerWrapper) {
        this.f4133i = i12;
        this.f4134l = playerWrapper;
        this.f4135m = i10;
        this.f4136n = i11;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4133i) {
            case 0:
                MediaSessionLegacyStub.AnonymousClass3.lambda$onSetVolumeTo$0(this.f4134l, this.f4135m, this.f4136n);
                break;
            default:
                MediaSessionLegacyStub.AnonymousClass3.lambda$onAdjustVolume$1(this.f4134l, this.f4135m, this.f4136n);
                break;
        }
    }
}
