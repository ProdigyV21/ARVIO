package io.ktor.utils.io;

import io.ktor.utils.io.core.internal.ChunkBuffer;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0002H&¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH&¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lio/ktor/utils/io/WriterSession;", "", "", "min", "Lio/ktor/utils/io/core/internal/ChunkBuffer;", "request", "(I)Lio/ktor/utils/io/core/internal/ChunkBuffer;", "n", "Lx6/t0;", "written", "(I)V", "flush", "()V", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public interface WriterSession {
    void flush();

    ChunkBuffer request(int min);

    void written(int n6);
}
