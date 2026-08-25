package z8;

import java.util.ArrayList;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b implements y.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f23253a = new ArrayList();

    @Override // y8.y.b
    public final void a() {
        f((String[]) this.f23253a.toArray(new String[0]));
    }

    @Override // y8.y.b
    public final y.a c(f9.b bVar) {
        return null;
    }

    @Override // y8.y.b
    public final void d(Object obj) {
        if (obj instanceof String) {
            this.f23253a.add((String) obj);
        }
    }

    public abstract void f(String[] strArr);

    @Override // y8.y.b
    public final void e(k9.f fVar) {
    }

    @Override // y8.y.b
    public final void b(f9.b bVar, f9.f fVar) {
    }
}
