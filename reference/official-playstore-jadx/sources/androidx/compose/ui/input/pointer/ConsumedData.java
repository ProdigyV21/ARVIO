package androidx.compose.ui.input.pointer;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R$\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\u0007\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000b¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/input/pointer/ConsumedData;", "", "positionChange", "", "downChange", "(ZZ)V", "getDownChange$annotations", "()V", "getDownChange", "()Z", "setDownChange", "(Z)V", "getPositionChange$annotations", "getPositionChange", "setPositionChange", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@e
public final class ConsumedData {
    public static final int $stable = 8;
    private boolean downChange;
    private boolean positionChange;

    /* JADX WARN: Illegal instructions before constructor call */
    public ConsumedData() {
        boolean z = false;
        this(z, z, 3, null);
    }

    @e
    public static /* synthetic */ void getDownChange$annotations() {
    }

    @e
    public static /* synthetic */ void getPositionChange$annotations() {
    }

    public final boolean getDownChange() {
        return this.downChange;
    }

    public final boolean getPositionChange() {
        return this.positionChange;
    }

    public final void setDownChange(boolean z) {
        this.downChange = z;
    }

    public final void setPositionChange(boolean z) {
        this.positionChange = z;
    }

    public ConsumedData(boolean z, boolean z5) {
        this.positionChange = z;
        this.downChange = z5;
    }

    public /* synthetic */ ConsumedData(boolean z, boolean z5, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z, (i10 & 2) != 0 ? false : z5);
    }
}
