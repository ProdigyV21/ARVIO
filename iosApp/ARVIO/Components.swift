import SwiftUI

struct PosterBackdrop: View {
    let item: MediaItem

    var body: some View {
        LinearGradient(
            colors: item.palette.map(Color.init(hex:)),
            startPoint: .topLeading,
            endPoint: .bottomTrailing
        )
        .overlay(
            RadialGradient(colors: [Color.white.opacity(0.24), Color.clear], center: .topTrailing, startRadius: 10, endRadius: 320)
        )
        .overlay(alignment: .center) {
            Text(item.title.uppercased())
                .font(.system(size: 27, weight: .bold, design: .serif))
                .foregroundStyle(Color.white.opacity(0.86))
                .multilineTextAlignment(.center)
                .padding(18)
        }
    }
}

struct MediaCard: View {
    let item: MediaItem

    var body: some View {
        VStack(alignment: .leading, spacing: 10) {
            PosterBackdrop(item: item)
                .frame(width: 210, height: 118)
                .clipShape(RoundedRectangle(cornerRadius: 8))
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(ArvioTheme.border, lineWidth: 1)
                )

            Text(item.title)
                .font(.system(size: 16, weight: .semibold))
                .foregroundStyle(ArvioTheme.textPrimary)
                .lineLimit(1)

            Text(item.subtitle)
                .font(.system(size: 13, weight: .medium))
                .foregroundStyle(ArvioTheme.textTertiary)
                .lineLimit(1)

            if item.progress > 0 {
                ProgressView(value: item.progress)
                    .tint(ArvioTheme.gold)
                    .frame(width: 210)
            }
        }
        .frame(width: 210, alignment: .leading)
    }
}

struct PrimaryButton: View {
    let title: String

    var body: some View {
        Text(title)
            .font(.system(size: 15, weight: .bold))
            .foregroundStyle(Color.black)
            .padding(.horizontal, 18)
            .padding(.vertical, 13)
            .background(RoundedRectangle(cornerRadius: 8).fill(ArvioTheme.gold))
    }
}

struct SecondaryButton: View {
    let title: String

    var body: some View {
        Text(title)
            .font(.system(size: 15, weight: .semibold))
            .foregroundStyle(ArvioTheme.textPrimary)
            .padding(.horizontal, 18)
            .padding(.vertical, 13)
            .background(RoundedRectangle(cornerRadius: 8).fill(ArvioTheme.panel))
            .overlay(RoundedRectangle(cornerRadius: 8).stroke(ArvioTheme.border, lineWidth: 1))
    }
}
