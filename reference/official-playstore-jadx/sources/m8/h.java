package m8;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends e implements w8.e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f20279b;

    public h(f9.f fVar, Object[] objArr) {
        super(fVar);
        this.f20279b = objArr;
    }

    @Override // w8.e
    public final ArrayList c() {
        Object[] objArr = this.f20279b;
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj : objArr) {
            Class<?> cls = obj.getClass();
            List list = c.f20266a;
            arrayList.add(Enum.class.isAssignableFrom(cls) ? new t(null, (Enum) obj) : obj instanceof Annotation ? new f(null, (Annotation) obj) : obj instanceof Object[] ? new h(null, (Object[]) obj) : obj instanceof Class ? new p(null, (Class) obj) : new v(null, obj));
        }
        return arrayList;
    }
}
