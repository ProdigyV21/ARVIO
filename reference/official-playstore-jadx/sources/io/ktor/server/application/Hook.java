package io.ktor.server.application;

import io.ktor.util.KtorDsl;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@KtorDsl
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lio/ktor/server/application/Hook;", "HookHandler", "", "Lio/ktor/server/application/ApplicationCallPipeline;", "pipeline", "handler", "Lx6/t0;", "install", "(Lio/ktor/server/application/ApplicationCallPipeline;Ljava/lang/Object;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Hook<HookHandler> {
    void install(ApplicationCallPipeline pipeline, HookHandler handler);
}
