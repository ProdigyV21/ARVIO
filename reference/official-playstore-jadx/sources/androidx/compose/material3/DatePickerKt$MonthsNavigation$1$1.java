package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DatePickerKt$MonthsNavigation$1$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ boolean $nextAvailable;
    final /* synthetic */ r7.a<t0> $onNextClicked;
    final /* synthetic */ r7.a<t0> $onPreviousClicked;
    final /* synthetic */ r7.a<t0> $onYearPickerButtonClicked;
    final /* synthetic */ boolean $previousAvailable;
    final /* synthetic */ String $yearPickerText;
    final /* synthetic */ boolean $yearPickerVisible;

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$MonthsNavigation$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ String $yearPickerText;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str) {
            super(2);
            this.$yearPickerText = str;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1377272806, i10, -1, "androidx.compose.material3.MonthsNavigation.<anonymous>.<anonymous>.<anonymous> (DatePicker.kt:2157)");
            }
            String str = this.$yearPickerText;
            Modifier.Companion companion = Modifier.INSTANCE;
            composer.startReplaceableGroup(1090374478);
            boolean zChanged = composer.changed(this.$yearPickerText);
            String str2 = this.$yearPickerText;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DatePickerKt$MonthsNavigation$1$1$1$1$1(str2);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            TextKt.m2153Text4IGK_g(str, SemanticsModifierKt.semantics$default(companion, false, (l) objRememberedValue, 1, null), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer, 0, 0, 131068);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatePickerKt$MonthsNavigation$1$1(r7.a<t0> aVar, boolean z, String str, r7.a<t0> aVar2, boolean z5, r7.a<t0> aVar3, boolean z10) {
        super(2);
        this.$onYearPickerButtonClicked = aVar;
        this.$yearPickerVisible = z;
        this.$yearPickerText = str;
        this.$onPreviousClicked = aVar2;
        this.$previousAvailable = z5;
        this.$onNextClicked = aVar3;
        this.$nextAvailable = z10;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return t0.f22605a;
    }

    public final void invoke(Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-962805198, i10, -1, "androidx.compose.material3.MonthsNavigation.<anonymous>.<anonymous> (DatePicker.kt:2153)");
        }
        DatePickerKt.YearPickerMenuButton(this.$onYearPickerButtonClicked, this.$yearPickerVisible, null, ComposableLambdaKt.composableLambda(composer, 1377272806, true, new AnonymousClass1(this.$yearPickerText)), composer, 3072, 4);
        if (!this.$yearPickerVisible) {
            r7.a<t0> aVar = this.$onPreviousClicked;
            boolean z = this.$previousAvailable;
            r7.a<t0> aVar2 = this.$onNextClicked;
            boolean z5 = this.$nextAvailable;
            composer.startReplaceableGroup(693286680);
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyI = androidx.compose.foundation.c.i(Alignment.INSTANCE, Arrangement.INSTANCE.getStart(), composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyI, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposableSingletons$DatePickerKt composableSingletons$DatePickerKt = ComposableSingletons$DatePickerKt.INSTANCE;
            IconButtonKt.IconButton(aVar, null, z, null, null, composableSingletons$DatePickerKt.m1420getLambda3$material3_release(), composer, 196608, 26);
            IconButtonKt.IconButton(aVar2, null, z5, null, null, composableSingletons$DatePickerKt.m1421getLambda4$material3_release(), composer, 196608, 26);
            androidx.compose.foundation.c.y(composer);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
