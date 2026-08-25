package io.ktor.utils.io.bits;

import io.ktor.http.ContentDisposition;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import r7.l;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\u001aE\u0010\u0006\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003H\u0087\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u0006\u0010\u0007\u001aE\u0010\u0006\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\b2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003H\u0087\bø\u0001\u0000ø\u0001\u0001\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0004\b\u0006\u0010\t\u0082\u0002\u000b\n\u0005\b\u009920\u0001\n\u0002\b\u0019¨\u0006\n"}, d2 = {"R", "", ContentDisposition.Parameters.Size, "Lkotlin/Function1;", "Lio/ktor/utils/io/bits/Memory;", "block", "withMemory", "(ILr7/l;)Ljava/lang/Object;", "", "(JLr7/l;)Ljava/lang/Object;", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MemoryFactoryKt {
    @e
    public static final <R> R withMemory(long j10, l<? super Memory, ? extends R> lVar) {
        DefaultAllocator defaultAllocator = DefaultAllocator.INSTANCE;
        ByteBuffer byteBufferMo6740allocgFvZug = defaultAllocator.mo6740allocgFvZug(j10);
        try {
            return (R) lVar.invoke(Memory.m6745boximpl(byteBufferMo6740allocgFvZug));
        } finally {
            defaultAllocator.mo6741free3GNKZMM(byteBufferMo6740allocgFvZug);
        }
    }

    @e
    public static final <R> R withMemory(int i10, l<? super Memory, ? extends R> lVar) {
        long j10 = i10;
        DefaultAllocator defaultAllocator = DefaultAllocator.INSTANCE;
        ByteBuffer byteBufferMo6740allocgFvZug = defaultAllocator.mo6740allocgFvZug(j10);
        try {
            return (R) lVar.invoke(Memory.m6745boximpl(byteBufferMo6740allocgFvZug));
        } finally {
            defaultAllocator.mo6741free3GNKZMM(byteBufferMo6740allocgFvZug);
        }
    }
}
