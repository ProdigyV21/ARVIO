package io.ktor.server.engine;

import com.google.android.gms.cast.MediaError;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.OnCallContext;
import io.ktor.server.application.PluginBuilder;
import io.ktor.server.engine.ShutDownUrl;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.r;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/application/PluginBuilder;", "Lio/ktor/server/engine/ShutDownUrl$Config;", "Lx6/t0;", "invoke", "(Lio/ktor/server/application/PluginBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ShutDownUrl$Companion$ApplicationCallPlugin$2 extends r implements l<PluginBuilder<ShutDownUrl.Config>, t0> {
    public static final ShutDownUrl$Companion$ApplicationCallPlugin$2 INSTANCE = new ShutDownUrl$Companion$ApplicationCallPlugin$2();

    /* JADX INFO: renamed from: io.ktor.server.engine.ShutDownUrl$Companion$ApplicationCallPlugin$2$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/server/application/OnCallContext;", "Lio/ktor/server/engine/ShutDownUrl$Config;", "Lio/ktor/server/application/ApplicationCall;", "call", "Lx6/t0;", "<anonymous>", "(Lio/ktor/server/application/OnCallContext;Lio/ktor/server/application/ApplicationCall;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.server.engine.ShutDownUrl$Companion$ApplicationCallPlugin$2$1", f = "ShutDownUrl.kt", l = {MediaError.DetailedErrorCode.MEDIA_NETWORK}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<OnCallContext<ShutDownUrl.Config>, ApplicationCall, d<? super t0>, Object> {
        final /* synthetic */ ShutDownUrl $plugin;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ShutDownUrl shutDownUrl, d<? super AnonymousClass1> dVar) {
            super(3, dVar);
            this.$plugin = shutDownUrl;
        }

        @Override // r7.q
        public final Object invoke(OnCallContext<ShutDownUrl.Config> onCallContext, ApplicationCall applicationCall, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$plugin, dVar);
            anonymousClass1.L$0 = applicationCall;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ApplicationCall applicationCall = (ApplicationCall) this.L$0;
                if (p.a(ApplicationRequestPropertiesKt.getUri(applicationCall.getRequest()), this.$plugin.getUrl())) {
                    ShutDownUrl shutDownUrl = this.$plugin;
                    this.label = 1;
                    Object objDoShutdown = shutDownUrl.doShutdown(applicationCall, this);
                    a aVar = a.f15033i;
                    if (objDoShutdown == aVar) {
                        return aVar;
                    }
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0.f22605a;
        }
    }

    public ShutDownUrl$Companion$ApplicationCallPlugin$2() {
        super(1);
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PluginBuilder<ShutDownUrl.Config>) obj);
        return t0.f22605a;
    }

    public final void invoke(PluginBuilder<ShutDownUrl.Config> pluginBuilder) {
        pluginBuilder.onCall(new AnonymousClass1(new ShutDownUrl(pluginBuilder.getPluginConfig().getShutDownUrl(), pluginBuilder.getPluginConfig().getExitCodeSupplier()), null));
    }
}
