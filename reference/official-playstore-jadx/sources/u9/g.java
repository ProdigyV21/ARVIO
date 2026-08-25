package u9;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends m implements a, b {
    @Override // u9.a
    public final Object a(Object obj, r7.a aVar) throws Throwable {
        Object objInvoke = invoke(new j(obj, aVar));
        if (objInvoke != null) {
            return objInvoke;
        }
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$CacheWithNotNullValuesBasedOnMemoizedFunction", "computeIfAbsent"));
    }
}
