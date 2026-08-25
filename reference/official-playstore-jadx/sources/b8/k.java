package b8;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public abstract class k extends u {
    public k(Field field, boolean z) {
        super(field, field.getGenericType(), z ? field.getDeclaringClass() : null, new Type[0]);
    }

    @Override // b8.e
    public Object call(Object[] objArr) {
        c(objArr);
        return ((Field) this.f7244a).get(this.f7246c != null ? kotlin.collections.r.Y(objArr) : null);
    }
}
