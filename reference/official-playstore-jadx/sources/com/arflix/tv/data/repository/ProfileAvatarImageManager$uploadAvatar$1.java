package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@f7.e(c = "com.arflix.tv.data.repository.ProfileAvatarImageManager", f = "ProfileAvatarImageManager.kt", l = {162}, m = "uploadAvatar-BWLJW6A", v = 2)
public final class ProfileAvatarImageManager$uploadAvatar$1 extends f7.c {
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ProfileAvatarImageManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileAvatarImageManager$uploadAvatar$1(ProfileAvatarImageManager profileAvatarImageManager, d7.d<? super ProfileAvatarImageManager$uploadAvatar$1> dVar) {
        super(dVar);
        this.this$0 = profileAvatarImageManager;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) throws Throwable {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6143uploadAvatarBWLJW6A = this.this$0.m6143uploadAvatarBWLJW6A(null, 0L, null, this);
        return objM6143uploadAvatarBWLJW6A == e7.a.f15033i ? objM6143uploadAvatarBWLJW6A : new x6.d0(objM6143uploadAvatarBWLJW6A);
    }
}
