package io.ktor.client.plugins;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteReadChannelJVMKt;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.WriterScope;
import java.util.concurrent.CancellationException;
import k2.c;
import ka.l0;
import ka.m0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.plugins.DefaultTransformKt$defaultTransformers$2$result$channel$1", f = "DefaultTransform.kt", l = {100}, m = "invokeSuspend")
public final class DefaultTransformKt$defaultTransformers$2$result$channel$1 extends j implements p<WriterScope, d<? super t0>, Object> {
    final /* synthetic */ Object $body;
    final /* synthetic */ HttpResponse $response;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultTransformKt$defaultTransformers$2$result$channel$1(Object obj, HttpResponse httpResponse, d<? super DefaultTransformKt$defaultTransformers$2$result$channel$1> dVar) {
        super(2, dVar);
        this.$body = obj;
        this.$response = httpResponse;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        DefaultTransformKt$defaultTransformers$2$result$channel$1 defaultTransformKt$defaultTransformers$2$result$channel$1 = new DefaultTransformKt$defaultTransformers$2$result$channel$1(this.$body, this.$response, dVar);
        defaultTransformKt$defaultTransformers$2$result$channel$1.L$0 = obj;
        return defaultTransformKt$defaultTransformers$2$result$channel$1;
    }

    @Override // r7.p
    public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
        return ((DefaultTransformKt$defaultTransformers$2$result$channel$1) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        try {
            if (i10 != 0) {
                try {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                } catch (Throwable th) {
                    HttpResponseKt.complete(this.$response);
                    throw th;
                }
            } else {
                c.G(obj);
                WriterScope writerScope = (WriterScope) this.L$0;
                ByteReadChannel byteReadChannel = (ByteReadChannel) this.$body;
                ByteWriteChannel channel = writerScope.getChannel();
                this.label = 1;
                Object objCopyTo = ByteReadChannelJVMKt.copyTo(byteReadChannel, channel, Long.MAX_VALUE, this);
                a aVar = a.f15033i;
                if (objCopyTo == aVar) {
                    return aVar;
                }
            }
            HttpResponseKt.complete(this.$response);
            return t0.f22605a;
        } catch (CancellationException e5) {
            l0.b(this.$response, e5);
            throw e5;
        } catch (Throwable th2) {
            l0.b(this.$response, m0.a("Receive failed", th2));
            throw th2;
        }
    }
}
