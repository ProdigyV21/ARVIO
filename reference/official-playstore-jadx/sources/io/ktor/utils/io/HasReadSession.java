package io.ktor.utils.io;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H&¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/utils/io/HasReadSession;", "", "Lio/ktor/utils/io/SuspendableReadSession;", "startReadSession", "()Lio/ktor/utils/io/SuspendableReadSession;", "Lx6/t0;", "endReadSession", "()V", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface HasReadSession {
    void endReadSession();

    SuspendableReadSession startReadSession();
}
