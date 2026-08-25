package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\n\bg\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002R\u0018\u0010\u0004\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00038W@WX\u0096\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0003"}, d2 = {"Landroidx/compose/runtime/MutableDoubleState;", "Landroidx/compose/runtime/DoubleState;", "Landroidx/compose/runtime/MutableState;", "", "doubleValue", "getDoubleValue", "()D", "setDoubleValue", "(D)V", "value", "getValue", "()Ljava/lang/Double;", "setValue", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface MutableDoubleState extends DoubleState, MutableState<Double> {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static Double getValue(MutableDoubleState mutableDoubleState) {
            return Double.valueOf(i.a(mutableDoubleState).doubleValue());
        }

        @Deprecated
        public static void setValue(MutableDoubleState mutableDoubleState, double d4) {
            i.c(mutableDoubleState, d4);
        }
    }

    @Override // androidx.compose.runtime.DoubleState
    double getDoubleValue();

    @Override // androidx.compose.runtime.DoubleState, androidx.compose.runtime.State
    Double getValue();

    void setDoubleValue(double d4);

    void setValue(double d4);
}
