package io.ktor.utils.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H&¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/utils/io/LookAheadSession;", "", "", "n", "Lx6/t0;", "consumed", "(I)V", "skip", "atLeast", "Ljava/nio/ByteBuffer;", "request", "(II)Ljava/nio/ByteBuffer;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public interface LookAheadSession {
    /* JADX INFO: renamed from: consumed */
    void mo7011consumed(int n6);

    ByteBuffer request(int skip, int atLeast);
}
