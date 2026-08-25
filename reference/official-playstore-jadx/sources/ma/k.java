package ma;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CancellationException;
import ka.f2;
import kotlinx.coroutines.JobCancellationException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class k extends ka.a implements j {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final f f20344n;

    public k(d7.j jVar, f fVar) {
        super(jVar, true);
        this.f20344n = fVar;
    }

    @Override // ma.w
    public final Object b() {
        return this.f20344n.b();
    }

    @Override // ka.f2, ka.v1
    public final void cancel(CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        if (isCancelled()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(p(), null, this);
        }
        m(cancellationException);
    }

    @Override // ma.x
    public final boolean close(Throwable th) {
        return this.f20344n.g(th, false);
    }

    @Override // ma.w
    public final Object e(f7.j jVar) {
        f fVar = this.f20344n;
        fVar.getClass();
        return f.w(fVar, jVar);
    }

    @Override // ma.x
    public final void invokeOnClose(r7.l lVar) {
        this.f20344n.invokeOnClose(lVar);
    }

    @Override // ma.x
    public final boolean isClosedForSend() {
        return this.f20344n.isClosedForSend();
    }

    @Override // ma.w
    public final l iterator() {
        f fVar = this.f20344n;
        fVar.getClass();
        return new c(fVar);
    }

    @Override // ka.f2
    public final void m(CancellationException cancellationException) throws IllegalAccessException, InvocationTargetException {
        CancellationException cancellationExceptionN = f2.N(this, cancellationException);
        this.f20344n.g(cancellationExceptionN, true);
        l(cancellationExceptionN);
    }

    @Override // ma.w
    public final Object receive(d7.d dVar) {
        return this.f20344n.receive(dVar);
    }

    @Override // ma.x
    public final Object send(Object obj, d7.d dVar) {
        return this.f20344n.send(obj, dVar);
    }

    @Override // ma.x
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    public final Object mo6685trySendJP2dKIU(Object obj) {
        return this.f20344n.mo6685trySendJP2dKIU(obj);
    }

    @Override // ka.f2, ka.v1
    public final /* synthetic */ void cancel() throws IllegalAccessException, InvocationTargetException {
        m(new JobCancellationException(p(), null, this));
    }

    @Override // ka.f2, ka.v1
    public final /* synthetic */ boolean cancel(Throwable th) throws IllegalAccessException, InvocationTargetException {
        m(new JobCancellationException(p(), null, this));
        return true;
    }
}
