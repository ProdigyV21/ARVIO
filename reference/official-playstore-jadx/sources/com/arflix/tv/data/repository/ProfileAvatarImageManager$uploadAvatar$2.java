package com.arflix.tv.data.repository;

import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import com.arflix.tv.R;
import com.arflix.tv.util.Constants;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.regex.Pattern;
import kotlin.Metadata;
import org.jsoup.helper.HttpConnection;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.ProfileAvatarImageManager$uploadAvatar$2", f = "ProfileAvatarImageManager.kt", l = {168}, m = "invokeSuspend", v = 2)
public final class ProfileAvatarImageManager$uploadAvatar$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ File $file;
    final /* synthetic */ String $profileId;
    final /* synthetic */ long $version;
    Object L$0;
    int label;
    final /* synthetic */ ProfileAvatarImageManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileAvatarImageManager$uploadAvatar$2(ProfileAvatarImageManager profileAvatarImageManager, String str, long j10, File file, d7.d<? super ProfileAvatarImageManager$uploadAvatar$2> dVar) {
        super(2, dVar);
        this.this$0 = profileAvatarImageManager;
        this.$profileId = str;
        this.$version = j10;
        this.$file = file;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new ProfileAvatarImageManager$uploadAvatar$2(this.this$0, this.$profileId, this.$version, this.$file, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        x6.c0 c0Var;
        Object obj2;
        String str;
        int i10 = this.label;
        String str2 = "";
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                if (Constants.INSTANCE.getUSE_NETLIFY_CLOUD_SYNC()) {
                    throw new IllegalStateException("Remote avatar storage is handled by account sync");
                }
                String currentUserId = this.this$0.authRepository.getCurrentUserId();
                str = currentUserId == null ? "" : currentUserId;
                AuthRepository authRepository = this.this$0.authRepository;
                this.L$0 = str;
                this.label = 1;
                obj = authRepository.getAccessToken(this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                str = (String) this.L$0;
                k2.c.G(obj);
            }
            String str3 = (String) obj;
            if (str3 != null) {
                str2 = str3;
            }
        } catch (IOException e5) {
            c0Var = new x6.c0(e5);
            obj2 = c0Var;
        } catch (CancellationException e6) {
            throw e6;
        } catch (Exception e10) {
            c0Var = new x6.c0(e10);
            obj2 = c0Var;
        }
        if (kotlin.text.o.h0(str) || kotlin.text.o.h0(str2)) {
            throw new IllegalStateException(this.this$0.context.getString(R.string.error_not_logged_in).toString());
        }
        String str4 = str + DomExceptionUtils.SEPARATOR + this.$profileId + DomExceptionUtils.SEPARATOR + this.$version + ".jpg";
        gb.j0 j0Var = new gb.j0();
        Constants constants = Constants.INSTANCE;
        j0Var.i(kotlin.text.o.O0(constants.getSUPABASE_URL(), '/') + "/storage/v1/object/profile-avatars/" + str4);
        j0Var.f15724c.h("apikey", constants.getSUPABASE_ANON_KEY());
        j0Var.f15724c.h("Authorization", "Bearer ".concat(str2));
        j0Var.f15724c.h(HttpConnection.CONTENT_TYPE, "image/jpeg");
        j0Var.f15724c.h("cache-control", "31536000");
        j0Var.f15724c.h("x-upsert", "true");
        File file = this.$file;
        Pattern pattern = gb.d0.f15633d;
        j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new gb.l0(xc.d.i0("image/jpeg"), file, 0));
        gb.p0 p0VarD = this.this$0.httpClient.a(j0Var.b()).d();
        ProfileAvatarImageManager profileAvatarImageManager = this.this$0;
        try {
            if (!p0VarD.k()) {
                throw new IllegalStateException(profileAvatarImageManager.context.getString(R.string.avatar_upload_failed, new Integer(p0VarD.f15783n)).toString());
            }
            p0VarD.close();
            obj2 = str4;
        } finally {
        }
        c0Var = new x6.c0(e5);
        obj2 = c0Var;
        return new x6.d0(obj2);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((ProfileAvatarImageManager$uploadAvatar$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
