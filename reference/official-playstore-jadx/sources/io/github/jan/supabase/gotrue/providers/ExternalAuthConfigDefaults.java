package io.github.jan.supabase.gotrue.providers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/github/jan/supabase/gotrue/providers/ExternalAuthConfigDefaults;", "", "()V", "queryParams", "", "", "getQueryParams", "()Ljava/util/Map;", "scopes", "", "getScopes", "()Ljava/util/List;", "gotrue-kt_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public class ExternalAuthConfigDefaults {
    private final List<String> scopes = new ArrayList();
    private final Map<String, String> queryParams = new LinkedHashMap();

    public final Map<String, String> getQueryParams() {
        return this.queryParams;
    }

    public final List<String> getScopes() {
        return this.scopes;
    }
}
