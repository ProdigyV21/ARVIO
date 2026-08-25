package androidx.media3.exoplayer.drm;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.drm.ExoMediaDrm;
import androidx.media3.exoplayer.drm.MediaDrmCallback;
import com.google.common.collect.a0;
import com.google.common.collect.t3;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.jsoup.helper.HttpConnection;

/* JADX INFO: loaded from: classes3.dex */
public final class HttpMediaDrmCallback implements MediaDrmCallback {
    private final DataSource.Factory dataSourceFactory;
    private final String defaultLicenseUrl;
    private final boolean forceDefaultLicenseUrl;
    private final Map<String, String> keyRequestProperties;

    public HttpMediaDrmCallback(String str, DataSource.Factory factory) {
        this(str, false, factory);
    }

    public void clearAllKeyRequestProperties() {
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.clear();
        }
    }

    public void clearKeyRequestProperty(String str) {
        str.getClass();
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.remove(str);
        }
    }

    @Override // androidx.media3.exoplayer.drm.MediaDrmCallback
    public MediaDrmCallback.Response executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws MediaDrmCallbackException {
        String licenseServerUrl = keyRequest.getLicenseServerUrl();
        if (this.forceDefaultLicenseUrl || TextUtils.isEmpty(licenseServerUrl)) {
            licenseServerUrl = this.defaultLicenseUrl;
        }
        if (TextUtils.isEmpty(licenseServerUrl)) {
            DataSpec.Builder builder = new DataSpec.Builder();
            Uri uri = Uri.EMPTY;
            throw new MediaDrmCallbackException(builder.setUri(uri).build(), uri, t3.f14114q, 0L, new IllegalStateException("No license URL"));
        }
        HashMap map = new HashMap();
        UUID uuid2 = C.PLAYREADY_UUID;
        map.put(HttpConnection.CONTENT_TYPE, uuid2.equals(uuid) ? "text/xml" : C.CLEARKEY_UUID.equals(uuid) ? "application/json" : "application/octet-stream");
        if (uuid2.equals(uuid)) {
            map.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
        }
        synchronized (this.keyRequestProperties) {
            map.putAll(this.keyRequestProperties);
        }
        return DrmUtil.executePost(this.dataSourceFactory.createDataSource(), licenseServerUrl, keyRequest.getData(), map);
    }

    @Override // androidx.media3.exoplayer.drm.MediaDrmCallback
    public MediaDrmCallback.Response executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws MediaDrmCallbackException {
        Charset charset = StandardCharsets.UTF_8;
        byte[][] bArr = {"{\"signedRequest\":\"".getBytes(charset), provisionRequest.getData(), "\"}".getBytes(charset)};
        long length = 0;
        for (int i10 = 0; i10 < 3; i10++) {
            length += (long) bArr[i10].length;
        }
        int i11 = (int) length;
        ac.b.g(length, "the total number of elements (%s) in the arrays must fit in an int", length == ((long) i11));
        byte[] bArr2 = new byte[i11];
        int length2 = 0;
        for (int i12 = 0; i12 < 3; i12++) {
            byte[] bArr3 = bArr[i12];
            System.arraycopy(bArr3, 0, bArr2, length2, bArr3.length);
            length2 += bArr3.length;
        }
        DataSource dataSourceCreateDataSource = this.dataSourceFactory.createDataSource();
        String defaultUrl = provisionRequest.getDefaultUrl();
        String string = com.google.common.net.b.f14170i.toString();
        String strValueOf = String.valueOf(i11);
        a0.b(HttpConnection.CONTENT_TYPE, string);
        a0.b("Content-Length", strValueOf);
        return DrmUtil.executePost(dataSourceCreateDataSource, defaultUrl, bArr2, t3.j(2, new Object[]{HttpConnection.CONTENT_TYPE, string, "Content-Length", strValueOf}, null));
    }

    public void setKeyRequestProperty(String str, String str2) {
        str.getClass();
        str2.getClass();
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.put(str, str2);
        }
    }

    public HttpMediaDrmCallback(String str, boolean z, DataSource.Factory factory) {
        ac.b.j((z && TextUtils.isEmpty(str)) ? false : true);
        this.dataSourceFactory = factory;
        this.defaultLicenseUrl = str;
        this.forceDefaultLicenseUrl = z;
        this.keyRequestProperties = new HashMap();
    }
}
