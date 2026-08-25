package gb;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 {
    public static final char[] k = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f15617a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f15618b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f15619c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f15620d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f15621e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f15622f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final List f15623g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f15624h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f15625i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f15626j;

    public b0(String str, String str2, String str3, String str4, int i10, ArrayList arrayList, ArrayList arrayList2, String str5, String str6) {
        this.f15617a = str;
        this.f15618b = str2;
        this.f15619c = str3;
        this.f15620d = str4;
        this.f15621e = i10;
        this.f15622f = arrayList;
        this.f15623g = arrayList2;
        this.f15624h = str5;
        this.f15625i = str6;
        this.f15626j = kotlin.jvm.internal.p.a(str, "https");
    }

    public final String a() {
        if (this.f15619c.length() == 0) {
            return "";
        }
        int length = this.f15617a.length() + 3;
        String str = this.f15625i;
        return str.substring(kotlin.text.o.e0(str, ':', length, false, 4) + 1, kotlin.text.o.e0(str, '@', 0, false, 6));
    }

    public final String b() {
        int length = this.f15617a.length() + 3;
        String str = this.f15625i;
        int iE0 = kotlin.text.o.e0(str, '/', length, false, 4);
        return str.substring(iE0, ib.c.f(iE0, str.length(), str, "?#"));
    }

    public final ArrayList c() {
        int length = this.f15617a.length() + 3;
        String str = this.f15625i;
        int iE0 = kotlin.text.o.e0(str, '/', length, false, 4);
        int iF = ib.c.f(iE0, str.length(), str, "?#");
        ArrayList arrayList = new ArrayList();
        while (iE0 < iF) {
            int i10 = iE0 + 1;
            int iG = ib.c.g(str, i10, iF, '/');
            arrayList.add(str.substring(i10, iG));
            iE0 = iG;
        }
        return arrayList;
    }

    public final String d() {
        if (this.f15623g == null) {
            return null;
        }
        String str = this.f15625i;
        int iE0 = kotlin.text.o.e0(str, '?', 0, false, 6) + 1;
        return str.substring(iE0, ib.c.g(str, iE0, str.length(), '#'));
    }

    public final String e() {
        if (this.f15618b.length() == 0) {
            return "";
        }
        int length = this.f15617a.length() + 3;
        String str = this.f15625i;
        return str.substring(length, ib.c.f(length, str.length(), str, ":@"));
    }

    public final boolean equals(Object obj) {
        return (obj instanceof b0) && kotlin.jvm.internal.p.a(((b0) obj).f15625i, this.f15625i);
    }

    public final androidx.tv.foundation.lazy.list.k f() {
        androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
        String str = this.f15617a;
        kVar.f5411e = str;
        kVar.f5412f = e();
        kVar.f5413g = a();
        kVar.f5414h = this.f15620d;
        int i10 = str.equals("http") ? 80 : str.equals("https") ? 443 : -1;
        int i11 = this.f15621e;
        kVar.f5408b = i11 != i10 ? i11 : -1;
        ArrayList arrayList = kVar.f5409c;
        arrayList.clear();
        arrayList.addAll(c());
        String strD = d();
        String strSubstring = null;
        kVar.f5410d = strD != null ? u.h(u.c(0, 0, 211, strD, " \"'<>#", true)) : null;
        if (this.f15624h != null) {
            String str2 = this.f15625i;
            strSubstring = str2.substring(kotlin.text.o.e0(str2, '#', 0, false, 6) + 1);
        }
        kVar.f5415i = strSubstring;
        return kVar;
    }

    public final String g(String str) {
        List list = this.f15623g;
        if (list == null) {
            return null;
        }
        x7.g gVarV = qb.d.V(qb.d.X(0, list.size()), 2);
        int i10 = gVarV.f22619i;
        int i11 = gVarV.f22620l;
        int i12 = gVarV.f22621m;
        if ((i12 <= 0 || i10 > i11) && (i12 >= 0 || i11 > i10)) {
            return null;
        }
        while (!str.equals(list.get(i10))) {
            if (i10 == i11) {
                return null;
            }
            i10 += i12;
        }
        return (String) list.get(i10 + 1);
    }

    public final String h() {
        androidx.tv.foundation.lazy.list.k kVar;
        try {
            kVar = new androidx.tv.foundation.lazy.list.k(1);
            kVar.i(this, "/...");
        } catch (IllegalArgumentException unused) {
            kVar = null;
        }
        kVar.f5412f = u.c(0, 0, 251, "", " \"':;<=>@[]^`{}|/\\?#", false);
        kVar.f5413g = u.c(0, 0, 251, "", " \"':;<=>@[]^`{}|/\\?#", false);
        return kVar.d().f15625i;
    }

    public final int hashCode() {
        return this.f15625i.hashCode();
    }

    public final URI i() {
        androidx.tv.foundation.lazy.list.k kVarF = f();
        ArrayList arrayList = kVarF.f5409c;
        String str = (String) kVarF.f5414h;
        kVarF.f5414h = str != null ? Pattern.compile("[\"<>^`{|}]").matcher(str).replaceAll("") : null;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.set(i10, u.c(0, 0, 227, (String) arrayList.get(i10), "[]", true));
        }
        ArrayList arrayList2 = (ArrayList) kVarF.f5410d;
        if (arrayList2 != null) {
            int size2 = arrayList2.size();
            for (int i11 = 0; i11 < size2; i11++) {
                String str2 = (String) arrayList2.get(i11);
                arrayList2.set(i11, str2 != null ? u.c(0, 0, 195, str2, "\\^`{|}", true) : null);
            }
        }
        String str3 = (String) kVarF.f5415i;
        kVarF.f5415i = str3 != null ? u.c(0, 0, 163, str3, " \"#<>\\^`{|}", true) : null;
        String string = kVarF.toString();
        try {
            return new URI(string);
        } catch (URISyntaxException e5) {
            try {
                return URI.create(Pattern.compile("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]").matcher(string).replaceAll(""));
            } catch (Exception unused) {
                throw new RuntimeException(e5);
            }
        }
    }

    public final String toString() {
        return this.f15625i;
    }
}
