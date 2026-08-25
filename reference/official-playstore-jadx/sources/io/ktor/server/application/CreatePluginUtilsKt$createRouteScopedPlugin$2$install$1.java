package io.ktor.server.application;

import io.ktor.server.config.ApplicationConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;

/* JADX INFO: Add missing generic type declarations: [PluginConfigT] */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "PluginConfigT", "", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class CreatePluginUtilsKt$createRouteScopedPlugin$2$install$1<PluginConfigT> extends r implements a<PluginConfigT> {
    final /* synthetic */ ApplicationConfig $config;
    final /* synthetic */ l<ApplicationConfig, PluginConfigT> $createConfiguration;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CreatePluginUtilsKt$createRouteScopedPlugin$2$install$1(l<? super ApplicationConfig, ? extends PluginConfigT> lVar, ApplicationConfig applicationConfig) {
        super(0);
        this.$createConfiguration = lVar;
        this.$config = applicationConfig;
    }

    @Override // r7.a
    public final PluginConfigT invoke() {
        return (PluginConfigT) this.$createConfiguration.invoke(this.$config);
    }
}
