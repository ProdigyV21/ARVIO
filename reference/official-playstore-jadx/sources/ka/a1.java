package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class a1 extends y1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f19544l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f19545m;

    public /* synthetic */ a1(Object obj, int i10) {
        this.f19544l = i10;
        this.f19545m = obj;
    }

    @Override // r7.l
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        switch (this.f19544l) {
            case 0:
                invoke((Throwable) obj);
                break;
            case 1:
                invoke((Throwable) obj);
                break;
            case 2:
                invoke((Throwable) obj);
                break;
            case 3:
                invoke((Throwable) obj);
                break;
            default:
                invoke((Throwable) obj);
                break;
        }
        return x6.t0.f22605a;
    }

    public a1(f2 f2Var, sa.f fVar) {
        this.f19544l = 2;
        this.f19545m = fVar;
    }

    @Override // ka.y1
    public final void invoke(Throwable th) {
        switch (this.f19544l) {
            case 0:
                ((z0) this.f19545m).dispose();
                break;
            case 1:
                ((r7.l) this.f19545m).invoke(th);
                break;
            case 2:
                ((sa.f) this.f19545m).b();
                break;
            case 3:
                z1 z1Var = (z1) this.f19545m;
                Object objY = c().y();
                if (objY instanceof v) {
                    z1Var.resumeWith(new x6.c0(((v) objY).f19647a));
                } else {
                    z1Var.resumeWith(m0.x(objY));
                }
                break;
            default:
                ((l) this.f19545m).resumeWith(x6.t0.f22605a);
                break;
        }
    }
}
