package androidx.work.impl.constraints.controllers;

import android.os.Build;
import androidx.work.impl.model.p;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f6791b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(androidx.work.impl.constraints.trackers.f fVar, int i10) {
        super(fVar);
        this.f6791b = i10;
    }

    @Override // androidx.work.impl.constraints.controllers.d
    public final int a() {
        switch (this.f6791b) {
            case 0:
                return 6;
            case 1:
                return 5;
            case 2:
                return 7;
            case 3:
                return 7;
            default:
                return 9;
        }
    }

    @Override // androidx.work.impl.constraints.controllers.d
    public final boolean b(p pVar) {
        switch (this.f6791b) {
            case 0:
                return pVar.f6931j.f20189b;
            case 1:
                return pVar.f6931j.f20191d;
            case 2:
                return pVar.f6931j.f20188a == 2;
            case 3:
                int i10 = pVar.f6931j.f20188a;
                return i10 == 3 || (Build.VERSION.SDK_INT >= 30 && i10 == 6);
            default:
                return pVar.f6931j.f20192e;
        }
    }

    @Override // androidx.work.impl.constraints.controllers.d
    public final boolean c(Object obj) {
        boolean zBooleanValue;
        switch (this.f6791b) {
            case 0:
                zBooleanValue = ((Boolean) obj).booleanValue();
                break;
            case 1:
                zBooleanValue = ((Boolean) obj).booleanValue();
                break;
            case 2:
                androidx.work.impl.constraints.e eVar = (androidx.work.impl.constraints.e) obj;
                boolean z = eVar.f6801a;
                return Build.VERSION.SDK_INT < 26 ? !z : !(z && eVar.f6802b);
            case 3:
                androidx.work.impl.constraints.e eVar2 = (androidx.work.impl.constraints.e) obj;
                return !eVar2.f6801a || eVar2.f6803c;
            default:
                zBooleanValue = ((Boolean) obj).booleanValue();
                break;
        }
        return !zBooleanValue;
    }
}
