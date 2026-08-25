package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.x;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BasePendingResult<R extends com.google.android.gms.common.api.x> extends com.google.android.gms.common.api.v {
    static final ThreadLocal zaa = new f1(0);
    public static final /* synthetic */ int zad = 0;
    private g1 resultGuardian;
    protected final f zab;
    protected final WeakReference zac;
    private final Object zae;
    private final CountDownLatch zaf;
    private final ArrayList zag;
    private com.google.android.gms.common.api.y zah;
    private final AtomicReference zai;
    private com.google.android.gms.common.api.x zaj;
    private Status zak;
    private volatile boolean zal;
    private boolean zam;
    private boolean zan;
    private com.google.android.gms.common.internal.o zao;
    private volatile y0 zap;
    private boolean zaq;

    public BasePendingResult(Looper looper) {
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        this.zab = new f(looper, 3);
        this.zac = new WeakReference(null);
    }

    public static void zal(com.google.android.gms.common.api.x xVar) {
        if (xVar instanceof com.google.android.gms.common.api.w) {
            try {
                ((com.google.android.gms.common.api.w) xVar).release();
            } catch (RuntimeException e5) {
                Log.w("BasePendingResult", "Unable to release ".concat(String.valueOf(xVar)), e5);
            }
        }
    }

    public final com.google.android.gms.common.api.x a() {
        com.google.android.gms.common.api.x xVar;
        synchronized (this.zae) {
            com.google.android.gms.common.internal.t.k("Result has already been consumed.", !this.zal);
            com.google.android.gms.common.internal.t.k("Result is not ready.", isReady());
            xVar = this.zaj;
            this.zaj = null;
            this.zah = null;
            this.zal = true;
        }
        if (this.zai.getAndSet(null) != null) {
            throw new ClassCastException();
        }
        com.google.android.gms.common.internal.t.i(xVar);
        return xVar;
    }

    @Override // com.google.android.gms.common.api.v
    public final void addStatusListener(com.google.android.gms.common.api.u uVar) {
        com.google.android.gms.common.internal.t.a("Callback cannot be null.", uVar != null);
        synchronized (this.zae) {
            try {
                if (isReady()) {
                    uVar.a(this.zak);
                } else {
                    this.zag.add(uVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final R await() {
        com.google.android.gms.common.internal.t.h("await must not be called on the UI thread");
        com.google.android.gms.common.internal.t.k("Result has already been consumed", !this.zal);
        com.google.android.gms.common.internal.t.k("Cannot await if then() has been called.", this.zap == null);
        try {
            this.zaf.await();
        } catch (InterruptedException unused) {
            forceFailureUnlessReady(Status.f12671p);
        }
        com.google.android.gms.common.internal.t.k("Result is not ready.", isReady());
        return (R) a();
    }

    public final void b(com.google.android.gms.common.api.x xVar) {
        this.zaj = xVar;
        this.zak = xVar.getStatus();
        this.zao = null;
        this.zaf.countDown();
        if (this.zam) {
            this.zah = null;
        } else {
            com.google.android.gms.common.api.y yVar = this.zah;
            if (yVar != null) {
                f fVar = this.zab;
                fVar.removeMessages(2);
                fVar.a(yVar, a());
            } else if (this.zaj instanceof com.google.android.gms.common.api.w) {
                this.resultGuardian = new g1(this);
            }
        }
        ArrayList arrayList = this.zag;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            ((com.google.android.gms.common.api.u) arrayList.get(i10)).a(this.zak);
        }
        arrayList.clear();
    }

    @Override // com.google.android.gms.common.api.v
    public void cancel() {
        synchronized (this.zae) {
            if (!this.zam && !this.zal) {
                com.google.android.gms.common.internal.o oVar = this.zao;
                if (oVar != null) {
                    try {
                        oVar.cancel();
                    } catch (RemoteException unused) {
                    }
                }
                zal(this.zaj);
                this.zam = true;
                b(createFailedResult(Status.f12674s));
            }
        }
    }

    public abstract R createFailedResult(Status status);

    @Deprecated
    public final void forceFailureUnlessReady(Status status) {
        synchronized (this.zae) {
            try {
                if (!isReady()) {
                    setResult(createFailedResult(status));
                    this.zan = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isCanceled() {
        boolean z;
        synchronized (this.zae) {
            z = this.zam;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zaf.getCount() == 0;
    }

    public final void setCancelToken(com.google.android.gms.common.internal.o oVar) {
        synchronized (this.zae) {
            this.zao = oVar;
        }
    }

    @Override // com.google.android.gms.common.api.v
    public final void setResultCallback(com.google.android.gms.common.api.y<? super R> yVar) {
        synchronized (this.zae) {
            try {
                if (yVar == null) {
                    this.zah = null;
                    return;
                }
                boolean z = true;
                com.google.android.gms.common.internal.t.k("Result has already been consumed.", !this.zal);
                if (this.zap != null) {
                    z = false;
                }
                com.google.android.gms.common.internal.t.k("Cannot set callbacks if then() has been called.", z);
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.zab.a(yVar, a());
                } else {
                    this.zah = yVar;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final <S extends com.google.android.gms.common.api.x> com.google.android.gms.common.api.a0 then(com.google.android.gms.common.api.z zVar) {
        y0 y0VarA;
        com.google.android.gms.common.internal.t.k("Result has already been consumed.", !this.zal);
        synchronized (this.zae) {
            try {
                com.google.android.gms.common.internal.t.k("Cannot call then() twice.", this.zap == null);
                com.google.android.gms.common.internal.t.k("Cannot call then() if callbacks are set.", this.zah == null);
                com.google.android.gms.common.internal.t.k("Cannot call then() if result was canceled.", !this.zam);
                this.zaq = true;
                this.zap = new y0(this.zac);
                y0VarA = this.zap.a();
                if (isReady()) {
                    this.zab.a(this.zap, a());
                } else {
                    this.zah = this.zap;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return y0VarA;
    }

    public final boolean zaj() {
        boolean zIsCanceled;
        synchronized (this.zae) {
            try {
                if (((com.google.android.gms.common.api.q) this.zac.get()) == null || !this.zaq) {
                    cancel();
                }
                zIsCanceled = isCanceled();
            } catch (Throwable th) {
                throw th;
            }
        }
        return zIsCanceled;
    }

    public final void zak() {
        boolean z = true;
        if (!this.zaq && !((Boolean) zaa.get()).booleanValue()) {
            z = false;
        }
        this.zaq = z;
    }

    public final /* synthetic */ com.google.android.gms.common.api.x zam() {
        return this.zaj;
    }

    public final void zan(z0 z0Var) {
        this.zai.set(z0Var);
    }

    @Override // com.google.android.gms.common.api.internal.e
    public final void setResult(R r4) {
        synchronized (this.zae) {
            try {
                if (this.zan || this.zam) {
                    zal(r4);
                    return;
                }
                isReady();
                com.google.android.gms.common.internal.t.k("Results have already been set", !isReady());
                com.google.android.gms.common.internal.t.k("Result has already been consumed", !this.zal);
                b(r4);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public BasePendingResult(com.google.android.gms.common.api.q qVar) {
        Looper mainLooper;
        this.zae = new Object();
        this.zaf = new CountDownLatch(1);
        this.zag = new ArrayList();
        this.zai = new AtomicReference();
        this.zaq = false;
        if (qVar != null) {
            mainLooper = ((i0) qVar).f12756b.getLooper();
        } else {
            mainLooper = Looper.getMainLooper();
        }
        this.zab = new f(mainLooper, 3);
        this.zac = new WeakReference(qVar);
    }

    @Override // com.google.android.gms.common.api.v
    public final R await(long j10, TimeUnit timeUnit) {
        if (j10 > 0) {
            com.google.android.gms.common.internal.t.h("await must not be called on the UI thread when time is greater than zero.");
        }
        com.google.android.gms.common.internal.t.k("Result has already been consumed.", !this.zal);
        com.google.android.gms.common.internal.t.k("Cannot await if then() has been called.", this.zap == null);
        try {
            if (!this.zaf.await(j10, timeUnit)) {
                forceFailureUnlessReady(Status.f12673r);
            }
        } catch (InterruptedException unused) {
            forceFailureUnlessReady(Status.f12671p);
        }
        com.google.android.gms.common.internal.t.k("Result is not ready.", isReady());
        return (R) a();
    }

    public final void setResultCallback(com.google.android.gms.common.api.y<? super R> yVar, long j10, TimeUnit timeUnit) {
        synchronized (this.zae) {
            try {
                if (yVar == null) {
                    this.zah = null;
                    return;
                }
                boolean z = true;
                com.google.android.gms.common.internal.t.k("Result has already been consumed.", !this.zal);
                if (this.zap != null) {
                    z = false;
                }
                com.google.android.gms.common.internal.t.k("Cannot set callbacks if then() has been called.", z);
                if (isCanceled()) {
                    return;
                }
                if (isReady()) {
                    this.zab.a(yVar, a());
                } else {
                    this.zah = yVar;
                    f fVar = this.zab;
                    fVar.sendMessageDelayed(fVar.obtainMessage(2, this), timeUnit.toMillis(j10));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
