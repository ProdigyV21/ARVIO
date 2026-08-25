package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f21517a = new j();

    @Override // retrofit2.d
    public final e a(Type type, Annotation[] annotationArr) {
        if (b1.f(type) != CompletableFuture.class) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("CompletableFuture return type must be parameterized as CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
        }
        Type typeE = b1.e(0, (ParameterizedType) type);
        if (b1.f(typeE) != r0.class) {
            return new h(0, typeE);
        }
        if (typeE instanceof ParameterizedType) {
            return new h(1, b1.e(0, (ParameterizedType) typeE));
        }
        throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
    }
}
