package androidx.activity;

import java.util.ListIterator;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f936i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l0 f937l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e0(l0 l0Var, int i10) {
        super(1);
        this.f936i = i10;
        this.f937l = l0Var;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        Object objPrevious;
        Object objPrevious2;
        switch (this.f936i) {
            case 0:
                l0 l0Var = this.f937l;
                kotlin.collections.p pVar = l0Var.f964b;
                ListIterator listIterator = pVar.listIterator(pVar.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        objPrevious = listIterator.previous();
                        if (((d0) objPrevious).f931a) {
                        }
                    } else {
                        objPrevious = null;
                    }
                }
                d0 d0Var = (d0) objPrevious;
                if (l0Var.f965c != null) {
                    l0Var.b();
                }
                l0Var.f965c = d0Var;
                if (d0Var != null) {
                    d0Var.d();
                }
                break;
            default:
                c cVar = (c) obj;
                l0 l0Var2 = this.f937l;
                d0 d0Var2 = l0Var2.f965c;
                if (d0Var2 == null) {
                    kotlin.collections.p pVar2 = l0Var2.f964b;
                    ListIterator listIterator2 = pVar2.listIterator(pVar2.size());
                    while (true) {
                        if (listIterator2.hasPrevious()) {
                            objPrevious2 = listIterator2.previous();
                            if (((d0) objPrevious2).f931a) {
                            }
                        } else {
                            objPrevious2 = null;
                        }
                    }
                    d0Var2 = (d0) objPrevious2;
                }
                if (d0Var2 != null) {
                    d0Var2.c(cVar);
                }
                break;
        }
        return t0.f22605a;
    }
}
