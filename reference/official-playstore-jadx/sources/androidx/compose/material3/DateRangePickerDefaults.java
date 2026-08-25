package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.material3.DisplayMode;
import androidx.compose.material3.Strings;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
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
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import java.util.Locale;
import kotlin.Metadata;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0087\u0001\u0010\u0018\u001a\u00020\u00112\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0011\u0010\u0013\u001a\r\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0002\b\u00122\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0002\b\u00122\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0002\b\u0012H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J$\u0010\u001b\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ@\u0010\u0018\u001a\u00020\u00112\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/material3/DateRangePickerDefaults;", "", "<init>", "()V", "", "selectedStartDateMillis", "selectedEndDateMillis", "Landroidx/compose/material3/DisplayMode;", "displayMode", "Landroidx/compose/material3/DatePickerFormatter;", "dateFormatter", "Landroidx/compose/ui/Modifier;", "modifier", "", "startDateText", "endDateText", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "startDatePlaceholder", "endDatePlaceholder", "datesDelimiter", "DateRangePickerHeadline-0YIUgSQ", "(Ljava/lang/Long;Ljava/lang/Long;ILandroidx/compose/material3/DatePickerFormatter;Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/String;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "DateRangePickerHeadline", "DateRangePickerTitle-hOD91z4", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "DateRangePickerTitle", "DateRangePickerHeadline-v84Udv0", "(Ljava/lang/Long;Ljava/lang/Long;ILandroidx/compose/material3/DatePickerFormatter;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class DateRangePickerDefaults {
    public static final int $stable = 0;
    public static final DateRangePickerDefaults INSTANCE = new DateRangePickerDefaults();

    private DateRangePickerDefaults() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: DateRangePickerHeadline-0YIUgSQ, reason: not valid java name */
    public final void m1500DateRangePickerHeadline0YIUgSQ(Long l10, Long l11, int i10, DatePickerFormatter datePickerFormatter, Modifier modifier, String str, String str2, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Composer composer, int i11, int i12) {
        int i13;
        Composer composer2;
        Long l12;
        String str3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-820363420);
        if ((i11 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(l10) ? 4 : 2) | i11;
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(l11) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(i10) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i13 |= (i11 & 4096) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changed(modifier) ? 16384 : 8192;
        }
        if ((196608 & i11) == 0) {
            i13 |= composerStartRestartGroup.changed(str) ? 131072 : 65536;
        }
        if ((1572864 & i11) == 0) {
            i13 |= composerStartRestartGroup.changed(str2) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((12582912 & i11) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(pVar) ? 8388608 : 4194304;
        }
        if ((100663296 & i11) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(pVar2) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((805306368 & i11) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(pVar3) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((306783379 & i13) == 306783378 && (i12 & 1) == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            l12 = l10;
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-820363420, i13, i12, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:425)");
            }
            Locale localeDefaultLocale = ActualAndroid_androidKt.defaultLocale(composerStartRestartGroup, 0);
            int i14 = i13;
            composer2 = composerStartRestartGroup;
            String strA = c.a(datePickerFormatter, l10, localeDefaultLocale, false, 4, null);
            l12 = l10;
            String strA2 = c.a(datePickerFormatter, l11, localeDefaultLocale, false, 4, null);
            String date = datePickerFormatter.formatDate(l12, localeDefaultLocale, true);
            composer2.startReplaceableGroup(-1212631660);
            String strM1994getStringNWtq28 = "";
            if (date == null) {
                DisplayMode.Companion companion = DisplayMode.INSTANCE;
                str3 = strA2;
                if (DisplayMode.m1526equalsimpl0(i10, companion.m1531getPickerjFl4v0())) {
                    composer2.startReplaceableGroup(-1212631439);
                    Strings.Companion companion2 = Strings.INSTANCE;
                    String strM1994getStringNWtq282 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_picker_no_selection_description), composer2, 0);
                    composer2.endReplaceableGroup();
                    date = strM1994getStringNWtq282;
                } else if (DisplayMode.m1526equalsimpl0(i10, companion.m1530getInputjFl4v0())) {
                    composer2.startReplaceableGroup(-1212631354);
                    Strings.Companion companion3 = Strings.INSTANCE;
                    date = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_input_no_input_description), composer2, 0);
                    composer2.endReplaceableGroup();
                } else {
                    composer2.startReplaceableGroup(1063135767);
                    composer2.endReplaceableGroup();
                    date = "";
                }
            } else {
                str3 = strA2;
            }
            composer2.endReplaceableGroup();
            String date2 = datePickerFormatter.formatDate(l11, localeDefaultLocale, true);
            composer2.startReplaceableGroup(-1212631233);
            if (date2 == null) {
                DisplayMode.Companion companion4 = DisplayMode.INSTANCE;
                if (DisplayMode.m1526equalsimpl0(i10, companion4.m1531getPickerjFl4v0())) {
                    composer2.startReplaceableGroup(-1212631014);
                    Strings.Companion companion5 = Strings.INSTANCE;
                    strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_picker_no_selection_description), composer2, 0);
                    composer2.endReplaceableGroup();
                } else if (DisplayMode.m1526equalsimpl0(i10, companion4.m1530getInputjFl4v0())) {
                    composer2.startReplaceableGroup(-1212630929);
                    Strings.Companion companion6 = Strings.INSTANCE;
                    strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_input_no_input_description), composer2, 0);
                    composer2.endReplaceableGroup();
                } else {
                    composer2.startReplaceableGroup(1063148942);
                    composer2.endReplaceableGroup();
                }
            } else {
                strM1994getStringNWtq28 = date2;
            }
            composer2.endReplaceableGroup();
            String str4 = str + ": " + date;
            String str5 = str2 + ": " + strM1994getStringNWtq28;
            composer2.startReplaceableGroup(-1212630615);
            boolean zChanged = composer2.changed(str4) | composer2.changed(str5);
            Object objRememberedValue = composer2.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DateRangePickerDefaults$DateRangePickerHeadline$4$1(str4, str5);
                composer2.updateRememberedValue(objRememberedValue);
            }
            composer2.endReplaceableGroup();
            Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(modifier, (l) objRememberedValue);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(4, Arrangement.INSTANCE, composer2, 693286680), Alignment.INSTANCE.getCenterVertically(), composer2, 54);
            composer2.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion7.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierClearAndSetSemantics);
            if (!(composer2.getApplier() != null)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer2);
            p pVarU = a0.c.u(companion7, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer2)), composer2, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            if (strA != null) {
                composer2.startReplaceableGroup(1922100124);
                TextKt.m2153Text4IGK_g(strA, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer2, 0, 0, 131070);
                composer2.endReplaceableGroup();
            } else {
                composer2.startReplaceableGroup(1922100193);
                pVar.invoke(composer2, Integer.valueOf((i14 >> 21) & 14));
                composer2.endReplaceableGroup();
            }
            pVar3.invoke(composer2, Integer.valueOf((i14 >> 27) & 14));
            if (str3 != null) {
                composer2.startReplaceableGroup(1922100319);
                TextKt.m2153Text4IGK_g(str3, (Modifier) null, 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, (TextStyle) null, composer2, 0, 0, 131070);
                composer2.endReplaceableGroup();
            } else {
                composer2.startReplaceableGroup(1922100386);
                pVar2.invoke(composer2, Integer.valueOf((i14 >> 24) & 14));
                composer2.endReplaceableGroup();
            }
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new DateRangePickerDefaults$DateRangePickerHeadline$6(this, l12, l11, i10, datePickerFormatter, modifier, str, str2, pVar, pVar2, pVar3, i11, i12));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:89:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: DateRangePickerHeadline-v84Udv0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m1502DateRangePickerHeadlinev84Udv0(java.lang.Long r18, java.lang.Long r19, int r20, androidx.compose.material3.DatePickerFormatter r21, androidx.compose.ui.Modifier r22, androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DateRangePickerDefaults.m1502DateRangePickerHeadlinev84Udv0(java.lang.Long, java.lang.Long, int, androidx.compose.material3.DatePickerFormatter, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: DateRangePickerTitle-hOD91z4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m1503DateRangePickerTitlehOD91z4(int r29, androidx.compose.ui.Modifier r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DateRangePickerDefaults.m1503DateRangePickerTitlehOD91z4(int, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }
}
