package dagger.hilt.android.internal.lifecycle;

import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.internal.Factory;
import dagger.internal.Provider;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class DefaultViewModelFactories_InternalFactoryFactory_Factory implements Factory<DefaultViewModelFactories.InternalFactoryFactory> {
    private final Provider<Map<Class<?>, Boolean>> keySetProvider;
    private final Provider<ViewModelComponentBuilder> viewModelComponentBuilderProvider;

    private DefaultViewModelFactories_InternalFactoryFactory_Factory(Provider<Map<Class<?>, Boolean>> provider, Provider<ViewModelComponentBuilder> provider2) {
        this.keySetProvider = provider;
        this.viewModelComponentBuilderProvider = provider2;
    }

    public static DefaultViewModelFactories_InternalFactoryFactory_Factory create(Provider<Map<Class<?>, Boolean>> provider, Provider<ViewModelComponentBuilder> provider2) {
        return new DefaultViewModelFactories_InternalFactoryFactory_Factory(provider, provider2);
    }

    public static DefaultViewModelFactories.InternalFactoryFactory newInstance(Map<Class<?>, Boolean> map, ViewModelComponentBuilder viewModelComponentBuilder) {
        return new DefaultViewModelFactories.InternalFactoryFactory(map, viewModelComponentBuilder);
    }

    @Override // javax.inject.Provider
    public DefaultViewModelFactories.InternalFactoryFactory get() {
        return newInstance(this.keySetProvider.get(), this.viewModelComponentBuilderProvider.get());
    }
}
