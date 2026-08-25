package androidx.compose.ui.graphics;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B%\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\n\u0010\u0007\u001a\u00060\u0005j\u0002`\u0006¢\u0006\u0004\b\b\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\nJ\u001a\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0096\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0004\u001a\u00020\u00028\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001a"}, d2 = {"Landroidx/compose/ui/graphics/LightingColorFilter;", "Landroidx/compose/ui/graphics/ColorFilter;", "Landroidx/compose/ui/graphics/Color;", "multiply", "add", "Landroid/graphics/ColorFilter;", "Landroidx/compose/ui/graphics/NativeColorFilter;", "nativeColorFilter", "<init>", "(JJLandroid/graphics/ColorFilter;Lkotlin/jvm/internal/h;)V", "(JJLkotlin/jvm/internal/h;)V", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "", "toString", "()Ljava/lang/String;", "J", "getMultiply-0d7_KjU", "()J", "getAdd-0d7_KjU", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LightingColorFilter extends ColorFilter {
    private final long add;
    private final long multiply;

    public /* synthetic */ LightingColorFilter(long j10, long j11, android.graphics.ColorFilter colorFilter, kotlin.jvm.internal.h hVar) {
        this(j10, j11, colorFilter);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LightingColorFilter)) {
            return false;
        }
        LightingColorFilter lightingColorFilter = (LightingColorFilter) other;
        return Color.m3473equalsimpl0(this.multiply, lightingColorFilter.multiply) && Color.m3473equalsimpl0(this.add, lightingColorFilter.add);
    }

    /* JADX INFO: renamed from: getAdd-0d7_KjU, reason: not valid java name and from getter */
    public final long getAdd() {
        return this.add;
    }

    /* JADX INFO: renamed from: getMultiply-0d7_KjU, reason: not valid java name and from getter */
    public final long getMultiply() {
        return this.multiply;
    }

    public int hashCode() {
        return Color.m3479hashCodeimpl(this.add) + (Color.m3479hashCodeimpl(this.multiply) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("LightingColorFilter(multiply=");
        androidx.compose.foundation.c.x(this.multiply, ", add=", sb2);
        sb2.append((Object) Color.m3480toStringimpl(this.add));
        sb2.append(')');
        return sb2.toString();
    }

    public /* synthetic */ LightingColorFilter(long j10, long j11, kotlin.jvm.internal.h hVar) {
        this(j10, j11);
    }

    private LightingColorFilter(long j10, long j11, android.graphics.ColorFilter colorFilter) {
        super(colorFilter);
        this.multiply = j10;
        this.add = j11;
    }

    private LightingColorFilter(long j10, long j11) {
        this(j10, j11, AndroidColorFilter_androidKt.m3343actualLightingColorFilterOWjLjI(j10, j11), null);
    }
}
