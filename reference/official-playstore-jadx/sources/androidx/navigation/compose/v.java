package androidx.navigation.compose;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.tv.material3.p1;
import androidx.tv.material3.q1;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ka.k0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class v extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4613i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f4614l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4615m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4616n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f4617o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public v(Object obj, c1.e eVar, y5.i iVar, d7.d dVar) {
        super(2, dVar);
        this.f4613i = 2;
        this.f4615m = obj;
        this.f4616n = eVar;
        this.f4617o = iVar;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f4613i) {
            case 0:
                return new v((Transition) this.f4614l, (Map) this.f4615m, (State) this.f4616n, (f) this.f4617o, dVar, 0);
            case 1:
                return new v((p1) this.f4614l, (FocusRequester) this.f4615m, (MutableState) this.f4616n, (MutableState) this.f4617o, dVar, 1);
            default:
                v vVar = new v(this.f4615m, (c1.e) this.f4616n, (y5.i) this.f4617o, dVar);
                vVar.f4614l = obj;
                return vVar;
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f4613i) {
            case 0:
                v vVar = (v) create((k0) obj, (d7.d) obj2);
                t0 t0Var = t0.f22605a;
                vVar.invokeSuspend(t0Var);
                return t0Var;
            case 1:
                v vVar2 = (v) create((k0) obj, (d7.d) obj2);
                t0 t0Var2 = t0.f22605a;
                vVar2.invokeSuspend(t0Var2);
                return t0Var2;
            default:
                v vVar3 = (v) create((c1.b) obj, (d7.d) obj2);
                t0 t0Var3 = t0.f22605a;
                vVar3.invokeSuspend(t0Var3);
                return t0Var3;
        }
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        FocusState focusState;
        switch (this.f4613i) {
            case 0:
                Map map = (Map) this.f4615m;
                k2.c.G(obj);
                Transition transition = (Transition) this.f4614l;
                if (kotlin.jvm.internal.p.a(transition.getCurrentState(), transition.getTargetState())) {
                    List list = (List) ((State) this.f4616n).getValue();
                    f fVar = (f) this.f4617o;
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        fVar.b().b((androidx.navigation.i) it.next());
                    }
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Map.Entry entry : map.entrySet()) {
                        if (!kotlin.jvm.internal.p.a(entry.getKey(), ((androidx.navigation.i) transition.getTargetState()).f4685p)) {
                            linkedHashMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                    Iterator it2 = linkedHashMap.entrySet().iterator();
                    while (it2.hasNext()) {
                        map.remove(((Map.Entry) it2.next()).getKey());
                    }
                }
                break;
            case 1:
                k2.c.G(obj);
                if (((p1) this.f4614l).a() == q1.f6330l && (focusState = (FocusState) ((MutableState) this.f4616n).getValue()) != null && !focusState.getHasFocus()) {
                    ((FocusRequester) this.f4615m).requestFocus();
                }
                ((MutableState) this.f4617o).setValue(Boolean.TRUE);
                break;
            default:
                c1.e eVar = (c1.e) this.f4616n;
                k2.c.G(obj);
                c1.b bVar = (c1.b) this.f4614l;
                Object obj2 = this.f4615m;
                if (obj2 != null) {
                    bVar.d(eVar, obj2);
                } else {
                    bVar.c(eVar);
                }
                y5.i iVar = (y5.i) this.f4617o;
                iVar.getClass();
                Boolean bool = (Boolean) bVar.f7368a.get(y5.i.f22902c);
                LinkedHashMap linkedHashMap2 = bVar.f7368a;
                iVar.f22908b = new y5.d(bool, (Double) linkedHashMap2.get(y5.i.f22903d), (Integer) linkedHashMap2.get(y5.i.f22904e), (Integer) linkedHashMap2.get(y5.i.f22905f), (Long) linkedHashMap2.get(y5.i.f22906g));
                break;
        }
        return t0.f22605a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(Object obj, Object obj2, State state, Object obj3, d7.d dVar, int i10) {
        super(2, dVar);
        this.f4613i = i10;
        this.f4614l = obj;
        this.f4615m = obj2;
        this.f4616n = state;
        this.f4617o = obj3;
    }
}
