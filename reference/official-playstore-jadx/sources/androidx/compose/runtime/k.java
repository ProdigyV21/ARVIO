package androidx.compose.runtime;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class k {
    public static Integer a(MutableIntState mutableIntState) {
        return Integer.valueOf(mutableIntState.getIntValue());
    }

    public static void c(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
    }
}
