package androidx.compose.ui.graphics.vector.compat;

import a0.c;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import i0.b;
import i0.d;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0080\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\n\u0010\u000bJ3\u0010\u0015\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0018\u00010\u000eR\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J/\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0001\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004¢\u0006\u0004\b\u001c\u0010\u001dJ/\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0001\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001e¢\u0006\u0004\b\u001f\u0010 J/\u0010\"\u001a\u00020!2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0001\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020!¢\u0006\u0004\b\"\u0010#J%\u0010%\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001e¢\u0006\u0004\b%\u0010&J%\u0010'\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004¢\u0006\u0004\b'\u0010(J\u001f\u0010)\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u0004¢\u0006\u0004\b)\u0010*J%\u0010,\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u001e¢\u0006\u0004\b,\u0010&J?\u0010.\u001a\u00020-2\u0006\u0010\u0017\u001a\u00020\u00142\f\u0010\u000f\u001a\b\u0018\u00010\u000eR\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0001\u0010\u001a\u001a\u00020\u00042\b\b\u0001\u0010\u001b\u001a\u00020\u0004¢\u0006\u0004\b.\u0010/J7\u00101\u001a\u0004\u0018\u0001002\u0006\u0010\u0017\u001a\u00020\u00142\f\u0010\u000f\u001a\b\u0018\u00010\u000eR\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00182\b\b\u0001\u0010\u001a\u001a\u00020\u0004¢\u0006\u0004\b1\u00102J\u0010\u00103\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b3\u00104J\u0010\u00105\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b5\u00106J$\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b7\u00108J\u0010\u00109\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b9\u0010:J\u0010\u0010;\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b;\u00106J\u001a\u0010=\u001a\u00020!2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b=\u0010>R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010?\u001a\u0004\b@\u00104R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010A\u001a\u0004\bB\u00106\"\u0004\bC\u0010\u000b¨\u0006D"}, d2 = {"Landroidx/compose/ui/graphics/vector/compat/AndroidVectorParser;", "", "Lorg/xmlpull/v1/XmlPullParser;", "xmlParser", "", "config", "<init>", "(Lorg/xmlpull/v1/XmlPullParser;I)V", "resConfig", "Lx6/t0;", "updateConfig", "(I)V", "Landroid/content/res/Resources;", "res", "Landroid/content/res/Resources$Theme;", "theme", "Landroid/util/AttributeSet;", "set", "", "attrs", "Landroid/content/res/TypedArray;", "obtainAttributes", "(Landroid/content/res/Resources;Landroid/content/res/Resources$Theme;Landroid/util/AttributeSet;[I)Landroid/content/res/TypedArray;", "typedArray", "", "attrName", "resId", "defaultValue", "getNamedInt", "(Landroid/content/res/TypedArray;Ljava/lang/String;II)I", "", "getNamedFloat", "(Landroid/content/res/TypedArray;Ljava/lang/String;IF)F", "", "getNamedBoolean", "(Landroid/content/res/TypedArray;Ljava/lang/String;IZ)Z", "index", "getFloat", "(Landroid/content/res/TypedArray;IF)F", "getInt", "(Landroid/content/res/TypedArray;II)I", "getString", "(Landroid/content/res/TypedArray;I)Ljava/lang/String;", "defValue", "getDimension", "Li0/d;", "getNamedComplexColor", "(Landroid/content/res/TypedArray;Landroid/content/res/Resources$Theme;Ljava/lang/String;II)Li0/d;", "Landroid/content/res/ColorStateList;", "getNamedColorStateList", "(Landroid/content/res/TypedArray;Landroid/content/res/Resources$Theme;Ljava/lang/String;I)Landroid/content/res/ColorStateList;", "component1", "()Lorg/xmlpull/v1/XmlPullParser;", "component2", "()I", "copy", "(Lorg/xmlpull/v1/XmlPullParser;I)Landroidx/compose/ui/graphics/vector/compat/AndroidVectorParser;", "toString", "()Ljava/lang/String;", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "Lorg/xmlpull/v1/XmlPullParser;", "getXmlParser", "I", "getConfig", "setConfig", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class AndroidVectorParser {
    public static final int $stable = 8;
    private int config;
    private final XmlPullParser xmlParser;

    public AndroidVectorParser(XmlPullParser xmlPullParser, int i10) {
        this.xmlParser = xmlPullParser;
        this.config = i10;
    }

    public static /* synthetic */ AndroidVectorParser copy$default(AndroidVectorParser androidVectorParser, XmlPullParser xmlPullParser, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            xmlPullParser = androidVectorParser.xmlParser;
        }
        if ((i11 & 2) != 0) {
            i10 = androidVectorParser.config;
        }
        return androidVectorParser.copy(xmlPullParser, i10);
    }

    private final void updateConfig(int resConfig) {
        this.config = resConfig | this.config;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final XmlPullParser getXmlParser() {
        return this.xmlParser;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getConfig() {
        return this.config;
    }

    public final AndroidVectorParser copy(XmlPullParser xmlParser, int config) {
        return new AndroidVectorParser(xmlParser, config);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AndroidVectorParser)) {
            return false;
        }
        AndroidVectorParser androidVectorParser = (AndroidVectorParser) other;
        return p.a(this.xmlParser, androidVectorParser.xmlParser) && this.config == androidVectorParser.config;
    }

    public final int getConfig() {
        return this.config;
    }

    public final float getDimension(TypedArray typedArray, int index, float defValue) {
        float dimension = typedArray.getDimension(index, defValue);
        updateConfig(typedArray.getChangingConfigurations());
        return dimension;
    }

    public final float getFloat(TypedArray typedArray, int index, float defaultValue) {
        float f10 = typedArray.getFloat(index, defaultValue);
        updateConfig(typedArray.getChangingConfigurations());
        return f10;
    }

    public final int getInt(TypedArray typedArray, int index, int defaultValue) {
        int i10 = typedArray.getInt(index, defaultValue);
        updateConfig(typedArray.getChangingConfigurations());
        return i10;
    }

    public final boolean getNamedBoolean(TypedArray typedArray, String attrName, int resId, boolean defaultValue) {
        if (b.d(this.xmlParser, attrName)) {
            defaultValue = typedArray.getBoolean(resId, defaultValue);
        }
        updateConfig(typedArray.getChangingConfigurations());
        return defaultValue;
    }

    public final ColorStateList getNamedColorStateList(TypedArray typedArray, Resources.Theme theme, String attrName, int resId) {
        ColorStateList colorStateListA = b.a(typedArray, this.xmlParser, theme, attrName, resId);
        updateConfig(typedArray.getChangingConfigurations());
        return colorStateListA;
    }

    public final d getNamedComplexColor(TypedArray typedArray, Resources.Theme theme, String attrName, int resId, int defaultValue) {
        d dVarB = b.b(typedArray, this.xmlParser, theme, attrName, resId, defaultValue);
        updateConfig(typedArray.getChangingConfigurations());
        return dVarB;
    }

    public final float getNamedFloat(TypedArray typedArray, String attrName, int resId, float defaultValue) {
        if (b.d(this.xmlParser, attrName)) {
            defaultValue = typedArray.getFloat(resId, defaultValue);
        }
        updateConfig(typedArray.getChangingConfigurations());
        return defaultValue;
    }

    public final int getNamedInt(TypedArray typedArray, String attrName, int resId, int defaultValue) {
        if (b.d(this.xmlParser, attrName)) {
            defaultValue = typedArray.getInt(resId, defaultValue);
        }
        updateConfig(typedArray.getChangingConfigurations());
        return defaultValue;
    }

    public final String getString(TypedArray typedArray, int index) {
        String string = typedArray.getString(index);
        updateConfig(typedArray.getChangingConfigurations());
        return string;
    }

    public final XmlPullParser getXmlParser() {
        return this.xmlParser;
    }

    public int hashCode() {
        return (this.xmlParser.hashCode() * 31) + this.config;
    }

    public final TypedArray obtainAttributes(Resources res, Resources.Theme theme, AttributeSet set, int[] attrs) {
        TypedArray typedArrayG = b.g(res, theme, set, attrs);
        updateConfig(typedArrayG.getChangingConfigurations());
        return typedArrayG;
    }

    public final void setConfig(int i10) {
        this.config = i10;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("AndroidVectorParser(xmlParser=");
        sb2.append(this.xmlParser);
        sb2.append(", config=");
        return c.o(sb2, this.config, ')');
    }

    public /* synthetic */ AndroidVectorParser(XmlPullParser xmlPullParser, int i10, int i11, h hVar) {
        this(xmlPullParser, (i11 & 2) != 0 ? 0 : i10);
    }
}
