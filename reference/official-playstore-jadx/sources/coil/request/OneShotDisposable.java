package coil.request;

import java.util.concurrent.CancellationException;
import ka.p0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0004\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcoil/request/OneShotDisposable;", "Lcoil/request/Disposable;", "Lka/p0;", "Lcoil/request/ImageResult;", "job", "<init>", "(Lka/p0;)V", "Lx6/t0;", "dispose", "()V", "Lka/p0;", "getJob", "()Lka/p0;", "", "isDisposed", "()Z", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class OneShotDisposable implements Disposable {
    private final p0<ImageResult> job;

    /* JADX WARN: Multi-variable type inference failed */
    public OneShotDisposable(p0<? extends ImageResult> p0Var) {
        this.job = p0Var;
    }

    @Override // coil.request.Disposable
    public void dispose() {
        if (isDisposed()) {
            return;
        }
        getJob().cancel((CancellationException) null);
    }

    @Override // coil.request.Disposable
    public p0<ImageResult> getJob() {
        return this.job;
    }

    @Override // coil.request.Disposable
    public boolean isDisposed() {
        return !getJob().isActive();
    }
}
