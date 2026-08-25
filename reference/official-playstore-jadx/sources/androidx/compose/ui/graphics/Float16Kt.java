package androidx.compose.ui.graphics;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\"\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\b"}, d2 = {"max", "Landroidx/compose/ui/graphics/Float16;", "x", "y", "max-AoSsdG0", "(SS)S", "min", "min-AoSsdG0", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Float16Kt {
    /* JADX INFO: renamed from: max-AoSsdG0, reason: not valid java name */
    public static final short m3618maxAoSsdG0(short s10, short s11) {
        return (Float16.m3591isNaNimpl(s10) || Float16.m3591isNaNimpl(s11)) ? Float16.INSTANCE.m3613getNaNslo4al4() : Float16.m3578compareTo41bOqos(s10, s11) >= 0 ? s10 : s11;
    }

    /* JADX INFO: renamed from: min-AoSsdG0, reason: not valid java name */
    public static final short m3619minAoSsdG0(short s10, short s11) {
        return (Float16.m3591isNaNimpl(s10) || Float16.m3591isNaNimpl(s11)) ? Float16.INSTANCE.m3613getNaNslo4al4() : Float16.m3578compareTo41bOqos(s10, s11) <= 0 ? s10 : s11;
    }
}
