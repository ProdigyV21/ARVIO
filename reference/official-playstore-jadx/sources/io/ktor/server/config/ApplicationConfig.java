package io.ktor.server.config;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004H&J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u00062\u0006\u0010\u0003\u001a\u00020\u0004H&J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H&J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0003\u001a\u00020\u0004H&J\u0016\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\rH&¨\u0006\u000e"}, d2 = {"Lio/ktor/server/config/ApplicationConfig;", "", "config", "path", "", "configList", "", UserMetadata.KEYDATA_FILENAME, "", "property", "Lio/ktor/server/config/ApplicationConfigValue;", "propertyOrNull", "toMap", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ApplicationConfig {
    ApplicationConfig config(String path);

    List<ApplicationConfig> configList(String path);

    Set<String> keys();

    ApplicationConfigValue property(String path);

    ApplicationConfigValue propertyOrNull(String path);

    Map<String, Object> toMap();
}
