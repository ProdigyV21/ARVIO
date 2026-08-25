package cb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 extends a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ya.h f7501a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ya.h f7502b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f7503c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f0 f7504d;

    public g0(ya.h hVar, ya.h hVar2, byte b10) {
        this.f7501a = hVar;
        this.f7502b = hVar2;
    }

    @Override // cb.a
    public final Object a() {
        switch (this.f7503c) {
            case 0:
                return new HashMap();
            default:
                return new LinkedHashMap();
        }
    }

    @Override // cb.a
    public final int b(Object obj) {
        int size;
        switch (this.f7503c) {
            case 0:
                size = ((HashMap) obj).size();
                break;
            default:
                size = ((LinkedHashMap) obj).size();
                break;
        }
        return size * 2;
    }

    @Override // cb.a
    public final Iterator c(Object obj) {
        switch (this.f7503c) {
        }
        return ((Map) obj).entrySet().iterator();
    }

    @Override // cb.a
    public final int d(Object obj) {
        switch (this.f7503c) {
        }
        return ((Map) obj).size();
    }

    @Override // cb.a
    public final void f(bb.b bVar, int i10, Object obj) {
        Map map = (Map) obj;
        Object objD = bVar.d(getDescriptor(), i10, this.f7501a, null);
        int iW = bVar.w(getDescriptor());
        if (iW != i10 + 1) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.n(i10, iW, "Value must follow key in a map, index for key: ", ", returned index for value: ").toString());
        }
        boolean zContainsKey = map.containsKey(objD);
        ya.h hVar = this.f7502b;
        map.put(objD, (!zContainsKey || (hVar.getDescriptor().getKind() instanceof ab.f)) ? bVar.d(getDescriptor(), iW, hVar, null) : bVar.d(getDescriptor(), iW, hVar, kotlin.collections.h0.s0(map, objD)));
    }

    @Override // cb.a
    public final Object g(Object obj) {
        switch (this.f7503c) {
            case 0:
                return new HashMap((Map) null);
            default:
                return new LinkedHashMap((Map) null);
        }
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        switch (this.f7503c) {
        }
        return this.f7504d;
    }

    @Override // cb.a
    public final Object h(Object obj) {
        switch (this.f7503c) {
            case 0:
                return (HashMap) obj;
            default:
                return (LinkedHashMap) obj;
        }
    }

    @Override // ya.s
    public final void serialize(bb.e eVar, Object obj) {
        d(obj);
        bb.c cVarV = eVar.v(getDescriptor());
        Iterator itC = c(obj);
        int i10 = 0;
        while (itC.hasNext()) {
            Map.Entry entry = (Map.Entry) itC.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            int i11 = i10 + 1;
            cVarV.j(getDescriptor(), i10, this.f7501a, key);
            i10 += 2;
            cVarV.j(getDescriptor(), i11, this.f7502b, value);
        }
        cVarV.e();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public g0(ya.h hVar, ya.h hVar2, int i10) {
        this(hVar, hVar2, (byte) 0);
        this.f7503c = i10;
        switch (i10) {
            case 1:
                this(hVar, hVar2, (byte) 0);
                this.f7504d = new f0("kotlin.collections.LinkedHashMap", hVar.getDescriptor(), hVar2.getDescriptor());
                break;
            default:
                this.f7504d = new f0("kotlin.collections.HashMap", hVar.getDescriptor(), hVar2.getDescriptor());
                break;
        }
    }
}
