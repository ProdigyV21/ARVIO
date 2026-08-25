package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class r1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4127i = 2;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaLibraryServiceLegacyStub f4128l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f4129m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MediaSession.ControllerInfo f4130n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MediaBrowserServiceCompat.Result f4131o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Bundle f4132p;

    public /* synthetic */ r1(MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub, MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, Bundle bundle, String str) {
        this.f4128l = mediaLibraryServiceLegacyStub;
        this.f4130n = controllerInfo;
        this.f4131o = result;
        this.f4132p = bundle;
        this.f4129m = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4127i) {
            case 0:
                this.f4128l.lambda$onSearch$5(this.f4130n, this.f4131o, this.f4129m, this.f4132p);
                break;
            case 1:
                MediaBrowserServiceCompat.Result result = this.f4131o;
                Bundle bundle = this.f4132p;
                this.f4128l.lambda$onCustomAction$6(this.f4129m, this.f4130n, result, bundle);
                break;
            default:
                this.f4128l.lambda$onLoadChildren$3(this.f4130n, this.f4131o, this.f4132p, this.f4129m);
                break;
        }
    }

    public /* synthetic */ r1(MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub, MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, String str, Bundle bundle) {
        this.f4128l = mediaLibraryServiceLegacyStub;
        this.f4130n = controllerInfo;
        this.f4131o = result;
        this.f4129m = str;
        this.f4132p = bundle;
    }

    public /* synthetic */ r1(MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub, String str, MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, Bundle bundle) {
        this.f4128l = mediaLibraryServiceLegacyStub;
        this.f4129m = str;
        this.f4130n = controllerInfo;
        this.f4131o = result;
        this.f4132p = bundle;
    }
}
