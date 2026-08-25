package gb;

import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class q {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Pattern f15793j = Pattern.compile("(\\d{2,4})[^\\d]*");
    public static final Pattern k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Pattern f15794l = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f15795m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f15796a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f15797b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f15798c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f15799d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f15800e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f15801f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f15802g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f15803h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f15804i;

    public q(String str, String str2, long j10, String str3, String str4, boolean z, boolean z5, boolean z10, boolean z11) {
        this.f15796a = str;
        this.f15797b = str2;
        this.f15798c = j10;
        this.f15799d = str3;
        this.f15800e = str4;
        this.f15801f = z;
        this.f15802g = z5;
        this.f15803h = z10;
        this.f15804i = z11;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof q)) {
            return false;
        }
        q qVar = (q) obj;
        return kotlin.jvm.internal.p.a(qVar.f15796a, this.f15796a) && kotlin.jvm.internal.p.a(qVar.f15797b, this.f15797b) && qVar.f15798c == this.f15798c && kotlin.jvm.internal.p.a(qVar.f15799d, this.f15799d) && kotlin.jvm.internal.p.a(qVar.f15800e, this.f15800e) && qVar.f15801f == this.f15801f && qVar.f15802g == this.f15802g && qVar.f15803h == this.f15803h && qVar.f15804i == this.f15804i;
    }

    public final int hashCode() {
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(527, 31, this.f15796a), 31, this.f15797b);
        long j10 = this.f15798c;
        return ((((((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((iC + ((int) (j10 ^ (j10 >>> 32)))) * 31, 31, this.f15799d), 31, this.f15800e) + (this.f15801f ? 1231 : 1237)) * 31) + (this.f15802g ? 1231 : 1237)) * 31) + (this.f15803h ? 1231 : 1237)) * 31) + (this.f15804i ? 1231 : 1237);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f15796a);
        sb2.append('=');
        sb2.append(this.f15797b);
        if (this.f15803h) {
            long j10 = this.f15798c;
            if (j10 == Long.MIN_VALUE) {
                sb2.append("; max-age=0");
            } else {
                sb2.append("; expires=");
                sb2.append(((DateFormat) mb.c.f20359a.get()).format(new Date(j10)));
            }
        }
        if (!this.f15804i) {
            sb2.append("; domain=");
            sb2.append(this.f15799d);
        }
        sb2.append("; path=");
        sb2.append(this.f15800e);
        if (this.f15801f) {
            sb2.append("; secure");
        }
        if (this.f15802g) {
            sb2.append("; httponly");
        }
        return sb2.toString();
    }
}
