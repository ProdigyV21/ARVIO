package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.zzab;
import com.google.android.gms.cast.internal.Logger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class c0 extends zzab {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Logger f13203e = new Logger("AppVisibilityProxy");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f13204f = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set f13205c = Collections.synchronizedSet(new HashSet());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f13206d = f13204f;

    public final boolean a() {
        return this.f13206d == 2;
    }

    @Override // com.google.android.gms.cast.framework.zzac
    public final com.google.android.gms.dynamic.b zzb() {
        return new com.google.android.gms.dynamic.d(this);
    }

    @Override // com.google.android.gms.cast.framework.zzac
    public final void zzc() {
        f13203e.i("onAppEnteredForeground", new Object[0]);
        this.f13206d = 1;
        Iterator it = this.f13205c.iterator();
        while (it.hasNext()) {
            ((b0) it.next()).zza();
        }
    }

    @Override // com.google.android.gms.cast.framework.zzac
    public final void zzd() {
        f13203e.i("onAppEnteredBackground", new Object[0]);
        this.f13206d = 2;
        Iterator it = this.f13205c.iterator();
        while (it.hasNext()) {
            ((b0) it.next()).zzb();
        }
    }
}
