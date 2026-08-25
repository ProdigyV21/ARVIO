package com.arflix.tv.data.repository;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.R;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.util.ProfileAvatarFiles;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import org.json.JSONException;
import org.json.JSONObject;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 =2\u00020\u0001:\u0001=B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ'\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J.\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u00192\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0082@¢\u0006\u0004\b\u001a\u0010\u001bJ&\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00192\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0017H\u0082@¢\u0006\u0004\b \u0010!J\u001a\u0010#\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0082@¢\u0006\u0004\b#\u0010$J \u0010'\u001a\u00020&2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0013H\u0086@¢\u0006\u0004\b'\u0010(J$\u0010,\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020)2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0013H\u0086@¢\u0006\u0004\b,\u0010-J'\u00102\u001a\u0002002\f\u0010/\u001a\b\u0012\u0004\u0012\u00020)0.2\n\b\u0002\u00101\u001a\u0004\u0018\u000100¢\u0006\u0004\b2\u00103J\u0017\u00104\u001a\u0004\u0018\u00010\u00132\u0006\u0010*\u001a\u00020)¢\u0006\u0004\b4\u00105J\u0015\u00106\u001a\u00020\u001f2\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b6\u00107R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u00108R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u00109R\u0014\u0010;\u001a\u00020:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<¨\u0006>"}, d2 = {"Lcom/arflix/tv/data/repository/ProfileAvatarImageManager;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/AuthRepository;)V", "Landroid/net/Uri;", "uri", "Landroid/graphics/Bitmap;", "decodeSquareAvatar", "(Landroid/net/Uri;)Landroid/graphics/Bitmap;", "", "width", "height", "target", "calculateSampleSize", "(III)I", "", "profileId", "", "version", "Ljava/io/File;", "file", "Lx6/d0;", "uploadAvatar-BWLJW6A", "(Ljava/lang/String;JLjava/io/File;Ld7/d;)Ljava/lang/Object;", "uploadAvatar", "storagePath", "destination", "Lx6/t0;", "downloadAvatar-0E7RQCE", "(Ljava/lang/String;Ljava/io/File;Ld7/d;)Ljava/lang/Object;", "downloadAvatar", "loadInlineAvatarFromCloud", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "uriString", "Lcom/arflix/tv/data/repository/ImportedProfileAvatar;", "importAvatar", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/Profile;", "profile", "inlineBase64", "restoreAvatarIfNeeded", "(Lcom/arflix/tv/data/model/Profile;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "", "profiles", "Lorg/json/JSONObject;", "existingImagesById", "buildInlineAvatarImagesJson", "(Ljava/util/List;Lorg/json/JSONObject;)Lorg/json/JSONObject;", "readInlineBase64", "(Lcom/arflix/tv/data/model/Profile;)Ljava/lang/String;", "clearLocalAvatar", "(Ljava/lang/String;)V", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/AuthRepository;", "Lgb/h0;", "httpClient", "Lgb/h0;", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ProfileAvatarImageManager {

    @Deprecated
    public static final String BUCKET = "profile-avatars";
    private final AuthRepository authRepository;
    private final Context context;
    private final gb.h0 httpClient = new gb.h0();
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/data/repository/ProfileAvatarImageManager$Companion;", "", "<init>", "()V", "BUCKET", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileAvatarImageManager$importAvatar$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/ImportedProfileAvatar;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/ImportedProfileAvatar;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.ProfileAvatarImageManager$importAvatar$2", f = "ProfileAvatarImageManager.kt", l = {52}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super ImportedProfileAvatar>, Object> {
        final /* synthetic */ String $profileId;
        final /* synthetic */ String $uriString;
        long J$0;
        long J$1;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, String str2, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$profileId = str;
            this.$uriString = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return ProfileAvatarImageManager.this.new AnonymousClass2(this.$profileId, this.$uriString, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objM6143uploadAvatarBWLJW6A;
            long j10;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                long jCurrentTimeMillis = System.currentTimeMillis();
                ProfileAvatarFiles profileAvatarFiles = ProfileAvatarFiles.INSTANCE;
                File fileLocalFile = profileAvatarFiles.localFile(ProfileAvatarImageManager.this.context, this.$profileId, jCurrentTimeMillis);
                Bitmap bitmapDecodeSquareAvatar = ProfileAvatarImageManager.this.decodeSquareAvatar(Uri.parse(this.$uriString));
                if (bitmapDecodeSquareAvatar == null) {
                    throw new IllegalArgumentException(ProfileAvatarImageManager.this.context.getString(R.string.avatar_decode_failed));
                }
                FileOutputStream fileOutputStream = new FileOutputStream(fileLocalFile);
                try {
                    bitmapDecodeSquareAvatar.compress(Bitmap.CompressFormat.JPEG, 86, fileOutputStream);
                    fileOutputStream.close();
                    bitmapDecodeSquareAvatar.recycle();
                    profileAvatarFiles.cleanupProfile(ProfileAvatarImageManager.this.context, this.$profileId, new Long(jCurrentTimeMillis));
                    ProfileAvatarImageManager profileAvatarImageManager = ProfileAvatarImageManager.this;
                    String str = this.$profileId;
                    this.L$0 = null;
                    this.J$0 = jCurrentTimeMillis;
                    this.J$1 = jCurrentTimeMillis;
                    this.label = 1;
                    objM6143uploadAvatarBWLJW6A = profileAvatarImageManager.m6143uploadAvatarBWLJW6A(str, jCurrentTimeMillis, fileLocalFile, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objM6143uploadAvatarBWLJW6A == aVar) {
                        return aVar;
                    }
                    j10 = jCurrentTimeMillis;
                } finally {
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                j10 = this.J$1;
                k2.c.G(obj);
                objM6143uploadAvatarBWLJW6A = ((x6.d0) obj).f22580i;
            }
            return new ImportedProfileAvatar(j10, (String) (objM6143uploadAvatarBWLJW6A instanceof x6.c0 ? null : objM6143uploadAvatarBWLJW6A));
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super ImportedProfileAvatar> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileAvatarImageManager$loadInlineAvatarFromCloud$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.ProfileAvatarImageManager", f = "ProfileAvatarImageManager.kt", l = {224}, m = "loadInlineAvatarFromCloud", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ProfileAvatarImageManager.this.loadInlineAvatarFromCloud(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.ProfileAvatarImageManager$restoreAvatarIfNeeded$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.ProfileAvatarImageManager$restoreAvatarIfNeeded$2", f = "ProfileAvatarImageManager.kt", l = {TsExtractor.TS_SYNC_BYTE, 91}, m = "invokeSuspend", v = 2)
    public static final class C09452 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $inlineBase64;
        final /* synthetic */ Profile $profile;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ ProfileAvatarImageManager this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09452(Profile profile, ProfileAvatarImageManager profileAvatarImageManager, String str, d7.d<? super C09452> dVar) {
            super(2, dVar);
            this.$profile = profile;
            this.this$0 = profileAvatarImageManager;
            this.$inlineBase64 = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C09452(this.$profile, this.this$0, this.$inlineBase64, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x0084, code lost:
        
            if (r13 == r5) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0130, code lost:
        
            if (r13 == r5) goto L62;
         */
        /* JADX WARN: Removed duplicated region for block: B:53:0x010d  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0116  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0119  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x0121 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0122  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 340
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileAvatarImageManager.C09452.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C09452) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public ProfileAvatarImageManager(@ApplicationContext Context context, AuthRepository authRepository) {
        this.context = context;
        this.authRepository = authRepository;
    }

    public static /* synthetic */ JSONObject buildInlineAvatarImagesJson$default(ProfileAvatarImageManager profileAvatarImageManager, List list, JSONObject jSONObject, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            jSONObject = null;
        }
        return profileAvatarImageManager.buildInlineAvatarImagesJson(list, jSONObject);
    }

    private final int calculateSampleSize(int width, int height, int target) {
        int i10 = width / 2;
        int i11 = height / 2;
        int i12 = 1;
        while (i10 / i12 >= target && i11 / i12 >= target) {
            i12 *= 2;
        }
        if (i12 < 1) {
            return 1;
        }
        return i12;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap decodeSquareAvatar(Uri uri) throws IOException {
        int i10;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream inputStreamOpenInputStream = this.context.getContentResolver().openInputStream(uri);
        if (inputStreamOpenInputStream != null) {
            try {
                BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options);
                inputStreamOpenInputStream.close();
            } finally {
            }
        }
        int i11 = options.outWidth;
        if (i11 > 0 && (i10 = options.outHeight) > 0) {
            int iCalculateSampleSize = calculateSampleSize(i11, i10, 1024);
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = iCalculateSampleSize;
            options2.inPreferredConfig = Bitmap.Config.ARGB_8888;
            inputStreamOpenInputStream = this.context.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream != null) {
                try {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream, null, options2);
                    inputStreamOpenInputStream.close();
                    if (bitmapDecodeStream != null) {
                        int iMin = Math.min(bitmapDecodeStream.getWidth(), bitmapDecodeStream.getHeight());
                        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeStream, Math.max(0, (bitmapDecodeStream.getWidth() - iMin) / 2), Math.max(0, (bitmapDecodeStream.getHeight() - iMin) / 2), iMin, iMin);
                        if (!bitmapCreateBitmap.equals(bitmapDecodeStream)) {
                            bitmapDecodeStream.recycle();
                        }
                        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateBitmap, 512, 512, true);
                        if (!bitmapCreateScaledBitmap.equals(bitmapCreateBitmap)) {
                            bitmapCreateBitmap.recycle();
                        }
                        return bitmapCreateScaledBitmap;
                    }
                } finally {
                    try {
                        throw th;
                    } finally {
                    }
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: downloadAvatar-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6142downloadAvatar0E7RQCE(java.lang.String r5, java.io.File r6, d7.d<? super x6.d0> r7) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.ProfileAvatarImageManager$downloadAvatar$1
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.ProfileAvatarImageManager$downloadAvatar$1 r0 = (com.arflix.tv.data.repository.ProfileAvatarImageManager$downloadAvatar$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileAvatarImageManager$downloadAvatar$1 r0 = new com.arflix.tv.data.repository.ProfileAvatarImageManager$downloadAvatar$1
            r0.<init>(r4, r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r5 = r0.L$1
            java.io.File r5 = (java.io.File) r5
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r7)
            goto L4f
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            k2.c.G(r7)
            ra.c r7 = ka.x0.f19655d
            com.arflix.tv.data.repository.ProfileAvatarImageManager$downloadAvatar$2 r1 = new com.arflix.tv.data.repository.ProfileAvatarImageManager$downloadAvatar$2
            r3 = 0
            r1.<init>(r4, r5, r6, r3)
            r0.L$0 = r3
            r0.L$1 = r3
            r0.label = r2
            java.lang.Object r7 = ka.m0.y(r7, r1, r0)
            e7.a r5 = e7.a.f15033i
            if (r7 != r5) goto L4f
            return r5
        L4f:
            x6.d0 r7 = (x6.d0) r7
            java.lang.Object r5 = r7.f22580i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileAvatarImageManager.m6142downloadAvatar0E7RQCE(java.lang.String, java.io.File, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadInlineAvatarFromCloud(java.lang.String r9, d7.d<? super java.lang.String> r10) throws java.lang.Exception {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.arflix.tv.data.repository.ProfileAvatarImageManager.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r10
            com.arflix.tv.data.repository.ProfileAvatarImageManager$loadInlineAvatarFromCloud$1 r0 = (com.arflix.tv.data.repository.ProfileAvatarImageManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileAvatarImageManager$loadInlineAvatarFromCloud$1 r0 = new com.arflix.tv.data.repository.ProfileAvatarImageManager$loadInlineAvatarFromCloud$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r9 = r0.L$0
            java.lang.String r9 = (java.lang.String) r9
            k2.c.G(r10)
            x6.d0 r10 = (x6.d0) r10
            java.lang.Object r10 = r10.f22580i
            goto L47
        L2d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L35:
            k2.c.G(r10)
            com.arflix.tv.data.repository.AuthRepository r10 = r8.authRepository
            r0.L$0 = r9
            r0.label = r2
            java.lang.Object r10 = r10.m6099loadAccountSyncPayloadIoAF18A(r0)
            e7.a r0 = e7.a.f15033i
            if (r10 != r0) goto L47
            return r0
        L47:
            boolean r0 = r10 instanceof x6.c0
            r1 = 0
            if (r0 == 0) goto L4d
            r10 = r1
        L4d:
            java.lang.String r10 = (java.lang.String) r10
            if (r10 == 0) goto Laa
            boolean r0 = kotlin.text.o.h0(r10)
            if (r0 != 0) goto L58
            goto L59
        L58:
            r10 = r1
        L59:
            if (r10 == 0) goto Laa
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Exception -> L76 org.json.JSONException -> L79
            r0.<init>(r10)     // Catch: java.lang.Exception -> L76 org.json.JSONException -> L79
            java.lang.String r10 = "profileAvatarImagesById"
            org.json.JSONObject r10 = r0.optJSONObject(r10)     // Catch: java.lang.Exception -> L76 org.json.JSONException -> L79
            if (r10 == 0) goto Laa
            java.lang.String r9 = r10.optString(r9)     // Catch: java.lang.Exception -> L76 org.json.JSONException -> L79
            if (r9 == 0) goto Laa
            boolean r10 = kotlin.text.o.h0(r9)     // Catch: java.lang.Exception -> L76 org.json.JSONException -> L79
            if (r10 != 0) goto Laa
            r1 = r9
            goto Laa
        L76:
            r0 = move-exception
            r9 = r0
            goto L7c
        L79:
            r0 = move-exception
            r9 = r0
            goto L96
        L7c:
            boolean r10 = r9 instanceof java.util.concurrent.CancellationException
            if (r10 != 0) goto L95
            com.arflix.tv.util.AppLogger r2 = com.arflix.tv.util.AppLogger.INSTANCE
            java.lang.String r9 = r9.getMessage()
            java.lang.String r10 = "Unexpected error parsing inline avatar: "
            java.lang.String r4 = androidx.compose.material3.d.C(r10, r9)
            r6 = 4
            r7 = 0
            java.lang.String r3 = "ProfileAvatar"
            r5 = 0
            com.arflix.tv.util.AppLogger.e$default(r2, r3, r4, r5, r6, r7)
            goto Laa
        L95:
            throw r9
        L96:
            com.arflix.tv.util.AppLogger r2 = com.arflix.tv.util.AppLogger.INSTANCE
            java.lang.String r9 = r9.getMessage()
            java.lang.String r10 = "Error parsing inline avatar JSON: "
            java.lang.String r4 = androidx.compose.material3.d.C(r10, r9)
            r6 = 4
            r7 = 0
            java.lang.String r3 = "ProfileAvatar"
            r5 = 0
            com.arflix.tv.util.AppLogger.e$default(r2, r3, r4, r5, r6, r7)
        Laa:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileAvatarImageManager.loadInlineAvatarFromCloud(java.lang.String, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object restoreAvatarIfNeeded$default(ProfileAvatarImageManager profileAvatarImageManager, Profile profile, String str, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        return profileAvatarImageManager.restoreAvatarIfNeeded(profile, str, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: uploadAvatar-BWLJW6A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6143uploadAvatarBWLJW6A(java.lang.String r11, long r12, java.io.File r14, d7.d<? super x6.d0> r15) throws java.lang.Throwable {
        /*
            r10 = this;
            boolean r0 = r15 instanceof com.arflix.tv.data.repository.ProfileAvatarImageManager$uploadAvatar$1
            if (r0 == 0) goto L13
            r0 = r15
            com.arflix.tv.data.repository.ProfileAvatarImageManager$uploadAvatar$1 r0 = (com.arflix.tv.data.repository.ProfileAvatarImageManager$uploadAvatar$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.ProfileAvatarImageManager$uploadAvatar$1 r0 = new com.arflix.tv.data.repository.ProfileAvatarImageManager$uploadAvatar$1
            r0.<init>(r10, r15)
        L18:
            java.lang.Object r15 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r11 = r0.L$1
            java.io.File r11 = (java.io.File) r11
            java.lang.Object r11 = r0.L$0
            java.lang.String r11 = (java.lang.String) r11
            k2.c.G(r15)
            goto L56
        L2d:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L35:
            k2.c.G(r15)
            ra.c r15 = ka.x0.f19655d
            com.arflix.tv.data.repository.ProfileAvatarImageManager$uploadAvatar$2 r3 = new com.arflix.tv.data.repository.ProfileAvatarImageManager$uploadAvatar$2
            r9 = 0
            r4 = r10
            r5 = r11
            r6 = r12
            r8 = r14
            r3.<init>(r4, r5, r6, r8, r9)
            r11 = 0
            r0.L$0 = r11
            r0.L$1 = r11
            r0.J$0 = r6
            r0.label = r2
            java.lang.Object r15 = ka.m0.y(r15, r3, r0)
            e7.a r11 = e7.a.f15033i
            if (r15 != r11) goto L56
            return r11
        L56:
            x6.d0 r15 = (x6.d0) r15
            java.lang.Object r11 = r15.f22580i
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.ProfileAvatarImageManager.m6143uploadAvatarBWLJW6A(java.lang.String, long, java.io.File, d7.d):java.lang.Object");
    }

    public final JSONObject buildInlineAvatarImagesJson(List<Profile> profiles, JSONObject existingImagesById) throws JSONException {
        String strOptString;
        JSONObject jSONObject = new JSONObject();
        ArrayList<Profile> arrayList = new ArrayList();
        for (Object obj : profiles) {
            if (((Profile) obj).getAvatarImageVersion() > 0) {
                arrayList.add(obj);
            }
        }
        for (Profile profile : arrayList) {
            String inlineBase64 = readInlineBase64(profile);
            String str = null;
            if (existingImagesById != null && (strOptString = existingImagesById.optString(profile.getId())) != null && !kotlin.text.o.h0(strOptString)) {
                str = strOptString;
            }
            if (inlineBase64 == null) {
                inlineBase64 = str;
            }
            if (inlineBase64 != null && !kotlin.text.o.h0(inlineBase64)) {
                jSONObject.put(profile.getId(), inlineBase64);
            }
        }
        return jSONObject;
    }

    public final void clearLocalAvatar(String profileId) {
        ProfileAvatarFiles.cleanupProfile$default(ProfileAvatarFiles.INSTANCE, this.context, profileId, null, 4, null);
    }

    public final Object importAvatar(String str, String str2, d7.d<? super ImportedProfileAvatar> dVar) {
        return ka.m0.y(x0.f19655d, new AnonymousClass2(str, str2, null), dVar);
    }

    public final String readInlineBase64(Profile profile) {
        File fileLocalFile = ProfileAvatarFiles.INSTANCE.localFile(this.context, profile);
        if (fileLocalFile != null && fileLocalFile.exists() && fileLocalFile.length() > 0) {
            try {
                return Base64.encodeToString(l7.i.r0(fileLocalFile), 2);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public final Object restoreAvatarIfNeeded(Profile profile, String str, d7.d<? super t0> dVar) throws Throwable {
        Object objY = ka.m0.y(x0.f19655d, new C09452(profile, this, str, null), dVar);
        return objY == e7.a.f15033i ? objY : t0.f22605a;
    }
}
