package com.arflix.tv.data.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import t7.a;
import v7.c;
import v7.d;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/arflix/tv/data/model/ProfileColors;", "", "<init>", "()V", "colors", "", "", "getColors", "()Ljava/util/List;", "random", "getByIndex", "index", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ProfileColors {
    public static final int $stable = 0;
    public static final ProfileColors INSTANCE = new ProfileColors();
    private static final List<Long> colors = a.E(4293200148L, 4280138068L, 4282090230L, 4294286859L, 4287323382L, 4293675161L, 4279548070L, 4284704497L);

    private ProfileColors() {
    }

    public final long getByIndex(int index) {
        List<Long> list = colors;
        return list.get(index % list.size()).longValue();
    }

    public final List<Long> getColors() {
        return colors;
    }

    public final long random() {
        List<Long> list = colors;
        c cVar = d.f22177i;
        return ((Number) x.J0(list)).longValue();
    }
}
