package io.ktor.utils.io.core;

import g8.b;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aB\u0010\u0007\u001a\u00020\u0005*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u0081\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"Lio/ktor/utils/io/core/Input;", "", "n", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Buffer;", "Lx6/t0;", "block", "read", "(Lio/ktor/utils/io/core/Input;ILr7/l;)V", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PacketDirectKt {
    public static final void read(Input input, int i10, l<? super Buffer, t0> lVar) {
        ChunkBuffer chunkBufferPrepareRead = input.prepareRead(i10);
        if (chunkBufferPrepareRead == null) {
            throw b.i(i10);
        }
        int readPosition = chunkBufferPrepareRead.getReadPosition();
        try {
            lVar.invoke(chunkBufferPrepareRead);
            int readPosition2 = chunkBufferPrepareRead.getReadPosition();
            if (readPosition2 < readPosition) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (readPosition2 == chunkBufferPrepareRead.getWritePosition()) {
                input.ensureNext(chunkBufferPrepareRead);
            } else {
                input.setHeadPosition(readPosition2);
            }
        } catch (Throwable th) {
            int readPosition3 = chunkBufferPrepareRead.getReadPosition();
            if (readPosition3 < readPosition) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (readPosition3 == chunkBufferPrepareRead.getWritePosition()) {
                input.ensureNext(chunkBufferPrepareRead);
            } else {
                input.setHeadPosition(readPosition3);
            }
            throw th;
        }
    }

    public static /* synthetic */ void read$default(Input input, int i10, l lVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 1;
        }
        ChunkBuffer chunkBufferPrepareRead = input.prepareRead(i10);
        if (chunkBufferPrepareRead == null) {
            throw b.i(i10);
        }
        int readPosition = chunkBufferPrepareRead.getReadPosition();
        try {
            lVar.invoke(chunkBufferPrepareRead);
            int readPosition2 = chunkBufferPrepareRead.getReadPosition();
            if (readPosition2 < readPosition) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (readPosition2 == chunkBufferPrepareRead.getWritePosition()) {
                input.ensureNext(chunkBufferPrepareRead);
            } else {
                input.setHeadPosition(readPosition2);
            }
        } catch (Throwable th) {
            int readPosition3 = chunkBufferPrepareRead.getReadPosition();
            if (readPosition3 < readPosition) {
                throw new IllegalStateException("Buffer's position shouldn't be rewinded");
            }
            if (readPosition3 == chunkBufferPrepareRead.getWritePosition()) {
                input.ensureNext(chunkBufferPrepareRead);
            } else {
                input.setHeadPosition(readPosition3);
            }
            throw th;
        }
    }
}
