package io.ktor.server.application;

import io.ktor.util.pipeline.Pipeline;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0014\b\u0000\u0010\u0001 \u0000*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020\u00030\u0002*\n\b\u0001\u0010\u0004 \u0001*\u00020\u0005*\b\b\u0002\u0010\u0006*\u00020\u00052\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00060\u0007¨\u0006\b"}, d2 = {"Lio/ktor/server/application/BaseApplicationPlugin;", "TPipeline", "Lio/ktor/util/pipeline/Pipeline;", "Lio/ktor/server/application/ApplicationCall;", "TConfiguration", "", "TPlugin", "Lio/ktor/server/application/Plugin;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface BaseApplicationPlugin<TPipeline extends Pipeline<?, ApplicationCall>, TConfiguration, TPlugin> extends Plugin<TPipeline, TConfiguration, TPlugin> {
}
