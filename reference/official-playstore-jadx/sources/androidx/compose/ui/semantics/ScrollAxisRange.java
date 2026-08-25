package androidx.compose.ui.semantics;

import a0.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B-\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\r\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/compose/ui/semantics/ScrollAxisRange;", "", "Lkotlin/Function0;", "", "value", "maxValue", "", "reverseScrolling", "<init>", "(Lr7/a;Lr7/a;Z)V", "", "toString", "()Ljava/lang/String;", "Lr7/a;", "getValue", "()Lr7/a;", "getMaxValue", "Z", "getReverseScrolling", "()Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScrollAxisRange {
    public static final int $stable = 0;
    private final r7.a<Float> maxValue;
    private final boolean reverseScrolling;
    private final r7.a<Float> value;

    public ScrollAxisRange(r7.a<Float> aVar, r7.a<Float> aVar2, boolean z) {
        this.value = aVar;
        this.maxValue = aVar2;
        this.reverseScrolling = z;
    }

    public final r7.a<Float> getMaxValue() {
        return this.maxValue;
    }

    public final boolean getReverseScrolling() {
        return this.reverseScrolling;
    }

    public final r7.a<Float> getValue() {
        return this.value;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ScrollAxisRange(value=");
        sb2.append(((Number) this.value.invoke()).floatValue());
        sb2.append(", maxValue=");
        sb2.append(((Number) this.maxValue.invoke()).floatValue());
        sb2.append(", reverseScrolling=");
        return c.r(sb2, this.reverseScrolling, ')');
    }

    public /* synthetic */ ScrollAxisRange(r7.a aVar, r7.a aVar2, boolean z, int i10, h hVar) {
        this(aVar, aVar2, (i10 & 4) != 0 ? false : z);
    }
}
