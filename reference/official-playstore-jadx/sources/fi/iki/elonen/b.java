package fi.iki.elonen;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final InputStream f15402i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Socket f15403l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ t f15404m;

    public b(t tVar, InputStream inputStream, Socket socket) {
        this.f15404m = tVar;
        this.f15402i = inputStream;
        this.f15403l = socket;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        OutputStream outputStream;
        InputStream inputStream = this.f15402i;
        t tVar = this.f15404m;
        Socket socket = this.f15403l;
        OutputStream outputStream2 = null;
        try {
            try {
                outputStream = socket.getOutputStream();
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            g gVar = new g(tVar, tVar.tempFileManagerFactory.a(), this.f15402i, outputStream, socket.getInetAddress());
            while (!socket.isClosed()) {
                gVar.e();
            }
            t.b(outputStream);
        } catch (Exception e6) {
            e = e6;
            outputStream2 = outputStream;
            if ((!(e instanceof SocketException) || !"NanoHttpd Shutdown".equals(e.getMessage())) && !(e instanceof SocketTimeoutException)) {
                t.LOG.log(Level.SEVERE, "Communication with the client broken, or an bug in the handler code", (Throwable) e);
            }
            t.b(outputStream2);
        } catch (Throwable th2) {
            th = th2;
            outputStream2 = outputStream;
            t.b(outputStream2);
            t.b(inputStream);
            t.b(socket);
            tVar.asyncRunner.a(this);
            throw th;
        }
        t.b(inputStream);
        t.b(socket);
        tVar.asyncRunner.a(this);
    }
}
