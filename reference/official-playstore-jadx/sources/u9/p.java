package u9;

import com.google.android.gms.internal.auth.d1;
import j$.util.concurrent.ConcurrentHashMap;
import j8.t0;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes5.dex */
public class p implements v {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f22088d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d f22089e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f22090a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f22091b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f22092c;

    static {
        String canonicalName = p.class.getCanonicalName();
        int iJ0 = kotlin.text.o.j0(6, canonicalName, ".");
        f22088d = iJ0 == -1 ? "" : canonicalName.substring(0, iJ0);
        f22089e = new d("NO_LOCKS", c.f22073i);
    }

    public p(String str) {
        this(str, new kb.d(new ReentrantLock(), 18));
    }

    public static void i(AssertionError assertionError) {
        StackTraceElement[] stackTrace = assertionError.getStackTrace();
        int length = stackTrace.length;
        int i10 = 0;
        while (true) {
            if (i10 >= length) {
                i10 = -1;
                break;
            } else if (!stackTrace[i10].getClassName().startsWith(f22088d)) {
                break;
            } else {
                i10++;
            }
        }
        List listSubList = Arrays.asList(stackTrace).subList(i10, length);
        assertionError.setStackTrace((StackTraceElement[]) listSubList.toArray(new StackTraceElement[listSubList.size()]));
    }

    @Override // u9.v
    public final g a() {
        return new g(this, new ConcurrentHashMap(3, 1.0f, 2), new h());
    }

    @Override // u9.v
    public final f b(t0 t0Var, s8.d dVar) {
        return new f(this, t0Var, dVar);
    }

    @Override // u9.v
    public final k c(r7.a aVar) {
        return new k(this, aVar);
    }

    @Override // u9.v
    public final l d(r7.a aVar) {
        return new l(this, aVar);
    }

    @Override // u9.v
    public final m e(r7.l lVar) {
        return new m(this, new ConcurrentHashMap(3, 1.0f, 2), lVar);
    }

    @Override // u9.v
    public final e f(r7.a aVar) {
        return new e(this, aVar);
    }

    @Override // u9.v
    public final n g(r7.l lVar) {
        return new n(this, new ConcurrentHashMap(3, 1.0f, 2), lVar);
    }

    public d1 h(Object obj, String str) {
        String str2;
        StringBuilder sb2 = new StringBuilder("Recursion detected ");
        sb2.append(str);
        if (obj == null) {
            str2 = "";
        } else {
            str2 = "on input: " + obj;
        }
        sb2.append(str2);
        sb2.append(" under ");
        sb2.append(this);
        AssertionError assertionError = new AssertionError(sb2.toString());
        i(assertionError);
        throw assertionError;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getClass().getSimpleName());
        sb2.append("@");
        sb2.append(Integer.toHexString(hashCode()));
        sb2.append(" (");
        return a0.c.p(sb2, this.f22092c, ")");
    }

    public p(String str, u uVar) {
        this.f22090a = uVar;
        this.f22091b = i.k;
        this.f22092c = str;
    }
}
