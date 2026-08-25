package fi.iki.elonen;

import j$.util.DesugarTimeZone;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.zip.GZIPOutputStream;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes4.dex */
public final class n implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l f15444i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f15445l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final InputStream f15446m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f15447n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final j f15448o = new j(this);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final HashMap f15449p = new HashMap();

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public i f15450q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f15451r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f15452s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f15453t;

    public n(l lVar, String str, InputStream inputStream, long j10) {
        this.f15444i = lVar;
        this.f15445l = str;
        if (inputStream == null) {
            this.f15446m = new ByteArrayInputStream(new byte[0]);
            this.f15447n = 0L;
        } else {
            this.f15446m = inputStream;
            this.f15447n = j10;
        }
        this.f15451r = this.f15447n < 0;
        this.f15453t = true;
    }

    public static void k(PrintWriter printWriter, String str, String str2) {
        printWriter.append((CharSequence) str).append(": ").append((CharSequence) str2).append("\r\n");
    }

    public final void G(boolean z) {
        this.f15453t = z;
    }

    public final void O(i iVar) {
        this.f15450q = iVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        InputStream inputStream = this.f15446m;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public final String i(String str) {
        return (String) this.f15449p.get(str.toLowerCase());
    }

    public final boolean j() {
        return "close".equals(i("connection"));
    }

    public final void l(OutputStream outputStream) {
        String str = this.f15445l;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("GMT"));
        l lVar = this.f15444i;
        try {
            if (lVar == null) {
                throw new Error("sendResponse(): Status can't be null.");
            }
            PrintWriter printWriter = new PrintWriter((Writer) new BufferedWriter(new OutputStreamWriter(outputStream, new c(str).a())), false);
            printWriter.append("HTTP/1.1 ").append(lVar.a()).append(" \r\n");
            if (str != null) {
                k(printWriter, HttpConnection.CONTENT_TYPE, str);
            }
            if (i("date") == null) {
                k(printWriter, "Date", simpleDateFormat.format(new Date()));
            }
            for (Map.Entry entry : this.f15448o.entrySet()) {
                k(printWriter, (String) entry.getKey(), (String) entry.getValue());
            }
            if (i("connection") == null) {
                k(printWriter, "Connection", this.f15453t ? "keep-alive" : "close");
            }
            if (i("content-length") != null) {
                this.f15452s = false;
            }
            if (this.f15452s) {
                k(printWriter, HttpConnection.CONTENT_ENCODING, "gzip");
                this.f15451r = true;
            }
            InputStream inputStream = this.f15446m;
            long jX = inputStream != null ? this.f15447n : 0L;
            i iVar = this.f15450q;
            i iVar2 = i.f15433n;
            if (iVar != iVar2 && this.f15451r) {
                k(printWriter, "Transfer-Encoding", "chunked");
            } else if (!this.f15452s) {
                jX = x(jX, printWriter);
            }
            printWriter.append("\r\n");
            printWriter.flush();
            if (this.f15450q != iVar2 && this.f15451r) {
                k kVar = new k(outputStream);
                if (this.f15452s) {
                    GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(kVar);
                    o(gZIPOutputStream, -1L);
                    gZIPOutputStream.finish();
                } else {
                    o(kVar, -1L);
                }
                kVar.i();
            } else if (this.f15452s) {
                GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(outputStream);
                o(gZIPOutputStream2, -1L);
                gZIPOutputStream2.finish();
            } else {
                o(outputStream, jX);
            }
            outputStream.flush();
            t.b(inputStream);
        } catch (IOException e5) {
            t.LOG.log(Level.SEVERE, "Could not send response to the client", (Throwable) e5);
        }
    }

    public final void o(OutputStream outputStream, long j10) throws IOException {
        byte[] bArr = new byte[(int) 16384];
        boolean z = j10 == -1;
        while (true) {
            if (j10 <= 0 && !z) {
                return;
            }
            int i10 = this.f15446m.read(bArr, 0, (int) (z ? 16384L : Math.min(j10, 16384L)));
            if (i10 <= 0) {
                return;
            }
            outputStream.write(bArr, 0, i10);
            if (!z) {
                j10 -= (long) i10;
            }
        }
    }

    public final long x(long j10, PrintWriter printWriter) {
        String strI = i("content-length");
        if (strI != null) {
            try {
                j10 = Long.parseLong(strI);
            } catch (NumberFormatException unused) {
                t.LOG.severe("content-length was no number ".concat(strI));
            }
        }
        printWriter.print("Content-Length: " + j10 + "\r\n");
        return j10;
    }

    public final void y(boolean z) {
        this.f15452s = z;
    }
}
