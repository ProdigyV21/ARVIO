package io.ktor.util;

import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u001c\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004¨\u0006\u0005"}, d2 = {"caseInsensitiveMap", "", "", "Value", "", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CollectionsKt {
    public static final <Value> Map<String, Value> caseInsensitiveMap() {
        return new CaseInsensitiveMap();
    }
}
