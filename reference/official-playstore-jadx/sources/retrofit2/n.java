package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Executor f21523a;

    public n(Executor executor) {
        this.f21523a = executor;
    }

    @Override // retrofit2.d
    public final e a(Type type, Annotation[] annotationArr) {
        if (b1.f(type) != c.class) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            return new fi.iki.elonen.f(b1.e(0, (ParameterizedType) type), b1.i(annotationArr, w0.class) ? null : this.f21523a);
        }
        throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }
}
