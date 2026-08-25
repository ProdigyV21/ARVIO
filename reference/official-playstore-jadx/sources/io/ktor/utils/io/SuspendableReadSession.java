package io.ktor.utils.io;

import d7.d;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u001d\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u0002H¦@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"Lio/ktor/utils/io/SuspendableReadSession;", "Lio/ktor/utils/io/ReadSession;", "", "atLeast", "", "await", "(ILd7/d;)Ljava/lang/Object;", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public interface SuspendableReadSession extends ReadSession {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object await$default(SuspendableReadSession suspendableReadSession, int i10, d dVar, int i11, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: await");
            }
            if ((i11 & 1) != 0) {
                i10 = 1;
            }
            return suspendableReadSession.await(i10, dVar);
        }
    }

    Object await(int i10, d<? super Boolean> dVar);
}
