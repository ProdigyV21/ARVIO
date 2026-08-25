package dagger.hilt.android.internal.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import androidx.lifecycle.d1;
import androidx.lifecycle.g1;
import androidx.lifecycle.z0;
import b2.g;
import dagger.Module;
import dagger.hilt.EntryPoints;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.multibindings.Multibinds;
import java.io.Closeable;
import java.util.Map;
import javax.inject.Provider;
import o1.b;
import o1.c;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
public final class HiltViewModelFactory implements g1 {
    public static final b<l<Object, d1>> CREATION_CALLBACK_KEY = new b<l<Object, d1>>() { // from class: dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.1
    };
    private final g1 delegateFactory;
    private final g1 hiltViewModelFactory;
    private final Map<Class<?>, Boolean> hiltViewModelKeys;

    public interface ActivityCreatorEntryPoint {
        ViewModelComponentBuilder getViewModelComponentBuilder();

        Map<Class<?>, Boolean> getViewModelKeys();
    }

    public interface ViewModelFactoriesEntryPoint {
        Map<Class<?>, Object> getHiltViewModelAssistedMap();

        Map<Class<?>, Provider<d1>> getHiltViewModelMap();
    }

    @Module
    public interface ViewModelModule {
        @Multibinds
        Map<Class<?>, Object> hiltViewModelAssistedMap();

        @Multibinds
        Map<Class<?>, d1> hiltViewModelMap();
    }

    public HiltViewModelFactory(Map<Class<?>, Boolean> map, g1 g1Var, final ViewModelComponentBuilder viewModelComponentBuilder) {
        this.hiltViewModelKeys = map;
        this.delegateFactory = g1Var;
        this.hiltViewModelFactory = new g1() { // from class: dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.2
            private <T extends d1> T createViewModel(ViewModelComponent viewModelComponent, Class<T> cls, c cVar) {
                Provider<d1> provider = ((ViewModelFactoriesEntryPoint) EntryPoints.get(viewModelComponent, ViewModelFactoriesEntryPoint.class)).getHiltViewModelMap().get(cls);
                l lVar = (l) cVar.a(HiltViewModelFactory.CREATION_CALLBACK_KEY);
                Object obj = ((ViewModelFactoriesEntryPoint) EntryPoints.get(viewModelComponent, ViewModelFactoriesEntryPoint.class)).getHiltViewModelAssistedMap().get(cls);
                if (obj == null) {
                    if (lVar != null) {
                        throw new IllegalStateException("Found creation callback but class " + cls.getName() + " does not have an assisted factory specified in @HiltViewModel.");
                    }
                    if (provider != null) {
                        return (T) provider.get();
                    }
                    throw new IllegalStateException("Expected the @HiltViewModel-annotated class " + cls.getName() + " to be available in the multi-binding of @HiltViewModelMap but none was found.");
                }
                if (provider != null) {
                    throw new AssertionError("Found the @HiltViewModel-annotated class " + cls.getName() + " in both the multi-bindings of @HiltViewModelMap and @HiltViewModelAssistedMap.");
                }
                if (lVar != null) {
                    return (T) lVar.invoke(obj);
                }
                throw new IllegalStateException("Found @HiltViewModel-annotated class " + cls.getName() + " using @AssistedInject but no creation callback was provided in CreationExtras.");
            }

            @Override // androidx.lifecycle.g1
            public d1 create(Class cls) {
                throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
            }

            @Override // androidx.lifecycle.g1
            public <T extends d1> T create(Class<T> cls, c cVar) {
                final RetainedLifecycleImpl retainedLifecycleImpl = new RetainedLifecycleImpl();
                T t2 = (T) createViewModel(viewModelComponentBuilder.savedStateHandle(z0.c(cVar)).viewModelLifecycle(retainedLifecycleImpl).build(), cls, cVar);
                t2.addCloseable(new Closeable() { // from class: dagger.hilt.android.internal.lifecycle.a
                    @Override // java.io.Closeable, java.lang.AutoCloseable
                    public final void close() {
                        retainedLifecycleImpl.dispatchOnCleared();
                    }
                });
                return t2;
            }
        };
    }

    public static g1 createInternal(Activity activity, g gVar, Bundle bundle, g1 g1Var) {
        return createInternal(activity, g1Var);
    }

    @Override // androidx.lifecycle.g1
    public <T extends d1> T create(Class<T> cls, c cVar) {
        return this.hiltViewModelKeys.containsKey(cls) ? (T) this.hiltViewModelFactory.create(cls, cVar) : (T) this.delegateFactory.create(cls, cVar);
    }

    public static g1 createInternal(Activity activity, g1 g1Var) {
        ActivityCreatorEntryPoint activityCreatorEntryPoint = (ActivityCreatorEntryPoint) EntryPoints.get(activity, ActivityCreatorEntryPoint.class);
        return new HiltViewModelFactory(activityCreatorEntryPoint.getViewModelKeys(), g1Var, activityCreatorEntryPoint.getViewModelComponentBuilder());
    }

    @Override // androidx.lifecycle.g1
    public <T extends d1> T create(Class<T> cls) {
        if (this.hiltViewModelKeys.containsKey(cls)) {
            return (T) this.hiltViewModelFactory.create(cls);
        }
        return (T) this.delegateFactory.create(cls);
    }
}
