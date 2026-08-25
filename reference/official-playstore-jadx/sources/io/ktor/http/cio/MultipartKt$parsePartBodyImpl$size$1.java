package io.ktor.http.cio;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteWriteChannel;
import java.nio.ByteBuffer;
import k2.c;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Ljava/nio/ByteBuffer;", "it", "Lx6/t0;", "<anonymous>", "(Ljava/nio/ByteBuffer;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.http.cio.MultipartKt$parsePartBodyImpl$size$1", f = "Multipart.kt", l = {177}, m = "invokeSuspend")
public final class MultipartKt$parsePartBodyImpl$size$1 extends j implements p<ByteBuffer, d<? super t0>, Object> {
    final /* synthetic */ ByteWriteChannel $output;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipartKt$parsePartBodyImpl$size$1(ByteWriteChannel byteWriteChannel, d<? super MultipartKt$parsePartBodyImpl$size$1> dVar) {
        super(2, dVar);
        this.$output = byteWriteChannel;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        MultipartKt$parsePartBodyImpl$size$1 multipartKt$parsePartBodyImpl$size$1 = new MultipartKt$parsePartBodyImpl$size$1(this.$output, dVar);
        multipartKt$parsePartBodyImpl$size$1.L$0 = obj;
        return multipartKt$parsePartBodyImpl$size$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            ByteBuffer byteBuffer = (ByteBuffer) this.L$0;
            ByteWriteChannel byteWriteChannel = this.$output;
            this.label = 1;
            Object objWriteFully = byteWriteChannel.writeFully(byteBuffer, this);
            a aVar = a.f15033i;
            if (objWriteFully == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ByteBuffer byteBuffer, d<? super t0> dVar) {
        return ((MultipartKt$parsePartBodyImpl$size$1) create(byteBuffer, dVar)).invokeSuspend(t0.f22605a);
    }
}
