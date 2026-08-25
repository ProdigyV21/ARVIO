package com.arflix.tv.data.repository;

import com.arflix.tv.R;
import com.arflix.tv.util.Constants;
import gb.s0;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.data.repository.ProfileAvatarImageManager$downloadAvatar$2", f = "ProfileAvatarImageManager.kt", l = {200}, m = "invokeSuspend", v = 2)
public final class ProfileAvatarImageManager$downloadAvatar$2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
    final /* synthetic */ File $destination;
    final /* synthetic */ String $storagePath;
    int label;
    final /* synthetic */ ProfileAvatarImageManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileAvatarImageManager$downloadAvatar$2(ProfileAvatarImageManager profileAvatarImageManager, String str, File file, d7.d<? super ProfileAvatarImageManager$downloadAvatar$2> dVar) {
        super(2, dVar);
        this.this$0 = profileAvatarImageManager;
        this.$storagePath = str;
        this.$destination = file;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new ProfileAvatarImageManager$downloadAvatar$2(this.this$0, this.$storagePath, this.$destination, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        x6.c0 c0Var;
        Object obj2;
        String str;
        int i10 = this.label;
        try {
            if (i10 == 0) {
                k2.c.G(obj);
                if (Constants.INSTANCE.getUSE_NETLIFY_CLOUD_SYNC()) {
                    throw new IllegalStateException("Remote avatar storage is handled by account sync");
                }
                AuthRepository authRepository = this.this$0.authRepository;
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
                k2.c.G(obj);
            }
            str = (String) obj;
            if (str == null) {
                str = "";
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
        if (kotlin.text.o.h0(str)) {
            throw new IllegalStateException(this.this$0.context.getString(R.string.error_not_logged_in).toString());
        }
        gb.j0 j0Var = new gb.j0();
        Constants constants = Constants.INSTANCE;
        j0Var.i(kotlin.text.o.O0(constants.getSUPABASE_URL(), '/') + "/storage/v1/object/profile-avatars/" + this.$storagePath);
        j0Var.f15724c.h("apikey", constants.getSUPABASE_ANON_KEY());
        j0Var.f15724c.h("Authorization", "Bearer ".concat(str));
        j0Var.d();
        gb.p0 p0VarD = this.this$0.httpClient.a(j0Var.b()).d();
        ProfileAvatarImageManager profileAvatarImageManager = this.this$0;
        File file = this.$destination;
        try {
            if (!p0VarD.k()) {
                throw new IllegalStateException(profileAvatarImageManager.context.getString(R.string.avatar_download_failed, new Integer(p0VarD.f15783n)).toString());
            }
            s0 s0Var = p0VarD.f15786q;
            if (s0Var == null) {
                throw new IllegalStateException(profileAvatarImageManager.context.getString(R.string.avatar_response_empty).toString());
            }
            l7.i.u0(file, s0Var.j());
            p0VarD.close();
            obj2 = t0.f22605a;
            return new x6.d0(obj2);
        } finally {
        }
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
        return ((ProfileAvatarImageManager$downloadAvatar$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
