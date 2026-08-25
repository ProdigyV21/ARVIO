package z8;

import java.security.AccessControlException;
import java.util.HashMap;
import p8.b0;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public final class k implements y.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final boolean f23262i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final HashMap f23263j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f23264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f23265b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f23266c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String[] f23267d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String[] f23268e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String[] f23269f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public a f23270g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String[] f23271h;

    static {
        try {
            f23262i = "true".equals(System.getProperty("kotlin.ignore.old.metadata"));
        } catch (AccessControlException unused) {
            f23262i = false;
        }
        HashMap map = new HashMap();
        f23263j = map;
        map.put(f9.b.j(new f9.c("kotlin.jvm.internal.KotlinClass")), a.CLASS);
        map.put(f9.b.j(new f9.c("kotlin.jvm.internal.KotlinFileFacade")), a.FILE_FACADE);
        map.put(f9.b.j(new f9.c("kotlin.jvm.internal.KotlinMultifileClass")), a.MULTIFILE_CLASS);
        map.put(f9.b.j(new f9.c("kotlin.jvm.internal.KotlinMultifileClassPart")), a.MULTIFILE_CLASS_PART);
        map.put(f9.b.j(new f9.c("kotlin.jvm.internal.KotlinSyntheticClass")), a.SYNTHETIC_CLASS);
    }

    @Override // y8.y.c
    public final y.a b(f9.b bVar, l8.a aVar) {
        a aVar2;
        f9.c cVarB = bVar.b();
        if (cVarB.equals(b0.f21088a)) {
            return new e(this);
        }
        if (cVarB.equals(b0.f21101o)) {
            return new g(this);
        }
        if (f23262i || this.f23270g != null || (aVar2 = (a) f23263j.get(bVar)) == null) {
            return null;
        }
        this.f23270g = aVar2;
        return new j(this);
    }

    @Override // y8.y.c
    public final void a() {
    }
}
