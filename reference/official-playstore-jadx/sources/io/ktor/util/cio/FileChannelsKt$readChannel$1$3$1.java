package io.ktor.util.cio;

import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.util.BufferViewJvmKt;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.WriterSuspendSession;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import java.io.IOException;
import java.nio.channels.FileChannel;
import k2.c;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterSuspendSession;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterSuspendSession;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.util.cio.FileChannelsKt$readChannel$1$3$1", f = "FileChannels.kt", l = {49}, m = "invokeSuspend")
public final class FileChannelsKt$readChannel$1$3$1 extends j implements p<WriterSuspendSession, d<? super t0>, Object> {
    final /* synthetic */ WriterScope $$this$writer;
    final /* synthetic */ FileChannel $fileChannel;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileChannelsKt$readChannel$1$3$1(WriterScope writerScope, FileChannel fileChannel, d<? super FileChannelsKt$readChannel$1$3$1> dVar) {
        super(2, dVar);
        this.$$this$writer = writerScope;
        this.$fileChannel = fileChannel;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        FileChannelsKt$readChannel$1$3$1 fileChannelsKt$readChannel$1$3$1 = new FileChannelsKt$readChannel$1$3$1(this.$$this$writer, this.$fileChannel, dVar);
        fileChannelsKt$readChannel$1$3$1.L$0 = obj;
        return fileChannelsKt$readChannel$1$3$1;
    }

    @Override // r7.p
    public final Object invoke(WriterSuspendSession writerSuspendSession, d<? super t0> dVar) {
        return ((FileChannelsKt$readChannel$1$3$1) create(writerSuspendSession, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws IOException {
        WriterSuspendSession writerSuspendSession;
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            writerSuspendSession = (WriterSuspendSession) this.L$0;
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            writerSuspendSession = (WriterSuspendSession) this.L$0;
            c.G(obj);
        }
        while (true) {
            ChunkBuffer chunkBufferRequest = writerSuspendSession.request(1);
            if (chunkBufferRequest == null) {
                this.$$this$writer.getChannel().flush();
                this.L$0 = writerSuspendSession;
                this.label = 1;
                Object objTryAwait = writerSuspendSession.tryAwait(1, this);
                a aVar = a.f15033i;
                if (objTryAwait == aVar) {
                    return aVar;
                }
            } else {
                int i11 = BufferViewJvmKt.read(this.$fileChannel, chunkBufferRequest);
                if (i11 == -1) {
                    return t0.f22605a;
                }
                writerSuspendSession.written(i11);
            }
        }
    }
}
