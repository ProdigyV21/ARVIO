package eb;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends a.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f15118b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ u f15119c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ String f15120d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f15121e;

    public c(u uVar, String str) {
        super(6);
        this.f15119c = uVar;
        this.f15120d = str;
        this.f15121e = uVar.f15177b.f14879b;
    }

    @Override // a.a, bb.e
    public void A(int i10) {
        switch (this.f15118b) {
            case 1:
                o0(Long.toString(((long) i10) & 4294967295L, 10));
                break;
            default:
                super.A(i10);
                break;
        }
    }

    @Override // a.a, bb.e
    public void E(String str) {
        switch (this.f15118b) {
            case 0:
                this.f15119c.L(new db.u(str, false, (ab.g) this.f15121e), this.f15120d);
                break;
            default:
                super.E(str);
                break;
        }
    }

    @Override // bb.e
    public final fb.b a() {
        switch (this.f15118b) {
            case 0:
                return this.f15119c.f15177b.f14879b;
            default:
                return (fb.b) this.f15121e;
        }
    }

    @Override // a.a, bb.e
    public void h(byte b10) {
        switch (this.f15118b) {
            case 1:
                o0(String.valueOf(b10 & 255));
                break;
            default:
                super.h(b10);
                break;
        }
    }

    public void o0(String str) {
        this.f15119c.L(new db.u(str, false, null), this.f15120d);
    }

    @Override // a.a, bb.e
    public void p(long j10) {
        String str;
        switch (this.f15118b) {
            case 1:
                if (j10 == 0) {
                    str = "0";
                } else if (j10 > 0) {
                    str = Long.toString(j10, 10);
                } else {
                    char[] cArr = new char[64];
                    long j11 = (j10 >>> 1) / ((long) 5);
                    long j12 = 10;
                    int i10 = 63;
                    cArr[63] = Character.forDigit((int) (j10 - (j11 * j12)), 10);
                    while (j11 > 0) {
                        i10--;
                        cArr[i10] = Character.forDigit((int) (j11 % j12), 10);
                        j11 /= j12;
                    }
                    str = new String(cArr, i10, 64 - i10);
                }
                o0(str);
                break;
            default:
                super.p(j10);
                break;
        }
    }

    @Override // a.a, bb.e
    public void s(short s10) {
        switch (this.f15118b) {
            case 1:
                o0(String.valueOf(s10 & 65535));
                break;
            default:
                super.s(s10);
                break;
        }
    }

    public c(u uVar, String str, ab.g gVar) {
        super(6);
        this.f15119c = uVar;
        this.f15120d = str;
        this.f15121e = gVar;
    }
}
