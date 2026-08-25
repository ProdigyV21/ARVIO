package androidx.compose.material3;

import androidx.compose.foundation.shape.CornerBasedShape;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ8\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0017"}, d2 = {"Landroidx/compose/material3/Shapes;", "", "extraSmall", "Landroidx/compose/foundation/shape/CornerBasedShape;", "small", "medium", "large", "extraLarge", "(Landroidx/compose/foundation/shape/CornerBasedShape;Landroidx/compose/foundation/shape/CornerBasedShape;Landroidx/compose/foundation/shape/CornerBasedShape;Landroidx/compose/foundation/shape/CornerBasedShape;Landroidx/compose/foundation/shape/CornerBasedShape;)V", "getExtraLarge", "()Landroidx/compose/foundation/shape/CornerBasedShape;", "getExtraSmall", "getLarge", "getMedium", "getSmall", "copy", "equals", "", "other", "hashCode", "", "toString", "", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Shapes {
    public static final int $stable = 0;
    private final CornerBasedShape extraLarge;
    private final CornerBasedShape extraSmall;
    private final CornerBasedShape large;
    private final CornerBasedShape medium;
    private final CornerBasedShape small;

    public Shapes() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ Shapes copy$default(Shapes shapes, CornerBasedShape cornerBasedShape, CornerBasedShape cornerBasedShape2, CornerBasedShape cornerBasedShape3, CornerBasedShape cornerBasedShape4, CornerBasedShape cornerBasedShape5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            cornerBasedShape = shapes.extraSmall;
        }
        if ((i10 & 2) != 0) {
            cornerBasedShape2 = shapes.small;
        }
        if ((i10 & 4) != 0) {
            cornerBasedShape3 = shapes.medium;
        }
        if ((i10 & 8) != 0) {
            cornerBasedShape4 = shapes.large;
        }
        if ((i10 & 16) != 0) {
            cornerBasedShape5 = shapes.extraLarge;
        }
        CornerBasedShape cornerBasedShape6 = cornerBasedShape5;
        CornerBasedShape cornerBasedShape7 = cornerBasedShape3;
        return shapes.copy(cornerBasedShape, cornerBasedShape2, cornerBasedShape7, cornerBasedShape4, cornerBasedShape6);
    }

    public final Shapes copy(CornerBasedShape extraSmall, CornerBasedShape small, CornerBasedShape medium, CornerBasedShape large, CornerBasedShape extraLarge) {
        return new Shapes(extraSmall, small, medium, large, extraLarge);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Shapes)) {
            return false;
        }
        Shapes shapes = (Shapes) other;
        return p.a(this.extraSmall, shapes.extraSmall) && p.a(this.small, shapes.small) && p.a(this.medium, shapes.medium) && p.a(this.large, shapes.large) && p.a(this.extraLarge, shapes.extraLarge);
    }

    public final CornerBasedShape getExtraLarge() {
        return this.extraLarge;
    }

    public final CornerBasedShape getExtraSmall() {
        return this.extraSmall;
    }

    public final CornerBasedShape getLarge() {
        return this.large;
    }

    public final CornerBasedShape getMedium() {
        return this.medium;
    }

    public final CornerBasedShape getSmall() {
        return this.small;
    }

    public int hashCode() {
        return this.extraLarge.hashCode() + ((this.large.hashCode() + ((this.medium.hashCode() + ((this.small.hashCode() + (this.extraSmall.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public String toString() {
        return "Shapes(extraSmall=" + this.extraSmall + ", small=" + this.small + ", medium=" + this.medium + ", large=" + this.large + ", extraLarge=" + this.extraLarge + ')';
    }

    public Shapes(CornerBasedShape cornerBasedShape, CornerBasedShape cornerBasedShape2, CornerBasedShape cornerBasedShape3, CornerBasedShape cornerBasedShape4, CornerBasedShape cornerBasedShape5) {
        this.extraSmall = cornerBasedShape;
        this.small = cornerBasedShape2;
        this.medium = cornerBasedShape3;
        this.large = cornerBasedShape4;
        this.extraLarge = cornerBasedShape5;
    }

    public /* synthetic */ Shapes(CornerBasedShape cornerBasedShape, CornerBasedShape cornerBasedShape2, CornerBasedShape cornerBasedShape3, CornerBasedShape cornerBasedShape4, CornerBasedShape cornerBasedShape5, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? ShapeDefaults.INSTANCE.getExtraSmall() : cornerBasedShape, (i10 & 2) != 0 ? ShapeDefaults.INSTANCE.getSmall() : cornerBasedShape2, (i10 & 4) != 0 ? ShapeDefaults.INSTANCE.getMedium() : cornerBasedShape3, (i10 & 8) != 0 ? ShapeDefaults.INSTANCE.getLarge() : cornerBasedShape4, (i10 & 16) != 0 ? ShapeDefaults.INSTANCE.getExtraLarge() : cornerBasedShape5);
    }
}
