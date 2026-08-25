package androidx.lifecycle;

import android.app.Application;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes3.dex */
public final class f1 extends y0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static f1 f3230d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Application f3231c;

    public f1(Application application) {
        super(1);
        this.f3231c = application;
    }

    public final d1 a(Class cls, Application application) {
        if (!AndroidViewModel.class.isAssignableFrom(cls)) {
            return super.create(cls);
        }
        try {
            return (d1) cls.getConstructor(Application.class).newInstance(application);
        } catch (IllegalAccessException e5) {
            throw new RuntimeException("Cannot create an instance of " + cls, e5);
        } catch (InstantiationException e6) {
            throw new RuntimeException("Cannot create an instance of " + cls, e6);
        } catch (NoSuchMethodException e10) {
            throw new RuntimeException("Cannot create an instance of " + cls, e10);
        } catch (InvocationTargetException e11) {
            throw new RuntimeException("Cannot create an instance of " + cls, e11);
        }
    }

    @Override // androidx.lifecycle.y0, androidx.lifecycle.g1
    public final d1 create(Class cls, o1.c cVar) {
        if (this.f3231c != null) {
            return create(cls);
        }
        Application application = (Application) cVar.a(e1.f3225a);
        if (application != null) {
            return a(cls, application);
        }
        if (AndroidViewModel.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("CreationExtras must have an application by `APPLICATION_KEY`");
        }
        return super.create(cls);
    }

    @Override // androidx.lifecycle.y0, androidx.lifecycle.g1
    public final d1 create(Class cls) {
        Application application = this.f3231c;
        if (application != null) {
            return a(cls, application);
        }
        throw new UnsupportedOperationException("AndroidViewModelFactory constructed with empty constructor works only with create(modelClass: Class<T>, extras: CreationExtras).");
    }
}
