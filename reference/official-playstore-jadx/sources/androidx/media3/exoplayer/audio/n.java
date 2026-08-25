package androidx.media3.exoplayer.audio;

import androidx.media3.common.util.ListenerSet;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class n implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3570i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3571l;

    public /* synthetic */ n(Object obj, int i10) {
        this.f3570i = i10;
        this.f3571l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3570i) {
            case 0:
                AudioTrackAudioOutput.lambda$releaseAudioTrackAsync$0((ListenerSet) this.f3571l);
                break;
            default:
                ((DefaultAudioSink) this.f3571l).maybeReportSkippedSilence();
                break;
        }
    }
}
