package androidx.compose.material3;

import android.util.Log;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.SnapshotStateMapKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Dp;
import androidx.datastore.preferences.protobuf.u;
import androidx.fragment.app.c0;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.m0;
import kotlin.jvm.internal.v;
import kotlin.reflect.j;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class d {
    public static void A(StringBuilder sb2, String str, String str2, int i10, String str3) {
        sb2.append(str);
        sb2.append(str2);
        sb2.append(i10);
        sb2.append(str3);
    }

    public static /* synthetic */ boolean B(Object obj) {
        return obj != null;
    }

    public static String C(String str, String str2) {
        return str + str2;
    }

    public static /* synthetic */ String D(int i10) {
        return i10 != 1 ? i10 != 2 ? i10 != 3 ? "null" : "REMOVING" : "ADDING" : "NONE";
    }

    public static int a(int i10, int i11, int i12) {
        return u.t(i10) + i11 + i12;
    }

    public static int b(int i10, int i11, int i12, int i13) {
        return u.v(i10) + i11 + i12 + i13;
    }

    public static int c(Shape shape, int i10, int i11) {
        return (shape.hashCode() + i10) * i11;
    }

    public static int d(LayoutNode layoutNode) {
        return layoutNode.getNodes().getHead().getAggregateChildKindSet();
    }

    public static int e(List list, int i10, int i11) {
        return (list.hashCode() + i10) * i11;
    }

    public static MeasurePolicy f(Arrangement arrangement, Alignment.Horizontal horizontal, Composer composer, int i10, int i11) {
        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(arrangement.getTop(), horizontal, composer, i10);
        composer.startReplaceableGroup(i11);
        return measurePolicyColumnMeasurePolicy;
    }

    public static MeasurePolicy g(Arrangement arrangement, Alignment.Vertical vertical, Composer composer, int i10, int i11) {
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.getStart(), vertical, composer, i10);
        composer.startReplaceableGroup(i11);
        return measurePolicyRowMeasurePolicy;
    }

    public static Object h(MutableVector mutableVector, int i10) {
        return mutableVector.removeAt(mutableVector.getSize() - i10);
    }

    public static String i(char c10, String str, String str2) {
        return str + str2 + c10;
    }

    public static String j(int i10, String str, StringBuilder sb2) {
        sb2.append(i10);
        sb2.append(str);
        return sb2.toString();
    }

    public static String k(long j10, String str, String str2) {
        return str + j10 + str2;
    }

    public static String l(String str, c0 c0Var, String str2) {
        return str + c0Var + str2;
    }

    public static String m(String str, String str2) {
        return str + str2;
    }

    public static String n(String str, String str2, String str3, String str4) {
        return str + str2 + str3 + str4;
    }

    public static String o(String str, String str2, String str3, String str4, String str5) {
        return str + str2 + str3 + str4 + str5;
    }

    public static String p(StringBuilder sb2, Object obj, char c10) {
        sb2.append(obj);
        sb2.append(c10);
        return sb2.toString();
    }

    public static String q(StringBuilder sb2, String str, String str2, String str3) {
        sb2.append(str);
        sb2.append(str2);
        sb2.append(str3);
        return sb2.toString();
    }

    public static StringBuilder r(long j10, String str, String str2) {
        StringBuilder sb2 = new StringBuilder(str);
        sb2.append(j10);
        sb2.append(str2);
        return sb2;
    }

    public static StringBuilder s(String str) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        return sb2;
    }

    public static ArrayList t(LinkedHashMap linkedHashMap, String str) {
        ArrayList arrayList = new ArrayList();
        linkedHashMap.put(str, arrayList);
        return arrayList;
    }

    public static KotlinNothingValueException u() {
        SnapshotStateMapKt.unsupported();
        return new KotlinNothingValueException();
    }

    public static j v(Class cls, String str, String str2, int i10, m0 m0Var) {
        return m0Var.e(new v(cls, str, str2, i10));
    }

    public static void w(float f10, Modifier.Companion companion, Composer composer, int i10) {
        SpacerKt.Spacer(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(f10)), composer, i10);
    }

    public static /* synthetic */ void x(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }

    public static void y(String str, String str2, String str3) {
        Log.w(str3, str + str2);
    }

    public static void z(StringBuilder sb2, String str, String str2) {
        sb2.append(str);
        sb2.append(str2);
        PreconditionsKt.throwIllegalStateException(sb2.toString());
    }
}
