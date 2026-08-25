package androidx.media3.exoplayer.source;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class b implements Handler.Callback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3755i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ CompositeMediaSource f3756l;

    public /* synthetic */ b(CompositeMediaSource compositeMediaSource, int i10) {
        this.f3755i = i10;
        this.f3756l = compositeMediaSource;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        switch (this.f3755i) {
            case 0:
                return ((ConcatenatingMediaSource) this.f3756l).handleMessage(message);
            default:
                return ((ConcatenatingMediaSource2) this.f3756l).handleMessage(message);
        }
    }
}
