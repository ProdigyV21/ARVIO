package fi.iki.elonen;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class t {
    public static final String MIME_HTML = "text/html";
    public static final String MIME_PLAINTEXT = "text/plain";
    protected static Map<String, String> MIME_TYPES = null;
    private static final String QUERY_STRING_PARAMETER = "NanoHttpd.QUERY_STRING";
    public static final int SOCKET_READ_TIMEOUT = 5000;
    protected a asyncRunner;
    private final int myPort;
    private volatile ServerSocket myServerSocket;
    private Thread myThread;
    private s tempFileManagerFactory;
    private static final String CONTENT_DISPOSITION_REGEX = "([ |\t]*Content-Disposition[ |\t]*:)(.*)";
    private static final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile(CONTENT_DISPOSITION_REGEX, 2);
    private static final String CONTENT_TYPE_REGEX = "([ |\t]*content-type[ |\t]*:)(.*)";
    private static final Pattern CONTENT_TYPE_PATTERN = Pattern.compile(CONTENT_TYPE_REGEX, 2);
    private static final String CONTENT_DISPOSITION_ATTRIBUTE_REGEX = "[ |\t]*([a-zA-Z]*)[ |\t]*=[ |\t]*['|\"]([^\"^']*)['|\"]";
    private static final Pattern CONTENT_DISPOSITION_ATTRIBUTE_PATTERN = Pattern.compile(CONTENT_DISPOSITION_ATTRIBUTE_REGEX);
    private static final Logger LOG = Logger.getLogger(t.class.getName());
    private p serverSocketFactory = new a1.a();
    private final String hostname = null;

    public t(int i10) {
        this.myPort = i10;
        setTempFileManagerFactory(new a1.a());
        setAsyncRunner(new androidx.recyclerview.widget.e(1));
    }

    public static void a(String str, Map map) {
        try {
            Enumeration<URL> resources = t.class.getClassLoader().getResources(str);
            while (resources.hasMoreElements()) {
                URL urlNextElement = resources.nextElement();
                Properties properties = new Properties();
                InputStream inputStreamOpenStream = null;
                try {
                    try {
                        inputStreamOpenStream = urlNextElement.openStream();
                        properties.load(inputStreamOpenStream);
                    } catch (IOException e5) {
                        LOG.log(Level.SEVERE, "could not load mimetypes from " + urlNextElement, (Throwable) e5);
                    }
                    b(inputStreamOpenStream);
                    map.putAll(properties);
                } catch (Throwable th) {
                    b(inputStreamOpenStream);
                    throw th;
                }
            }
        } catch (IOException unused) {
            LOG.log(Level.INFO, "no mime types available at ".concat(str));
        }
    }

    public static final void b(Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                } else if (obj instanceof Socket) {
                    ((Socket) obj).close();
                } else {
                    if (!(obj instanceof ServerSocket)) {
                        throw new IllegalArgumentException("Unknown object to close");
                    }
                    ((ServerSocket) obj).close();
                }
            } catch (IOException e5) {
                LOG.log(Level.SEVERE, "Could not close", (Throwable) e5);
            }
        }
    }

    public static Map<String, List<String>> decodeParameters(Map<String, String> map) {
        return decodeParameters(map.get(QUERY_STRING_PARAMETER));
    }

    public static String decodePercent(String str) {
        try {
            return URLDecoder.decode(str, "UTF8");
        } catch (UnsupportedEncodingException e5) {
            LOG.log(Level.WARNING, "Encoding not supported, ignored", (Throwable) e5);
            return null;
        }
    }

    public static String getMimeTypeForFile(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        String str2 = iLastIndexOf >= 0 ? mimeTypes().get(str.substring(iLastIndexOf + 1).toLowerCase()) : null;
        return str2 == null ? "application/octet-stream" : str2;
    }

    public static SSLServerSocketFactory makeSSLSocketFactory(KeyStore keyStore, KeyManager[] keyManagerArr) throws IOException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(keyManagerArr, trustManagerFactory.getTrustManagers(), null);
            return sSLContext.getServerSocketFactory();
        } catch (Exception e5) {
            throw new IOException(e5.getMessage());
        }
    }

    public static Map<String, String> mimeTypes() {
        if (MIME_TYPES == null) {
            HashMap map = new HashMap();
            MIME_TYPES = map;
            a("META-INF/nanohttpd/default-mimetypes.properties", map);
            a("META-INF/nanohttpd/mimetypes.properties", MIME_TYPES);
            if (MIME_TYPES.isEmpty()) {
                LOG.log(Level.WARNING, "no mime types found in the classpath! please provide mimetypes.properties");
            }
        }
        return MIME_TYPES;
    }

    public static n newChunkedResponse(l lVar, String str, InputStream inputStream) {
        return new n(lVar, str, inputStream, -1L);
    }

    public static n newFixedLengthResponse(l lVar, String str, InputStream inputStream, long j10) {
        return new n(lVar, str, inputStream, j10);
    }

    public synchronized void closeAllConnections() {
        stop();
    }

    public b createClientHandler(Socket socket, InputStream inputStream) {
        return new b(this, inputStream, socket);
    }

    public o createServerRunnable(int i10) {
        return new o(this, i10);
    }

    public String getHostname() {
        return this.hostname;
    }

    public final int getListeningPort() {
        if (this.myServerSocket == null) {
            return -1;
        }
        return this.myServerSocket.getLocalPort();
    }

    public p getServerSocketFactory() {
        return this.serverSocketFactory;
    }

    public s getTempFileManagerFactory() {
        return this.tempFileManagerFactory;
    }

    public final boolean isAlive() {
        return wasStarted() && !this.myServerSocket.isClosed() && this.myThread.isAlive();
    }

    public void makeSecure(SSLServerSocketFactory sSLServerSocketFactory, String[] strArr) {
        f fVar = new f();
        fVar.f15416i = sSLServerSocketFactory;
        fVar.f15417l = strArr;
        this.serverSocketFactory = fVar;
    }

    public abstract n serve(h hVar);

    @Deprecated
    public n serve(String str, i iVar, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) {
        return newFixedLengthResponse(m.NOT_FOUND, MIME_PLAINTEXT, "Not Found");
    }

    public void setAsyncRunner(a aVar) {
        this.asyncRunner = aVar;
    }

    public void setServerSocketFactory(p pVar) {
        this.serverSocketFactory = pVar;
    }

    public void setTempFileManagerFactory(s sVar) {
        this.tempFileManagerFactory = sVar;
    }

    public void start() throws IOException {
        start(5000);
    }

    public void stop() {
        try {
            b(this.myServerSocket);
            this.asyncRunner.b();
            Thread thread = this.myThread;
            if (thread != null) {
                thread.join();
            }
        } catch (Exception e5) {
            LOG.log(Level.SEVERE, "Could not stop all connections", (Throwable) e5);
        }
    }

    public boolean useGzipWhenAccepted(n nVar) {
        String str = nVar.f15445l;
        if (str != null) {
            return str.toLowerCase().contains("text/") || nVar.f15445l.toLowerCase().contains("/json");
        }
        return false;
    }

    public final boolean wasStarted() {
        return (this.myServerSocket == null || this.myThread == null) ? false : true;
    }

    public static Map<String, List<String>> decodeParameters(String str) {
        HashMap map = new HashMap();
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
            while (stringTokenizer.hasMoreTokens()) {
                String strNextToken = stringTokenizer.nextToken();
                int iIndexOf = strNextToken.indexOf(61);
                String strTrim = (iIndexOf >= 0 ? decodePercent(strNextToken.substring(0, iIndexOf)) : decodePercent(strNextToken)).trim();
                if (!map.containsKey(strTrim)) {
                    map.put(strTrim, new ArrayList());
                }
                String strDecodePercent = iIndexOf >= 0 ? decodePercent(strNextToken.substring(iIndexOf + 1)) : null;
                if (strDecodePercent != null) {
                    ((List) map.get(strTrim)).add(strDecodePercent);
                }
            }
        }
        return map;
    }

    public static n newFixedLengthResponse(l lVar, String str, String str2) {
        byte[] bytes;
        c cVar = new c(str);
        if (str2 == null) {
            return newFixedLengthResponse(lVar, str, new ByteArrayInputStream(new byte[0]), 0L);
        }
        try {
            if (!Charset.forName(cVar.a()).newEncoder().canEncode(str2) && cVar.f15410c == null) {
                cVar = new c(str + "; charset=UTF-8");
            }
            bytes = str2.getBytes(cVar.a());
        } catch (UnsupportedEncodingException e5) {
            LOG.log(Level.SEVERE, "encoding problem, responding nothing", (Throwable) e5);
            bytes = new byte[0];
        }
        return newFixedLengthResponse(lVar, cVar.f15408a, new ByteArrayInputStream(bytes), bytes.length);
    }

    public void start(int i10) throws IOException {
        start(i10, true);
    }

    public void start(int i10, boolean z) throws IOException {
        this.myServerSocket = getServerSocketFactory().mo0a();
        this.myServerSocket.setReuseAddress(true);
        o oVarCreateServerRunnable = createServerRunnable(i10);
        Thread thread = new Thread(oVarCreateServerRunnable);
        this.myThread = thread;
        thread.setDaemon(z);
        this.myThread.setName("NanoHttpd Main Listener");
        this.myThread.start();
        while (!oVarCreateServerRunnable.f15456m && oVarCreateServerRunnable.f15455l == null) {
            try {
                Thread.sleep(10L);
            } catch (Throwable unused) {
            }
        }
        IOException iOException = oVarCreateServerRunnable.f15455l;
        if (iOException != null) {
            throw iOException;
        }
    }

    public static SSLServerSocketFactory makeSSLSocketFactory(KeyStore keyStore, KeyManagerFactory keyManagerFactory) throws IOException {
        try {
            return makeSSLSocketFactory(keyStore, keyManagerFactory.getKeyManagers());
        } catch (Exception e5) {
            throw new IOException(e5.getMessage());
        }
    }

    public static SSLServerSocketFactory makeSSLSocketFactory(String str, char[] cArr) throws IOException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream resourceAsStream = t.class.getResourceAsStream(str);
            if (resourceAsStream == null) {
                throw new IOException("Unable to load keystore from classpath: " + str);
            }
            keyStore.load(resourceAsStream, cArr);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, cArr);
            return makeSSLSocketFactory(keyStore, keyManagerFactory);
        } catch (Exception e5) {
            throw new IOException(e5.getMessage());
        }
    }

    public static n newFixedLengthResponse(String str) {
        return newFixedLengthResponse(m.OK, MIME_HTML, str);
    }
}
