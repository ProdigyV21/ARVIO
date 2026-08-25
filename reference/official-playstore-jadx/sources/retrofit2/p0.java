package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes5.dex */
public final class p0 {
    public static final Pattern x = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final Pattern f21541y = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u0 f21542a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f21543b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Annotation[] f21544c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Annotation[][] f21545d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Type[] f21546e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f21547f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f21548g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f21549h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f21550i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f21551j;
    public boolean k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f21552l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f21553m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f21554n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f21555o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f21556p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f21557q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f21558r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public gb.a0 f21559s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public gb.d0 f21560t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public LinkedHashSet f21561u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public b1[] f21562v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f21563w;

    public p0(u0 u0Var, Method method) {
        this.f21542a = u0Var;
        this.f21543b = method;
        this.f21544c = method.getAnnotations();
        this.f21546e = method.getGenericParameterTypes();
        this.f21545d = method.getParameterAnnotations();
    }

    public static Class a(Class cls) {
        return Boolean.TYPE == cls ? Boolean.class : Byte.TYPE == cls ? Byte.class : Character.TYPE == cls ? Character.class : Double.TYPE == cls ? Double.class : Float.TYPE == cls ? Float.class : Integer.TYPE == cls ? Integer.class : Long.TYPE == cls ? Long.class : Short.TYPE == cls ? Short.class : cls;
    }

    public final void b(String str, String str2, boolean z) {
        String str3 = this.f21554n;
        Method method = this.f21543b;
        if (str3 != null) {
            throw b1.j(method, null, "Only one HTTP method is allowed. Found: %s and %s.", str3, str);
        }
        this.f21554n = str;
        this.f21555o = z;
        if (str2.isEmpty()) {
            return;
        }
        int iIndexOf = str2.indexOf(63);
        Pattern pattern = x;
        if (iIndexOf != -1 && iIndexOf < str2.length() - 1) {
            String strSubstring = str2.substring(iIndexOf + 1);
            if (pattern.matcher(strSubstring).find()) {
                throw b1.j(method, null, "URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", strSubstring);
            }
        }
        this.f21558r = str2;
        Matcher matcher = pattern.matcher(str2);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        this.f21561u = linkedHashSet;
    }

    public final void c(int i10, Type type) {
        if (b1.h(type)) {
            throw b1.k(this.f21543b, i10, "Parameter type must not include a type variable or wildcard: %s", type);
        }
    }
}
