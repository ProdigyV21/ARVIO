package io.ktor.client.plugins;

import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.content.ObservableContent;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpReceivePipeline;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.AttributeKey;
import io.ktor.util.pipeline.InvalidPhaseException;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \t2\u00020\u0001:\u0001\tB\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lio/ktor/client/plugins/BodyProgress;", "", "<init>", "()V", "Lio/ktor/client/HttpClient;", "scope", "Lx6/t0;", "handle", "(Lio/ktor/client/HttpClient;)V", "Plugin", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BodyProgress {

    /* JADX INFO: renamed from: Plugin, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AttributeKey<BodyProgress> key = new AttributeKey<>("BodyProgress");

    /* JADX INFO: renamed from: io.ktor.client.plugins.BodyProgress$Plugin, reason: from kotlin metadata */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J(\u0010\t\u001a\u00020\u00032\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0002\b\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000e\u0010\u000fR \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lio/ktor/client/plugins/BodyProgress$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lx6/t0;", "Lio/ktor/client/plugins/BodyProgress;", "<init>", "()V", "Lkotlin/Function1;", "Lx6/n;", "block", "prepare", "(Lr7/l;)Lio/ktor/client/plugins/BodyProgress;", "plugin", "Lio/ktor/client/HttpClient;", "scope", "install", "(Lio/ktor/client/plugins/BodyProgress;Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/AttributeKey;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements HttpClientPlugin<t0, BodyProgress> {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public AttributeKey<BodyProgress> getKey() {
            return BodyProgress.key;
        }

        private Companion() {
        }

        @Override // io.ktor.client.plugins.HttpClientPlugin
        public void install(BodyProgress plugin, HttpClient scope) throws InvalidPhaseException {
            plugin.handle(scope);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.ktor.client.plugins.HttpClientPlugin
        public BodyProgress prepare(l<? super t0, t0> block) {
            return new BodyProgress();
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.BodyProgress$handle$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u00020\u0004*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "content", "Lx6/t0;", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Ljava/lang/Object;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.plugins.BodyProgress$handle$1", f = "BodyProgress.kt", l = {FlacConstants.STREAM_INFO_BLOCK_SIZE}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements q<PipelineContext<Object, HttpRequestBuilder>, Object, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, d<? super t0> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(dVar);
            anonymousClass1.L$0 = pipelineContext;
            anonymousClass1.L$1 = obj;
            return anonymousClass1.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 == 0) {
                c.G(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                Object obj2 = this.L$1;
                q qVar = (q) ((HttpRequestBuilder) pipelineContext.getContext()).getAttributes().getOrNull(BodyProgressKt.UploadProgressListenerAttributeKey);
                if (qVar == null) {
                    return t0Var;
                }
                ObservableContent observableContent = new ObservableContent((OutgoingContent) obj2, ((HttpRequestBuilder) pipelineContext.getContext()).getExecutionContext(), qVar);
                this.L$0 = null;
                this.label = 1;
                Object objProceedWith = pipelineContext.proceedWith(observableContent, this);
                a aVar = a.f15033i;
                if (objProceedWith == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0Var;
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.plugins.BodyProgress$handle$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0002*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponse;", "Lx6/t0;", "response", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponse;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.plugins.BodyProgress$handle$2", f = "BodyProgress.kt", l = {TsExtractor.TS_STREAM_TYPE_MHAS}, m = "invokeSuspend")
    public static final class AnonymousClass2 extends j implements q<PipelineContext<HttpResponse, t0>, HttpResponse, d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public AnonymousClass2(d<? super AnonymousClass2> dVar) {
            super(3, dVar);
        }

        @Override // r7.q
        public final Object invoke(PipelineContext<HttpResponse, t0> pipelineContext, HttpResponse httpResponse, d<? super t0> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.L$0 = pipelineContext;
            anonymousClass2.L$1 = httpResponse;
            return anonymousClass2.invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 == 0) {
                c.G(obj);
                PipelineContext pipelineContext = (PipelineContext) this.L$0;
                HttpResponse httpResponse = (HttpResponse) this.L$1;
                q qVar = (q) httpResponse.getCall().getRequest().getAttributes().getOrNull(BodyProgressKt.DownloadProgressListenerAttributeKey);
                if (qVar == null) {
                    return t0Var;
                }
                HttpResponse httpResponseWithObservableDownload = BodyProgressKt.withObservableDownload(httpResponse, qVar);
                this.L$0 = null;
                this.label = 1;
                Object objProceedWith = pipelineContext.proceedWith(httpResponseWithObservableDownload, this);
                a aVar = a.f15033i;
                if (objProceedWith == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                c.G(obj);
            }
            return t0Var;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handle(HttpClient scope) throws InvalidPhaseException {
        PipelinePhase pipelinePhase = new PipelinePhase("ObservableContent");
        scope.getRequestPipeline().insertPhaseAfter(HttpRequestPipeline.INSTANCE.getRender(), pipelinePhase);
        scope.getRequestPipeline().intercept(pipelinePhase, new AnonymousClass1(null));
        scope.getReceivePipeline().intercept(HttpReceivePipeline.INSTANCE.getAfter(), new AnonymousClass2(null));
    }
}
