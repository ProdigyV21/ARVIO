package io.ktor.client.plugins.api;

import io.ktor.client.HttpClient;
import io.ktor.events.EventDefinition;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\u000e\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u00028\u00000\u00032\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u00060\u0005B\u000f\u0012\u0006\u0010\b\u001a\u00028\u0001¢\u0006\u0004\b\t\u0010\nJ+\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u0006H\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\u0014\u0010\b\u001a\u00028\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/client/plugins/api/MonitoringEvent;", "", "Param", "Lio/ktor/events/EventDefinition;", "Event", "Lio/ktor/client/plugins/api/ClientHook;", "Lkotlin/Function1;", "Lx6/t0;", "event", "<init>", "(Lio/ktor/events/EventDefinition;)V", "Lio/ktor/client/HttpClient;", "client", "handler", "install", "(Lio/ktor/client/HttpClient;Lr7/l;)V", "Lio/ktor/events/EventDefinition;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MonitoringEvent<Param, Event extends EventDefinition<Param>> implements ClientHook<l<? super Param, ? extends t0>> {
    private final Event event;

    /* JADX INFO: renamed from: io.ktor.client.plugins.api.MonitoringEvent$install$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\u0004\u001a\u00028\u0000H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"", "Param", "Lio/ktor/events/EventDefinition;", "Event", "it", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Param, t0> {
        final /* synthetic */ l<Param, t0> $handler;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(l<? super Param, t0> lVar) {
            super(1);
            this.$handler = lVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m6668invoke(obj);
            return t0.f22605a;
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6668invoke(Param param) {
            this.$handler.invoke(param);
        }
    }

    public MonitoringEvent(Event event) {
        this.event = event;
    }

    @Override // io.ktor.client.plugins.api.ClientHook
    public void install(HttpClient client, l<? super Param, t0> handler) {
        client.getMonitor().subscribe(this.event, new AnonymousClass1(handler));
    }
}
