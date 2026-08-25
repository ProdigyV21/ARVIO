package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f13254i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f13255l;

    public /* synthetic */ g1(Object obj, int i10) {
        this.f13254i = i10;
        this.f13255l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f13254i) {
            case 0:
                com.google.android.gms.tasks.i iVar = (com.google.android.gms.tasks.i) this.f13255l;
                h1.f13273d.d("get checkbox consent timed out", new Object[0]);
                iVar.d(Boolean.FALSE);
                break;
            case 1:
                i5 i5Var = (i5) this.f13255l;
                j5 j5Var = i5Var.f13322g;
                if (j5Var != null) {
                    i5Var.f13316a.a((c6) i5Var.f13318c.b(j5Var).e(), 223);
                }
                i5Var.c();
                break;
            default:
                l6 l6Var = (l6) this.f13255l;
                SharedPreferences sharedPreferences = l6Var.f13419b;
                HashSet hashSet = l6Var.f13423f;
                if (!hashSet.isEmpty()) {
                    HashSet hashSet2 = l6Var.f13424g;
                    long j10 = true != hashSet2.equals(hashSet) ? 86400000L : 172800000L;
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    long j11 = l6Var.f13425h;
                    if (j11 == 0 || jCurrentTimeMillis - j11 >= j10) {
                        l6.f13416i.d("Upload the feature usage report.", new Object[0]);
                        s5 s5VarN = t5.n();
                        String str = l6.f13417j;
                        s5VarN.c();
                        ((t5) s5VarN.f13530l).p(str);
                        String str2 = l6Var.f13420c;
                        s5VarN.c();
                        ((t5) s5VarN.f13530l).o(str2);
                        t5 t5Var = (t5) s5VarN.e();
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(hashSet);
                        o5 o5VarN = p5.n();
                        o5VarN.c();
                        ((p5) o5VarN.f13530l).p(arrayList);
                        o5VarN.c();
                        ((p5) o5VarN.f13530l).o(t5Var);
                        p5 p5Var = (p5) o5VarN.e();
                        b6 b6VarO = c6.o();
                        b6VarO.c();
                        ((c6) b6VarO.f13530l).A(p5Var);
                        l6Var.f13418a.a((c6) b6VarO.e(), 243);
                        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                        if (!hashSet2.equals(hashSet)) {
                            hashSet2.clear();
                            hashSet2.addAll(hashSet);
                            Iterator it = hashSet2.iterator();
                            while (it.hasNext()) {
                                String string = Integer.toString(((m5) it.next()).f13472i);
                                String strC = androidx.compose.material3.d.C("feature_usage_timestamp_reported_feature_", string);
                                if (!sharedPreferences.contains(strC)) {
                                    strC = androidx.compose.material3.d.C("feature_usage_timestamp_detected_feature_", string);
                                }
                                String strC2 = androidx.compose.material3.d.C("feature_usage_timestamp_reported_feature_", string);
                                if (!TextUtils.equals(strC, strC2)) {
                                    long j12 = sharedPreferences.getLong(strC, 0L);
                                    editorEdit.remove(strC);
                                    if (j12 != 0) {
                                        editorEdit.putLong(strC2, j12);
                                    }
                                }
                            }
                        }
                        l6Var.f13425h = jCurrentTimeMillis;
                        editorEdit.putLong("feature_usage_last_report_time", jCurrentTimeMillis).apply();
                    }
                    break;
                }
                break;
        }
    }
}
