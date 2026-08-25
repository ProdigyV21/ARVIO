package androidx.media3.datasource.okhttp;

import android.net.Uri;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.BaseDataSource;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.HttpUtil;
import androidx.media3.datasource.TransferListener;
import androidx.tv.foundation.lazy.list.k;
import com.google.common.base.r;
import com.google.common.util.concurrent.q1;
import gb.b0;
import gb.d0;
import gb.h;
import gb.i;
import gb.j;
import gb.j0;
import gb.k0;
import gb.m0;
import gb.p0;
import gb.s0;
import ib.c;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes3.dex */
public class OkHttpDataSource extends BaseDataSource implements HttpDataSource {
    private long bytesRead;
    private long bytesToRead;
    private final h cacheControl;
    private final i.a callFactory;
    private boolean connectionEstablished;
    private final r<String> contentTypePredicate;
    private DataSpec dataSpec;
    private final HttpDataSource.RequestProperties defaultRequestProperties;
    private final HttpDataSource.RequestProperties requestProperties;
    private p0 response;
    private InputStream responseByteStream;
    private final String userAgent;

    public static final class Factory implements HttpDataSource.Factory {
        private h cacheControl;
        private final i.a callFactory;
        private r<String> contentTypePredicate;
        private final HttpDataSource.RequestProperties defaultRequestProperties = new HttpDataSource.RequestProperties();
        private TransferListener transferListener;
        private String userAgent;

        public Factory(i.a aVar) {
            this.callFactory = aVar;
        }

        public Factory setCacheControl(h hVar) {
            this.cacheControl = hVar;
            return this;
        }

        public Factory setContentTypePredicate(r<String> rVar) {
            this.contentTypePredicate = rVar;
            return this;
        }

        @Override // androidx.media3.datasource.HttpDataSource.Factory
        public /* bridge */ /* synthetic */ HttpDataSource.Factory setDefaultRequestProperties(Map map) {
            return setDefaultRequestProperties((Map<String, String>) map);
        }

        public Factory setTransferListener(TransferListener transferListener) {
            this.transferListener = transferListener;
            return this;
        }

        public Factory setUserAgent(String str) {
            this.userAgent = str;
            return this;
        }

        @Override // androidx.media3.datasource.HttpDataSource.Factory
        public final Factory setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }

        @Override // androidx.media3.datasource.HttpDataSource.Factory, androidx.media3.datasource.DataSource.Factory
        public OkHttpDataSource createDataSource() {
            OkHttpDataSource okHttpDataSource = new OkHttpDataSource(this.callFactory, this.userAgent, this.cacheControl, this.defaultRequestProperties, this.contentTypePredicate);
            TransferListener transferListener = this.transferListener;
            if (transferListener != null) {
                okHttpDataSource.addTransferListener(transferListener);
            }
            return okHttpDataSource;
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.datasource.okhttp");
    }

    private void closeConnectionQuietly() {
        p0 p0Var = this.response;
        if (p0Var != null) {
            s0 s0Var = p0Var.f15786q;
            s0Var.getClass();
            s0Var.close();
        }
        this.responseByteStream = null;
    }

    private p0 executeCall(i iVar) throws IOException {
        final q1 q1Var = new q1();
        iVar.K0(new j() { // from class: androidx.media3.datasource.okhttp.OkHttpDataSource.1
            @Override // gb.j
            public void onFailure(i iVar2, IOException iOException) {
                q1Var.setException(iOException);
            }

            @Override // gb.j
            public void onResponse(i iVar2, p0 p0Var) {
                q1Var.set(p0Var);
            }
        });
        try {
            return (p0) q1Var.get();
        } catch (InterruptedException unused) {
            iVar.cancel();
            throw new InterruptedIOException();
        } catch (ExecutionException e5) {
            throw new IOException(e5);
        }
    }

