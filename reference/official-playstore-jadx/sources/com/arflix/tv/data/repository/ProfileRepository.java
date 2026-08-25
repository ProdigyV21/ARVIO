package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.compose.runtime.ComposerKt;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.util.AppLogger;
import com.arflix.tv.util.Constants;
import com.arflix.tv.util.DataStoresKt;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.ktor.http.ContentDisposition;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import na.y0;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 @2\u00020\u0001:\u0001@B+\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fH\u0082@¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0016\u001a\u00020\u000f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0086@¢\u0006\u0004\b\u0018\u0010\u000eJ\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u000fH\u0086@¢\u0006\u0004\b\u0019\u0010\u000eJ\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0012H\u0086@¢\u0006\u0004\b\u001a\u0010\u000eJ\u0010\u0010\u001c\u001a\u00020\u001bH\u0086@¢\u0006\u0004\b\u001c\u0010\u000eJ4\u0010#\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020\u001bH\u0086@¢\u0006\u0004\b#\u0010$J\u0018\u0010&\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u0012H\u0086@¢\u0006\u0004\b&\u0010'J\u0018\u0010)\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u000fH\u0086@¢\u0006\u0004\b)\u0010*J\u0018\u0010+\u001a\u00020\f2\u0006\u0010(\u001a\u00020\u000fH\u0086@¢\u0006\u0004\b+\u0010*J\u0010\u0010,\u001a\u00020\fH\u0086@¢\u0006\u0004\b,\u0010\u000eJ(\u0010.\u001a\u00020\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\u0010-\u001a\u0004\u0018\u00010\u000fH\u0086@¢\u0006\u0004\b.\u0010/J\u0012\u00100\u001a\u0004\u0018\u00010\u0012H\u0086@¢\u0006\u0004\b0\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00101R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00102R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u00103R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u00104R\u0014\u00106\u001a\u0002058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u001c\u0010:\u001a\n 9*\u0004\u0018\u000108088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R#\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110<8\u0006¢\u0006\f\n\u0004\b\u0015\u0010=\u001a\u0004\b\u0018\u0010>R\u001f\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0<8\u0006¢\u0006\f\n\u0004\b-\u0010=\u001a\u0004\b\u0019\u0010>R\u001f\u0010?\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120<8\u0006¢\u0006\f\n\u0004\b?\u0010=\u001a\u0004\b\u001a\u0010>¨\u0006A"}, d2 = {"Lcom/arflix/tv/data/repository/ProfileRepository;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "Lcom/arflix/tv/data/repository/ProfileAvatarImageManager;", "profileAvatarImageManager", "Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;", "invalidationBus", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/AuthRepository;Lcom/arflix/tv/data/repository/ProfileAvatarImageManager;Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;)V", "Lx6/t0;", "pushProfilesStateToCloud", "(Ld7/d;)Ljava/lang/Object;", "", "json", "", "Lcom/arflix/tv/data/model/Profile;", "decodeProfiles", "(Ljava/lang/String;)Ljava/util/List;", "profiles", "encodeProfiles", "(Ljava/util/List;)Ljava/lang/String;", "getProfiles", "getActiveProfileId", "getActiveProfile", "", "hasProfiles", ContentDisposition.Parameters.Name, "", "avatarColor", "", "avatarId", "isKidsProfile", "createProfile", "(Ljava/lang/String;JIZLd7/d;)Ljava/lang/Object;", "profile", "updateProfile", "(Lcom/arflix/tv/data/model/Profile;Ld7/d;)Ljava/lang/Object;", "profileId", "deleteProfile", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "setActiveProfile", "clearActiveProfile", "activeProfileId", "replaceProfilesFromCloud", "(Ljava/util/List;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "createDefaultProfileIfNeeded", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/AuthRepository;", "Lcom/arflix/tv/data/repository/ProfileAvatarImageManager;", "Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "Ljava/lang/reflect/Type;", "kotlin.jvm.PlatformType", "profileListType", "Ljava/lang/reflect/Type;", "Lna/j;", "Lna/j;", "()Lna/j;", "activeProfile", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ProfileRepository {
    private final na.j<Profile> activeProfile;
    private final na.j<String> activeProfileId;
    private final AuthRepository authRepository;
    private final Context context;
    private final CloudSyncInvalidationBus invalidationBus;
    private final ProfileAvatarImageManager profileAvatarImageManager;
    private final na.j<List<Profile>> profiles;
    public static final int $stable = 8;
    private static final c1.e PROFILES_KEY = new c1.e("profiles");
    private static final c1.e ACTIVE_PROFILE_KEY = new c1.e("active_profile_id");
    private final Gson gson = new Gson();
    private final Type profileListType = TypeToken.getParameterized(List.class, Profile.class).getType();

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$clearActiveProfile$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository", f = "ProfileRepository.kt", l = {159, 163}, m = "clearActiveProfile", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileRepository.this.clearActiveProfile(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$clearActiveProfile$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository$clearActiveProfile$2", f = "ProfileRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.c(ProfileRepository.ACTIVE_PROFILE_KEY);
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$createDefaultProfileIfNeeded$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository", f = "ProfileRepository.kt", l = {225, 226}, m = "createDefaultProfileIfNeeded", v = 2)
    public static final class C09471 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C09471(d7.d<? super C09471> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileRepository.this.createDefaultProfileIfNeeded(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$createProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository", f = "ProfileRepository.kt", l = {Constants.WATCHED_THRESHOLD, 96}, m = "createProfile", v = 2)
    public static final class C09481 extends f7.c {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C09481(d7.d<? super C09481> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileRepository.this.createProfile(null, 0L, 0, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$createProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository$createProfile$2", f = "ProfileRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C09492 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ Profile $profile;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09492(Profile profile, d7.d<? super C09492> dVar) {
            super(2, dVar);
            this.$profile = profile;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09492 c09492 = ProfileRepository.this.new C09492(this.$profile, dVar);
            c09492.L$0 = obj;
            return c09492;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C09492) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            ArrayList arrayList = new ArrayList(ProfileRepository.this.decodeProfiles((String) bVar.f7368a.get(ProfileRepository.PROFILES_KEY)));
            arrayList.add(this.$profile);
            bVar.d(ProfileRepository.PROFILES_KEY, ProfileRepository.this.encodeProfiles(arrayList));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$deleteProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository", f = "ProfileRepository.kt", l = {121, 133}, m = "deleteProfile", v = 2)
    public static final class C09501 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C09501(d7.d<? super C09501> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileRepository.this.deleteProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$deleteProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository$deleteProfile$2", f = "ProfileRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C09512 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $profileId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09512(String str, d7.d<? super C09512> dVar) {
            super(2, dVar);
            this.$profileId = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$lambda$0(String str, Profile profile) {
            return kotlin.jvm.internal.p.a(profile.getId(), str);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09512 c09512 = ProfileRepository.this.new C09512(this.$profileId, dVar);
            c09512.L$0 = obj;
            return c09512;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C09512) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            ArrayList arrayList = new ArrayList(ProfileRepository.this.decodeProfiles((String) bVar.f7368a.get(ProfileRepository.PROFILES_KEY)));
            kotlin.collections.x.M0(arrayList, new x(this.$profileId, 1));
            bVar.d(ProfileRepository.PROFILES_KEY, ProfileRepository.this.encodeProfiles(arrayList));
            if (kotlin.jvm.internal.p.a(bVar.f7368a.get(ProfileRepository.ACTIVE_PROFILE_KEY), this.$profileId)) {
                bVar.c(ProfileRepository.ACTIVE_PROFILE_KEY);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$hasProfiles$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository", f = "ProfileRepository.kt", l = {77}, m = "hasProfiles", v = 2)
    public static final class C09521 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C09521(d7.d<? super C09521> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileRepository.this.hasProfiles(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$pushProfilesStateToCloud$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository", f = "ProfileRepository.kt", l = {ComposerKt.providerMapsKey, 205, ComposerKt.referenceKey, ComposerKt.reuseKey}, m = "pushProfilesStateToCloud", v = 2)
    public static final class C09531 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C09531(d7.d<? super C09531> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileRepository.this.pushProfilesStateToCloud(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$replaceProfilesFromCloud$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository", f = "ProfileRepository.kt", l = {170, TsExtractor.TS_PACKET_SIZE, 199}, m = "replaceProfilesFromCloud", v = 2)
    public static final class C09541 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C09541(d7.d<? super C09541> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileRepository.this.replaceProfilesFromCloud(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$replaceProfilesFromCloud$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository$replaceProfilesFromCloud$2", f = "ProfileRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C09552 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $activeProfileId;
        final /* synthetic */ List<Profile> $mergedProfiles;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09552(List<Profile> list, String str, d7.d<? super C09552> dVar) {
            super(2, dVar);
            this.$mergedProfiles = list;
            this.$activeProfileId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09552 c09552 = ProfileRepository.this.new C09552(this.$mergedProfiles, this.$activeProfileId, dVar);
            c09552.L$0 = obj;
            return c09552;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C09552) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0076  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r4) {
            /*
                r3 = this;
                java.lang.Object r0 = r3.L$0
                c1.b r0 = (c1.b) r0
                int r1 = r3.label
                if (r1 != 0) goto L80
                k2.c.G(r4)
                c1.e r4 = com.arflix.tv.data.repository.ProfileRepository.access$getPROFILES_KEY$cp()
                com.arflix.tv.data.repository.ProfileRepository r1 = com.arflix.tv.data.repository.ProfileRepository.this
                com.google.gson.Gson r1 = com.arflix.tv.data.repository.ProfileRepository.access$getGson$p(r1)
                java.util.List<com.arflix.tv.data.model.Profile> r2 = r3.$mergedProfiles
                java.lang.String r1 = r1.toJson(r2)
                r0.d(r4, r1)
                java.lang.String r4 = r3.$activeProfileId
                if (r4 == 0) goto L5a
                boolean r4 = kotlin.text.o.h0(r4)
                if (r4 == 0) goto L29
                goto L5a
            L29:
                java.util.List<com.arflix.tv.data.model.Profile> r4 = r3.$mergedProfiles
                java.lang.String r1 = r3.$activeProfileId
                if (r4 == 0) goto L36
                boolean r2 = r4.isEmpty()
                if (r2 == 0) goto L36
                goto L5a
            L36:
                java.util.Iterator r4 = r4.iterator()
            L3a:
                boolean r2 = r4.hasNext()
                if (r2 == 0) goto L5a
                java.lang.Object r2 = r4.next()
                com.arflix.tv.data.model.Profile r2 = (com.arflix.tv.data.model.Profile) r2
                java.lang.String r2 = r2.getId()
                boolean r2 = kotlin.jvm.internal.p.a(r2, r1)
                if (r2 == 0) goto L3a
                c1.e r4 = com.arflix.tv.data.repository.ProfileRepository.access$getACTIVE_PROFILE_KEY$cp()
                java.lang.String r1 = r3.$activeProfileId
                r0.d(r4, r1)
                goto L7d
            L5a:
                java.util.List<com.arflix.tv.data.model.Profile> r4 = r3.$mergedProfiles
                boolean r4 = r4.isEmpty()
                if (r4 != 0) goto L76
                c1.e r4 = com.arflix.tv.data.repository.ProfileRepository.access$getACTIVE_PROFILE_KEY$cp()
                java.util.List<com.arflix.tv.data.model.Profile> r1 = r3.$mergedProfiles
                java.lang.Object r1 = kotlin.collections.x.m0(r1)
                com.arflix.tv.data.model.Profile r1 = (com.arflix.tv.data.model.Profile) r1
                java.lang.String r1 = r1.getId()
                r0.d(r4, r1)
                goto L7d
            L76:
                c1.e r4 = com.arflix.tv.data.repository.ProfileRepository.access$getACTIVE_PROFILE_KEY$cp()
                r0.c(r4)
            L7d:
                x6.t0 r4 = x6.t0.f22605a
                return r4
            L80:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository.C09552.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$setActiveProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository", f = "ProfileRepository.kt", l = {140, 152}, m = "setActiveProfile", v = 2)
    public static final class C09561 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C09561(d7.d<? super C09561> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileRepository.this.setActiveProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$setActiveProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository$setActiveProfile$2", f = "ProfileRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C09572 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $profileId;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ProfileRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09572(String str, ProfileRepository profileRepository, d7.d<? super C09572> dVar) {
            super(2, dVar);
            this.$profileId = str;
            this.this$0 = profileRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09572 c09572 = new C09572(this.$profileId, this.this$0, dVar);
            c09572.L$0 = obj;
            return c09572;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C09572) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(ProfileRepository.ACTIVE_PROFILE_KEY, this.$profileId);
            ArrayList arrayList = new ArrayList(this.this$0.decodeProfiles((String) bVar.f7368a.get(ProfileRepository.PROFILES_KEY)));
            String str = this.$profileId;
            Iterator it = arrayList.iterator();
            int i10 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i10 = -1;
                    break;
                }
                if (kotlin.jvm.internal.p.a(((Profile) it.next()).getId(), str)) {
                    break;
                }
                i10++;
            }
            if (i10 >= 0) {
                arrayList.set(i10, Profile.copy$default((Profile) arrayList.get(i10), null, null, 0L, 0, 0L, null, false, null, false, 0L, System.currentTimeMillis(), AnalyticsListener.EVENT_DRM_KEYS_LOADED, null));
                bVar.d(ProfileRepository.PROFILES_KEY, this.this$0.encodeProfiles(arrayList));
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$updateProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository", f = "ProfileRepository.kt", l = {105, 114}, m = "updateProfile", v = 2)
    public static final class C09581 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C09581(d7.d<? super C09581> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileRepository.this.updateProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$updateProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository$updateProfile$2", f = "ProfileRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C09592 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ Profile $profile;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09592(Profile profile, d7.d<? super C09592> dVar) {
            super(2, dVar);
            this.$profile = profile;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09592 c09592 = ProfileRepository.this.new C09592(this.$profile, dVar);
            c09592.L$0 = obj;
            return c09592;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C09592) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            ArrayList arrayList = new ArrayList(ProfileRepository.this.decodeProfiles((String) bVar.f7368a.get(ProfileRepository.PROFILES_KEY)));
            Profile profile = this.$profile;
            Iterator it = arrayList.iterator();
            int i10 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i10 = -1;
                    break;
                }
                if (kotlin.jvm.internal.p.a(((Profile) it.next()).getId(), profile.getId())) {
                    break;
                }
                i10++;
            }
            if (i10 >= 0) {
                arrayList.set(i10, this.$profile);
                bVar.d(ProfileRepository.PROFILES_KEY, ProfileRepository.this.encodeProfiles(arrayList));
            }
            return t0.f22605a;
        }
    }

    @Inject
    public ProfileRepository(@ApplicationContext Context context, AuthRepository authRepository, ProfileAvatarImageManager profileAvatarImageManager, CloudSyncInvalidationBus cloudSyncInvalidationBus) {
        this.context = context;
        this.authRepository = authRepository;
        this.profileAvatarImageManager = profileAvatarImageManager;
        this.invalidationBus = cloudSyncInvalidationBus;
        final na.j data = DataStoresKt.getProfilesDataStore(context).getData();
        this.profiles = new na.j<List<? extends Profile>>() { // from class: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1

            /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
            public static final class AnonymousClass2<T> implements na.k {
                final /* synthetic */ na.k $this_unsafeFlow;
                final /* synthetic */ ProfileRepository this$0;

                /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1$2", f = "ProfileRepository.kt", l = {223}, m = "emit", v = 2)
                public static final class AnonymousClass1 extends f7.c {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d7.d dVar) {
                        super(dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(na.k kVar, ProfileRepository profileRepository) {
                    this.$this_unsafeFlow = kVar;
                    this.this$0 = profileRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // na.k
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, d7.d r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1$2$1 r0 = (com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1$2$1 r0 = new com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        int r1 = r0.label
                        r2 = 1
                        if (r1 == 0) goto L35
                        if (r1 != r2) goto L2d
                        java.lang.Object r5 = r0.L$3
                        na.k r5 = (na.k) r5
                        java.lang.Object r5 = r0.L$1
                        com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1$2$1 r5 = (com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r5
                        k2.c.G(r6)
                        goto L67
                    L2d:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L35:
                        k2.c.G(r6)
                        na.k r6 = r4.$this_unsafeFlow
                        c1.g r5 = (c1.g) r5
                        com.arflix.tv.data.repository.ProfileRepository r1 = r4.this$0
                        c1.e r3 = com.arflix.tv.data.repository.ProfileRepository.access$getPROFILES_KEY$cp()
                        c1.b r5 = (c1.b) r5
                        java.util.LinkedHashMap r5 = r5.f7368a
                        java.lang.Object r5 = r5.get(r3)
                        java.lang.String r5 = (java.lang.String) r5
                        java.util.List r5 = com.arflix.tv.data.repository.ProfileRepository.access$decodeProfiles(r1, r5)
                        r1 = 0
                        r0.L$0 = r1
                        r0.L$1 = r1
                        r0.L$2 = r1
                        r0.L$3 = r1
                        r1 = 0
                        r0.I$0 = r1
                        r0.label = r2
                        java.lang.Object r5 = r6.emit(r5, r0)
                        e7.a r6 = e7.a.f15033i
                        if (r5 != r6) goto L67
                        return r6
                    L67:
                        x6.t0 r5 = x6.t0.f22605a
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(na.k kVar, d7.d dVar) {
                Object objCollect = data.collect(new AnonymousClass2(kVar, this), dVar);
                return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
            }
        };
        final na.j data2 = DataStoresKt.getProfilesDataStore(context).getData();
        this.activeProfileId = new na.j<String>() { // from class: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2

            /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
            public static final class AnonymousClass2<T> implements na.k {
                final /* synthetic */ na.k $this_unsafeFlow;

                /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2$2", f = "ProfileRepository.kt", l = {223}, m = "emit", v = 2)
                public static final class AnonymousClass1 extends f7.c {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d7.d dVar) {
                        super(dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(na.k kVar) {
                    this.$this_unsafeFlow = kVar;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // na.k
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, d7.d r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2$2$1 r0 = (com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2$2$1 r0 = new com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        int r1 = r0.label
                        r2 = 1
                        if (r1 == 0) goto L35
                        if (r1 != r2) goto L2d
                        java.lang.Object r5 = r0.L$3
                        na.k r5 = (na.k) r5
                        java.lang.Object r5 = r0.L$1
                        com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2$2$1 r5 = (com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r5
                        k2.c.G(r6)
                        goto L5f
                    L2d:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L35:
                        k2.c.G(r6)
                        na.k r6 = r4.$this_unsafeFlow
                        c1.g r5 = (c1.g) r5
                        c1.e r1 = com.arflix.tv.data.repository.ProfileRepository.access$getACTIVE_PROFILE_KEY$cp()
                        c1.b r5 = (c1.b) r5
                        java.util.LinkedHashMap r5 = r5.f7368a
                        java.lang.Object r5 = r5.get(r1)
                        r1 = 0
                        r0.L$0 = r1
                        r0.L$1 = r1
                        r0.L$2 = r1
                        r0.L$3 = r1
                        r1 = 0
                        r0.I$0 = r1
                        r0.label = r2
                        java.lang.Object r5 = r6.emit(r5, r0)
                        e7.a r6 = e7.a.f15033i
                        if (r5 != r6) goto L5f
                        return r6
                    L5f:
                        x6.t0 r5 = x6.t0.f22605a
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(na.k kVar, d7.d dVar) {
                Object objCollect = data2.collect(new AnonymousClass2(kVar), dVar);
                return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
            }
        };
        final na.j data3 = DataStoresKt.getProfilesDataStore(context).getData();
        this.activeProfile = new na.j<Profile>() { // from class: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3

            /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
            public static final class AnonymousClass2<T> implements na.k {
                final /* synthetic */ na.k $this_unsafeFlow;
                final /* synthetic */ ProfileRepository this$0;

                /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                @f7.e(c = "com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3$2", f = "ProfileRepository.kt", l = {223}, m = "emit", v = 2)
                public static final class AnonymousClass1 extends f7.c {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d7.d dVar) {
                        super(dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(na.k kVar, ProfileRepository profileRepository) {
                    this.$this_unsafeFlow = kVar;
                    this.this$0 = profileRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:24:0x0091 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // na.k
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r7, d7.d r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3$2$1 r0 = (com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3$2$1 r0 = new com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        int r1 = r0.label
                        r2 = 1
                        if (r1 == 0) goto L35
                        if (r1 != r2) goto L2d
                        java.lang.Object r7 = r0.L$3
                        na.k r7 = (na.k) r7
                        java.lang.Object r7 = r0.L$1
                        com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3$2$1 r7 = (com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r7
                        k2.c.G(r8)
                        goto L92
                    L2d:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L35:
                        k2.c.G(r8)
                        na.k r8 = r6.$this_unsafeFlow
                        c1.g r7 = (c1.g) r7
                        c1.e r1 = com.arflix.tv.data.repository.ProfileRepository.access$getACTIVE_PROFILE_KEY$cp()
                        c1.b r7 = (c1.b) r7
                        java.util.LinkedHashMap r3 = r7.f7368a
                        java.lang.Object r1 = r3.get(r1)
                        java.lang.String r1 = (java.lang.String) r1
                        r3 = 0
                        if (r1 != 0) goto L4f
                    L4d:
                        r4 = r3
                        goto L7c
                    L4f:
                        com.arflix.tv.data.repository.ProfileRepository r4 = r6.this$0
                        c1.e r5 = com.arflix.tv.data.repository.ProfileRepository.access$getPROFILES_KEY$cp()
                        java.util.LinkedHashMap r7 = r7.f7368a
                        java.lang.Object r7 = r7.get(r5)
                        java.lang.String r7 = (java.lang.String) r7
                        java.util.List r7 = com.arflix.tv.data.repository.ProfileRepository.access$decodeProfiles(r4, r7)
                        java.util.Iterator r7 = r7.iterator()
                    L65:
                        boolean r4 = r7.hasNext()
                        if (r4 == 0) goto L4d
                        java.lang.Object r4 = r7.next()
                        r5 = r4
                        com.arflix.tv.data.model.Profile r5 = (com.arflix.tv.data.model.Profile) r5
                        java.lang.String r5 = r5.getId()
                        boolean r5 = kotlin.jvm.internal.p.a(r5, r1)
                        if (r5 == 0) goto L65
                    L7c:
                        r0.L$0 = r3
                        r0.L$1 = r3
                        r0.L$2 = r3
                        r0.L$3 = r3
                        r7 = 0
                        r0.I$0 = r7
                        r0.label = r2
                        java.lang.Object r7 = r8.emit(r4, r0)
                        e7.a r8 = e7.a.f15033i
                        if (r7 != r8) goto L92
                        return r8
                    L92:
                        x6.t0 r7 = x6.t0.f22605a
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(na.k kVar, d7.d dVar) {
                Object objCollect = data3.collect(new AnonymousClass2(kVar, this), dVar);
                return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
            }
        };
    }

    public static /* synthetic */ Object createProfile$default(ProfileRepository profileRepository, String str, long j10, int i10, boolean z, d7.d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            i10 = 0;
        }
        if ((i11 & 8) != 0) {
            z = false;
        }
        return profileRepository.createProfile(str, j10, i10, z, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Profile> decodeProfiles(String json) {
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        if (json != null && !kotlin.text.o.h0(json)) {
            try {
                List<Profile> list = (List) this.gson.fromJson(json, this.profileListType);
                return list == null ? zVar : list;
            } catch (Exception e5) {
                AppLogger.INSTANCE.e("ProfileRepository", "Error getting profiles from network", e5);
            }
        }
        return zVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String encodeProfiles(List<Profile> profiles) {
        return this.gson.toJson(profiles, this.profileListType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object pushProfilesStateToCloud(d7.d<? super x6.t0> r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof com.arflix.tv.data.repository.ProfileRepository.C09531
            if (r0 == 0) goto L13
            r0 = r10
            com.arflix.tv.data.repository.ProfileRepository$pushProfilesStateToCloud$1 r0 = (com.arflix.tv.data.repository.ProfileRepository.C09531) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileRepository$pushProfilesStateToCloud$1 r0 = new com.arflix.tv.data.repository.ProfileRepository$pushProfilesStateToCloud$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            e7.a r7 = e7.a.f15033i
            if (r1 == 0) goto L63
            if (r1 == r6) goto L5f
            if (r1 == r5) goto L57
            if (r1 == r4) goto L4b
            if (r1 != r3) goto L43
            java.lang.Object r1 = r0.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r1 = r0.L$1
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r10)
            x6.d0 r10 = (x6.d0) r10
            r10.getClass()
            return r2
        L43:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L4b:
            java.lang.Object r1 = r0.L$1
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r4 = r0.L$0
            java.lang.String r4 = (java.lang.String) r4
            k2.c.G(r10)
            goto L97
        L57:
            java.lang.Object r1 = r0.L$0
            java.lang.String r1 = (java.lang.String) r1
            k2.c.G(r10)
            goto L84
        L5f:
            k2.c.G(r10)
            goto L71
        L63:
            k2.c.G(r10)
            com.arflix.tv.data.repository.AuthRepository r10 = r9.authRepository
            r0.label = r6
            java.lang.Object r10 = r10.getCurrentUserIdForSync(r0)
            if (r10 != r7) goto L71
            goto Laf
        L71:
            java.lang.String r10 = (java.lang.String) r10
            if (r10 != 0) goto L76
            goto Lb0
        L76:
            r0.L$0 = r10
            r0.label = r5
            java.lang.Object r1 = r9.getProfiles(r0)
            if (r1 != r7) goto L81
            goto Laf
        L81:
            r8 = r1
            r1 = r10
            r10 = r8
        L84:
            java.util.List r10 = (java.util.List) r10
            r0.L$0 = r1
            r0.L$1 = r10
            r0.label = r4
            java.lang.Object r4 = r9.getActiveProfileId(r0)
            if (r4 != r7) goto L93
            goto Laf
        L93:
            r8 = r1
            r1 = r10
            r10 = r4
            r4 = r8
        L97:
            java.lang.String r10 = (java.lang.String) r10
            com.arflix.tv.data.repository.AuthRepository r5 = r9.authRepository
            com.arflix.tv.data.repository.b0 r6 = new com.arflix.tv.data.repository.b0
            r6.<init>(r10, r9, r1, r4)
            r10 = 0
            r0.L$0 = r10
            r0.L$1 = r10
            r0.L$2 = r10
            r0.label = r3
            java.lang.Object r10 = r5.m6100mutateAccountSyncPayloadgIAlus(r6, r0)
            if (r10 != r7) goto Lb0
        Laf:
            return r7
        Lb0:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository.pushProfilesStateToCloud(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 pushProfilesStateToCloud$lambda$0(String str, ProfileRepository profileRepository, List list, String str2, JSONObject jSONObject) throws JSONException {
        Object obj = str;
        if (str == null) {
            obj = JSONObject.NULL;
        }
        jSONObject.put("activeProfileId", obj);
        jSONObject.put("profiles", new JSONArray(profileRepository.gson.toJson(list)));
        jSONObject.put("profileAvatarImagesById", profileRepository.profileAvatarImageManager.buildInlineAvatarImagesJson(list, jSONObject.optJSONObject("profileAvatarImagesById")));
        jSONObject.put("userId", str2);
        return t0.f22605a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0060, code lost:
    
        if (pushProfilesStateToCloud(r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object clearActiveProfile(d7.d<? super x6.t0> r12) {
        /*
            r11 = this;
            boolean r0 = r12 instanceof com.arflix.tv.data.repository.ProfileRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r12
            com.arflix.tv.data.repository.ProfileRepository$clearActiveProfile$1 r0 = (com.arflix.tv.data.repository.ProfileRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileRepository$clearActiveProfile$1 r0 = new com.arflix.tv.data.repository.ProfileRepository$clearActiveProfile$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L36
            if (r1 == r3) goto L32
            if (r1 != r2) goto L2a
            k2.c.G(r12)
            goto L63
        L2a:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L32:
            k2.c.G(r12)
            goto L4e
        L36:
            k2.c.G(r12)
            android.content.Context r12 = r11.context
            z0.g r12 = com.arflix.tv.util.DataStoresKt.getProfilesDataStore(r12)
            com.arflix.tv.data.repository.ProfileRepository$clearActiveProfile$2 r1 = new com.arflix.tv.data.repository.ProfileRepository$clearActiveProfile$2
            r5 = 0
            r1.<init>(r5)
            r0.label = r3
            java.lang.Object r12 = androidx.work.impl.t.o(r12, r1, r0)
            if (r12 != r4) goto L4e
            goto L62
        L4e:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r5 = r11.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r6 = com.arflix.tv.data.repository.CloudSyncScope.PROFILES
            r9 = 2
            r10 = 0
            r7 = 0
            java.lang.String r8 = "clear active profile"
            com.arflix.tv.data.repository.CloudSyncInvalidationBus.markDirty$default(r5, r6, r7, r8, r9, r10)
            r0.label = r2
            java.lang.Object r12 = r11.pushProfilesStateToCloud(r0)
            if (r12 != r4) goto L63
        L62:
            return r4
        L63:
            x6.t0 r12 = x6.t0.f22605a
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository.clearActiveProfile(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object createDefaultProfileIfNeeded(d7.d<? super com.arflix.tv.data.model.Profile> r12) {
        /*
            r11 = this;
            boolean r0 = r12 instanceof com.arflix.tv.data.repository.ProfileRepository.C09471
            if (r0 == 0) goto L14
            r0 = r12
            com.arflix.tv.data.repository.ProfileRepository$createDefaultProfileIfNeeded$1 r0 = (com.arflix.tv.data.repository.ProfileRepository.C09471) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r7 = r0
            goto L1a
        L14:
            com.arflix.tv.data.repository.ProfileRepository$createDefaultProfileIfNeeded$1 r0 = new com.arflix.tv.data.repository.ProfileRepository$createDefaultProfileIfNeeded$1
            r0.<init>(r12)
            goto L12
        L1a:
            java.lang.Object r12 = r7.result
            int r0 = r7.label
            r1 = 2
            r2 = 1
            e7.a r10 = e7.a.f15033i
            if (r0 == 0) goto L38
            if (r0 == r2) goto L34
            if (r0 != r1) goto L2c
            k2.c.G(r12)
            return r12
        L2c:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L34:
            k2.c.G(r12)
            goto L44
        L38:
            k2.c.G(r12)
            r7.label = r2
            java.lang.Object r12 = r11.hasProfiles(r7)
            if (r12 != r10) goto L44
            goto L6f
        L44:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L4e
            r12 = 0
            return r12
        L4e:
            com.arflix.tv.data.model.ProfileColors r12 = com.arflix.tv.data.model.ProfileColors.INSTANCE
            java.util.List r12 = r12.getColors()
            r0 = 0
            java.lang.Object r12 = r12.get(r0)
            java.lang.Number r12 = (java.lang.Number) r12
            long r3 = r12.longValue()
            r7.label = r1
            java.lang.String r2 = "Profile 1"
            r5 = 0
            r6 = 0
            r8 = 12
            r9 = 0
            r1 = r11
            java.lang.Object r12 = createProfile$default(r1, r2, r3, r5, r6, r7, r8, r9)
            if (r12 != r10) goto L70
        L6f:
            return r10
        L70:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository.createDefaultProfileIfNeeded(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object createProfile(java.lang.String r31, long r32, int r34, boolean r35, d7.d<? super com.arflix.tv.data.model.Profile> r36) {
        /*
            r30 = this;
            r0 = r30
            r1 = r36
            boolean r2 = r1 instanceof com.arflix.tv.data.repository.ProfileRepository.C09481
            if (r2 == 0) goto L17
            r2 = r1
            com.arflix.tv.data.repository.ProfileRepository$createProfile$1 r2 = (com.arflix.tv.data.repository.ProfileRepository.C09481) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            com.arflix.tv.data.repository.ProfileRepository$createProfile$1 r2 = new com.arflix.tv.data.repository.ProfileRepository$createProfile$1
            r2.<init>(r1)
        L1c:
            java.lang.Object r1 = r2.result
            int r3 = r2.label
            r4 = 2
            r5 = 1
            r6 = 0
            e7.a r7 = e7.a.f15033i
            if (r3 == 0) goto L54
            if (r3 == r5) goto L3f
            if (r3 != r4) goto L37
            java.lang.Object r3 = r2.L$1
            com.arflix.tv.data.model.Profile r3 = (com.arflix.tv.data.model.Profile) r3
            java.lang.Object r2 = r2.L$0
            java.lang.String r2 = (java.lang.String) r2
            k2.c.G(r1)
            return r3
        L37:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L3f:
            boolean r3 = r2.Z$0
            int r5 = r2.I$0
            long r8 = r2.J$0
            java.lang.Object r10 = r2.L$1
            com.arflix.tv.data.model.Profile r10 = (com.arflix.tv.data.model.Profile) r10
            java.lang.Object r11 = r2.L$0
            java.lang.String r11 = (java.lang.String) r11
            k2.c.G(r1)
            r11 = r3
            r12 = r10
            r10 = r5
            goto L99
        L54:
            k2.c.G(r1)
            com.arflix.tv.data.model.Profile r12 = new com.arflix.tv.data.model.Profile
            r28 = 1969(0x7b1, float:2.759E-42)
            r29 = 0
            r13 = 0
            r18 = 0
            r20 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r26 = 0
            r14 = r31
            r15 = r32
            r17 = r34
            r21 = r35
            r12.<init>(r13, r14, r15, r17, r18, r20, r21, r22, r23, r24, r26, r28, r29)
            android.content.Context r1 = r0.context
            z0.g r1 = com.arflix.tv.util.DataStoresKt.getProfilesDataStore(r1)
            com.arflix.tv.data.repository.ProfileRepository$createProfile$2 r3 = new com.arflix.tv.data.repository.ProfileRepository$createProfile$2
            r3.<init>(r12, r6)
            r2.L$0 = r6
            r2.L$1 = r12
            r8 = r32
            r2.J$0 = r8
            r10 = r34
            r2.I$0 = r10
            r11 = r35
            r2.Z$0 = r11
            r2.label = r5
            java.lang.Object r1 = androidx.work.impl.t.o(r1, r3, r2)
            if (r1 != r7) goto L99
            goto Lb8
        L99:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r1 = r0.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r3 = com.arflix.tv.data.repository.CloudSyncScope.PROFILES
            java.lang.String r5 = r12.getId()
            java.lang.String r13 = "create profile"
            r1.markDirty(r3, r5, r13)
            r2.L$0 = r6
            r2.L$1 = r12
            r2.J$0 = r8
            r2.I$0 = r10
            r2.Z$0 = r11
            r2.label = r4
            java.lang.Object r1 = r0.pushProfilesStateToCloud(r2)
            if (r1 != r7) goto Lb9
        Lb8:
            return r7
        Lb9:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository.createProfile(java.lang.String, long, int, boolean, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006e, code lost:
    
        if (pushProfilesStateToCloud(r0) == r5) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object deleteProfile(java.lang.String r7, d7.d<? super x6.t0> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.ProfileRepository.C09501
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.ProfileRepository$deleteProfile$1 r0 = (com.arflix.tv.data.repository.ProfileRepository.C09501) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileRepository$deleteProfile$1 r0 = new com.arflix.tv.data.repository.ProfileRepository$deleteProfile$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L3f
            if (r1 == r4) goto L37
            if (r1 != r3) goto L2f
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r8)
            goto L71
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L37:
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r8)
            goto L58
        L3f:
            k2.c.G(r8)
            android.content.Context r8 = r6.context
            z0.g r8 = com.arflix.tv.util.DataStoresKt.getProfilesDataStore(r8)
            com.arflix.tv.data.repository.ProfileRepository$deleteProfile$2 r1 = new com.arflix.tv.data.repository.ProfileRepository$deleteProfile$2
            r1.<init>(r7, r2)
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r8 = androidx.work.impl.t.o(r8, r1, r0)
            if (r8 != r5) goto L58
            goto L70
        L58:
            com.arflix.tv.data.repository.ProfileAvatarImageManager r8 = r6.profileAvatarImageManager
            r8.clearLocalAvatar(r7)
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r8 = r6.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r1 = com.arflix.tv.data.repository.CloudSyncScope.PROFILES
            java.lang.String r4 = "delete profile"
            r8.markDirty(r1, r7, r4)
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r7 = r6.pushProfilesStateToCloud(r0)
            if (r7 != r5) goto L71
        L70:
            return r5
        L71:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository.deleteProfile(java.lang.String, d7.d):java.lang.Object");
    }

    public final na.j<Profile> getActiveProfile() {
        return this.activeProfile;
    }

    public final na.j<String> getActiveProfileId() {
        return this.activeProfileId;
    }

    public final na.j<List<Profile>> getProfiles() {
        return this.profiles;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object hasProfiles(d7.d<? super java.lang.Boolean> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.ProfileRepository.C09521
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.ProfileRepository$hasProfiles$1 r0 = (com.arflix.tv.data.repository.ProfileRepository.C09521) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileRepository$hasProfiles$1 r0 = new com.arflix.tv.data.repository.ProfileRepository$hasProfiles$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.getProfiles(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3b
            return r0
        L3b:
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            r5 = r5 ^ r2
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository.hasProfiles(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object replaceProfilesFromCloud(java.util.List<com.arflix.tv.data.model.Profile> r33, java.lang.String r34, d7.d<? super x6.t0> r35) {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository.replaceProfilesFromCloud(java.util.List, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0069, code lost:
    
        if (pushProfilesStateToCloud(r0) == r5) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setActiveProfile(java.lang.String r7, d7.d<? super x6.t0> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.ProfileRepository.C09561
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.ProfileRepository$setActiveProfile$1 r0 = (com.arflix.tv.data.repository.ProfileRepository.C09561) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileRepository$setActiveProfile$1 r0 = new com.arflix.tv.data.repository.ProfileRepository$setActiveProfile$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L3f
            if (r1 == r4) goto L37
            if (r1 != r3) goto L2f
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r8)
            goto L6c
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L37:
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r8)
            goto L58
        L3f:
            k2.c.G(r8)
            android.content.Context r8 = r6.context
            z0.g r8 = com.arflix.tv.util.DataStoresKt.getProfilesDataStore(r8)
            com.arflix.tv.data.repository.ProfileRepository$setActiveProfile$2 r1 = new com.arflix.tv.data.repository.ProfileRepository$setActiveProfile$2
            r1.<init>(r7, r6, r2)
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r8 = androidx.work.impl.t.o(r8, r1, r0)
            if (r8 != r5) goto L58
            goto L6b
        L58:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r8 = r6.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r1 = com.arflix.tv.data.repository.CloudSyncScope.PROFILES
            java.lang.String r4 = "set active profile"
            r8.markDirty(r1, r7, r4)
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r7 = r6.pushProfilesStateToCloud(r0)
            if (r7 != r5) goto L6c
        L6b:
            return r5
        L6c:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository.setActiveProfile(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x006e, code lost:
    
        if (pushProfilesStateToCloud(r0) == r5) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object updateProfile(com.arflix.tv.data.model.Profile r7, d7.d<? super x6.t0> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.ProfileRepository.C09581
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.ProfileRepository$updateProfile$1 r0 = (com.arflix.tv.data.repository.ProfileRepository.C09581) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileRepository$updateProfile$1 r0 = new com.arflix.tv.data.repository.ProfileRepository$updateProfile$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 0
            r3 = 2
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L3f
            if (r1 == r4) goto L37
            if (r1 != r3) goto L2f
            java.lang.Object r7 = r0.L$0
            com.arflix.tv.data.model.Profile r7 = (com.arflix.tv.data.model.Profile) r7
            k2.c.G(r8)
            goto L71
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L37:
            java.lang.Object r7 = r0.L$0
            com.arflix.tv.data.model.Profile r7 = (com.arflix.tv.data.model.Profile) r7
            k2.c.G(r8)
            goto L58
        L3f:
            k2.c.G(r8)
            android.content.Context r8 = r6.context
            z0.g r8 = com.arflix.tv.util.DataStoresKt.getProfilesDataStore(r8)
            com.arflix.tv.data.repository.ProfileRepository$updateProfile$2 r1 = new com.arflix.tv.data.repository.ProfileRepository$updateProfile$2
            r1.<init>(r7, r2)
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r8 = androidx.work.impl.t.o(r8, r1, r0)
            if (r8 != r5) goto L58
            goto L70
        L58:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r8 = r6.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r1 = com.arflix.tv.data.repository.CloudSyncScope.PROFILES
            java.lang.String r7 = r7.getId()
            java.lang.String r4 = "update profile"
            r8.markDirty(r1, r7, r4)
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r7 = r6.pushProfilesStateToCloud(r0)
            if (r7 != r5) goto L71
        L70:
            return r5
        L71:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileRepository.updateProfile(com.arflix.tv.data.model.Profile, d7.d):java.lang.Object");
    }

    public final Object getActiveProfile(d7.d<? super Profile> dVar) {
        return y0.l(this.activeProfile, dVar);
    }

    public final Object getActiveProfileId(d7.d<? super String> dVar) {
        return y0.l(this.activeProfileId, dVar);
    }

    public final Object getProfiles(d7.d<? super List<Profile>> dVar) {
        return y0.l(this.profiles, dVar);
    }
}
