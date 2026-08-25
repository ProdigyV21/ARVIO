package androidx.compose.ui.viewinterop;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1946i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f1947l;

    public /* synthetic */ a(int i10, r7.a aVar) {
        this.f1946i = i10;
        this.f1947l = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1946i) {
            case 0:
                this.f1947l.invoke();
                break;
            default:
                this.f1947l.invoke();
                break;
        }
    }
}
