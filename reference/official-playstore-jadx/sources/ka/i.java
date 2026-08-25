package ka;

import java.util.concurrent.ScheduledFuture;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19598i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f19599l;

    public /* synthetic */ i(Object obj, int i10) {
        this.f19598i = i10;
        this.f19599l = obj;
    }

    @Override // ka.j
    public final void a(Throwable th) {
        switch (this.f19598i) {
            case 0:
                if (th != null) {
                    ((ScheduledFuture) this.f19599l).cancel(false);
                }
                break;
            case 1:
                ((z0) this.f19599l).dispose();
                break;
            default:
                ((r7.l) this.f19599l).invoke(th);
                break;
        }
    }

    @Override // r7.l
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        switch (this.f19598i) {
            case 0:
                a((Throwable) obj);
                break;
            case 1:
                a((Throwable) obj);
                break;
            default:
                a((Throwable) obj);
                break;
        }
        return x6.t0.f22605a;
    }

    public final String toString() {
        switch (this.f19598i) {
            case 0:
                return "CancelFutureOnCancel[" + ((ScheduledFuture) this.f19599l) + ']';
            case 1:
                return "DisposeOnCancel[" + ((z0) this.f19599l) + ']';
            default:
                return "InvokeOnCancel[" + ((r7.l) this.f19599l).getClass().getSimpleName() + '@' + m0.j(this) + ']';
        }
    }
}
