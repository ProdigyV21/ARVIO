package com.google.android.gms.auth.api.identity;

import android.os.Bundle;
import com.google.android.gms.common.api.d;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class zbc implements d.e {
    private final String zba;

    public zbc(String str) {
        this.zba = str;
    }

    public final boolean equals(Object obj) {
        return obj instanceof zbc;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{zbc.class});
    }

    public final Bundle zba() {
        Bundle bundle = new Bundle();
        bundle.putString("session_id", this.zba);
        return bundle;
    }

    public final String zbb() {
        return this.zba;
    }
}
