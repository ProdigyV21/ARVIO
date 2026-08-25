package d8;

import java.util.ServiceLoader;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a f14691l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a f14692m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14693i;

    static {
        int i10 = 0;
        f14691l = new a(i10, 0);
        f14692m = new a(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(int i10, int i11) {
        super(i10);
        this.f14693i = i11;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f14693i) {
            case 0:
                c cVar = (c) x.n0(ServiceLoader.load(c.class, c.class.getClassLoader()));
                if (cVar != null) {
                    return cVar;
                }
                throw new IllegalStateException("No BuiltInsLoader implementation was found. Please ensure that the META-INF/services/ is not stripped from your application and that the Java virtual machine is not running under a security manager");
            default:
                f fVar = new f(new u9.p("DefaultBuiltIns"));
                fVar.c(false);
                return fVar;
        }
    }
}
