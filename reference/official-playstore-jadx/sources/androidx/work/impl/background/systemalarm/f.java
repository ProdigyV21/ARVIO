package androidx.work.impl.background.systemalarm;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class f implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6745i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ g f6746l;

    public /* synthetic */ f(g gVar, int i10) {
        this.f6745i = i10;
        this.f6746l = gVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f6745i) {
            case 0:
                g.b(this.f6746l);
                break;
            default:
                g.c(this.f6746l);
                break;
        }
    }
}
