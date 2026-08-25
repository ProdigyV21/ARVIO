package com.google.android.gms.auth.api.signin.internal;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.c0;
import com.google.android.gms.common.api.internal.w;
import com.google.android.gms.common.api.v;
import com.google.android.gms.common.internal.t;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.helper.HttpConnection;
import v3.a;

/* JADX INFO: loaded from: classes4.dex */
public final class zbb implements Runnable {
    private static final a zba = new a("RevokeAccessOperation", new String[0]);
    private final String zbb;
    private final w zbc;

    public zbb(String str) {
        t.f(str);
        this.zbb = str;
        this.zbc = new w(null);
    }

    public static v zba(String str) {
        if (str != null) {
            zbb zbbVar = new zbb(str);
            new Thread(zbbVar).start();
            return zbbVar.zbc;
        }
        Status status = new Status(4, null, null, null);
        t.a("Status code must not be SUCCESS", !status.d());
        c0 c0Var = new c0(status);
        c0Var.setResult(status);
        return c0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Status status = Status.f12672q;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://accounts.google.com/o/oauth2/revoke?token=" + this.zbb).openConnection();
            httpURLConnection.setRequestProperty(HttpConnection.CONTENT_TYPE, HttpConnection.FORM_URL_ENCODED);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                status = Status.f12670o;
            } else {
                a aVar = zba;
                Log.e(aVar.f22155a, aVar.b("Unable to revoke access!", new Object[0]));
            }
            zba.a("Response Code: " + responseCode, new Object[0]);
        } catch (IOException e5) {
            a aVar2 = zba;
            Log.e(aVar2.f22155a, aVar2.b("IOException when revoking access: ".concat(String.valueOf(e5.toString())), new Object[0]));
        } catch (Exception e6) {
            a aVar3 = zba;
            Log.e(aVar3.f22155a, aVar3.b("Exception when revoking access: ".concat(String.valueOf(e6.toString())), new Object[0]));
        }
        this.zbc.setResult(status);
    }
}
