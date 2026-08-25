package b8;

import java.lang.reflect.Field;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class h extends k implements d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f7234e;

    public h(Field field, Object obj) {
        super(field, false);
        this.f7234e = obj;
    }

    @Override // b8.k, b8.e
    public final Object call(Object[] objArr) {
        b0.f(this, objArr);
        return ((Field) this.f7244a).get(this.f7234e);
    }
}
