package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.common.api.d;
import com.google.android.gms.common.internal.t;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class zbd implements d.e {
    public static final zbd zba = new zbd(new zbc());
    private final String zbb = null;
    private final boolean zbc;
    private final String zbd;

    public zbd(zbc zbcVar) {
        this.zbc = zbcVar.zba.booleanValue();
        this.zbd = zbcVar.zbb;
    }

    public static /* bridge */ /* synthetic */ String zbb(zbd zbdVar) {
        String str = zbdVar.zbb;
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zbd)) {
            return false;
        }
        zbd zbdVar = (zbd) obj;
        return t.l(null, null) && this.zbc == zbdVar.zbc && t.l(this.zbd, zbdVar.zbd);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{null, Boolean.valueOf(this.zbc), this.zbd});
    }

    public final Bundle zba() {
        Bundle bundle = new Bundle();
        bundle.putString("consumer_package", null);
        bundle.putBoolean("force_save_dialog", this.zbc);
        bundle.putString("log_session_id", this.zbd);
        return bundle;
    }
}
