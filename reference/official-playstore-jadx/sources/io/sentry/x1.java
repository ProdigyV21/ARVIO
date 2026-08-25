package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class x1 implements z1, a4, io.sentry.util.j, o5 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f18049i;

    public /* synthetic */ x1(int i10) {
        this.f18049i = i10;
    }

    @Override // io.sentry.a4
    public void a(j7 j7Var) {
    }

    @Override // io.sentry.z1
    public Object c() {
        return null;
    }

    @Override // io.sentry.util.j
    public Object g() {
        switch (this.f18049i) {
            case 2:
                return y6.empty();
            case 3:
                return y6.empty();
            case 4:
                return new u4();
            default:
                byte[] bArr = new byte[8];
                io.sentry.util.q.a().b(bArr);
                byte b10 = (byte) (bArr[6] & 15);
                bArr[6] = b10;
                bArr[6] = (byte) (b10 | 64);
                long j10 = 0;
                for (int i10 = 0; i10 < 8; i10++) {
                    j10 = (j10 << 8) | ((long) (bArr[i10] & 255));
                }
                char[] cArr = new char[16];
                io.sentry.util.t.a(cArr, j10);
                return new String(cArr);
        }
    }
}
