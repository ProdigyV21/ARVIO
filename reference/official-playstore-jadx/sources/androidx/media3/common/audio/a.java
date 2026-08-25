package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioBecomingNoisyManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3362i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3363l;

    public /* synthetic */ a(Object obj, int i10) {
        this.f3362i = i10;
        this.f3363l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3362i) {
            case 0:
                ((AudioBecomingNoisyManager) this.f3363l).lambda$setEnabled$0();
                break;
            case 1:
                ((AudioBecomingNoisyManager) this.f3363l).lambda$setEnabled$1();
                break;
            default:
                ((AudioBecomingNoisyManager.AudioBecomingNoisyReceiver) this.f3363l).callListenerIfEnabled();
                break;
        }
    }
}
