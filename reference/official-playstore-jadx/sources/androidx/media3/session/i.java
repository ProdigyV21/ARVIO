package androidx.media3.session;

import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaLibraryService;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class i implements Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4015i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaBrowserImplBase f4016l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f4017m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f4018n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MediaLibraryService.LibraryParams f4019o;

    public /* synthetic */ i(MediaBrowserImplBase mediaBrowserImplBase, String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams) {
        this.f4015i = i11;
        this.f4016l = mediaBrowserImplBase;
        this.f4017m = str;
        this.f4018n = i10;
        this.f4019o = libraryParams;
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object obj) {
        switch (this.f4015i) {
            case 0:
                this.f4016l.lambda$notifySearchResultChanged$0(this.f4017m, this.f4018n, this.f4019o, (MediaBrowser.Listener) obj);
                break;
            default:
                this.f4016l.lambda$notifyChildrenChanged$1(this.f4017m, this.f4018n, this.f4019o, (MediaBrowser.Listener) obj);
                break;
        }
    }
}
