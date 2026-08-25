package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class s0 implements Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4140i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaControllerImplBase f4141l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f4142m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f4143n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f4144o;

    public /* synthetic */ s0(MediaControllerImplBase mediaControllerImplBase, boolean z, boolean z5, int i10, int i11) {
        this.f4140i = i11;
        this.f4141l = mediaControllerImplBase;
        this.f4142m = z;
        this.f4143n = z5;
        this.f4144o = i10;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object obj) {
        switch (this.f4140i) {
            case 0:
                this.f4141l.lambda$onSetMediaButtonPreferences$118(this.f4142m, this.f4143n, this.f4144o, (MediaController.Listener) obj);
                break;
            default:
                this.f4141l.lambda$onSetCustomLayout$117(this.f4142m, this.f4143n, this.f4144o, (MediaController.Listener) obj);
                break;
        }
    }
}
