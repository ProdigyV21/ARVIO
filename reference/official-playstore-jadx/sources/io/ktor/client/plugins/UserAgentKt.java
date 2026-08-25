package io.ktor.client.plugins;

import io.ktor.client.HttpClientConfig;
import io.ktor.client.plugins.UserAgent;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0015\u0010\u0002\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0000¢\u0006\u0004\b\u0004\u0010\u0003\"\u0018\u0010\u0007\u001a\u00060\u0005j\u0002`\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/client/HttpClientConfig;", "Lx6/t0;", "BrowserUserAgent", "(Lio/ktor/client/HttpClientConfig;)V", "CurlUserAgent", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UserAgentKt {
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.UserAgent");

    /* JADX INFO: renamed from: io.ktor.client.plugins.UserAgentKt$BrowserUserAgent$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/plugins/UserAgent$Config;", "Lx6/t0;", "invoke", "(Lio/ktor/client/plugins/UserAgent$Config;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<UserAgent.Config, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((UserAgent.Config) obj);
            return t0.f22605a;
        }

        public final void invoke(UserAgent.Config config) {
            config.setAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/70.0.3538.77 Chrome/70.0.3538.77 Safari/537.36");
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.UserAgentKt$CurlUserAgent$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/client/plugins/UserAgent$Config;", "Lx6/t0;", "invoke", "(Lio/ktor/client/plugins/UserAgent$Config;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C17321 extends r implements l<UserAgent.Config, t0> {
        public static final C17321 INSTANCE = new C17321();

        public C17321() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((UserAgent.Config) obj);
            return t0.f22605a;
        }

        public final void invoke(UserAgent.Config config) {
            config.setAgent("curl/7.61.0");
        }
    }

    public static final void BrowserUserAgent(HttpClientConfig<?> httpClientConfig) {
        httpClientConfig.install(UserAgent.INSTANCE, AnonymousClass1.INSTANCE);
    }

    public static final void CurlUserAgent(HttpClientConfig<?> httpClientConfig) {
        httpClientConfig.install(UserAgent.INSTANCE, C17321.INSTANCE);
    }
}
