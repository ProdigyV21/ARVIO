package androidx.activity;

/* JADX INFO: loaded from: classes.dex */
public final class j0 implements d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d0 f958i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f959l;

    public j0(l0 l0Var, d0 d0Var) {
        this.f959l = l0Var;
        this.f958i = d0Var;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.internal.m, r7.a] */
    @Override // androidx.activity.d
    public final void cancel() {
        l0 l0Var = this.f959l;
        kotlin.collections.p pVar = l0Var.f964b;
        d0 d0Var = this.f958i;
        pVar.remove(d0Var);
        if (kotlin.jvm.internal.p.a(l0Var.f965c, d0Var)) {
            d0Var.a();
            l0Var.f965c = null;
        }
        d0Var.f932b.remove(this);
        ?? r02 = d0Var.f933c;
        if (r02 != 0) {
            r02.invoke();
        }
        d0Var.f933c = null;
    }
}
