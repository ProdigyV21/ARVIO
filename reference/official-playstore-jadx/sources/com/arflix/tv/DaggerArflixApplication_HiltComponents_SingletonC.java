package com.arflix.tv;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.fragment.app.c0;
import androidx.lifecycle.d1;
import androidx.lifecycle.w0;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.work.WorkerParameters;
import com.arflix.tv.ArflixApplication_HiltComponents;
import com.arflix.tv.cast.CastManager;
import com.arflix.tv.core.plugin.PluginManager;
import com.arflix.tv.data.api.AniSkipApi;
import com.arflix.tv.data.api.ArmApi;
import com.arflix.tv.data.api.InAppYouTubeExtractor;
import com.arflix.tv.data.api.IntroDbApi;
import com.arflix.tv.data.api.MdbListApi;
import com.arflix.tv.data.api.SimklApi;
import com.arflix.tv.data.api.StreamApi;
import com.arflix.tv.data.api.SupabaseApi;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.local.PluginDataStore;
import com.arflix.tv.data.local.ProfileDataStoreFactory;
import com.arflix.tv.data.repository.AppUsageAnalyticsRepository;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.CatalogDiscoveryRepository;
import com.arflix.tv.data.repository.CatalogRepository;
import com.arflix.tv.data.repository.CloudSyncCoordinator;
import com.arflix.tv.data.repository.CloudSyncInvalidationBus;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.ContinueWatchingUpdates;
import com.arflix.tv.data.repository.HomeServerRepository;
import com.arflix.tv.data.repository.HttpLocalScraperRuntime;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import com.arflix.tv.data.repository.MdbListRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.PlaybackTelemetryRepository;
import com.arflix.tv.data.repository.ProfileAvatarImageManager;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.RealtimeSyncManager;
import com.arflix.tv.data.repository.SkipIntroRepository;
import com.arflix.tv.data.repository.SportsRepository;
import com.arflix.tv.data.repository.StreamRepository;
import com.arflix.tv.data.repository.TraktOutboxRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.TraktSyncService;
import com.arflix.tv.data.repository.TvDeviceAuthRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.data.repository.simkl.SimklAuthManager;
import com.arflix.tv.data.repository.simkl.SimklScrobbler;
import com.arflix.tv.data.repository.simkl.SimklSyncService;
import com.arflix.tv.data.repository.sync.MdbListRemoteProvider;
import com.arflix.tv.data.repository.sync.RemoteSyncManager;
import com.arflix.tv.data.repository.sync.SimklRemoteProvider;
import com.arflix.tv.data.repository.sync.SyncProviderStore;
import com.arflix.tv.data.repository.sync.TraktRemoteProvider;
import com.arflix.tv.data.telegram.TelegramClient;
import com.arflix.tv.data.telegram.TelegramRepository;
import com.arflix.tv.data.telegram.TelegramSearchMatcher;
import com.arflix.tv.data.telegram.TelegramSourceResolver;
import com.arflix.tv.data.telegram.TelegramStreamingProxy;
import com.arflix.tv.di.AppModule_ProvideAniSkipApiFactory;
import com.arflix.tv.di.AppModule_ProvideAniSkipRetrofitFactory;
import com.arflix.tv.di.AppModule_ProvideArmApiFactory;
import com.arflix.tv.di.AppModule_ProvideArmRetrofitFactory;
import com.arflix.tv.di.AppModule_ProvideIntroDbApiFactory;
import com.arflix.tv.di.AppModule_ProvideIntroDbRetrofitFactory;
import com.arflix.tv.di.AppModule_ProvideMdbListApiFactory;
import com.arflix.tv.di.AppModule_ProvideMoshiFactory;
import com.arflix.tv.di.AppModule_ProvideOkHttpClientFactory;
import com.arflix.tv.di.AppModule_ProvideSimklApiFactory;
import com.arflix.tv.di.AppModule_ProvideStreamApiFactory;
import com.arflix.tv.di.AppModule_ProvideSupabaseApiFactory;
import com.arflix.tv.di.AppModule_ProvideTmdbApiFactory;
import com.arflix.tv.di.AppModule_ProvideTraktApiFactory;
import com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel;
import com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel_HiltModules;
import com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.collections.CollectionDetailsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.details.DetailsViewModel;
import com.arflix.tv.ui.screens.details.DetailsViewModel_HiltModules;
import com.arflix.tv.ui.screens.details.DetailsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.details.DetailsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.home.HomeViewModel;
import com.arflix.tv.ui.screens.home.HomeViewModel_HiltModules;
import com.arflix.tv.ui.screens.home.HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.home.HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.login.LoginViewModel;
import com.arflix.tv.ui.screens.login.LoginViewModel_HiltModules;
import com.arflix.tv.ui.screens.login.LoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.login.LoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.player.PlayerViewModel;
import com.arflix.tv.ui.screens.player.PlayerViewModel_HiltModules;
import com.arflix.tv.ui.screens.player.PlayerViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.player.PlayerViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.plugin.PluginViewModel;
import com.arflix.tv.ui.screens.plugin.PluginViewModel_HiltModules;
import com.arflix.tv.ui.screens.plugin.PluginViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.plugin.PluginViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.profile.ProfileViewModel;
import com.arflix.tv.ui.screens.profile.ProfileViewModel_HiltModules;
import com.arflix.tv.ui.screens.profile.ProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.profile.ProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.search.SearchViewModel;
import com.arflix.tv.ui.screens.search.SearchViewModel_HiltModules;
import com.arflix.tv.ui.screens.search.SearchViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.search.SearchViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.settings.SettingsViewModel;
import com.arflix.tv.ui.screens.settings.SettingsViewModel_HiltModules;
import com.arflix.tv.ui.screens.settings.SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.settings.SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel_HiltModules;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import com.arflix.tv.ui.screens.tv.TvViewModel_HiltModules;
import com.arflix.tv.ui.screens.tv.TvViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.tv.TvViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel_HiltModules;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.ui.startup.StartupViewModel;
import com.arflix.tv.ui.startup.StartupViewModel_HiltModules;
import com.arflix.tv.ui.startup.StartupViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.arflix.tv.ui.startup.StartupViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.arflix.tv.updater.ApkDownloader;
import com.arflix.tv.updater.ApkInstallReceiver;
import com.arflix.tv.updater.ApkInstallReceiver_MembersInjector;
import com.arflix.tv.updater.AppUpdateRepository;
import com.arflix.tv.updater.UpdatePreferences;
import com.arflix.tv.updater.UpdateStatusManager;
import com.arflix.tv.util.AnimeMapper;
import com.arflix.tv.worker.CloudSyncWorker;
import com.arflix.tv.worker.CloudSyncWorker_AssistedFactory;
import com.google.common.collect.a0;
import com.google.common.collect.k1;
import com.google.common.collect.t3;
import com.google.common.collect.w1;
import com.google.common.collect.w3;
import com.squareup.moshi.z;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import gb.h0;
import java.util.Map;
import java.util.Set;
import m2.s;
import retrofit2.u0;

/* JADX INFO: loaded from: classes3.dex */
public final class DaggerArflixApplication_HiltComponents_SingletonC {

    public static final class ActivityCBuilder implements ArflixApplication_HiltComponents.ActivityC.Builder {
        private Activity activity;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;

