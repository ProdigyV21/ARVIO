package androidx.activity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f934i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ t f935l;

    public /* synthetic */ e(t tVar, int i10) {
        this.f934i = i10;
        this.f935l = tVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f934i) {
            case 0:
                this.f935l.invalidateMenu();
                return;
            default:
                try {
                    super/*android.app.Activity*/.onBackPressed();
                    return;
                } catch (IllegalStateException e5) {
                    if (!kotlin.jvm.internal.p.a(e5.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                        throw e5;
                    }
                    return;
                } catch (NullPointerException e6) {
                    if (!kotlin.jvm.internal.p.a(e6.getMessage(), "Attempt to invoke virtual method 'android.os.Handler android.app.FragmentHostCallback.getHandler()' on a null object reference")) {
                        throw e6;
                    }
                    return;
                }
        }
    }
}
