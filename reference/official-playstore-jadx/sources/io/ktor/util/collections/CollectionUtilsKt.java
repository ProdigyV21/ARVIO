package io.ktor.util.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import t7.a;
import x6.e;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\u0007\u001a-\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0001\"\u0004\b\u0000\u0010\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00040\u0006\"\u0002H\u0004H\u0007¢\u0006\u0002\u0010\u0007\u001a2\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u0002H\u00020\t\"\b\b\u0000\u0010\n*\u00020\u000b\"\b\b\u0001\u0010\u0002*\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007¨\u0006\u000e"}, d2 = {"sharedList", "", "V", "sharedListOf", "T", "values", "", "([Ljava/lang/Object;)Ljava/util/List;", "sharedMap", "", "K", "", "initialCapacity", "", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CollectionUtilsKt {
    @e
    public static final <V> List<V> sharedList() {
        return new ArrayList();
    }

    @e
    public static final <T> List<T> sharedListOf(T... tArr) {
        return a.G(Arrays.copyOf(tArr, tArr.length));
    }

    @e
    public static final <K, V> Map<K, V> sharedMap(int i10) {
        return new LinkedHashMap(i10);
    }

    public static /* synthetic */ Map sharedMap$default(int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 8;
        }
        return sharedMap(i10);
    }
}
