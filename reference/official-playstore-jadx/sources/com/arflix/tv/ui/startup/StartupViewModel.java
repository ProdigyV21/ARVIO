package com.arflix.tv.ui.startup;

import android.content.Context;
import android.view.Precision;
import androidx.lifecycle.d1;
import androidx.lifecycle.z0;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import com.arflix.tv.d;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.MediaRepository;
import dagger.hilt.android.qualifiers.ApplicationContext;
import e7.a;
import f4.f;
import f7.e;
import f7.j;
import java.util.LinkedHashMap;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import k2.c;
import ka.f0;
import ka.k0;
import ka.m0;
import ka.x0;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.text.o;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import r7.p;
import ra.l;
import x6.s;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0013\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0016R\u001b\u0010\u001c\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082D¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020 8\u0002X\u0082D¢\u0006\u0006\n\u0004\b#\u0010\"R\u0014\u0010$\u001a\u00020 8\u0002X\u0082D¢\u0006\u0006\n\u0004\b$\u0010\"R\u0014\u0010%\u001a\u00020 8\u0002X\u0082D¢\u0006\u0006\n\u0004\b%\u0010\"R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020'0&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00020'0*8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.¨\u0006/"}, d2 = {"Lcom/arflix/tv/ui/startup/StartupViewModel;", "Landroidx/lifecycle/d1;", "Lcom/arflix/tv/data/repository/MediaRepository;", "mediaRepository", "Landroid/content/Context;", "context", "<init>", "(Lcom/arflix/tv/data/repository/MediaRepository;Landroid/content/Context;)V", "Lx6/t0;", "startParallelLoading", "()V", "", "progress", "", "message", "updateProgress", "(FLjava/lang/String;)V", "Lcom/arflix/tv/data/model/MediaItem;", "heroItem", "prefetchHeroAssets", "(Lcom/arflix/tv/data/model/MediaItem;)V", "Lcom/arflix/tv/data/repository/MediaRepository;", "Landroid/content/Context;", "Lcoil/ImageLoader;", "imageLoader$delegate", "Lx6/s;", "getImageLoader", "()Lcoil/ImageLoader;", "imageLoader", "Lka/f0;", "networkDispatcher", "Lka/f0;", "", "heroLogoPreloadWidth", "I", "heroLogoPreloadHeight", "heroBackdropPreloadWidth", "heroBackdropPreloadHeight", "Lna/q0;", "Lcom/arflix/tv/ui/startup/StartupState;", "_state", "Lna/q0;", "Lna/h1;", "state", "Lna/h1;", "getState", "()Lna/h1;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class StartupViewModel extends d1 {
    public static final int $stable = 8;
    private final q0<StartupState> _state;
    private final Context context;
    private final int heroBackdropPreloadHeight;
    private final int heroBackdropPreloadWidth;
    private final int heroLogoPreloadHeight;
    private final int heroLogoPreloadWidth;

    /* JADX INFO: renamed from: imageLoader$delegate, reason: from kotlin metadata */
    private final s imageLoader = f.o(3, new d(this, 2));
    private final MediaRepository mediaRepository;
    private final f0 networkDispatcher;
    private final h1<StartupState> state;

    /* JADX INFO: renamed from: com.arflix.tv.ui.startup.StartupViewModel$prefetchHeroAssets$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.startup.StartupViewModel$prefetchHeroAssets$1", f = "StartupViewModel.kt", l = {115}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ MediaItem $heroItem;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MediaItem mediaItem, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$heroItem = mediaItem;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return StartupViewModel.this.new AnonymousClass1(this.$heroItem, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Exception {
            Object logoUrl;
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    c.G(obj);
                    MediaRepository mediaRepository = StartupViewModel.this.mediaRepository;
                    MediaType mediaType = this.$heroItem.getMediaType();
                    int id = this.$heroItem.getId();
                    this.label = 1;
                    logoUrl = mediaRepository.getLogoUrl(mediaType, id, this);
                    a aVar = a.f15033i;
                    if (logoUrl == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    c.G(obj);
                    logoUrl = obj;
                }
                String str = (String) logoUrl;
                if (str != null && !o.h0(str)) {
                    StartupViewModel.this.getImageLoader().enqueue(new ImageRequest.Builder(StartupViewModel.this.context).data(str).size(StartupViewModel.this.heroLogoPreloadWidth, StartupViewModel.this.heroLogoPreloadHeight).precision(Precision.INEXACT).allowHardware(true).build());
                    String str2 = this.$heroItem.getMediaType() + "_" + this.$heroItem.getId();
                    LinkedHashMap linkedHashMap = new LinkedHashMap(((StartupState) StartupViewModel.this._state.getValue()).getLogoCache());
                    linkedHashMap.put(str2, str);
                    StartupViewModel.this._state.setValue(StartupState.copy$default((StartupState) StartupViewModel.this._state.getValue(), false, false, 0.0f, null, null, null, str, linkedHashMap, false, null, 831, null));
                }
            } catch (Exception e5) {
                if (e5 instanceof CancellationException) {
                    throw e5;
                }
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.startup.StartupViewModel$startParallelLoading$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @e(c = "com.arflix.tv.ui.startup.StartupViewModel$startParallelLoading$1", f = "StartupViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C16401 extends j implements p<k0, d7.d<? super t0>, Object> {
        int label;

        public C16401(d7.d<? super C16401> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return StartupViewModel.this.new C16401(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Exception {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            try {
                StartupViewModel.this.updateProgress(0.7f, "Preparing...");
                StartupViewModel.this._state.setValue(StartupState.copy$default((StartupState) StartupViewModel.this._state.getValue(), false, true, 0.0f, null, z.f19728i, null, null, null, false, null, 716, null));
                StartupViewModel.this.updateProgress(1.0f, "Ready!");
            } catch (Exception e5) {
                if (e5 instanceof CancellationException) {
                    throw e5;
                }
                StartupViewModel.this._state.setValue(StartupState.copy$default((StartupState) StartupViewModel.this._state.getValue(), false, true, 0.0f, null, null, null, null, null, false, e5.getMessage(), 508, null));
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16401) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public StartupViewModel(MediaRepository mediaRepository, @ApplicationContext Context context) {
        this.mediaRepository = mediaRepository;
        this.context = context;
        x0.f19655d.getClass();
        this.networkDispatcher = l.f21440i.limitedParallelism(8);
        this.heroLogoPreloadWidth = 300;
        this.heroLogoPreloadHeight = 70;
        this.heroBackdropPreloadWidth = 3840;
        this.heroBackdropPreloadHeight = 2160;
        j1 j1VarB = y0.b(new StartupState(false, false, 0.0f, null, null, null, null, null, false, null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null));
        this._state = j1VarB;
        this.state = y0.e(j1VarB);
        startParallelLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageLoader getImageLoader() {
        return (ImageLoader) this.imageLoader.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ImageLoader imageLoader_delegate$lambda$0(StartupViewModel startupViewModel) {
        return Coil.imageLoader(startupViewModel.context);
    }

    private final void prefetchHeroAssets(MediaItem heroItem) {
        if (heroItem == null) {
            return;
        }
        String backdrop = heroItem.getBackdrop();
        if (backdrop == null) {
            backdrop = heroItem.getImage();
        }
        if (backdrop != null && !o.h0(backdrop)) {
            getImageLoader().enqueue(new ImageRequest.Builder(this.context).data(backdrop).size(this.heroBackdropPreloadWidth, this.heroBackdropPreloadHeight).precision(Precision.INEXACT).allowHardware(true).build());
        }
        m0.p(z0.h(this), this.networkDispatcher, 0, new AnonymousClass1(heroItem, null), 2);
    }

    private final void startParallelLoading() {
        m0.p(z0.h(this), null, 0, new C16401(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateProgress(float progress, String message) {
        q0<StartupState> q0Var = this._state;
        q0Var.setValue(StartupState.copy$default((StartupState) q0Var.getValue(), false, false, progress, message, null, null, null, null, false, null, AnalyticsListener.EVENT_AUDIO_UNDERRUN, null));
    }

    public final h1<StartupState> getState() {
        return this.state;
    }
}
