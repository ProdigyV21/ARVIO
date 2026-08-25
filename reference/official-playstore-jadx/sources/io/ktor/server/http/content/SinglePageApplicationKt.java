package io.ktor.server.http.content;

import io.ktor.http.ContentDisposition;
import io.ktor.server.routing.Route;
import java.io.File;
import java.net.URL;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\u001a,\u0010\u0006\u001a\u00020\u0003*\u00020\u00002\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u0006\u0010\u0007\u001a4\u0010\u000e\u001a\u00020\u0003*\u00020\u00022!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0019\u0010\u0011\u001a\u00020\u0003*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0019\u0010\u0013\u001a\u00020\u0003*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0013\u0010\u0012\u001a\u0019\u0010\u0014\u001a\u00020\u0003*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0014\u0010\u0012\u001a\u0019\u0010\u0015\u001a\u00020\u0003*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0015\u0010\u0012\u001a\u0019\u0010\u0016\u001a\u00020\u0003*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\b¢\u0006\u0004\b\u0016\u0010\u0012¨\u0006\u0017"}, d2 = {"Lio/ktor/server/routing/Route;", "Lkotlin/Function1;", "Lio/ktor/server/http/content/SPAConfig;", "Lx6/t0;", "Lx6/n;", "configBuilder", "singlePageApplication", "(Lio/ktor/server/routing/Route;Lr7/l;)V", "", "Lx6/y;", ContentDisposition.Parameters.Name, "path", "", "block", "ignoreFiles", "(Lio/ktor/server/http/content/SPAConfig;Lr7/l;)V", "filesPath", "angular", "(Lio/ktor/server/http/content/SPAConfig;Ljava/lang/String;)V", "react", "vue", "ember", "backbone", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SinglePageApplicationKt {

    /* JADX INFO: renamed from: io.ktor.server.http.content.SinglePageApplicationKt$singlePageApplication$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/server/http/content/SPAConfig;", "Lx6/t0;", "invoke", "(Lio/ktor/server/http/content/SPAConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<SPAConfig, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        public final void invoke(SPAConfig sPAConfig) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SPAConfig) obj);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.SinglePageApplicationKt$singlePageApplication$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/http/content/StaticContentConfig;", "Ljava/net/URL;", "Lx6/t0;", "invoke", "(Lio/ktor/server/http/content/StaticContentConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<StaticContentConfig<URL>, t0> {
        final /* synthetic */ SPAConfig $config;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(SPAConfig sPAConfig) {
            super(1);
            this.$config = sPAConfig;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((StaticContentConfig<URL>) obj);
            return t0.f22605a;
        }

        public final void invoke(StaticContentConfig<URL> staticContentConfig) {
            staticContentConfig.m6713default(this.$config.getDefaultPage());
            Iterator<T> it = this.$config.getIgnoredFiles$ktor_server_core().iterator();
            while (it.hasNext()) {
                staticContentConfig.exclude(new SinglePageApplicationKt$singlePageApplication$2$1$1((l) it.next()));
            }
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.http.content.SinglePageApplicationKt$singlePageApplication$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/server/http/content/StaticContentConfig;", "Ljava/io/File;", "Lx6/t0;", "invoke", "(Lio/ktor/server/http/content/StaticContentConfig;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<StaticContentConfig<File>, t0> {
        final /* synthetic */ SPAConfig $config;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(SPAConfig sPAConfig) {
            super(1);
            this.$config = sPAConfig;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((StaticContentConfig<File>) obj);
            return t0.f22605a;
        }

        public final void invoke(StaticContentConfig<File> staticContentConfig) {
            staticContentConfig.m6713default(this.$config.getDefaultPage());
            Iterator<T> it = this.$config.getIgnoredFiles$ktor_server_core().iterator();
            while (it.hasNext()) {
                staticContentConfig.exclude(new SinglePageApplicationKt$singlePageApplication$3$1$1((l) it.next()));
            }
        }
    }

    public static final void angular(SPAConfig sPAConfig, String str) {
        sPAConfig.setFilesPath(str);
    }

    public static final void backbone(SPAConfig sPAConfig, String str) {
        sPAConfig.setFilesPath(str);
    }

    public static final void ember(SPAConfig sPAConfig, String str) {
        sPAConfig.setFilesPath(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void ignoreFiles(SPAConfig sPAConfig, l<? super String, Boolean> lVar) {
        sPAConfig.getIgnoredFiles$ktor_server_core().add(lVar);
    }

    public static final void react(SPAConfig sPAConfig, String str) {
        sPAConfig.setFilesPath(str);
    }

    public static final void singlePageApplication(Route route, l<? super SPAConfig, t0> lVar) {
        SPAConfig sPAConfig = new SPAConfig(null, null, null, false, null, 31, null);
        lVar.invoke(sPAConfig);
        if (sPAConfig.getUseResources()) {
            StaticContentKt.staticResources(route, sPAConfig.getApplicationRoute(), sPAConfig.getFilesPath(), sPAConfig.getDefaultPage(), new AnonymousClass2(sPAConfig));
        } else {
            StaticContentKt.staticFiles(route, sPAConfig.getApplicationRoute(), new File(sPAConfig.getFilesPath()), sPAConfig.getDefaultPage(), new AnonymousClass3(sPAConfig));
        }
    }

    public static /* synthetic */ void singlePageApplication$default(Route route, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = AnonymousClass1.INSTANCE;
        }
        singlePageApplication(route, lVar);
    }

    public static final void vue(SPAConfig sPAConfig, String str) {
        sPAConfig.setFilesPath(str);
    }
}
