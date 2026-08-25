package androidx.compose.material3;

import androidx.compose.material3.tokens.SnackbarTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class SnackbarKt$Snackbar$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ p<Composer, Integer, t0> $action;
    final /* synthetic */ long $actionContentColor;
    final /* synthetic */ boolean $actionOnNewLine;
    final /* synthetic */ p<Composer, Integer, t0> $content;
    final /* synthetic */ p<Composer, Integer, t0> $dismissAction;
    final /* synthetic */ long $dismissActionContentColor;

    /* JADX INFO: renamed from: androidx.compose.material3.SnackbarKt$Snackbar$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $action;
        final /* synthetic */ long $actionContentColor;
        final /* synthetic */ boolean $actionOnNewLine;
        final /* synthetic */ TextStyle $actionTextStyle;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ p<Composer, Integer, t0> $dismissAction;
        final /* synthetic */ long $dismissActionContentColor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, TextStyle textStyle, long j10, long j11, boolean z) {
            super(2);
            this.$action = pVar;
            this.$content = pVar2;
            this.$dismissAction = pVar3;
            this.$actionTextStyle = textStyle;
            this.$actionContentColor = j10;
            this.$dismissActionContentColor = j11;
            this.$actionOnNewLine = z;
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
                ComposerKt.traceEventStart(835891690, i10, -1, "androidx.compose.material3.Snackbar.<anonymous>.<anonymous> (Snackbar.kt:118)");
            }
            if (this.$action == null) {
                composer.startReplaceableGroup(-2104362406);
                SnackbarKt.m1917OneRowSnackbarkKq0p4A(this.$content, null, this.$dismissAction, this.$actionTextStyle, this.$actionContentColor, this.$dismissActionContentColor, composer, 48);
                composer.endReplaceableGroup();
            } else if (this.$actionOnNewLine) {
                composer.startReplaceableGroup(-2104362092);
                SnackbarKt.m1916NewLineButtonSnackbarkKq0p4A(this.$content, this.$action, this.$dismissAction, this.$actionTextStyle, this.$actionContentColor, this.$dismissActionContentColor, composer, 0);
                composer.endReplaceableGroup();
            } else {
                composer.startReplaceableGroup(-2104361812);
                SnackbarKt.m1917OneRowSnackbarkKq0p4A(this.$content, this.$action, this.$dismissAction, this.$actionTextStyle, this.$actionContentColor, this.$dismissActionContentColor, composer, 0);
                composer.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SnackbarKt$Snackbar$1(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, long j10, long j11, boolean z) {
        super(2);
        this.$action = pVar;
        this.$content = pVar2;
        this.$dismissAction = pVar3;
        this.$actionContentColor = j10;
        this.$dismissActionContentColor = j11;
        this.$actionOnNewLine = z;
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
            ComposerKt.traceEventStart(-1829663446, i10, -1, "androidx.compose.material3.Snackbar.<anonymous> (Snackbar.kt:115)");
        }
        MaterialTheme materialTheme = MaterialTheme.INSTANCE;
        Typography typography = materialTheme.getTypography(composer, 6);
        SnackbarTokens snackbarTokens = SnackbarTokens.INSTANCE;
        CompositionLocalKt.CompositionLocalProvider(TextKt.getLocalTextStyle().provides(TypographyKt.fromToken(typography, snackbarTokens.getSupportingTextFont())), ComposableLambdaKt.composableLambda(composer, 835891690, true, new AnonymousClass1(this.$action, this.$content, this.$dismissAction, TypographyKt.fromToken(materialTheme.getTypography(composer, 6), snackbarTokens.getActionLabelTextFont()), this.$actionContentColor, this.$dismissActionContentColor, this.$actionOnNewLine)), composer, ProvidedValue.$stable | 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
