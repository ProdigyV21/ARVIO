package xb;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public final class n0 extends e {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Socket f22776m;

    public n0(Socket socket) {
        this.f22776m = socket;
    }

    @Override // xb.e
    public final void k() {
        Socket socket = this.f22776m;
        try {
            socket.close();
        } catch (AssertionError e5) {
            Logger logger = d0.f22723a;
            boolean z = false;
            if (e5.getCause() != null) {
                String message = e5.getMessage();
                if (message != null ? kotlin.text.o.T(message, "getsockname failed", false) : false) {
                    z = true;
                }
            }
            if (!z) {
                throw e5;
            }
            d0.f22723a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e5);
        } catch (Exception e6) {
            d0.f22723a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e6);
        }
    }

    public final IOException l(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }
}
