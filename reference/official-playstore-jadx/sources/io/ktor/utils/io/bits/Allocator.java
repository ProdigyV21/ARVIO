package io.ktor.utils.io.bits;

import io.ktor.http.ContentDisposition;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J \u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0005\u0010\u0006J \u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\bH&ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0005\u0010\tJ\u001d\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0004H&ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/utils/io/bits/Allocator;", "", "", ContentDisposition.Parameters.Size, "Lio/ktor/utils/io/bits/Memory;", "alloc-gFv-Zug", "(I)Ljava/nio/ByteBuffer;", "alloc", "", "(J)Ljava/nio/ByteBuffer;", "instance", "Lx6/t0;", "free-3GNKZMM", "(Ljava/nio/ByteBuffer;)V", "free", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Allocator {
    /* JADX INFO: renamed from: alloc-gFv-Zug, reason: not valid java name */
    ByteBuffer mo6739allocgFvZug(int size);

    /* JADX INFO: renamed from: alloc-gFv-Zug, reason: not valid java name */
    ByteBuffer mo6740allocgFvZug(long size);

    /* JADX INFO: renamed from: free-3GNKZMM, reason: not valid java name */
    void mo6741free3GNKZMM(ByteBuffer instance);
}
