package io.ktor.network.sockets;

import java.io.Closeable;
import ka.v1;
import ka.z0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lio/ktor/network/sockets/ASocket;", "Ljava/io/Closeable;", "Lio/ktor/utils/io/core/Closeable;", "Lka/z0;", "Lx6/t0;", "dispose", "()V", "Lka/v1;", "getSocketContext", "()Lka/v1;", "socketContext", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ASocket extends Closeable, z0 {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void dispose(ASocket aSocket) {
            try {
                aSocket.close();
            } catch (Throwable unused) {
            }
        }
    }

    @Override // ka.z0
    void dispose();

    v1 getSocketContext();
}
