package androidx.media3.exoplayer.drm;

import android.media.DeniedByServerException;
import android.media.MediaCryptoException;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.os.PersistableBundle;
import androidx.media3.common.DrmInitData;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.exoplayer.analytics.PlayerId;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public interface ExoMediaDrm {
    public static final int EVENT_KEY_EXPIRED = 3;
    public static final int EVENT_KEY_REQUIRED = 2;
    public static final int EVENT_PROVISION_REQUIRED = 1;
    public static final int KEY_TYPE_OFFLINE = 2;
    public static final int KEY_TYPE_RELEASE = 3;
    public static final int KEY_TYPE_STREAMING = 1;

    public static final class AppManagedProvider implements Provider {
        private final ExoMediaDrm exoMediaDrm;

        public AppManagedProvider(ExoMediaDrm exoMediaDrm) {
            this.exoMediaDrm = exoMediaDrm;
        }

        @Override // androidx.media3.exoplayer.drm.ExoMediaDrm.Provider
        public ExoMediaDrm acquireExoMediaDrm(UUID uuid) {
            this.exoMediaDrm.acquire();
            return this.exoMediaDrm;
        }
    }

    public static final class KeyRequest {
        public static final int REQUEST_TYPE_INITIAL = 0;
        public static final int REQUEST_TYPE_NONE = 3;
        public static final int REQUEST_TYPE_RELEASE = 2;
        public static final int REQUEST_TYPE_RENEWAL = 1;
        public static final int REQUEST_TYPE_UNKNOWN = Integer.MIN_VALUE;
        public static final int REQUEST_TYPE_UPDATE = 4;
        private final byte[] data;
        private final String licenseServerUrl;
        private final int requestType;

        @Target({ElementType.TYPE_USE})
        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface RequestType {
        }

        public KeyRequest(byte[] bArr, String str) {
            this(bArr, str, Integer.MIN_VALUE);
        }

        public byte[] getData() {
            return this.data;
        }

        public String getLicenseServerUrl() {
            return this.licenseServerUrl;
        }

        public int getRequestType() {
            return this.requestType;
        }

        public KeyRequest(byte[] bArr, String str, int i10) {
            this.data = bArr;
            this.licenseServerUrl = str;
            this.requestType = i10;
        }
    }

    public static final class KeyStatus {
        private final byte[] keyId;
        private final int statusCode;

        public KeyStatus(int i10, byte[] bArr) {
            this.statusCode = i10;
            this.keyId = bArr;
        }

        public byte[] getKeyId() {
            return this.keyId;
        }

        public int getStatusCode() {
            return this.statusCode;
        }
    }

    public interface OnEventListener {
        void onEvent(ExoMediaDrm exoMediaDrm, byte[] bArr, int i10, int i11, byte[] bArr2);
    }

    public interface OnExpirationUpdateListener {
        void onExpirationUpdate(ExoMediaDrm exoMediaDrm, byte[] bArr, long j10);
    }

    public interface OnKeyStatusChangeListener {
        void onKeyStatusChange(ExoMediaDrm exoMediaDrm, byte[] bArr, List<KeyStatus> list, boolean z);
    }

    public interface Provider {
        ExoMediaDrm acquireExoMediaDrm(UUID uuid);
    }

    public static final class ProvisionRequest {
        private final byte[] data;
        private final String defaultUrl;

        public ProvisionRequest(byte[] bArr, String str) {
            this.data = bArr;
            this.defaultUrl = str;
        }

        public byte[] getData() {
            return this.data;
        }

        public String getDefaultUrl() {
            return this.defaultUrl;
        }
    }

    void acquire();

    void closeSession(byte[] bArr);

    CryptoConfig createCryptoConfig(byte[] bArr) throws MediaCryptoException;

    int getCryptoType();

    KeyRequest getKeyRequest(byte[] bArr, List<DrmInitData.SchemeData> list, int i10, HashMap<String, String> map) throws NotProvisionedException;

    PersistableBundle getMetrics();

    List<byte[]> getOfflineLicenseKeySetIds();

    byte[] getPropertyByteArray(String str);

    String getPropertyString(String str);

    ProvisionRequest getProvisionRequest();

    byte[] openSession() throws MediaDrmException;

    byte[] provideKeyResponse(byte[] bArr, byte[] bArr2) throws DeniedByServerException, NotProvisionedException;

    void provideProvisionResponse(byte[] bArr) throws DeniedByServerException;

    Map<String, String> queryKeyStatus(byte[] bArr);

    void release();

    void removeOfflineLicense(byte[] bArr);

    boolean requiresSecureDecoder(byte[] bArr, String str);

    void restoreKeys(byte[] bArr, byte[] bArr2);

    void setOnEventListener(OnEventListener onEventListener);

    void setOnExpirationUpdateListener(OnExpirationUpdateListener onExpirationUpdateListener);

    void setOnKeyStatusChangeListener(OnKeyStatusChangeListener onKeyStatusChangeListener);

    void setPlayerIdForSession(byte[] bArr, PlayerId playerId);

    void setPropertyByteArray(String str, byte[] bArr);

    void setPropertyString(String str, String str2);
}
