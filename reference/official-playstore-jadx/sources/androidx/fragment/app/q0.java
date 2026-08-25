package androidx.fragment.app;

import android.util.Log;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class q0 implements androidx.activity.result.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f2826i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ b1 f2827l;

    public /* synthetic */ q0(b1 b1Var, int i10) {
        this.f2826i = i10;
        this.f2827l = b1Var;
    }

    @Override // androidx.activity.result.b
    public final void a(Object obj) {
        switch (this.f2826i) {
            case 0:
                Map map = (Map) obj;
                String[] strArr = (String[]) map.keySet().toArray(new String[0]);
                ArrayList arrayList = new ArrayList(map.values());
                int[] iArr = new int[arrayList.size()];
                for (int i10 = 0; i10 < arrayList.size(); i10++) {
                    iArr[i10] = ((Boolean) arrayList.get(i10)).booleanValue() ? 0 : -1;
                }
                b1 b1Var = this.f2827l;
                x0 x0Var = (x0) b1Var.D.pollFirst();
                if (x0Var == null) {
                    Log.w("FragmentManager", "No permissions were requested for " + this);
                } else {
                    String str = x0Var.f2865i;
                    int i11 = x0Var.f2866l;
                    c0 c0VarC = b1Var.f2669c.c(str);
                    if (c0VarC == null) {
                        androidx.compose.material3.d.y("Permission request result delivered for unknown Fragment ", str, "FragmentManager");
                    } else {
                        c0VarC.onRequestPermissionsResult(i11, strArr, iArr);
                    }
                }
                break;
            case 1:
                androidx.activity.result.a aVar = (androidx.activity.result.a) obj;
                b1 b1Var2 = this.f2827l;
                x0 x0Var2 = (x0) b1Var2.D.pollFirst();
                if (x0Var2 == null) {
                    Log.w("FragmentManager", "No Activities were started for result for " + this);
                } else {
                    String str2 = x0Var2.f2865i;
                    int i12 = x0Var2.f2866l;
                    c0 c0VarC2 = b1Var2.f2669c.c(str2);
                    if (c0VarC2 == null) {
                        androidx.compose.material3.d.y("Activity result delivered for unknown Fragment ", str2, "FragmentManager");
                    } else {
                        c0VarC2.onActivityResult(i12, aVar.f990i, aVar.f991l);
                    }
                }
                break;
            default:
                androidx.activity.result.a aVar2 = (androidx.activity.result.a) obj;
                b1 b1Var3 = this.f2827l;
                x0 x0Var3 = (x0) b1Var3.D.pollFirst();
                if (x0Var3 == null) {
                    Log.w("FragmentManager", "No IntentSenders were started for " + this);
                } else {
                    String str3 = x0Var3.f2865i;
                    int i13 = x0Var3.f2866l;
                    c0 c0VarC3 = b1Var3.f2669c.c(str3);
                    if (c0VarC3 == null) {
                        androidx.compose.material3.d.y("Intent Sender result delivered for unknown Fragment ", str3, "FragmentManager");
                    } else {
                        c0VarC3.onActivityResult(i13, aVar2.f990i, aVar2.f991l);
                    }
                }
                break;
        }
    }
}
