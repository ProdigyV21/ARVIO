package io.ktor.utils.io;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003H&J\u0014\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u0003H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000b"}, d2 = {"Lio/ktor/utils/io/ReadSession;", "", "availableForRead", "", "getAvailableForRead", "()I", "discard", "n", "request", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "atLeast", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public interface ReadSession {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ ChunkBuffer request$default(ReadSession readSession, int i10, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: request");
            }
            if ((i11 & 1) != 0) {
                i10 = 1;
            }
            return readSession.request(i10);
        }
    }

    int discard(int n6);

    int getAvailableForRead();

    ChunkBuffer request(int atLeast);
}
