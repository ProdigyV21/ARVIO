package a0;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.ValueElementSequence;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import java.util.ArrayList;
import java.util.List;
import r7.p;
import r7.q;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static boolean A(Composer composer, int i10, p pVar) {
        pVar.invoke(composer, Integer.valueOf(i10));
        return ComposerKt.isTraceInProgress();
    }

    public static boolean B(p pVar, Composer composer, Integer num) {
        pVar.invoke(composer, num);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return ComposerKt.isTraceInProgress();
    }

    public static int C(float f10, int i10, int i11) {
        return (Dp.m5684hashCodeimpl(f10) + i10) * i11;
    }

    public static void D(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.horizontalLineToRelative(f10);
        pathBuilder.verticalLineTo(f11);
        pathBuilder.horizontalLineTo(f12);
        pathBuilder.verticalLineTo(f13);
        pathBuilder.close();
    }

    public static float a(float f10, float f11, float f12, float f13) {
        return ((f10 * f11) + f12) * f13;
    }

    public static int b(float f10, int i10, int i11) {
        return (Float.floatToIntBits(f10) + i10) * i11;
    }

    public static long c(int i10, int i11, long j10) {
        return IntOffsetKt.IntOffset(i11, IntOffset.m5803getYimpl(j10) + i10);
    }

    public static MeasurePolicy d(Alignment.Companion companion, boolean z, Composer composer, int i10, int i11) {
        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(companion.getTopStart(), z, composer, i10);
        composer.startReplaceableGroup(i11);
        return measurePolicyRememberBoxMeasurePolicy;
    }

    public static ValueElementSequence e(float f10, ValueElementSequence valueElementSequence, String str, InspectorInfo inspectorInfo) {
        valueElementSequence.set(str, Dp.m5676boximpl(f10));
        return inspectorInfo.getProperties();
    }

    public static ValueElementSequence f(boolean z, ValueElementSequence valueElementSequence, String str, InspectorInfo inspectorInfo) {
        valueElementSequence.set(str, Boolean.valueOf(z));
        return inspectorInfo.getProperties();
    }

    public static Object g(int i10, Composer composer, int i11) {
        composer.startReplaceableGroup(i10);
        composer.startReplaceableGroup(i11);
        return composer.rememberedValue();
    }

    public static Object h(int i10, ArrayList arrayList) {
        return arrayList.get(arrayList.size() - i10);
    }

    public static String i(int i10, String str) {
        return str + i10;
    }

    public static String j(long j10, String str, StringBuilder sb2) {
        sb2.append(j10);
        sb2.append(str);
        return sb2.toString();
    }

    public static String k(String str, int i10, char c10) {
        return str + i10 + c10;
    }

    public static String l(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    public static String m(String str, boolean z, StringBuilder sb2) {
        sb2.append(z);
        sb2.append(str);
        return sb2.toString();
    }

    public static String n(StringBuilder sb2, float f10, char c10) {
        sb2.append(f10);
        sb2.append(c10);
        return sb2.toString();
    }

    public static String o(StringBuilder sb2, int i10, char c10) {
        sb2.append(i10);
        sb2.append(c10);
        return sb2.toString();
    }

    public static String p(StringBuilder sb2, String str, String str2) {
        sb2.append(str);
        sb2.append(str2);
        return sb2.toString();
    }

    public static String q(StringBuilder sb2, List list, String str) {
        sb2.append(list);
        sb2.append(str);
        return sb2.toString();
    }

    public static String r(StringBuilder sb2, boolean z, char c10) {
        sb2.append(z);
        sb2.append(c10);
        return sb2.toString();
    }

    public static StringBuilder s(int i10, String str, String str2) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(i10);
        sb2.append(str2);
        return sb2;
    }

    public static StringBuilder t(String str, String str2, String str3) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(str2);
        sb2.append(str3);
        return sb2;
    }

    public static p u(ComposeUiNode.Companion companion, Composer composer, MeasurePolicy measurePolicy, Composer composer2, CompositionLocalMap compositionLocalMap) {
        Updater.m2998setimpl(composer, measurePolicy, companion.getSetMeasurePolicy());
        Updater.m2998setimpl(composer2, compositionLocalMap, companion.getSetResolvedCompositionLocals());
        return companion.getSetCompositeKeyHash();
    }

    public static void v(float f10, StringBuilder sb2, String str) {
        sb2.append((Object) Dp.m5689toStringimpl(f10));
        sb2.append(str);
    }

    public static void w(int i10, Composer composer, int i11, p pVar) {
        composer.updateRememberedValue(Integer.valueOf(i10));
        composer.apply(Integer.valueOf(i11), pVar);
    }

    public static void x(int i10, q qVar, SkippableUpdater skippableUpdater, Composer composer, int i11) {
        qVar.invoke(skippableUpdater, composer, Integer.valueOf(i10));
        composer.startReplaceableGroup(i11);
    }

    public static void y(Composer composer, q qVar, Composer composer2, Integer num, int i10) {
        qVar.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer2, num);
        composer.startReplaceableGroup(i10);
    }

    public static void z(PathBuilder pathBuilder, float f10, float f11, float f12, float f13) {
        pathBuilder.lineTo(f10, f11);
        pathBuilder.verticalLineTo(f12);
        pathBuilder.horizontalLineTo(f13);
        pathBuilder.close();
    }
}