        public /* synthetic */ ActivityCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, int i10) {
            this(singletonCImpl, activityRetainedCImpl);
        }

        private ActivityCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityComponentBuilder
        public ActivityCBuilder activity(Activity activity) {
            this.activity = (Activity) Preconditions.checkNotNull(activity);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityComponentBuilder
        public ArflixApplication_HiltComponents.ActivityC build() {
            Preconditions.checkBuilderRequirement(this.activity, Activity.class);
            return new ActivityCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activity);
        }
    }

    public static final class ActivityCImpl extends ArflixApplication_HiltComponents.ActivityC {
        private final ActivityCImpl activityCImpl = this;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;

        public ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, Activity activity) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        private MainActivity injectMainActivity2(MainActivity mainActivity) {
            MainActivity_MembersInjector.injectAuthRepository(mainActivity, DoubleCheck.lazy((Provider) this.singletonCImpl.authRepositoryProvider));
            MainActivity_MembersInjector.injectProfileRepository(mainActivity, DoubleCheck.lazy((Provider) this.singletonCImpl.profileRepositoryProvider));
            MainActivity_MembersInjector.injectTraktRepository(mainActivity, DoubleCheck.lazy((Provider) this.singletonCImpl.traktRepositoryProvider));
            MainActivity_MembersInjector.injectProfileManager(mainActivity, DoubleCheck.lazy((Provider) this.singletonCImpl.profileManagerProvider));
            MainActivity_MembersInjector.injectWatchHistoryRepository(mainActivity, DoubleCheck.lazy((Provider) this.singletonCImpl.watchHistoryRepositoryProvider));
            MainActivity_MembersInjector.injectWatchlistRepository(mainActivity, DoubleCheck.lazy((Provider) this.singletonCImpl.watchlistRepositoryProvider));
            MainActivity_MembersInjector.injectLauncherContinueWatchingRepository(mainActivity, DoubleCheck.lazy((Provider) this.singletonCImpl.launcherContinueWatchingRepositoryProvider));
            MainActivity_MembersInjector.injectMediaRepository(mainActivity, DoubleCheck.lazy((Provider) this.singletonCImpl.mediaRepositoryProvider));
            MainActivity_MembersInjector.injectIptvRepository(mainActivity, DoubleCheck.lazy((Provider) this.singletonCImpl.iptvRepositoryProvider));
            return mainActivity;
        }

        @Override // dagger.hilt.android.internal.managers.FragmentComponentManager.FragmentComponentBuilderEntryPoint
        public FragmentComponentBuilder fragmentComponentBuilder() {
            return new FragmentCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, 0);
        }

        @Override // dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories.ActivityEntryPoint
        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
            return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(this.singletonCImpl, this.activityRetainedCImpl, 0));
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ActivityCreatorEntryPoint
        public ViewModelComponentBuilder getViewModelComponentBuilder() {
            return new ViewModelCBuilder(this.singletonCImpl, this.activityRetainedCImpl, 0);
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ActivityCreatorEntryPoint
        public Map<Class<?>, Boolean> getViewModelKeys() {
            a0.c(13, "expectedSize");
            k1 k1Var = new k1(13);
            k1Var.c(CollectionDetailsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(CollectionDetailsViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(DetailsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(DetailsViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(HomeViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(LoginViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(LoginViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(PlayerViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(PlayerViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(PluginViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(PluginViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(ProfileViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(ProfileViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(SearchViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(SearchViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(SettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(SettingsViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(StartupViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(StartupViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(TelegramSettingsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(TelegramSettingsViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(TvViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(TvViewModel_HiltModules.KeyModule.provide()));
            k1Var.c(WatchlistViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, Boolean.valueOf(WatchlistViewModel_HiltModules.KeyModule.provide()));
            return LazyClassKeyMap.of(k1Var.a(true));
        }

        @Override // com.arflix.tv.MainActivity_GeneratedInjector
        public void injectMainActivity(MainActivity mainActivity) {
            injectMainActivity2(mainActivity);
        }

        @Override // dagger.hilt.android.internal.managers.ViewComponentManager.ViewComponentBuilderEntryPoint
        public ViewComponentBuilder viewComponentBuilder() {
            return new ViewCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, 0);
        }
    }

    public static final class ActivityRetainedCBuilder implements ArflixApplication_HiltComponents.ActivityRetainedC.Builder {
        private SavedStateHandleHolder savedStateHandleHolder;
        private final SingletonCImpl singletonCImpl;

        public /* synthetic */ ActivityRetainedCBuilder(SingletonCImpl singletonCImpl, int i10) {
            this(singletonCImpl);
        }

        private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
            this.singletonCImpl = singletonCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder
        public ArflixApplication_HiltComponents.ActivityRetainedC build() {
            Preconditions.checkBuilderRequirement(this.savedStateHandleHolder, SavedStateHandleHolder.class);
            return new ActivityRetainedCImpl(this.singletonCImpl, this.savedStateHandleHolder);
        }

        @Override // dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder
        public ActivityRetainedCBuilder savedStateHandleHolder(SavedStateHandleHolder savedStateHandleHolder) {
            this.savedStateHandleHolder = (SavedStateHandleHolder) Preconditions.checkNotNull(savedStateHandleHolder);
            return this;
        }
    }

    public static final class ActivityRetainedCImpl extends ArflixApplication_HiltComponents.ActivityRetainedC {
        private final ActivityRetainedCImpl activityRetainedCImpl = this;
        Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;
        private final SingletonCImpl singletonCImpl;

        public static final class SwitchingProvider<T> implements Provider<T> {
            private final ActivityRetainedCImpl activityRetainedCImpl;
            private final int id;
            private final SingletonCImpl singletonCImpl;

            public SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, int i10) {
                this.singletonCImpl = singletonCImpl;
                this.activityRetainedCImpl = activityRetainedCImpl;
                this.id = i10;
            }

            @Override // javax.inject.Provider
            public T get() {
                if (this.id == 0) {
                    return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();
                }
                throw new AssertionError(this.id);
            }
        }

        public ActivityRetainedCImpl(SingletonCImpl singletonCImpl, SavedStateHandleHolder savedStateHandleHolder) {
            this.singletonCImpl = singletonCImpl;
            initialize(savedStateHandleHolder);
        }

        private void initialize(SavedStateHandleHolder savedStateHandleHolder) {
            this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, 0));
        }

        @Override // dagger.hilt.android.internal.managers.ActivityComponentManager.ActivityComponentBuilderEntryPoint
        public ActivityComponentBuilder activityComponentBuilder() {
            return new ActivityCBuilder(this.singletonCImpl, this.activityRetainedCImpl, 0);
        }

        @Override // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.ActivityRetainedLifecycleEntryPoint
        public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
            return this.provideActivityRetainedLifecycleProvider.get();
        }
    }

    public static final class Builder {
        private ApplicationContextModule applicationContextModule;

        public /* synthetic */ Builder(int i10) {
            this();
        }

        public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
            this.applicationContextModule = (ApplicationContextModule) Preconditions.checkNotNull(applicationContextModule);
            return this;
        }

        public ArflixApplication_HiltComponents.SingletonC build() {
            Preconditions.checkBuilderRequirement(this.applicationContextModule, ApplicationContextModule.class);
            return new SingletonCImpl(this.applicationContextModule);
        }

        private Builder() {
        }
    }

    public static final class FragmentCBuilder implements ArflixApplication_HiltComponents.FragmentC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private c0 fragment;
        private final SingletonCImpl singletonCImpl;

        public /* synthetic */ FragmentCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, int i10) {
            this(singletonCImpl, activityRetainedCImpl, activityCImpl);
        }

        private FragmentCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.FragmentComponentBuilder
        public ArflixApplication_HiltComponents.FragmentC build() {
            Preconditions.checkBuilderRequirement(this.fragment, c0.class);
            return new FragmentCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragment);
        }

        @Override // dagger.hilt.android.internal.builders.FragmentComponentBuilder
        public FragmentCBuilder fragment(c0 c0Var) {
            this.fragment = (c0) Preconditions.checkNotNull(c0Var);
            return this;
        }
    }

    public static final class FragmentCImpl extends ArflixApplication_HiltComponents.FragmentC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl = this;
        private final SingletonCImpl singletonCImpl;

        public FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, c0 c0Var) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories.FragmentEntryPoint
        public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
            return this.activityCImpl.getHiltInternalFactoryFactory();
        }

        @Override // dagger.hilt.android.internal.managers.ViewComponentManager.ViewWithFragmentComponentBuilderEntryPoint
        public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
            return new ViewWithFragmentCBuilder(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragmentCImpl, 0);
        }
    }

    public static final class ServiceCBuilder implements ArflixApplication_HiltComponents.ServiceC.Builder {
        private Service service;
        private final SingletonCImpl singletonCImpl;

        public /* synthetic */ ServiceCBuilder(SingletonCImpl singletonCImpl, int i10) {
            this(singletonCImpl);
        }

        private ServiceCBuilder(SingletonCImpl singletonCImpl) {
            this.singletonCImpl = singletonCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ServiceComponentBuilder
        public ArflixApplication_HiltComponents.ServiceC build() {
            Preconditions.checkBuilderRequirement(this.service, Service.class);
            return new ServiceCImpl(this.singletonCImpl, this.service);
        }

        @Override // dagger.hilt.android.internal.builders.ServiceComponentBuilder
        public ServiceCBuilder service(Service service) {
            this.service = (Service) Preconditions.checkNotNull(service);
            return this;
        }
    }

    public static final class ServiceCImpl extends ArflixApplication_HiltComponents.ServiceC {
        private final ServiceCImpl serviceCImpl = this;
        private final SingletonCImpl singletonCImpl;

        public ServiceCImpl(SingletonCImpl singletonCImpl, Service service) {
            this.singletonCImpl = singletonCImpl;
        }
    }

    public static final class SingletonCImpl extends ArflixApplication_HiltComponents.SingletonC {
        Provider<AnimeMapper> animeMapperProvider;
        Provider<ApkDownloader> apkDownloaderProvider;
        Provider<AppUpdateRepository> appUpdateRepositoryProvider;
        Provider<AppUsageAnalyticsRepository> appUsageAnalyticsRepositoryProvider;
        private final ApplicationContextModule applicationContextModule;
        Provider<AuthRepository> authRepositoryProvider;
        Provider<CastManager> castManagerProvider;
        Provider<CatalogDiscoveryRepository> catalogDiscoveryRepositoryProvider;
        Provider<CatalogRepository> catalogRepositoryProvider;
        Provider<CloudSyncCoordinator> cloudSyncCoordinatorProvider;
        Provider<CloudSyncInvalidationBus> cloudSyncInvalidationBusProvider;
        Provider<CloudSyncRepository> cloudSyncRepositoryProvider;
        Provider<CloudSyncWorker_AssistedFactory> cloudSyncWorker_AssistedFactoryProvider;
        Provider<ContinueWatchingUpdates> continueWatchingUpdatesProvider;
        Provider<HomeServerRepository> homeServerRepositoryProvider;
        Provider<HttpLocalScraperRuntime> httpLocalScraperRuntimeProvider;
        Provider<InAppYouTubeExtractor> inAppYouTubeExtractorProvider;
        Provider<IptvRepository> iptvRepositoryProvider;
        Provider<LauncherContinueWatchingRepository> launcherContinueWatchingRepositoryProvider;
        Provider<MdbListRemoteProvider> mdbListRemoteProvider;
        Provider<MdbListRepository> mdbListRepositoryProvider;
        Provider<MediaRepository> mediaRepositoryProvider;
        Provider<PlaybackTelemetryRepository> playbackTelemetryRepositoryProvider;
        Provider<PluginDataStore> pluginDataStoreProvider;
        Provider<PluginManager> pluginManagerProvider;
        Provider<ProfileAvatarImageManager> profileAvatarImageManagerProvider;
        Provider<ProfileDataStoreFactory> profileDataStoreFactoryProvider;
        Provider<ProfileManager> profileManagerProvider;
        Provider<ProfileRepository> profileRepositoryProvider;
        Provider<AniSkipApi> provideAniSkipApiProvider;
        Provider<u0> provideAniSkipRetrofitProvider;
        Provider<ArmApi> provideArmApiProvider;
        Provider<u0> provideArmRetrofitProvider;
        Provider<IntroDbApi> provideIntroDbApiProvider;
        Provider<u0> provideIntroDbRetrofitProvider;
        Provider<MdbListApi> provideMdbListApiProvider;
        Provider<z> provideMoshiProvider;
        Provider<h0> provideOkHttpClientProvider;
        Provider<SimklApi> provideSimklApiProvider;
        Provider<StreamApi> provideStreamApiProvider;
        Provider<SupabaseApi> provideSupabaseApiProvider;
        Provider<TmdbApi> provideTmdbApiProvider;
        Provider<TraktApi> provideTraktApiProvider;
        Provider<RealtimeSyncManager> realtimeSyncManagerProvider;
        Provider<RemoteSyncManager> remoteSyncManagerProvider;
        Provider<SimklAuthManager> simklAuthManagerProvider;
        Provider<SimklRemoteProvider> simklRemoteProvider;
        Provider<SimklScrobbler> simklScrobblerProvider;
        Provider<SimklSyncService> simklSyncServiceProvider;
        private final SingletonCImpl singletonCImpl = this;
        Provider<SkipIntroRepository> skipIntroRepositoryProvider;
        Provider<SportsRepository> sportsRepositoryProvider;
        Provider<StreamRepository> streamRepositoryProvider;
        Provider<SyncProviderStore> syncProviderStoreProvider;
        Provider<TelegramClient> telegramClientProvider;
        Provider<TelegramRepository> telegramRepositoryProvider;
        Provider<TelegramSearchMatcher> telegramSearchMatcherProvider;
        Provider<TelegramSourceResolver> telegramSourceResolverProvider;
        Provider<TelegramStreamingProxy> telegramStreamingProxyProvider;
        Provider<TraktOutboxRepository> traktOutboxRepositoryProvider;
        Provider<TraktRemoteProvider> traktRemoteProvider;
        Provider<TraktRepository> traktRepositoryProvider;
        Provider<TraktSyncService> traktSyncServiceProvider;
        Provider<TvDeviceAuthRepository> tvDeviceAuthRepositoryProvider;
        Provider<UpdatePreferences> updatePreferencesProvider;
        Provider<UpdateStatusManager> updateStatusManagerProvider;
        Provider<WatchHistoryRepository> watchHistoryRepositoryProvider;
        Provider<WatchlistRepository> watchlistRepositoryProvider;

        public static final class SwitchingProvider<T> implements Provider<T> {
            private final int id;
            private final SingletonCImpl singletonCImpl;

            public SwitchingProvider(SingletonCImpl singletonCImpl, int i10) {
                this.singletonCImpl = singletonCImpl;
                this.id = i10;
            }

            @Override // javax.inject.Provider
            public T get() {
                switch (this.id) {
                    case 0:
                        return (T) new CloudSyncWorker_AssistedFactory() { // from class: com.arflix.tv.DaggerArflixApplication_HiltComponents_SingletonC.SingletonCImpl.SwitchingProvider.1
                            @Override // com.arflix.tv.worker.CloudSyncWorker_AssistedFactory, i1.c
                            public CloudSyncWorker create(Context context, WorkerParameters workerParameters) {
                                return new CloudSyncWorker(context, workerParameters, SwitchingProvider.this.singletonCImpl.cloudSyncRepositoryProvider.get());
                            }
                        };
                    case 1:
                        return (T) new CloudSyncRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.authRepositoryProvider.get(), this.singletonCImpl.profileRepositoryProvider.get(), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.catalogRepositoryProvider.get(), this.singletonCImpl.iptvRepositoryProvider.get(), this.singletonCImpl.streamRepositoryProvider.get(), this.singletonCImpl.homeServerRepositoryProvider.get(), this.singletonCImpl.traktRepositoryProvider.get(), this.singletonCImpl.watchHistoryRepositoryProvider.get(), this.singletonCImpl.watchlistRepositoryProvider.get(), this.singletonCImpl.profileAvatarImageManagerProvider.get(), this.singletonCImpl.cloudSyncInvalidationBusProvider.get(), this.singletonCImpl.pluginDataStoreProvider.get(), this.singletonCImpl.syncProviderStoreProvider.get());
                    case 2:
                        Context contextProvideContext = ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule);
                        h0 h0Var = this.singletonCImpl.provideOkHttpClientProvider.get();
                        SingletonCImpl singletonCImpl = this.singletonCImpl;
                        return (T) new AuthRepository(contextProvideContext, h0Var, singletonCImpl.traktRepositoryProvider, singletonCImpl.cloudSyncRepositoryProvider);
                    case 3:
                        return (T) AppModule_ProvideOkHttpClientFactory.provideOkHttpClient();
                    case 4:
                        Context contextProvideContext2 = ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule);
                        TraktApi traktApi = this.singletonCImpl.provideTraktApiProvider.get();
                        TmdbApi tmdbApi = this.singletonCImpl.provideTmdbApiProvider.get();
                        h0 h0Var2 = this.singletonCImpl.provideOkHttpClientProvider.get();
                        SingletonCImpl singletonCImpl2 = this.singletonCImpl;
                        return (T) new TraktRepository(contextProvideContext2, traktApi, tmdbApi, h0Var2, singletonCImpl2.traktSyncServiceProvider, singletonCImpl2.profileManagerProvider.get(), this.singletonCImpl.mdbListRepositoryProvider.get(), this.singletonCImpl.syncProviderStoreProvider.get(), this.singletonCImpl.simklSyncServiceProvider.get(), this.singletonCImpl.continueWatchingUpdatesProvider.get());
                    case 5:
                        return (T) AppModule_ProvideTraktApiFactory.provideTraktApi(this.singletonCImpl.provideOkHttpClientProvider.get());
                    case 6:
                        return (T) AppModule_ProvideTmdbApiFactory.provideTmdbApi(this.singletonCImpl.provideOkHttpClientProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 7:
                        return (T) new TraktSyncService(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideTraktApiProvider.get(), this.singletonCImpl.provideSupabaseApiProvider.get(), this.singletonCImpl.authRepositoryProvider.get(), this.singletonCImpl.traktOutboxRepositoryProvider.get(), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.mdbListRepositoryProvider.get(), this.singletonCImpl.syncProviderStoreProvider.get());
                    case 8:
                        return (T) AppModule_ProvideSupabaseApiFactory.provideSupabaseApi(this.singletonCImpl.provideOkHttpClientProvider.get());
                    case 9:
                        return (T) new TraktOutboxRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.profileManagerProvider.get());
                    case 10:
                        return (T) new ProfileManager(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.profileRepositoryProvider.get());
                    case 11:
                        return (T) new ProfileRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.authRepositoryProvider.get(), this.singletonCImpl.profileAvatarImageManagerProvider.get(), this.singletonCImpl.cloudSyncInvalidationBusProvider.get());
                    case 12:
                        return (T) new ProfileAvatarImageManager(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.authRepositoryProvider.get());
                    case 13:
                        return (T) new CloudSyncInvalidationBus();
                    case 14:
                        return (T) new MdbListRepository(this.singletonCImpl.provideMdbListApiProvider.get(), this.singletonCImpl.syncProviderStoreProvider.get());
                    case 15:
                        return (T) AppModule_ProvideMdbListApiFactory.provideMdbListApi(this.singletonCImpl.provideOkHttpClientProvider.get());
                    case 16:
                        return (T) new SyncProviderStore(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.profileManagerProvider.get());
                    case 17:
                        return (T) new SimklSyncService(this.singletonCImpl.provideSimklApiProvider.get(), this.singletonCImpl.simklAuthManagerProvider.get(), this.singletonCImpl.provideTmdbApiProvider.get());
                    case 18:
                        return (T) AppModule_ProvideSimklApiFactory.provideSimklApi(this.singletonCImpl.provideOkHttpClientProvider.get());
                    case 19:
                        return (T) new SimklAuthManager(this.singletonCImpl.provideSimklApiProvider.get(), this.singletonCImpl.syncProviderStoreProvider.get());
                    case 20:
                        return (T) new ContinueWatchingUpdates();
                    case 21:
                        return (T) new CatalogRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.provideTraktApiProvider.get(), this.singletonCImpl.provideOkHttpClientProvider.get(), this.singletonCImpl.cloudSyncInvalidationBusProvider.get());
                    case 22:
                        return (T) new IptvRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideOkHttpClientProvider.get(), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.cloudSyncInvalidationBusProvider.get());
                    case 23:
                        return (T) new StreamRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideStreamApiProvider.get(), this.singletonCImpl.provideOkHttpClientProvider.get(), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.animeMapperProvider.get(), this.singletonCImpl.iptvRepositoryProvider.get(), this.singletonCImpl.httpLocalScraperRuntimeProvider.get(), this.singletonCImpl.homeServerRepositoryProvider.get(), this.singletonCImpl.cloudSyncInvalidationBusProvider.get(), this.singletonCImpl.telegramSourceResolverProvider.get());
                    case 24:
                        return (T) AppModule_ProvideStreamApiFactory.provideStreamApi(this.singletonCImpl.provideOkHttpClientProvider.get());
                    case 25:
                        return (T) new AnimeMapper(this.singletonCImpl.provideStreamApiProvider.get(), this.singletonCImpl.provideTmdbApiProvider.get());
                    case 26:
                        return (T) new HttpLocalScraperRuntime(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideOkHttpClientProvider.get(), this.singletonCImpl.provideTmdbApiProvider.get());
                    case 27:
                        return (T) new HomeServerRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideOkHttpClientProvider.get(), this.singletonCImpl.profileManagerProvider.get());
                    case 28:
                        return (T) new TelegramSourceResolver(this.singletonCImpl.telegramRepositoryProvider.get(), this.singletonCImpl.telegramSearchMatcherProvider.get(), this.singletonCImpl.provideTmdbApiProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 29:
                        return (T) new TelegramRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.telegramClientProvider.get(), this.singletonCImpl.telegramStreamingProxyProvider.get());
                    case 30:
                        return (T) new TelegramClient(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 31:
                        return (T) new TelegramStreamingProxy(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.telegramClientProvider.get());
                    case 32:
                        return (T) new TelegramSearchMatcher();
                    case 33:
                        SingletonCImpl singletonCImpl3 = this.singletonCImpl;
                        return (T) new WatchHistoryRepository(singletonCImpl3.authRepositoryProvider, singletonCImpl3.provideSupabaseApiProvider.get(), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.realtimeSyncManagerProvider);
                    case 34:
                        return (T) new RealtimeSyncManager(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.cloudSyncRepositoryProvider.get(), this.singletonCImpl.authRepositoryProvider.get());
                    case 35:
                        return (T) new WatchlistRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.provideTmdbApiProvider.get(), this.singletonCImpl.cloudSyncInvalidationBusProvider.get());
                    case TsExtractor.TS_STREAM_TYPE_H265 /* 36 */:
                        return (T) new PluginDataStore(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideMoshiProvider.get(), this.singletonCImpl.profileDataStoreFactoryProvider.get());
                    case 37:
                        return (T) AppModule_ProvideMoshiFactory.provideMoshi();
                    case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                        return (T) new ProfileDataStoreFactory(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                        return (T) new CloudSyncCoordinator(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.cloudSyncInvalidationBusProvider.get(), this.singletonCImpl.cloudSyncRepositoryProvider.get(), this.singletonCImpl.authRepositoryProvider.get());
                    case 40:
                        return (T) new AppUsageAnalyticsRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideOkHttpClientProvider.get(), this.singletonCImpl.authRepositoryProvider.get(), this.singletonCImpl.profileManagerProvider.get());
                    case 41:
                        return (T) new CastManager(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                        return (T) new MediaRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideTmdbApiProvider.get(), this.singletonCImpl.traktRepositoryProvider.get(), this.singletonCImpl.provideTraktApiProvider.get(), this.singletonCImpl.provideOkHttpClientProvider.get(), this.singletonCImpl.streamRepositoryProvider.get(), this.singletonCImpl.homeServerRepositoryProvider.get());
                    case 43:
                        return (T) new InAppYouTubeExtractor();
                    case 44:
                        return (T) new UpdateStatusManager();
                    case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                        return (T) new LauncherContinueWatchingRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.traktRepositoryProvider.get(), this.singletonCImpl.remoteSyncManagerProvider.get(), this.singletonCImpl.watchHistoryRepositoryProvider.get(), this.singletonCImpl.streamRepositoryProvider.get());
                    case 46:
                        return (T) new RemoteSyncManager(this.singletonCImpl.syncProviderStoreProvider.get(), this.singletonCImpl.traktRemoteProvider.get(), this.singletonCImpl.mdbListRemoteProvider.get(), this.singletonCImpl.simklRemoteProvider.get());
                    case 47:
                        return (T) new TraktRemoteProvider(this.singletonCImpl.traktRepositoryProvider.get());
                    case 48:
                        return (T) new MdbListRemoteProvider(this.singletonCImpl.mdbListRepositoryProvider.get());
                    case 49:
                        return (T) new SimklRemoteProvider(this.singletonCImpl.simklAuthManagerProvider.get(), this.singletonCImpl.simklScrobblerProvider.get(), this.singletonCImpl.simklSyncServiceProvider.get());
                    case 50:
                        return (T) new SimklScrobbler(this.singletonCImpl.provideSimklApiProvider.get(), this.singletonCImpl.simklAuthManagerProvider.get());
                    case 51:
                        return (T) new SportsRepository(this.singletonCImpl.streamRepositoryProvider.get(), this.singletonCImpl.provideStreamApiProvider.get());
                    case 52:
                        return (T) new PluginManager();
                    case 53:
                        return (T) new AppUpdateRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideOkHttpClientProvider.get());
                    case 54:
                        return (T) new ApkDownloader(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideOkHttpClientProvider.get());
                    case 55:
                        return (T) new UpdatePreferences(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 56:
                        return (T) new SkipIntroRepository(this.singletonCImpl.provideIntroDbApiProvider.get(), this.singletonCImpl.provideAniSkipApiProvider.get(), this.singletonCImpl.provideArmApiProvider.get());
                    case 57:
                        return (T) AppModule_ProvideIntroDbApiFactory.provideIntroDbApi(this.singletonCImpl.provideIntroDbRetrofitProvider.get());
                    case 58:
                        return (T) AppModule_ProvideIntroDbRetrofitFactory.provideIntroDbRetrofit(this.singletonCImpl.provideOkHttpClientProvider.get());
                    case 59:
                        return (T) AppModule_ProvideAniSkipApiFactory.provideAniSkipApi(this.singletonCImpl.provideAniSkipRetrofitProvider.get());
                    case 60:
                        return (T) AppModule_ProvideAniSkipRetrofitFactory.provideAniSkipRetrofit(this.singletonCImpl.provideOkHttpClientProvider.get());
                    case 61:
                        return (T) AppModule_ProvideArmApiFactory.provideArmApi(this.singletonCImpl.provideArmRetrofitProvider.get());
                    case 62:
                        return (T) AppModule_ProvideArmRetrofitFactory.provideArmRetrofit(this.singletonCImpl.provideOkHttpClientProvider.get());
                    case ColorSpace.MaxId /* 63 */:
                        return (T) new PlaybackTelemetryRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 64:
                        return (T) new CatalogDiscoveryRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideTraktApiProvider.get(), this.singletonCImpl.provideOkHttpClientProvider.get());
                    case 65:
                        return (T) new TvDeviceAuthRepository(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.provideOkHttpClientProvider.get());
                    default:
                        throw new AssertionError(this.id);
                }
            }
        }

        public SingletonCImpl(ApplicationContextModule applicationContextModule) {
            this.applicationContextModule = applicationContextModule;
            initialize(applicationContextModule);
            initialize2(applicationContextModule);
            initialize3(applicationContextModule);
        }

        private void initialize(ApplicationContextModule applicationContextModule) {
            this.provideOkHttpClientProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 3));
            this.provideTraktApiProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 5));
            this.provideTmdbApiProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 6));
            this.provideSupabaseApiProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 8));
            this.authRepositoryProvider = new DelegateFactory();
            this.profileAvatarImageManagerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 12));
            this.cloudSyncInvalidationBusProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 13));
            this.profileRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 11));
            this.profileManagerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 10));
            this.traktOutboxRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 9));
            this.provideMdbListApiProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 15));
            this.syncProviderStoreProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 16));
            this.mdbListRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 14));
            this.traktSyncServiceProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 7));
            this.provideSimklApiProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 18));
            this.simklAuthManagerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 19));
            this.simklSyncServiceProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 17));
            this.continueWatchingUpdatesProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 20));
            this.traktRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 4));
            this.cloudSyncRepositoryProvider = new DelegateFactory();
            DelegateFactory.setDelegate((Provider) this.authRepositoryProvider, DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 2)));
            this.catalogRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 21));
            this.iptvRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 22));
            this.provideStreamApiProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 24));
            this.animeMapperProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 25));
        }

        private void initialize2(ApplicationContextModule applicationContextModule) {
            this.httpLocalScraperRuntimeProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 26));
            this.homeServerRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 27));
            this.telegramClientProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 30));
            this.telegramStreamingProxyProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 31));
            this.telegramRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 29));
            this.telegramSearchMatcherProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 32));
            this.telegramSourceResolverProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 28));
            this.streamRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 23));
            this.realtimeSyncManagerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 34));
            this.watchHistoryRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 33));
            this.watchlistRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 35));
            this.provideMoshiProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 37));
            this.profileDataStoreFactoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 38));
            this.pluginDataStoreProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 36));
            DelegateFactory.setDelegate((Provider) this.cloudSyncRepositoryProvider, DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 1)));
            this.cloudSyncWorker_AssistedFactoryProvider = SingleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 0));
            this.cloudSyncCoordinatorProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 39));
            this.appUsageAnalyticsRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 40));
            this.castManagerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 41));
            this.mediaRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 42));
            this.inAppYouTubeExtractorProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 43));
            this.updateStatusManagerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 44));
            this.traktRemoteProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 47));
            this.mdbListRemoteProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 48));
            this.simklScrobblerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 50));
        }

        private void initialize3(ApplicationContextModule applicationContextModule) {
            this.simklRemoteProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 49));
            this.remoteSyncManagerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 46));
            this.launcherContinueWatchingRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 45));
            this.sportsRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 51));
            this.pluginManagerProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 52));
            this.appUpdateRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 53));
            this.apkDownloaderProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 54));
            this.updatePreferencesProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 55));
            this.provideIntroDbRetrofitProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 58));
            this.provideIntroDbApiProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 57));
            this.provideAniSkipRetrofitProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 60));
            this.provideAniSkipApiProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 59));
            this.provideArmRetrofitProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 62));
            this.provideArmApiProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 61));
            this.skipIntroRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 56));
            this.playbackTelemetryRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 63));
            this.catalogDiscoveryRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 64));
            this.tvDeviceAuthRepositoryProvider = DoubleCheck.provider((Provider) new SwitchingProvider(this.singletonCImpl, 65));
        }

        private ApkInstallReceiver injectApkInstallReceiver2(ApkInstallReceiver apkInstallReceiver) {
            ApkInstallReceiver_MembersInjector.injectUpdateStatusManager(apkInstallReceiver, this.updateStatusManagerProvider.get());
            return apkInstallReceiver;
        }

        private ArflixApplication injectArflixApplication2(ArflixApplication arflixApplication) {
            ArflixApplication_MembersInjector.injectWorkerFactory(arflixApplication, hiltWorkerFactory());
            ArflixApplication_MembersInjector.injectProfileManager(arflixApplication, this.profileManagerProvider.get());
            ArflixApplication_MembersInjector.injectAuthRepository(arflixApplication, this.authRepositoryProvider.get());
            ArflixApplication_MembersInjector.injectCloudSyncRepository(arflixApplication, this.cloudSyncRepositoryProvider.get());
            ArflixApplication_MembersInjector.injectCloudSyncCoordinator(arflixApplication, this.cloudSyncCoordinatorProvider.get());
            ArflixApplication_MembersInjector.injectRealtimeSyncManager(arflixApplication, this.realtimeSyncManagerProvider.get());
            ArflixApplication_MembersInjector.injectWatchlistRepository(arflixApplication, this.watchlistRepositoryProvider.get());
            ArflixApplication_MembersInjector.injectAppUsageAnalyticsRepository(arflixApplication, this.appUsageAnalyticsRepositoryProvider.get());
            return arflixApplication;
        }

        @Override // com.arflix.tv.cast.CastManagerEntryPoint
        public CastManager castManager() {
            return this.castManagerProvider.get();
        }

        @Override // com.arflix.tv.di.RepositoryAccessEntryPoint
        public CloudSyncInvalidationBus cloudSyncInvalidationBus() {
            return this.cloudSyncInvalidationBusProvider.get();
        }

        @Override // dagger.hilt.android.flags.FragmentGetContextFix.FragmentGetContextFixEntryPoint
        public Set<Boolean> getDisableFragmentGetContextFix() {
            int i10 = w1.f14132m;
            return w3.f14135t;
        }

        public i1.b hiltWorkerFactory() {
            return (i1.b) Preconditions.checkNotNullFromProvides(new i1.b(mapOfStringAndProviderOfWorkerAssistedFactoryOf()));
        }

        @Override // com.arflix.tv.ui.components.TrailerPlayerEntryPoint
        public InAppYouTubeExtractor inAppYouTubeExtractor() {
            return this.inAppYouTubeExtractorProvider.get();
        }

        @Override // com.arflix.tv.updater.ApkInstallReceiver_GeneratedInjector
        public void injectApkInstallReceiver(ApkInstallReceiver apkInstallReceiver) {
            injectApkInstallReceiver2(apkInstallReceiver);
        }

        @Override // com.arflix.tv.ArflixApplication_GeneratedInjector
        public void injectArflixApplication(ArflixApplication arflixApplication) {
            injectArflixApplication2(arflixApplication);
        }

        public Map<String, javax.inject.Provider<i1.c<? extends s>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf() {
            Provider<CloudSyncWorker_AssistedFactory> provider = this.cloudSyncWorker_AssistedFactoryProvider;
            a0.b("com.arflix.tv.worker.CloudSyncWorker", provider);
            return t3.j(1, new Object[]{"com.arflix.tv.worker.CloudSyncWorker", provider}, null);
        }

        @Override // com.arflix.tv.di.RepositoryAccessEntryPoint
        public MediaRepository mediaRepository() {
            return this.mediaRepositoryProvider.get();
        }

        @Override // com.arflix.tv.ui.components.ProfileAvatarVisualEntryPoint
        public ProfileAvatarImageManager profileAvatarImageManager() {
            return this.profileAvatarImageManagerProvider.get();
        }

        @Override // com.arflix.tv.di.RepositoryAccessEntryPoint
        public ProfileManager profileManager() {
            return this.profileManagerProvider.get();
        }

        @Override // com.arflix.tv.di.RepositoryAccessEntryPoint
        public ProfileRepository profileRepository() {
            return this.profileRepositoryProvider.get();
        }

        @Override // dagger.hilt.android.internal.managers.ActivityRetainedComponentManager.ActivityRetainedComponentBuilderEntryPoint
        public ActivityRetainedComponentBuilder retainedComponentBuilder() {
            return new ActivityRetainedCBuilder(this.singletonCImpl, 0);
        }

        @Override // dagger.hilt.android.internal.managers.ServiceComponentManager.ServiceComponentBuilderEntryPoint
        public ServiceComponentBuilder serviceComponentBuilder() {
            return new ServiceCBuilder(this.singletonCImpl, 0);
        }

        @Override // com.arflix.tv.di.RepositoryAccessEntryPoint
        public StreamRepository streamRepository() {
            return this.streamRepositoryProvider.get();
        }

        @Override // com.arflix.tv.di.RepositoryAccessEntryPoint
        public TmdbApi tmdbApi() {
            return this.provideTmdbApiProvider.get();
        }

        @Override // com.arflix.tv.worker.TraktSyncWorker.TraktSyncWorkerEntryPoint
        public TraktRepository traktRepository() {
            return this.traktRepositoryProvider.get();
        }

        @Override // com.arflix.tv.worker.TraktSyncWorker.TraktSyncWorkerEntryPoint
        public TraktSyncService traktSyncService() {
            return this.traktSyncServiceProvider.get();
        }
    }

    public static final class ViewCBuilder implements ArflixApplication_HiltComponents.ViewC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;
        private View view;

        public /* synthetic */ ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, int i10) {
            this(singletonCImpl, activityRetainedCImpl, activityCImpl);
        }

        private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewComponentBuilder
        public ArflixApplication_HiltComponents.ViewC build() {
            Preconditions.checkBuilderRequirement(this.view, View.class);
            return new ViewCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.view);
        }

        @Override // dagger.hilt.android.internal.builders.ViewComponentBuilder
        public ViewCBuilder view(View view) {
            this.view = (View) Preconditions.checkNotNull(view);
            return this;
        }
    }

    public static final class ViewCImpl extends ArflixApplication_HiltComponents.ViewC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final SingletonCImpl singletonCImpl;
        private final ViewCImpl viewCImpl = this;

        public ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, View view) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
        }
    }

    public static final class ViewModelCBuilder implements ArflixApplication_HiltComponents.ViewModelC.Builder {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private w0 savedStateHandle;
        private final SingletonCImpl singletonCImpl;
        private ViewModelLifecycle viewModelLifecycle;

        public /* synthetic */ ViewModelCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, int i10) {
            this(singletonCImpl, activityRetainedCImpl);
        }

        private ViewModelCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public ArflixApplication_HiltComponents.ViewModelC build() {
            Preconditions.checkBuilderRequirement(this.savedStateHandle, w0.class);
            Preconditions.checkBuilderRequirement(this.viewModelLifecycle, ViewModelLifecycle.class);
            return new ViewModelCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.savedStateHandle, this.viewModelLifecycle);
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public ViewModelCBuilder savedStateHandle(w0 w0Var) {
            this.savedStateHandle = (w0) Preconditions.checkNotNull(w0Var);
            return this;
        }

        @Override // dagger.hilt.android.internal.builders.ViewModelComponentBuilder
        public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
            this.viewModelLifecycle = (ViewModelLifecycle) Preconditions.checkNotNull(viewModelLifecycle);
            return this;
        }
    }

    public static final class ViewModelCImpl extends ArflixApplication_HiltComponents.ViewModelC {
        private final ActivityRetainedCImpl activityRetainedCImpl;
        Provider<CollectionDetailsViewModel> collectionDetailsViewModelProvider;
        Provider<DetailsViewModel> detailsViewModelProvider;
        Provider<HomeViewModel> homeViewModelProvider;
        Provider<LoginViewModel> loginViewModelProvider;
        Provider<PlayerViewModel> playerViewModelProvider;
        Provider<PluginViewModel> pluginViewModelProvider;
        Provider<ProfileViewModel> profileViewModelProvider;
        Provider<SearchViewModel> searchViewModelProvider;
        Provider<SettingsViewModel> settingsViewModelProvider;
        private final SingletonCImpl singletonCImpl;
        Provider<StartupViewModel> startupViewModelProvider;
        Provider<TelegramSettingsViewModel> telegramSettingsViewModelProvider;
        Provider<TvViewModel> tvViewModelProvider;
        private final ViewModelCImpl viewModelCImpl = this;
        Provider<WatchlistViewModel> watchlistViewModelProvider;

        public static final class SwitchingProvider<T> implements Provider<T> {
            private final ActivityRetainedCImpl activityRetainedCImpl;
            private final int id;
            private final SingletonCImpl singletonCImpl;
            private final ViewModelCImpl viewModelCImpl;

            public SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ViewModelCImpl viewModelCImpl, int i10) {
                this.singletonCImpl = singletonCImpl;
                this.activityRetainedCImpl = activityRetainedCImpl;
                this.viewModelCImpl = viewModelCImpl;
                this.id = i10;
            }

            @Override // javax.inject.Provider
            public T get() {
                switch (this.id) {
                    case 0:
                        return (T) new CollectionDetailsViewModel(this.singletonCImpl.catalogRepositoryProvider.get(), this.singletonCImpl.mediaRepositoryProvider.get(), this.singletonCImpl.sportsRepositoryProvider.get());
                    case 1:
                        return (T) new DetailsViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.mediaRepositoryProvider.get(), this.singletonCImpl.pluginManagerProvider.get(), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.traktRepositoryProvider.get(), this.singletonCImpl.remoteSyncManagerProvider.get(), this.singletonCImpl.streamRepositoryProvider.get(), this.singletonCImpl.animeMapperProvider.get(), this.singletonCImpl.provideTmdbApiProvider.get(), this.singletonCImpl.watchHistoryRepositoryProvider.get(), this.singletonCImpl.watchlistRepositoryProvider.get(), this.singletonCImpl.cloudSyncRepositoryProvider.get(), this.singletonCImpl.launcherContinueWatchingRepositoryProvider.get());
                    case 2:
                        return (T) new HomeViewModel(this.singletonCImpl.mediaRepositoryProvider.get(), this.singletonCImpl.catalogRepositoryProvider.get(), this.singletonCImpl.streamRepositoryProvider.get(), this.singletonCImpl.sportsRepositoryProvider.get(), this.singletonCImpl.traktRepositoryProvider.get(), this.singletonCImpl.remoteSyncManagerProvider.get(), this.singletonCImpl.traktSyncServiceProvider.get(), this.singletonCImpl.iptvRepositoryProvider.get(), this.singletonCImpl.homeServerRepositoryProvider.get(), this.singletonCImpl.watchHistoryRepositoryProvider.get(), this.singletonCImpl.watchlistRepositoryProvider.get(), this.singletonCImpl.cloudSyncRepositoryProvider.get(), this.singletonCImpl.launcherContinueWatchingRepositoryProvider.get(), this.singletonCImpl.continueWatchingUpdatesProvider.get(), this.singletonCImpl.realtimeSyncManagerProvider.get(), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.appUpdateRepositoryProvider.get(), this.singletonCImpl.apkDownloaderProvider.get(), this.singletonCImpl.updatePreferencesProvider.get(), this.singletonCImpl.updateStatusManagerProvider.get(), this.singletonCImpl.inAppYouTubeExtractorProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 3:
                        return (T) new LoginViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.authRepositoryProvider.get(), this.singletonCImpl.streamRepositoryProvider.get(), this.singletonCImpl.cloudSyncRepositoryProvider.get());
                    case 4:
                        return (T) new PlayerViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.mediaRepositoryProvider.get(), this.singletonCImpl.streamRepositoryProvider.get(), this.singletonCImpl.traktRepositoryProvider.get(), this.singletonCImpl.remoteSyncManagerProvider.get(), this.singletonCImpl.watchHistoryRepositoryProvider.get(), this.singletonCImpl.cloudSyncRepositoryProvider.get(), this.singletonCImpl.launcherContinueWatchingRepositoryProvider.get(), this.singletonCImpl.animeMapperProvider.get(), this.singletonCImpl.provideTmdbApiProvider.get(), this.singletonCImpl.skipIntroRepositoryProvider.get(), this.singletonCImpl.playbackTelemetryRepositoryProvider.get());
                    case 5:
                        return (T) new PluginViewModel(this.singletonCImpl.pluginManagerProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 6:
                        return (T) new ProfileViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.authRepositoryProvider.get(), this.singletonCImpl.profileRepositoryProvider.get(), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.traktRepositoryProvider.get(), this.singletonCImpl.watchHistoryRepositoryProvider.get(), this.singletonCImpl.watchlistRepositoryProvider.get(), this.singletonCImpl.iptvRepositoryProvider.get(), this.singletonCImpl.profileAvatarImageManagerProvider.get(), this.singletonCImpl.cloudSyncRepositoryProvider.get());
                    case 7:
                        return (T) new SearchViewModel(this.singletonCImpl.mediaRepositoryProvider.get());
                    case 8:
                        return (T) new SettingsViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.profileManagerProvider.get(), this.singletonCImpl.traktRepositoryProvider.get(), this.singletonCImpl.streamRepositoryProvider.get(), this.singletonCImpl.mediaRepositoryProvider.get(), this.singletonCImpl.catalogRepositoryProvider.get(), this.singletonCImpl.catalogDiscoveryRepositoryProvider.get(), this.singletonCImpl.iptvRepositoryProvider.get(), this.singletonCImpl.homeServerRepositoryProvider.get(), this.singletonCImpl.watchlistRepositoryProvider.get(), this.singletonCImpl.authRepositoryProvider.get(), this.singletonCImpl.profileRepositoryProvider.get(), this.singletonCImpl.tvDeviceAuthRepositoryProvider.get(), this.singletonCImpl.traktSyncServiceProvider.get(), this.singletonCImpl.cloudSyncRepositoryProvider.get(), this.singletonCImpl.launcherContinueWatchingRepositoryProvider.get(), this.singletonCImpl.appUpdateRepositoryProvider.get(), this.singletonCImpl.updatePreferencesProvider.get(), this.singletonCImpl.apkDownloaderProvider.get(), this.singletonCImpl.updateStatusManagerProvider.get(), this.singletonCImpl.mdbListRepositoryProvider.get(), this.singletonCImpl.syncProviderStoreProvider.get(), this.singletonCImpl.watchHistoryRepositoryProvider.get(), this.singletonCImpl.simklAuthManagerProvider.get());
                    case 9:
                        return (T) new StartupViewModel(this.singletonCImpl.mediaRepositoryProvider.get(), ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule));
                    case 10:
                        return (T) new TelegramSettingsViewModel(this.singletonCImpl.telegramRepositoryProvider.get());
                    case 11:
                        return (T) new TvViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.iptvRepositoryProvider.get(), this.singletonCImpl.cloudSyncRepositoryProvider.get());
                    case 12:
                        return (T) new WatchlistViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(this.singletonCImpl.applicationContextModule), this.singletonCImpl.watchlistRepositoryProvider.get(), this.singletonCImpl.cloudSyncRepositoryProvider.get(), this.singletonCImpl.traktRepositoryProvider.get(), this.singletonCImpl.remoteSyncManagerProvider.get(), this.singletonCImpl.mediaRepositoryProvider.get(), this.singletonCImpl.homeServerRepositoryProvider.get(), this.singletonCImpl.catalogRepositoryProvider.get(), this.singletonCImpl.watchHistoryRepositoryProvider.get(), this.singletonCImpl.simklAuthManagerProvider.get(), this.singletonCImpl.simklSyncServiceProvider.get(), this.singletonCImpl.profileManagerProvider.get());
                    default:
                        throw new AssertionError(this.id);
                }
            }
        }

        public ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, w0 w0Var, ViewModelLifecycle viewModelLifecycle) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            initialize(w0Var, viewModelLifecycle);
        }

        private void initialize(w0 w0Var, ViewModelLifecycle viewModelLifecycle) {
            this.collectionDetailsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 0);
            this.detailsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 1);
            this.homeViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 2);
            this.loginViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 3);
            this.playerViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 4);
            this.pluginViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 5);
            this.profileViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 6);
            this.searchViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 7);
            this.settingsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 8);
            this.startupViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 9);
            this.telegramSettingsViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 10);
            this.tvViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 11);
            this.watchlistViewModelProvider = new SwitchingProvider(this.singletonCImpl, this.activityRetainedCImpl, this.viewModelCImpl, 12);
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ViewModelFactoriesEntryPoint
        public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
            return t3.f14114q;
        }

        @Override // dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.ViewModelFactoriesEntryPoint
        public Map<Class<?>, javax.inject.Provider<d1>> getHiltViewModelMap() {
            a0.c(13, "expectedSize");
            k1 k1Var = new k1(13);
            k1Var.c(CollectionDetailsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.collectionDetailsViewModelProvider);
            k1Var.c(DetailsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.detailsViewModelProvider);
            k1Var.c(HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.homeViewModelProvider);
            k1Var.c(LoginViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.loginViewModelProvider);
            k1Var.c(PlayerViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.playerViewModelProvider);
            k1Var.c(PluginViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.pluginViewModelProvider);
            k1Var.c(ProfileViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.profileViewModelProvider);
            k1Var.c(SearchViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.searchViewModelProvider);
            k1Var.c(SettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.settingsViewModelProvider);
            k1Var.c(StartupViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.startupViewModelProvider);
            k1Var.c(TelegramSettingsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.telegramSettingsViewModelProvider);
            k1Var.c(TvViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.tvViewModelProvider);
            k1Var.c(WatchlistViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, this.watchlistViewModelProvider);
            return LazyClassKeyMap.of(k1Var.a(true));
        }
    }

    public static final class ViewWithFragmentCBuilder implements ArflixApplication_HiltComponents.ViewWithFragmentC.Builder {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final SingletonCImpl singletonCImpl;
        private View view;

        public /* synthetic */ ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, FragmentCImpl fragmentCImpl, int i10) {
            this(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
        }

        private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, FragmentCImpl fragmentCImpl) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
            this.fragmentCImpl = fragmentCImpl;
        }

        @Override // dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder
        public ArflixApplication_HiltComponents.ViewWithFragmentC build() {
            Preconditions.checkBuilderRequirement(this.view, View.class);
            return new ViewWithFragmentCImpl(this.singletonCImpl, this.activityRetainedCImpl, this.activityCImpl, this.fragmentCImpl, this.view);
        }

        @Override // dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder
        public ViewWithFragmentCBuilder view(View view) {
            this.view = (View) Preconditions.checkNotNull(view);
            return this;
        }
    }

    public static final class ViewWithFragmentCImpl extends ArflixApplication_HiltComponents.ViewWithFragmentC {
        private final ActivityCImpl activityCImpl;
        private final ActivityRetainedCImpl activityRetainedCImpl;
        private final FragmentCImpl fragmentCImpl;
        private final SingletonCImpl singletonCImpl;
        private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

        public ViewWithFragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl, FragmentCImpl fragmentCImpl, View view) {
            this.singletonCImpl = singletonCImpl;
            this.activityRetainedCImpl = activityRetainedCImpl;
            this.activityCImpl = activityCImpl;
            this.fragmentCImpl = fragmentCImpl;
        }
    }

    private DaggerArflixApplication_HiltComponents_SingletonC() {
    }

    public static Builder builder() {
        return new Builder(0);
    }
}
