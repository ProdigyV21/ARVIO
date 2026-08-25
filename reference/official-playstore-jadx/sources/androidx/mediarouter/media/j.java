package androidx.mediarouter.media;

import android.media.MediaRouter2$RouteCallback;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class j extends MediaRouter2$RouteCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l f4417b;

    public /* synthetic */ j(l lVar, int i10) {
        this.f4416a = i10;
        this.f4417b = lVar;
    }

    public void onRoutesAdded(List list) {
        switch (this.f4416a) {
            case 0:
                this.f4417b.j();
                break;
            default:
                super.onRoutesAdded(list);
                break;
        }
    }

    public void onRoutesChanged(List list) {
        switch (this.f4416a) {
            case 0:
                this.f4417b.j();
                break;
            default:
                super.onRoutesChanged(list);
                break;
        }
    }

    public void onRoutesRemoved(List list) {
        switch (this.f4416a) {
            case 0:
                this.f4417b.j();
                break;
            default:
                super.onRoutesRemoved(list);
                break;
        }
    }

    public void onRoutesUpdated(List list) {
        switch (this.f4416a) {
            case 1:
                this.f4417b.j();
                break;
            default:
                super.onRoutesUpdated(list);
                break;
        }
    }
}
