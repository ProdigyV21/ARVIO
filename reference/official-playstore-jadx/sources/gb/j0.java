package gb;

import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import java.util.Collections;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b0 f15722a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public n0 f15725d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public LinkedHashMap f15726e = new LinkedHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f15723b = "GET";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public z f15724c = new z();

    public final void a(String str, String str2) {
        this.f15724c.a(str, str2);
    }

    public final k0 b() {
        b0 b0Var = this.f15722a;
        if (b0Var == null) {
            throw new IllegalStateException("url == null");
        }
        String str = this.f15723b;
        a0 a0VarE = this.f15724c.e();
        n0 n0Var = this.f15725d;
        LinkedHashMap linkedHashMap = this.f15726e;
        byte[] bArr = ib.c.f16268a;
        return new k0(b0Var, str, a0VarE, n0Var, linkedHashMap.isEmpty() ? kotlin.collections.a0.f19683i : Collections.unmodifiableMap(new LinkedHashMap(linkedHashMap)));
    }

    public final void c(h hVar) {
        String string = hVar.toString();
        if (string.length() == 0) {
            this.f15724c.g("Cache-Control");
        } else {
            this.f15724c.h("Cache-Control", string);
        }
    }

    public final void d() {
        f("GET", null);
    }

    public final void e(String str, String str2) {
        this.f15724c.h(str, str2);
    }

    public final void f(String str, n0 n0Var) {
        if (str.length() <= 0) {
            throw new IllegalArgumentException("method.isEmpty() == true");
        }
        if (n0Var == null) {
            if (str.equals(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST) || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT")) {
                throw new IllegalArgumentException(a0.c.l("method ", str, " must have a request body.").toString());
            }
        } else if (!qb.l.v(str)) {
            throw new IllegalArgumentException(a0.c.l("method ", str, " must not have a request body.").toString());
        }
        this.f15723b = str;
        this.f15725d = n0Var;
    }

    public final void g(String str) {
        this.f15724c.g(str);
    }

    public final void h(Class cls, Object obj) {
        if (obj == null) {
            this.f15726e.remove(cls);
            return;
        }
        if (this.f15726e.isEmpty()) {
            this.f15726e = new LinkedHashMap();
        }
        this.f15726e.put(cls, cls.cast(obj));
    }

    public final void i(String str) {
        if (kotlin.text.u.P(str, "ws:", true)) {
            str = "http:".concat(str.substring(3));
        } else if (kotlin.text.u.P(str, "wss:", true)) {
            str = "https:".concat(str.substring(4));
        }
        androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
        kVar.i(null, str);
        this.f15722a = kVar.d();
    }
}
