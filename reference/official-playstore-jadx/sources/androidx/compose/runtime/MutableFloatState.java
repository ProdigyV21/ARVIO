package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\n\bg\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002R\u0018\u0010\u0004\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00038W@WX\u0096\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0003"}, d2 = {"Landroidx/compose/runtime/MutableFloatState;", "Landroidx/compose/runtime/FloatState;", "Landroidx/compose/runtime/MutableState;", "", "floatValue", "getFloatValue", "()F", "setFloatValue", "(F)V", "value", "getValue", "()Ljava/lang/Float;", "setValue", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface MutableFloatState extends FloatState, MutableState<Float> {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static Float getValue(MutableFloatState mutableFloatState) {
            return Float.valueOf(j.a(mutableFloatState).floatValue());
        }

        @Deprecated
        public static void setValue(MutableFloatState mutableFloatState, float f10) {
            j.c(mutableFloatState, f10);
        }
    }

    @Override // androidx.compose.runtime.FloatState
    float getFloatValue();

    @Override // androidx.compose.runtime.FloatState, androidx.compose.runtime.State
    Float getValue();

    void setFloatValue(float f10);

    void setValue(float f10);
}
