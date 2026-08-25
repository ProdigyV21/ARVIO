package io.ktor.server.application.debug;

import d7.d;
import e7.a;
import io.ktor.util.debug.ContextUtilsKt;
import io.ktor.util.debug.plugins.PluginTraceElement;
import io.ktor.util.debug.plugins.PluginsTrace;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a#\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a#\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0080@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"", "pluginName", "handler", "Lx6/t0;", "ijDebugReportHandlerStarted", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "ijDebugReportHandlerFinished", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UtilsKt {

    /* JADX INFO: renamed from: io.ktor.server.application.debug.UtilsKt$ijDebugReportHandlerFinished$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/util/debug/plugins/PluginsTrace;", "trace", "Lx6/t0;", "invoke", "(Lio/ktor/util/debug/plugins/PluginsTrace;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<PluginsTrace, t0> {
        final /* synthetic */ String $handler;
        final /* synthetic */ String $pluginName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, String str2) {
            super(1);
            this.$pluginName = str;
            this.$handler = str2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((PluginsTrace) obj);
            return t0.f22605a;
        }

        public final void invoke(PluginsTrace pluginsTrace) {
            pluginsTrace.getEventOrder().add(new PluginTraceElement(this.$pluginName, this.$handler, PluginTraceElement.PluginEvent.FINISHED));
        }
    }

    /* JADX INFO: renamed from: io.ktor.server.application.debug.UtilsKt$ijDebugReportHandlerStarted$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lio/ktor/util/debug/plugins/PluginsTrace;", "trace", "Lx6/t0;", "invoke", "(Lio/ktor/util/debug/plugins/PluginsTrace;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C18562 extends r implements l<PluginsTrace, t0> {
        final /* synthetic */ String $handler;
        final /* synthetic */ String $pluginName;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C18562(String str, String str2) {
            super(1);
            this.$pluginName = str;
            this.$handler = str2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((PluginsTrace) obj);
            return t0.f22605a;
        }

        public final void invoke(PluginsTrace pluginsTrace) {
            pluginsTrace.getEventOrder().add(new PluginTraceElement(this.$pluginName, this.$handler, PluginTraceElement.PluginEvent.STARTED));
        }
    }

    public static final Object ijDebugReportHandlerFinished(String str, String str2, d<? super t0> dVar) {
        Object objUseContextElementInDebugMode = ContextUtilsKt.useContextElementInDebugMode(PluginsTrace.INSTANCE, new AnonymousClass2(str, str2), dVar);
        return objUseContextElementInDebugMode == a.f15033i ? objUseContextElementInDebugMode : t0.f22605a;
    }

    public static final Object ijDebugReportHandlerStarted(String str, String str2, d<? super t0> dVar) {
        Object objUseContextElementInDebugMode = ContextUtilsKt.useContextElementInDebugMode(PluginsTrace.INSTANCE, new C18562(str, str2), dVar);
        return objUseContextElementInDebugMode == a.f15033i ? objUseContextElementInDebugMode : t0.f22605a;
    }
}
