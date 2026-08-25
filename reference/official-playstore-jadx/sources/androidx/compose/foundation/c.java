package androidx.compose.foundation;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.text.selection.BaseTextPreparedSelection;
import androidx.compose.foundation.text2.input.internal.TextFieldPreparedSelection;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static boolean A(Composer composer) {
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return ComposerKt.isTraceInProgress();
    }

    public static int B(Placeable placeable, int i10, int i11) {
        return (i10 - placeable.getWidth()) / i11;
    }

    public static MeasurePolicy C(Alignment.Companion companion, Arrangement.HorizontalOrVertical horizontalOrVertical, Composer composer, int i10, int i11) {
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVertical, companion.getTop(), composer, i10);
        composer.startReplaceableGroup(i11);
        return measurePolicyRowMeasurePolicy;
    }

    public static boolean D(Composer composer) {
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return ComposerKt.isTraceInProgress();
    }

    public static int a(int i10, int i11, long j10) {
        return (Color.m3479hashCodeimpl(j10) + i10) * i11;
    }

    public static int b(int i10, int i11, TextStyle textStyle) {
        return (textStyle.hashCode() + i10) * i11;
    }

    public static int c(int i10, int i11, String str) {
        return (str.hashCode() + i10) * i11;
    }

    public static int d(BaseTextPreparedSelection baseTextPreparedSelection) {
        baseTextPreparedSelection.getState().resetCachedX();
        return baseTextPreparedSelection.getText$foundation_release().length();
    }

    public static int e(TextFieldPreparedSelection textFieldPreparedSelection, TextFieldPreparedSelection textFieldPreparedSelection2) {
        textFieldPreparedSelection.textPreparedSelectionState.resetCachedX();
        return textFieldPreparedSelection2.text.length();
    }

    public static int f(Placeable placeable, int i10, int i11) {
        return (i10 - placeable.getHeight()) / i11;
    }

    public static Arrangement.HorizontalOrVertical g(float f10, Arrangement arrangement, Composer composer, int i10) {
        Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(f10));
        composer.startReplaceableGroup(i10);
        return horizontalOrVerticalM441spacedBy0680j_4;
    }

    public static FocusRequester h(Composer composer) {
        FocusRequester focusRequester = new FocusRequester();
        composer.updateRememberedValue(focusRequester);
        return focusRequester;
    }

    public static MeasurePolicy i(Alignment.Companion companion, Arrangement.Horizontal horizontal, Composer composer, int i10, int i11) {
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontal, companion.getTop(), composer, i10);
        composer.startReplaceableGroup(i11);
        return measurePolicyRowMeasurePolicy;
    }

    public static MeasurePolicy j(Alignment.Companion companion, Arrangement.HorizontalOrVertical horizontalOrVertical, Composer composer, int i10, int i11) {
        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVertical, companion.getStart(), composer, i10);
        composer.startReplaceableGroup(i11);
        return measurePolicyColumnMeasurePolicy;
    }

    public static MeasurePolicy k(Alignment.Companion companion, Arrangement.Vertical vertical, Composer composer, int i10, int i11) {
        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(vertical, companion.getStart(), composer, i10);
        composer.startReplaceableGroup(i11);
        return measurePolicyColumnMeasurePolicy;
    }

    public static Object l(Composer composer, int i10) {
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(i10);
        return composer.rememberedValue();
    }

    public static String m(char c10, String str, String str2) {
        return str + c10 + str2;
    }

    public static String n(int i10, int i11, String str, String str2) {
        return str + i10 + str2 + i11;
    }

    public static String o(int i10, String str, String str2) {
        return str + i10 + str2;
    }

    public static String p(int i10, String str, StringBuilder sb2) {
        sb2.append(str);
        sb2.append(i10);
        return sb2.toString();
    }

    public static String q(long j10, String str) {
        return str + j10;
    }

    public static String r(String str, int i10, String str2, int i11, char c10) {
        return str + i10 + str2 + i11 + c10;
    }

    public static String s(String str, int i10, String str2, int i11, String str3) {
        return str + i10 + str2 + i11 + str3;
    }

    public static String t(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String u(StringBuilder sb2, String str, char c10) {
        sb2.append(str);
        sb2.append(c10);
        return sb2.toString();
    }

    public static StringBuilder v(String str, int i10, String str2, int i11, String str3) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(i10);
        sb2.append(str2);
        sb2.append(i11);
        sb2.append(str3);
        return sb2;
    }

    public static ArrayList w(Object obj) {
        k2.c.G(obj);
        return new ArrayList();
    }

    public static void x(long j10, String str, StringBuilder sb2) {
        sb2.append((Object) Color.m3480toStringimpl(j10));
        sb2.append(str);
    }

    public static void y(Composer composer) {
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
    }

    public static void z(StringBuilder sb2, String str, long j10, String str2) {
        sb2.append(str);
        sb2.append(j10);
        sb2.append(str2);
    }
}
