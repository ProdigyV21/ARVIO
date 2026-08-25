package io.sentry.android.core.internal.gestures;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import io.sentry.android.core.SentryAndroidOptions;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f16554a = new int[2];

    public static io.sentry.internal.gestures.c a(SentryAndroidOptions sentryAndroidOptions, View view, float f10, float f11, io.sentry.internal.gestures.b bVar) {
        List<io.sentry.internal.gestures.a> gestureTargetLocators = sentryAndroidOptions.getGestureTargetLocators();
        LinkedList linkedList = new LinkedList();
        linkedList.add(view);
        io.sentry.internal.gestures.c cVar = null;
        while (linkedList.size() > 0) {
            View view2 = (View) linkedList.poll();
            if (view2 != null) {
                int[] iArr = f16554a;
                view2.getLocationOnScreen(iArr);
                int i10 = iArr[0];
                int i11 = iArr[1];
                int width = view2.getWidth();
                int height = view2.getHeight();
                if (f10 >= i10 && f10 <= i10 + width && f11 >= i11 && f11 <= i11 + height) {
                    if (view2 instanceof ViewGroup) {
                        ViewGroup viewGroup = (ViewGroup) view2;
                        for (int i12 = 0; i12 < viewGroup.getChildCount(); i12++) {
                            linkedList.add(viewGroup.getChildAt(i12));
                        }
                    }
                    for (int i13 = 0; i13 < gestureTargetLocators.size(); i13++) {
                        io.sentry.internal.gestures.c cVarA = gestureTargetLocators.get(i13).a(view2, bVar);
                        if (cVarA != null) {
                            if (bVar == io.sentry.internal.gestures.b.CLICKABLE) {
                                cVar = cVarA;
                            } else if (bVar == io.sentry.internal.gestures.b.SCROLLABLE) {
                                return cVarA;
                            }
                        }
                    }
                }
            }
        }
        return cVar;
    }

    public static String b(View view) {
        int id = view.getId();
        if (id == -1 || (((-16777216) & id) == 0 && (16777215 & id) != 0)) {
            throw new Resources.NotFoundException();
        }
        Resources resources = view.getContext().getResources();
        return resources != null ? resources.getResourceEntryName(id) : "";
    }
}
