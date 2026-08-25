package androidx.media3.exoplayer.drm;

import android.net.Uri;
import androidx.media3.common.MediaItem;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultHttpDataSource;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import com.google.common.collect.l1;
import com.google.common.collect.o4;
import com.google.common.collect.w1;
import java.util.Map;
import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class DefaultDrmSessionManagerProvider implements DrmSessionManagerProvider {
    private MediaItem.DrmConfiguration drmConfiguration;
    private DataSource.Factory drmHttpDataSourceFactory;
    private LoadErrorHandlingPolicy drmLoadErrorHandlingPolicy;
    private final Object lock = new Object();
    private DrmSessionManager manager;
    private String userAgent;

    private DrmSessionManager createManager(MediaItem.DrmConfiguration drmConfiguration) {
        DataSource.Factory userAgent = this.drmHttpDataSourceFactory;
        if (userAgent == null) {
            userAgent = new DefaultHttpDataSource.Factory().setUserAgent(this.userAgent);
        }
        Uri uri = drmConfiguration.licenseUri;
        HttpMediaDrmCallback httpMediaDrmCallback = new HttpMediaDrmCallback(uri == null ? null : uri.toString(), drmConfiguration.forceDefaultLicenseUri, userAgent);
        l1 l1Var = drmConfiguration.licenseRequestHeaders;
        w1 w1VarC = l1Var.f14047i;
        if (w1VarC == null) {
            w1VarC = l1Var.c();
            l1Var.f14047i = w1VarC;
        }
        o4 it = w1VarC.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            httpMediaDrmCallback.setKeyRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        DefaultDrmSessionManager.Builder useDrmSessionsForClearContent = new DefaultDrmSessionManager.Builder().setUuidAndExoMediaDrmProvider(drmConfiguration.scheme, FrameworkMediaDrm.DEFAULT_PROVIDER).setMultiSession(drmConfiguration.multiSession).setPlayClearSamplesWithoutKeys(drmConfiguration.playClearContentWithoutKey).setUseDrmSessionsForClearContent(f0.O(drmConfiguration.forcedSessionTrackTypes));
        LoadErrorHandlingPolicy loadErrorHandlingPolicy = this.drmLoadErrorHandlingPolicy;
        if (loadErrorHandlingPolicy != null) {
            useDrmSessionsForClearContent.setLoadErrorHandlingPolicy(loadErrorHandlingPolicy);
        }
        DefaultDrmSessionManager defaultDrmSessionManagerBuild = useDrmSessionsForClearContent.build(httpMediaDrmCallback);
        defaultDrmSessionManagerBuild.setMode(0, drmConfiguration.getKeySetId());
        return defaultDrmSessionManagerBuild;
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionManagerProvider
    public DrmSessionManager get(MediaItem mediaItem) {
        DrmSessionManager drmSessionManager;
        mediaItem.localConfiguration.getClass();
        MediaItem.DrmConfiguration drmConfiguration = mediaItem.localConfiguration.drmConfiguration;
        if (drmConfiguration == null) {
            return DrmSessionManager.DRM_UNSUPPORTED;
        }
        synchronized (this.lock) {
            try {
                if (!drmConfiguration.equals(this.drmConfiguration)) {
                    this.drmConfiguration = drmConfiguration;
                    this.manager = createManager(drmConfiguration);
                }
                drmSessionManager = this.manager;
                drmSessionManager.getClass();
            } catch (Throwable th) {
                throw th;
            }
        }
        return drmSessionManager;
    }

    public void setDrmHttpDataSourceFactory(DataSource.Factory factory) {
        this.drmHttpDataSourceFactory = factory;
    }

    public void setDrmLoadErrorHandlingPolicy(LoadErrorHandlingPolicy loadErrorHandlingPolicy) {
        this.drmLoadErrorHandlingPolicy = loadErrorHandlingPolicy;
    }

    @Deprecated
    public void setDrmUserAgent(String str) {
        this.userAgent = str;
    }
}
