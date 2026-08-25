package org.jsoup.helper;

import a0.c;
import com.google.android.gms.cast.MediaError;
import io.ktor.http.ContentDisposition;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.jsoup.Connection;
import org.jsoup.UncheckedIOException;
import org.jsoup.a;
import org.jsoup.internal.ControllableInputStream;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.parser.TokenQueue;

/* JADX INFO: loaded from: classes5.dex */
public class HttpConnection implements Connection {
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DEFAULT_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36";
    private static final String DefaultUploadType = "application/octet-stream";
    public static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final int HTTP_TEMP_REDIR = 307;
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String USER_AGENT = "User-Agent";
    private Request req;
    private Connection.Response res;

    public static abstract class Base<T extends Connection.Base<T>> implements Connection.Base<T> {
        private static final URL UnsetUrl;
        Map<String, String> cookies;
        Map<String, List<String>> headers;
        Connection.Method method;
        URL url;

        static {
            try {
                UnsetUrl = new URL("http://undefined/");
            } catch (MalformedURLException e5) {
                throw new IllegalStateException(e5);
            }
        }

        private List<String> getHeadersCaseInsensitive(String str) {
            Validate.notNull(str);
            for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                if (str.equalsIgnoreCase(entry.getKey())) {
                    return entry.getValue();
                }
            }
            return Collections.EMPTY_LIST;
        }

