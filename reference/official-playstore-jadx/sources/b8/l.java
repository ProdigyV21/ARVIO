package b8;

import java.lang.reflect.Field;
import x6.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends o implements d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f7236g;

    public l(Field field, boolean z, Object obj) {
        super(field, z, false);
        this.f7236g = obj;
    }

    @Override // b8.o, b8.e
    public final Object call(Object[] objArr) throws IllegalAccessException {
        c(objArr);
        ((Field) this.f7244a).set(this.f7236g, kotlin.collections.r.Y(objArr));
        return t0.f22605a;
    }
}
