package com.arflix.tv.updater;

import android.os.Build;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.x;
import kotlin.collections.z;
import kotlin.text.o;
import kotlin.text.u;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0005R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/arflix/tv/updater/AbiSelector;", "", "<init>", "()V", "knownAbis", "", "", "chooseBestApkAsset", "Lcom/arflix/tv/updater/GitHubAssetDto;", "assets", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AbiSelector {
    public static final int $stable = 0;
    public static final AbiSelector INSTANCE = new AbiSelector();
    private static final List<String> knownAbis = a.E("arm64-v8a", "armeabi-v7a", "x86_64", "x86");

    private AbiSelector() {
    }

    public final GitHubAssetDto chooseBestApkAsset(List<GitHubAssetDto> assets) {
        Object next;
        Object next2;
        ArrayList arrayList = new ArrayList();
        for (Object obj : assets) {
            if (u.K(((GitHubAssetDto) obj).getName(), ".apk", true)) {
                arrayList.add(obj);
            }
        }
        Object obj2 = null;
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (GitHubAssetDto) x.m0(arrayList);
        }
        String[] strArr = Build.SUPPORTED_ABIS;
        List<String> listO0 = strArr != null ? r.o0(strArr) : null;
        if (listO0 == null) {
            listO0 = z.f19728i;
        }
        for (String str : listO0) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it.next();
                if (o.T(((GitHubAssetDto) next2).getName(), str, true)) {
                    break;
                }
            }
            GitHubAssetDto gitHubAssetDto = (GitHubAssetDto) next2;
            if (gitHubAssetDto != null) {
                return gitHubAssetDto;
            }
        }
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
            String lowerCase = ((GitHubAssetDto) next).getName().toLowerCase(Locale.ROOT);
            if (o.T(lowerCase, "universal", false) || o.T(lowerCase, TtmlNode.COMBINE_ALL, false) || o.T(lowerCase, "release", false)) {
                break;
            }
        }
        GitHubAssetDto gitHubAssetDto2 = (GitHubAssetDto) next;
        if (gitHubAssetDto2 != null) {
            return gitHubAssetDto2;
        }
        loop4: for (Object obj3 : arrayList) {
            GitHubAssetDto gitHubAssetDto3 = (GitHubAssetDto) obj3;
            List<String> list = knownAbis;
            if (list == null || !list.isEmpty()) {
                Iterator<T> it3 = list.iterator();
                while (it3.hasNext()) {
                    if (o.T(gitHubAssetDto3.getName(), (String) it3.next(), true)) {
                        break;
                    }
                }
            }
            obj2 = obj3;
        }
        GitHubAssetDto gitHubAssetDto4 = (GitHubAssetDto) obj2;
        return gitHubAssetDto4 == null ? (GitHubAssetDto) x.m0(arrayList) : gitHubAssetDto4;
    }
}
