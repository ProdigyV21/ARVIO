package io.ktor.client.plugins.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.client.HttpClient;
import io.ktor.http.ContentDisposition;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aR\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\"\b\b\u0000\u0010\u0001*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u001d\u0010\n\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¢\u0006\u0004\b\f\u0010\r\u001a:\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u001d\u0010\n\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¢\u0006\u0004\b\f\u0010\u000e¨\u0006\u000f"}, d2 = {"", "PluginConfigT", "", ContentDisposition.Parameters.Name, "Lkotlin/Function0;", "createConfiguration", "Lkotlin/Function1;", "Lio/ktor/client/plugins/api/ClientPluginBuilder;", "Lx6/t0;", "Lx6/n;", TtmlNode.TAG_BODY, "Lio/ktor/client/plugins/api/ClientPlugin;", "createClientPlugin", "(Ljava/lang/String;Lr7/a;Lr7/l;)Lio/ktor/client/plugins/api/ClientPlugin;", "(Ljava/lang/String;Lr7/l;)Lio/ktor/client/plugins/api/ClientPlugin;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CreatePluginUtilsKt {

    /* JADX INFO: renamed from: io.ktor.client.plugins.api.CreatePluginUtilsKt$createClientPlugin$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements a<t0> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(0);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6667invoke() {
        }

        @Override // r7.a
        public /* bridge */ /* synthetic */ Object invoke() {
            m6667invoke();
            return t0.f22605a;
        }
    }

    public static final <PluginConfigT> ClientPlugin<PluginConfigT> createClientPlugin(String str, a<? extends PluginConfigT> aVar, l<? super ClientPluginBuilder<PluginConfigT>, t0> lVar) {
        return new ClientPlugin<PluginConfigT>(str, aVar, lVar) { // from class: io.ktor.client.plugins.api.CreatePluginUtilsKt.createClientPlugin.1
            final /* synthetic */ l<ClientPluginBuilder<PluginConfigT>, t0> $body;
            final /* synthetic */ a<PluginConfigT> $createConfiguration;
            final /* synthetic */ String $name;
            private final AttributeKey<ClientPluginInstance<PluginConfigT>> key;

            /* JADX WARN: Multi-variable type inference failed */
            {
                this.$name = str;
                this.$createConfiguration = aVar;
                this.$body = lVar;
                this.key = new AttributeKey<>(str);
            }

            @Override // io.ktor.client.plugins.HttpClientPlugin
            public AttributeKey<ClientPluginInstance<PluginConfigT>> getKey() {
                return this.key;
            }

            @Override // io.ktor.client.plugins.HttpClientPlugin
            public void install(ClientPluginInstance<PluginConfigT> plugin, HttpClient scope) {
                plugin.install(scope);
            }

            @Override // io.ktor.client.plugins.HttpClientPlugin
            public ClientPluginInstance<PluginConfigT> prepare(l<? super PluginConfigT, t0> block) {
                Object objInvoke = this.$createConfiguration.invoke();
                block.invoke(objInvoke);
                return new ClientPluginInstance<>(objInvoke, this.$name, this.$body);
            }
        };
    }

    public static final ClientPlugin<t0> createClientPlugin(String str, l<? super ClientPluginBuilder<t0>, t0> lVar) {
        return createClientPlugin(str, AnonymousClass2.INSTANCE, lVar);
    }
}
