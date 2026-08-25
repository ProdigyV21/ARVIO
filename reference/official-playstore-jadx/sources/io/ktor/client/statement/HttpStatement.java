package io.ktor.client.statement;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.c;
import f7.e;
import f7.j;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.call.SavedCallKt;
import io.ktor.client.engine.HttpClientEngineCapabilityKt;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.utils.ExceptionUtilsJvmKt;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJL\u0010\u0013\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000b21\u0010\u0012\u001a-\b\u0001\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0013\u0010\u0013\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0015J\u001b\u0010\u0016\u001a\u00028\u0000\"\u0006\b\u0000\u0010\u000b\u0018\u0001H\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0015JV\u0010\u0016\u001a\u00028\u0001\"\u0006\b\u0000\u0010\u000b\u0018\u0001\"\u0004\b\u0001\u0010\u001723\b\u0004\u0010\u0012\u001a-\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0086Hø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0014J\u0013\u0010\u0018\u001a\u00020\rH\u0081@ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0015J\u0017\u0010\u0019\u001a\u00020\b*\u00020\rH\u0081@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u000f\u0010\u001c\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001eR \u0010\u0005\u001a\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0012\n\u0004\b\u0005\u0010\u001f\u0012\u0004\b\"\u0010\n\u001a\u0004\b \u0010!\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lio/ktor/client/statement/HttpStatement;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "builder", "Lio/ktor/client/HttpClient;", "client", "<init>", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/client/HttpClient;)V", "Lx6/t0;", "checkCapabilities", "()V", "T", "Lkotlin/Function2;", "Lio/ktor/client/statement/HttpResponse;", "Lx6/y;", ContentDisposition.Parameters.Name, "response", "Ld7/d;", "block", "execute", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "(Ld7/d;)Ljava/lang/Object;", TtmlNode.TAG_BODY, "R", "executeUnsafe", "cleanup", "(Lio/ktor/client/statement/HttpResponse;Ld7/d;)Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/client/HttpClient;", "getClient", "()Lio/ktor/client/HttpClient;", "getClient$annotations", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HttpStatement {
    private final HttpRequestBuilder builder;
    private final HttpClient client;

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpStatement$cleanup$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.statement.HttpStatement", f = "HttpStatement.kt", l = {126}, m = "cleanup")
    public static final class AnonymousClass1 extends c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpStatement.this.cleanup(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpStatement$execute$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.statement.HttpStatement", f = "HttpStatement.kt", l = {47, 50, 52, 52}, m = "execute")
    public static final class C18081<T> extends c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C18081(d<? super C18081> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpStatement.this.execute(null, this);
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpStatement$execute$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@"}, d2 = {"<anonymous>", "Lio/ktor/client/statement/HttpResponse;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.statement.HttpStatement$execute$4", f = "HttpStatement.kt", l = {ColorSpace.MaxId}, m = "invokeSuspend")
    public static final class AnonymousClass4 extends j implements p<HttpResponse, d<? super HttpResponse>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass4(d<? super AnonymousClass4> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(dVar);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        @Override // r7.p
        public final Object invoke(HttpResponse httpResponse, d<? super HttpResponse> dVar) {
            return ((AnonymousClass4) create(httpResponse, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                HttpClientCall call = ((HttpResponse) this.L$0).getCall();
                this.label = 1;
                obj = SavedCallKt.save(call, this);
                a aVar = a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return ((HttpClientCall) obj).getResponse();
        }
    }

    /* JADX INFO: renamed from: io.ktor.client.statement.HttpStatement$executeUnsafe$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @e(c = "io.ktor.client.statement.HttpStatement", f = "HttpStatement.kt", l = {108}, m = "executeUnsafe")
    public static final class C18091 extends c {
        int label;
        /* synthetic */ Object result;

        public C18091(d<? super C18091> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HttpStatement.this.executeUnsafe(this);
        }
    }

    public HttpStatement(HttpRequestBuilder httpRequestBuilder, HttpClient httpClient) {
        this.builder = httpRequestBuilder;
        this.client = httpClient;
        checkCapabilities();
    }

    private final void checkCapabilities() {
        Set setKeySet;
        Map map = (Map) this.builder.getAttributes().getOrNull(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY());
        if (map == null || (setKeySet = map.keySet()) == null) {
            return;
        }
        ArrayList<HttpClientPlugin> arrayList = new ArrayList();
        for (Object obj : setKeySet) {
            if (obj instanceof HttpClientPlugin) {
                arrayList.add(obj);
            }
        }
        for (HttpClientPlugin httpClientPlugin : arrayList) {
            if (HttpClientPluginKt.pluginOrNull(this.client, httpClientPlugin) == null) {
                throw new IllegalArgumentException(("Consider installing " + httpClientPlugin + " plugin because the request requires it to be installed").toString());
            }
        }
    }

    public static /* synthetic */ void getClient$annotations() {
    }

    public final <T> Object body(d<? super T> dVar) throws Throwable {
        try {
            HttpResponse httpResponse = (HttpResponse) executeUnsafe(null);
            try {
                httpResponse.getCall();
                kotlin.jvm.internal.p.h();
                throw null;
            } catch (Throwable th) {
                HttpResponseKt.complete(httpResponse);
                throw th;
            }
        } catch (CancellationException e5) {
            throw ExceptionUtilsJvmKt.unwrapCancellationException(e5);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object cleanup(io.ktor.client.statement.HttpResponse r5, d7.d<? super x6.t0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.statement.HttpStatement.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r6
            io.ktor.client.statement.HttpStatement$cleanup$1 r0 = (io.ktor.client.statement.HttpStatement.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.statement.HttpStatement$cleanup$1 r0 = new io.ktor.client.statement.HttpStatement$cleanup$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            ka.t r5 = (ka.t) r5
            k2.c.G(r6)
            goto L57
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            d7.j r6 = r5.getCoroutineContext()
            ka.u1 r1 = ka.u1.f19642i
            d7.j$a r6 = r6.get(r1)
            ka.t r6 = (ka.t) r6
            r6.complete()
            io.ktor.utils.io.ByteReadChannel r5 = r5.getContent()     // Catch: java.lang.Throwable -> L4a
            io.ktor.utils.io.ByteReadChannelKt.cancel(r5)     // Catch: java.lang.Throwable -> L4a
        L4a:
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r5 = r6.join(r0)
            e7.a r6 = e7.a.f15033i
            if (r5 != r6) goto L57
            return r6
        L57:
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpStatement.cleanup(io.ktor.client.statement.HttpResponse, d7.d):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:57|(1:(1:(1:(1:(2:14|15)(3:16|17|50))(3:21|22|23))(5:24|53|25|42|(2:44|49)(1:45)))(2:29|30))(3:32|33|(2:35|49)(1:36))|37|55|38|(3:41|42|(0)(0))|49) */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0095, code lost:
    
        r10 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a0, code lost:
    
        if (r1.cleanup(r11, r0) == r7) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0094 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final <T> java.lang.Object execute(r7.p<? super io.ktor.client.statement.HttpResponse, ? super d7.d<? super T>, ? extends java.lang.Object> r10, d7.d<? super T> r11) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r11 instanceof io.ktor.client.statement.HttpStatement.C18081
            if (r0 == 0) goto L13
            r0 = r11
            io.ktor.client.statement.HttpStatement$execute$1 r0 = (io.ktor.client.statement.HttpStatement.C18081) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.statement.HttpStatement$execute$1 r0 = new io.ktor.client.statement.HttpStatement$execute$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            e7.a r7 = e7.a.f15033i
            if (r1 == 0) goto L64
            if (r1 == r6) goto L58
            if (r1 == r5) goto L47
            if (r1 == r4) goto L41
            if (r1 == r3) goto L35
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L35:
            java.lang.Object r10 = r0.L$0
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            k2.c.G(r11)     // Catch: java.util.concurrent.CancellationException -> L3e
            goto La3
        L3e:
            r10 = move-exception
            goto La4
        L41:
            java.lang.Object r10 = r0.L$0
            k2.c.G(r11)     // Catch: java.util.concurrent.CancellationException -> L3e
            return r10
        L47:
            java.lang.Object r10 = r0.L$1
            io.ktor.client.statement.HttpResponse r10 = (io.ktor.client.statement.HttpResponse) r10
            java.lang.Object r1 = r0.L$0
            io.ktor.client.statement.HttpStatement r1 = (io.ktor.client.statement.HttpStatement) r1
            k2.c.G(r11)     // Catch: java.lang.Throwable -> L53
            goto L87
        L53:
            r11 = move-exception
            r8 = r11
            r11 = r10
            r10 = r8
            goto L96
        L58:
            java.lang.Object r10 = r0.L$1
            r7.p r10 = (r7.p) r10
            java.lang.Object r1 = r0.L$0
            io.ktor.client.statement.HttpStatement r1 = (io.ktor.client.statement.HttpStatement) r1
            k2.c.G(r11)     // Catch: java.util.concurrent.CancellationException -> L3e
            goto L75
        L64:
            k2.c.G(r11)
            r0.L$0 = r9     // Catch: java.util.concurrent.CancellationException -> L3e
            r0.L$1 = r10     // Catch: java.util.concurrent.CancellationException -> L3e
            r0.label = r6     // Catch: java.util.concurrent.CancellationException -> L3e
            java.lang.Object r11 = r9.executeUnsafe(r0)     // Catch: java.util.concurrent.CancellationException -> L3e
            if (r11 != r7) goto L74
            goto La2
        L74:
            r1 = r9
        L75:
            io.ktor.client.statement.HttpResponse r11 = (io.ktor.client.statement.HttpResponse) r11     // Catch: java.util.concurrent.CancellationException -> L3e
            r0.L$0 = r1     // Catch: java.lang.Throwable -> L95
            r0.L$1 = r11     // Catch: java.lang.Throwable -> L95
            r0.label = r5     // Catch: java.lang.Throwable -> L95
            java.lang.Object r10 = r10.invoke(r11, r0)     // Catch: java.lang.Throwable -> L95
            if (r10 != r7) goto L84
            goto La2
        L84:
            r8 = r11
            r11 = r10
            r10 = r8
        L87:
            r0.L$0 = r11     // Catch: java.util.concurrent.CancellationException -> L3e
            r0.L$1 = r2     // Catch: java.util.concurrent.CancellationException -> L3e
            r0.label = r4     // Catch: java.util.concurrent.CancellationException -> L3e
            java.lang.Object r10 = r1.cleanup(r10, r0)     // Catch: java.util.concurrent.CancellationException -> L3e
            if (r10 != r7) goto L94
            goto La2
        L94:
            return r11
        L95:
            r10 = move-exception
        L96:
            r0.L$0 = r10     // Catch: java.util.concurrent.CancellationException -> L3e
            r0.L$1 = r2     // Catch: java.util.concurrent.CancellationException -> L3e
            r0.label = r3     // Catch: java.util.concurrent.CancellationException -> L3e
            java.lang.Object r11 = r1.cleanup(r11, r0)     // Catch: java.util.concurrent.CancellationException -> L3e
            if (r11 != r7) goto La3
        La2:
            return r7
        La3:
            throw r10     // Catch: java.util.concurrent.CancellationException -> L3e
        La4:
            java.lang.Throwable r10 = io.ktor.client.utils.ExceptionUtilsJvmKt.unwrapCancellationException(r10)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpStatement.execute(r7.p, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object executeUnsafe(d7.d<? super io.ktor.client.statement.HttpResponse> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof io.ktor.client.statement.HttpStatement.C18091
            if (r0 == 0) goto L13
            r0 = r5
            io.ktor.client.statement.HttpStatement$executeUnsafe$1 r0 = (io.ktor.client.statement.HttpStatement.C18091) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            io.ktor.client.statement.HttpStatement$executeUnsafe$1 r0 = new io.ktor.client.statement.HttpStatement$executeUnsafe$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2f
            if (r1 != r2) goto L27
            k2.c.G(r5)     // Catch: java.util.concurrent.CancellationException -> L25
            goto L4a
        L25:
            r5 = move-exception
            goto L51
        L27:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2f:
            k2.c.G(r5)
            io.ktor.client.request.HttpRequestBuilder r5 = new io.ktor.client.request.HttpRequestBuilder     // Catch: java.util.concurrent.CancellationException -> L25
            r5.<init>()     // Catch: java.util.concurrent.CancellationException -> L25
            io.ktor.client.request.HttpRequestBuilder r1 = r4.builder     // Catch: java.util.concurrent.CancellationException -> L25
            io.ktor.client.request.HttpRequestBuilder r5 = r5.takeFromWithExecutionContext(r1)     // Catch: java.util.concurrent.CancellationException -> L25
            io.ktor.client.HttpClient r1 = r4.client     // Catch: java.util.concurrent.CancellationException -> L25
            r0.label = r2     // Catch: java.util.concurrent.CancellationException -> L25
            java.lang.Object r5 = r1.execute$ktor_client_core(r5, r0)     // Catch: java.util.concurrent.CancellationException -> L25
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L4a
            return r0
        L4a:
            io.ktor.client.call.HttpClientCall r5 = (io.ktor.client.call.HttpClientCall) r5     // Catch: java.util.concurrent.CancellationException -> L25
            io.ktor.client.statement.HttpResponse r5 = r5.getResponse()     // Catch: java.util.concurrent.CancellationException -> L25
            return r5
        L51:
            java.lang.Throwable r5 = io.ktor.client.utils.ExceptionUtilsJvmKt.unwrapCancellationException(r5)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.statement.HttpStatement.executeUnsafe(d7.d):java.lang.Object");
    }

    public final HttpClient getClient() {
        return this.client;
    }

    public String toString() {
        return "HttpStatement[" + this.builder.getUrl() + ']';
    }

    public final <T, R> Object body(p<? super T, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar) throws Throwable {
        try {
            HttpResponse httpResponse = (HttpResponse) executeUnsafe(null);
            try {
                httpResponse.getCall();
                kotlin.jvm.internal.p.h();
                throw null;
            } catch (Throwable th) {
                cleanup(httpResponse, null);
                throw th;
            }
        } catch (CancellationException e5) {
            throw ExceptionUtilsJvmKt.unwrapCancellationException(e5);
        }
    }

    public final Object execute(d<? super HttpResponse> dVar) {
        return execute(new AnonymousClass4(null), dVar);
    }
}
