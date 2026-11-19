import React, { useEffect } from 'react';

function marker(name: string) {
  return function FixtureComponent() {
    useEffect(() => { (window as unknown as { __shellMounts: string[] }).__shellMounts.push(name); }, []);
    return <div data-testid={`shell-${name}`}>{name === 'login' ? 'Fixture Cloud sign-in' : `Fixture ${name}`}</div>;
  };
}
export const LoginScreen = marker('login');
export const ProfileSelectionScreen = marker('profiles');
export const HomeScreen = marker('home');
export const AddonsScreen = marker('addons');
export const DetailsDrawer = marker('details');
export const LiveTvScreen = marker('livetv');
export const PlayerOverlay = marker('player');
export const SearchScreen = marker('search');
export const SettingsScreen = marker('settings');
export const WatchlistScreen = marker('watchlist');
export const BackHandler = marker('back-handler');
export const ExternalPlaybackPrompt = marker('external-player');
export const MediaContextMenu = marker('media-menu');
export const NoAddonsPrompt = marker('addons-prompt');
export const Toast = marker('toast');
export const TopNav = marker('navigation');
export const PremiumUsage = marker('usage');
export const PartnerLinkHandler = marker('partner-link');
export function installTvNav() {}
export function installGamepadNav() {}
export function focusAfterViewChange() { return () => {}; }
