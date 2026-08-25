package androidx.core.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final androidx.compose.ui.node.d f2138a = new androidx.compose.ui.node.d(2);

    public static j a(Context context, e eVar) throws Throwable {
        Cursor cursorA;
        c cVar;
        Uri uriWithAppendedId;
        PackageManager packageManager = context.getPackageManager();
        Resources resources = context.getResources();
        String str = (String) eVar.f2140l;
        String str2 = (String) eVar.f2141m;
        int i10 = 0;
        ProviderInfo providerInfoResolveContentProvider = packageManager.resolveContentProvider(str, 0);
        if (providerInfoResolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException(androidx.compose.material3.d.C("No package found for authority: ", str));
        }
        if (!providerInfoResolveContentProvider.packageName.equals(str2)) {
            throw new PackageManager.NameNotFoundException(androidx.compose.material3.d.n("Found content provider ", str, ", but package was not ", str2));
        }
        Signature[] signatureArr = packageManager.getPackageInfo(providerInfoResolveContentProvider.packageName, 64).signatures;
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        androidx.compose.ui.node.d dVar = f2138a;
        Collections.sort(arrayList, dVar);
        List listI = (List) eVar.f2144p;
        if (listI == null) {
            listI = i0.b.i(resources, 0);
        }
        int i11 = 0;
        loop1: while (true) {
            cursorA = null;
            if (i11 >= listI.size()) {
                providerInfoResolveContentProvider = null;
                break;
            }
            ArrayList arrayList2 = new ArrayList((Collection) listI.get(i11));
            Collections.sort(arrayList2, dVar);
            if (arrayList.size() == arrayList2.size()) {
                for (int i12 = 0; i12 < arrayList.size(); i12++) {
                    if (!Arrays.equals((byte[]) arrayList.get(i12), (byte[]) arrayList2.get(i12))) {
                        break;
                    }
                }
                break loop1;
            }
            i11++;
        }
        if (providerInfoResolveContentProvider == null) {
            return new j(1, null, 0);
        }
        String str3 = providerInfoResolveContentProvider.authority;
        ArrayList arrayList3 = new ArrayList();
        Uri uriBuild = new Uri.Builder().scheme("content").authority(str3).build();
        Uri uriBuild2 = new Uri.Builder().scheme("content").authority(str3).appendPath("file").build();
        c cVar2 = Build.VERSION.SDK_INT < 24 ? new c(context, uriBuild, 0) : new c(context, uriBuild, 1);
        try {
            cursorA = cVar2.a(uriBuild, new String[]{"_id", "file_id", "font_ttc_index", "font_variation_settings", "font_weight", "font_italic", "result_code"}, new String[]{(String) eVar.f2142n});
            if (cursorA != null && cursorA.getCount() > 0) {
                int columnIndex = cursorA.getColumnIndex("result_code");
                arrayList3 = new ArrayList();
                int columnIndex2 = cursorA.getColumnIndex("_id");
                int columnIndex3 = cursorA.getColumnIndex("file_id");
                int columnIndex4 = cursorA.getColumnIndex("font_ttc_index");
                int columnIndex5 = cursorA.getColumnIndex("font_weight");
                int columnIndex6 = cursorA.getColumnIndex("font_italic");
                while (cursorA.moveToNext()) {
                    int i13 = columnIndex != -1 ? cursorA.getInt(columnIndex) : i10;
                    int i14 = columnIndex4 != -1 ? cursorA.getInt(columnIndex4) : i10;
                    if (columnIndex3 == -1) {
                        cVar = cVar2;
                        try {
                            uriWithAppendedId = ContentUris.withAppendedId(uriBuild, cursorA.getLong(columnIndex2));
                        } catch (Throwable th) {
                            th = th;
                            if (cursorA != null) {
                                cursorA.close();
                            }
                            cVar.close();
                            throw th;
                        }
                    } else {
                        cVar = cVar2;
                        uriWithAppendedId = ContentUris.withAppendedId(uriBuild2, cursorA.getLong(columnIndex3));
                    }
                    arrayList3.add(new k(uriWithAppendedId, i14, columnIndex5 != -1 ? cursorA.getInt(columnIndex5) : MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, columnIndex6 != -1 && cursorA.getInt(columnIndex6) == 1, i13));
                    i10 = 0;
                    cVar2 = cVar;
                }
            }
            c cVar3 = cVar2;
            if (cursorA != null) {
                cursorA.close();
            }
            cVar3.close();
            return new j(0, (k[]) arrayList3.toArray(new k[0]), 0);
        } catch (Throwable th2) {
            th = th2;
            cVar = cVar2;
        }
    }
}
