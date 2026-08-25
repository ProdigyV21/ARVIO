package retrofit2;

import j$.util.Optional;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public final class z extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final z f21618a = new z();

    @Override // retrofit2.k
    public final l b(Type type, Annotation[] annotationArr, u0 u0Var) {
        if (b1.f(type) != Optional.class) {
            return null;
        }
        return new kb.d(u0Var.d(b1.e(0, (ParameterizedType) type), annotationArr), 13);
    }
}
