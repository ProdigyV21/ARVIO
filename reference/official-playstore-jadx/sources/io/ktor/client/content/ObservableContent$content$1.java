package io.ktor.client.content;

import androidx.media3.extractor.ts.TsExtractor;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.http.content.OutgoingContent;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.WriterScope;
import k2.c;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "io.ktor.client.content.ObservableContent$content$1", f = "ObservableContent.kt", l = {TsExtractor.TS_STREAM_TYPE_H265}, m = "invokeSuspend")
public final class ObservableContent$content$1 extends j implements p<WriterScope, d<? super t0>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ObservableContent this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ObservableContent$content$1(ObservableContent observableContent, d<? super ObservableContent$content$1> dVar) {
        super(2, dVar);
        this.this$0 = observableContent;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        ObservableContent$content$1 observableContent$content$1 = new ObservableContent$content$1(this.this$0, dVar);
        observableContent$content$1.L$0 = obj;
        return observableContent$content$1;
    }

    @Override // r7.p
    public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
        return ((ObservableContent$content$1) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            WriterScope writerScope = (WriterScope) this.L$0;
            OutgoingContent.WriteChannelContent writeChannelContent = (OutgoingContent.WriteChannelContent) this.this$0.delegate;
            ByteWriteChannel channel = writerScope.getChannel();
            this.label = 1;
            Object objWriteTo = writeChannelContent.writeTo(channel, this);
            a aVar = a.f15033i;
            if (objWriteTo == aVar) {
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
}
