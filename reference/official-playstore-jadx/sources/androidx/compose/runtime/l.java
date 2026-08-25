package androidx.compose.runtime;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class l {
    public static Long a(MutableLongState mutableLongState) {
        return Long.valueOf(mutableLongState.getLongValue());
    }

    public static void c(MutableLongState mutableLongState, long j10) {
        mutableLongState.setLongValue(j10);
    }
}
