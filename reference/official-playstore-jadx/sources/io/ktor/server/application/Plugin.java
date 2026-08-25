package io.ktor.server.application;

import io.ktor.util.AttributeKey;
import io.ktor.util.pipeline.Pipeline;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0014\b\u0000\u0010\u0003 \u0000*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00020\u0001*\n\b\u0001\u0010\u0005 \u0001*\u00020\u0004*\b\b\u0002\u0010\u0006*\u00020\u00042\u00020\u0004J0\u0010\f\u001a\u00028\u00022\u0006\u0010\u0007\u001a\u00028\u00002\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\nH&¢\u0006\u0004\b\f\u0010\rR\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00020\u000e8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lio/ktor/server/application/Plugin;", "Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/server/application/ApplicationCall;", "TPipeline", "", "TConfiguration", "TPlugin", "pipeline", "Lkotlin/Function1;", "Lx6/t0;", "Lx6/n;", "configure", "install", "(Lio/ktor/util/pipeline/Pipeline;Lr7/l;)Ljava/lang/Object;", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "key", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Plugin<TPipeline extends Pipeline<?, ApplicationCall>, TConfiguration, TPlugin> {
    AttributeKey<TPlugin> getKey();

    TPlugin install(TPipeline pipeline, l<? super TConfiguration, t0> configure);
}
