package fi.iki.elonen;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes4.dex */
public final class o implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f15454i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public IOException f15455l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f15456m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ t f15457n;

    public o(t tVar, int i10) {
        this.f15457n = tVar;
        this.f15454i = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        t tVar = this.f15457n;
        try {
            tVar.myServerSocket.bind(tVar.hostname != null ? new InetSocketAddress(tVar.hostname, tVar.myPort) : new InetSocketAddress(tVar.myPort));
            this.f15456m = true;
            do {
                try {
                    Socket socketAccept = tVar.myServerSocket.accept();
                    int i10 = this.f15454i;
                    if (i10 > 0) {
                        socketAccept.setSoTimeout(i10);
                    }
                    tVar.asyncRunner.c(tVar.createClientHandler(socketAccept, socketAccept.getInputStream()));
                } catch (IOException e5) {
                    t.LOG.log(Level.FINE, "Communication with the client broken", (Throwable) e5);
                }
            } while (!tVar.myServerSocket.isClosed());
        } catch (IOException e6) {
            this.f15455l = e6;
        }
    }
}
