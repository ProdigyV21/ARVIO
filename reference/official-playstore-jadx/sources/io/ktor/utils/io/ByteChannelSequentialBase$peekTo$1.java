package io.ktor.utils.io;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {820}, m = "peekTo-lBXzO7A")
public final class ByteChannelSequentialBase$peekTo$1 extends c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$peekTo$1(ByteChannelSequentialBase byteChannelSequentialBase, d<? super ByteChannelSequentialBase$peekTo$1> dVar) {
        super(dVar);
        this.this$0 = byteChannelSequentialBase;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.mo6735peekTolBXzO7A(null, 0L, 0L, 0L, 0L, this);
    }
}
