package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lio/ktor/client/plugins/HttpRequestLifecycle;", "", "()V", "Plugin", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpRequestLifecycle {

    /* JADX INFO: renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<HttpRequestLifecycle> key = new AttributeKey<>("RequestLifecycle");

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpRequestLifecycle$Plugin, reason: from kotlin metadata */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\t\u001a\u00020\u00032\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\b\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/ktor/client/plugins/HttpRequestLifecycle$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lx6/t0;", "Lio/ktor/client/plugins/HttpRequestLifecycle;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/HttpRequestLifecycle;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/HttpRequestLifecycle;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<t0, HttpRequestLifecycle> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<HttpRequestLifecycle> getKey() {
            return HttpRequestLifecycle.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(HttpRequestLifecycle plugin, HttpClient scope) {
            scope.getRequestPipeline().intercept(HttpRequestPipeline.INSTANCE.getBefore(), new HttpRequestLifecycle$Plugin$install$1(scope, null));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public HttpRequestLifecycle prepare(l<? super t0, t0> block) {
            return new HttpRequestLifecycle(null);
        }
    }

    public /* synthetic */ HttpRequestLifecycle(h hVar) {
        this();
    }

    private HttpRequestLifecycle() {
    }
}
