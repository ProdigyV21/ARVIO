package io.ktor.utils.io;

import d7.j;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Lio/ktor/utils/io/ReaderScope;", "Lka/k0;", "Lio/ktor/utils/io/ByteReadChannel;", "getChannel", "()Lio/ktor/utils/io/ByteReadChannel;", "channel", "ktor-io"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ReaderScope extends k0 {
    ByteReadChannel getChannel();

    @Override // ka.k0
    /* synthetic */ j getCoroutineContext();
}
