package ma;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u0002¨\u0006\u0003"}, d2 = {"Lma/x;", "E", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface x<E> {
    boolean close(Throwable th);

    void invokeOnClose(r7.l lVar);

    boolean isClosedForSend();

    Object send(Object obj, d7.d dVar);

    /* JADX INFO: renamed from: trySend-JP2dKIU */
    Object mo6685trySendJP2dKIU(Object obj);
}
