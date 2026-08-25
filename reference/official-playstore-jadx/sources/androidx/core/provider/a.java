package androidx.core.provider;

import androidx.leanback.widget.SearchBar;
import androidx.work.impl.foreground.SystemForegroundService;
import com.google.android.gms.common.api.internal.e0;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2133i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f2134l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2135m;

    public /* synthetic */ a(Object obj, int i10, int i11) {
        this.f2133i = i11;
        this.f2135m = obj;
        this.f2134l = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2133i) {
            case 0:
                i0.o oVar = (i0.o) ((a8.e) this.f2135m).f183l;
                if (oVar != null) {
                    oVar.onFontRetrievalFailed(this.f2134l);
                }
                break;
            case 1:
                SearchBar searchBar = (SearchBar) this.f2135m;
                searchBar.F.play(searchBar.G.get(this.f2134l), 1.0f, 1.0f, 1, 0, 1.0f);
                break;
            case 2:
                ((SystemForegroundService) this.f2135m).f6863o.cancel(this.f2134l);
                break;
            default:
                ((e0) this.f2135m).d(this.f2134l);
                break;
        }
    }
}
