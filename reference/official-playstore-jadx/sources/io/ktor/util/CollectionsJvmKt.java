package io.ktor.util;

import java.util.Collections;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001¨\u0006\u0003"}, d2 = {"unmodifiable", "", "T", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CollectionsJvmKt {
    public static final <T> Set<T> unmodifiable(Set<? extends T> set) {
        return Collections.unmodifiableSet(set);
    }
}
