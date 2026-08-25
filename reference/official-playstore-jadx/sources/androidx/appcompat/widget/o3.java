package androidx.appcompat.widget;

/* JADX INFO: loaded from: classes.dex */
public final class o3 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1706i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SearchView f1707l;

    public /* synthetic */ o3(SearchView searchView, int i10) {
        this.f1706i = i10;
        this.f1707l = searchView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1706i) {
            case 0:
                this.f1707l.r();
                break;
            default:
                androidx.cursoradapter.widget.b bVar = this.f1707l.f1455e0;
                if (bVar instanceof c4) {
                    bVar.c(null);
                }
                break;
        }
    }
}
