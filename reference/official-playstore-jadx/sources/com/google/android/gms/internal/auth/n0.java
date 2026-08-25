package com.google.android.gms.internal.auth;

import android.util.Log;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class n0 extends com.google.android.gms.common.server.response.d {
    public final byte[] toByteArray() {
        try {
            return toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e5) {
            Log.e("AUTH", "Error serializing object.", e5);
            return null;
        }
    }
}
