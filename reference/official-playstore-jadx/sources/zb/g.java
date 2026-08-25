package zb;

import org.fusesource.jansi.internal.Kernel32;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements bc.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f23287a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ long f23288b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int[] f23289c;

    public /* synthetic */ g(long j10, int i10, int[] iArr) {
        this.f23287a = i10;
        this.f23288b = j10;
        this.f23289c = iArr;
    }

    @Override // bc.a
    public final void run() {
        switch (this.f23287a) {
            case 0:
                i.k++;
                Kernel32.SetConsoleMode(this.f23288b, this.f23289c[0] | 4);
                break;
            default:
                int i10 = i.k - 1;
                i.k = i10;
                if (i10 == 0) {
                    Kernel32.SetConsoleMode(this.f23288b, this.f23289c[0]);
                }
                break;
        }
    }
}
