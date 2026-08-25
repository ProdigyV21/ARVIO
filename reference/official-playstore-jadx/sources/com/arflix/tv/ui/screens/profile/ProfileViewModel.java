package com.arflix.tv.ui.screens.profile;

import android.content.Context;
import androidx.lifecycle.d1;
import androidx.lifecycle.z0;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.model.ProfileColors;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.AuthState;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.ProfileAvatarImageManager;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.ui.components.ToastType;
import com.arflix.tv.util.PinUtil;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import io.ktor.network.sockets.DatagramKt;
import java.util.List;
import javax.inject.Inject;
import ka.k0;
import ka.m0;
import ka.s0;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B[\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010\u001f\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001f\u0010\u001eJ\r\u0010 \u001a\u00020\u0018¢\u0006\u0004\b \u0010\u001aJ\r\u0010!\u001a\u00020\u0018¢\u0006\u0004\b!\u0010\u001aJ\r\u0010\"\u001a\u00020\u0018¢\u0006\u0004\b\"\u0010\u001aJ\u0015\u0010%\u001a\u00020\u00182\u0006\u0010$\u001a\u00020#¢\u0006\u0004\b%\u0010&J\u0015\u0010)\u001a\u00020\u00182\u0006\u0010(\u001a\u00020'¢\u0006\u0004\b)\u0010*J\u0015\u0010,\u001a\u00020\u00182\u0006\u0010+\u001a\u00020'¢\u0006\u0004\b,\u0010*J\u0015\u0010.\u001a\u00020\u00182\u0006\u0010-\u001a\u00020#¢\u0006\u0004\b.\u0010&J\r\u0010/\u001a\u00020\u0018¢\u0006\u0004\b/\u0010\u001aJ\r\u00100\u001a\u00020\u0018¢\u0006\u0004\b0\u0010\u001aJ\u0015\u00101\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b1\u0010\u001eJ\r\u00102\u001a\u00020\u0018¢\u0006\u0004\b2\u0010\u001aJ\r\u00103\u001a\u00020\u0018¢\u0006\u0004\b3\u0010\u001aJ\r\u00104\u001a\u00020\u0018¢\u0006\u0004\b4\u0010\u001aJ\r\u00105\u001a\u00020\u0018¢\u0006\u0004\b5\u0010\u001aJ\u0015\u00106\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b6\u0010\u001eJ\u0015\u00107\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b7\u0010\u001eJ\r\u00108\u001a\u00020\u0018¢\u0006\u0004\b8\u0010\u001aJ\r\u00109\u001a\u00020\u0018¢\u0006\u0004\b9\u0010\u001aJ\u0015\u0010;\u001a\u00020\u00182\u0006\u0010:\u001a\u00020#¢\u0006\u0004\b;\u0010&J\u0015\u0010=\u001a\u00020\u00182\u0006\u0010<\u001a\u00020#¢\u0006\u0004\b=\u0010&J\r\u0010>\u001a\u00020\u0018¢\u0006\u0004\b>\u0010\u001aJ\u000f\u0010?\u001a\u00020\u0018H\u0002¢\u0006\u0004\b?\u0010\u001aJ\u000f\u0010@\u001a\u00020\u0018H\u0002¢\u0006\u0004\b@\u0010\u001aJ\u000f\u0010A\u001a\u00020\u0018H\u0002¢\u0006\u0004\bA\u0010\u001aJ!\u0010E\u001a\u00020\u00182\u0006\u0010B\u001a\u00020#2\b\b\u0002\u0010D\u001a\u00020CH\u0002¢\u0006\u0004\bE\u0010FJ\u0017\u0010G\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001bH\u0002¢\u0006\u0004\bG\u0010\u001eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010HR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010IR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010JR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010KR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010LR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010MR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010NR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010OR\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010PR\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010QR\u001a\u0010T\u001a\b\u0012\u0004\u0012\u00020S0R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010UR\u001d\u0010W\u001a\b\u0012\u0004\u0012\u00020S0V8\u0006¢\u0006\f\n\u0004\bW\u0010X\u001a\u0004\bY\u0010ZR\u0018\u0010[\u001a\u0004\u0018\u00010#8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010\\¨\u0006]"}, d2 = {"Lcom/arflix/tv/ui/screens/profile/ProfileViewModel;", "Landroidx/lifecycle/d1;", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "Lcom/arflix/tv/data/repository/ProfileRepository;", "profileRepository", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktRepository", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "watchHistoryRepository", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "watchlistRepository", "Lcom/arflix/tv/data/repository/IptvRepository;", "iptvRepository", "Lcom/arflix/tv/data/repository/ProfileAvatarImageManager;", "profileAvatarImageManager", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepository", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/AuthRepository;Lcom/arflix/tv/data/repository/ProfileRepository;Lcom/arflix/tv/data/repository/ProfileManager;Lcom/arflix/tv/data/repository/TraktRepository;Lcom/arflix/tv/data/repository/WatchHistoryRepository;Lcom/arflix/tv/data/repository/WatchlistRepository;Lcom/arflix/tv/data/repository/IptvRepository;Lcom/arflix/tv/data/repository/ProfileAvatarImageManager;Lcom/arflix/tv/data/repository/CloudSyncRepository;)V", "Lx6/t0;", "dismissToast", "()V", "Lcom/arflix/tv/data/model/Profile;", "profile", "preloadForProfile", "(Lcom/arflix/tv/data/model/Profile;)V", "selectProfile", "switchProfile", "showAddDialog", "hideAddDialog", "", ContentDisposition.Parameters.Name, "setNewProfileName", "(Ljava/lang/String;)V", "", "index", "setSelectedColorIndex", "(I)V", TtmlNode.ATTR_ID, "setSelectedAvatarId", "uri", "setSelectedAvatarImage", "removeSelectedAvatarImage", "createProfile", "showEditDialog", "hideEditDialog", "updateProfile", "toggleManageMode", "exitManageMode", "deleteProfile", "selectProfileWithLockCheck", "showPinSetupDialog", "hidePinDialog", "enteredPin", "verifyPinAndSelectProfile", "pin", "setupProfilePin", "removeProfilePin", "loadProfiles", "observeProfiles", "restoreCloudProfilesForFreshLogin", "message", "Lcom/arflix/tv/ui/components/ToastType;", LinkHeader.Parameters.Type, "showToast", "(Ljava/lang/String;Lcom/arflix/tv/ui/components/ToastType;)V", "performDeleteProfile", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/AuthRepository;", "Lcom/arflix/tv/data/repository/ProfileRepository;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lcom/arflix/tv/data/repository/TraktRepository;", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "Lcom/arflix/tv/data/repository/IptvRepository;", "Lcom/arflix/tv/data/repository/ProfileAvatarImageManager;", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "Lna/q0;", "Lcom/arflix/tv/ui/screens/profile/ProfileUiState;", "_uiState", "Lna/q0;", "Lna/h1;", "uiState", "Lna/h1;", "getUiState", "()Lna/h1;", "lastInitialRestoreUserId", "Ljava/lang/String;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ProfileViewModel extends d1 {
    public static final int $stable = 8;
    private final q0<ProfileUiState> _uiState;
    private final AuthRepository authRepository;
    private final CloudSyncRepository cloudSyncRepository;
    private final Context context;
    private final IptvRepository iptvRepository;
    private String lastInitialRestoreUserId;
    private final ProfileAvatarImageManager profileAvatarImageManager;
    private final ProfileManager profileManager;
    private final ProfileRepository profileRepository;
    private final TraktRepository traktRepository;
    private final h1<ProfileUiState> uiState;
    private final WatchHistoryRepository watchHistoryRepository;
    private final WatchlistRepository watchlistRepository;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$createProfile$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$createProfile$1", f = "ProfileViewModel.kt", l = {326, 335, 337, 350}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ ProfileUiState $state;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ProfileUiState profileUiState, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$state = profileUiState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = ProfileViewModel.this.new AnonymousClass1(this.$state, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x0125, code lost:
        
            if (r1.updateProfile(r2, r39) == r14) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x01b2, code lost:
        
            if (r0.m6125pushToCloudgIAlus(true, r39) != r14) goto L53;
         */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00ea  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0131  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r40) {
            /*
                Method dump skipped, instruction units count: 440
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$loadProfiles$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$loadProfiles$1", f = "ProfileViewModel.kt", l = {88, TsExtractor.TS_STREAM_TYPE_DVBSUBS}, m = "invokeSuspend", v = 2)
    public static final class C14511 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        Object L$0;
        int label;

        public C14511(d7.d<? super C14511> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return ProfileViewModel.this.new C14511(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0089, code lost:
        
            if (r2 == r4) goto L16;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r31) {
            /*
                Method dump skipped, instruction units count: 205
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileViewModel.C14511.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14511) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$observeProfiles$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$observeProfiles$1", f = "ProfileViewModel.kt", l = {100}, m = "invokeSuspend", v = 2)
    public static final class C14521 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        int label;

        public C14521(d7.d<? super C14521> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return ProfileViewModel.this.new C14521(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<List<Profile>> profiles = ProfileViewModel.this.profileRepository.getProfiles();
                final ProfileViewModel profileViewModel = ProfileViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.profile.ProfileViewModel.observeProfiles.1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((List<Profile>) obj2, (d7.d<? super t0>) dVar);
                    }

                    public final Object emit(List<Profile> list, d7.d<? super t0> dVar) {
                        profileViewModel._uiState.setValue(ProfileUiState.copy$default((ProfileUiState) profileViewModel._uiState.getValue(), list, null, ((ProfileUiState) profileViewModel._uiState.getValue()).isLoading() && list.isEmpty(), false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2097146, null));
                        return t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = profiles.collect(kVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
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
            return ((C14521) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$observeProfiles$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$observeProfiles$2", f = "ProfileViewModel.kt", l = {108}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        int label;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return ProfileViewModel.this.new AnonymousClass2(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<Profile> activeProfile = ProfileViewModel.this.profileRepository.getActiveProfile();
                final ProfileViewModel profileViewModel = ProfileViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.profile.ProfileViewModel.observeProfiles.2.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((Profile) obj2, (d7.d<? super t0>) dVar);
                    }

                    public final Object emit(Profile profile, d7.d<? super t0> dVar) {
                        profileViewModel._uiState.setValue(ProfileUiState.copy$default((ProfileUiState) profileViewModel._uiState.getValue(), null, profile, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2097149, null));
                        return t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = activeProfile.collect(kVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
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
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$performDeleteProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$performDeleteProfile$1", f = "ProfileViewModel.kt", l = {450, 460}, m = "invokeSuspend", v = 2)
    public static final class C14531 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ Profile $profile;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14531(Profile profile, d7.d<? super C14531> dVar) {
            super(2, dVar);
            this.$profile = profile;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C14531 c14531 = ProfileViewModel.this.new C14531(this.$profile, dVar);
            c14531.L$0 = obj;
            return c14531;
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x00d1, code lost:
        
            if (r8.m6125pushToCloudgIAlus(true, r7) == r5) goto L26;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                Method dump skipped, instruction units count: 215
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileViewModel.C14531.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14531) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$preloadForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$preloadForProfile$1", f = "ProfileViewModel.kt", l = {TsExtractor.TS_PACKET_SIZE}, m = "invokeSuspend", v = 2)
    public static final class C14541 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ Profile $profile;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14541(Profile profile, d7.d<? super C14541> dVar) {
            super(2, dVar);
            this.$profile = profile;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return ProfileViewModel.this.new C14541(this.$profile, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Exception {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                TraktRepository traktRepository = ProfileViewModel.this.traktRepository;
                String id = this.$profile.getId();
                this.label = 1;
                Object objPreloadContinueWatchingForProfile = traktRepository.preloadContinueWatchingForProfile(id, this);
                e7.a aVar = e7.a.f15033i;
                if (objPreloadContinueWatchingForProfile == aVar) {
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
            return ((C14541) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$removeProfilePin$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$removeProfilePin$1", f = "ProfileViewModel.kt", l = {551, 553}, m = "invokeSuspend", v = 2)
    public static final class C14551 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ Profile $updatedProfile;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14551(Profile profile, d7.d<? super C14551> dVar) {
            super(2, dVar);
            this.$updatedProfile = profile;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C14551 c14551 = ProfileViewModel.this.new C14551(this.$updatedProfile, dVar);
            c14551.L$0 = obj;
            return c14551;
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0099, code lost:
        
            if (r1.m6125pushToCloudgIAlus(true, r30) == r5) goto L18;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r31) {
            /*
                r30 = this;
                r0 = r30
                java.lang.Object r1 = r0.L$0
                ka.k0 r1 = (ka.k0) r1
                int r2 = r0.label
                r3 = 2
                r4 = 1
                e7.a r5 = e7.a.f15033i
                if (r2 == 0) goto L2d
                if (r2 == r4) goto L29
                if (r2 != r3) goto L21
                java.lang.Object r1 = r0.L$1
                ka.k0 r1 = (ka.k0) r1
                k2.c.G(r31)     // Catch: java.lang.Throwable -> L9c
                r1 = r31
                x6.d0 r1 = (x6.d0) r1     // Catch: java.lang.Throwable -> L9c
                java.lang.Object r1 = r1.f22580i     // Catch: java.lang.Throwable -> L9c
                goto L9c
            L21:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r1
            L29:
                k2.c.G(r31)
                goto L43
            L2d:
                k2.c.G(r31)
                com.arflix.tv.ui.screens.profile.ProfileViewModel r2 = com.arflix.tv.ui.screens.profile.ProfileViewModel.this
                com.arflix.tv.data.repository.ProfileRepository r2 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$getProfileRepository$p(r2)
                com.arflix.tv.data.model.Profile r6 = r0.$updatedProfile
                r0.L$0 = r1
                r0.label = r4
                java.lang.Object r1 = r2.updateProfile(r6, r0)
                if (r1 != r5) goto L43
                goto L9b
            L43:
                com.arflix.tv.ui.screens.profile.ProfileViewModel r1 = com.arflix.tv.ui.screens.profile.ProfileViewModel.this
                na.q0 r1 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$get_uiState$p(r1)
                com.arflix.tv.ui.screens.profile.ProfileViewModel r2 = com.arflix.tv.ui.screens.profile.ProfileViewModel.this
                na.q0 r2 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$get_uiState$p(r2)
                java.lang.Object r2 = r2.getValue()
                r6 = r2
                com.arflix.tv.ui.screens.profile.ProfileUiState r6 = (com.arflix.tv.ui.screens.profile.ProfileUiState) r6
                com.arflix.tv.data.model.Profile r2 = r0.$updatedProfile
                r28 = 2093055(0x1fefff, float:2.932995E-39)
                r29 = 0
                r7 = 0
                r8 = 0
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                r13 = 0
                r14 = 0
                r15 = 0
                r16 = 0
                r17 = 0
                r18 = 0
                r20 = 0
                r21 = 0
                r22 = 0
                r23 = 0
                r24 = 0
                r25 = 0
                r26 = 0
                r27 = 0
                r19 = r2
                com.arflix.tv.ui.screens.profile.ProfileUiState r2 = com.arflix.tv.ui.screens.profile.ProfileUiState.copy$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
                r1.setValue(r2)
                com.arflix.tv.ui.screens.profile.ProfileViewModel r1 = com.arflix.tv.ui.screens.profile.ProfileViewModel.this
                com.arflix.tv.data.repository.CloudSyncRepository r1 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$getCloudSyncRepository$p(r1)     // Catch: java.lang.Throwable -> L9c
                r2 = 0
                r0.L$0 = r2     // Catch: java.lang.Throwable -> L9c
                r0.L$1 = r2     // Catch: java.lang.Throwable -> L9c
                r2 = 0
                r0.I$0 = r2     // Catch: java.lang.Throwable -> L9c
                r0.label = r3     // Catch: java.lang.Throwable -> L9c
                java.lang.Object r1 = r1.m6125pushToCloudgIAlus(r4, r0)     // Catch: java.lang.Throwable -> L9c
                if (r1 != r5) goto L9c
            L9b:
                return r5
            L9c:
                x6.t0 r1 = x6.t0.f22605a
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileViewModel.C14551.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14551) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$restoreCloudProfilesForFreshLogin$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$restoreCloudProfilesForFreshLogin$1", f = "ProfileViewModel.kt", l = {AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID}, m = "invokeSuspend", v = 2)
    public static final class C14561 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public C14561(d7.d<? super C14561> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C14561 c14561 = ProfileViewModel.this.new C14561(dVar);
            c14561.L$0 = obj;
            return c14561;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                h1<AuthState> authState = ProfileViewModel.this.authRepository.getAuthState();
                C01521 c01521 = new C01521(ProfileViewModel.this, k0Var);
                this.L$0 = null;
                this.label = 1;
                Object objCollect = authState.collect(c01521, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            throw new KotlinNothingValueException();
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14561) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class C01521<T> implements na.k {
            final /* synthetic */ k0 $$this$launch;
            final /* synthetic */ ProfileViewModel this$0;

            /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/CloudSyncRepository$RestoreResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/CloudSyncRepository$RestoreResult;"}, k = 3, mv = {2, 3, 0})
            @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$1", f = "ProfileViewModel.kt", l = {149}, m = "invokeSuspend", v = 2)
            public static final class C01531 extends f7.j implements r7.p<k0, d7.d<? super CloudSyncRepository.RestoreResult>, Object> {
                int label;
                final /* synthetic */ ProfileViewModel this$0;

                /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/CloudSyncRepository$RestoreResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/CloudSyncRepository$RestoreResult;"}, k = 3, mv = {2, 3, 0})
                @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$restoreCloudProfilesForFreshLogin$1$1$1$1", f = "ProfileViewModel.kt", l = {150}, m = "invokeSuspend", v = 2)
                public static final class C01541 extends f7.j implements r7.p<k0, d7.d<? super CloudSyncRepository.RestoreResult>, Object> {
                    int label;
                    final /* synthetic */ ProfileViewModel this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C01541(ProfileViewModel profileViewModel, d7.d<? super C01541> dVar) {
                        super(2, dVar);
                        this.this$0 = profileViewModel;
                    }

                    @Override // f7.a
                    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                        return new C01541(this.this$0, dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        int i10 = this.label;
                        if (i10 != 0) {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            k2.c.G(obj);
                            return obj;
                        }
                        k2.c.G(obj);
                        CloudSyncRepository cloudSyncRepository = this.this$0.cloudSyncRepository;
                        this.label = 1;
                        Object objPullFromCloud = cloudSyncRepository.pullFromCloud(false, this);
                        e7.a aVar = e7.a.f15033i;
                        return objPullFromCloud == aVar ? aVar : objPullFromCloud;
                    }

                    @Override // r7.p
                    public final Object invoke(k0 k0Var, d7.d<? super CloudSyncRepository.RestoreResult> dVar) {
                        return ((C01541) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01531(ProfileViewModel profileViewModel, d7.d<? super C01531> dVar) {
                    super(2, dVar);
                    this.this$0 = profileViewModel;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    return new C01531(this.this$0, dVar);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) throws Throwable {
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        C01541 c01541 = new C01541(this.this$0, null);
                        this.label = 1;
                        obj = m0.A(18000L, c01541, this);
                        e7.a aVar = e7.a.f15033i;
                        if (obj == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                    }
                    CloudSyncRepository.RestoreResult restoreResult = (CloudSyncRepository.RestoreResult) obj;
                    return restoreResult == null ? CloudSyncRepository.RestoreResult.FAILED : restoreResult;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d7.d<? super CloudSyncRepository.RestoreResult> dVar) {
                    return ((C01531) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            public C01521(ProfileViewModel profileViewModel, k0 k0Var) {
                this.this$0 = profileViewModel;
                this.$$this$launch = k0Var;
            }

            /* JADX WARN: Code restructure failed: missing block: B:64:0x01d1, code lost:
            
                if (r2 != r9) goto L66;
             */
            /* JADX WARN: Removed duplicated region for block: B:37:0x0106  */
            /* JADX WARN: Removed duplicated region for block: B:46:0x0130  */
            /* JADX WARN: Removed duplicated region for block: B:53:0x013f  */
            /* JADX WARN: Removed duplicated region for block: B:57:0x019e  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x01a5  */
            /* JADX WARN: Removed duplicated region for block: B:67:0x01d6 A[PHI: r0
              0x01d6: PHI (r0v11 int) = (r0v9 int), (r0v12 int) binds: [B:59:0x01a3, B:66:0x01d4] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:70:0x01ee A[PHI: r0 r2
              0x01ee: PHI (r0v13 int) = (r0v11 int), (r0v41 int) binds: [B:68:0x01eb, B:13:0x004a] A[DONT_GENERATE, DONT_INLINE]
              0x01ee: PHI (r2v24 java.lang.Object) = (r2v21 java.lang.Object), (r2v1 java.lang.Object) binds: [B:68:0x01eb, B:13:0x004a] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:73:0x020a  */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.arflix.tv.data.repository.AuthState r35, d7.d<? super x6.t0> r36) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 614
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileViewModel.C14561.C01521.emit(com.arflix.tv.data.repository.AuthState, d7.d):java.lang.Object");
            }

            @Override // na.k
            public /* bridge */ /* synthetic */ Object emit(Object obj, d7.d dVar) {
                return emit((AuthState) obj, (d7.d<? super t0>) dVar);
            }
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1", f = "ProfileViewModel.kt", l = {198, 202, 225}, m = "invokeSuspend", v = 2)
    public static final class C14571 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ Profile $profile;
        Object L$0;
        boolean Z$0;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1$1", f = "ProfileViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01551 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ boolean $isSameProfile;
            int label;
            final /* synthetic */ ProfileViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01551(boolean z, ProfileViewModel profileViewModel, d7.d<? super C01551> dVar) {
                super(2, dVar);
                this.$isSameProfile = z;
                this.this$0 = profileViewModel;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new C01551(this.$isSameProfile, this.this$0, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                if (!this.$isSameProfile) {
                    this.this$0.traktRepository.clearAllProfileCaches();
                    this.this$0.watchHistoryRepository.clearProfileCaches();
                    this.this$0.watchlistRepository.clearWatchlistCache();
                    this.this$0.iptvRepository.invalidateCache();
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((C01551) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1$2", f = "ProfileViewModel.kt", l = {226}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass2 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ Profile $profile;
            int label;
            final /* synthetic */ ProfileViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(ProfileViewModel profileViewModel, Profile profile, d7.d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.this$0 = profileViewModel;
                this.$profile = profile;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass2(this.this$0, this.$profile, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    ProfileRepository profileRepository = this.this$0.profileRepository;
                    String id = this.$profile.getId();
                    this.label = 1;
                    Object activeProfile = profileRepository.setActiveProfile(id, this);
                    e7.a aVar = e7.a.f15033i;
                    if (activeProfile == aVar) {
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
                return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1$3", f = "ProfileViewModel.kt", l = {231, 232}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass3 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ Profile $profile;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ ProfileViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(ProfileViewModel profileViewModel, Profile profile, d7.d<? super AnonymousClass3> dVar) {
                super(2, dVar);
                this.this$0 = profileViewModel;
                this.$profile = profile;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, this.$profile, dVar);
                anonymousClass3.L$0 = obj;
                return anonymousClass3;
            }

            /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
            
                if (r7.warmupFromCacheOnly(r6) == r5) goto L21;
             */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                /*
                    r6 = this;
                    java.lang.Object r0 = r6.L$0
                    ka.k0 r0 = (ka.k0) r0
                    int r1 = r6.label
                    x6.t0 r2 = x6.t0.f22605a
                    r3 = 2
                    r4 = 1
                    e7.a r5 = e7.a.f15033i
                    if (r1 == 0) goto L26
                    if (r1 == r4) goto L22
                    if (r1 != r3) goto L1a
                    java.lang.Object r0 = r6.L$1
                    ka.k0 r0 = (ka.k0) r0
                    k2.c.G(r7)     // Catch: java.lang.Throwable -> L5e
                    goto L5e
                L1a:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r0)
                    throw r7
                L22:
                    k2.c.G(r7)
                    goto L3a
                L26:
                    k2.c.G(r7)
                    com.arflix.tv.ui.screens.profile.ProfileViewModel r7 = r6.this$0
                    com.arflix.tv.data.repository.ProfileRepository r7 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$getProfileRepository$p(r7)
                    r6.L$0 = r0
                    r6.label = r4
                    java.lang.Object r7 = r7.getActiveProfileId(r6)
                    if (r7 != r5) goto L3a
                    goto L5d
                L3a:
                    com.arflix.tv.data.model.Profile r0 = r6.$profile
                    java.lang.String r0 = r0.getId()
                    boolean r7 = kotlin.jvm.internal.p.a(r7, r0)
                    if (r7 != 0) goto L47
                    return r2
                L47:
                    com.arflix.tv.ui.screens.profile.ProfileViewModel r7 = r6.this$0
                    com.arflix.tv.data.repository.IptvRepository r7 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$getIptvRepository$p(r7)     // Catch: java.lang.Throwable -> L5e
                    r0 = 0
                    r6.L$0 = r0     // Catch: java.lang.Throwable -> L5e
                    r6.L$1 = r0     // Catch: java.lang.Throwable -> L5e
                    r0 = 0
                    r6.I$0 = r0     // Catch: java.lang.Throwable -> L5e
                    r6.label = r3     // Catch: java.lang.Throwable -> L5e
                    java.lang.Object r7 = r7.warmupFromCacheOnly(r6)     // Catch: java.lang.Throwable -> L5e
                    if (r7 != r5) goto L5e
                L5d:
                    return r5
                L5e:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileViewModel.C14571.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1$4, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$selectProfile$1$4", f = "ProfileViewModel.kt", l = {243, 244, 246}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass4 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ Profile $profile;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ ProfileViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(ProfileViewModel profileViewModel, Profile profile, d7.d<? super AnonymousClass4> dVar) {
                super(2, dVar);
                this.this$0 = profileViewModel;
                this.$profile = profile;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass4 anonymousClass4 = new AnonymousClass4(this.this$0, this.$profile, dVar);
                anonymousClass4.L$0 = obj;
                return anonymousClass4;
            }

            /* JADX WARN: Code restructure failed: missing block: B:25:0x0070, code lost:
            
                if (r10.prefetchFreshStartupData(r9) == r6) goto L26;
             */
            /* JADX WARN: Removed duplicated region for block: B:22:0x005b A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:23:0x005c  */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                /*
                    r9 = this;
                    java.lang.Object r0 = r9.L$0
                    ka.k0 r0 = (ka.k0) r0
                    int r1 = r9.label
                    x6.t0 r2 = x6.t0.f22605a
                    r3 = 3
                    r4 = 2
                    r5 = 1
                    e7.a r6 = e7.a.f15033i
                    if (r1 == 0) goto L2d
                    if (r1 == r5) goto L29
                    if (r1 == r4) goto L25
                    if (r1 != r3) goto L1d
                    java.lang.Object r0 = r9.L$1
                    ka.k0 r0 = (ka.k0) r0
                    k2.c.G(r10)     // Catch: java.lang.Throwable -> L73
                    goto L73
                L1d:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r0)
                    throw r10
                L25:
                    k2.c.G(r10)
                    goto L4f
                L29:
                    k2.c.G(r10)
                    goto L3e
                L2d:
                    k2.c.G(r10)
                    r9.L$0 = r0
                    r9.label = r5
                    r7 = 45000(0xafc8, double:2.2233E-319)
                    java.lang.Object r10 = ka.s0.a(r7, r9)
                    if (r10 != r6) goto L3e
                    goto L72
                L3e:
                    com.arflix.tv.ui.screens.profile.ProfileViewModel r10 = r9.this$0
                    com.arflix.tv.data.repository.ProfileRepository r10 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$getProfileRepository$p(r10)
                    r9.L$0 = r0
                    r9.label = r4
                    java.lang.Object r10 = r10.getActiveProfileId(r9)
                    if (r10 != r6) goto L4f
                    goto L72
                L4f:
                    com.arflix.tv.data.model.Profile r0 = r9.$profile
                    java.lang.String r0 = r0.getId()
                    boolean r10 = kotlin.jvm.internal.p.a(r10, r0)
                    if (r10 != 0) goto L5c
                    return r2
                L5c:
                    com.arflix.tv.ui.screens.profile.ProfileViewModel r10 = r9.this$0
                    com.arflix.tv.data.repository.IptvRepository r10 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$getIptvRepository$p(r10)     // Catch: java.lang.Throwable -> L73
                    r0 = 0
                    r9.L$0 = r0     // Catch: java.lang.Throwable -> L73
                    r9.L$1 = r0     // Catch: java.lang.Throwable -> L73
                    r0 = 0
                    r9.I$0 = r0     // Catch: java.lang.Throwable -> L73
                    r9.label = r3     // Catch: java.lang.Throwable -> L73
                    java.lang.Object r10 = r10.prefetchFreshStartupData(r9)     // Catch: java.lang.Throwable -> L73
                    if (r10 != r6) goto L73
                L72:
                    return r6
                L73:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileViewModel.C14571.AnonymousClass4.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14571(Profile profile, d7.d<? super C14571> dVar) {
            super(2, dVar);
            this.$profile = profile;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return ProfileViewModel.this.new C14571(this.$profile, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x00f4, code lost:
        
            if (ka.m0.y(r4, r7, r32) == r6) goto L29;
         */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00df A[Catch: all -> 0x001b, TryCatch #0 {all -> 0x001b, blocks: (B:8:0x0016, B:30:0x00f7, B:15:0x002c, B:25:0x00b0, B:27:0x00df, B:16:0x0031, B:22:0x008e, B:19:0x007c), top: B:35:0x000a }] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r33) {
            /*
                Method dump skipped, instruction units count: 413
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileViewModel.C14571.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14571) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$setupProfilePin$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$setupProfilePin$1", f = "ProfileViewModel.kt", l = {539, 543}, m = "invokeSuspend", v = 2)
    public static final class C14581 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ Profile $updatedProfile;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14581(Profile profile, d7.d<? super C14581> dVar) {
            super(2, dVar);
            this.$updatedProfile = profile;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C14581 c14581 = ProfileViewModel.this.new C14581(this.$updatedProfile, dVar);
            c14581.L$0 = obj;
            return c14581;
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x00af, code lost:
        
            if (r1.m6125pushToCloudgIAlus(true, r30) == r5) goto L18;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r31) {
            /*
                r30 = this;
                r0 = r30
                java.lang.Object r1 = r0.L$0
                ka.k0 r1 = (ka.k0) r1
                int r2 = r0.label
                r3 = 2
                r4 = 1
                e7.a r5 = e7.a.f15033i
                if (r2 == 0) goto L2d
                if (r2 == r4) goto L29
                if (r2 != r3) goto L21
                java.lang.Object r1 = r0.L$1
                ka.k0 r1 = (ka.k0) r1
                k2.c.G(r31)     // Catch: java.lang.Throwable -> Lb2
                r1 = r31
                x6.d0 r1 = (x6.d0) r1     // Catch: java.lang.Throwable -> Lb2
                java.lang.Object r1 = r1.f22580i     // Catch: java.lang.Throwable -> Lb2
                goto Lb2
            L21:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r1
            L29:
                k2.c.G(r31)
                goto L43
            L2d:
                k2.c.G(r31)
                com.arflix.tv.ui.screens.profile.ProfileViewModel r2 = com.arflix.tv.ui.screens.profile.ProfileViewModel.this
                com.arflix.tv.data.repository.ProfileRepository r2 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$getProfileRepository$p(r2)
                com.arflix.tv.data.model.Profile r6 = r0.$updatedProfile
                r0.L$0 = r1
                r0.label = r4
                java.lang.Object r1 = r2.updateProfile(r6, r0)
                if (r1 != r5) goto L43
                goto Lb1
            L43:
                com.arflix.tv.ui.screens.profile.ProfileViewModel r1 = com.arflix.tv.ui.screens.profile.ProfileViewModel.this
                na.q0 r1 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$get_uiState$p(r1)
                com.arflix.tv.ui.screens.profile.ProfileViewModel r2 = com.arflix.tv.ui.screens.profile.ProfileViewModel.this
                na.q0 r2 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$get_uiState$p(r2)
                java.lang.Object r2 = r2.getValue()
                r6 = r2
                com.arflix.tv.ui.screens.profile.ProfileUiState r6 = (com.arflix.tv.ui.screens.profile.ProfileUiState) r6
                com.arflix.tv.data.model.Profile r2 = r0.$updatedProfile
                r28 = 2093055(0x1fefff, float:2.932995E-39)
                r29 = 0
                r7 = 0
                r8 = 0
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                r13 = 0
                r14 = 0
                r15 = 0
                r16 = 0
                r17 = 0
                r18 = 0
                r20 = 0
                r21 = 0
                r22 = 0
                r23 = 0
                r24 = 0
                r25 = 0
                r26 = 0
                r27 = 0
                r19 = r2
                com.arflix.tv.ui.screens.profile.ProfileUiState r2 = com.arflix.tv.ui.screens.profile.ProfileUiState.copy$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
                r1.setValue(r2)
                com.arflix.tv.ui.screens.profile.ProfileViewModel r1 = com.arflix.tv.ui.screens.profile.ProfileViewModel.this
                r1.hidePinDialog()
                com.arflix.tv.ui.screens.profile.ProfileViewModel r1 = com.arflix.tv.ui.screens.profile.ProfileViewModel.this
                android.content.Context r2 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$getContext$p(r1)
                int r6 = com.arflix.tv.R.string.profile_pin_set_success
                java.lang.String r2 = r2.getString(r6)
                com.arflix.tv.ui.components.ToastType r6 = com.arflix.tv.ui.components.ToastType.SUCCESS
                com.arflix.tv.ui.screens.profile.ProfileViewModel.access$showToast(r1, r2, r6)
                com.arflix.tv.ui.screens.profile.ProfileViewModel r1 = com.arflix.tv.ui.screens.profile.ProfileViewModel.this
                com.arflix.tv.data.repository.CloudSyncRepository r1 = com.arflix.tv.ui.screens.profile.ProfileViewModel.access$getCloudSyncRepository$p(r1)     // Catch: java.lang.Throwable -> Lb2
                r2 = 0
                r0.L$0 = r2     // Catch: java.lang.Throwable -> Lb2
                r0.L$1 = r2     // Catch: java.lang.Throwable -> Lb2
                r2 = 0
                r0.I$0 = r2     // Catch: java.lang.Throwable -> Lb2
                r0.label = r3     // Catch: java.lang.Throwable -> Lb2
                java.lang.Object r1 = r1.m6125pushToCloudgIAlus(r4, r0)     // Catch: java.lang.Throwable -> Lb2
                if (r1 != r5) goto Lb2
            Lb1:
                return r5
            Lb2:
                x6.t0 r1 = x6.t0.f22605a
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileViewModel.C14581.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14581) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$showToast$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$showToast$1", f = "ProfileViewModel.kt", l = {173}, m = "invokeSuspend", v = 2)
    public static final class C14591 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        int label;

        public C14591(d7.d<? super C14591> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return ProfileViewModel.this.new C14591(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = s0.a(3500L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            ProfileViewModel.this._uiState.setValue(ProfileUiState.copy$default((ProfileUiState) ProfileViewModel.this._uiState.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2064383, null));
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14591) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$switchProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$switchProfile$1", f = "ProfileViewModel.kt", l = {263}, m = "invokeSuspend", v = 2)
    public static final class C14601 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        int label;

        public C14601(d7.d<? super C14601> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return ProfileViewModel.this.new C14601(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                ProfileRepository profileRepository = ProfileViewModel.this.profileRepository;
                this.label = 1;
                Object objClearActiveProfile = profileRepository.clearActiveProfile(this);
                e7.a aVar = e7.a.f15033i;
                if (objClearActiveProfile == aVar) {
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
            return ((C14601) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.profile.ProfileViewModel$updateProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileViewModel$updateProfile$1", f = "ProfileViewModel.kt", l = {397, 416, 419}, m = "invokeSuspend", v = 2)
    public static final class C14611 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ Profile $editing;
        final /* synthetic */ ProfileUiState $state;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ ProfileViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14611(Profile profile, ProfileUiState profileUiState, ProfileViewModel profileViewModel, d7.d<? super C14611> dVar) {
            super(2, dVar);
            this.$editing = profile;
            this.$state = profileUiState;
            this.this$0 = profileViewModel;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C14611 c14611 = new C14611(this.$editing, this.$state, this.this$0, dVar);
            c14611.L$0 = obj;
            return c14611;
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x01e7, code lost:
        
            if (r0.m6125pushToCloudgIAlus(true, r34) != r9) goto L52;
         */
        /* JADX WARN: Removed duplicated region for block: B:44:0x017c  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r35) {
            /*
                Method dump skipped, instruction units count: 491
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileViewModel.C14611.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14611) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public ProfileViewModel(@ApplicationContext Context context, AuthRepository authRepository, ProfileRepository profileRepository, ProfileManager profileManager, TraktRepository traktRepository, WatchHistoryRepository watchHistoryRepository, WatchlistRepository watchlistRepository, IptvRepository iptvRepository, ProfileAvatarImageManager profileAvatarImageManager, CloudSyncRepository cloudSyncRepository) {
        this.context = context;
        this.authRepository = authRepository;
        this.profileRepository = profileRepository;
        this.profileManager = profileManager;
        this.traktRepository = traktRepository;
        this.watchHistoryRepository = watchHistoryRepository;
        this.watchlistRepository = watchlistRepository;
        this.iptvRepository = iptvRepository;
        this.profileAvatarImageManager = profileAvatarImageManager;
        this.cloudSyncRepository = cloudSyncRepository;
        j1 j1VarB = y0.b(new ProfileUiState(null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2097151, null));
        this._uiState = j1VarB;
        this.uiState = y0.e(j1VarB);
        loadProfiles();
        observeProfiles();
        restoreCloudProfilesForFreshLogin();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadProfiles() {
        m0.p(z0.h(this), null, 0, new C14511(null), 3);
    }

    private final void observeProfiles() {
        m0.p(z0.h(this), null, 0, new C14521(null), 3);
        m0.p(z0.h(this), null, 0, new AnonymousClass2(null), 3);
    }

    private final void performDeleteProfile(Profile profile) {
        m0.p(z0.h(this), null, 0, new C14531(profile, null), 3);
    }

    private final void restoreCloudProfilesForFreshLogin() {
        m0.p(z0.h(this), null, 0, new C14561(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showToast(String message, ToastType type) {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, message, type, true, false, null, null, null, null, 2039807, null));
        m0.p(z0.h(this), null, 0, new C14591(null), 3);
    }

    public static /* synthetic */ void showToast$default(ProfileViewModel profileViewModel, String str, ToastType toastType, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            toastType = ToastType.SUCCESS;
        }
        profileViewModel.showToast(str, toastType);
    }

    public final void createProfile() {
        ProfileUiState profileUiState = (ProfileUiState) this._uiState.getValue();
        if (kotlin.text.o.h0(profileUiState.getNewProfileName())) {
            return;
        }
        m0.p(z0.h(this), null, 0, new AnonymousClass1(profileUiState, null), 3);
    }

    public final void deleteProfile(Profile profile) {
        String pin;
        if (!profile.isLocked() || (pin = profile.getPin()) == null || pin.length() == 0) {
            performDeleteProfile(profile);
        } else {
            q0<ProfileUiState> q0Var = this._uiState;
            q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, true, "verify", profile, "delete", null, 1114111, null));
        }
    }

    public final void dismissToast() {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2064383, null));
    }

    public final void exitManageMode() {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2097135, null));
    }

    public final h1<ProfileUiState> getUiState() {
        return this.uiState;
    }

    public final void hideAddDialog() {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2097119, null));
    }

    public final void hideEditDialog() {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2093055, null));
    }

    public final void hidePinDialog() {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, "", null, "", "", DatagramKt.MAX_DATAGRAM_SIZE, null));
    }

    public final void preloadForProfile(Profile profile) {
        m0.p(z0.h(this), null, 0, new C14541(profile, null), 3);
    }

    public final void removeProfilePin() {
        Profile editingProfile = ((ProfileUiState) this._uiState.getValue()).getEditingProfile();
        if (editingProfile == null) {
            return;
        }
        m0.p(z0.h(this), null, 0, new C14551(Profile.copy$default(editingProfile, null, null, 0L, 0, 0L, null, false, null, false, 0L, 0L, 1663, null), null), 3);
    }

    public final void removeSelectedAvatarImage() {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2095615, null));
    }

    public final void selectProfile(Profile profile) {
        if (((ProfileUiState) this._uiState.getValue()).isSwitchingProfile()) {
            return;
        }
        m0.p(z0.h(this), null, 0, new C14571(profile, null), 3);
    }

    public final void selectProfileWithLockCheck(Profile profile) {
        String pin;
        if (!profile.isLocked() || (pin = profile.getPin()) == null || pin.length() == 0) {
            selectProfile(profile);
        } else {
            q0<ProfileUiState> q0Var = this._uiState;
            q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, true, "verify", profile, "select", null, 1114111, null));
        }
    }

    public final void setNewProfileName(String name) {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, name, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2097087, null));
    }

    public final void setSelectedAvatarId(int id) {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, id, null, false, false, null, null, null, false, false, null, null, null, null, 2095359, null));
    }

    public final void setSelectedAvatarImage(String uri) {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, uri, true, false, null, null, null, false, false, null, null, null, null, 2095359, null));
    }

    public final void setSelectedColorIndex(int index) {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, index, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2095231, null));
    }

    public final void setupProfilePin(String pin) {
        Profile editingProfile = ((ProfileUiState) this._uiState.getValue()).getEditingProfile();
        if (editingProfile == null) {
            return;
        }
        m0.p(z0.h(this), null, 0, new C14581(Profile.copy$default(editingProfile, null, null, 0L, 0, 0L, null, false, PinUtil.INSTANCE.hashPin(pin), true, 0L, 0L, 1663, null), null), 3);
    }

    public final void showAddDialog() {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, true, "", ((ProfileUiState) this._uiState.getValue()).getProfiles().size() % ProfileColors.INSTANCE.getColors().size(), 0, null, false, false, null, null, null, false, false, null, null, null, null, 2093087, null));
    }

    public final void showEditDialog(Profile profile) {
        String pin;
        if (profile.isLocked() && (pin = profile.getPin()) != null && pin.length() != 0) {
            q0<ProfileUiState> q0Var = this._uiState;
            q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, true, "verify", profile, "edit", null, 1114111, null));
            return;
        }
        q0<ProfileUiState> q0Var2 = this._uiState;
        ProfileUiState profileUiState = (ProfileUiState) q0Var2.getValue();
        String name = profile.getName();
        Integer numValueOf = Integer.valueOf(ProfileColors.INSTANCE.getColors().indexOf(Long.valueOf(profile.getAvatarColor())));
        if (numValueOf.intValue() < 0) {
            numValueOf = null;
        }
        q0Var2.setValue(ProfileUiState.copy$default(profileUiState, null, null, false, false, false, false, name, numValueOf != null ? numValueOf.intValue() : 0, profile.getAvatarId(), null, profile.getAvatarImageVersion() > 0, false, profile, null, null, false, false, null, null, null, null, 2089023, null));
    }

    public final void showPinSetupDialog() {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, true, "setup", null, null, null, 1900543, null));
    }

    public final void switchProfile() {
        this.traktRepository.clearAllProfileCaches();
        this.watchHistoryRepository.clearProfileCaches();
        this.watchlistRepository.clearWatchlistCache();
        this.iptvRepository.invalidateCache();
        m0.p(z0.h(this), null, 0, new C14601(null), 3);
    }

    public final void toggleManageMode() {
        q0<ProfileUiState> q0Var = this._uiState;
        q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, !((ProfileUiState) this._uiState.getValue()).isManageMode(), false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, null, 2097135, null));
    }

    public final void updateProfile() {
        ProfileUiState profileUiState = (ProfileUiState) this._uiState.getValue();
        Profile editingProfile = profileUiState.getEditingProfile();
        if (editingProfile == null || kotlin.text.o.h0(profileUiState.getNewProfileName())) {
            return;
        }
        m0.p(z0.h(this), null, 0, new C14611(editingProfile, profileUiState, this, null), 3);
    }

    public final void verifyPinAndSelectProfile(String enteredPin) {
        Profile pendingProfileForPin = ((ProfileUiState) this._uiState.getValue()).getPendingProfileForPin();
        if (pendingProfileForPin == null) {
            return;
        }
        String pinContext = ((ProfileUiState) this._uiState.getValue()).getPinContext();
        if (!PinUtil.INSTANCE.verifyPin(enteredPin, pendingProfileForPin.getPin())) {
            q0<ProfileUiState> q0Var = this._uiState;
            q0Var.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, "", 1048575, null));
            q0<ProfileUiState> q0Var2 = this._uiState;
            q0Var2.setValue(ProfileUiState.copy$default((ProfileUiState) q0Var2.getValue(), null, null, false, false, false, false, null, 0, 0, null, false, false, null, null, null, false, false, null, null, null, "Incorrect PIN. Please try again.", 1048575, null));
            return;
        }
        hidePinDialog();
        if (!kotlin.jvm.internal.p.a(pinContext, "edit")) {
            if (kotlin.jvm.internal.p.a(pinContext, "delete")) {
                performDeleteProfile(pendingProfileForPin);
                return;
            } else {
                selectProfile(pendingProfileForPin);
                return;
            }
        }
        q0<ProfileUiState> q0Var3 = this._uiState;
        ProfileUiState profileUiState = (ProfileUiState) q0Var3.getValue();
        String name = pendingProfileForPin.getName();
        Integer numValueOf = Integer.valueOf(ProfileColors.INSTANCE.getColors().indexOf(Long.valueOf(pendingProfileForPin.getAvatarColor())));
        if (numValueOf.intValue() < 0) {
            numValueOf = null;
        }
        q0Var3.setValue(ProfileUiState.copy$default(profileUiState, null, null, false, false, false, false, name, numValueOf != null ? numValueOf.intValue() : 0, pendingProfileForPin.getAvatarId(), null, pendingProfileForPin.getAvatarImageVersion() > 0, false, pendingProfileForPin, null, null, false, false, null, null, null, null, 2089023, null));
    }
}
