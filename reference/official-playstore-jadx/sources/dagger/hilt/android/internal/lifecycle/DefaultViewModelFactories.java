package dagger.hilt.android.internal.lifecycle;

import androidx.activity.t;
import androidx.fragment.app.c0;
import androidx.lifecycle.g1;
import dagger.Module;
import dagger.hilt.EntryPoints;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.internal.Preconditions;
import dagger.multibindings.Multibinds;
import java.util.Map;
import javax.inject.Inject;

/* JADX INFO: loaded from: classes4.dex */
public final class DefaultViewModelFactories {

    public interface ActivityEntryPoint {
        InternalFactoryFactory getHiltInternalFactoryFactory();
    }

    @Module
    public interface ActivityModule {
        @Multibinds
        Map<Class<?>, Boolean> viewModelKeys();
    }

    public interface FragmentEntryPoint {
        InternalFactoryFactory getHiltInternalFactoryFactory();
    }

    public static final class InternalFactoryFactory {
        private final Map<Class<?>, Boolean> keySet;
        private final ViewModelComponentBuilder viewModelComponentBuilder;

        @Inject
        public InternalFactoryFactory(Map<Class<?>, Boolean> map, ViewModelComponentBuilder viewModelComponentBuilder) {
            this.keySet = map;
            this.viewModelComponentBuilder = viewModelComponentBuilder;
        }

        private g1 getHiltViewModelFactory(g1 g1Var) {
            return new HiltViewModelFactory(this.keySet, (g1) Preconditions.checkNotNull(g1Var), this.viewModelComponentBuilder);
        }

        public g1 fromActivity(t tVar, g1 g1Var) {
            return getHiltViewModelFactory(g1Var);
        }

        public g1 fromFragment(c0 c0Var, g1 g1Var) {
            return getHiltViewModelFactory(g1Var);
        }
    }

    private DefaultViewModelFactories() {
    }

    public static g1 getActivityFactory(t tVar, g1 g1Var) {
        return ((ActivityEntryPoint) EntryPoints.get(tVar, ActivityEntryPoint.class)).getHiltInternalFactoryFactory().fromActivity(tVar, g1Var);
    }

    public static g1 getFragmentFactory(c0 c0Var, g1 g1Var) {
        return ((FragmentEntryPoint) EntryPoints.get(c0Var, FragmentEntryPoint.class)).getHiltInternalFactoryFactory().fromFragment(c0Var, g1Var);
    }
}
