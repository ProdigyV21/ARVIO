package io.ktor.utils.io;

import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import d7.d;
import f7.c;
import f7.e;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@e(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", l = {1200, 1271, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD}, m = "copyDirect$ktor_io")
public final class ByteBufferChannel$copyDirect$1 extends c {
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ByteBufferChannel$copyDirect$1(ByteBufferChannel byteBufferChannel, d<? super ByteBufferChannel$copyDirect$1> dVar) {
        super(dVar);
        this.this$0 = byteBufferChannel;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.copyDirect$ktor_io(null, 0L, null, this);
    }
}
