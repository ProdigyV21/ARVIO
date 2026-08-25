package n;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.SubMenu;
import androidx.appcompat.view.menu.u;
import androidx.appcompat.widget.z1;
import io.ktor.network.sockets.DatagramKt;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class j extends MenuInflater {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Class[] f20414e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Class[] f20415f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f20416a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f20417b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f20418c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f20419d;

    static {
        Class[] clsArr = {Context.class};
        f20414e = clsArr;
        f20415f = clsArr;
    }

    public j(Context context) {
        super(context);
        this.f20418c = context;
        Object[] objArr = {context};
        this.f20416a = objArr;
        this.f20417b = objArr;
    }

    public static Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    public final void b(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) throws XmlPullParserException, IOException {
        int i10;
        XmlPullParser xmlPullParser2;
        ColorStateList colorStateList;
        int resourceId;
        i iVar = new i(this, menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            i10 = 2;
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (!name.equals("menu")) {
                    throw new RuntimeException("Expecting menu, got ".concat(name));
                }
                eventType = xmlPullParser.next();
            } else {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        boolean z = false;
        boolean z5 = false;
        String str = null;
        while (!z) {
            if (eventType == 1) {
                throw new RuntimeException("Unexpected end of document");
            }
            if (eventType != i10) {
                if (eventType != 3) {
                    xmlPullParser2 = xmlPullParser;
                    z = z;
                } else {
                    String name2 = xmlPullParser.getName();
                    if (z5 && name2.equals(str)) {
                        xmlPullParser2 = xmlPullParser;
                        z5 = false;
                        str = null;
                    } else {
                        if (name2.equals("group")) {
                            iVar.f20392b = 0;
                            iVar.f20393c = 0;
                            iVar.f20394d = 0;
                            iVar.f20395e = 0;
                            iVar.f20396f = true;
                            iVar.f20397g = true;
                        } else if (name2.equals("item")) {
                            if (!iVar.f20398h) {
                                androidx.core.view.e eVar = iVar.z;
                                if (eVar == null || !((u) eVar).f1390a.hasSubMenu()) {
                                    iVar.f20398h = true;
                                    iVar.b(iVar.f20391a.add(iVar.f20392b, iVar.f20399i, iVar.f20400j, iVar.k));
                                } else {
                                    iVar.f20398h = true;
                                    iVar.b(iVar.f20391a.addSubMenu(iVar.f20392b, iVar.f20399i, iVar.f20400j, iVar.k).getItem());
                                }
                            }
                        } else if (name2.equals("menu")) {
                            xmlPullParser2 = xmlPullParser;
                            z = true;
                        }
                        xmlPullParser2 = xmlPullParser;
                        z = z;
                    }
                }
                eventType = xmlPullParser2.next();
                i10 = 2;
                z = z;
                z5 = z5;
            } else {
                if (!z5) {
                    String name3 = xmlPullParser.getName();
                    boolean zEquals = name3.equals("group");
                    Context context = this.f20418c;
                    if (zEquals) {
                        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.a.f19031p);
                        iVar.f20392b = typedArrayObtainStyledAttributes.getResourceId(1, 0);
                        iVar.f20393c = typedArrayObtainStyledAttributes.getInt(3, 0);
                        iVar.f20394d = typedArrayObtainStyledAttributes.getInt(4, 0);
                        iVar.f20395e = typedArrayObtainStyledAttributes.getInt(5, 0);
                        iVar.f20396f = typedArrayObtainStyledAttributes.getBoolean(2, true);
                        iVar.f20397g = typedArrayObtainStyledAttributes.getBoolean(0, true);
                        typedArrayObtainStyledAttributes.recycle();
                    } else {
                        if (name3.equals("item")) {
                            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, j.a.f19032q);
                            iVar.f20399i = typedArrayObtainStyledAttributes2.getResourceId(2, 0);
                            iVar.f20400j = (typedArrayObtainStyledAttributes2.getInt(5, iVar.f20393c) & (-65536)) | (typedArrayObtainStyledAttributes2.getInt(6, iVar.f20394d) & DatagramKt.MAX_DATAGRAM_SIZE);
                            iVar.k = typedArrayObtainStyledAttributes2.getText(7);
                            iVar.f20401l = typedArrayObtainStyledAttributes2.getText(8);
                            iVar.f20402m = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
                            String string = typedArrayObtainStyledAttributes2.getString(9);
                            iVar.f20403n = string == null ? (char) 0 : string.charAt(0);
                            iVar.f20404o = typedArrayObtainStyledAttributes2.getInt(16, 4096);
                            String string2 = typedArrayObtainStyledAttributes2.getString(10);
                            iVar.f20405p = string2 == null ? (char) 0 : string2.charAt(0);
                            iVar.f20406q = typedArrayObtainStyledAttributes2.getInt(20, 4096);
                            if (typedArrayObtainStyledAttributes2.hasValue(11)) {
                                iVar.f20407r = typedArrayObtainStyledAttributes2.getBoolean(11, false) ? 1 : 0;
                            } else {
                                iVar.f20407r = iVar.f20395e;
                            }
                            iVar.f20408s = typedArrayObtainStyledAttributes2.getBoolean(3, false);
                            iVar.f20409t = typedArrayObtainStyledAttributes2.getBoolean(4, iVar.f20396f);
                            iVar.f20410u = typedArrayObtainStyledAttributes2.getBoolean(1, iVar.f20397g);
                            iVar.f20411v = typedArrayObtainStyledAttributes2.getInt(21, -1);
                            iVar.f20413y = typedArrayObtainStyledAttributes2.getString(12);
                            iVar.f20412w = typedArrayObtainStyledAttributes2.getResourceId(13, 0);
                            iVar.x = typedArrayObtainStyledAttributes2.getString(15);
                            String string3 = typedArrayObtainStyledAttributes2.getString(14);
                            boolean z10 = string3 != null;
                            if (z10 && iVar.f20412w == 0 && iVar.x == null) {
                                iVar.z = (androidx.core.view.e) iVar.a(string3, f20415f, this.f20417b);
                            } else {
                                if (z10) {
                                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                                }
                                iVar.z = null;
                            }
                            iVar.A = typedArrayObtainStyledAttributes2.getText(17);
                            iVar.B = typedArrayObtainStyledAttributes2.getText(22);
                            if (typedArrayObtainStyledAttributes2.hasValue(19)) {
                                iVar.D = z1.b(typedArrayObtainStyledAttributes2.getInt(19, -1), iVar.D);
                            } else {
                                iVar.D = null;
                            }
                            if (typedArrayObtainStyledAttributes2.hasValue(18)) {
                                if (!typedArrayObtainStyledAttributes2.hasValue(18) || (resourceId = typedArrayObtainStyledAttributes2.getResourceId(18, 0)) == 0 || (colorStateList = qb.d.C(context, resourceId)) == null) {
                                    colorStateList = typedArrayObtainStyledAttributes2.getColorStateList(18);
                                }
                                iVar.C = colorStateList;
                            } else {
                                iVar.C = null;
                            }
                            typedArrayObtainStyledAttributes2.recycle();
                            iVar.f20398h = false;
                            xmlPullParser2 = xmlPullParser;
                        } else if (name3.equals("menu")) {
                            iVar.f20398h = true;
                            SubMenu subMenuAddSubMenu = iVar.f20391a.addSubMenu(iVar.f20392b, iVar.f20399i, iVar.f20400j, iVar.k);
                            iVar.b(subMenuAddSubMenu.getItem());
                            xmlPullParser2 = xmlPullParser;
                            b(xmlPullParser2, attributeSet, subMenuAddSubMenu);
                        } else {
                            xmlPullParser2 = xmlPullParser;
                            str = name3;
                            z5 = true;
                        }
                        eventType = xmlPullParser2.next();
                        i10 = 2;
                        z = z;
                        z5 = z5;
                    }
                }
                xmlPullParser2 = xmlPullParser;
                z = z;
            }
            eventType = xmlPullParser2.next();
            i10 = 2;
            z = z;
            z5 = z5;
        }
    }

    @Override // android.view.MenuInflater
    public final void inflate(int i10, Menu menu) {
        if (!(menu instanceof k0.a)) {
            super.inflate(i10, menu);
            return;
        }
        XmlResourceParser layout = null;
        try {
            try {
                try {
                    layout = this.f20418c.getResources().getLayout(i10);
                    b(layout, Xml.asAttributeSet(layout), menu);
                    layout.close();
                } catch (IOException e5) {
                    throw new InflateException("Error inflating menu XML", e5);
                }
            } catch (XmlPullParserException e6) {
                throw new InflateException("Error inflating menu XML", e6);
            }
        } catch (Throwable th) {
            if (layout != null) {
                layout.close();
            }
            throw th;
        }
    }
}
