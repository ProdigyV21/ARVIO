package androidx.tv.material3;

import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a@\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u0000H\u0001¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/tv/material3/h1;", "colorScheme", "Landroidx/tv/material3/d3;", "shapes", "Landroidx/tv/material3/p4;", "typography", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "MaterialTheme", "(Landroidx/tv/material3/h1;Landroidx/tv/material3/d3;Landroidx/tv/material3/p4;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", "rememberTextSelectionColors", "(Landroidx/tv/material3/h1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MaterialThemeKt {

    /* JADX INFO: renamed from: androidx.tv.material3.MaterialThemeKt$MaterialTheme$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends kotlin.jvm.internal.r implements r7.p<Composer, Integer, x6.t0> {

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ p4 f5671i;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public final /* synthetic */ r7.p f5672l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(p4 p4Var, r7.p pVar) {
            super(2);
            this.f5671i = p4Var;
            this.f5672l = pVar;
        }

        @Override // r7.p
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return x6.t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-223479076, i10, -1, "androidx.tv.material3.MaterialTheme.<anonymous> (MaterialTheme.kt:68)");
            }
            TextKt.ProvideTextStyle(this.f5671i.f6308j, this.f5672l, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x034d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void MaterialTheme(androidx.tv.material3.h1 r69, androidx.tv.material3.d3 r70, androidx.tv.material3.p4 r71, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r72, androidx.compose.runtime.Composer r73, int r74, int r75) {
        /*
            Method dump skipped, instruction units count: 869
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.tv.material3.MaterialThemeKt.MaterialTheme(androidx.tv.material3.h1, androidx.tv.material3.d3, androidx.tv.material3.p4, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final TextSelectionColors rememberTextSelectionColors(h1 h1Var, Composer composer, int i10) {
        composer.startReplaceableGroup(-83549050);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-83549050, i10, -1, "androidx.tv.material3.rememberTextSelectionColors (MaterialTheme.kt:95)");
        }
        long jR = h1Var.r();
        composer.startReplaceableGroup(1001025886);
        boolean zChanged = composer.changed(jR);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            TextSelectionColors textSelectionColors = new TextSelectionColors(jR, Color.m3471copywmQWz5c$default(jR, 0.4f, 0.0f, 0.0f, 0.0f, 14, null), null);
            composer.updateRememberedValue(textSelectionColors);
            objRememberedValue = textSelectionColors;
        }
        TextSelectionColors textSelectionColors2 = (TextSelectionColors) objRememberedValue;
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return textSelectionColors2;
    }
}
