package com.arflix.tv.data.telegram;

import java.io.File;
import kotlin.text.i;
import r7.l;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7724i;

    public /* synthetic */ d(int i10) {
        this.f7724i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7724i) {
            case 0:
                return Boolean.valueOf(((File) obj).isFile());
            default:
                return Integer.valueOf(TelegramSearchMatcher.score$lambda$2((i) obj));
        }
    }
}
