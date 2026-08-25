package androidx.compose.runtime;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class i {
    public static Double a(MutableDoubleState mutableDoubleState) {
        return Double.valueOf(mutableDoubleState.getDoubleValue());
    }

    public static void c(MutableDoubleState mutableDoubleState, double d4) {
        mutableDoubleState.setDoubleValue(d4);
    }
}
