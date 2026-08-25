package retrofit2;

import androidx.appcompat.app.i1;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes5.dex */
public final class o0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final char[] f21528l = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f21529m = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f21530a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final gb.b0 f21531b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f21532c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public androidx.tv.foundation.lazy.list.k f21533d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final gb.j0 f21534e = new gb.j0();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final gb.z f21535f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public gb.d0 f21536g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f21537h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i1 f21538i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final fi.iki.elonen.f f21539j;
    public gb.n0 k;

    public o0(String str, gb.b0 b0Var, String str2, gb.a0 a0Var, gb.d0 d0Var, boolean z, boolean z5, boolean z10) {
        this.f21530a = str;
        this.f21531b = b0Var;
        this.f21532c = str2;
        this.f21536g = d0Var;
        this.f21537h = z;
        if (a0Var != null) {
            this.f21535f = a0Var.c();
        } else {
            this.f21535f = new gb.z();
        }
        if (z5) {
            this.f21539j = new fi.iki.elonen.f(2);
            return;
        }
        if (z10) {
            i1 i1Var = new i1(16);
            this.f21538i = i1Var;
            gb.d0 d0Var2 = gb.f0.f15654f;
            if (d0Var2.f15636b.equals("multipart")) {
                i1Var.f1060m = d0Var2;
            } else {
                throw new IllegalArgumentException(("multipart != " + d0Var2).toString());
            }
        }
    }

    public final void a(String str, String str2, boolean z) {
        fi.iki.elonen.f fVar = this.f21539j;
        if (z) {
            ((ArrayList) fVar.f15417l).add(gb.u.c(0, 0, 83, str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true));
            ((ArrayList) fVar.f15416i).add(gb.u.c(0, 0, 83, str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true));
        } else {
            ((ArrayList) fVar.f15417l).add(gb.u.c(0, 0, 91, str, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false));
            ((ArrayList) fVar.f15416i).add(gb.u.c(0, 0, 91, str2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false));
        }
    }

    public final void b(String str, String str2) {
        if (!HttpConnection.CONTENT_TYPE.equalsIgnoreCase(str)) {
            this.f21535f.a(str, str2);
            return;
        }
        try {
            Pattern pattern = gb.d0.f15633d;
            this.f21536g = xc.d.i0(str2);
        } catch (IllegalArgumentException e5) {
            throw new IllegalArgumentException(androidx.compose.material3.d.C("Malformed content type: ", str2), e5);
        }
    }

    public final void c(gb.a0 a0Var, gb.n0 n0Var) {
        i1 i1Var = this.f21538i;
        i1Var.getClass();
        if (a0Var.a(HttpConnection.CONTENT_TYPE) != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Type");
        }
        if (a0Var.a("Content-Length") != null) {
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }
        ((ArrayList) i1Var.f1061n).add(new gb.e0(a0Var, n0Var));
    }

    public final void d(String str, String str2, boolean z) {
        androidx.tv.foundation.lazy.list.k kVar;
        String str3 = this.f21532c;
        if (str3 != null) {
            gb.b0 b0Var = this.f21531b;
            b0Var.getClass();
            try {
                kVar = new androidx.tv.foundation.lazy.list.k(1);
                kVar.i(b0Var, str3);
            } catch (IllegalArgumentException unused) {
                kVar = null;
            }
            this.f21533d = kVar;
            if (kVar == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + b0Var + ", Relative: " + this.f21532c);
            }
            this.f21532c = null;
        }
        if (z) {
            this.f21533d.a(str, str2);
        } else {
            this.f21533d.c(str, str2);
        }
    }
}
