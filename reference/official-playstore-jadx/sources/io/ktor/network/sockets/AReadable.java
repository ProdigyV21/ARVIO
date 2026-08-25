package io.ktor.network.sockets;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.WriterJob;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lio/ktor/network/sockets/AReadable;", "", "attachForReading", "Lio/ktor/utils/io/WriterJob;", "channel", "Lio/ktor/utils/io/ByteChannel;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface AReadable {
    WriterJob attachForReading(ByteChannel channel);
}
