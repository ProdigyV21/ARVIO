package androidx.appcompat.widget;

/* JADX INFO: loaded from: classes.dex */
public final class p4 extends com.google.common.util.concurrent.p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1716a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1717b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1718c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Object f1719d;

    public p4(q4 q4Var, int i10) {
        this.f1716a = 0;
        this.f1719d = q4Var;
        this.f1718c = i10;
        this.f1717b = false;
    }

    @Override // com.google.common.util.concurrent.p0, androidx.core.view.o2
    public void a() {
        switch (this.f1716a) {
            case 0:
                this.f1717b = true;
                break;
        }
    }

    @Override // com.google.common.util.concurrent.p0, androidx.core.view.o2
    public final void b() {
        switch (this.f1716a) {
            case 0:
                ((q4) this.f1719d).f1723a.setVisibility(0);
                break;
            default:
                if (!this.f1717b) {
                    this.f1717b = true;
                    androidx.core.view.o2 o2Var = ((n.k) this.f1719d).f20423d;
                    if (o2Var != null) {
                        o2Var.b();
                    }
                    break;
                }
                break;
        }
    }

    @Override // androidx.core.view.o2
    public final void c() {
        switch (this.f1716a) {
            case 0:
                if (!this.f1717b) {
                    ((q4) this.f1719d).f1723a.setVisibility(this.f1718c);
                }
                break;
            default:
                int i10 = this.f1718c + 1;
                this.f1718c = i10;
                n.k kVar = (n.k) this.f1719d;
                if (i10 == kVar.f20420a.size()) {
                    androidx.core.view.o2 o2Var = kVar.f20423d;
                    if (o2Var != null) {
                        o2Var.c();
                    }
                    this.f1718c = 0;
                    this.f1717b = false;
                    kVar.f20424e = false;
                }
                break;
        }
    }

    public p4(n.k kVar) {
        this.f1716a = 1;
        this.f1719d = kVar;
        this.f1717b = false;
        this.f1718c = 0;
    }
}