    private k0 makeRequest(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        b0 b0VarD;
        m0 m0Var;
        long j10 = dataSpec.position;
        long j11 = dataSpec.length;
        String string = dataSpec.uri.toString();
        m0 m0Var2 = null;
        try {
            k kVar = new k(1);
            kVar.i(null, string);
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
            b0VarD = null;
        }
        if (b0VarD == null) {
            throw new HttpDataSource.HttpDataSourceException("Malformed URL", dataSpec, 1004, 1);
        }
        j0 j0Var = new j0();
        j0Var.f15722a = b0VarD;
        h hVar = this.cacheControl;
        if (hVar != null) {
            j0Var.c(hVar);
        }
        HashMap map = new HashMap();
        HttpDataSource.RequestProperties requestProperties = this.defaultRequestProperties;
        if (requestProperties != null) {
            map.putAll(requestProperties.getSnapshot());
        }
        map.putAll(this.requestProperties.getSnapshot());
        map.putAll(dataSpec.httpRequestHeaders);
        for (Map.Entry entry : map.entrySet()) {
            j0Var.e((String) entry.getKey(), (String) entry.getValue());
        }
        String strBuildRangeRequestHeader = HttpUtil.buildRangeRequestHeader(j10, j11);
        if (strBuildRangeRequestHeader != null) {
            j0Var.f15724c.a("Range", strBuildRangeRequestHeader);
        }
        String str = this.userAgent;
        if (str != null) {
            j0Var.f15724c.a("User-Agent", str);
        }
        if (!dataSpec.isFlagSet(1)) {
            j0Var.f15724c.a("Accept-Encoding", "identity");
        }
        byte[] bArr = dataSpec.httpBody;
        if (bArr == null) {
            if (dataSpec.httpMethod == 2) {
                byte[] bArr2 = Util.EMPTY_BYTE_ARRAY;
                int length = bArr2.length;
                c.c(bArr2.length, 0, length);
                m0Var = new m0(null, length, bArr2);
            }
            j0Var.f(dataSpec.getHttpMethodString(), m0Var2);
            return j0Var.b();
        }
        int length2 = bArr.length;
        c.c(bArr.length, 0, length2);
        m0Var = new m0(null, length2, bArr);
        m0Var2 = m0Var;
        j0Var.f(dataSpec.getHttpMethodString(), m0Var2);
        return j0Var.b();
    }

    private int readInternal(byte[] bArr, int i10, int i11) throws IOException {
        if (i11 == 0) {
            return 0;
        }
        long j10 = this.bytesToRead;
        if (j10 != -1) {
            long j11 = j10 - this.bytesRead;
            if (j11 == 0) {
                return -1;
            }
            i11 = (int) Math.min(i11, j11);
        }
        int i12 = ((InputStream) Util.castNonNull(this.responseByteStream)).read(bArr, i10, i11);
        if (i12 == -1) {
            return -1;
        }
        this.bytesRead += (long) i12;
        bytesTransferred(i12);
        return i12;
    }

    private void skipFully(long j10, DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        if (j10 == 0) {
            return;
        }
        byte[] bArr = new byte[4096];
        while (j10 > 0) {
            try {
                int i10 = ((InputStream) Util.castNonNull(this.responseByteStream)).read(bArr, 0, (int) Math.min(j10, 4096));
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedIOException();
                }
                if (i10 == -1) {
                    throw new HttpDataSource.HttpDataSourceException(dataSpec, 2008, 1);
                }
                j10 -= (long) i10;
                bytesTransferred(i10);
            } catch (IOException e5) {
                if (!(e5 instanceof HttpDataSource.HttpDataSourceException)) {
                    throw new HttpDataSource.HttpDataSourceException(dataSpec, 2000, 1);
                }
                throw ((HttpDataSource.HttpDataSourceException) e5);
            }
        }
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void clearRequestProperty(String str) {
        str.getClass();
        this.requestProperties.remove(str);
    }

