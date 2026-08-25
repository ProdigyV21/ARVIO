package io.ktor.server.config;

import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.x;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u0007\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u0012\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001¨\u0006\u0006"}, d2 = {"merge", "Lio/ktor/server/config/ApplicationConfig;", "", "mergeWith", "other", "withFallback", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MergedApplicationConfigKt {
    @e
    public static final ApplicationConfig merge(List<? extends ApplicationConfig> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List of configs can not be empty");
        }
        Object objW0 = x.w0(list);
        if (!list.isEmpty()) {
            ListIterator<? extends ApplicationConfig> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                objW0 = withFallback(listIterator.previous(), (ApplicationConfig) objW0);
            }
        }
        return (ApplicationConfig) objW0;
    }

    public static final ApplicationConfig mergeWith(ApplicationConfig applicationConfig, ApplicationConfig applicationConfig2) {
        return new MergedApplicationConfig(applicationConfig2, applicationConfig);
    }

    public static final ApplicationConfig withFallback(ApplicationConfig applicationConfig, ApplicationConfig applicationConfig2) {
        return new MergedApplicationConfig(applicationConfig, applicationConfig2);
    }
}
