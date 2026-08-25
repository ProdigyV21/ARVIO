package com.arflix.tv.network;

import androidx.tv.foundation.lazy.list.k;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.util.Constants;
import gb.b0;
import gb.c0;
import gb.j0;
import gb.k0;
import gb.p0;
import java.util.List;
import kotlin.Metadata;
import kotlin.text.o;
import kotlin.text.u;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\u0007J\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/network/ApiProxyInterceptor;", "Lgb/c0;", "<init>", "()V", "Lgb/k0;", "originalRequest", "rewriteForSimklProxy", "(Lgb/k0;)Lgb/k0;", "rewriteForTmdbProxy", "", "hasProxyConfig", "()Z", "Lgb/c0$a;", "chain", "Lgb/p0;", "intercept", "(Lgb/c0$a;)Lgb/p0;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ApiProxyInterceptor implements c0 {
    public static final int $stable = 0;

    /* JADX WARN: Removed duplicated region for block: B:19:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0062 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean hasProxyConfig() {
        /*
            r6 = this;
            com.arflix.tv.util.Constants r0 = com.arflix.tv.util.Constants.INSTANCE
            boolean r1 = r0.getUSE_NETLIFY_CLOUD_SYNC()
            java.lang.String r2 = "http://"
            java.lang.String r3 = "https://"
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L23
            java.lang.String r1 = r0.getNETLIFY_BACKEND_URL()
            boolean r1 = kotlin.text.u.P(r1, r3, r5)
            if (r1 != 0) goto L61
            java.lang.String r0 = r0.getNETLIFY_BACKEND_URL()
            boolean r0 = kotlin.text.u.P(r0, r2, r5)
            if (r0 == 0) goto L62
            goto L61
        L23:
            java.lang.String r1 = r0.getSUPABASE_URL()
            java.lang.CharSequence r1 = kotlin.text.o.L0(r1)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = r0.getSUPABASE_ANON_KEY()
            java.lang.CharSequence r0 = kotlin.text.o.L0(r0)
            java.lang.String r0 = r0.toString()
            boolean r3 = kotlin.text.u.P(r1, r3, r5)
            if (r3 != 0) goto L47
            boolean r2 = kotlin.text.u.P(r1, r2, r5)
            if (r2 == 0) goto L62
        L47:
            java.lang.String r2 = "your-project"
            boolean r1 = kotlin.text.o.T(r1, r2, r4)
            if (r1 != 0) goto L62
            int r1 = r0.length()
            r2 = 40
            if (r1 <= r2) goto L62
            java.lang.String r1 = "your-"
            boolean r0 = kotlin.text.u.P(r0, r1, r4)
            if (r0 != 0) goto L62
        L61:
            return r4
        L62:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.network.ApiProxyInterceptor.hasProxyConfig():boolean");
    }

    private final k0 rewriteForSimklProxy(k0 originalRequest) {
        b0 b0VarD;
        b0 b0Var = originalRequest.f15730a;
        String strB = b0Var.b();
        List list = b0Var.f15623g;
        String simkl_proxy_url = Constants.INSTANCE.getSIMKL_PROXY_URL();
        try {
            k kVar = new k(1);
            kVar.i(null, simkl_proxy_url);
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
            b0VarD = null;
        }
        if (b0VarD == null) {
            return null;
        }
        k kVarF = b0VarD.f();
        kVarF.c("path", strB);
        kVarF.c("method", originalRequest.f15731b);
        int size = list != null ? list.size() / 2 : 0;
        for (int i10 = 0; i10 < size; i10++) {
            if (list == null) {
                throw new IndexOutOfBoundsException();
            }
            int i11 = i10 * 2;
            String str = (String) list.get(i11);
            if (list == null) {
                throw new IndexOutOfBoundsException();
            }
            String str2 = (String) list.get(i11 + 1);
            if (str2 != null) {
                kVarF.c(str, str2);
            }
        }
        String strA = originalRequest.f15732c.a("Authorization");
        String strR0 = strA != null ? o.r0(strA, "Bearer ") : null;
        j0 j0VarB = originalRequest.b();
        j0VarB.f15722a = kVarF.d();
        Constants constants = Constants.INSTANCE;
        j0VarB.f15724c.h("apikey", constants.getAPP_ANON_KEY());
        j0VarB.f15724c.h("Authorization", androidx.compose.material3.d.C("Bearer ", constants.getAPP_ANON_KEY()));
        if (strR0 != null && !o.h0(strR0)) {
            j0VarB.f15724c.h("x-user-token", strR0);
        }
        return j0VarB.b();
    }

    private final k0 rewriteForTmdbProxy(k0 originalRequest) {
        b0 b0VarD;
        b0 b0Var = originalRequest.f15730a;
        String strB = b0Var.b();
        List list = b0Var.f15623g;
        if (u.P(strB, "/3/", false)) {
            strB = o.r0(strB, "/3");
        }
        String tmdb_proxy_url = Constants.INSTANCE.getTMDB_PROXY_URL();
        try {
            k kVar = new k(1);
            kVar.i(null, tmdb_proxy_url);
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
            b0VarD = null;
        }
        if (b0VarD == null) {
            return null;
        }
        k kVarF = b0VarD.f();
        kVarF.c("path", strB);
        int size = list != null ? list.size() / 2 : 0;
        for (int i10 = 0; i10 < size; i10++) {
            if (list == null) {
                throw new IndexOutOfBoundsException();
            }
            int i11 = i10 * 2;
            String str = (String) list.get(i11);
            if (!str.equals("api_key")) {
                if (list == null) {
                    throw new IndexOutOfBoundsException();
                }
                String str2 = (String) list.get(i11 + 1);
                if (str2 != null) {
                    kVarF.c(str, str2);
                }
            }
        }
        j0 j0VarB = originalRequest.b();
        j0VarB.f15722a = kVarF.d();
        Constants constants = Constants.INSTANCE;
        j0VarB.f15724c.h("apikey", constants.getAPP_ANON_KEY());
        j0VarB.f15724c.h("Authorization", androidx.compose.material3.d.C("Bearer ", constants.getAPP_ANON_KEY()));
        return j0VarB.b();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // gb.c0
    public p0 intercept(c0.a chain) {
        k0 k0VarRequest = chain.request();
        b0 b0Var = k0VarRequest.f15730a;
        if (!hasProxyConfig()) {
            return chain.a(k0VarRequest);
        }
        String str = b0Var.f15620d;
        switch (str.hashCode()) {
            case -1984072217:
                if (str.equals("api.themoviedb.org")) {
                    if (!BuildConfig.ENABLE_TMDB_EDGE_PROXY.booleanValue()) {
                        return chain.a(k0VarRequest);
                    }
                    k0 k0VarRewriteForTmdbProxy = rewriteForTmdbProxy(k0VarRequest);
                    if (k0VarRewriteForTmdbProxy != null) {
                        k0VarRequest = k0VarRewriteForTmdbProxy;
                    }
                    return chain.a(k0VarRequest);
                }
                break;
            case -147111672:
                if (str.equals("api.mdblist.com")) {
                    return chain.a(k0VarRequest);
                }
                break;
            case 756864055:
                if (str.equals("api.simkl.com")) {
                    if (!o.h0(Constants.INSTANCE.getSIMKL_CLIENT_ID())) {
                        return chain.a(k0VarRequest);
                    }
                    k0 k0VarRewriteForSimklProxy = rewriteForSimklProxy(k0VarRequest);
                    if (k0VarRewriteForSimklProxy != null) {
                        k0VarRequest = k0VarRewriteForSimklProxy;
                    }
                    return chain.a(k0VarRequest);
                }
                break;
            case 821512888:
                if (str.equals("api.trakt.tv")) {
                    return chain.a(k0VarRequest);
                }
                break;
        }
        return chain.a(k0VarRequest);
    }
}
