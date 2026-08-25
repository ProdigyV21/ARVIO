package androidx.media3.exoplayer.drm;

import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.NotProvisionedException;
import android.os.Build;
import android.os.SystemClock;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceInputStream;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.drm.MediaDrmCallback;
import androidx.media3.exoplayer.source.LoadEventInfo;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class DrmUtil {
    public static final int ERROR_SOURCE_EXO_MEDIA_DRM = 1;
    public static final int ERROR_SOURCE_LICENSE_ACQUISITION = 2;
    public static final int ERROR_SOURCE_PROVISIONING = 3;
    private static final int MAX_MANUAL_REDIRECTS = 5;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorSource {
    }

    private DrmUtil() {
    }

    public static MediaDrmCallback.Response executePost(DataSource dataSource, String str, byte[] bArr, Map<String, String> map) throws Throwable {
        DataSpec dataSpec;
        DataSourceInputStream dataSourceInputStream;
        StatsDataSource statsDataSource = new StatsDataSource(dataSource);
        DataSpec dataSpecBuild = new DataSpec.Builder().setUri(str).setHttpRequestHeaders(map).setHttpMethod(2).setHttpBody(bArr).setFlags(1).build();
        int i10 = 0;
        DataSpec dataSpecBuild2 = dataSpecBuild;
        while (true) {
            try {
                DataSourceInputStream dataSourceInputStream2 = new DataSourceInputStream(statsDataSource, dataSpecBuild2);
                try {
                    try {
                        dataSpec = dataSpecBuild;
                        dataSourceInputStream = dataSourceInputStream2;
                        try {
                            MediaDrmCallback.Response responseBuild = new MediaDrmCallback.Response.Builder(com.google.common.io.i.b(dataSourceInputStream2)).setLoadEventInfo(new LoadEventInfo(-1L, dataSpec, statsDataSource.getLastOpenedUri(), statsDataSource.getLastResponseHeaders(), SystemClock.elapsedRealtime(), 0L, r0.length)).build();
                            Util.closeQuietly(dataSourceInputStream);
                            return responseBuild;
                        } catch (HttpDataSource.InvalidResponseCodeException e5) {
                            e = e5;
                            try {
                                String redirectUrl = getRedirectUrl(e, i10);
                                if (redirectUrl == null) {
                                    throw e;
                                }
                                i10++;
                                dataSpecBuild2 = dataSpecBuild2.buildUpon().setUri(redirectUrl).build();
                                try {
                                    Util.closeQuietly(dataSourceInputStream);
                                    dataSpecBuild = dataSpec;
                                } catch (Exception e6) {
                                    e = e6;
                                    throw new MediaDrmCallbackException(dataSpec, statsDataSource.getLastOpenedUri(), statsDataSource.getResponseHeaders(), statsDataSource.getBytesRead(), e);
                                }
                            } catch (Throwable th) {
                                th = th;
                                Util.closeQuietly(dataSourceInputStream);
                                throw th;
                            }
                        }
                    } catch (HttpDataSource.InvalidResponseCodeException e10) {
                        e = e10;
                        dataSpec = dataSpecBuild;
                        dataSourceInputStream = dataSourceInputStream2;
                    } catch (Throwable th2) {
                        th = th2;
                        dataSourceInputStream = dataSourceInputStream2;
                        Util.closeQuietly(dataSourceInputStream);
                        throw th;
                    }
                } catch (HttpDataSource.InvalidResponseCodeException e11) {
                    e = e11;
                    dataSourceInputStream = dataSourceInputStream2;
                    dataSpec = dataSpecBuild;
                } catch (Throwable th3) {
                    th = th3;
                    dataSourceInputStream = dataSourceInputStream2;
                }
                dataSpecBuild = dataSpec;
            } catch (Exception e12) {
                e = e12;
                dataSpec = dataSpecBuild;
            }
        }
    }

    public static int getErrorCodeForMediaDrmException(Throwable th, int i10) {
        if (th instanceof MediaDrm.MediaDrmStateException) {
            return Util.getErrorCodeForMediaDrmErrorCode(Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaDrm.MediaDrmStateException) th).getDiagnosticInfo()));
        }
        if (th instanceof MediaDrmResetException) {
            return PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR;
        }
        if ((th instanceof NotProvisionedException) || isFailureToConstructNotProvisionedException(th)) {
            return PlaybackException.ERROR_CODE_DRM_PROVISIONING_FAILED;
        }
        if (th instanceof DeniedByServerException) {
            return PlaybackException.ERROR_CODE_DRM_DEVICE_REVOKED;
        }
        if (th instanceof UnsupportedDrmException) {
            return PlaybackException.ERROR_CODE_DRM_SCHEME_UNSUPPORTED;
        }
        if (th instanceof DefaultDrmSessionManager.MissingSchemeDataException) {
            return PlaybackException.ERROR_CODE_DRM_CONTENT_ERROR;
        }
        if (th instanceof KeysExpiredException) {
            return PlaybackException.ERROR_CODE_DRM_LICENSE_EXPIRED;
        }
        if (i10 == 1) {
            return PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR;
        }
        if (i10 == 2) {
            return PlaybackException.ERROR_CODE_DRM_LICENSE_ACQUISITION_FAILED;
        }
        if (i10 == 3) {
            return PlaybackException.ERROR_CODE_DRM_PROVISIONING_FAILED;
        }
        throw new IllegalArgumentException();
    }

    private static String getRedirectUrl(HttpDataSource.InvalidResponseCodeException invalidResponseCodeException, int i10) {
        Map<String, List<String>> map;
        List<String> list;
        int i11 = invalidResponseCodeException.responseCode;
        if ((i11 != 307 && i11 != 308) || i10 >= 5 || (map = invalidResponseCodeException.headerFields) == null || (list = map.get("Location")) == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static boolean isFailureToConstructNotProvisionedException(Throwable th) {
        return Build.VERSION.SDK_INT == 34 && (th instanceof NoSuchMethodError) && th.getMessage() != null && th.getMessage().contains("Landroid/media/NotProvisionedException;.<init>(");
    }

    public static boolean isFailureToConstructResourceBusyException(Throwable th) {
        return Build.VERSION.SDK_INT == 34 && (th instanceof NoSuchMethodError) && th.getMessage() != null && th.getMessage().contains("Landroid/media/ResourceBusyException;.<init>(");
    }
}
