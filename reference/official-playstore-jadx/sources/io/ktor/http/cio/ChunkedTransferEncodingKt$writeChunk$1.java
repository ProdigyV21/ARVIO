package io.ktor.http.cio;

import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.http.cio.ChunkedTransferEncodingKt", f = "ChunkedTransferEncoding.kt", l = {167, 168, 170, 171}, m = "writeChunk-yRinSxo")
public final class ChunkedTransferEncodingKt$writeChunk$1 extends c {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    public ChunkedTransferEncodingKt$writeChunk$1(d<? super ChunkedTransferEncodingKt$writeChunk$1> dVar) {
        super(dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChunkedTransferEncodingKt.m6681writeChunkyRinSxo(null, null, 0, 0, this);
    }
}
