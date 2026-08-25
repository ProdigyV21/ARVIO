package com.arflix.tv;

import com.arflix.tv.cast.CastManagerEntryPoint;
import com.arflix.tv.di.AppModule;
import com.arflix.tv.di.RepositoryAccessEntryPoint;
import com.arflix.tv.di.WorkerModule;
import com.arflix.tv.ui.components.HiltWrapper_ProfileAvatarVisualEntryPoint;
import com.arflix.tv.ui.components.TrailerPlayerEntryPoint;
import com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel_HiltModules;
import com.arflix.tv.ui.screens.details.DetailsViewModel_HiltModules;
import com.arflix.tv.ui.screens.home.HomeViewModel_HiltModules;
import com.arflix.tv.ui.screens.login.LoginViewModel_HiltModules;
import com.arflix.tv.ui.screens.player.PlayerViewModel_HiltModules;
import com.arflix.tv.ui.screens.plugin.PluginViewModel_HiltModules;
import com.arflix.tv.ui.screens.profile.ProfileViewModel_HiltModules;
import com.arflix.tv.ui.screens.search.SearchViewModel_HiltModules;
import com.arflix.tv.ui.screens.settings.SettingsViewModel_HiltModules;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel_HiltModules;
import com.arflix.tv.ui.screens.tv.TvViewModel_HiltModules;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel_HiltModules;
import com.arflix.tv.ui.startup.StartupViewModel_HiltModules;
import com.arflix.tv.updater.ApkInstallReceiver_GeneratedInjector;
import com.arflix.tv.worker.CloudSyncWorker_HiltModule;
import com.arflix.tv.worker.TraktSyncWorker;
import com.google.common.util.concurrent.r0;
import dagger.Binds;
import dagger.Component;
import dagger.Module;
import dagger.Subcomponent;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ServiceComponent;
import dagger.hilt.android.components.ViewComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.components.ViewWithFragmentComponent;
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.flags.HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_DefaultViewModelFactories_ActivityModule;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint;
import dagger.hilt.android.internal.lifecycle.HiltWrapper_HiltViewModelFactory_ViewModelModule;
import dagger.hilt.android.internal.managers.ActivityComponentManager;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint;
import dagger.hilt.android.internal.managers.HiltWrapper_ActivityRetainedComponentManager_LifecycleModule;
import dagger.hilt.android.internal.managers.HiltWrapper_SavedStateHandleModule;
import dagger.hilt.android.internal.managers.ServiceComponentManager;
import dagger.hilt.android.internal.managers.ViewComponentManager;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.HiltWrapper_ActivityModule;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedComponent;
import javax.inject.Singleton;

/* JADX INFO: loaded from: classes3.dex */
public final class ArflixApplication_HiltComponents {

    @Subcomponent(modules = {FragmentCBuilderModule.class, ViewCBuilderModule.class, HiltWrapper_ActivityModule.class, HiltWrapper_DefaultViewModelFactories_ActivityModule.class})
    public static abstract class ActivityC implements MainActivity_GeneratedInjector, ActivityComponent, DefaultViewModelFactories.ActivityEntryPoint, HiltWrapper_HiltViewModelFactory_ActivityCreatorEntryPoint, FragmentComponentManager.FragmentComponentBuilderEntryPoint, ViewComponentManager.ViewComponentBuilderEntryPoint, GeneratedComponent {

        @Subcomponent.Builder
        public interface Builder extends ActivityComponentBuilder {
        }
    }

    @Module(subcomponents = {ActivityC.class})
    public interface ActivityCBuilderModule {
        @Binds
        ActivityComponentBuilder bind(ActivityC.Builder builder);
    }

    @Subcomponent(modules = {ActivityCBuilderModule.class, ViewModelCBuilderModule.class, CollectionDetailsViewModel_HiltModules.KeyModule.class, DetailsViewModel_HiltModules.KeyModule.class, HiltWrapper_ActivityRetainedComponentManager_LifecycleModule.class, HiltWrapper_SavedStateHandleModule.class, HomeViewModel_HiltModules.KeyModule.class, LoginViewModel_HiltModules.KeyModule.class, PlayerViewModel_HiltModules.KeyModule.class, PluginViewModel_HiltModules.KeyModule.class, ProfileViewModel_HiltModules.KeyModule.class, SearchViewModel_HiltModules.KeyModule.class, SettingsViewModel_HiltModules.KeyModule.class, StartupViewModel_HiltModules.KeyModule.class, TelegramSettingsViewModel_HiltModules.KeyModule.class, TvViewModel_HiltModules.KeyModule.class, WatchlistViewModel_HiltModules.KeyModule.class})
    public static abstract class ActivityRetainedC implements ActivityRetainedComponent, ActivityComponentManager.ActivityComponentBuilderEntryPoint, HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedLifecycleEntryPoint, GeneratedComponent {

