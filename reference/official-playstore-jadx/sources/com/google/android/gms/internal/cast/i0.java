package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import com.google.android.gms.cast.internal.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class i0 extends e0 implements j0 {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.android.gms.internal.cast.e0
    public final boolean zza(int i10, Parcel parcel, Parcel parcel2, int i11) {
        l0 k0Var = null;
        Bundle bundle = null;
        int i12 = 0;
        switch (i10) {
            case 1:
                Bundle bundle2 = (Bundle) a1.b(parcel, Bundle.CREATOR);
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder != null) {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
                    k0Var = iInterfaceQueryLocalInterface instanceof l0 ? (l0) iInterfaceQueryLocalInterface : new k0(strongBinder, "com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
                }
                a1.e(parcel);
                x0 x0Var = (x0) this;
                androidx.mediarouter.media.z zVarB = androidx.mediarouter.media.z.b(bundle2);
                if (zVarB != null) {
                    HashMap map = x0Var.f13675e;
                    if (!map.containsKey(zVarB)) {
                        map.put(zVarB, new HashSet());
                    }
                    ((Set) map.get(zVarB)).add(new p0(k0Var, x0Var, x0Var.f13676f));
                }
                parcel2.writeNoException();
                return true;
            case 2:
                Bundle bundle3 = (Bundle) a1.b(parcel, Bundle.CREATOR);
                int i13 = parcel.readInt();
                a1.e(parcel);
                x0 x0Var2 = (x0) this;
                androidx.mediarouter.media.z zVarB2 = androidx.mediarouter.media.z.b(bundle3);
                if (zVarB2 != null) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        x0Var2.c(zVarB2, i13);
                    } else {
                        new androidx.loader.content.j(Looper.getMainLooper(), 2).post(new android.support.v4.os.e(i13, 5, x0Var2, zVarB2));
                    }
                }
                parcel2.writeNoException();
                return true;
            case 3:
                Bundle bundle4 = (Bundle) a1.b(parcel, Bundle.CREATOR);
                a1.e(parcel);
                x0 x0Var3 = (x0) this;
                androidx.mediarouter.media.z zVarB3 = androidx.mediarouter.media.z.b(bundle4);
                if (zVarB3 != null) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        x0Var3.f0(zVarB3);
                    } else {
                        new androidx.loader.content.j(Looper.getMainLooper(), 2).post(new w0(x0Var3, zVarB3, i12));
                    }
                }
                parcel2.writeNoException();
                return true;
            case 4:
                Bundle bundle5 = (Bundle) a1.b(parcel, Bundle.CREATOR);
                int i14 = parcel.readInt();
                a1.e(parcel);
                x0 x0Var4 = (x0) this;
                androidx.mediarouter.media.z zVarB4 = androidx.mediarouter.media.z.b(bundle5);
                if (zVarB4 != null) {
                    x0Var4.f13673c.getClass();
                    androidx.mediarouter.media.k0.b();
                    androidx.mediarouter.media.f fVarC = androidx.mediarouter.media.k0.c();
                    ArrayList arrayList = fVarC.f4335j;
                    zVarB4.a();
                    if (!zVarB4.f4518b.isEmpty()) {
                        if ((i14 & 2) == 0 && fVarC.f4341q) {
                            i12 = 1;
                        } else {
                            androidx.mediarouter.media.o0 o0Var = fVarC.f4346v;
                            Object[] objArr = o0Var != null && o0Var.f4467b && fVarC.h();
                            int size = arrayList.size();
                            for (int i15 = 0; i15 < size; i15++) {
                                androidx.mediarouter.media.i0 i0Var = (androidx.mediarouter.media.i0) arrayList.get(i15);
                                if (((i14 & 1) == 0 || !i0Var.c()) && ((!objArr == true || i0Var.c() || i0Var.a() == fVarC.f4343s) && i0Var.e(zVarB4))) {
                                    i12 = 1;
                                }
                            }
                        }
                    }
                }
                parcel2.writeNoException();
                parcel2.writeInt(i12);
                return true;
            case 5:
                String string = parcel.readString();
                a1.e(parcel);
                Logger logger = x0.f13672j;
                logger.d("select route with routeId = %s", string);
                ((x0) this).f13673c.getClass();
                androidx.mediarouter.media.k0.b();
                Iterator it = androidx.mediarouter.media.k0.c().f4335j.iterator();
                while (true) {
                    if (it.hasNext()) {
                        androidx.mediarouter.media.i0 i0Var2 = (androidx.mediarouter.media.i0) it.next();
                        if (i0Var2.f4388c.equals(string)) {
                            logger.d("media route is found and selected", new Object[0]);
                            i0Var2.g(true);
                        }
                    }
                }
                parcel2.writeNoException();
                return true;
            case 6:
                ((x0) this).f13673c.getClass();
                androidx.mediarouter.media.k0.b();
                androidx.mediarouter.media.i0 i0Var3 = androidx.mediarouter.media.k0.c().f4347w;
                if (i0Var3 == null) {
                    throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
                }
                i0Var3.g(true);
                parcel2.writeNoException();
                return true;
            case 7:
                ((x0) this).f13673c.getClass();
                androidx.mediarouter.media.k0.b();
                androidx.mediarouter.media.i0 i0Var4 = androidx.mediarouter.media.k0.c().f4347w;
                if (i0Var4 == null) {
                    throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
                }
                androidx.mediarouter.media.k0.b();
                boolean zEquals = androidx.mediarouter.media.k0.c().g().f4388c.equals(i0Var4.f4388c);
                parcel2.writeNoException();
                int i16 = a1.f13157a;
                parcel2.writeInt(zEquals ? 1 : 0);
                return true;
            case 8:
                String string2 = parcel.readString();
                a1.e(parcel);
                ((x0) this).f13673c.getClass();
                androidx.mediarouter.media.k0.b();
                Iterator it2 = androidx.mediarouter.media.k0.c().f4335j.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        androidx.mediarouter.media.i0 i0Var5 = (androidx.mediarouter.media.i0) it2.next();
                        if (i0Var5.f4388c.equals(string2)) {
                            bundle = i0Var5.f4403s;
                        }
                    }
                }
                parcel2.writeNoException();
                if (bundle == null) {
                    parcel2.writeInt(0);
                    return true;
                }
                parcel2.writeInt(1);
                bundle.writeToParcel(parcel2, 1);
                return true;
            case 9:
                ((x0) this).f13673c.getClass();
                androidx.mediarouter.media.k0.b();
                String str = androidx.mediarouter.media.k0.c().g().f4388c;
                parcel2.writeNoException();
                parcel2.writeString(str);
                return true;
            case 10:
                parcel2.writeNoException();
                parcel2.writeInt(12451000);
                return true;
            case 11:
                x0 x0Var5 = (x0) this;
                HashMap map2 = x0Var5.f13675e;
                Iterator it3 = map2.values().iterator();
                while (it3.hasNext()) {
                    Iterator it4 = ((Set) it3.next()).iterator();
                    while (it4.hasNext()) {
                        x0Var5.f13673c.e((androidx.mediarouter.media.a0) it4.next());
                    }
                }
                map2.clear();
                parcel2.writeNoException();
                return true;
            case 12:
                ((x0) this).f13673c.getClass();
                androidx.mediarouter.media.k0.b();
                androidx.mediarouter.media.i0 i0Var6 = androidx.mediarouter.media.k0.c().x;
                if (i0Var6 != null) {
                    androidx.mediarouter.media.k0.b();
                    if (androidx.mediarouter.media.k0.c().g().f4388c.equals(i0Var6.f4388c)) {
                        i12 = 1;
                    }
                }
                parcel2.writeNoException();
                int i17 = a1.f13157a;
                parcel2.writeInt(i12);
                return true;
            case 13:
                int i18 = parcel.readInt();
                a1.e(parcel);
                ((x0) this).f13673c.getClass();
                androidx.mediarouter.media.k0.g(i18);
                parcel2.writeNoException();
                return true;
            case 14:
                String string3 = parcel.readString();
                a1.e(parcel);
                Logger logger2 = x0.f13672j;
                ((x0) this).f13673c.getClass();
                androidx.mediarouter.media.k0.b();
                for (androidx.mediarouter.media.c0 c0Var : androidx.mediarouter.media.k0.c().e()) {
                    if (c0Var.f4388c.equals(string3)) {
                        logger2.d("clean up the connectedGroupRoute = %s", c0Var);
                        androidx.mediarouter.media.k0.b();
                        if (androidx.mediarouter.media.k0.c().k.get(c0Var.f4388c) != null) {
                            throw new ClassCastException();
                        }
                    }
                }
                androidx.mediarouter.media.k0.b();
                androidx.mediarouter.media.i0 i0VarG = androidx.mediarouter.media.k0.c().g();
                if (i0VarG != null && !i0VarG.f4393h && i0VarG.f4388c.equals(string3)) {
                    logger2.d("clean up the selected route = %s", i0VarG);
                    androidx.mediarouter.media.k0.g(0);
                }
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
