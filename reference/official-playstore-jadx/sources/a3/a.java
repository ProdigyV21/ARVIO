package a3;

import com.google.android.datatransport.runtime.m;
import com.google.common.util.concurrent.p0;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class a implements m {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f105c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Set f106d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f107e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f108f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f109a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f110b;

    static {
        String strW = p0.w("hts/frbslgiggolai.o/0clgbthfra=snpoo", "tp:/ieaeogn.ogepscmvc/o/ac?omtjo_rt3");
        f105c = strW;
        String strW2 = p0.w("hts/frbslgigp.ogepscmv/ieo/eaybtho", "tp:/ieaeogn-agolai.o/1frlglgc/aclg");
        String strW3 = p0.w("AzSCki82AwsLzKd5O8zo", "IayckHiZRO1EFl1aGoK");
        f106d = Collections.unmodifiableSet(new HashSet(Arrays.asList(new z2.b("proto"), new z2.b("json"))));
        f107e = new a(strW, null);
        f108f = new a(strW2, strW3);
    }

    public a(String str, String str2) {
        this.f109a = str;
        this.f110b = str2;
    }

    public static a b(byte[] bArr) {
        String str = new String(bArr, Charset.forName("UTF-8"));
        if (!str.startsWith("1$")) {
            throw new IllegalArgumentException("Version marker missing from extras");
        }
        String[] strArrSplit = str.substring(2).split(Pattern.quote("\\"), 2);
        if (strArrSplit.length != 2) {
            throw new IllegalArgumentException("Extra is not a valid encoded LegacyFlgDestination");
        }
        String str2 = strArrSplit[0];
        if (str2.isEmpty()) {
            throw new IllegalArgumentException("Missing endpoint in CCTDestination extras");
        }
        String str3 = strArrSplit[1];
        if (str3.isEmpty()) {
            str3 = null;
        }
        return new a(str2, str3);
    }

    @Override // com.google.android.datatransport.runtime.m
    public final Set a() {
        return f106d;
    }

    @Override // com.google.android.datatransport.runtime.l
    public final byte[] getExtras() {
        String str = this.f109a;
        String str2 = this.f110b;
        if (str2 == null && str == null) {
            return null;
        }
        if (str2 == null) {
            str2 = "";
        }
        return androidx.compose.material3.d.n("1$", str, "\\", str2).getBytes(Charset.forName("UTF-8"));
    }
}
