package io.ktor.client.plugins;

import io.ktor.client.HttpClientConfig;
import io.ktor.client.plugins.HttpPlainText;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import r7.l;
import vc.b;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a.\u0010\u0006\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\u0004\b\u0006\u0010\u0007\"\u0018\u0010\n\u001a\u00060\bj\u0002`\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/client/HttpClientConfig;", "Lkotlin/Function1;", "Lio/ktor/client/plugins/HttpPlainText$Config;", "Lx6/t0;", "Lx6/n;", "block", "Charsets", "(Lio/ktor/client/HttpClientConfig;Lr7/l;)V", "Lvc/b;", "Lio/ktor/util/logging/Logger;", "LOGGER", "Lvc/b;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpPlainTextKt {
    private static final b LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpPlainText");

    /* JADX WARN: Multi-variable type inference failed */
    public static final void Charsets(HttpClientConfig<?> httpClientConfig, l<? super HttpPlainText.Config, t0> lVar) {
        httpClientConfig.install(HttpPlainText.Plugin, lVar);
    }
}
