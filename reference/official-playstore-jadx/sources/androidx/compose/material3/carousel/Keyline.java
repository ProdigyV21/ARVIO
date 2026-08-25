package androidx.compose.material3.carousel;

import a0.c;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003JO\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00072\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r¨\u0006 "}, d2 = {"Landroidx/compose/material3/carousel/Keyline;", "", ContentDisposition.Parameters.Size, "", "offset", "unadjustedOffset", "isFocal", "", "isAnchor", "isPivot", "cutoff", "(FFFZZZF)V", "getCutoff", "()F", "()Z", "getOffset", "getSize", "getUnadjustedOffset", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class Keyline {
    public static final int $stable = 0;
    private final float cutoff;
    private final boolean isAnchor;
    private final boolean isFocal;
    private final boolean isPivot;
    private final float offset;
    private final float size;
    private final float unadjustedOffset;

    public Keyline(float f10, float f11, float f12, boolean z, boolean z5, boolean z10, float f13) {
        this.size = f10;
        this.offset = f11;
        this.unadjustedOffset = f12;
        this.isFocal = z;
        this.isAnchor = z5;
        this.isPivot = z10;
        this.cutoff = f13;
    }

    public static /* synthetic */ Keyline copy$default(Keyline keyline, float f10, float f11, float f12, boolean z, boolean z5, boolean z10, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = keyline.size;
        }
        if ((i10 & 2) != 0) {
            f11 = keyline.offset;
        }
        if ((i10 & 4) != 0) {
            f12 = keyline.unadjustedOffset;
        }
        if ((i10 & 8) != 0) {
            z = keyline.isFocal;
        }
        if ((i10 & 16) != 0) {
            z5 = keyline.isAnchor;
        }
        if ((i10 & 32) != 0) {
            z10 = keyline.isPivot;
        }
        if ((i10 & 64) != 0) {
            f13 = keyline.cutoff;
        }
        boolean z11 = z10;
        float f14 = f13;
        boolean z12 = z5;
        float f15 = f12;
        return keyline.copy(f10, f11, f15, z, z12, z11, f14);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getOffset() {
        return this.offset;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getUnadjustedOffset() {
        return this.unadjustedOffset;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getIsFocal() {
        return this.isFocal;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsAnchor() {
        return this.isAnchor;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsPivot() {
        return this.isPivot;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getCutoff() {
        return this.cutoff;
    }

    public final Keyline copy(float size, float offset, float unadjustedOffset, boolean isFocal, boolean isAnchor, boolean isPivot, float cutoff) {
        return new Keyline(size, offset, unadjustedOffset, isFocal, isAnchor, isPivot, cutoff);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Keyline)) {
            return false;
        }
        Keyline keyline = (Keyline) other;
        return Float.compare(this.size, keyline.size) == 0 && Float.compare(this.offset, keyline.offset) == 0 && Float.compare(this.unadjustedOffset, keyline.unadjustedOffset) == 0 && this.isFocal == keyline.isFocal && this.isAnchor == keyline.isAnchor && this.isPivot == keyline.isPivot && Float.compare(this.cutoff, keyline.cutoff) == 0;
    }

    public final float getCutoff() {
        return this.cutoff;
    }

    public final float getOffset() {
        return this.offset;
    }

    public final float getSize() {
        return this.size;
    }

    public final float getUnadjustedOffset() {
        return this.unadjustedOffset;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.cutoff) + ((((((c.b(this.unadjustedOffset, c.b(this.offset, Float.floatToIntBits(this.size) * 31, 31), 31) + (this.isFocal ? 1231 : 1237)) * 31) + (this.isAnchor ? 1231 : 1237)) * 31) + (this.isPivot ? 1231 : 1237)) * 31);
    }

    public final boolean isAnchor() {
        return this.isAnchor;
    }

    public final boolean isFocal() {
        return this.isFocal;
    }

    public final boolean isPivot() {
        return this.isPivot;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Keyline(size=");
        sb2.append(this.size);
        sb2.append(", offset=");
        sb2.append(this.offset);
        sb2.append(", unadjustedOffset=");
        sb2.append(this.unadjustedOffset);
        sb2.append(", isFocal=");
        sb2.append(this.isFocal);
        sb2.append(", isAnchor=");
        sb2.append(this.isAnchor);
        sb2.append(", isPivot=");
        sb2.append(this.isPivot);
        sb2.append(", cutoff=");
        return c.n(sb2, this.cutoff, ')');
    }
}
