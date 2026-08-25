package io.ktor.server.config;

import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\b\u0010\u0005\u001a\u00020\u0004H&¨\u0006\u0006"}, d2 = {"Lio/ktor/server/config/ApplicationConfigValue;", "", "getList", "", "", "getString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ApplicationConfigValue {
    List<String> getList();

    String getString();
}
