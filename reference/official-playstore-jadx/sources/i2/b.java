package i2;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class b implements OnGloballyPositionedModifier {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f16022i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public d7.m f16023l;

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(d7.d r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof i2.a
            if (r0 == 0) goto L13
            r0 = r5
            i2.a r0 = (i2.a) r0
            int r1 = r0.f16021n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f16021n = r1
            goto L1a
        L13:
            i2.a r0 = new i2.a
            f7.c r5 = (f7.c) r5
            r0.<init>(r4, r5)
        L1a:
            java.lang.Object r5 = r0.f16019l
            int r1 = r0.f16021n
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L33
            if (r1 != r3) goto L2b
            d7.m r0 = r0.f16018i
            k2.c.G(r5)
            goto L55
        L2b:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L33:
            k2.c.G(r5)
            boolean r5 = r4.f16022i
            if (r5 != 0) goto L5a
            d7.m r5 = r4.f16023l
            r0.f16018i = r5
            r0.f16021n = r3
            d7.m r1 = new d7.m
            d7.d r0 = t7.a.A(r0)
            r1.<init>(r0)
            r4.f16023l = r1
            java.lang.Object r0 = r1.b()
            e7.a r1 = e7.a.f15033i
            if (r0 != r1) goto L54
            return r1
        L54:
            r0 = r5
        L55:
            if (r0 == 0) goto L5a
            r0.resumeWith(r2)
        L5a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: i2.b.a(d7.d):java.lang.Object");
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ boolean all(r7.l lVar) {
        return androidx.compose.ui.b.a(this, lVar);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ boolean any(r7.l lVar) {
        return androidx.compose.ui.b.b(this, lVar);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ Object foldIn(Object obj, r7.p pVar) {
        return androidx.compose.ui.b.c(this, obj, pVar);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public final /* synthetic */ Object foldOut(Object obj, r7.p pVar) {
        return androidx.compose.ui.b.d(this, obj, pVar);
    }

    @Override // androidx.compose.ui.layout.OnGloballyPositionedModifier
    public final void onGloballyPositioned(LayoutCoordinates layoutCoordinates) {
        if (this.f16022i) {
            return;
        }
        this.f16022i = true;
        d7.m mVar = this.f16023l;
        if (mVar != null) {
            mVar.resumeWith(t0.f22605a);
        }
        this.f16023l = null;
    }

    @Override // androidx.compose.ui.Modifier
    public final /* synthetic */ Modifier then(Modifier modifier) {
        return androidx.compose.ui.a.a(this, modifier);
    }
}
