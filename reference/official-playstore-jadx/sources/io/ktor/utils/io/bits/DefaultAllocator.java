package io.ktor.utils.io.bits;

import g8.b;
import io.ktor.http.ContentDisposition;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0007\u0010\bJ \u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\nH\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0007\u0010\u000bJ\u001d\u0010\u0010\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0006H\u0016ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/utils/io/bits/DefaultAllocator;", "Lio/ktor/utils/io/bits/Allocator;", "<init>", "()V", "", ContentDisposition.Parameters.Size, "Lio/ktor/utils/io/bits/Memory;", "alloc-gFv-Zug", "(I)Ljava/nio/ByteBuffer;", "alloc", "", "(J)Ljava/nio/ByteBuffer;", "instance", "Lx6/t0;", "free-3GNKZMM", "(Ljava/nio/ByteBuffer;)V", "free", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DefaultAllocator implements Allocator {
    public static final DefaultAllocator INSTANCE = new DefaultAllocator();

    private DefaultAllocator() {
    }

    @Override // io.ktor.utils.io.bits.Allocator
    /* JADX INFO: renamed from: alloc-gFv-Zug */
    public ByteBuffer mo6739allocgFvZug(int size) {
        return Memory.m6746constructorimpl(ByteBuffer.allocate(size));
    }

    @Override // io.ktor.utils.io.bits.Allocator
    /* JADX INFO: renamed from: free-3GNKZMM */
    public void mo6741free3GNKZMM(ByteBuffer instance) {
    }

    @Override // io.ktor.utils.io.bits.Allocator
    /* JADX INFO: renamed from: alloc-gFv-Zug */
    public ByteBuffer mo6740allocgFvZug(long size) {
        if (size < 2147483647L) {
            return mo6739allocgFvZug((int) size);
        }
        throw b.j(size, ContentDisposition.Parameters.Size);
    }
}
