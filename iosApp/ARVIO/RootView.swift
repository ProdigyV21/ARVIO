import SwiftUI

enum ArvioTab: String, CaseIterable {
    case home = "Home"
    case movies = "Movies"
    case series = "Series"
    case watchlist = "Watchlist"
    case search = "Search"
    case settings = "Settings"
}

struct RootView: View {
    @State private var selectedTab: ArvioTab = .home

    var body: some View {
        ZStack {
            ArvioTheme.background.ignoresSafeArea()

            LinearGradient(
                colors: [Color(hex: "#0b1b2a").opacity(0.65), Color.black.opacity(0.1), Color.black.opacity(0.72)],
                startPoint: .topTrailing,
                endPoint: .bottom
            )
            .ignoresSafeArea()

            VStack(spacing: 0) {
                TopNavigation(selectedTab: $selectedTab)

                Group {
                    switch selectedTab {
                    case .home:
                        HomeView()
                    case .movies:
                        CatalogView(title: "Movies", subtitle: "Curated cinema and recent releases.", items: featuredItems.filter { $0.kind == .movie })
                    case .series:
                        CatalogView(title: "Series", subtitle: "Continue seasons and discover premium TV.", items: featuredItems.filter { $0.kind == .series })
                    case .watchlist:
                        WatchlistView()
                    case .search:
                        SearchView()
                    case .settings:
                        SettingsView()
                    }
                }
                .frame(maxWidth: .infinity, maxHeight: .infinity)
            }
        }
    }
}

struct TopNavigation: View {
    @Binding var selectedTab: ArvioTab

    var body: some View {
        HStack(spacing: 20) {
            BrandMark()

            ForEach(ArvioTab.allCases, id: \.self) { tab in
                Button {
                    selectedTab = tab
                } label: {
                    Text(tab.rawValue)
                        .font(.system(size: 16, weight: selectedTab == tab ? .semibold : .medium))
                        .foregroundStyle(selectedTab == tab ? ArvioTheme.textPrimary : ArvioTheme.textSecondary)
                        .padding(.horizontal, 16)
                        .padding(.vertical, 10)
                        .background(
                            RoundedRectangle(cornerRadius: 8)
                                .fill(selectedTab == tab ? ArvioTheme.gold.opacity(0.16) : Color.clear)
                        )
                        .overlay(
                            RoundedRectangle(cornerRadius: 8)
                                .stroke(selectedTab == tab ? ArvioTheme.gold : Color.clear, lineWidth: 1)
                        )
                }
                .buttonStyle(.plain)
            }

            Spacer()

            Text("ARVIO")
                .font(.system(size: 15, weight: .semibold))
                .foregroundStyle(ArvioTheme.textSecondary)
        }
        .padding(.horizontal, 28)
        .padding(.top, 14)
        .padding(.bottom, 10)
    }
}

struct BrandMark: View {
    var body: some View {
        Image("ARVIOAppIcon")
            .resizable()
            .scaledToFit()
            .frame(width: 42, height: 42)
            .clipShape(RoundedRectangle(cornerRadius: 8))
    }
}
