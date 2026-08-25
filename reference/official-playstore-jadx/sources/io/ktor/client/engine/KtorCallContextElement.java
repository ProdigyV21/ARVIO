package io.ktor.client.engine;

import d7.i;
import d7.j;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\f\u001a\u0006\u0012\u0002\b\u00030\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lio/ktor/client/engine/KtorCallContextElement;", "Ld7/j$a;", "Ld7/j;", "callContext", "<init>", "(Ld7/j;)V", "Ld7/j;", "getCallContext", "()Ld7/j;", "Ld7/j$b;", "getKey", "()Ld7/j$b;", "key", "Companion", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KtorCallContextElement implements j.a {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final j callContext;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/client/engine/KtorCallContextElement$Companion;", "Ld7/j$b;", "Lio/ktor/client/engine/KtorCallContextElement;", "<init>", "()V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements j.b<KtorCallContextElement> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        private Companion() {
        }
    }

    public KtorCallContextElement(j jVar) {
        this.callContext = jVar;
    }

    @Override // d7.j
    public <R> R fold(R r4, p<? super R, ? super j.a, ? extends R> pVar) {
        return (R) pVar.invoke(r4, this);
    }

    @Override // d7.j
    public <E extends j.a> E get(j.b<E> bVar) {
        return (E) i.a(this, bVar);
    }

    public final j getCallContext() {
        return this.callContext;
    }

    @Override // d7.j.a
    public j.b<?> getKey() {
        return INSTANCE;
    }

    @Override // d7.j
    public j minusKey(j.b<?> bVar) {
        return i.b(this, bVar);
    }

    @Override // d7.j
    public j plus(j jVar) {
        return d7.h.a(this, jVar);
    }
}
