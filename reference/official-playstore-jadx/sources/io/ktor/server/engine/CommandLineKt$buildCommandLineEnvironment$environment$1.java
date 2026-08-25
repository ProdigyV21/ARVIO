package io.ktor.server.engine;

import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.ApplicationConfigKt;
import io.ktor.util.PlatformUtils;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import kotlin.text.o;
import r7.l;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "Lx6/t0;", "invoke", "(Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CommandLineKt$buildCommandLineEnvironment$environment$1 extends r implements l<ApplicationEngineEnvironmentBuilder, t0> {
    final /* synthetic */ String[] $args;
    final /* synthetic */ Map<String, String> $argumentsPairs;
    final /* synthetic */ ApplicationConfig $configuration;
    final /* synthetic */ l<ApplicationEngineEnvironmentBuilder, t0> $environmentBuilder;
    final /* synthetic */ b $logger;
    final /* synthetic */ String $rootPath;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CommandLineKt$buildCommandLineEnvironment$environment$1(b bVar, String[] strArr, ApplicationConfig applicationConfig, String str, Map<String, String> map, l<? super ApplicationEngineEnvironmentBuilder, t0> lVar) {
        super(1);
        this.$logger = bVar;
        this.$args = strArr;
        this.$configuration = applicationConfig;
        this.$rootPath = str;
        this.$argumentsPairs = map;
        this.$environmentBuilder = lVar;
    }

    @Override // r7.l
    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApplicationEngineEnvironmentBuilder) obj);
        return t0.f22605a;
    }

    public final void invoke(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder) {
        ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder2;
        applicationEngineEnvironmentBuilder.setLog(this.$logger);
        EnvironmentUtilsJvmKt.configurePlatformProperties(applicationEngineEnvironmentBuilder, this.$args);
        applicationEngineEnvironmentBuilder.setConfig(this.$configuration);
        applicationEngineEnvironmentBuilder.setRootPath(this.$rootPath);
        String strTryGetString = this.$argumentsPairs.get("-host");
        if (strTryGetString == null && (strTryGetString = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostConfigPath)) == null) {
            strTryGetString = "0.0.0.0";
        }
        String str = strTryGetString;
        String strTryGetString2 = this.$argumentsPairs.get("-port");
        if (strTryGetString2 == null) {
            strTryGetString2 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostPortPath);
        }
        String strTryGetString3 = this.$argumentsPairs.get("-sslPort");
        if (strTryGetString3 == null) {
            strTryGetString3 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostSslPortPath);
        }
        String str2 = strTryGetString3;
        String strTryGetString4 = this.$argumentsPairs.get("-sslKeyStore");
        if (strTryGetString4 == null) {
            strTryGetString4 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostSslKeyStore);
        }
        String str3 = strTryGetString4;
        String strTryGetString5 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostSslKeyStorePassword);
        String string = strTryGetString5 != null ? o.L0(strTryGetString5).toString() : null;
        String strTryGetString6 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostSslPrivateKeyPassword);
        String string2 = strTryGetString6 != null ? o.L0(strTryGetString6).toString() : null;
        String strTryGetString7 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostSslKeyAlias);
        if (strTryGetString7 == null) {
            strTryGetString7 = "mykey";
        }
        String strTryGetString8 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.developmentModeKey);
        applicationEngineEnvironmentBuilder.setDevelopmentMode(strTryGetString8 != null ? Boolean.parseBoolean(strTryGetString8) : PlatformUtils.INSTANCE.getIS_DEVELOPMENT_MODE());
        if (strTryGetString2 != null) {
            List<EngineConnectorConfig> connectors = applicationEngineEnvironmentBuilder.getConnectors();
            EngineConnectorBuilder engineConnectorBuilder = new EngineConnectorBuilder(null, 1, null);
            engineConnectorBuilder.setHost(str);
            engineConnectorBuilder.setPort(Integer.parseInt(strTryGetString2));
            connectors.add(engineConnectorBuilder);
        }
        if (str2 != null) {
            String str4 = string;
            applicationEngineEnvironmentBuilder2 = applicationEngineEnvironmentBuilder;
            EnvironmentUtilsJvmKt.configureSSLConnectors(applicationEngineEnvironmentBuilder2, str, str2, str3, str4, string2, strTryGetString7);
        } else {
            applicationEngineEnvironmentBuilder2 = applicationEngineEnvironmentBuilder;
        }
        if (strTryGetString2 == null && str2 == null) {
            throw new IllegalArgumentException("Neither port nor sslPort specified. Use command line options -port/-sslPort or configure connectors in application.conf");
        }
        String str5 = this.$argumentsPairs.get("-watch");
        List<String> listY0 = str5 != null ? o.y0(str5, new String[]{","}, 0, 6) : ApplicationConfigKt.tryGetStringList(this.$configuration, ConfigKeys.hostWatchPaths);
        if (listY0 != null) {
            applicationEngineEnvironmentBuilder2.setWatchPaths(listY0);
        }
        this.$environmentBuilder.invoke(applicationEngineEnvironmentBuilder2);
    }
}
