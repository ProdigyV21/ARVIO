package gb;

import io.ktor.http.auth.HttpAuthHeader;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Pattern f15633d = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Pattern f15634e = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f15635a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f15636b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String[] f15637c;

    public d0(String str, String str2, String[] strArr) {
        this.f15635a = str;
        this.f15636b = str2;
        this.f15637c = strArr;
    }

    public final Charset a(Charset charset) {
        String str;
        String[] strArr = this.f15637c;
        int i10 = 0;
        int iA = i7.n.a(0, strArr.length - 1, 2);
        if (iA < 0) {
            str = null;
            break;
        }
        while (!kotlin.text.u.L(strArr[i10], HttpAuthHeader.Parameters.Charset, true)) {
            if (i10 == iA) {
                str = null;
                break;
            }
            i10 += 2;
        }
        str = strArr[i10 + 1];
        if (str == null) {
            return charset;
        }
        try {
            return Charset.forName(str);
        } catch (IllegalArgumentException unused) {
            return charset;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof d0) && kotlin.jvm.internal.p.a(((d0) obj).f15635a, this.f15635a);
    }

    public final int hashCode() {
        return this.f15635a.hashCode();
    }

    public final String toString() {
        return this.f15635a;
    }
}
