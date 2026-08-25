package androidx.compose.ui;

import androidx.compose.ui.Modifier;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class b {
    public static boolean a(Modifier.Element element, l lVar) {
        return ((Boolean) lVar.invoke(element)).booleanValue();
    }

    public static boolean b(Modifier.Element element, l lVar) {
        return ((Boolean) lVar.invoke(element)).booleanValue();
    }

    public static Object c(Modifier.Element element, Object obj, p pVar) {
        return pVar.invoke(obj, element);
    }

    public static Object d(Modifier.Element element, Object obj, p pVar) {
        return pVar.invoke(element, obj);
    }
}
