package androidx.media3.exoplayer.analytics;

import com.google.common.base.c0;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class z implements c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3542i;

    public /* synthetic */ z(int i10) {
        this.f3542i = i10;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f3542i) {
            case 0:
                return DefaultPlaybackSessionManager.generateDefaultSessionId();
            default:
                throw new IllegalStateException();
        }
    }
}
