package io.sentry.android.replay.util;

import android.graphics.Bitmap;
import android.graphics.Paint;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends r implements r7.a {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final d f17043l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final d f17044m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f17045i;

    static {
        int i10 = 0;
        f17043l = new d(i10, 0);
        f17044m = new d(i10, 1);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(int i10, int i11) {
        super(i10);
        this.f17045i = i11;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f17045i) {
            case 0:
                return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            default:
                return new Paint();
        }
    }
}
