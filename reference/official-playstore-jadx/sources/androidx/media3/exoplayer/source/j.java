package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.source.MediaSourceEventListener;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j implements Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3763i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSourceEventListener.EventDispatcher f3764l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ LoadEventInfo f3765m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MediaLoadData f3766n;

    public /* synthetic */ j(MediaSourceEventListener.EventDispatcher eventDispatcher, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i10) {
        this.f3763i = i10;
        this.f3764l = eventDispatcher;
        this.f3765m = loadEventInfo;
        this.f3766n = mediaLoadData;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object obj) {
        switch (this.f3763i) {
            case 0:
                this.f3764l.lambda$loadCompleted$1(this.f3765m, this.f3766n, (MediaSourceEventListener) obj);
                break;
            default:
                this.f3764l.lambda$loadCanceled$2(this.f3765m, this.f3766n, (MediaSourceEventListener) obj);
                break;
        }
    }
}
