package androidx.work.impl.constraints.controllers;

import androidx.compose.runtime.ProduceStateScope;
import androidx.emoji2.text.e0;
import f7.j;
import java.util.ArrayList;
import java.util.List;
import ka.k0;
import ma.u;
import na.k;
import r7.p;
import w5.m;
import w5.x;
import x6.t0;
import y5.i;

/* JADX INFO: loaded from: classes3.dex */
public final class c extends j implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6794i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f6795l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f6796m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f6797n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(Object obj, d7.d dVar, int i10) {
        super(2, dVar);
        this.f6794i = i10;
        this.f6797n = obj;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f6794i) {
            case 0:
                c cVar = new c((d) this.f6797n, dVar, 0);
                cVar.f6796m = obj;
                return cVar;
            case 1:
                return new c((na.j) this.f6796m, (ProduceStateScope) this.f6797n, dVar, 1);
            case 2:
                c cVar2 = new c((oa.d) this.f6797n, dVar, 2);
                cVar2.f6796m = obj;
                return cVar2;
            case 3:
                c cVar3 = new c((oa.e) this.f6797n, dVar, 3);
                cVar3.f6796m = obj;
                return cVar3;
            case 4:
                c cVar4 = new c((k) this.f6797n, dVar, 4);
                cVar4.f6796m = obj;
                return cVar4;
            case 5:
                return new c((m) this.f6796m, (d7.j) this.f6797n, dVar, 5);
            case 6:
                return new c((x) this.f6796m, (String) this.f6797n, dVar, 6);
            case 7:
                return new c((e0) this.f6796m, (ArrayList) this.f6797n, dVar, 7);
            case 8:
                return new c((i) this.f6797n, dVar, 8);
            case 9:
                c cVar5 = new c((List) this.f6797n, dVar, 9);
                cVar5.f6796m = obj;
                return cVar5;
            default:
                return new c((e0) this.f6797n, dVar, 10);
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f6794i) {
            case 0:
                return ((c) create((u) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            case 1:
                return ((c) create((k0) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            case 2:
                return ((c) create((u) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            case 3:
                return ((c) create((k) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            case 4:
                return ((c) create(obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            case 5:
                return ((c) create((k0) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            case 6:
                return ((c) create((k0) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            case 7:
                return ((c) create((k0) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            case 8:
                return ((c) create((k0) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            case 9:
                return ((c) create((z0.i) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
            default:
                return ((c) create((k0) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x02b9 A[PHI: r13
      0x02b9: PHI (r13v40 java.lang.Boolean) = (r13v31 java.lang.Boolean), (r13v33 java.lang.Boolean) binds: [B:122:0x02b7, B:125:0x02c4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:224:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:225:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0060 -> B:20:0x0063). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instruction units count: 1132
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.constraints.controllers.c.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(Object obj, Object obj2, d7.d dVar, int i10) {
        super(2, dVar);
        this.f6794i = i10;
        this.f6796m = obj;
        this.f6797n = obj2;
    }
}