        @Subcomponent.Builder
        public interface Builder extends ActivityRetainedComponentBuilder {
        }
    }

    @Module(subcomponents = {ActivityRetainedC.class})
    public interface ActivityRetainedCBuilderModule {
        @Binds
        ActivityRetainedComponentBuilder bind(ActivityRetainedC.Builder builder);
    }

    @Subcomponent(modules = {ViewWithFragmentCBuilderModule.class})
    public static abstract class FragmentC implements FragmentComponent, DefaultViewModelFactories.FragmentEntryPoint, ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint, GeneratedComponent {

        @Subcomponent.Builder
        public interface Builder extends FragmentComponentBuilder {
        }
    }

    @Module(subcomponents = {FragmentC.class})
    public interface FragmentCBuilderModule {
        @Binds
        FragmentComponentBuilder bind(FragmentC.Builder builder);
    }

    @Subcomponent
    public static abstract class ServiceC implements ServiceComponent, GeneratedComponent {

        @Subcomponent.Builder
        public interface Builder extends ServiceComponentBuilder {
        }
    }

    @Module(subcomponents = {ServiceC.class})
    public interface ServiceCBuilderModule {
        @Binds
        ServiceComponentBuilder bind(ServiceC.Builder builder);
    }

    @Component(modules = {AppModule.class, ApplicationContextModule.class, ActivityRetainedCBuilderModule.class, ServiceCBuilderModule.class, CloudSyncWorker_HiltModule.class, HiltWrapper_FragmentGetContextFix_FragmentGetContextFixModule.class, r0.class, WorkerModule.class})
    @Singleton
    public static abstract class SingletonC implements ArflixApplication_GeneratedInjector, CastManagerEntryPoint, RepositoryAccessEntryPoint, HiltWrapper_ProfileAvatarVisualEntryPoint, TrailerPlayerEntryPoint, ApkInstallReceiver_GeneratedInjector, TraktSyncWorker.TraktSyncWorkerEntryPoint, FragmentGetContextFix.FragmentGetContextFixEntryPoint, HiltWrapper_ActivityRetainedComponentManager_ActivityRetainedComponentBuilderEntryPoint, ServiceComponentManager.ServiceComponentBuilderEntryPoint, SingletonComponent, GeneratedComponent {
    }

    @Subcomponent
    public static abstract class ViewC implements ViewComponent, GeneratedComponent {

        @Subcomponent.Builder
        public interface Builder extends ViewComponentBuilder {
        }
    }

    @Module(subcomponents = {ViewC.class})
    public interface ViewCBuilderModule {
        @Binds
        ViewComponentBuilder bind(ViewC.Builder builder);
    }

    @Subcomponent(modules = {CollectionDetailsViewModel_HiltModules.BindsModule.class, DetailsViewModel_HiltModules.BindsModule.class, HiltWrapper_HiltViewModelFactory_ViewModelModule.class, HomeViewModel_HiltModules.BindsModule.class, LoginViewModel_HiltModules.BindsModule.class, PlayerViewModel_HiltModules.BindsModule.class, PluginViewModel_HiltModules.BindsModule.class, ProfileViewModel_HiltModules.BindsModule.class, SearchViewModel_HiltModules.BindsModule.class, SettingsViewModel_HiltModules.BindsModule.class, StartupViewModel_HiltModules.BindsModule.class, TelegramSettingsViewModel_HiltModules.BindsModule.class, TvViewModel_HiltModules.BindsModule.class, WatchlistViewModel_HiltModules.BindsModule.class})
    public static abstract class ViewModelC implements ViewModelComponent, HiltViewModelFactory.ViewModelFactoriesEntryPoint, GeneratedComponent {

        @Subcomponent.Builder
        public interface Builder extends ViewModelComponentBuilder {
        }
    }

    @Module(subcomponents = {ViewModelC.class})
    public interface ViewModelCBuilderModule {
        @Binds
        ViewModelComponentBuilder bind(ViewModelC.Builder builder);
    }

    @Subcomponent
    public static abstract class ViewWithFragmentC implements ViewWithFragmentComponent, GeneratedComponent {

        @Subcomponent.Builder
        public interface Builder extends ViewWithFragmentComponentBuilder {
        }
    }

    @Module(subcomponents = {ViewWithFragmentC.class})
    public interface ViewWithFragmentCBuilderModule {
        @Binds
        ViewWithFragmentComponentBuilder bind(ViewWithFragmentC.Builder builder);
    }

    private ArflixApplication_HiltComponents() {
    }
}
