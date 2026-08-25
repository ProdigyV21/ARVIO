package androidx.media3.session;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class t1 implements com.google.common.util.concurrent.w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4157i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaLibraryServiceLegacyStub f4158l;

    public /* synthetic */ t1(MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub, int i10) {
        this.f4157i = i10;
        this.f4158l = mediaLibraryServiceLegacyStub;
    }

    @Override // com.google.common.util.concurrent.w
    public final com.google.common.util.concurrent.d1 apply(Object obj) {
        switch (this.f4157i) {
            case 0:
                return this.f4158l.lambda$createMediaItemToBrowserItemAsyncFunction$15((LibraryResult) obj);
            default:
                return this.f4158l.lambda$createMediaItemsToBrowserItemsAsyncFunction$12((LibraryResult) obj);
        }
    }
}
