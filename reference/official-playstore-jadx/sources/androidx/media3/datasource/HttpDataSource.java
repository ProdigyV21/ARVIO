package androidx.media3.datasource;

import androidx.media3.datasource.DataSource;
import com.google.common.base.r;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface HttpDataSource extends DataSource {
    public static final r<String> REJECT_PAYWALL_TYPES = new d(2);

    public static abstract class BaseFactory implements Factory {
        private final RequestProperties defaultRequestProperties = new RequestProperties();

        public abstract HttpDataSource createDataSourceInternal(RequestProperties requestProperties);

        @Override // androidx.media3.datasource.HttpDataSource.Factory
        public final Factory setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }

        @Override // androidx.media3.datasource.DataSource.Factory
        public final HttpDataSource createDataSource() {
            return createDataSourceInternal(this.defaultRequestProperties);
        }
    }

    public static final class CleartextNotPermittedException extends HttpDataSourceException {
        public CleartextNotPermittedException(IOException iOException, DataSpec dataSpec) {
            super("Cleartext HTTP traffic not permitted. See https://developer.android.com/guide/topics/media/issues/cleartext-not-permitted", iOException, dataSpec, 2007, 1);
        }
    }

    public interface Factory extends DataSource.Factory {
        @Override // androidx.media3.datasource.DataSource.Factory
        HttpDataSource createDataSource();

        Factory setDefaultRequestProperties(Map<String, String> map);
    }

    public static class HttpDataSourceException extends DataSourceException {
        public static final int TYPE_CLOSE = 3;
        public static final int TYPE_OPEN = 1;
        public static final int TYPE_READ = 2;
        public final DataSpec dataSpec;
        public final int type;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Type {
        }

        @Deprecated
        public HttpDataSourceException(DataSpec dataSpec, int i10) {
            this(dataSpec, 2000, i10);
        }

        private static int assignErrorCode(int i10, int i11) {
            if (i10 == 2000 && i11 == 1) {
                return 2001;
            }
            return i10;
        }

        public static HttpDataSourceException createForIOException(IOException iOException, DataSpec dataSpec, int i10) {
            String message = iOException.getMessage();
            int i11 = iOException instanceof SocketTimeoutException ? 2002 : iOException instanceof InterruptedIOException ? 1004 : (message == null || !t7.a.S(message).matches("cleartext.*not permitted.*")) ? 2001 : 2007;
            return i11 == 2007 ? new CleartextNotPermittedException(iOException, dataSpec) : new HttpDataSourceException(iOException, dataSpec, i11, i10);
        }

        public HttpDataSourceException(DataSpec dataSpec, int i10, int i11) {
            super(assignErrorCode(i10, i11));
            this.dataSpec = dataSpec;
            this.type = i11;
        }

        @Deprecated
        public HttpDataSourceException(String str, DataSpec dataSpec, int i10) {
            this(str, dataSpec, 2000, i10);
        }

        public HttpDataSourceException(String str, DataSpec dataSpec, int i10, int i11) {
            super(str, assignErrorCode(i10, i11));
            this.dataSpec = dataSpec;
            this.type = i11;
        }

        @Deprecated
        public HttpDataSourceException(IOException iOException, DataSpec dataSpec, int i10) {
            this(iOException, dataSpec, 2000, i10);
        }

        public HttpDataSourceException(IOException iOException, DataSpec dataSpec, int i10, int i11) {
            super(iOException, assignErrorCode(i10, i11));
            this.dataSpec = dataSpec;
            this.type = i11;
        }

        @Deprecated
        public HttpDataSourceException(String str, IOException iOException, DataSpec dataSpec, int i10) {
            this(str, iOException, dataSpec, 2000, i10);
        }

        public HttpDataSourceException(String str, IOException iOException, DataSpec dataSpec, int i10, int i11) {
            super(str, iOException, assignErrorCode(i10, i11));
            this.dataSpec = dataSpec;
            this.type = i11;
        }
    }

    public static final class InvalidContentTypeException extends HttpDataSourceException {
        public final String contentType;

        public InvalidContentTypeException(String str, DataSpec dataSpec) {
            super(androidx.compose.material3.d.C("Invalid content type: ", str), dataSpec, 2003, 1);
            this.contentType = str;
        }
    }

    public static final class InvalidResponseCodeException extends HttpDataSourceException {
        public final Map<String, List<String>> headerFields;
        public final byte[] responseBody;
        public final int responseCode;
        public final String responseMessage;

        public InvalidResponseCodeException(int i10, String str, IOException iOException, Map<String, List<String>> map, DataSpec dataSpec, byte[] bArr) {
            super(a0.c.i(i10, "Response code: "), iOException, dataSpec, 2004, 1);
            this.responseCode = i10;
            this.responseMessage = str;
            this.headerFields = map;
            this.responseBody = bArr;
        }
    }

    void clearAllRequestProperties();

    void clearRequestProperty(String str);

    @Override // androidx.media3.datasource.DataSource
    void close() throws HttpDataSourceException;

    int getResponseCode();

    @Override // androidx.media3.datasource.DataSource
    Map<String, List<String>> getResponseHeaders();

    @Override // androidx.media3.datasource.DataSource
    long open(DataSpec dataSpec) throws HttpDataSourceException;

    @Override // androidx.media3.common.DataReader
    int read(byte[] bArr, int i10, int i11) throws HttpDataSourceException;

    void setRequestProperty(String str, String str2);

    public static final class RequestProperties {
        private final Map<String, String> requestProperties = new HashMap();
        private Map<String, String> requestPropertiesSnapshot;

        public synchronized void clear() {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.clear();
        }

        public synchronized void clearAndSet(Map<String, String> map) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.clear();
            this.requestProperties.putAll(map);
        }

        public synchronized Map<String, String> getSnapshot() {
            try {
                if (this.requestPropertiesSnapshot == null) {
                    this.requestPropertiesSnapshot = Collections.unmodifiableMap(new HashMap(this.requestProperties));
                }
            } catch (Throwable th) {
                throw th;
            }
            return this.requestPropertiesSnapshot;
        }

        public synchronized void remove(String str) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.remove(str);
        }

        public synchronized void set(String str, String str2) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.put(str, str2);
        }

        public synchronized void set(Map<String, String> map) {
            this.requestPropertiesSnapshot = null;
            this.requestProperties.putAll(map);
        }
    }
}
