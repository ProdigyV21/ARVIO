package p8;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f9.c f21078a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final f9.b f21079b;

    static {
        f9.c cVar = new f9.c("kotlin.jvm.JvmField");
        f21078a = cVar;
        f9.b.j(cVar);
        f9.b.j(new f9.c("kotlin.reflect.jvm.internal.ReflectionFactoryImpl"));
        f21079b = f9.b.e("kotlin/jvm/internal/RepeatableContainer", false);
    }

    public static final String a(String str) {
        if (b(str)) {
            return str;
        }
        return "get" + kotlin.reflect.b0.e(str);
    }

    public static final boolean b(String str) {
        if (kotlin.text.u.P(str, "is", false) && str.length() != 2) {
            char cCharAt = str.charAt(2);
            if (kotlin.jvm.internal.p.c(97, cCharAt) > 0 || kotlin.jvm.internal.p.c(cCharAt, 122) > 0) {
                return true;
            }
        }
        return false;
    }
}
