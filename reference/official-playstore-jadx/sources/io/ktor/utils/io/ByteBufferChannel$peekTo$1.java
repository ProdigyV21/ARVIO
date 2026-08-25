package io.ktor.utils.io;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {2374}, m = "peekTo-lBXzO7A$suspendImpl")
public final class ByteBufferChannel$peekTo$1 extends c {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$peekTo$1(ByteBufferChannel byteBufferChannel, d<? super ByteBufferChannel$peekTo$1> dVar) {
        super(dVar);
        this.this$0 = byteBufferChannel;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteBufferChannel.m6733peekTolBXzO7A$suspendImpl(this.this$0, null, 0L, 0L, 0L, 0L, this);
    }
}
