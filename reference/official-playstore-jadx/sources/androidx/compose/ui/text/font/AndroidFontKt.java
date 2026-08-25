package androidx.compose.ui.text.font;

import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;
import androidx.compose.ui.text.font.FontVariation;
import java.io.File;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a@\u0010\r\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a8\u0010\r\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a8\u0010\r\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0014\u001a\u000f\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0016\u0010\u0017\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"", "path", "Landroid/content/res/AssetManager;", "assetManager", "Landroidx/compose/ui/text/font/FontWeight;", "weight", "Landroidx/compose/ui/text/font/FontStyle;", "style", "Landroidx/compose/ui/text/font/FontVariation$Settings;", "variationSettings", "Landroidx/compose/ui/text/font/Font;", "Font-MuC2MFs", "(Ljava/lang/String;Landroid/content/res/AssetManager;Landroidx/compose/ui/text/font/FontWeight;ILandroidx/compose/ui/text/font/FontVariation$Settings;)Landroidx/compose/ui/text/font/Font;", "Font", "Ljava/io/File;", "file", "Font-Ej4NQ78", "(Ljava/io/File;Landroidx/compose/ui/text/font/FontWeight;ILandroidx/compose/ui/text/font/FontVariation$Settings;)Landroidx/compose/ui/text/font/Font;", "Landroid/os/ParcelFileDescriptor;", "fileDescriptor", "(Landroid/os/ParcelFileDescriptor;Landroidx/compose/ui/text/font/FontWeight;ILandroidx/compose/ui/text/font/FontVariation$Settings;)Landroidx/compose/ui/text/font/Font;", "Lx6/t0;", "generateAndroidFontKtForApiCompatibility", "()V", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidFontKt {
    /* JADX INFO: renamed from: Font-Ej4NQ78, reason: not valid java name */
    public static final Font m5250FontEj4NQ78(File file, FontWeight fontWeight, int i10, FontVariation.Settings settings) {
        return new AndroidFileFont(file, fontWeight, i10, settings, null);
    }

    /* JADX INFO: renamed from: Font-Ej4NQ78$default, reason: not valid java name */
    public static /* synthetic */ Font m5252FontEj4NQ78$default(File file, FontWeight fontWeight, int i10, FontVariation.Settings settings, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        if ((i11 & 4) != 0) {
            i10 = FontStyle.INSTANCE.m5309getNormal_LCdwA();
        }
        if ((i11 & 8) != 0) {
            settings = FontVariation.INSTANCE.m5324Settings6EWAqTQ(fontWeight, i10, new FontVariation.Setting[0]);
        }
        return m5250FontEj4NQ78(file, fontWeight, i10, settings);
    }

    /* JADX INFO: renamed from: Font-MuC2MFs, reason: not valid java name */
    public static final Font m5253FontMuC2MFs(String str, AssetManager assetManager, FontWeight fontWeight, int i10, FontVariation.Settings settings) {
        return new AndroidAssetFont(assetManager, str, fontWeight, i10, settings, null);
    }

    /* JADX INFO: renamed from: Font-MuC2MFs$default, reason: not valid java name */
    public static /* synthetic */ Font m5254FontMuC2MFs$default(String str, AssetManager assetManager, FontWeight fontWeight, int i10, FontVariation.Settings settings, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        if ((i11 & 8) != 0) {
            i10 = FontStyle.INSTANCE.m5309getNormal_LCdwA();
        }
        if ((i11 & 16) != 0) {
            settings = FontVariation.INSTANCE.m5324Settings6EWAqTQ(fontWeight, i10, new FontVariation.Setting[0]);
        }
        return m5253FontMuC2MFs(str, assetManager, fontWeight, i10, settings);
    }

    private static final void generateAndroidFontKtForApiCompatibility() {
    }

    /* JADX INFO: renamed from: Font-Ej4NQ78, reason: not valid java name */
    public static final Font m5249FontEj4NQ78(ParcelFileDescriptor parcelFileDescriptor, FontWeight fontWeight, int i10, FontVariation.Settings settings) {
        return new AndroidFileDescriptorFont(parcelFileDescriptor, fontWeight, i10, settings, null);
    }

    /* JADX INFO: renamed from: Font-Ej4NQ78$default, reason: not valid java name */
    public static /* synthetic */ Font m5251FontEj4NQ78$default(ParcelFileDescriptor parcelFileDescriptor, FontWeight fontWeight, int i10, FontVariation.Settings settings, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            fontWeight = FontWeight.INSTANCE.getNormal();
        }
        if ((i11 & 4) != 0) {
            i10 = FontStyle.INSTANCE.m5309getNormal_LCdwA();
        }
        if ((i11 & 8) != 0) {
            settings = FontVariation.INSTANCE.m5324Settings6EWAqTQ(fontWeight, i10, new FontVariation.Setting[0]);
        }
        return m5249FontEj4NQ78(parcelFileDescriptor, fontWeight, i10, settings);
    }
}
