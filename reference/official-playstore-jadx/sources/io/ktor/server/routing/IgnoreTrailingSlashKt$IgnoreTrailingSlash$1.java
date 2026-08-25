package io.ktor.server.routing;

import d7.d;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.OnCallContext;
import io.ktor.server.application.PluginBuilder;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/application/PluginBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/server/application/PluginBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class IgnoreTrailingSlashKt$IgnoreTrailingSlash$1 extends r implements l<PluginBuilder<t0>, t0> {
    public static final IgnoreTrailingSlashKt$IgnoreTrailingSlash$1 INSTANCE = new IgnoreTrailingSlashKt$IgnoreTrailingSlash$1();

    /* JADX INFO: renamed from: io.ktor.server.routing.IgnoreTrailingSlashKt$IgnoreTrailingSlash$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/server/application/OnCallContext;", "Lx6/t0;", "Lio/ktor/server/application/ApplicationCall;", "call", "<anonymous>", "(Lio/ktor/server/application/OnCallContext;Lio/ktor/server/application/ApplicationCall;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.routing.IgnoreTrailingSlashKt$IgnoreTrailingSlash$1$1", f = "IgnoreTrailingSlash.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<OnCallContext<t0>, ApplicationCall, d<? super t0>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(OnCallContext<t0> onCallContext, ApplicationCall applicationCall, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
            anonymousClass1.L$0 = applicationCall;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            IgnoreTrailingSlashKt.setIgnoreTrailingSlash((ApplicationCall) this.L$0, true);
            return t0.f22605a;
        }
    }

    public IgnoreTrailingSlashKt$IgnoreTrailingSlash$1() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PluginBuilder<t0>) obj);
        return t0.f22605a;
    }

    public final void invoke(PluginBuilder<t0> pluginBuilder) {
        pluginBuilder.onCall(new AnonymousClass1(null));
    }
}