        private Map.Entry<String, List<String>> scanHeaders(String str) {
            String strLowerCase = Normalizer.lowerCase(str);
            for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                if (Normalizer.lowerCase(entry.getKey()).equals(strLowerCase)) {
                    return entry;
                }
            }
            return null;
        }

        @Override // org.jsoup.Connection.Base
        public T addHeader(String str, String str2) {
            Validate.notEmptyParam(str, ContentDisposition.Parameters.Name);
            if (str2 == null) {
                str2 = "";
            }
            List<String> listHeaders = headers(str);
            if (listHeaders.isEmpty()) {
                listHeaders = new ArrayList<>();
                this.headers.put(str, listHeaders);
            }
            listHeaders.add(str2);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public String cookie(String str) {
            Validate.notEmptyParam(str, ContentDisposition.Parameters.Name);
            return this.cookies.get(str);
        }

        @Override // org.jsoup.Connection.Base
        public Map<String, String> cookies() {
            return this.cookies;
        }

        @Override // org.jsoup.Connection.Base
        public boolean hasCookie(String str) {
            Validate.notEmptyParam(str, ContentDisposition.Parameters.Name);
            return this.cookies.containsKey(str);
        }

        @Override // org.jsoup.Connection.Base
        public boolean hasHeader(String str) {
            Validate.notEmptyParam(str, ContentDisposition.Parameters.Name);
            return !getHeadersCaseInsensitive(str).isEmpty();
        }

        @Override // org.jsoup.Connection.Base
        public boolean hasHeaderWithValue(String str, String str2) {
            Validate.notEmpty(str);
            Validate.notEmpty(str2);
            Iterator<String> it = headers(str).iterator();
            while (it.hasNext()) {
                if (str2.equalsIgnoreCase(it.next())) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.jsoup.Connection.Base
        public String header(String str) {
            Validate.notNullParam(str, ContentDisposition.Parameters.Name);
            List<String> headersCaseInsensitive = getHeadersCaseInsensitive(str);
            if (headersCaseInsensitive.size() > 0) {
                return StringUtil.join(headersCaseInsensitive, ", ");
            }
            return null;
        }

        @Override // org.jsoup.Connection.Base
        public List<String> headers(String str) {
            Validate.notEmptyParam(str, ContentDisposition.Parameters.Name);
            return getHeadersCaseInsensitive(str);
        }

        @Override // org.jsoup.Connection.Base
        public Connection.Method method() {
            return this.method;
        }

        @Override // org.jsoup.Connection.Base
        public Map<String, List<String>> multiHeaders() {
            return this.headers;
        }

        @Override // org.jsoup.Connection.Base
        public T removeCookie(String str) {
            Validate.notEmptyParam(str, ContentDisposition.Parameters.Name);
            this.cookies.remove(str);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public T removeHeader(String str) {
            Validate.notEmptyParam(str, ContentDisposition.Parameters.Name);
            Map.Entry<String, List<String>> entryScanHeaders = scanHeaders(str);
            if (entryScanHeaders != null) {
                this.headers.remove(entryScanHeaders.getKey());
            }
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public URL url() {
            URL url = this.url;
            if (url != UnsetUrl) {
                return url;
            }
            throw new IllegalArgumentException("URL not set. Make sure to call #url(...) before executing the request.");
        }

        @Override // org.jsoup.Connection.Base
        public T method(Connection.Method method) {
            Validate.notNullParam(method, "method");
            this.method = method;
            return this;
        }

        private Base() {
            this.url = UnsetUrl;
            this.method = Connection.Method.GET;
            this.headers = new LinkedHashMap();
            this.cookies = new LinkedHashMap();
        }

        @Override // org.jsoup.Connection.Base
        public T cookie(String str, String str2) {
            Validate.notEmptyParam(str, ContentDisposition.Parameters.Name);
            Validate.notNullParam(str2, "value");
            this.cookies.put(str, str2);
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public Map<String, String> headers() {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.headers.size());
            for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (value.size() > 0) {
                    linkedHashMap.put(key, value.get(0));
                }
            }
            return linkedHashMap;
        }

        @Override // org.jsoup.Connection.Base
        public T url(URL url) {
            Validate.notNullParam(url, "url");
            this.url = new UrlBuilder(url).build();
            return this;
        }

        @Override // org.jsoup.Connection.Base
        public T header(String str, String str2) {
            Validate.notEmptyParam(str, ContentDisposition.Parameters.Name);
            removeHeader(str);
            addHeader(str, str2);
            return this;
        }

        private Base(Base<T> base) {
            this.url = UnsetUrl;
            this.method = Connection.Method.GET;
            this.url = base.url;
            this.method = base.method;
            this.headers = new LinkedHashMap();
            for (Map.Entry<String, List<String>> entry : base.headers.entrySet()) {
                this.headers.put(entry.getKey(), new ArrayList(entry.getValue()));
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            this.cookies = linkedHashMap;
            linkedHashMap.putAll(base.cookies);
        }
    }

    public static class KeyVal implements Connection.KeyVal {
        private String contentType;
        private String key;
        private InputStream stream;
        private String value;

        private KeyVal(String str, String str2) {
            Validate.notEmptyParam(str, "key");
            Validate.notNullParam(str2, "value");
            this.key = str;
            this.value = str2;
        }

        public static KeyVal create(String str, String str2) {
            return new KeyVal(str, str2);
        }

        @Override // org.jsoup.Connection.KeyVal
        public Connection.KeyVal contentType(String str) {
            Validate.notEmpty(str);
            this.contentType = str;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public boolean hasInputStream() {
            return this.stream != null;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }

        public static KeyVal create(String str, String str2, InputStream inputStream) {
            return new KeyVal(str, str2).inputStream(inputStream);
        }

        @Override // org.jsoup.Connection.KeyVal
        public KeyVal inputStream(InputStream inputStream) {
            Validate.notNullParam(this.value, "inputStream");
            this.stream = inputStream;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public KeyVal key(String str) {
            Validate.notEmptyParam(str, "key");
            this.key = str;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public KeyVal value(String str) {
            Validate.notNullParam(str, "value");
            this.value = str;
            return this;
        }

        @Override // org.jsoup.Connection.KeyVal
        public String contentType() {
            return this.contentType;
        }

        @Override // org.jsoup.Connection.KeyVal
        public InputStream inputStream() {
            return this.stream;
        }

        @Override // org.jsoup.Connection.KeyVal
        public String key() {
            return this.key;
        }

        @Override // org.jsoup.Connection.KeyVal
        public String value() {
            return this.value;
        }
    }

    public static class Request extends Base<Connection.Request> implements Connection.Request {
        private RequestAuthenticator authenticator;
        private String body;
        private CookieManager cookieManager;
        private final Collection<Connection.KeyVal> data;
        private volatile boolean executing;
        private boolean followRedirects;
        private boolean ignoreContentType;
        private boolean ignoreHttpErrors;
        private int maxBodySizeBytes;
        private Parser parser;
        private boolean parserDefined;
        private String postDataCharset;
        private Proxy proxy;
        private SSLSocketFactory sslSocketFactory;
        private int timeoutMilliseconds;

        static {
            System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
        }

        public Request() {
            super();
            this.body = null;
            this.ignoreHttpErrors = false;
            this.ignoreContentType = false;
            this.parserDefined = false;
            this.postDataCharset = DataUtil.defaultCharsetName;
            this.executing = false;
            this.timeoutMilliseconds = 30000;
            this.maxBodySizeBytes = 2097152;
            this.followRedirects = true;
            this.data = new ArrayList();
            this.method = Connection.Method.GET;
            addHeader("Accept-Encoding", "gzip");
            addHeader(HttpConnection.USER_AGENT, HttpConnection.DEFAULT_UA);
            this.parser = Parser.htmlParser();
            this.cookieManager = new CookieManager();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base addHeader(String str, String str2) {
            return super.addHeader(str, str2);
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request auth(RequestAuthenticator requestAuthenticator) {
            this.authenticator = requestAuthenticator;
            return this;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String cookie(String str) {
            return super.cookie(str);
        }

        public CookieManager cookieManager() {
            return this.cookieManager;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map cookies() {
            return super.cookies();
        }

        @Override // org.jsoup.Connection.Request
        public boolean followRedirects() {
            return this.followRedirects;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasCookie(String str) {
            return super.hasCookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeader(String str) {
            return super.hasHeader(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeaderWithValue(String str, String str2) {
            return super.hasHeaderWithValue(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String header(String str) {
            return super.header(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ List headers(String str) {
            return super.headers(str);
        }

        @Override // org.jsoup.Connection.Request
        public boolean ignoreContentType() {
            return this.ignoreContentType;
        }

        @Override // org.jsoup.Connection.Request
        public boolean ignoreHttpErrors() {
            return this.ignoreHttpErrors;
        }

        @Override // org.jsoup.Connection.Request
        public int maxBodySize() {
            return this.maxBodySizeBytes;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base method(Connection.Method method) {
            return super.method(method);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map multiHeaders() {
            return super.multiHeaders();
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request postDataCharset(String str) {
            Validate.notNullParam(str, HttpAuthHeader.Parameters.Charset);
            if (!Charset.isSupported(str)) {
                throw new IllegalCharsetNameException(str);
            }
            this.postDataCharset = str;
            return this;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base removeCookie(String str) {
            return super.removeCookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base removeHeader(String str) {
            return super.removeHeader(str);
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request requestBody(String str) {
            this.body = str;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public SSLSocketFactory sslSocketFactory() {
            return this.sslSocketFactory;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ URL url() {
            return super.url();
        }

        @Override // org.jsoup.Connection.Request
        public RequestAuthenticator auth() {
            return this.authenticator;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base cookie(String str, String str2) {
            return super.cookie(str, str2);
        }

        @Override // org.jsoup.Connection.Request
        public Request data(Connection.KeyVal keyVal) {
            Validate.notNullParam(keyVal, "keyval");
            this.data.add(keyVal);
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request followRedirects(boolean z) {
            this.followRedirects = z;
            return this;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base header(String str, String str2) {
            return super.header(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map headers() {
            return super.headers();
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request ignoreContentType(boolean z) {
            this.ignoreContentType = z;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request ignoreHttpErrors(boolean z) {
            this.ignoreHttpErrors = z;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Connection.Request maxBodySize(int i10) {
            Validate.isTrue(i10 >= 0, "maxSize must be 0 (unlimited) or larger");
            this.maxBodySizeBytes = i10;
            return this;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Method method() {
            return super.method();
        }

        @Override // org.jsoup.Connection.Request
        public Request parser(Parser parser) {
            this.parser = parser;
            this.parserDefined = true;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public String requestBody() {
            return this.body;
        }

        @Override // org.jsoup.Connection.Request
        public void sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.sslSocketFactory = sSLSocketFactory;
        }

        @Override // org.jsoup.Connection.Request
        public int timeout() {
            return this.timeoutMilliseconds;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base url(URL url) {
            return super.url(url);
        }

        @Override // org.jsoup.Connection.Request
        public Proxy proxy() {
            return this.proxy;
        }

        @Override // org.jsoup.Connection.Request
        public Request timeout(int i10) {
            Validate.isTrue(i10 >= 0, "Timeout milliseconds must be 0 (infinite) or greater");
            this.timeoutMilliseconds = i10;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public Collection<Connection.KeyVal> data() {
            return this.data;
        }

        @Override // org.jsoup.Connection.Request
        public Parser parser() {
            return this.parser;
        }

        @Override // org.jsoup.Connection.Request
        public Request proxy(Proxy proxy) {
            this.proxy = proxy;
            return this;
        }

        @Override // org.jsoup.Connection.Request
        public String postDataCharset() {
            return this.postDataCharset;
        }

        @Override // org.jsoup.Connection.Request
        public Request proxy(String str, int i10) {
            this.proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(str, i10));
            return this;
        }

        public Request(Request request) {
            super(request);
            this.body = null;
            this.ignoreHttpErrors = false;
            this.ignoreContentType = false;
            this.parserDefined = false;
            this.postDataCharset = DataUtil.defaultCharsetName;
            this.executing = false;
            this.proxy = request.proxy;
            this.postDataCharset = request.postDataCharset;
            this.timeoutMilliseconds = request.timeoutMilliseconds;
            this.maxBodySizeBytes = request.maxBodySizeBytes;
            this.followRedirects = request.followRedirects;
            this.data = new ArrayList();
            this.ignoreHttpErrors = request.ignoreHttpErrors;
            this.ignoreContentType = request.ignoreContentType;
            this.parser = request.parser.newInstance();
            this.parserDefined = request.parserDefined;
            this.sslSocketFactory = request.sslSocketFactory;
            this.cookieManager = request.cookieManager;
            this.authenticator = request.authenticator;
            this.executing = false;
        }
    }

    public static class Response extends Base<Connection.Response> implements Connection.Response {
        private static final String LOCATION = "Location";
        private static final int MAX_REDIRECTS = 20;
        private static final Pattern xmlContentTypeRxp = Pattern.compile("(\\w+)/\\w*\\+?xml.*");
        private ControllableInputStream bodyStream;
        private ByteBuffer byteData;
        private String charset;
        private HttpURLConnection conn;
        private final String contentType;
        private boolean executed;
        private boolean inputStreamRead;
        private int numRedirects;
        private final Request req;
        private final int statusCode;
        private final String statusMessage;

        public Response() {
            super();
            this.executed = false;
            this.inputStreamRead = false;
            this.numRedirects = 0;
            this.statusCode = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
            this.statusMessage = "Request not made";
            this.req = new Request();
            this.contentType = null;
        }

        private static HttpURLConnection createConnection(Request request) throws IOException {
            Proxy proxy = request.proxy();
            HttpURLConnection httpURLConnection = (HttpURLConnection) (proxy == null ? request.url().openConnection() : request.url().openConnection(proxy));
            httpURLConnection.setRequestMethod(request.method().name());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setConnectTimeout(request.timeout());
            httpURLConnection.setReadTimeout(request.timeout() / 2);
            if (request.sslSocketFactory() != null && (httpURLConnection instanceof HttpsURLConnection)) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(request.sslSocketFactory());
            }
            if (request.authenticator != null) {
                AuthenticationHandler.handler.enable(request.authenticator, httpURLConnection);
            }
            if (request.method().hasBody()) {
                httpURLConnection.setDoOutput(true);
            }
            CookieUtil.applyCookiesToRequest(request, httpURLConnection);
            for (Map.Entry entry : request.multiHeaders().entrySet()) {
                Iterator it = ((List) entry.getValue()).iterator();
                while (it.hasNext()) {
                    httpURLConnection.addRequestProperty((String) entry.getKey(), (String) it.next());
                }
            }
            return httpURLConnection;
        }

        private static LinkedHashMap<String, List<String>> createHeaderMap(HttpURLConnection httpURLConnection) {
            LinkedHashMap<String, List<String>> linkedHashMap = new LinkedHashMap<>();
            int i10 = 0;
            while (true) {
                String headerFieldKey = httpURLConnection.getHeaderFieldKey(i10);
                String headerField = httpURLConnection.getHeaderField(i10);
                if (headerFieldKey == null && headerField == null) {
                    return linkedHashMap;
                }
                i10++;
                if (headerFieldKey != null && headerField != null) {
                    if (linkedHashMap.containsKey(headerFieldKey)) {
                        linkedHashMap.get(headerFieldKey).add(headerField);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(headerField);
                        linkedHashMap.put(headerFieldKey, arrayList);
                    }
                }
            }
        }

        public static Response execute(Request request) throws IOException {
            return execute(request, null);
        }

        private static String fixHeaderEncoding(String str) {
            if (str == null) {
                return str;
            }
            byte[] bytes = str.getBytes(HttpConnection.ISO_8859_1);
            return looksLikeUtf8(bytes) ? new String(bytes, DataUtil.UTF_8) : str;
        }

        private static boolean looksLikeUtf8(byte[] bArr) {
            int i10;
            int i11 = (bArr.length >= 3 && (bArr[0] & 255) == 239 && (bArr[1] & 255) == 187 && (bArr[2] & 255) == 191) ? 3 : 0;
            int length = bArr.length;
            boolean z = false;
            while (i11 < length) {
                byte b10 = bArr[i11];
                if ((b10 & 128) != 0) {
                    if ((b10 & 224) == 192) {
                        i10 = i11 + 1;
                    } else if ((b10 & 240) == 224) {
                        i10 = i11 + 2;
                    } else {
                        if ((b10 & 248) != 240) {
                            return false;
                        }
                        i10 = i11 + 3;
                    }
                    if (i10 >= bArr.length) {
                        return false;
                    }
                    while (i11 < i10) {
                        i11++;
                        if ((bArr[i11] & 192) != 128) {
                            return false;
                        }
                    }
                    z = true;
                }
                i11++;
            }
            return z;
        }

        private void prepareByteData() {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            if (this.bodyStream == null || this.byteData != null) {
                return;
            }
            Validate.isFalse(this.inputStreamRead, "Request has already been read (with .parse())");
            try {
                try {
                    this.byteData = DataUtil.readToByteBuffer(this.bodyStream, this.req.maxBodySize());
                } catch (IOException e5) {
                    throw new UncheckedIOException(e5);
                }
            } finally {
                this.inputStreamRead = true;
                safeClose();
            }
        }

        private void safeClose() {
            ControllableInputStream controllableInputStream = this.bodyStream;
            if (controllableInputStream != null) {
                try {
                    controllableInputStream.close();
                } catch (IOException unused) {
                } catch (Throwable th) {
                    this.bodyStream = null;
                    throw th;
                }
                this.bodyStream = null;
            }
            HttpURLConnection httpURLConnection = this.conn;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                this.conn = null;
            }
        }

        private static void serialiseRequestUrl(Connection.Request request) throws IOException {
            UrlBuilder urlBuilder = new UrlBuilder(request.url());
            for (Connection.KeyVal keyVal : request.data()) {
                Validate.isFalse(keyVal.hasInputStream(), "InputStream data not supported in URL query string.");
                urlBuilder.appendKeyVal(keyVal);
            }
            request.url(urlBuilder.build());
            request.data().clear();
        }

        private static String setOutputContentType(Connection.Request request) {
            String strHeader = request.header(HttpConnection.CONTENT_TYPE);
            if (strHeader != null) {
                if (!strHeader.contains(HttpConnection.MULTIPART_FORM_DATA) || strHeader.contains("boundary")) {
                    return null;
                }
                String strMimeBoundary = DataUtil.mimeBoundary();
                request.header(HttpConnection.CONTENT_TYPE, "multipart/form-data; boundary=" + strMimeBoundary);
                return strMimeBoundary;
            }
            if (!HttpConnection.needsMultipart(request)) {
                request.header(HttpConnection.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + request.postDataCharset());
                return null;
            }
            String strMimeBoundary2 = DataUtil.mimeBoundary();
            request.header(HttpConnection.CONTENT_TYPE, "multipart/form-data; boundary=" + strMimeBoundary2);
            return strMimeBoundary2;
        }

        private static void writePost(Connection.Request request, OutputStream outputStream, String str) throws IOException {
            Collection<Connection.KeyVal> collectionData = request.data();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, Charset.forName(request.postDataCharset())));
            if (str != null) {
                for (Connection.KeyVal keyVal : collectionData) {
                    bufferedWriter.write("--");
                    bufferedWriter.write(str);
                    bufferedWriter.write("\r\n");
                    bufferedWriter.write("Content-Disposition: form-data; name=\"");
                    bufferedWriter.write(HttpConnection.encodeMimeName(keyVal.key()));
                    bufferedWriter.write("\"");
                    InputStream inputStream = keyVal.inputStream();
                    if (inputStream != null) {
                        bufferedWriter.write("; filename=\"");
                        bufferedWriter.write(HttpConnection.encodeMimeName(keyVal.value()));
                        bufferedWriter.write("\"\r\nContent-Type: ");
                        String strContentType = keyVal.contentType();
                        if (strContentType == null) {
                            strContentType = HttpConnection.DefaultUploadType;
                        }
                        bufferedWriter.write(strContentType);
                        bufferedWriter.write("\r\n\r\n");
                        bufferedWriter.flush();
                        DataUtil.crossStreams(inputStream, outputStream);
                        outputStream.flush();
                    } else {
                        bufferedWriter.write("\r\n\r\n");
                        bufferedWriter.write(keyVal.value());
                    }
                    bufferedWriter.write("\r\n");
                }
                bufferedWriter.write("--");
                bufferedWriter.write(str);
                bufferedWriter.write("--");
            } else {
                String strRequestBody = request.requestBody();
                if (strRequestBody != null) {
                    bufferedWriter.write(strRequestBody);
                } else {
                    boolean z = true;
                    for (Connection.KeyVal keyVal2 : collectionData) {
                        if (z) {
                            z = false;
                        } else {
                            bufferedWriter.append('&');
                        }
                        bufferedWriter.write(URLEncoder.encode(keyVal2.key(), request.postDataCharset()));
                        bufferedWriter.write(61);
                        bufferedWriter.write(URLEncoder.encode(keyVal2.value(), request.postDataCharset()));
                    }
                }
            }
            bufferedWriter.close();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base addHeader(String str, String str2) {
            return super.addHeader(str, str2);
        }

        @Override // org.jsoup.Connection.Response
        public String body() {
            prepareByteData();
            Validate.notNull(this.byteData);
            String str = this.charset;
            String string = (str == null ? DataUtil.UTF_8 : Charset.forName(str)).decode(this.byteData).toString();
            this.byteData.rewind();
            return string;
        }

        @Override // org.jsoup.Connection.Response
        public byte[] bodyAsBytes() {
            prepareByteData();
            Validate.notNull(this.byteData);
            return this.byteData.array();
        }

        @Override // org.jsoup.Connection.Response
        public BufferedInputStream bodyStream() {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            if (this.byteData != null) {
                return new BufferedInputStream(new ByteArrayInputStream(this.byteData.array()), 32768);
            }
            Validate.isFalse(this.inputStreamRead, "Request has already been read");
            Validate.notNull(this.bodyStream);
            this.inputStreamRead = true;
            return this.bodyStream.inputStream();
        }

        @Override // org.jsoup.Connection.Response
        public Connection.Response bufferUp() {
            prepareByteData();
            return this;
        }

        @Override // org.jsoup.Connection.Response
        public String contentType() {
            return this.contentType;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String cookie(String str) {
            return super.cookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map cookies() {
            return super.cookies();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasCookie(String str) {
            return super.hasCookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeader(String str) {
            return super.hasHeader(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ boolean hasHeaderWithValue(String str, String str2) {
            return super.hasHeaderWithValue(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ String header(String str) {
            return super.header(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ List headers(String str) {
            return super.headers(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base method(Connection.Method method) {
            return super.method(method);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map multiHeaders() {
            return super.multiHeaders();
        }

        @Override // org.jsoup.Connection.Response
        public Document parse() throws IOException {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before parsing response");
            InputStream byteArrayInputStream = this.bodyStream;
            if (this.byteData != null) {
                byteArrayInputStream = new ByteArrayInputStream(this.byteData.array());
                this.inputStreamRead = false;
            }
            Validate.isFalse(this.inputStreamRead, "Input stream already read and parsed, cannot re-read.");
            Document inputStream = DataUtil.parseInputStream(byteArrayInputStream, this.charset, this.url.toExternalForm(), this.req.parser());
            inputStream.connection(new HttpConnection(this.req, this));
            this.charset = inputStream.outputSettings().charset().name();
            this.inputStreamRead = true;
            safeClose();
            return inputStream;
        }

        public void processResponseHeaders(Map<String, List<String>> map) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                if (key != null) {
                    List<String> value = entry.getValue();
                    if (key.equalsIgnoreCase("Set-Cookie")) {
                        for (String str : value) {
                            if (str != null) {
                                TokenQueue tokenQueue = new TokenQueue(str);
                                String strTrim = tokenQueue.chompTo("=").trim();
                                String strTrim2 = tokenQueue.consumeTo(";").trim();
                                if (strTrim.length() > 0 && !this.cookies.containsKey(strTrim)) {
                                    cookie(strTrim, strTrim2);
                                }
                            }
                        }
                    }
                    Iterator<String> it = value.iterator();
                    while (it.hasNext()) {
                        addHeader(key, fixHeaderEncoding(it.next()));
                    }
                }
            }
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base removeCookie(String str) {
            return super.removeCookie(str);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base removeHeader(String str) {
            return super.removeHeader(str);
        }

        @Override // org.jsoup.Connection.Response
        public int statusCode() {
            return this.statusCode;
        }

        @Override // org.jsoup.Connection.Response
        public String statusMessage() {
            return this.statusMessage;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ URL url() {
            return super.url();
        }

        /* JADX WARN: Can't wrap try/catch for region: R(11:12|(1:14)(1:15)|(1:17)|18|(15:(9:(1:24)|26|125|27|(4:29|121|30|31)|44|126|45|(2:64|(2:109|110)(9:70|(2:79|80)|(1:86)|87|(1:103)(6:91|(1:93)(1:94)|95|(1:97)(3:99|(1:101)|102)|98|102)|104|(1:106)|107|108))(8:49|(1:51)|54|(1:58)|59|60|(1:62)|63))(1:22)|126|45|(1:47)|64|(4:66|68|109|110)(3:68|109|110)|70|(5:72|74|76|79|80)|(3:82|84|86)|87|(2:89|103)(0)|104|(0)|107|108)|25|26|125|27|(0)|44) */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x009d, code lost:
        
            r9 = e;
         */
        /* JADX WARN: Removed duplicated region for block: B:103:0x01da A[Catch: all -> 0x009a, IOException -> 0x00db, TRY_LEAVE, TryCatch #3 {all -> 0x009a, blocks: (B:27:0x0086, B:29:0x008f, B:31:0x0096, B:42:0x00a7, B:43:0x00aa, B:44:0x00ab, B:45:0x00b4, B:47:0x00bc, B:51:0x00c6, B:54:0x00df, B:56:0x00f0, B:58:0x00f9, B:59:0x00fd, B:70:0x012c, B:72:0x0132, B:74:0x0138, B:76:0x0140, B:79:0x014d, B:80:0x015c, B:82:0x015f, B:84:0x016b, B:86:0x0171, B:87:0x0178, B:89:0x0186, B:91:0x018e, B:93:0x0194, B:95:0x019d, B:97:0x01a7, B:102:0x01c3, B:99:0x01ae, B:101:0x01b8, B:94:0x0199, B:103:0x01da, B:68:0x0126, B:109:0x01f1, B:110:0x0200, B:112:0x0203, B:113:0x0206), top: B:125:0x0086 }] */
        /* JADX WARN: Removed duplicated region for block: B:106:0x01e9  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x008f A[Catch: all -> 0x009a, IOException -> 0x009d, TRY_LEAVE, TryCatch #3 {all -> 0x009a, blocks: (B:27:0x0086, B:29:0x008f, B:31:0x0096, B:42:0x00a7, B:43:0x00aa, B:44:0x00ab, B:45:0x00b4, B:47:0x00bc, B:51:0x00c6, B:54:0x00df, B:56:0x00f0, B:58:0x00f9, B:59:0x00fd, B:70:0x012c, B:72:0x0132, B:74:0x0138, B:76:0x0140, B:79:0x014d, B:80:0x015c, B:82:0x015f, B:84:0x016b, B:86:0x0171, B:87:0x0178, B:89:0x0186, B:91:0x018e, B:93:0x0194, B:95:0x019d, B:97:0x01a7, B:102:0x01c3, B:99:0x01ae, B:101:0x01b8, B:94:0x0199, B:103:0x01da, B:68:0x0126, B:109:0x01f1, B:110:0x0200, B:112:0x0203, B:113:0x0206), top: B:125:0x0086 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static org.jsoup.helper.HttpConnection.Response execute(org.jsoup.helper.HttpConnection.Request r8, org.jsoup.helper.HttpConnection.Response r9) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 537
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.HttpConnection.Response.execute(org.jsoup.helper.HttpConnection$Request, org.jsoup.helper.HttpConnection$Response):org.jsoup.helper.HttpConnection$Response");
        }

        @Override // org.jsoup.Connection.Response
        public String charset() {
            return this.charset;
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base cookie(String str, String str2) {
            return super.cookie(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base header(String str, String str2) {
            return super.header(str, str2);
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Map headers() {
            return super.headers();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Method method() {
            return super.method();
        }

        @Override // org.jsoup.helper.HttpConnection.Base, org.jsoup.Connection.Base
        public /* bridge */ /* synthetic */ Connection.Base url(URL url) {
            return super.url(url);
        }

        @Override // org.jsoup.Connection.Response
        public Response charset(String str) {
            this.charset = str;
            return this;
        }

        private Response(HttpURLConnection httpURLConnection, Request request, Response response) throws IOException {
            super();
            this.executed = false;
            this.inputStreamRead = false;
            this.numRedirects = 0;
            this.conn = httpURLConnection;
            this.req = request;
            this.method = Connection.Method.valueOf(httpURLConnection.getRequestMethod());
            this.url = httpURLConnection.getURL();
            this.statusCode = httpURLConnection.getResponseCode();
            this.statusMessage = httpURLConnection.getResponseMessage();
            this.contentType = httpURLConnection.getContentType();
            LinkedHashMap<String, List<String>> linkedHashMapCreateHeaderMap = createHeaderMap(httpURLConnection);
            processResponseHeaders(linkedHashMapCreateHeaderMap);
            CookieUtil.storeCookies(request, this.url, linkedHashMapCreateHeaderMap);
            if (response != null) {
                for (Map.Entry entry : response.cookies().entrySet()) {
                    if (!hasCookie((String) entry.getKey())) {
                        cookie((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                response.safeClose();
                int i10 = response.numRedirects + 1;
                this.numRedirects = i10;
                if (i10 < 20) {
                    return;
                }
                throw new IOException("Too many redirects occurred trying to load URL " + response.url());
            }
        }
    }

    public static Connection connect(String str) {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.url(str);
        return httpConnection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String encodeMimeName(String str) {
        return str.replace("\"", "%22");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean needsMultipart(Connection.Request request) {
        Iterator<Connection.KeyVal> it = request.data().iterator();
        while (it.hasNext()) {
            if (it.next().hasInputStream()) {
                return true;
            }
        }
        return false;
    }

    @Override // org.jsoup.Connection
    public Connection auth(RequestAuthenticator requestAuthenticator) {
        this.req.auth(requestAuthenticator);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection cookie(String str, String str2) {
        this.req.cookie(str, str2);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection cookieStore(CookieStore cookieStore) {
        this.req.cookieManager = new CookieManager(cookieStore, null);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection cookies(Map<String, String> map) {
        Validate.notNullParam(map, "cookies");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.req.cookie(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(String str, String str2) {
        this.req.data((Connection.KeyVal) KeyVal.create(str, str2));
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection.Response execute() throws IOException {
        Response responseExecute = Response.execute(this.req);
        this.res = responseExecute;
        return responseExecute;
    }

    @Override // org.jsoup.Connection
    public Connection followRedirects(boolean z) {
        this.req.followRedirects(z);
        return this;
    }

    @Override // org.jsoup.Connection
    public Document get() throws IOException {
        this.req.method(Connection.Method.GET);
        execute();
        Validate.notNull(this.res);
        return this.res.parse();
    }

    @Override // org.jsoup.Connection
    public Connection header(String str, String str2) {
        this.req.header(str, str2);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection headers(Map<String, String> map) {
        Validate.notNullParam(map, "headers");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.req.header(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection ignoreContentType(boolean z) {
        this.req.ignoreContentType(z);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection ignoreHttpErrors(boolean z) {
        this.req.ignoreHttpErrors(z);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection maxBodySize(int i10) {
        this.req.maxBodySize(i10);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection method(Connection.Method method) {
        this.req.method(method);
        return this;
    }

    @Override // org.jsoup.Connection
    public final /* synthetic */ Connection newRequest(String str) {
        return a.b(this, str);
    }

    @Override // org.jsoup.Connection
    public Connection parser(Parser parser) {
        this.req.parser(parser);
        return this;
    }

    @Override // org.jsoup.Connection
    public Document post() throws IOException {
        this.req.method(Connection.Method.POST);
        execute();
        Validate.notNull(this.res);
        return this.res.parse();
    }

    @Override // org.jsoup.Connection
    public Connection postDataCharset(String str) {
        this.req.postDataCharset(str);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection proxy(Proxy proxy) {
        this.req.proxy(proxy);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection referrer(String str) {
        Validate.notNullParam(str, "referrer");
        this.req.header("Referer", str);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection.Request request() {
        return this.req;
    }

    @Override // org.jsoup.Connection
    public Connection requestBody(String str) {
        this.req.requestBody(str);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection.Response response() {
        Connection.Response response = this.res;
        if (response != null) {
            return response;
        }
        throw new IllegalArgumentException("You must execute the request before getting a response.");
    }

    @Override // org.jsoup.Connection
    public Connection sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.req.sslSocketFactory(sSLSocketFactory);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection timeout(int i10) {
        this.req.timeout(i10);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection url(URL url) {
        this.req.url(url);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection userAgent(String str) {
        Validate.notNullParam(str, "userAgent");
        this.req.header(USER_AGENT, str);
        return this;
    }

    public HttpConnection() {
        this.req = new Request();
    }

    @Override // org.jsoup.Connection
    public CookieStore cookieStore() {
        return this.req.cookieManager.getCookieStore();
    }

    @Override // org.jsoup.Connection
    public Connection data(String str, String str2, InputStream inputStream) {
        this.req.data((Connection.KeyVal) KeyVal.create(str, str2, inputStream));
        return this;
    }

    @Override // org.jsoup.Connection
    public final /* synthetic */ Connection newRequest(URL url) {
        return a.c(this, url);
    }

    @Override // org.jsoup.Connection
    public Connection proxy(String str, int i10) {
        this.req.proxy(str, i10);
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection request(Connection.Request request) {
        this.req = (Request) request;
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection url(String str) {
        Validate.notEmptyParam(str, "url");
        try {
            this.req.url(new URL(str));
            return this;
        } catch (MalformedURLException e5) {
            throw new IllegalArgumentException(c.l("The supplied URL, '", str, "', is malformed. Make sure it is an absolute URL, and starts with 'http://' or 'https://'. See https://jsoup.org/cookbook/extracting-data/working-with-urls"), e5);
        }
    }

    public static Connection connect(URL url) {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.url(url);
        return httpConnection;
    }

    @Override // org.jsoup.Connection
    public Connection data(String str, String str2, InputStream inputStream, String str3) {
        this.req.data(KeyVal.create(str, str2, inputStream).contentType(str3));
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection newRequest() {
        return new HttpConnection(this.req);
    }

    @Override // org.jsoup.Connection
    public Connection response(Connection.Response response) {
        this.res = response;
        return this;
    }

    public HttpConnection(Request request) {
        this.req = new Request(request);
    }

    @Override // org.jsoup.Connection
    public Connection data(Map<String, String> map) {
        Validate.notNullParam(map, "data");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.req.data((Connection.KeyVal) KeyVal.create(entry.getKey(), entry.getValue()));
        }
        return this;
    }

    private HttpConnection(Request request, Response response) {
        this.req = request;
        this.res = response;
    }

    @Override // org.jsoup.Connection
    public Connection data(String... strArr) {
        Validate.notNullParam(strArr, "keyvals");
        Validate.isTrue(strArr.length % 2 == 0, "Must supply an even number of key value pairs");
        for (int i10 = 0; i10 < strArr.length; i10 += 2) {
            String str = strArr[i10];
            String str2 = strArr[i10 + 1];
            Validate.notEmpty(str, "Data key must not be empty");
            Validate.notNull(str2, "Data value must not be null");
            this.req.data((Connection.KeyVal) KeyVal.create(str, str2));
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection data(Collection<Connection.KeyVal> collection) {
        Validate.notNullParam(collection, "data");
        Iterator<Connection.KeyVal> it = collection.iterator();
        while (it.hasNext()) {
            this.req.data(it.next());
        }
        return this;
    }

    @Override // org.jsoup.Connection
    public Connection.KeyVal data(String str) {
        Validate.notEmptyParam(str, "key");
        for (Connection.KeyVal keyVal : request().data()) {
            if (keyVal.key().equals(str)) {
                return keyVal;
            }
        }
        return null;
    }
}
