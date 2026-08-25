package j$.util.concurrent;

import j$.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/* JADX INFO: renamed from: j$.util.concurrent.ConcurrentMap$-EL, reason: invalid class name */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ConcurrentMap$EL {
    public static Object computeIfAbsent(ConcurrentMap concurrentMap, Object obj, Function function) {
        Object objApply;
        if (concurrentMap instanceof u) {
            return ((u) concurrentMap).computeIfAbsent(obj, function);
        }
        Objects.requireNonNull(function);
        Object obj2 = concurrentMap.get(obj);
        if (obj2 != null || (objApply = function.apply(obj)) == null) {
            return obj2;
        }
        Object objPutIfAbsent = concurrentMap.putIfAbsent(obj, objApply);
        return objPutIfAbsent == null ? objApply : objPutIfAbsent;
    }

    public static Object computeIfPresent(ConcurrentMap concurrentMap, Object obj, BiFunction biFunction) {
        Object objApply;
        if (concurrentMap instanceof u) {
            return ((u) concurrentMap).computeIfPresent(obj, biFunction);
        }
        Objects.requireNonNull(biFunction);
        while (true) {
            Object obj2 = concurrentMap.get(obj);
            if (obj2 == null) {
                return null;
            }
            objApply = biFunction.apply(obj, obj2);
            if (objApply == null) {
                if (concurrentMap.remove(obj, obj2)) {
                    break;
                }
            } else if (concurrentMap.replace(obj, obj2, objApply)) {
                break;
            }
        }
        return objApply;
    }
}
