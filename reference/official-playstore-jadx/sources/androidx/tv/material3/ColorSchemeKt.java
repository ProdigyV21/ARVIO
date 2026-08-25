package androidx.tv.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0013\u0010\u0006\u001a\u00020\u0000*\u00020\u0005H\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\b"}, d2 = {"Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "contentColorFor-ek8zF_U", "(JLandroidx/compose/runtime/Composer;I)J", "contentColorFor", "Lj2/d;", "toColor", "(Lj2/d;Landroidx/compose/runtime/Composer;I)J", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ColorSchemeKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ProvidableCompositionLocal f5609a = CompositionLocalKt.staticCompositionLocalOf(i1.f6091l);

    /* JADX WARN: Multi-variable type inference failed */
    public static final long a(h1 h1Var, j2.d dVar) {
        switch (dVar.ordinal()) {
            case 0:
                return h1Var.a();
            case 1:
                return h1Var.c();
            case 2:
                return h1Var.d();
            case 3:
                return h1Var.e();
            case 4:
                return ((Color) h1Var.f6027e.getValue()).m3482unboximpl();
            case 5:
                return h1Var.f();
            case 6:
                return h1Var.g();
            case 7:
                return h1Var.h();
            case 8:
                return h1Var.i();
            case 9:
                return h1Var.j();
            case 10:
                return h1Var.k();
            case 11:
                return h1Var.l();
            case 12:
                return h1Var.m();
            case 13:
                return h1Var.n();
            case 14:
                return h1Var.o();
            case 15:
                return h1Var.p();
            case 16:
                return h1Var.q();
            case 17:
                return h1Var.b();
            case 18:
                return ((Color) h1Var.B.getValue()).m3482unboximpl();
            case 19:
                return h1Var.r();
            case 20:
                return h1Var.s();
            case 21:
                return h1Var.t();
            case 22:
                return h1Var.u();
            case 23:
                return h1Var.v();
            case 24:
                return h1Var.w();
            case 25:
                return h1Var.x();
            case 26:
                return h1Var.y();
            case 27:
                return h1Var.z();
            case 28:
                return h1Var.A();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: renamed from: contentColorFor-ek8zF_U, reason: not valid java name */
    public static final long m5987contentColorForek8zF_U(long j10, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1222667572, i10, -1, "androidx.tv.material3.contentColorFor (ColorScheme.kt:503)");
        }
        h1 colorScheme = MaterialTheme.f5670a.getColorScheme(composer, 6);
        long j11 = Color.m3473equalsimpl0(j10, colorScheme.r()) ? colorScheme.j() : Color.m3473equalsimpl0(j10, colorScheme.u()) ? colorScheme.l() : Color.m3473equalsimpl0(j10, colorScheme.z()) ? colorScheme.p() : Color.m3473equalsimpl0(j10, colorScheme.a()) ? colorScheme.g() : Color.m3473equalsimpl0(j10, colorScheme.c()) ? colorScheme.h() : Color.m3473equalsimpl0(j10, colorScheme.w()) ? colorScheme.n() : Color.m3473equalsimpl0(j10, colorScheme.y()) ? colorScheme.o() : Color.m3473equalsimpl0(j10, colorScheme.s()) ? colorScheme.k() : Color.m3473equalsimpl0(j10, colorScheme.v()) ? colorScheme.m() : Color.m3473equalsimpl0(j10, colorScheme.A()) ? colorScheme.q() : Color.m3473equalsimpl0(j10, colorScheme.d()) ? colorScheme.i() : Color.m3473equalsimpl0(j10, colorScheme.f()) ? colorScheme.e() : Color.INSTANCE.m3508getUnspecified0d7_KjU();
        if (j11 == Color.INSTANCE.m3508getUnspecified0d7_KjU()) {
            j11 = ((Color) composer.consume(o1.f6258a)).m3482unboximpl();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return j11;
    }

    public static final long toColor(j2.d dVar, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1399809969, i10, -1, "androidx.tv.material3.toColor (ColorScheme.kt:634)");
        }
        long jA = a(MaterialTheme.f5670a.getColorScheme(composer, 6), dVar);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return jA;
    }
}
