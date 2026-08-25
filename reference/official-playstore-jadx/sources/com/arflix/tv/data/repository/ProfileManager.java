package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.media3.container.MdtaMetadataEntry;
import com.arflix.tv.data.model.Profile;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.ktor.http.ContentDisposition;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\bH\u0086@¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rH\u0086@¢\u0006\u0004\b\u000e\u0010\fJ\u0015\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\b¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\b¢\u0006\u0004\b\u0013\u0010\u0011J\u001b\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\u0006\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u0016\u0010\u0017J#\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u0018\u0010\u0019J\u001b\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00152\u0006\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u001b\u0010\u0017J#\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00152\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u001c\u0010\u0019J\u001b\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00152\u0006\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u001e\u0010\u0017J#\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00152\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u001f\u0010\u0019J\r\u0010 \u001a\u00020\b¢\u0006\u0004\b \u0010\nJ\r\u0010!\u001a\u00020\b¢\u0006\u0004\b!\u0010\nJ\r\u0010\"\u001a\u00020\u001d¢\u0006\u0004\b\"\u0010#R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010$R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010%R\u0014\u0010&\u001a\u00020\b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b&\u0010'R\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020\b0(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u001d\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0+8\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020\b0(8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010*R\u001d\u00101\u001a\b\u0012\u0004\u0012\u00020\b0+8\u0006¢\u0006\f\n\u0004\b1\u0010-\u001a\u0004\b2\u0010/R\u001d\u00104\u001a\b\u0012\u0004\u0012\u00020\b038\u0006¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u001d\u00108\u001a\b\u0012\u0004\u0012\u00020\b038\u0006¢\u0006\f\n\u0004\b8\u00105\u001a\u0004\b9\u00107¨\u0006:"}, d2 = {"Lcom/arflix/tv/data/repository/ProfileManager;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/ProfileRepository;", "profileRepository", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/ProfileRepository;)V", "", "getProfileIdSync", "()Ljava/lang/String;", "getProfileId", "(Ld7/d;)Ljava/lang/Object;", "Lx6/t0;", "initialize", "profileId", "setCurrentProfileId", "(Ljava/lang/String;)V", "profileName", "setCurrentProfileName", ContentDisposition.Parameters.Name, "Lc1/e;", "profileStringKey", "(Ljava/lang/String;)Lc1/e;", "profileStringKeyFor", "(Ljava/lang/String;Ljava/lang/String;)Lc1/e;", "", "profileLongKey", "profileLongKeyFor", "", "profileBooleanKey", "profileBooleanKeyFor", "getKeyPrefix", "getProfileNameSync", "isDefaultProfile", "()Z", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/ProfileRepository;", "DEFAULT_PROFILE_ID", "Ljava/lang/String;", "Lna/q0;", "_currentProfileId", "Lna/q0;", "Lna/h1;", "currentProfileId", "Lna/h1;", "getCurrentProfileId", "()Lna/h1;", "_currentProfileName", "currentProfileName", "getCurrentProfileName", "Lna/j;", "activeProfileId", "Lna/j;", "getActiveProfileId", "()Lna/j;", "activeProfileName", "getActiveProfileName", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ProfileManager {
    public static final int $stable = 8;
    private final String DEFAULT_PROFILE_ID = "default";
    private final q0<String> _currentProfileId;
    private final q0<String> _currentProfileName;
    private final na.j<String> activeProfileId;
    private final na.j<String> activeProfileName;
    private final Context context;
    private final h1<String> currentProfileId;
    private final h1<String> currentProfileName;
    private final ProfileRepository profileRepository;

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileManager$getProfileId$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileManager", f = "ProfileManager.kt", l = {MdtaMetadataEntry.TYPE_INDICATOR_INT32}, m = "getProfileId", v = 2)
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
            return ProfileManager.this.getProfileId(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileManager$initialize$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileManager", f = "ProfileManager.kt", l = {76}, m = "initialize", v = 2)
    public static final class C09461 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C09461(d7.d<? super C09461> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileManager.this.initialize(this);
        }
    }

    @Inject
    public ProfileManager(@ApplicationContext Context context, ProfileRepository profileRepository) {
        this.context = context;
        this.profileRepository = profileRepository;
        j1 j1VarB = y0.b("default");
        this._currentProfileId = j1VarB;
        this.currentProfileId = y0.e(j1VarB);
        j1 j1VarB2 = y0.b("default");
        this._currentProfileName = j1VarB2;
        this.currentProfileName = y0.e(j1VarB2);
        final na.j<String> activeProfileId = profileRepository.getActiveProfileId();
        this.activeProfileId = new na.j<String>() { // from class: com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1

            /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
            public static final class AnonymousClass2<T> implements na.k {
                final /* synthetic */ na.k $this_unsafeFlow;
                final /* synthetic */ ProfileManager this$0;

                /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                @f7.e(c = "com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1$2", f = "ProfileManager.kt", l = {223}, m = "emit", v = 2)
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

                public AnonymousClass2(na.k kVar, ProfileManager profileManager) {
                    this.$this_unsafeFlow = kVar;
                    this.this$0 = profileManager;
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
                        boolean r0 = r6 instanceof com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1$2$1 r0 = (com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1$2$1 r0 = new com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1$2$1
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
                        com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1$2$1 r5 = (com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r5
                        k2.c.G(r6)
                        goto L64
                    L2d:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L35:
                        k2.c.G(r6)
                        na.k r6 = r4.$this_unsafeFlow
                        java.lang.String r5 = (java.lang.String) r5
                        if (r5 != 0) goto L44
                        com.arflix.tv.data.repository.ProfileManager r5 = r4.this$0
                        java.lang.String r5 = com.arflix.tv.data.repository.ProfileManager.access$getDEFAULT_PROFILE_ID$p(r5)
                    L44:
                        com.arflix.tv.data.repository.ProfileManager r1 = r4.this$0
                        na.q0 r1 = com.arflix.tv.data.repository.ProfileManager.access$get_currentProfileId$p(r1)
                        r1.setValue(r5)
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
                        if (r5 != r6) goto L64
                        return r6
                    L64:
                        x6.t0 r5 = x6.t0.f22605a
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(na.k kVar, d7.d dVar) {
                Object objCollect = activeProfileId.collect(new AnonymousClass2(kVar, this), dVar);
                return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
            }
        };
        final na.j<Profile> activeProfile = profileRepository.getActiveProfile();
        this.activeProfileName = new na.j<String>() { // from class: com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2

            /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
            public static final class AnonymousClass2<T> implements na.k {
                final /* synthetic */ na.k $this_unsafeFlow;
                final /* synthetic */ ProfileManager this$0;

                /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                @f7.e(c = "com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2$2", f = "ProfileManager.kt", l = {223}, m = "emit", v = 2)
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

                public AnonymousClass2(na.k kVar, ProfileManager profileManager) {
                    this.$this_unsafeFlow = kVar;
                    this.this$0 = profileManager;
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
                        boolean r0 = r6 instanceof com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2$2$1 r0 = (com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2$2$1 r0 = new com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2$2$1
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
                        com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2$2$1 r5 = (com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r5
                        k2.c.G(r6)
                        goto L85
                    L2d:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L35:
                        k2.c.G(r6)
                        na.k r6 = r4.$this_unsafeFlow
                        com.arflix.tv.data.model.Profile r5 = (com.arflix.tv.data.model.Profile) r5
                        r1 = 0
                        if (r5 == 0) goto L54
                        java.lang.String r5 = r5.getName()
                        if (r5 == 0) goto L54
                        java.util.Locale r3 = java.util.Locale.ROOT
                        java.lang.String r5 = r5.toLowerCase(r3)
                        java.lang.CharSequence r5 = kotlin.text.o.L0(r5)
                        java.lang.String r5 = r5.toString()
                        goto L55
                    L54:
                        r5 = r1
                    L55:
                        if (r5 == 0) goto L5d
                        boolean r3 = kotlin.text.o.h0(r5)
                        if (r3 == 0) goto L5e
                    L5d:
                        r5 = r1
                    L5e:
                        if (r5 != 0) goto L66
                        com.arflix.tv.data.repository.ProfileManager r5 = r4.this$0
                        java.lang.String r5 = com.arflix.tv.data.repository.ProfileManager.access$getDEFAULT_PROFILE_ID$p(r5)
                    L66:
                        com.arflix.tv.data.repository.ProfileManager r3 = r4.this$0
                        na.q0 r3 = com.arflix.tv.data.repository.ProfileManager.access$get_currentProfileName$p(r3)
                        r3.setValue(r5)
                        r0.L$0 = r1
                        r0.L$1 = r1
                        r0.L$2 = r1
                        r0.L$3 = r1
                        r1 = 0
                        r0.I$0 = r1
                        r0.label = r2
                        java.lang.Object r5 = r6.emit(r5, r0)
                        e7.a r6 = e7.a.f15033i
                        if (r5 != r6) goto L85
                        return r6
                    L85:
                        x6.t0 r5 = x6.t0.f22605a
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileManager$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(na.k kVar, d7.d dVar) {
                Object objCollect = activeProfile.collect(new AnonymousClass2(kVar, this), dVar);
                return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
            }
        };
    }

    public final na.j<String> getActiveProfileId() {
        return this.activeProfileId;
    }

    public final na.j<String> getActiveProfileName() {
        return this.activeProfileName;
    }

    public final h1<String> getCurrentProfileId() {
        return this.currentProfileId;
    }

    public final h1<String> getCurrentProfileName() {
        return this.currentProfileName;
    }

    public final String getKeyPrefix() {
        return a0.c.l("profile_", getProfileIdSync(), "_");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getProfileId(d7.d<? super java.lang.String> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.ProfileManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.ProfileManager$getProfileId$1 r0 = (com.arflix.tv.data.repository.ProfileManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileManager$getProfileId$1 r0 = new com.arflix.tv.data.repository.ProfileManager$getProfileId$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3d
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            com.arflix.tv.data.repository.ProfileRepository r5 = r4.profileRepository
            r0.label = r2
            java.lang.Object r5 = r5.getActiveProfileId(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3d
            return r0
        L3d:
            java.lang.String r5 = (java.lang.String) r5
            if (r5 != 0) goto L43
            java.lang.String r5 = r4.DEFAULT_PROFILE_ID
        L43:
            na.q0<java.lang.String> r0 = r4._currentProfileId
            r0.setValue(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileManager.getProfileId(d7.d):java.lang.Object");
    }

    public final String getProfileIdSync() {
        return (String) this._currentProfileId.getValue();
    }

    public final String getProfileNameSync() {
        Object value = this._currentProfileName.getValue();
        if (kotlin.text.o.h0((String) value)) {
            value = null;
        }
        String str = (String) value;
        return str == null ? getProfileIdSync() : str;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object initialize(d7.d<? super x6.t0> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.ProfileManager.C09461
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.ProfileManager$initialize$1 r0 = (com.arflix.tv.data.repository.ProfileManager.C09461) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileManager$initialize$1 r0 = new com.arflix.tv.data.repository.ProfileManager$initialize$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3d
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            com.arflix.tv.data.repository.ProfileRepository r5 = r4.profileRepository
            r0.label = r2
            java.lang.Object r5 = r5.getActiveProfile(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3d
            return r0
        L3d:
            com.arflix.tv.data.model.Profile r5 = (com.arflix.tv.data.model.Profile) r5
            if (r5 == 0) goto L47
            java.lang.String r0 = r5.getId()
            if (r0 != 0) goto L49
        L47:
            java.lang.String r0 = r4.DEFAULT_PROFILE_ID
        L49:
            na.q0<java.lang.String> r1 = r4._currentProfileId
            r1.setValue(r0)
            na.q0<java.lang.String> r1 = r4._currentProfileName
            r2 = 0
            if (r5 == 0) goto L68
            java.lang.String r5 = r5.getName()
            if (r5 == 0) goto L68
            java.util.Locale r3 = java.util.Locale.ROOT
            java.lang.String r5 = r5.toLowerCase(r3)
            java.lang.CharSequence r5 = kotlin.text.o.L0(r5)
            java.lang.String r5 = r5.toString()
            goto L69
        L68:
            r5 = r2
        L69:
            if (r5 == 0) goto L73
            boolean r3 = kotlin.text.o.h0(r5)
            if (r3 == 0) goto L72
            goto L73
        L72:
            r2 = r5
        L73:
            if (r2 != 0) goto L76
            goto L77
        L76:
            r0 = r2
        L77:
            r1.setValue(r0)
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileManager.initialize(d7.d):java.lang.Object");
    }

    public final boolean isDefaultProfile() {
        return kotlin.jvm.internal.p.a(getProfileIdSync(), this.DEFAULT_PROFILE_ID);
    }

    public final c1.e profileBooleanKey(String name) {
        return new c1.e(androidx.compose.material3.d.n("profile_", getProfileIdSync(), "_", name));
    }

    public final c1.e profileBooleanKeyFor(String profileId, String name) {
        return new c1.e(androidx.compose.material3.d.n("profile_", profileId, "_", name));
    }

    public final c1.e profileLongKey(String name) {
        return new c1.e(androidx.compose.material3.d.n("profile_", getProfileIdSync(), "_", name));
    }

    public final c1.e profileLongKeyFor(String profileId, String name) {
        return new c1.e(androidx.compose.material3.d.n("profile_", profileId, "_", name));
    }

    public final c1.e profileStringKey(String name) {
        return new c1.e(androidx.compose.material3.d.n("profile_", getProfileIdSync(), "_", name));
    }

    public final c1.e profileStringKeyFor(String profileId, String name) {
        return new c1.e(androidx.compose.material3.d.n("profile_", profileId, "_", name));
    }

    public final void setCurrentProfileId(String profileId) {
        this._currentProfileId.setValue(profileId);
        if (kotlin.text.o.h0((CharSequence) this._currentProfileName.getValue())) {
            this._currentProfileName.setValue(profileId);
        }
    }

    public final void setCurrentProfileName(String profileName) {
        String string = kotlin.text.o.L0(profileName.toLowerCase(Locale.ROOT)).toString();
        if (kotlin.text.o.h0(string)) {
            return;
        }
        this._currentProfileName.setValue(string);
    }
}
