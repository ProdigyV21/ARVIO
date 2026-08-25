package com.arflix.tv.ui.screens.plugin;

import android.content.Context;
import androidx.lifecycle.d1;
import androidx.lifecycle.z0;
import androidx.media3.container.MdtaMetadataEntry;
import com.arflix.tv.R;
import com.arflix.tv.core.plugin.PluginManager;
import com.arflix.tv.core.plugin.TestDiagnostics;
import com.arflix.tv.domain.model.PluginRepository;
import com.arflix.tv.domain.model.ScraperInfo;
import com.arflix.tv.ui.screens.plugin.PluginUiEvent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import f7.j;
import io.ktor.network.sockets.DatagramKt;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.z;
import kotlin.text.o;
import na.b0;
import na.h1;
import na.j1;
import na.k;
import na.o0;
import na.q0;
import na.y0;
import r7.p;
import r7.s;
import x6.d0;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0011\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0012\u0010\u000fJ\u001f\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u0018\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0019\u0010\nJ\u000f\u0010\u001a\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001a\u0010\nJ\u0017\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0017\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b\u001d\u0010\u001cJ\u0017\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\fH\u0002¢\u0006\u0004\b\u001e\u0010\u000fJ\u000f\u0010\u001f\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001f\u0010\nJ\u0017\u0010 \u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\bH\u0002¢\u0006\u0004\b\"\u0010\nJ\u000f\u0010#\u001a\u00020\bH\u0002¢\u0006\u0004\b#\u0010\nJ\u000f\u0010$\u001a\u00020\bH\u0002¢\u0006\u0004\b$\u0010\nJ\u0015\u0010'\u001a\u00020\b2\u0006\u0010&\u001a\u00020%¢\u0006\u0004\b'\u0010(J\r\u0010)\u001a\u00020\b¢\u0006\u0004\b)\u0010\nJ\u000f\u0010*\u001a\u00020\bH\u0014¢\u0006\u0004\b*\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010+R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010,R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020.0-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u001d\u00102\u001a\b\u0012\u0004\u0012\u00020.018\u0006¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u00105R\u0018\u00107\u001a\u0004\u0018\u0001068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00108R\u0018\u0010:\u001a\u0004\u0018\u0001098\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u0010;R\u0011\u0010<\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b<\u0010=¨\u0006>"}, d2 = {"Lcom/arflix/tv/ui/screens/plugin/PluginViewModel;", "Landroidx/lifecycle/d1;", "Lcom/arflix/tv/core/plugin/PluginManager;", "pluginManager", "Landroid/content/Context;", "context", "<init>", "(Lcom/arflix/tv/core/plugin/PluginManager;Landroid/content/Context;)V", "Lx6/t0;", "loadLogoBytes", "()V", "observePluginData", "", "url", "addRepository", "(Ljava/lang/String;)V", "repoId", "removeRepository", "refreshRepository", "scraperId", "", "enabled", "toggleScraper", "(Ljava/lang/String;Z)V", "toggleAllScrapersForRepo", "confirmPendingScraperEnable", "dismissPendingScraperEnable", "setPluginsEnabled", "(Z)V", "setGroupStreamsByRepository", "testScraper", "resetAllPlugins", "normalizeUrlForComparison", "(Ljava/lang/String;)Ljava/lang/String;", "startQrMode", "confirmPendingRepoChange", "rejectPendingRepoChange", "Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;", "event", "onEvent", "(Lcom/arflix/tv/ui/screens/plugin/PluginUiEvent;)V", "stopQrMode", "onCleared", "Lcom/arflix/tv/core/plugin/PluginManager;", "Landroid/content/Context;", "Lna/q0;", "Lcom/arflix/tv/ui/screens/plugin/PluginUiState;", "_uiState", "Lna/q0;", "Lna/h1;", "uiState", "Lna/h1;", "getUiState", "()Lna/h1;", "", "repoServer", "Ljava/lang/Object;", "", "logoBytes", "[B", "isReadOnly", "()Z", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PluginViewModel extends d1 {
    public static final int $stable = 8;
    private final q0<PluginUiState> _uiState;
    private final Context context;
    private byte[] logoBytes;
    private final PluginManager pluginManager;
    private Object repoServer;
    private final h1<PluginUiState> uiState;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$addRepository$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$addRepository$2", f = "PluginViewModel.kt", l = {117}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$url = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new AnonymousClass2(this.$url, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object value;
            Object objM6064addRepositorygIAlus;
            Object value2;
            PluginUiState pluginUiState;
            Context context;
            int i10;
            String message;
            Object value3;
            int i11 = this.label;
            if (i11 == 0) {
                k2.c.G(obj);
                q0 q0Var = PluginViewModel.this._uiState;
                do {
                    value = q0Var.getValue();
                } while (!q0Var.e(value, PluginUiState.copy$default((PluginUiState) value, false, false, null, null, false, true, false, null, null, null, null, null, false, null, null, null, null, 130015, null)));
                PluginManager pluginManager = PluginViewModel.this.pluginManager;
                String str = this.$url;
                this.label = 1;
                objM6064addRepositorygIAlus = pluginManager.m6064addRepositorygIAlus(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6064addRepositorygIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6064addRepositorygIAlus = ((d0) obj).f22580i;
            }
            PluginViewModel pluginViewModel = PluginViewModel.this;
            Throwable thA = d0.a(objM6064addRepositorygIAlus);
            if (thA == null) {
                PluginRepository pluginRepository = (PluginRepository) objM6064addRepositorygIAlus;
                q0 q0Var2 = pluginViewModel._uiState;
                do {
                    value3 = q0Var2.getValue();
                } while (!q0Var2.e(value3, PluginUiState.copy$default((PluginUiState) value3, false, false, null, null, false, false, false, null, null, null, null, pluginViewModel.context.getString(R.string.plugin_repo_added_with_providers, new Integer(pluginRepository.getScraperCount())), false, null, null, null, null, 128991, null)));
            } else {
                q0 q0Var3 = pluginViewModel._uiState;
                do {
                    value2 = q0Var3.getValue();
                    pluginUiState = (PluginUiState) value2;
                    context = pluginViewModel.context;
                    i10 = R.string.plugin_error_add_repo;
                    message = thA.getMessage();
                    if (message == null) {
                        message = "";
                    }
                } while (!q0Var3.e(value2, PluginUiState.copy$default(pluginUiState, false, false, null, null, false, false, false, null, null, null, context.getString(i10, message), null, false, null, null, null, null, 130015, null)));
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$confirmPendingScraperEnable$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$confirmPendingScraperEnable$2", f = "PluginViewModel.kt", l = {199}, m = "invokeSuspend", v = 2)
    public static final class C14422 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ PendingScraperEnableInfo $pending;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14422(PendingScraperEnableInfo pendingScraperEnableInfo, d7.d<? super C14422> dVar) {
            super(2, dVar);
            this.$pending = pendingScraperEnableInfo;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new C14422(this.$pending, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PluginManager pluginManager = PluginViewModel.this.pluginManager;
                String scraperId = this.$pending.getScraperId();
                this.label = 1;
                Object obj2 = pluginManager.toggleScraper(scraperId, true, this);
                e7.a aVar = e7.a.f15033i;
                if (obj2 == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14422) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$observePluginData$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$observePluginData$1", f = "PluginViewModel.kt", l = {MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$observePluginData$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/ui/screens/plugin/PluginUiState;", "enabled", "", "groupStreamsByRepository", "repos", "", "Lcom/arflix/tv/domain/model/PluginRepository;", "scrapers", "Lcom/arflix/tv/domain/model/ScraperInfo;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$observePluginData$1$1", f = "PluginViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01501 extends j implements s<Boolean, Boolean, List<? extends PluginRepository>, List<? extends ScraperInfo>, d7.d<? super PluginUiState>, Object> {
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            /* synthetic */ boolean Z$0;
            /* synthetic */ boolean Z$1;
            int label;

            public C01501(d7.d<? super C01501> dVar) {
                super(5, dVar);
            }

            @Override // r7.s
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
                return invoke(((Boolean) obj).booleanValue(), ((Boolean) obj2).booleanValue(), (List<PluginRepository>) obj3, (List<ScraperInfo>) obj4, (d7.d<? super PluginUiState>) obj5);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                boolean z = this.Z$0;
                boolean z5 = this.Z$1;
                List list = (List) this.L$0;
                List list2 = (List) this.L$1;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return new PluginUiState(z, z5, list, list2, false, false, false, null, null, null, null, null, false, null, null, null, null, 131056, null);
            }

            public final Object invoke(boolean z, boolean z5, List<PluginRepository> list, List<ScraperInfo> list2, d7.d<? super PluginUiState> dVar) {
                C01501 c01501 = new C01501(dVar);
                c01501.Z$0 = z;
                c01501.Z$1 = z5;
                c01501.L$0 = list;
                c01501.L$1 = list2;
                return c01501.invokeSuspend(t0.f22605a);
            }
        }

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<Boolean> pluginsEnabled = PluginViewModel.this.pluginManager.getPluginsEnabled();
                na.j<Boolean> groupStreamsByRepository = PluginViewModel.this.pluginManager.getGroupStreamsByRepository();
                na.j<List<PluginRepository>> repositories = PluginViewModel.this.pluginManager.getRepositories();
                na.j<List<ScraperInfo>> scrapers = PluginViewModel.this.pluginManager.getScrapers();
                C01501 c01501 = new C01501(null);
                na.j[] jVarArr = {pluginsEnabled, groupStreamsByRepository, repositories, scrapers};
                final PluginViewModel pluginViewModel = PluginViewModel.this;
                k kVar = new k() { // from class: com.arflix.tv.ui.screens.plugin.PluginViewModel.observePluginData.1.2
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((PluginUiState) obj2, (d7.d<? super t0>) dVar);
                    }

                    public final Object emit(PluginUiState pluginUiState, d7.d<? super t0> dVar) {
                        List<ScraperInfo> scrapers2;
                        Object value;
                        if (pluginViewModel.isReadOnly()) {
                            List<ScraperInfo> scrapers3 = pluginUiState.getScrapers();
                            scrapers2 = new ArrayList<>();
                            for (T t2 : scrapers3) {
                                if (((ScraperInfo) t2).getEnabled()) {
                                    scrapers2.add(t2);
                                }
                            }
                        } else {
                            scrapers2 = pluginUiState.getScrapers();
                        }
                        List<ScraperInfo> list = scrapers2;
                        q0 q0Var = pluginViewModel._uiState;
                        do {
                            value = q0Var.getValue();
                        } while (!q0Var.e(value, PluginUiState.copy$default((PluginUiState) value, pluginUiState.getPluginsEnabled(), pluginUiState.getGroupStreamsByRepository(), pluginUiState.getRepositories(), list, false, false, false, null, null, null, null, null, false, null, null, null, null, 131056, null)));
                        return t0.f22605a;
                    }
                };
                this.label = 1;
                Object objA = oa.b.a(this, kVar, o0.f20631i, new b0(c01501, (d7.d) null), jVarArr);
                e7.a aVar = e7.a.f15033i;
                if (objA != aVar) {
                    objA = t0Var;
                }
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$refreshRepository$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$refreshRepository$1", f = "PluginViewModel.kt", l = {160}, m = "invokeSuspend", v = 2)
    public static final class C14431 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $repoId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14431(String str, d7.d<? super C14431> dVar) {
            super(2, dVar);
            this.$repoId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new C14431(this.$repoId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object value;
            Object objM6065refreshRepositorygIAlus;
            Object value2;
            PluginUiState pluginUiState;
            Context context;
            int i10;
            String message;
            Object value3;
            int i11 = this.label;
            if (i11 == 0) {
                k2.c.G(obj);
                q0 q0Var = PluginViewModel.this._uiState;
                do {
                    value = q0Var.getValue();
                } while (!q0Var.e(value, PluginUiState.copy$default((PluginUiState) value, false, false, null, null, true, false, false, null, null, null, null, null, false, null, null, null, null, 131055, null)));
                PluginManager pluginManager = PluginViewModel.this.pluginManager;
                String str = this.$repoId;
                this.label = 1;
                objM6065refreshRepositorygIAlus = pluginManager.m6065refreshRepositorygIAlus(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6065refreshRepositorygIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6065refreshRepositorygIAlus = ((d0) obj).f22580i;
            }
            PluginViewModel pluginViewModel = PluginViewModel.this;
            Throwable thA = d0.a(objM6065refreshRepositorygIAlus);
            if (thA == null) {
                q0 q0Var2 = pluginViewModel._uiState;
                do {
                    value3 = q0Var2.getValue();
                } while (!q0Var2.e(value3, PluginUiState.copy$default((PluginUiState) value3, false, false, null, null, false, false, false, null, null, null, null, "Error", false, null, null, null, null, 129007, null)));
            } else {
                q0 q0Var3 = pluginViewModel._uiState;
                do {
                    value2 = q0Var3.getValue();
                    pluginUiState = (PluginUiState) value2;
                    context = pluginViewModel.context;
                    i10 = R.string.plugin_error_refresh;
                    message = thA.getMessage();
                    if (message == null) {
                        message = "";
                    }
                } while (!q0Var3.e(value2, PluginUiState.copy$default(pluginUiState, false, false, null, null, false, false, false, null, null, null, context.getString(i10, message), null, false, null, null, null, null, 130031, null)));
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14431) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$removeRepository$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$removeRepository$1", f = "PluginViewModel.kt", l = {146}, m = "invokeSuspend", v = 2)
    public static final class C14441 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $repoId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14441(String str, d7.d<? super C14441> dVar) {
            super(2, dVar);
            this.$repoId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new C14441(this.$repoId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object value;
            Object value2;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                q0 q0Var = PluginViewModel.this._uiState;
                do {
                    value = q0Var.getValue();
                } while (!q0Var.e(value, PluginUiState.copy$default((PluginUiState) value, false, false, null, null, true, false, false, null, null, null, null, null, false, null, null, null, null, 131055, null)));
                PluginManager pluginManager = PluginViewModel.this.pluginManager;
                String str = this.$repoId;
                this.label = 1;
                Object objRemoveRepository = pluginManager.removeRepository(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objRemoveRepository == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            q0 q0Var2 = PluginViewModel.this._uiState;
            do {
                value2 = q0Var2.getValue();
            } while (!q0Var2.e(value2, PluginUiState.copy$default((PluginUiState) value2, false, false, null, null, false, false, false, null, null, null, null, "Error", false, null, null, null, null, 129007, null)));
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14441) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$resetAllPlugins$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$resetAllPlugins$1", f = "PluginViewModel.kt", l = {262}, m = "invokeSuspend", v = 2)
    public static final class C14451 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public C14451(d7.d<? super C14451> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new C14451(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object value;
            Object value2;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                q0 q0Var = PluginViewModel.this._uiState;
                do {
                    value = q0Var.getValue();
                } while (!q0Var.e(value, PluginUiState.copy$default((PluginUiState) value, false, false, null, null, true, false, false, null, null, null, null, null, false, null, null, null, null, 131055, null)));
                PluginManager pluginManager = PluginViewModel.this.pluginManager;
                this.label = 1;
                Object objClearAllPlugins = pluginManager.clearAllPlugins(this);
                e7.a aVar = e7.a.f15033i;
                if (objClearAllPlugins == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            q0 q0Var2 = PluginViewModel.this._uiState;
            do {
                value2 = q0Var2.getValue();
            } while (!q0Var2.e(value2, PluginUiState.copy$default((PluginUiState) value2, false, false, null, null, false, false, false, null, null, null, null, "All plugins and extensions cleared successfully", false, null, null, null, null, 129007, null)));
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14451) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$setGroupStreamsByRepository$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$setGroupStreamsByRepository$1", f = "PluginViewModel.kt", l = {217}, m = "invokeSuspend", v = 2)
    public static final class C14461 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14461(boolean z, d7.d<? super C14461> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new C14461(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PluginManager pluginManager = PluginViewModel.this.pluginManager;
                boolean z = this.$enabled;
                this.label = 1;
                Object groupStreamsByRepository = pluginManager.setGroupStreamsByRepository(z, this);
                e7.a aVar = e7.a.f15033i;
                if (groupStreamsByRepository == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14461) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$setPluginsEnabled$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$setPluginsEnabled$1", f = "PluginViewModel.kt", l = {210}, m = "invokeSuspend", v = 2)
    public static final class C14471 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14471(boolean z, d7.d<? super C14471> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new C14471(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PluginManager pluginManager = PluginViewModel.this.pluginManager;
                boolean z = this.$enabled;
                this.label = 1;
                Object pluginsEnabled = pluginManager.setPluginsEnabled(z, this);
                e7.a aVar = e7.a.f15033i;
                if (pluginsEnabled == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14471) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$testScraper$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$testScraper$1", f = "PluginViewModel.kt", l = {225}, m = "invokeSuspend", v = 2)
    public static final class C14481 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $scraperId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14481(String str, d7.d<? super C14481> dVar) {
            super(2, dVar);
            this.$scraperId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new C14481(this.$scraperId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object value;
            Object objM6066testScrapergIAlus;
            Object value2;
            PluginUiState pluginUiState;
            Context context;
            int i10;
            String message;
            Object value3;
            int i11 = this.label;
            if (i11 == 0) {
                k2.c.G(obj);
                q0 q0Var = PluginViewModel.this._uiState;
                String str = this.$scraperId;
                do {
                    value = q0Var.getValue();
                } while (!q0Var.e(value, PluginUiState.copy$default((PluginUiState) value, false, false, null, null, false, false, true, null, null, str, null, null, false, null, null, null, null, 130111, null)));
                PluginManager pluginManager = PluginViewModel.this.pluginManager;
                String str2 = this.$scraperId;
                this.label = 1;
                objM6066testScrapergIAlus = pluginManager.m6066testScrapergIAlus(str2, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6066testScrapergIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6066testScrapergIAlus = ((d0) obj).f22580i;
            }
            PluginViewModel pluginViewModel = PluginViewModel.this;
            Throwable thA = d0.a(objM6066testScrapergIAlus);
            if (thA == null) {
                x xVar = (x) objM6066testScrapergIAlus;
                List list = (List) xVar.f22608i;
                TestDiagnostics testDiagnostics = (TestDiagnostics) xVar.f22609l;
                q0 q0Var2 = pluginViewModel._uiState;
                do {
                    value3 = q0Var2.getValue();
                } while (!q0Var2.e(value3, PluginUiState.copy$default((PluginUiState) value3, false, false, null, null, false, false, false, list, testDiagnostics, null, null, list.isEmpty() ? "Error" : pluginViewModel.context.getString(R.string.plugin_test_found_streams, new Integer(list.size())), false, null, null, null, null, 128575, null)));
            } else {
                q0 q0Var3 = pluginViewModel._uiState;
                do {
                    value2 = q0Var3.getValue();
                    pluginUiState = (PluginUiState) value2;
                    context = pluginViewModel.context;
                    i10 = R.string.plugin_error_test;
                    message = thA.getMessage();
                    if (message == null) {
                        message = "Error";
                    }
                } while (!q0Var3.e(value2, PluginUiState.copy$default(pluginUiState, false, false, null, null, false, false, false, z.f19728i, null, null, context.getString(i10, message), null, false, null, null, null, null, 129599, null)));
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14481) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$toggleAllScrapersForRepo$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$toggleAllScrapersForRepo$1", f = "PluginViewModel.kt", l = {191}, m = "invokeSuspend", v = 2)
    public static final class C14491 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ String $repoId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14491(String str, boolean z, d7.d<? super C14491> dVar) {
            super(2, dVar);
            this.$repoId = str;
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new C14491(this.$repoId, this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PluginManager pluginManager = PluginViewModel.this.pluginManager;
                String str = this.$repoId;
                boolean z = this.$enabled;
                this.label = 1;
                Object obj2 = pluginManager.toggleAllScrapersForRepo(str, z, this);
                e7.a aVar = e7.a.f15033i;
                if (obj2 == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14491) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.plugin.PluginViewModel$toggleScraper$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.plugin.PluginViewModel$toggleScraper$1", f = "PluginViewModel.kt", l = {185}, m = "invokeSuspend", v = 2)
    public static final class C14501 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ String $scraperId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14501(String str, boolean z, d7.d<? super C14501> dVar) {
            super(2, dVar);
            this.$scraperId = str;
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return PluginViewModel.this.new C14501(this.$scraperId, this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PluginManager pluginManager = PluginViewModel.this.pluginManager;
                String str = this.$scraperId;
                boolean z = this.$enabled;
                this.label = 1;
                Object obj2 = pluginManager.toggleScraper(str, z, this);
                e7.a aVar = e7.a.f15033i;
                if (obj2 == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14501) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public PluginViewModel(PluginManager pluginManager, @ApplicationContext Context context) {
        this.pluginManager = pluginManager;
        this.context = context;
        j1 j1VarB = y0.b(new PluginUiState(false, false, null, null, false, false, false, null, null, null, null, null, false, null, null, null, null, 131071, null));
        this._uiState = j1VarB;
        this.uiState = y0.e(j1VarB);
        loadLogoBytes();
        observePluginData();
    }

    private final void addRepository(String url) {
        Object value;
        if (!o.h0(url)) {
            m0.p(z0.h(this), null, 0, new AnonymousClass2(url, null), 3);
            return;
        }
        q0<PluginUiState> q0Var = this._uiState;
        do {
            value = q0Var.getValue();
        } while (!q0Var.e(value, PluginUiState.copy$default((PluginUiState) value, false, false, null, null, false, false, false, null, null, null, "Error", null, false, null, null, null, null, 130047, null)));
    }

    private final void confirmPendingRepoChange() {
    }

    private final void confirmPendingScraperEnable() {
        Object value;
        PendingScraperEnableInfo pendingScraperEnable = ((PluginUiState) this._uiState.getValue()).getPendingScraperEnable();
        if (pendingScraperEnable == null) {
            return;
        }
        q0<PluginUiState> q0Var = this._uiState;
        do {
            value = q0Var.getValue();
        } while (!q0Var.e(value, PluginUiState.copy$default((PluginUiState) value, false, false, null, null, false, false, false, null, null, null, null, null, false, null, null, null, null, DatagramKt.MAX_DATAGRAM_SIZE, null)));
        m0.p(z0.h(this), null, 0, new C14422(pendingScraperEnable, null), 3);
    }

    private final void dismissPendingScraperEnable() {
        Object value;
        q0<PluginUiState> q0Var = this._uiState;
        do {
            value = q0Var.getValue();
        } while (!q0Var.e(value, PluginUiState.copy$default((PluginUiState) value, false, false, null, null, false, false, false, null, null, null, null, null, false, null, null, null, null, DatagramKt.MAX_DATAGRAM_SIZE, null)));
    }

    private final void loadLogoBytes() {
        try {
            InputStream inputStreamOpenRawResource = this.context.getResources().openRawResource(0);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, inputStreamOpenRawResource.available()));
                t7.a.k(inputStreamOpenRawResource, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                inputStreamOpenRawResource.close();
                this.logoBytes = byteArray;
            } finally {
            }
        } catch (Exception unused) {
        }
    }

    private final String normalizeUrlForComparison(String url) {
        return o.O0(o.L0(url).toString(), '/').toLowerCase(Locale.ROOT);
    }

    private final void observePluginData() {
        m0.p(z0.h(this), null, 0, new AnonymousClass1(null), 3);
    }

    private final void refreshRepository(String repoId) {
        m0.p(z0.h(this), null, 0, new C14431(repoId, null), 3);
    }

    private final void rejectPendingRepoChange() {
    }

    private final void removeRepository(String repoId) {
        m0.p(z0.h(this), null, 0, new C14441(repoId, null), 3);
    }

    private final void resetAllPlugins() {
        m0.p(z0.h(this), null, 0, new C14451(null), 3);
    }

    private final void setGroupStreamsByRepository(boolean enabled) {
        if (isReadOnly()) {
            return;
        }
        m0.p(z0.h(this), null, 0, new C14461(enabled, null), 3);
    }

    private final void setPluginsEnabled(boolean enabled) {
        if (isReadOnly()) {
            return;
        }
        m0.p(z0.h(this), null, 0, new C14471(enabled, null), 3);
    }

    private final void startQrMode() {
    }

    private final void testScraper(String scraperId) {
        m0.p(z0.h(this), null, 0, new C14481(scraperId, null), 3);
    }

    private final void toggleAllScrapersForRepo(String repoId, boolean enabled) {
        m0.p(z0.h(this), null, 0, new C14491(repoId, enabled, null), 3);
    }

    private final void toggleScraper(String scraperId, boolean enabled) {
        m0.p(z0.h(this), null, 0, new C14501(scraperId, enabled, null), 3);
    }

    public final h1<PluginUiState> getUiState() {
        return this.uiState;
    }

    public final boolean isReadOnly() {
        return false;
    }

    @Override // androidx.lifecycle.d1
    public void onCleared() {
        super.onCleared();
    }

    public final void onEvent(PluginUiEvent event) {
        Object value;
        Object value2;
        Object value3;
        if (event instanceof PluginUiEvent.AddRepository) {
            addRepository(((PluginUiEvent.AddRepository) event).getUrl());
            return;
        }
        if (event instanceof PluginUiEvent.RemoveRepository) {
            removeRepository(((PluginUiEvent.RemoveRepository) event).getRepoId());
            return;
        }
        if (event instanceof PluginUiEvent.RefreshRepository) {
            refreshRepository(((PluginUiEvent.RefreshRepository) event).getRepoId());
            return;
        }
        if (event instanceof PluginUiEvent.ToggleScraper) {
            PluginUiEvent.ToggleScraper toggleScraper = (PluginUiEvent.ToggleScraper) event;
            toggleScraper(toggleScraper.getScraperId(), toggleScraper.getEnabled());
            return;
        }
        if (event instanceof PluginUiEvent.ToggleAllScrapersForRepo) {
            PluginUiEvent.ToggleAllScrapersForRepo toggleAllScrapersForRepo = (PluginUiEvent.ToggleAllScrapersForRepo) event;
            toggleAllScrapersForRepo(toggleAllScrapersForRepo.getRepoId(), toggleAllScrapersForRepo.getEnabled());
            return;
        }
        if (event instanceof PluginUiEvent.TestScraper) {
            testScraper(((PluginUiEvent.TestScraper) event).getScraperId());
            return;
        }
        if (event instanceof PluginUiEvent.SetPluginsEnabled) {
            setPluginsEnabled(((PluginUiEvent.SetPluginsEnabled) event).getEnabled());
            return;
        }
        if (event instanceof PluginUiEvent.SetGroupStreamsByRepository) {
            setGroupStreamsByRepository(((PluginUiEvent.SetGroupStreamsByRepository) event).getEnabled());
            return;
        }
        if (event.equals(PluginUiEvent.ClearTestResults.INSTANCE)) {
            q0<PluginUiState> q0Var = this._uiState;
            do {
                value3 = q0Var.getValue();
            } while (!q0Var.e(value3, PluginUiState.copy$default((PluginUiState) value3, false, false, null, null, false, false, false, null, null, null, null, null, false, null, null, null, null, 130175, null)));
            return;
        }
        if (event.equals(PluginUiEvent.ClearError.INSTANCE)) {
            q0<PluginUiState> q0Var2 = this._uiState;
            do {
                value2 = q0Var2.getValue();
            } while (!q0Var2.e(value2, PluginUiState.copy$default((PluginUiState) value2, false, false, null, null, false, false, false, null, null, null, null, null, false, null, null, null, null, 130047, null)));
            return;
        }
        if (event.equals(PluginUiEvent.ClearSuccess.INSTANCE)) {
            q0<PluginUiState> q0Var3 = this._uiState;
            do {
                value = q0Var3.getValue();
            } while (!q0Var3.e(value, PluginUiState.copy$default((PluginUiState) value, false, false, null, null, false, false, false, null, null, null, null, null, false, null, null, null, null, 129023, null)));
            return;
        }
        if (event.equals(PluginUiEvent.StartQrMode.INSTANCE)) {
            startQrMode();
            return;
        }
        if (event.equals(PluginUiEvent.StopQrMode.INSTANCE)) {
            stopQrMode();
            return;
        }
        if (event.equals(PluginUiEvent.ConfirmPendingRepoChange.INSTANCE)) {
            confirmPendingRepoChange();
            return;
        }
        if (event.equals(PluginUiEvent.RejectPendingRepoChange.INSTANCE)) {
            rejectPendingRepoChange();
            return;
        }
        if (event.equals(PluginUiEvent.ConfirmPendingScraperEnable.INSTANCE)) {
            confirmPendingScraperEnable();
        } else if (event.equals(PluginUiEvent.DismissPendingScraperEnable.INSTANCE)) {
            dismissPendingScraperEnable();
        } else {
            if (!event.equals(PluginUiEvent.ResetAllPlugins.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            resetAllPlugins();
        }
    }

    public final void stopQrMode() {
    }
}
