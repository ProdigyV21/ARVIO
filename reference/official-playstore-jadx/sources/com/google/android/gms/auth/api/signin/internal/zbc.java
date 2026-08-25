package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.util.Log;
import androidx.loader.content.b;
import com.google.android.gms.common.api.internal.u;
import com.google.android.gms.common.api.q;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public final class zbc extends b implements u {
    private final Semaphore zba;
    private final Set zbb;

    public zbc(Context context, Set set) {
        super(context);
        this.zba = new Semaphore(0);
        this.zbb = set;
    }

    @Override // androidx.loader.content.b
    public final Object loadInBackground() {
        Iterator it = this.zbb.iterator();
        if (it.hasNext()) {
            ((q) it.next()).getClass();
            throw new UnsupportedOperationException();
        }
        try {
            this.zba.tryAcquire(0, 5L, TimeUnit.SECONDS);
            return null;
        } catch (InterruptedException e5) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e5);
            Thread.currentThread().interrupt();
            return null;
        }
    }

    public final void onComplete() {
        this.zba.release();
    }

    @Override // androidx.loader.content.e
    public final void onStartLoading() {
        this.zba.drainPermits();
        forceLoad();
    }
}
