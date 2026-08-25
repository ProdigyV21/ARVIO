package io.ktor.server.engine;

import d7.d;
import d7.e;
import d7.g;
import d7.h;
import d7.j;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\u0004\b\u0000\u0010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u0006\u0012\u0002\b\u00030\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/server/engine/ClassLoaderAwareContinuationInterceptor;", "Ld7/g;", "<init>", "()V", "T", "Ld7/d;", "continuation", "interceptContinuation", "(Ld7/d;)Ld7/d;", "Ld7/j$b;", "key", "Ld7/j$b;", "getKey", "()Ld7/j$b;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ClassLoaderAwareContinuationInterceptor implements g {
    public static final ClassLoaderAwareContinuationInterceptor INSTANCE = new ClassLoaderAwareContinuationInterceptor();
    private static final j.b<?> key = new j.b<ClassLoaderAwareContinuationInterceptor>() { // from class: io.ktor.server.engine.ClassLoaderAwareContinuationInterceptor$key$1
    };

    private ClassLoaderAwareContinuationInterceptor() {
    }

    @Override // d7.j
    public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) pVar.invoke(r4, this);
    }

    @Override // d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) e.a(this, bVar);
    }

    @Override // d7.j.a
    public j.b<?> getKey() {
        return key;
    }

    @Override // d7.g
    public <T> d<T> interceptContinuation(d<? super T> continuation) {
        return new d<T>(continuation, Thread.currentThread().getContextClassLoader()) { // from class: io.ktor.server.engine.ClassLoaderAwareContinuationInterceptor.interceptContinuation.1
            final /* synthetic */ ClassLoader $classLoader;
            final /* synthetic */ d<T> $continuation;
            private final j context;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$continuation = continuation;
                this.$classLoader = classLoader;
                this.context = continuation.getContext();
            }

            @Override // d7.d
            public j getContext() {
                return this.context;
            }

            @Override // d7.d
            public void resumeWith(Object result) {
                Thread.currentThread().setContextClassLoader(this.$classLoader);
                this.$continuation.resumeWith(result);
            }
        };
    }

    @Override // d7.j
    public j minusKey(j.b<?> bVar) {
        return e.b(this, bVar);
    }

    @Override // d7.j
    public j plus(j jVar) {
        return h.a(this, jVar);
    }

    @Override // d7.g
    public void releaseInterceptedContinuation(d<?> dVar) {
    }
}
