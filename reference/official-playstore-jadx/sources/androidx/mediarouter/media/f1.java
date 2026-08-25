package androidx.mediarouter.media;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class f1 extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4359a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f4360b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f1(h hVar) {
        super(Looper.getMainLooper());
        this.f4360b = hVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        q qVar;
        int i10 = this.f4359a;
        e1 e1Var = null;
        e1 e1Var2 = null;
        Object obj = this.f4360b;
        switch (i10) {
            case 0:
                c1 c1Var = (c1) ((WeakReference) obj).get();
                if (c1Var != null) {
                    SparseArray sparseArray = c1Var.f4317h;
                    j1 j1Var = c1Var.f4318i;
                    ArrayList arrayList = j1Var.f4420u;
                    int i11 = message.what;
                    int i12 = message.arg1;
                    int i13 = message.arg2;
                    Object obj2 = message.obj;
                    Bundle bundlePeekData = message.peekData();
                    switch (i11) {
                        case 0:
                            if (i12 == c1Var.f4316g) {
                                c1Var.f4316g = 0;
                                if (j1Var.x == c1Var) {
                                    j1Var.k();
                                }
                            }
                            if (((g1) sparseArray.get(i12)) != null) {
                                sparseArray.remove(i12);
                                g1.a(null, null);
                            }
                            break;
                        case 2:
                            if (obj2 == null || (obj2 instanceof Bundle)) {
                                Bundle bundle = (Bundle) obj2;
                                if (c1Var.f4315f == 0 && i12 == c1Var.f4316g && i13 >= 1) {
                                    c1Var.f4316g = 0;
                                    c1Var.f4315f = i13;
                                    x xVarA = x.a(bundle);
                                    if (j1Var.x == c1Var) {
                                        j1Var.f(xVarA);
                                    }
                                    if (j1Var.x == c1Var) {
                                        j1Var.f4423y = true;
                                        int size = arrayList.size();
                                        for (int i14 = 0; i14 < size; i14++) {
                                            ((e1) arrayList.get(i14)).c(j1Var.x);
                                        }
                                        n nVar = j1Var.f4497o;
                                        if (nVar != null) {
                                            c1 c1Var2 = j1Var.x;
                                            int i15 = c1Var2.f4313d;
                                            c1Var2.f4313d = i15 + 1;
                                            c1Var2.b(10, i15, 0, nVar.f4454a, null);
                                        }
                                    }
                                }
                            }
                            break;
                        case 3:
                            if (obj2 == null || (obj2 instanceof Bundle)) {
                                Bundle bundle2 = (Bundle) obj2;
                                g1 g1Var = (g1) sparseArray.get(i12);
                                if (g1Var != null) {
                                    sparseArray.remove(i12);
                                    g1Var.b(bundle2);
                                }
                            }
                            break;
                        case 4:
                            if (obj2 == null || (obj2 instanceof Bundle)) {
                                String string = bundlePeekData != null ? bundlePeekData.getString("error") : null;
                                Bundle bundle3 = (Bundle) obj2;
                                if (((g1) sparseArray.get(i12)) != null) {
                                    sparseArray.remove(i12);
                                    g1.a(string, bundle3);
                                }
                            }
                            break;
                        case 5:
                            if (obj2 == null || (obj2 instanceof Bundle)) {
                                Bundle bundle4 = (Bundle) obj2;
                                if (c1Var.f4315f != 0) {
                                    x xVarA2 = x.a(bundle4);
                                    if (j1Var.x == c1Var) {
                                        j1Var.f(xVarA2);
                                    }
                                }
                            }
                            break;
                        case 6:
                            if (obj2 instanceof Bundle) {
                                Bundle bundle5 = (Bundle) obj2;
                                g1 g1Var2 = (g1) sparseArray.get(i12);
                                if (bundle5.containsKey("routeId")) {
                                    sparseArray.remove(i12);
                                    g1Var2.b(bundle5);
                                } else {
                                    g1Var2.getClass();
                                    g1.a("DynamicGroupRouteController is created without valid route id.", bundle5);
                                }
                            } else {
                                Log.w("MediaRouteProviderProxy", "No further information on the dynamic group controller");
                            }
                            break;
                        case 7:
                            if (obj2 == null || (obj2 instanceof Bundle)) {
                                Bundle bundle6 = (Bundle) obj2;
                                if (c1Var.f4315f != 0) {
                                    Bundle bundle7 = (Bundle) bundle6.getParcelable("groupRoute");
                                    m mVar = bundle7 != null ? new m(bundle7) : null;
                                    ArrayList<Bundle> parcelableArrayList = bundle6.getParcelableArrayList("dynamicRoutes");
                                    ArrayList arrayList2 = new ArrayList();
                                    for (Bundle bundle8 : parcelableArrayList) {
                                        if (bundle8 == null) {
                                            qVar = null;
                                        } else {
                                            Bundle bundle9 = bundle8.getBundle("mrDescriptor");
                                            m mVar2 = bundle9 != null ? new m(bundle9) : null;
                                            int i16 = bundle8.getInt("selectionState", 1);
                                            bundle8.getBoolean("isUnselectable", false);
                                            bundle8.getBoolean("isGroupable", false);
                                            bundle8.getBoolean("isTransferable", false);
                                            qVar = new q(mVar2, i16);
                                        }
                                        arrayList2.add(qVar);
                                    }
                                    if (j1Var.x == c1Var) {
                                        Iterator it = arrayList.iterator();
                                        while (true) {
                                            if (it.hasNext()) {
                                                e1 e1Var3 = (e1) it.next();
                                                if (e1Var3.a() == i13) {
                                                    e1Var2 = e1Var3;
                                                }
                                            }
                                        }
                                        if (e1Var2 instanceof h1) {
                                            ((h1) e1Var2).j(mVar, arrayList2);
                                        }
                                    }
                                }
                            }
                            break;
                        case 8:
                            if (j1Var.x == c1Var) {
                                Iterator it2 = arrayList.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        e1 e1Var4 = (e1) it2.next();
                                        if (e1Var4.a() == i13) {
                                            e1Var = e1Var4;
                                        }
                                    }
                                }
                                k1 k1Var = j1Var.z;
                                if (k1Var != null && (e1Var instanceof u)) {
                                    u uVar = (u) e1Var;
                                    f fVar = k1Var.f4428a.f4446b;
                                    if (fVar.f4330e == uVar) {
                                        fVar.l(fVar.c(), 2, true);
                                    }
                                }
                                if (e1Var != null) {
                                    arrayList.remove(e1Var);
                                    e1Var.b();
                                    j1Var.l();
                                }
                            }
                            break;
                    }
                    int i17 = j1.A;
                }
                break;
            case 1:
                int i18 = message.what;
                int i19 = message.arg1;
                Object obj3 = message.obj;
                Bundle bundlePeekData2 = message.peekData();
                SparseArray sparseArray2 = ((h) obj).f4372j;
                g1 g1Var3 = (g1) sparseArray2.get(i19);
                if (g1Var3 == null) {
                    Log.w("MR2Provider", "Pending callback not found for control request.");
                    break;
                } else {
                    sparseArray2.remove(i19);
                    if (i18 == 3) {
                        g1Var3.b((Bundle) obj3);
                        break;
                    } else if (i18 == 4) {
                        g1.a(bundlePeekData2 != null ? bundlePeekData2.getString("error") : null, (Bundle) obj3);
                        break;
                    }
                }
                break;
            default:
                w wVar = (w) obj;
                int i20 = message.what;
                if (i20 == 1) {
                    wVar.f4500r = false;
                    b bVar = wVar.f4496n;
                    if (bVar != null) {
                        x xVar = wVar.f4499q;
                        f fVar2 = bVar.f4298a;
                        g0 g0VarD = fVar2.d(wVar);
                        if (g0VarD != null) {
                            fVar2.p(g0VarD, xVar);
                        }
                    }
                    break;
                } else if (i20 == 2) {
                    wVar.f4498p = false;
                    wVar.e(wVar.f4497o);
                    break;
                }
                break;
        }
    }

    public f1(w wVar) {
        this.f4360b = wVar;
    }

    public f1(c1 c1Var) {
        this.f4360b = new WeakReference(c1Var);
    }
}
