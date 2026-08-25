package androidx.compose.foundation;

import android.os.Build;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0094\u0001\u0010\u0014\u001a\u00020\u0000*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001¢\u0006\u0002\b\u00042\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00012\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\u0010ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a¬\u0001\u0010\u0014\u001a\u00020\u0000*\u00020\u00002\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u00042\u001b\b\u0002\u0010\u0006\u001a\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001¢\u0006\u0002\b\u00042\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u00012\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\u00102\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\u00102\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u0019\u0010\u001d\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u0001¢\u0006\u0004\b\u001d\u0010\u001e\"&\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030 0\u001f8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006%"}, d2 = {"Landroidx/compose/ui/Modifier;", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/geometry/Offset;", "Lx6/n;", "sourceCenter", "magnifierCenter", "Landroidx/compose/ui/unit/DpSize;", "Lx6/t0;", "onSizeChanged", "", "zoom", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/unit/Dp;", "cornerRadius", "elevation", "", "clip", "magnifier-UpNRX3w", "(Landroidx/compose/ui/Modifier;Lr7/l;Lr7/l;Lr7/l;FJFFZ)Landroidx/compose/ui/Modifier;", "magnifier", "useTextDefault", "clippingEnabled", "Landroidx/compose/foundation/PlatformMagnifierFactory;", "platformMagnifierFactory", "magnifier-jPUL71Q", "(Landroidx/compose/ui/Modifier;Lr7/l;Lr7/l;Lr7/l;FZJFFZLandroidx/compose/foundation/PlatformMagnifierFactory;)Landroidx/compose/ui/Modifier;", "", "sdkVersion", "isPlatformMagnifierSupported", "(I)Z", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "Lkotlin/Function0;", "MagnifierPositionInRoot", "Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "getMagnifierPositionInRoot", "()Landroidx/compose/ui/semantics/SemanticsPropertyKey;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Magnifier_androidKt {
    private static final SemanticsPropertyKey<r7.a<Offset>> MagnifierPositionInRoot = new SemanticsPropertyKey<>("MagnifierPositionInRoot", null, 2, null);

    public static final SemanticsPropertyKey<r7.a<Offset>> getMagnifierPositionInRoot() {
        return MagnifierPositionInRoot;
    }

    public static final boolean isPlatformMagnifierSupported(int i10) {
        return i10 >= 28;
    }

    public static /* synthetic */ boolean isPlatformMagnifierSupported$default(int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = Build.VERSION.SDK_INT;
        }
        return isPlatformMagnifierSupported(i10);
    }

    /* JADX INFO: renamed from: magnifier-UpNRX3w, reason: not valid java name */
    public static final Modifier m250magnifierUpNRX3w(Modifier modifier, l<? super Density, Offset> lVar, l<? super Density, Offset> lVar2, l<? super DpSize, t0> lVar3, float f10, long j10, float f11, float f12, boolean z) {
        return m253magnifierjPUL71Q$default(modifier, lVar, lVar2, lVar3, f10, false, j10, f11, f12, z, null, 512, null);
    }

    /* JADX INFO: renamed from: magnifier-UpNRX3w$default, reason: not valid java name */
    public static /* synthetic */ Modifier m251magnifierUpNRX3w$default(Modifier modifier, l lVar, l lVar2, l lVar3, float f10, long j10, float f11, float f12, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar2 = null;
        }
        if ((i10 & 4) != 0) {
            lVar3 = null;
        }
        if ((i10 & 8) != 0) {
            f10 = Float.NaN;
        }
        if ((i10 & 16) != 0) {
            j10 = DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ();
        }
        if ((i10 & 32) != 0) {
            f11 = Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM();
        }
        if ((i10 & 64) != 0) {
            f12 = Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM();
        }
        if ((i10 & 128) != 0) {
            z = true;
        }
        return m250magnifierUpNRX3w(modifier, lVar, lVar2, lVar3, f10, j10, f11, f12, z);
    }

    /* JADX INFO: renamed from: magnifier-jPUL71Q, reason: not valid java name */
    public static final Modifier m252magnifierjPUL71Q(Modifier modifier, l<? super Density, Offset> lVar, l<? super Density, Offset> lVar2, l<? super DpSize, t0> lVar3, float f10, boolean z, long j10, float f11, float f12, boolean z5, PlatformMagnifierFactory platformMagnifierFactory) {
        if (isPlatformMagnifierSupported$default(0, 1, null)) {
            return modifier.then(new MagnifierElement(lVar, lVar2, lVar3, f10, z, j10, f11, f12, z5, platformMagnifierFactory == null ? PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform() : platformMagnifierFactory, null));
        }
        return InspectableValueKt.inspectableWrapper(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Magnifier_androidKt$magnifierjPUL71Q$$inlined$debugInspectorInfo$1(lVar, lVar2, f10, j10, f11, f12, z5) : InspectableValueKt.getNoInspectorInfo(), Modifier.INSTANCE);
    }

    /* JADX INFO: renamed from: magnifier-jPUL71Q$default, reason: not valid java name */
    public static /* synthetic */ Modifier m253magnifierjPUL71Q$default(Modifier modifier, l lVar, l lVar2, l lVar3, float f10, boolean z, long j10, float f11, float f12, boolean z5, PlatformMagnifierFactory platformMagnifierFactory, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            lVar2 = null;
        }
        if ((i10 & 4) != 0) {
            lVar3 = null;
        }
        if ((i10 & 8) != 0) {
            f10 = Float.NaN;
        }
        if ((i10 & 16) != 0) {
            z = false;
        }
        if ((i10 & 32) != 0) {
            j10 = DpSize.INSTANCE.m5785getUnspecifiedMYxV2XQ();
        }
        if ((i10 & 64) != 0) {
            f11 = Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM();
        }
        if ((i10 & 128) != 0) {
            f12 = Dp.INSTANCE.m5698getUnspecifiedD9Ej5fM();
        }
        if ((i10 & 256) != 0) {
            z5 = true;
        }
        if ((i10 & 512) != 0) {
            platformMagnifierFactory = null;
        }
        return m252magnifierjPUL71Q(modifier, lVar, lVar2, lVar3, f10, z, j10, f11, f12, z5, platformMagnifierFactory);
    }
}
