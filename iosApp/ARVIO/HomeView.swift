import SwiftUI

struct HomeView: View {
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 28) {
                HeroSection(item: featuredItems[0])
                MediaRail(title: "Continue Watching", items: continueWatchingItems)
                MediaRail(title: "Featured", items: featuredItems)
            }
            .padding(.horizontal, 28)
            .padding(.bottom, 36)
        }
    }
}

struct HeroSection: View {
    let item: MediaItem

    var body: some View {
        ZStack(alignment: .bottomLeading) {
            PosterBackdrop(item: item)
                .frame(height: 360)
                .clipShape(RoundedRectangle(cornerRadius: 8))

            LinearGradient(colors: [Color.black.opacity(0.0), Color.black.opacity(0.78)], startPoint: .center, endPoint: .bottom)
                .clipShape(RoundedRectangle(cornerRadius: 8))

            VStack(alignment: .leading, spacing: 12) {
                Text(item.title)
                    .font(.system(size: 42, weight: .bold))
                    .foregroundStyle(ArvioTheme.textPrimary)

                Text("\(item.subtitle)  •  \(item.year)  •  \(item.duration)")
                    .font(.system(size: 17, weight: .medium))
                    .foregroundStyle(ArvioTheme.textSecondary)

                Text("A premium media hub experience for browsing, tracking and continuing your library across screens.")
                    .font(.system(size: 17))
                    .foregroundStyle(ArvioTheme.textSecondary)
                    .lineLimit(2)
                    .frame(maxWidth: 580, alignment: .leading)

                HStack(spacing: 12) {
                    PrimaryButton(title: "Open Details")
                    SecondaryButton(title: "Add to Watchlist")
                }
                .padding(.top, 6)
            }
            .padding(28)
        }
    }
}

struct MediaRail: View {
    let title: String
    let items: [MediaItem]

    var body: some View {
        VStack(alignment: .leading, spacing: 14) {
            Text(title)
                .font(.system(size: 22, weight: .bold))
                .foregroundStyle(ArvioTheme.textPrimary)

            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 14) {
                    ForEach(items) { item in
                        MediaCard(item: item)
                    }
                }
            }
        }
    }
}

struct CatalogView: View {
    let title: String
    let subtitle: String
    let items: [MediaItem]

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 18) {
                Text(title)
                    .font(.system(size: 38, weight: .bold))
                    .foregroundStyle(ArvioTheme.textPrimary)
                Text(subtitle)
                    .font(.system(size: 17))
                    .foregroundStyle(ArvioTheme.textSecondary)

                LazyVGrid(columns: [GridItem(.adaptive(minimum: 210), spacing: 16)], spacing: 16) {
                    ForEach(items.isEmpty ? featuredItems : items) { item in
                        MediaCard(item: item)
                    }
                }
                .padding(.top, 8)
            }
            .padding(28)
        }
    }
}
