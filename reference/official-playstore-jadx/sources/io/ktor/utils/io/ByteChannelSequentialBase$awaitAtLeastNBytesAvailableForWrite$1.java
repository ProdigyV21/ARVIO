package io.ktor.utils.io;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", l = {81}, m = "awaitAtLeastNBytesAvailableForWrite$ktor_io")
public final class ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1 extends c {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1(ByteChannelSequentialBase byteChannelSequentialBase, d<? super ByteChannelSequentialBase$awaitAtLeastNBytesAvailableForWrite$1> dVar) {
        super(dVar);
        this.this$0 = byteChannelSequentialBase;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.awaitAtLeastNBytesAvailableForWrite$ktor_io(0, this);
    }
}