    @Override // androidx.media3.datasource.DataSource
    public void close() {
        if (this.connectionEstablished) {
            this.connectionEstablished = false;
            transferEnded();
            closeConnectionQuietly();
        }
        this.response = null;
        this.dataSpec = null;
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public int getResponseCode() {
        p0 p0Var = this.response;
        if (p0Var == null) {
            return -1;
        }
        return p0Var.f15783n;
    }

    @Override // androidx.media3.datasource.BaseDataSource, androidx.media3.datasource.DataSource
    public Map<String, List<String>> getResponseHeaders() {
        p0 p0Var = this.response;
        return p0Var == null ? Collections.EMPTY_MAP : p0Var.f15785p.d();
    }

    @Override // androidx.media3.datasource.DataSource
    public Uri getUri() {
        p0 p0Var = this.response;
        if (p0Var != null) {
            return Uri.parse(p0Var.f15780i.f15730a.f15625i);
        }
        DataSpec dataSpec = this.dataSpec;
        if (dataSpec != null) {
            return dataSpec.uri;
        }
        return null;
    }

    @Override // androidx.media3.datasource.DataSource
    public long open(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        byte[] bArrB;
        this.dataSpec = dataSpec;
        long j10 = 0;
        this.bytesRead = 0L;
        this.bytesToRead = 0L;
        transferInitializing(dataSpec);
        try {
            p0 p0VarExecuteCall = executeCall(this.callFactory.a(makeRequest(dataSpec)));
            this.response = p0VarExecuteCall;
            s0 s0Var = p0VarExecuteCall.f15786q;
            s0Var.getClass();
            this.responseByteStream = s0Var.i();
            int i10 = p0VarExecuteCall.f15783n;
            if (!p0VarExecuteCall.k()) {
                if (i10 == 416) {
                    if (dataSpec.position == HttpUtil.getDocumentSize(p0VarExecuteCall.f15785p.a("Content-Range"))) {
                        this.connectionEstablished = true;
                        transferStarted(dataSpec);
                        long j11 = dataSpec.length;
                        if (j11 != -1) {
                            return j11;
                        }
                        return 0L;
                    }
                }
                try {
                    InputStream inputStream = this.responseByteStream;
                    inputStream.getClass();
                    bArrB = com.google.common.io.i.b(inputStream);
                } catch (IOException unused) {
                    bArrB = Util.EMPTY_BYTE_ARRAY;
                }
                byte[] bArr = bArrB;
                TreeMap treeMapD = p0VarExecuteCall.f15785p.d();
                closeConnectionQuietly();
                throw new HttpDataSource.InvalidResponseCodeException(i10, p0VarExecuteCall.f15782m, i10 == 416 ? new DataSourceException(2008) : null, treeMapD, dataSpec, bArr);
            }
            d0 d0VarL = s0Var.l();
            String str = d0VarL != null ? d0VarL.f15635a : "";
            r<String> rVar = this.contentTypePredicate;
            if (rVar != null && !rVar.apply(str)) {
                closeConnectionQuietly();
                throw new HttpDataSource.InvalidContentTypeException(str, dataSpec);
            }
            if (i10 == 200) {
                long j12 = dataSpec.position;
                if (j12 != 0) {
                    j10 = j12;
                }
            }
            long j13 = dataSpec.length;
            if (j13 != -1) {
                this.bytesToRead = j13;
            } else {
                long jK = s0Var.k();
                this.bytesToRead = jK != -1 ? jK - j10 : -1L;
            }
            this.connectionEstablished = true;
            transferStarted(dataSpec);
            try {
                skipFully(j10, dataSpec);
                return this.bytesToRead;
            } catch (HttpDataSource.HttpDataSourceException e5) {
                closeConnectionQuietly();
                throw e5;
            }
        } catch (IOException e6) {
            throw HttpDataSource.HttpDataSourceException.createForIOException(e6, dataSpec, 1);
        }
    }

    @Override // androidx.media3.common.DataReader
    public int read(byte[] bArr, int i10, int i11) throws HttpDataSource.HttpDataSourceException {
        try {
            return readInternal(bArr, i10, i11);
        } catch (IOException e5) {
            throw HttpDataSource.HttpDataSourceException.createForIOException(e5, (DataSpec) Util.castNonNull(this.dataSpec), 2);
        }
    }

    @Override // androidx.media3.datasource.HttpDataSource
    public void setRequestProperty(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.requestProperties.set(str, str2);
    }

    private OkHttpDataSource(i.a aVar, String str, h hVar, HttpDataSource.RequestProperties requestProperties, r<String> rVar) {
        super(true);
        aVar.getClass();
        this.callFactory = aVar;
        this.userAgent = str;
        this.cacheControl = hVar;
        this.defaultRequestProperties = requestProperties;
        this.contentTypePredicate = rVar;
        this.requestProperties = new HttpDataSource.RequestProperties();
    }
}
