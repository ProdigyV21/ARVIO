package androidx.navigation;

import android.net.Uri;
import android.os.Bundle;
import io.sentry.y6;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public final class y {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f4803m = Pattern.compile("^[a-zA-Z]+[+\\w\\-.]*:");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Pattern f4804n = Pattern.compile("\\{(.+?)\\}");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4805a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f4806b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f4807c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final x6.i0 f4808d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final x6.i0 f4809e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f4810f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4811g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f4812h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f4813i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f4814j;
    public final x6.i0 k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f4815l;

    public y(String str) {
        this.f4805a = str;
        ArrayList arrayList = new ArrayList();
        this.f4806b = arrayList;
        this.f4808d = new x6.i0(new w(this, 6));
        this.f4809e = new x6.i0(new w(this, 4));
        this.f4810f = f4.f.o(3, new w(this, 7));
        this.f4812h = f4.f.o(3, new w(this, 1));
        this.f4813i = f4.f.o(3, new w(this, 0));
        this.f4814j = f4.f.o(3, new w(this, 3));
        this.k = new x6.i0(new w(this, 2));
        new x6.i0(new w(this, 5));
        StringBuilder sb2 = new StringBuilder("^");
        if (!f4803m.matcher(str).find()) {
            sb2.append("http[s]?://");
        }
        Matcher matcher = Pattern.compile("(\\?|\\#|$)").matcher(str);
        matcher.find();
        a(str.substring(0, matcher.start()), arrayList, sb2);
        this.f4815l = (kotlin.text.o.T(sb2, y6.DEFAULT_PROPAGATION_TARGETS, false) || kotlin.text.o.T(sb2, "([^/]+?)", false)) ? false : true;
        sb2.append("($|(\\?(.)*)|(\\#(.)*))");
        this.f4807c = kotlin.text.u.O(sb2.toString(), y6.DEFAULT_PROPAGATION_TARGETS, "\\E.*\\Q", false);
    }

    public static void a(String str, ArrayList arrayList, StringBuilder sb2) {
        Matcher matcher = f4804n.matcher(str);
        int iEnd = 0;
        while (matcher.find()) {
            arrayList.add(matcher.group(1));
            if (matcher.start() > iEnd) {
                sb2.append(Pattern.quote(str.substring(iEnd, matcher.start())));
            }
            sb2.append("([^/]+?)");
            iEnd = matcher.end();
        }
        if (iEnd < str.length()) {
            sb2.append(Pattern.quote(str.substring(iEnd)));
        }
    }

    public final boolean b(Matcher matcher, Bundle bundle, LinkedHashMap linkedHashMap) {
        ArrayList arrayList = this.f4806b;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        int i10 = 0;
        for (Object obj : arrayList) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            String str = (String) obj;
            String strDecode = Uri.decode(matcher.group(i11));
            g gVar = (g) linkedHashMap.get(str);
            if (gVar != null) {
                try {
                    w0 w0Var = gVar.f4666a;
                    w0Var.e(bundle, str, w0Var.c(strDecode));
                } catch (IllegalArgumentException unused) {
                    return false;
                }
            } else {
                bundle.putString(str, strDecode);
            }
            arrayList2.add(x6.t0.f22605a);
            i10 = i11;
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, x6.s] */
    public final boolean c(Uri uri, Bundle bundle, LinkedHashMap linkedHashMap) {
        String query;
        y yVar = this;
        for (Map.Entry entry : ((Map) yVar.f4810f.getValue()).entrySet()) {
            String str = (String) entry.getKey();
            v vVar = (v) entry.getValue();
            List<String> queryParameters = uri.getQueryParameters(str);
            if (yVar.f4811g && (query = uri.getQuery()) != null && !query.equals(uri.toString())) {
                queryParameters = Collections.singletonList(query);
            }
            if (queryParameters != null) {
                for (String str2 : queryParameters) {
                    String str3 = vVar.f4786a;
                    Matcher matcher = str3 != null ? Pattern.compile(str3, 32).matcher(str2) : null;
                    int i10 = 0;
                    if (matcher == null || !matcher.matches()) {
                        return false;
                    }
                    Bundle bundle2 = new Bundle();
                    try {
                        ArrayList arrayList = vVar.f4787b;
                        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
                        for (Object obj : arrayList) {
                            int i11 = i10 + 1;
                            if (i10 < 0) {
                                t7.a.Q();
                                throw null;
                            }
                            String str4 = (String) obj;
                            String strGroup = matcher.group(i11);
                            if (strGroup == null) {
                                strGroup = "";
                            }
                            try {
                                g gVar = (g) linkedHashMap.get(str4);
                                if (!bundle.containsKey(str4)) {
                                    if (!strGroup.equals('{' + str4 + '}')) {
                                        if (gVar != null) {
                                            w0 w0Var = gVar.f4666a;
                                            w0Var.e(bundle2, str4, w0Var.c(strGroup));
                                        } else {
                                            bundle2.putString(str4, strGroup);
                                        }
                                    }
                                } else if (gVar != null) {
                                    w0 w0Var2 = gVar.f4666a;
                                    Object objA = w0Var2.a(bundle, str4);
                                    if (!bundle.containsKey(str4)) {
                                        throw new IllegalArgumentException("There is no previous value in this bundle.");
                                    }
                                    w0Var2.e(bundle, str4, w0Var2.d(strGroup, objA));
                                } else {
                                    continue;
                                }
                                arrayList2.add(x6.t0.f22605a);
                                i10 = i11;
                            } catch (IllegalArgumentException unused) {
                                continue;
                            }
                        }
                        bundle.putAll(bundle2);
                    } catch (IllegalArgumentException unused2) {
                    }
                }
            }
            yVar = this;
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof y)) {
            return false;
        }
        return this.f4805a.equals(((y) obj).f4805a);
    }

    public final int hashCode() {
        return this.f4805a.hashCode() * 961;
    }
}
