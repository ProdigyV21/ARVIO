package androidx.compose.runtime.reflect;

import a0.c;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/reflect/ComposableInfo;", "", "isComposable", "", "realParamsCount", "", "changedParams", "defaultParams", "(ZIII)V", "getChangedParams", "()I", "getDefaultParams", "()Z", "getRealParamsCount", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class ComposableInfo {
    public static final int $stable = 0;
    private final int changedParams;
    private final int defaultParams;
    private final boolean isComposable;
    private final int realParamsCount;

    public ComposableInfo(boolean z, int i10, int i11, int i12) {
        this.isComposable = z;
        this.realParamsCount = i10;
        this.changedParams = i11;
        this.defaultParams = i12;
    }

    public static /* synthetic */ ComposableInfo copy$default(ComposableInfo composableInfo, boolean z, int i10, int i11, int i12, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            z = composableInfo.isComposable;
        }
        if ((i13 & 2) != 0) {
            i10 = composableInfo.realParamsCount;
        }
        if ((i13 & 4) != 0) {
            i11 = composableInfo.changedParams;
        }
        if ((i13 & 8) != 0) {
            i12 = composableInfo.defaultParams;
        }
        return composableInfo.copy(z, i10, i11, i12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsComposable() {
        return this.isComposable;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getRealParamsCount() {
        return this.realParamsCount;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getChangedParams() {
        return this.changedParams;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getDefaultParams() {
        return this.defaultParams;
    }

    public final ComposableInfo copy(boolean isComposable, int realParamsCount, int changedParams, int defaultParams) {
        return new ComposableInfo(isComposable, realParamsCount, changedParams, defaultParams);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ComposableInfo)) {
            return false;
        }
        ComposableInfo composableInfo = (ComposableInfo) other;
        return this.isComposable == composableInfo.isComposable && this.realParamsCount == composableInfo.realParamsCount && this.changedParams == composableInfo.changedParams && this.defaultParams == composableInfo.defaultParams;
    }

    public final int getChangedParams() {
        return this.changedParams;
    }

    public final int getDefaultParams() {
        return this.defaultParams;
    }

    public final int getRealParamsCount() {
        return this.realParamsCount;
    }

    public int hashCode() {
        return ((((((this.isComposable ? 1231 : 1237) * 31) + this.realParamsCount) * 31) + this.changedParams) * 31) + this.defaultParams;
    }

    public final boolean isComposable() {
        return this.isComposable;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ComposableInfo(isComposable=");
        sb2.append(this.isComposable);
        sb2.append(", realParamsCount=");
        sb2.append(this.realParamsCount);
        sb2.append(", changedParams=");
        sb2.append(this.changedParams);
        sb2.append(", defaultParams=");
        return c.o(sb2, this.defaultParams, ')');
    }
}
