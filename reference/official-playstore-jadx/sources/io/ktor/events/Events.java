package io.ktor.events;

import com.google.common.util.concurrent.r0;
import io.ktor.util.collections.CopyOnWriteHashMap;
import ka.z0;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.q0;
import kotlin.jvm.internal.r;
import pa.j;
import pa.m;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J?\u0010\f\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00028\u0000`\t¢\u0006\u0004\b\f\u0010\rJ?\u0010\u000e\u001a\u00020\b\"\u0004\b\u0000\u0010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00028\u0000`\t¢\u0006\u0004\b\u000e\u0010\u000fJ)\u0010\u0011\u001a\u00020\b\"\u0004\b\u0000\u0010\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0010\u001a\u00028\u0000¢\u0006\u0004\b\u0011\u0010\u0012R*\u0010\u0015\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00140\u00138\u0002X\u0082\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u0012\u0004\b\u0017\u0010\u0003¨\u0006\u0019"}, d2 = {"Lio/ktor/events/Events;", "", "<init>", "()V", "T", "Lio/ktor/events/EventDefinition;", "definition", "Lkotlin/Function1;", "Lx6/t0;", "Lio/ktor/events/EventHandler;", "handler", "Lka/z0;", "subscribe", "(Lio/ktor/events/EventDefinition;Lr7/l;)Lka/z0;", "unsubscribe", "(Lio/ktor/events/EventDefinition;Lr7/l;)V", "value", "raise", "(Lio/ktor/events/EventDefinition;Ljava/lang/Object;)V", "Lio/ktor/util/collections/CopyOnWriteHashMap;", "Lpa/j;", "handlers", "Lio/ktor/util/collections/CopyOnWriteHashMap;", "getHandlers$annotations", "HandlerRegistration", "ktor-events"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Events {
    private final CopyOnWriteHashMap<EventDefinition<?>, j> handlers = new CopyOnWriteHashMap<>();

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00040\u0003j\u0006\u0012\u0002\b\u0003`\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\t\u0010\nR)\u0010\u0006\u001a\u0014\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00040\u0003j\u0006\u0012\u0002\b\u0003`\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/events/Events$HandlerRegistration;", "Lpa/m;", "Lka/z0;", "Lkotlin/Function1;", "Lx6/t0;", "Lio/ktor/events/EventHandler;", "handler", "<init>", "(Lr7/l;)V", "dispose", "()V", "Lr7/l;", "getHandler", "()Lr7/l;", "ktor-events"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class HandlerRegistration extends m implements z0 {
        private final l<?, t0> handler;

        public HandlerRegistration(l<?, t0> lVar) {
            this.handler = lVar;
        }

        @Override // ka.z0
        public void dispose() {
            remove();
        }

        public final l<?, t0> getHandler() {
            return this.handler;
        }
    }

    /* JADX INFO: renamed from: io.ktor.events.Events$subscribe$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "Lio/ktor/events/EventDefinition;", "it", "Lpa/j;", "invoke", "(Lio/ktor/events/EventDefinition;)Lpa/j;", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<EventDefinition<?>, j> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public final j invoke(EventDefinition<?> eventDefinition) {
            return new j();
        }
    }

    private static /* synthetic */ void getHandlers$annotations() {
    }

    public final <T> void raise(EventDefinition<T> definition, T value) {
        t0 t0Var;
        j jVar = this.handlers.get(definition);
        Throwable th = null;
        if (jVar != null) {
            Throwable th2 = null;
            for (m nextNode = (m) jVar.getNext(); !p.a(nextNode, jVar); nextNode = nextNode.getNextNode()) {
                if (nextNode instanceof HandlerRegistration) {
                    try {
                        l<?, t0> handler = ((HandlerRegistration) nextNode).getHandler();
                        q0.e(1, handler);
                        handler.invoke(value);
                    } catch (Throwable th3) {
                        if (th2 != null) {
                            r0.a(th2, th3);
                            t0Var = t0.f22605a;
                        } else {
                            t0Var = null;
                        }
                        if (t0Var == null) {
                            th2 = th3;
                        }
                    }
                }
            }
            th = th2;
        }
        if (th != null) {
            throw th;
        }
    }

    public final <T> z0 subscribe(EventDefinition<T> definition, l<? super T, t0> handler) {
        HandlerRegistration handlerRegistration = new HandlerRegistration(handler);
        this.handlers.computeIfAbsent(definition, AnonymousClass1.INSTANCE).addLast(handlerRegistration);
        return handlerRegistration;
    }

    public final <T> void unsubscribe(EventDefinition<T> definition, l<? super T, t0> handler) {
        j jVar = this.handlers.get(definition);
        if (jVar != null) {
            for (m nextNode = (m) jVar.getNext(); !p.a(nextNode, jVar); nextNode = nextNode.getNextNode()) {
                if (nextNode instanceof HandlerRegistration) {
                    HandlerRegistration handlerRegistration = (HandlerRegistration) nextNode;
                    if (p.a(handlerRegistration.getHandler(), handler)) {
                        handlerRegistration.remove();
                    }
                }
            }
        }
    }
}
