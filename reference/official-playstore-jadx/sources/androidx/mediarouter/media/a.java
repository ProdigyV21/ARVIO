package androidx.mediarouter.media;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4296i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4297l;

    public /* synthetic */ a(Object obj, int i10) {
        this.f4296i = i10;
        this.f4297l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4296i) {
            case 0:
                ((f) this.f4297l).n();
                break;
            case 1:
                ((h) this.f4297l).f4375n = -1;
                break;
            default:
                ((f0) this.f4297l).b();
                break;
        }
    }
}
