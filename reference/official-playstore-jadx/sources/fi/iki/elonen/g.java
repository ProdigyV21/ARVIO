package fi.iki.elonen;

import io.ktor.http.ContentDisposition;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.regex.Matcher;
import javax.net.ssl.SSLException;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes4.dex */
public final class g implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r f15418a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final OutputStream f15419b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final BufferedInputStream f15420c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f15421d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f15422e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f15423f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public i f15424g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public HashMap f15425h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public HashMap f15426i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public d f15427j;
    public final String k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f15428l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ t f15429m;

    public g(t tVar, r rVar, InputStream inputStream, OutputStream outputStream, InetAddress inetAddress) {
        this.f15429m = tVar;
        this.f15418a = rVar;
        this.f15420c = new BufferedInputStream(inputStream, 8192);
        this.f15419b = outputStream;
        this.k = (inetAddress.isLoopbackAddress() || inetAddress.isAnyLocalAddress()) ? "127.0.0.1" : inetAddress.getHostAddress().toString();
        if (!inetAddress.isLoopbackAddress() && !inetAddress.isAnyLocalAddress()) {
            inetAddress.getHostName().getClass();
        }
        this.f15426i = new HashMap();
    }

    public static void d(String str, Map map) {
        String strTrim;
        String strDecodePercent;
        if (str == null) {
            return;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            int iIndexOf = strNextToken.indexOf(61);
            if (iIndexOf >= 0) {
                strTrim = t.decodePercent(strNextToken.substring(0, iIndexOf)).trim();
                strDecodePercent = t.decodePercent(strNextToken.substring(iIndexOf + 1));
            } else {
                strTrim = t.decodePercent(strNextToken).trim();
                strDecodePercent = "";
            }
            List arrayList = (List) map.get(strTrim);
            if (arrayList == null) {
                arrayList = new ArrayList();
                map.put(strTrim, arrayList);
            }
            arrayList.add(strDecodePercent);
        }
    }

    public static int f(int i10, byte[] bArr) {
        int i11;
        int i12 = 0;
        while (true) {
            int i13 = i12 + 1;
            if (i13 >= i10) {
                return 0;
            }
            byte b10 = bArr[i12];
            if (b10 == 13 && bArr[i13] == 10 && (i11 = i12 + 3) < i10 && bArr[i12 + 2] == 13 && bArr[i11] == 10) {
                return i12 + 4;
            }
            if (b10 == 10 && bArr[i13] == 10) {
                return i12 + 2;
            }
            i12 = i13;
        }
    }

    public static int[] g(ByteBuffer byteBuffer, byte[] bArr) {
        int[] iArr = new int[0];
        if (byteBuffer.remaining() < bArr.length) {
            return iArr;
        }
        int length = bArr.length + 4096;
        byte[] bArr2 = new byte[length];
        int iRemaining = byteBuffer.remaining() < length ? byteBuffer.remaining() : length;
        byteBuffer.get(bArr2, 0, iRemaining);
        int length2 = iRemaining - bArr.length;
        int i10 = 0;
        do {
            for (int i11 = 0; i11 < length2; i11++) {
                for (int i12 = 0; i12 < bArr.length && bArr2[i11 + i12] == bArr[i12]; i12++) {
                    if (i12 == bArr.length - 1) {
                        int[] iArr2 = new int[iArr.length + 1];
                        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                        iArr2[iArr.length] = i10 + i11;
                        iArr = iArr2;
                    }
                }
            }
            i10 += length2;
            System.arraycopy(bArr2, length - bArr.length, bArr2, 0, bArr.length);
            length2 = length - bArr.length;
            if (byteBuffer.remaining() < length2) {
                length2 = byteBuffer.remaining();
            }
            byteBuffer.get(bArr2, bArr.length, length2);
        } while (length2 > 0);
        return iArr;
    }

    @Override // fi.iki.elonen.h
    public final void a(HashMap map) throws Throwable {
        long j10;
        RandomAccessFile randomAccessFile;
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutput dataOutputStream;
        ByteBuffer map2;
        RandomAccessFile randomAccessFile2 = null;
        try {
            if (this.f15426i.containsKey("content-length")) {
                j10 = Long.parseLong((String) this.f15426i.get("content-length"));
            } else {
                j10 = this.f15421d < this.f15422e ? r4 - r3 : 0L;
            }
            if (j10 < 1024) {
                byteArrayOutputStream = new ByteArrayOutputStream();
                dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                randomAccessFile = null;
            } else {
                try {
                    randomAccessFile = new RandomAccessFile(this.f15418a.y().f15414a.getAbsolutePath(), "rw");
                    byteArrayOutputStream = null;
                    dataOutputStream = randomAccessFile;
                } catch (Exception e5) {
                    throw new Error(e5);
                }
            }
            try {
                byte[] bArr = new byte[512];
                while (this.f15422e >= 0 && j10 > 0) {
                    int i10 = this.f15420c.read(bArr, 0, (int) Math.min(j10, 512L));
                    this.f15422e = i10;
                    j10 -= (long) i10;
                    if (i10 > 0) {
                        dataOutputStream.write(bArr, 0, i10);
                    }
                }
                if (byteArrayOutputStream != null) {
                    map2 = ByteBuffer.wrap(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
                } else {
                    map2 = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0L, randomAccessFile.length());
                    randomAccessFile.seek(0L);
                }
                if (i.f15432m.equals(this.f15424g)) {
                    c cVar = new c((String) this.f15426i.get("content-type"));
                    String str = cVar.f15409b;
                    if (!HttpConnection.MULTIPART_FORM_DATA.equalsIgnoreCase(str)) {
                        byte[] bArr2 = new byte[map2.remaining()];
                        map2.get(bArr2);
                        String strTrim = new String(bArr2, cVar.a()).trim();
                        if (HttpConnection.FORM_URL_ENCODED.equalsIgnoreCase(str)) {
                            d(strTrim, this.f15425h);
                        } else if (strTrim.length() != 0) {
                            map.put("postData", strTrim);
                        }
                    } else {
                        if (cVar.f15411d == null) {
                            throw new NanoHTTPD$ResponseException(m.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
                        }
                        c(cVar, map2, this.f15425h, map);
                    }
                } else if (i.f15431l.equals(this.f15424g)) {
                    map.put("content", h(map2, 0, map2.limit(), null));
                }
                t.b(randomAccessFile);
            } catch (Throwable th) {
                th = th;
                randomAccessFile2 = randomAccessFile;
                t.b(randomAccessFile2);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            t.b(randomAccessFile2);
            throw th;
        }
    }

    public final void b(BufferedReader bufferedReader, HashMap map, HashMap map2, HashMap map3) throws NanoHTTPD$ResponseException {
        String strDecodePercent;
        try {
            String line = bufferedReader.readLine();
            if (line == null) {
                return;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            boolean zHasMoreTokens = stringTokenizer.hasMoreTokens();
            m mVar = m.BAD_REQUEST;
            if (!zHasMoreTokens) {
                throw new NanoHTTPD$ResponseException(mVar, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
            }
            map.put("method", stringTokenizer.nextToken());
            if (!stringTokenizer.hasMoreTokens()) {
                throw new NanoHTTPD$ResponseException(mVar, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
            }
            String strNextToken = stringTokenizer.nextToken();
            int iIndexOf = strNextToken.indexOf(63);
            if (iIndexOf >= 0) {
                d(strNextToken.substring(iIndexOf + 1), map2);
                strDecodePercent = t.decodePercent(strNextToken.substring(0, iIndexOf));
            } else {
                strDecodePercent = t.decodePercent(strNextToken);
            }
            if (stringTokenizer.hasMoreTokens()) {
                this.f15428l = stringTokenizer.nextToken();
            } else {
                this.f15428l = "HTTP/1.1";
                t.LOG.log(Level.FINE, "no protocol version specified, strange. Assuming HTTP/1.1.");
            }
            String line2 = bufferedReader.readLine();
            while (line2 != null && !line2.trim().isEmpty()) {
                int iIndexOf2 = line2.indexOf(58);
                if (iIndexOf2 >= 0) {
                    map3.put(line2.substring(0, iIndexOf2).trim().toLowerCase(Locale.US), line2.substring(iIndexOf2 + 1).trim());
                }
                line2 = bufferedReader.readLine();
            }
            map.put("uri", strDecodePercent);
        } catch (IOException e5) {
            throw new NanoHTTPD$ResponseException("SERVER INTERNAL ERROR: IOException: " + e5.getMessage(), e5);
        }
    }

    public final void c(c cVar, ByteBuffer byteBuffer, HashMap map, HashMap map2) throws Throwable {
        int[] iArrG;
        int length;
        m mVar;
        String str;
        c cVar2 = cVar;
        String str2 = cVar2.f15411d;
        m mVar2 = m.INTERNAL_ERROR;
        try {
            iArrG = g(byteBuffer, str2.getBytes());
            length = iArrG.length;
            mVar = m.BAD_REQUEST;
        } catch (NanoHTTPD$ResponseException e5) {
            throw e5;
        } catch (Exception e6) {
            e = e6;
        }
        try {
            if (length < 2) {
                throw new NanoHTTPD$ResponseException(mVar, "BAD REQUEST: Content type is multipart/form-data but contains less than two boundary strings.");
            }
            int i10 = 1024;
            byte[] bArr = new byte[1024];
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            while (i12 < iArrG.length - 1) {
                byteBuffer.position(iArrG[i12]);
                int iRemaining = byteBuffer.remaining() < i10 ? byteBuffer.remaining() : i10;
                byteBuffer.get(bArr, i11, iRemaining);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, i11, iRemaining), Charset.forName(cVar2.a())), iRemaining);
                String line = bufferedReader.readLine();
                if (line == null || !line.contains(str2)) {
                    throw new NanoHTTPD$ResponseException(mVar, "BAD REQUEST: Content type is multipart/form-data but chunk does not start with boundary.");
                }
                String line2 = bufferedReader.readLine();
                String strGroup = null;
                String strTrim = null;
                int i14 = i13;
                int i15 = 2;
                String str3 = null;
                while (line2 != null && line2.trim().length() > 0) {
                    Matcher matcher = t.CONTENT_DISPOSITION_PATTERN.matcher(line2);
                    if (matcher.matches()) {
                        str = str2;
                        Matcher matcher2 = t.CONTENT_DISPOSITION_ATTRIBUTE_PATTERN.matcher(matcher.group(2));
                        while (matcher2.find()) {
                            int[] iArr = iArrG;
                            String strGroup2 = matcher2.group(1);
                            if (ContentDisposition.Parameters.Name.equalsIgnoreCase(strGroup2)) {
                                strGroup = matcher2.group(2);
                            } else if (ContentDisposition.Parameters.FileName.equalsIgnoreCase(strGroup2)) {
                                String strGroup3 = matcher2.group(2);
                                if (strGroup3.isEmpty()) {
                                    str3 = strGroup3;
                                } else if (i14 > 0) {
                                    str3 = strGroup3;
                                    i14++;
                                    strGroup = strGroup + String.valueOf(i14);
                                } else {
                                    i14++;
                                    str3 = strGroup3;
                                }
                            }
                            iArrG = iArr;
                        }
                    } else {
                        str = str2;
                    }
                    int[] iArr2 = iArrG;
                    Matcher matcher3 = t.CONTENT_TYPE_PATTERN.matcher(line2);
                    if (matcher3.matches()) {
                        strTrim = matcher3.group(2).trim();
                    }
                    line2 = bufferedReader.readLine();
                    i15++;
                    iArrG = iArr2;
                    str2 = str;
                }
                String str4 = str2;
                int[] iArr3 = iArrG;
                int i16 = 0;
                while (true) {
                    int i17 = i15 - 1;
                    if (i15 <= 0) {
                        break;
                    }
                    while (bArr[i16] != 10) {
                        i16++;
                    }
                    i16++;
                    i15 = i17;
                }
                if (i16 >= iRemaining - 4) {
                    throw new NanoHTTPD$ResponseException(mVar2, "Multipart header size exceeds MAX_HEADER_SIZE.");
                }
                int i18 = iArr3[i12] + i16;
                i12++;
                int i19 = iArr3[i12] - 4;
                byteBuffer.position(i18);
                List arrayList = (List) map.get(strGroup);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    map.put(strGroup, arrayList);
                }
                if (strTrim == null) {
                    byte[] bArr2 = new byte[i19 - i18];
                    byteBuffer.get(bArr2);
                    arrayList.add(new String(bArr2, cVar.a()));
                } else {
                    String strH = h(byteBuffer, i18, i19 - i18, str3);
                    if (map2.containsKey(strGroup)) {
                        int i20 = 2;
                        while (true) {
                            if (!map2.containsKey(strGroup + i20)) {
                                break;
                            } else {
                                i20++;
                            }
                        }
                        map2.put(strGroup + i20, strH);
                    } else {
                        map2.put(strGroup, strH);
                    }
                    arrayList.add(str3);
                }
                cVar2 = cVar;
                iArrG = iArr3;
                i13 = i14;
                str2 = str4;
                i10 = 1024;
                i11 = 0;
            }
        } catch (NanoHTTPD$ResponseException e10) {
            throw e10;
        } catch (Exception e11) {
            e = e11;
            throw new NanoHTTPD$ResponseException(mVar2, e.toString());
        }
    }

    public final void e() {
        m mVar = m.INTERNAL_ERROR;
        t tVar = this.f15429m;
        r rVar = this.f15418a;
        BufferedInputStream bufferedInputStream = this.f15420c;
        OutputStream outputStream = this.f15419b;
        try {
            try {
                try {
                    try {
                        byte[] bArr = new byte[8192];
                        boolean z = false;
                        this.f15421d = 0;
                        this.f15422e = 0;
                        bufferedInputStream.mark(8192);
                        try {
                            int i10 = bufferedInputStream.read(bArr, 0, 8192);
                            if (i10 == -1) {
                                t.b(bufferedInputStream);
                                t.b(outputStream);
                                throw new SocketException("NanoHttpd Shutdown");
                            }
                            while (i10 > 0) {
                                int i11 = this.f15422e + i10;
                                this.f15422e = i11;
                                int iF = f(i11, bArr);
                                this.f15421d = iF;
                                if (iF > 0) {
                                    break;
                                }
                                int i12 = this.f15422e;
                                i10 = bufferedInputStream.read(bArr, i12, 8192 - i12);
                            }
                            if (this.f15421d < this.f15422e) {
                                bufferedInputStream.reset();
                                bufferedInputStream.skip(this.f15421d);
                            }
                            this.f15425h = new HashMap();
                            HashMap map = this.f15426i;
                            if (map == null) {
                                this.f15426i = new HashMap();
                            } else {
                                map.clear();
                            }
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, 0, this.f15422e)));
                            HashMap map2 = new HashMap();
                            b(bufferedReader, map2, this.f15425h, this.f15426i);
                            String str = this.k;
                            if (str != null) {
                                this.f15426i.put("remote-addr", str);
                                this.f15426i.put("http-client-ip", str);
                            }
                            i iVarA = i.a((String) map2.get("method"));
                            this.f15424g = iVarA;
                            if (iVarA == null) {
                                throw new NanoHTTPD$ResponseException(m.BAD_REQUEST, "BAD REQUEST: Syntax error. HTTP verb " + ((String) map2.get("method")) + " unhandled.");
                            }
                            this.f15423f = (String) map2.get("uri");
                            this.f15427j = new d(this.f15426i);
                            String str2 = (String) this.f15426i.get("connection");
                            boolean z5 = "HTTP/1.1".equals(this.f15428l) && (str2 == null || !str2.matches("(?i).*close.*"));
                            n nVarServe = tVar.serve(this);
                            if (nVarServe == null) {
                                throw new NanoHTTPD$ResponseException(mVar, "SERVER INTERNAL ERROR: Serve() returned a null response.");
                            }
                            String str3 = (String) this.f15426i.get("accept-encoding");
                            this.f15427j.a();
                            nVarServe.O(this.f15424g);
                            if (tVar.useGzipWhenAccepted(nVarServe) && str3 != null && str3.contains("gzip")) {
                                z = true;
                            }
                            nVarServe.y(z);
                            nVarServe.G(z5);
                            nVarServe.l(outputStream);
                            if (!z5 || nVarServe.j()) {
                                throw new SocketException("NanoHttpd Shutdown");
                            }
                            t.b(nVarServe);
                            rVar.clear();
                        } catch (SSLException e5) {
                            throw e5;
                        } catch (IOException unused) {
                            t.b(bufferedInputStream);
                            t.b(outputStream);
                            throw new SocketException("NanoHttpd Shutdown");
                        }
                    } catch (NanoHTTPD$ResponseException e6) {
                        t.newFixedLengthResponse(e6.a(), t.MIME_PLAINTEXT, e6.getMessage()).l(outputStream);
                        t.b(outputStream);
                        t.b(null);
                        rVar.clear();
                    }
                } catch (SSLException e10) {
                    t.newFixedLengthResponse(mVar, t.MIME_PLAINTEXT, "SSL PROTOCOL FAILURE: " + e10.getMessage()).l(outputStream);
                    t.b(outputStream);
                    t.b(null);
                    rVar.clear();
                } catch (IOException e11) {
                    t.newFixedLengthResponse(mVar, t.MIME_PLAINTEXT, "SERVER INTERNAL ERROR: IOException: " + e11.getMessage()).l(outputStream);
                    t.b(outputStream);
                    t.b(null);
                    rVar.clear();
                }
            } catch (SocketException e12) {
                throw e12;
            } catch (SocketTimeoutException e13) {
                throw e13;
            }
        } catch (Throwable th) {
            t.b(null);
            rVar.clear();
            throw th;
        }
    }

    @Override // fi.iki.elonen.h
    public final i getMethod() {
        return this.f15424g;
    }

    @Override // fi.iki.elonen.h
    public final String getUri() {
        return this.f15423f;
    }

    public final String h(ByteBuffer byteBuffer, int i10, int i11, String str) throws Throwable {
        File file;
        ByteBuffer byteBufferDuplicate;
        FileOutputStream fileOutputStream;
        if (i11 <= 0) {
            return "";
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                file = this.f15418a.y().f15414a;
                byteBufferDuplicate = byteBuffer.duplicate();
                fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            FileChannel channel = fileOutputStream.getChannel();
            byteBufferDuplicate.position(i10).limit(i10 + i11);
            channel.write(byteBufferDuplicate.slice());
            String absolutePath = file.getAbsolutePath();
            t.b(fileOutputStream);
            return absolutePath;
        } catch (Exception e6) {
            e = e6;
            fileOutputStream2 = fileOutputStream;
            throw new Error(e);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            t.b(fileOutputStream2);
            throw th;
        }
    }
}
