package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\n\b\u0000\u0010\u0002 \u0001*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001J*\u0010\b\u001a\u00028\u00012\u0019\b\u0002\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006H&¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00028\u00012\u0006\u0010\f\u001a\u00020\u000bH&¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00010\u000f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lio/ktor/client/plugins/HttpClientPlugin;", "", "TConfig", "TPlugin", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Ljava/lang/Object;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Ljava/lang/Object;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "key", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface HttpClientPlugin<TConfig, TPlugin> {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Object prepare$default(HttpClientPlugin httpClientPlugin, l lVar, int i10, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: prepare");
            }
            if ((i10 & 1) != 0) {
                lVar = AnonymousClass1.INSTANCE;
            }
            return httpClientPlugin.prepare(lVar);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.HttpClientPlugin$prepare$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\n\b\u0000\u0010\u0001 \u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\u00028\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "TConfig", "TPlugin", "Lx6/t0;", "invoke", "(Ljava/lang/Object;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<TConfig, t0> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
        public final void m6664invoke(TConfig tconfig) {
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            m6664invoke(obj);
            return t0.f22605a;
        }
    }

    AttributeKey<TPlugin> getKey();

    void install(TPlugin plugin, HttpClient scope);

    TPlugin prepare(l<? super TConfig, t0> block);
}
