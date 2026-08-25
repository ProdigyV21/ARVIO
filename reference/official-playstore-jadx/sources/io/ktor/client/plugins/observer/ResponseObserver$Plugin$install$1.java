package io.ktor.client.plugins.observer;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.statement.HttpResponse;
import io.ktor.util.ByteChannelsKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.utils.io.ByteReadChannel;
import k2.c;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0002*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponse;", "Lx6/t0;", "response", "<anonymous>", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponse;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.observer.ResponseObserver$Plugin$install$1", f = "ResponseObserver.kt", l = {68, 77}, m = "invokeSuspend")
public final class ResponseObserver$Plugin$install$1 extends j implements q<PipelineContext<HttpResponse, t0>, HttpResponse, d<? super t0>, Object> {
    final /* synthetic */ ResponseObserver $plugin;
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: renamed from: io.ktor.client.plugins.observer.ResponseObserver$Plugin$install$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.client.plugins.observer.ResponseObserver$Plugin$install$1$1", f = "ResponseObserver.kt", l = {69, 73}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d<? super t0>, Object> {
        final /* synthetic */ ResponseObserver $plugin;
        final /* synthetic */ HttpResponse $sideResponse;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(HttpResponse httpResponse, ResponseObserver responseObserver, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$sideResponse = httpResponse;
            this.$plugin = responseObserver;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$sideResponse, this.$plugin, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0037, code lost:
        
            if (r0.invoke(r4, r5) == r3) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
        
            if (r6 == r3) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0051, code lost:
        
            return r3;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                int r0 = r5.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L20
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r6)     // Catch: java.lang.Throwable -> L5d
                goto L52
            L10:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L18:
                java.lang.Object r0 = r5.L$0
                ka.k0 r0 = (ka.k0) r0
                k2.c.G(r6)     // Catch: java.lang.Throwable -> L3a
                goto L3a
            L20:
                k2.c.G(r6)
                java.lang.Object r6 = r5.L$0
                ka.k0 r6 = (ka.k0) r6
                io.ktor.client.plugins.observer.ResponseObserver r0 = r5.$plugin
                io.ktor.client.statement.HttpResponse r4 = r5.$sideResponse
                r7.p r0 = io.ktor.client.plugins.observer.ResponseObserver.access$getResponseHandler$p(r0)     // Catch: java.lang.Throwable -> L3a
                r5.L$0 = r6     // Catch: java.lang.Throwable -> L3a
                r5.label = r2     // Catch: java.lang.Throwable -> L3a
                java.lang.Object r6 = r0.invoke(r4, r5)     // Catch: java.lang.Throwable -> L3a
                if (r6 != r3) goto L3a
                goto L51
            L3a:
                io.ktor.client.statement.HttpResponse r6 = r5.$sideResponse
                io.ktor.utils.io.ByteReadChannel r6 = r6.getContent()
                boolean r0 = r6.isClosedForRead()
                if (r0 != 0) goto L5d
                r0 = 0
                r5.L$0 = r0     // Catch: java.lang.Throwable -> L5d
                r5.label = r1     // Catch: java.lang.Throwable -> L5d
                java.lang.Object r6 = io.ktor.utils.io.ByteReadChannelKt.discard(r6, r5)     // Catch: java.lang.Throwable -> L5d
                if (r6 != r3) goto L52
            L51:
                return r3
            L52:
                java.lang.Number r6 = (java.lang.Number) r6     // Catch: java.lang.Throwable -> L5d
                long r0 = r6.longValue()     // Catch: java.lang.Throwable -> L5d
                java.lang.Long r6 = new java.lang.Long     // Catch: java.lang.Throwable -> L5d
                r6.<init>(r0)     // Catch: java.lang.Throwable -> L5d
            L5d:
                x6.t0 r6 = x6.t0.f22605a
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.observer.ResponseObserver$Plugin$install$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ResponseObserver$Plugin$install$1(ResponseObserver responseObserver, HttpClient httpClient, d<? super ResponseObserver$Plugin$install$1> dVar) {
        super(3, dVar);
        this.$plugin = responseObserver;
        this.$scope = httpClient;
    }

    @Override // r7.q
    public final Object invoke(PipelineContext<HttpResponse, t0> pipelineContext, HttpResponse httpResponse, d<? super t0> dVar) {
        ResponseObserver$Plugin$install$1 responseObserver$Plugin$install$1 = new ResponseObserver$Plugin$install$1(this.$plugin, this.$scope, dVar);
        responseObserver$Plugin$install$1.L$0 = pipelineContext;
        responseObserver$Plugin$install$1.L$1 = httpResponse;
        return responseObserver$Plugin$install$1.invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        PipelineContext pipelineContext;
        HttpResponse httpResponse;
        HttpResponse httpResponse2;
        k0 k0Var;
        int i10 = this.label;
        t0 t0Var = t0.f22605a;
        a aVar = a.f15033i;
        if (i10 == 0) {
            c.G(obj);
            pipelineContext = (PipelineContext) this.L$0;
            HttpResponse httpResponse3 = (HttpResponse) this.L$1;
            l lVar = this.$plugin.filter;
            if (lVar == null || ((Boolean) lVar.invoke(httpResponse3.getCall())).booleanValue()) {
                x xVarSplit = ByteChannelsKt.split(httpResponse3.getContent(), httpResponse3);
                ByteReadChannel byteReadChannel = (ByteReadChannel) xVarSplit.f22608i;
                HttpResponse response = DelegatedCallKt.wrapWithContent(httpResponse3.getCall(), (ByteReadChannel) xVarSplit.f22609l).getResponse();
                HttpResponse response2 = DelegatedCallKt.wrapWithContent(httpResponse3.getCall(), byteReadChannel).getResponse();
                HttpClient httpClient = this.$scope;
                this.L$0 = pipelineContext;
                this.L$1 = response;
                this.L$2 = response2;
                this.L$3 = httpClient;
                this.label = 1;
                Object responseObserverContext = ResponseObserverContextJvmKt.getResponseObserverContext(this);
                if (responseObserverContext != aVar) {
                    httpResponse = response2;
                    obj = responseObserverContext;
                    httpResponse2 = response;
                    k0Var = httpClient;
                }
            }
        }
        if (i10 != 1) {
            if (i10 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            return t0Var;
        }
        k0Var = (k0) this.L$3;
        httpResponse = (HttpResponse) this.L$2;
        httpResponse2 = (HttpResponse) this.L$1;
        pipelineContext = (PipelineContext) this.L$0;
        c.G(obj);
        m0.p(k0Var, (d7.j) obj, 0, new AnonymousClass1(httpResponse, this.$plugin, null), 2);
        this.L$0 = null;
        this.L$1 = null;
        this.L$2 = null;
        this.L$3 = null;
        this.label = 2;
        return pipelineContext.proceedWith(httpResponse2, this) == aVar ? aVar : t0Var;
    }
}
