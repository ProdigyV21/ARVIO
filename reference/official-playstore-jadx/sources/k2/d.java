package k2;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t.e f19462a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final t.e f19463b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final t.e f19464c;

    public d(t.e eVar, t.e eVar2, t.e eVar3) {
        this.f19462a = eVar;
        this.f19463b = eVar2;
        this.f19464c = eVar3;
    }

    public abstract e a();

    public final Class b(Class cls) throws ClassNotFoundException {
        String name = cls.getName();
        t.e eVar = this.f19464c;
        Class cls2 = (Class) eVar.get(name);
        if (cls2 != null) {
            return cls2;
        }
        Class<?> cls3 = Class.forName(cls.getPackage().getName() + "." + cls.getSimpleName() + "Parcelizer", false, cls.getClassLoader());
        eVar.put(cls.getName(), cls3);
        return cls3;
    }

    public final Method c(String str) throws NoSuchMethodException {
        t.e eVar = this.f19462a;
        Method method = (Method) eVar.get(str);
        if (method != null) {
            return method;
        }
        System.currentTimeMillis();
        Method declaredMethod = Class.forName(str, true, d.class.getClassLoader()).getDeclaredMethod("read", d.class);
        eVar.put(str, declaredMethod);
        return declaredMethod;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Method d(Class cls) throws NoSuchMethodException, ClassNotFoundException {
        String name = cls.getName();
        t.e eVar = this.f19463b;
        Method method = (Method) eVar.get(name);
        if (method != null) {
            return method;
        }
        Class clsB = b(cls);
        System.currentTimeMillis();
        Method declaredMethod = clsB.getDeclaredMethod("write", cls, d.class);
        eVar.put(cls.getName(), declaredMethod);
        return declaredMethod;
    }

    public abstract boolean e(int i10);

    public final int f(int i10, int i11) {
        return !e(i11) ? i10 : ((e) this).f19466e.readInt();
    }

    public final Parcelable g(Parcelable parcelable, int i10) {
        if (!e(i10)) {
            return parcelable;
        }
        return ((e) this).f19466e.readParcelable(e.class.getClassLoader());
    }

    public final f h() {
        String string = ((e) this).f19466e.readString();
        if (string == null) {
            return null;
        }
        try {
            return (f) c(string).invoke(null, a());
        } catch (ClassNotFoundException e5) {
            throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e5);
        } catch (IllegalAccessException e6) {
            throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e6);
        } catch (NoSuchMethodException e10) {
            throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e10);
        } catch (InvocationTargetException e11) {
            if (e11.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e11.getCause());
            }
            throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e11);
        }
    }

    public abstract void i(int i10);

    public final void j(int i10, int i11) {
        i(i11);
        ((e) this).f19466e.writeInt(i10);
    }

    public final void k(Parcelable parcelable, int i10) {
        i(i10);
        ((e) this).f19466e.writeParcelable(parcelable, 0);
    }

    public final void l(f fVar) {
        if (fVar == null) {
            ((e) this).f19466e.writeString(null);
            return;
        }
        try {
            ((e) this).f19466e.writeString(b(fVar.getClass()).getName());
            e eVarA = a();
            try {
                d(fVar.getClass()).invoke(null, fVar, eVarA);
                Parcel parcel = eVarA.f19466e;
                int i10 = eVarA.f19470i;
                if (i10 >= 0) {
                    int i11 = eVarA.f19465d.get(i10);
                    int iDataPosition = parcel.dataPosition();
                    parcel.setDataPosition(i11);
                    parcel.writeInt(iDataPosition - i11);
                    parcel.setDataPosition(iDataPosition);
                }
            } catch (ClassNotFoundException e5) {
                throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", e5);
            } catch (IllegalAccessException e6) {
                throw new RuntimeException("VersionedParcel encountered IllegalAccessException", e6);
            } catch (NoSuchMethodException e10) {
                throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", e10);
            } catch (InvocationTargetException e11) {
                if (!(e11.getCause() instanceof RuntimeException)) {
                    throw new RuntimeException("VersionedParcel encountered InvocationTargetException", e11);
                }
                throw ((RuntimeException) e11.getCause());
            }
        } catch (ClassNotFoundException e12) {
            throw new RuntimeException(fVar.getClass().getSimpleName().concat(" does not have a Parcelizer"), e12);
        }
    }
}
