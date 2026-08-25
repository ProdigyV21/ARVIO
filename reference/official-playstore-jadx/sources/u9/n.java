package u9;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends m implements q {
    @Override // u9.m, r7.l
    public final Object invoke(Object obj) throws Throwable {
        Object objInvoke = super.invoke(obj);
        if (objInvoke != null) {
            return objInvoke;
        }
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunctionToNotNull", "invoke"));
    }
}
