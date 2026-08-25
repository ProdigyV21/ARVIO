package io.ktor.utils.io.core;

import a0.c;
import androidx.compose.material3.d;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.io.EOFException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0005\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0019\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0019\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0004\u001a\u00020\b¢\u0006\u0004\b\u0006\u0010\t\u001a+\u0010\u000e\u001a\u00020\u0005*\u00020\u00002\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a5\u0010\u0011\u001a\u00020\u0005*\u00020\u00002\b\b\u0002\u0010\u0010\u001a\u00020\b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\nH\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0011\u0010\u0014\u001a\u00020\u0013*\u00020\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a+\u0010\u0017\u001a\u00020\u0005*\u00020\u00002\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00050\nH\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u000f\u001a\u001b\u0010\u0019\u001a\u00020\u0013*\u00020\u00002\u0006\u0010\u0018\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001b"}, d2 = {"Lio/ktor/utils/io/core/Input;", "", "discard", "(Lio/ktor/utils/io/core/Input;)J", "n", "Lx6/t0;", "discardExact", "(Lio/ktor/utils/io/core/Input;J)V", "", "(Lio/ktor/utils/io/core/Input;I)V", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/Buffer;", "", "block", "takeWhile", "(Lio/ktor/utils/io/core/Input;Lr7/l;)V", "initialSize", "takeWhileSize", "(Lio/ktor/utils/io/core/Input;ILr7/l;)V", "", "peekCharUtf8", "(Lio/ktor/utils/io/core/Input;)C", "", "forEach", "first", "peekCharUtf8Impl", "(Lio/ktor/utils/io/core/Input;I)C", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InputKt {
    public static final long discard(Input input) {
        return input.discard(Long.MAX_VALUE);
    }

    public static final void discardExact(Input input, long j10) {
        long jDiscard = input.discard(j10);
        if (jDiscard != j10) {
            throw new IllegalStateException(c.j(j10, " requested", d.r(jDiscard, "Only ", " bytes were discarded of ")));
        }
    }

    public static final void forEach(Input input, l<? super Byte, t0> lVar) throws Throwable {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            return;
        }
        do {
            try {
                ByteBuffer memory = chunkBufferPrepareReadFirstHead.getMemory();
                int readPosition = chunkBufferPrepareReadFirstHead.getReadPosition();
                int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition();
                for (int i10 = readPosition; i10 < writePosition; i10++) {
                    lVar.invoke(Byte.valueOf(memory.get(i10)));
                }
                chunkBufferPrepareReadFirstHead.discardExact(writePosition - readPosition);
                try {
                    chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                } catch (Throwable th) {
                    th = th;
                    z = false;
                    if (z) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } while (chunkBufferPrepareReadFirstHead != null);
    }

    public static final char peekCharUtf8(Input input) throws EOFException {
        int iTryPeek = input.tryPeek();
        if ((iTryPeek & 128) == 0) {
            return (char) iTryPeek;
        }
        if (iTryPeek != -1) {
            return peekCharUtf8Impl(input, iTryPeek);
        }
        throw new EOFException("Failed to peek a char: end of input");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0043, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Finally extract failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final char peekCharUtf8Impl(io.ktor.utils.io.core.Input r17, int r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputKt.peekCharUtf8Impl(io.ktor.utils.io.core.Input, int):char");
    }

    public static final void takeWhile(Input input, l<? super Buffer, Boolean> lVar) throws Throwable {
        boolean z = true;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, 1);
        if (chunkBufferPrepareReadFirstHead == null) {
            return;
        }
        while (true) {
            try {
                if (!((Boolean) lVar.invoke(chunkBufferPrepareReadFirstHead)).booleanValue()) {
                    break;
                }
                try {
                    ChunkBuffer chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                    if (chunkBufferPrepareReadNextHead == null) {
                        z = false;
                        break;
                    }
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                } catch (Throwable th) {
                    th = th;
                    z = false;
                    if (z) {
                        UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        if (z) {
            UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        }
    }

    public static final void takeWhileSize(Input input, int i10, l<? super Buffer, Integer> lVar) throws Throwable {
        boolean z;
        ChunkBuffer chunkBufferPrepareReadNextHead;
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, i10);
        if (chunkBufferPrepareReadFirstHead == null) {
            return;
        }
        while (true) {
            z = true;
            try {
                int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                if (writePosition >= i10) {
                    try {
                        i10 = ((Number) lVar.invoke(chunkBufferPrepareReadFirstHead)).intValue();
                        writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    } catch (Throwable th) {
                        chunkBufferPrepareReadFirstHead.getWritePosition();
                        chunkBufferPrepareReadFirstHead.getReadPosition();
                        throw th;
                    }
                }
                if (writePosition == 0) {
                    try {
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                    } catch (Throwable th2) {
                        th = th2;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        }
                        throw th;
                    }
                } else if (writePosition < i10 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                    UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i10);
                } else {
                    chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                }
                if (chunkBufferPrepareReadNextHead == null) {
                    z = false;
                    break;
                } else {
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                    if (i10 <= 0) {
                        break;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        if (z) {
            UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        }
    }

    public static /* synthetic */ void takeWhileSize$default(Input input, int i10, l lVar, int i11, Object obj) throws Throwable {
        ChunkBuffer chunkBufferPrepareReadNextHead;
        boolean z = true;
        if ((i11 & 1) != 0) {
            i10 = 1;
        }
        ChunkBuffer chunkBufferPrepareReadFirstHead = UnsafeKt.prepareReadFirstHead(input, i10);
        if (chunkBufferPrepareReadFirstHead == null) {
            return;
        }
        while (true) {
            try {
                int writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                if (writePosition >= i10) {
                    try {
                        i10 = ((Number) lVar.invoke(chunkBufferPrepareReadFirstHead)).intValue();
                        writePosition = chunkBufferPrepareReadFirstHead.getWritePosition() - chunkBufferPrepareReadFirstHead.getReadPosition();
                    } catch (Throwable th) {
                        chunkBufferPrepareReadFirstHead.getWritePosition();
                        chunkBufferPrepareReadFirstHead.getReadPosition();
                        throw th;
                    }
                }
                if (writePosition == 0) {
                    try {
                        chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadNextHead(input, chunkBufferPrepareReadFirstHead);
                    } catch (Throwable th2) {
                        th = th2;
                        z = false;
                        if (z) {
                            UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                        }
                        throw th;
                    }
                } else if (writePosition < i10 || chunkBufferPrepareReadFirstHead.getCapacity() - chunkBufferPrepareReadFirstHead.getLimit() < 8) {
                    UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
                    chunkBufferPrepareReadNextHead = UnsafeKt.prepareReadFirstHead(input, i10);
                } else {
                    chunkBufferPrepareReadNextHead = chunkBufferPrepareReadFirstHead;
                }
                if (chunkBufferPrepareReadNextHead == null) {
                    z = false;
                    break;
                } else {
                    chunkBufferPrepareReadFirstHead = chunkBufferPrepareReadNextHead;
                    if (i10 <= 0) {
                        break;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        if (z) {
            UnsafeKt.completeReadHead(input, chunkBufferPrepareReadFirstHead);
        }
    }

    public static final void discardExact(Input input, int i10) {
        discardExact(input, i10);
    }
}
