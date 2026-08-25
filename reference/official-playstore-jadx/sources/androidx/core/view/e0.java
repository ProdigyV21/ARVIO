package androidx.core.view;

/* JADX INFO: loaded from: classes3.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final androidx.lifecycle.r f2220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public androidx.lifecycle.w f2221b;

    public e0(androidx.lifecycle.r rVar, androidx.lifecycle.w wVar) {
        this.f2220a = rVar;
        this.f2221b = wVar;
        rVar.addObserver(wVar);
    }
}
