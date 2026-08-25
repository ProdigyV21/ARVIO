package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.FilterQuality;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@DrawScopeMarker
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u0000 n2\u00020\u0001:\u0001nJj\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0003\u0010\r\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014Jj\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0003\u0010\r\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019JX\u0010!\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 JX\u0010!\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\b\"\u0010#JN\u0010(\u001a\u00020\u00122\u0006\u0010%\u001a\u00020$2\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\b&\u0010'Jl\u0010(\u001a\u00020\u00122\u0006\u0010%\u001a\u00020$2\b\b\u0002\u0010*\u001a\u00020)2\b\b\u0002\u0010,\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020)2\b\b\u0002\u0010.\u001a\u00020+2\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H'ø\u0001\u0000¢\u0006\u0004\b/\u00100Jv\u0010(\u001a\u00020\u00122\u0006\u0010%\u001a\u00020$2\b\b\u0002\u0010*\u001a\u00020)2\b\b\u0002\u0010,\u001a\u00020+2\b\b\u0002\u0010-\u001a\u00020)2\b\b\u0002\u0010.\u001a\u00020+2\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u00102\u001a\u000201H\u0016ø\u0001\u0000¢\u0006\u0004\b3\u00104Jb\u00109\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u00106\u001a\u0002052\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\b7\u00108Jb\u00109\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u00106\u001a\u0002052\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0003\u0010\r\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\b:\u0010;JX\u0010@\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010<\u001a\u00020\u00072\b\b\u0002\u0010=\u001a\u00020\u00042\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\b>\u0010?JX\u0010@\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010<\u001a\u00020\u00072\b\b\u0002\u0010=\u001a\u00020\u00042\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\bA\u0010BJX\u0010D\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\bC\u0010 JX\u0010D\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\bE\u0010#Jp\u0010L\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010F\u001a\u00020\u00072\u0006\u0010G\u001a\u00020\u00072\u0006\u0010I\u001a\u00020H2\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\bJ\u0010KJp\u0010L\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010F\u001a\u00020\u00072\u0006\u0010G\u001a\u00020\u00072\u0006\u0010I\u001a\u00020H2\b\b\u0002\u0010\u001a\u001a\u00020\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\bM\u0010NJL\u0010S\u001a\u00020\u00122\u0006\u0010P\u001a\u00020O2\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\bQ\u0010RJL\u0010S\u001a\u00020\u00122\u0006\u0010P\u001a\u00020O2\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0003\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\bT\u0010UJp\u0010\\\u001a\u00020\u00122\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00040V2\u0006\u0010Y\u001a\u00020X2\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0003\u0010\r\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\bZ\u0010[Jp\u0010\\\u001a\u00020\u00122\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00040V2\u0006\u0010Y\u001a\u00020X2\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\b\u0003\u0010\r\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H&ø\u0001\u0000¢\u0006\u0004\b]\u0010^J\u001e\u0010b\u001a\u00020\u001b*\u00020\u001b2\u0006\u0010_\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b`\u0010aR\u0014\u0010f\u001a\u00020c8&X¦\u0004¢\u0006\u0006\u001a\u0004\bd\u0010eR\u001a\u0010=\u001a\u00020\u00048VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bg\u0010hR\u001a\u0010\u001c\u001a\u00020\u001b8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bi\u0010hR\u0014\u0010m\u001a\u00020j8&X¦\u0004¢\u0006\u0006\u001a\u0004\bk\u0010lø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006oÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/graphics/Brush;", "brush", "Landroidx/compose/ui/geometry/Offset;", TtmlNode.START, TtmlNode.END, "", "strokeWidth", "Landroidx/compose/ui/graphics/StrokeCap;", "cap", "Landroidx/compose/ui/graphics/PathEffect;", "pathEffect", "alpha", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "Landroidx/compose/ui/graphics/BlendMode;", "blendMode", "Lx6/t0;", "drawLine-1RTmtNc", "(Landroidx/compose/ui/graphics/Brush;JJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawLine", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "drawLine-NGM6Ib0", "(JJJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "topLeft", "Landroidx/compose/ui/geometry/Size;", ContentDisposition.Parameters.Size, "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "style", "drawRect-AsUm42w", "(Landroidx/compose/ui/graphics/Brush;JJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRect", "drawRect-n-J9OG0", "(JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/graphics/ImageBitmap;", "image", "drawImage-gbVJVH8", "(Landroidx/compose/ui/graphics/ImageBitmap;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawImage", "Landroidx/compose/ui/unit/IntOffset;", "srcOffset", "Landroidx/compose/ui/unit/IntSize;", "srcSize", "dstOffset", "dstSize", "drawImage-9jGpkUE", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "drawImage-AZ2fEMs", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;II)V", "Landroidx/compose/ui/geometry/CornerRadius;", "cornerRadius", "drawRoundRect-ZuiqVtQ", "(Landroidx/compose/ui/graphics/Brush;JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRoundRect", "drawRoundRect-u-Aw5IA", "(JJJJLandroidx/compose/ui/graphics/drawscope/DrawStyle;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "radius", TtmlNode.CENTER, "drawCircle-V9BoPsw", "(Landroidx/compose/ui/graphics/Brush;FJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle", "drawCircle-VaOC9Bg", "(JFJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval-AsUm42w", "drawOval", "drawOval-n-J9OG0", "startAngle", "sweepAngle", "", "useCenter", "drawArc-illE91I", "(Landroidx/compose/ui/graphics/Brush;FFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawArc", "drawArc-yD3GUKo", "(JFFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "Landroidx/compose/ui/graphics/Path;", "path", "drawPath-LG529CI", "(Landroidx/compose/ui/graphics/Path;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath", "drawPath-GBMwjPU", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "", "points", "Landroidx/compose/ui/graphics/PointMode;", "pointMode", "drawPoints-F8ZwMP8", "(Ljava/util/List;IJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints", "drawPoints-Gsft0Ws", "(Ljava/util/List;ILandroidx/compose/ui/graphics/Brush;FILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "offset", "offsetSize-PENXr5M", "(JJ)J", "offsetSize", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "getDrawContext", "()Landroidx/compose/ui/graphics/drawscope/DrawContext;", "drawContext", "getCenter-F1C5BW0", "()J", "getSize-NH-jbRc", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface DrawScope extends Density {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\n\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/DrawScope$Companion;", "", "()V", "DefaultBlendMode", "Landroidx/compose/ui/graphics/BlendMode;", "getDefaultBlendMode-0nO6VwU", "()I", "I", "DefaultFilterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "getDefaultFilterQuality-f-v9h1I", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final int DefaultBlendMode = BlendMode.INSTANCE.m3420getSrcOver0nO6VwU();
        private static final int DefaultFilterQuality = FilterQuality.INSTANCE.m3572getLowfv9h1I();

        private Companion() {
        }

        /* JADX INFO: renamed from: getDefaultBlendMode-0nO6VwU, reason: not valid java name */
        public final int m3946getDefaultBlendMode0nO6VwU() {
            return DefaultBlendMode;
        }

        /* JADX INFO: renamed from: getDefaultFilterQuality-f-v9h1I, reason: not valid java name */
        public final int m3947getDefaultFilterQualityfv9h1I() {
            return DefaultFilterQuality;
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        /* JADX INFO: renamed from: drawImage-AZ2fEMs, reason: not valid java name */
        public static void m3953drawImageAZ2fEMs(DrawScope drawScope, ImageBitmap imageBitmap, long j10, long j11, long j12, long j13, float f10, DrawStyle drawStyle, ColorFilter colorFilter, int i10, int i11) {
            c.a(drawScope, imageBitmap, j10, j11, j12, j13, f10, drawStyle, colorFilter, i10, i11);
        }

        @Deprecated
        /* JADX INFO: renamed from: getCenter-F1C5BW0, reason: not valid java name */
        public static long m3968getCenterF1C5BW0(DrawScope drawScope) {
            return c.b(drawScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
        public static long m3969getSizeNHjbRc(DrawScope drawScope) {
            return c.c(drawScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m3970roundToPxR2X_6o(DrawScope drawScope, long j10) {
            return androidx.compose.ui.unit.a.a(drawScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m3971roundToPx0680j_4(DrawScope drawScope, float f10) {
            return androidx.compose.ui.unit.a.b(drawScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m3972toDpGaN1DYA(DrawScope drawScope, long j10) {
            return androidx.compose.ui.unit.b.a(drawScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m3973toDpu2uoSUM(DrawScope drawScope, float f10) {
            return androidx.compose.ui.unit.a.c(drawScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m3975toDpSizekrfVVM(DrawScope drawScope, long j10) {
            return androidx.compose.ui.unit.a.e(drawScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m3976toPxR2X_6o(DrawScope drawScope, long j10) {
            return androidx.compose.ui.unit.a.f(drawScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m3977toPx0680j_4(DrawScope drawScope, float f10) {
            return androidx.compose.ui.unit.a.g(drawScope, f10);
        }

        @Deprecated
        public static Rect toRect(DrawScope drawScope, DpRect dpRect) {
            return androidx.compose.ui.unit.a.h(drawScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m3978toSizeXkaWNTQ(DrawScope drawScope, long j10) {
            return androidx.compose.ui.unit.a.i(drawScope, j10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m3979toSp0xMU5do(DrawScope drawScope, float f10) {
            return androidx.compose.ui.unit.b.b(drawScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m3980toSpkPz2Gy4(DrawScope drawScope, float f10) {
            return androidx.compose.ui.unit.a.j(drawScope, f10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m3974toDpu2uoSUM(DrawScope drawScope, int i10) {
            return androidx.compose.ui.unit.a.d(drawScope, i10);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m3981toSpkPz2Gy4(DrawScope drawScope, int i10) {
            return androidx.compose.ui.unit.a.k(drawScope, i10);
        }
    }

    /* JADX INFO: renamed from: drawArc-illE91I */
    void mo3896drawArcillE91I(Brush brush, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawArc-yD3GUKo */
    void mo3897drawArcyD3GUKo(long color, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawCircle-V9BoPsw */
    void mo3898drawCircleV9BoPsw(Brush brush, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawCircle-VaOC9Bg */
    void mo3899drawCircleVaOC9Bg(long color, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    @e
    /* JADX INFO: renamed from: drawImage-9jGpkUE */
    /* synthetic */ void mo3900drawImage9jGpkUE(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawImage-AZ2fEMs */
    void mo3901drawImageAZ2fEMs(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode, int filterQuality);

    /* JADX INFO: renamed from: drawImage-gbVJVH8 */
    void mo3902drawImagegbVJVH8(ImageBitmap image, long topLeft, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawLine-1RTmtNc */
    void mo3903drawLine1RTmtNc(Brush brush, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawLine-NGM6Ib0 */
    void mo3904drawLineNGM6Ib0(long color, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawOval-AsUm42w */
    void mo3905drawOvalAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawOval-n-J9OG0 */
    void mo3906drawOvalnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPath-GBMwjPU */
    void mo3907drawPathGBMwjPU(Path path, Brush brush, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPath-LG529CI */
    void mo3908drawPathLG529CI(Path path, long color, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPoints-F8ZwMP8 */
    void mo3909drawPointsF8ZwMP8(List<Offset> points, int pointMode, long color, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawPoints-Gsft0Ws */
    void mo3910drawPointsGsft0Ws(List<Offset> points, int pointMode, Brush brush, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRect-AsUm42w */
    void mo3911drawRectAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRect-n-J9OG0 */
    void mo3912drawRectnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRoundRect-ZuiqVtQ */
    void mo3913drawRoundRectZuiqVtQ(Brush brush, long topLeft, long size, long cornerRadius, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: drawRoundRect-u-Aw5IA */
    void mo3914drawRoundRectuAw5IA(long color, long topLeft, long size, long cornerRadius, DrawStyle style, float alpha, ColorFilter colorFilter, int blendMode);

    /* JADX INFO: renamed from: getCenter-F1C5BW0 */
    long mo3915getCenterF1C5BW0();

    DrawContext getDrawContext();

    LayoutDirection getLayoutDirection();

    /* JADX INFO: renamed from: getSize-NH-jbRc */
    long mo3916getSizeNHjbRc();
}
