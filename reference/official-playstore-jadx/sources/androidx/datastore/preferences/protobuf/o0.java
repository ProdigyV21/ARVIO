package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public abstract class o0 extends a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final r0 f2482i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public r0 f2483l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f2484m = false;

    public o0(r0 r0Var) {
        this.f2482i = r0Var;
        this.f2483l = (r0) r0Var.h(4);
    }

    public static void i(r0 r0Var, r0 r0Var2) {
        j2 j2Var = j2.f2452c;
        j2Var.getClass();
        j2Var.a(r0Var.getClass()).f(r0Var, r0Var2);
    }

    public final Object clone() {
        o0 o0Var = (o0) this.f2482i.h(5);
        r0 r0VarG = g();
        o0Var.h();
        i(o0Var.f2483l, r0VarG);
        return o0Var;
    }

    public final r0 f() {
        r0 r0VarG = g();
        if (r0VarG.k()) {
            return r0VarG;
        }
        throw new UninitializedMessageException();
    }

    public final r0 g() {
        if (this.f2484m) {
            return this.f2483l;
        }
        r0 r0Var = this.f2483l;
        r0Var.getClass();
        j2 j2Var = j2.f2452c;
        j2Var.getClass();
        j2Var.a(r0Var.getClass()).b(r0Var);
        this.f2484m = true;
        return this.f2483l;
    }

    public final void h() {
        if (this.f2484m) {
            r0 r0Var = (r0) this.f2483l.h(4);
            i(r0Var, this.f2483l);
            this.f2483l = r0Var;
            this.f2484m = false;
        }
    }
}
