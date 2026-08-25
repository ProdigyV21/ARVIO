package androidx.media3.session;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i2 implements com.google.common.base.c0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4024i;

    public /* synthetic */ i2(int i10) {
        this.f4024i = i10;
    }

    @Override // com.google.common.base.c0
    public final Object get() {
        switch (this.f4024i) {
            case 0:
                return Integer.valueOf(MediaSessionImpl.getMediaMetadataBitmapMaxSize());
            default:
                return SimpleBitmapLoader.lambda$static$0();
        }
    }
}
