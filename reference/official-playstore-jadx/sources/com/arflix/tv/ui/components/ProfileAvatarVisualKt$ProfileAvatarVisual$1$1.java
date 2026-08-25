package com.arflix.tv.ui.components;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.repository.ProfileAvatarImageManager;
import com.arflix.tv.util.ProfileAvatarFiles;
import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.ProfileAvatarVisualKt$ProfileAvatarVisual$1$1", f = "ProfileAvatarVisual.kt", l = {68}, m = "invokeSuspend", v = 2)
public final class ProfileAvatarVisualKt$ProfileAvatarVisual$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ ProfileAvatarImageManager $avatarManager;
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<File> $customFile$delegate;
    final /* synthetic */ Profile $profile;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileAvatarVisualKt$ProfileAvatarVisual$1$1(Profile profile, Context context, ProfileAvatarImageManager profileAvatarImageManager, MutableState<File> mutableState, d7.d<? super ProfileAvatarVisualKt$ProfileAvatarVisual$1$1> dVar) {
        super(2, dVar);
        this.$profile = profile;
        this.$context = context;
        this.$avatarManager = profileAvatarImageManager;
        this.$customFile$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new ProfileAvatarVisualKt$ProfileAvatarVisual$1$1(this.$profile, this.$context, this.$avatarManager, this.$customFile$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ProfileAvatarVisualKt$ProfileAvatarVisual$1$1 profileAvatarVisualKt$ProfileAvatarVisual$1$1;
        int i10 = this.label;
        x6.t0 t0Var = x6.t0.f22605a;
        File file = null;
        if (i10 == 0) {
            k2.c.G(obj);
            if (this.$profile.getAvatarImageVersion() <= 0) {
                this.$customFile$delegate.setValue(null);
                return t0Var;
            }
            File fileLocalFile = ProfileAvatarFiles.INSTANCE.localFile(this.$context, this.$profile);
            if (fileLocalFile == null || !fileLocalFile.exists() || fileLocalFile.length() <= 0) {
                fileLocalFile = null;
            }
            if (fileLocalFile != null) {
                this.$customFile$delegate.setValue(fileLocalFile);
                return t0Var;
            }
            ProfileAvatarImageManager profileAvatarImageManager = this.$avatarManager;
            Profile profile = this.$profile;
            this.L$0 = null;
            this.label = 1;
            profileAvatarVisualKt$ProfileAvatarVisual$1$1 = this;
            Object objRestoreAvatarIfNeeded$default = ProfileAvatarImageManager.restoreAvatarIfNeeded$default(profileAvatarImageManager, profile, null, profileAvatarVisualKt$ProfileAvatarVisual$1$1, 2, null);
            e7.a aVar = e7.a.f15033i;
            if (objRestoreAvatarIfNeeded$default == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            profileAvatarVisualKt$ProfileAvatarVisual$1$1 = this;
        }
        MutableState<File> mutableState = profileAvatarVisualKt$ProfileAvatarVisual$1$1.$customFile$delegate;
        File fileLocalFile2 = ProfileAvatarFiles.INSTANCE.localFile(profileAvatarVisualKt$ProfileAvatarVisual$1$1.$context, profileAvatarVisualKt$ProfileAvatarVisual$1$1.$profile);
        if (fileLocalFile2 != null && fileLocalFile2.exists() && fileLocalFile2.length() > 0) {
            file = fileLocalFile2;
        }
        mutableState.setValue(file);
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((ProfileAvatarVisualKt$ProfileAvatarVisual$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
