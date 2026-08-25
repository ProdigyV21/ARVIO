package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public q f3314a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public w f3315b;

    public final void a(y yVar, p pVar) {
        q qVarA = pVar.a();
        q qVar = this.f3314a;
        if (qVarA.compareTo(qVar) < 0) {
            qVar = qVarA;
        }
        this.f3314a = qVar;
        this.f3315b.onStateChanged(yVar, pVar);
        this.f3314a = qVarA;
    }
}
