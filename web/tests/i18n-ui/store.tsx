import {createContext, useContext, useState, type ReactNode} from 'react';
import {app as library, titles} from '../library-ui/stubs';
export {traktClient} from '../library-ui/stubs';
export const defaultSettings = (window as any).fixtureDefaults;
export const authClient = {session:null};
const Context = createContext<any>(null);
export const useApp = () => useContext(Context);
export function FixtureStore({children}: {children:ReactNode}) {
  const [settings, setSettings] = useState({...defaultSettings, ...library.settings, language:'es-ES'});
  const [section, setSection] = useState('watchlist');
  const [query, setQuery] = useState('');
  const value = {...library, settings, section, setSection, query, setQuery, searchState:'idle', results:[],
    view:'home', selected:null, closeDetails:()=>{}, activeProfile:{id:'fixture',name:'Home'}, avatarImages:{},
    auth:null, addons:[], profiles:[], playlists:[], catalogConfigs:[], watchlist:titles, toast:null, busy:'',
    settingsSyncState:'local', trackingPreferences:{watchlistReadMode:'both',continueWatchingReadMode:'both',writeMode:'both'},
    updateSettings:(patch:any)=>setSettings((current:any)=>({...current,...patch})),
    setToast:()=>{}, refreshData:async()=>{}, switchProfile:()=>{}, updateTrackingPreferences:()=>{},
    saveProfile:async()=>{}, deleteProfile:async()=>{}, setView:()=>{}, signOut:async()=>{},
    tvSnapshot:{channels:[],favoriteChannels:[],favoriteGroups:[],hiddenGroups:[],groupOrder:[],grouped:{},nowNext:{}},
    updateAvailable:false
  };
  (window as any).setFixtureLanguage = (language:string)=>setSettings((current:any)=>({...current,language}));
  return <Context.Provider value={value}>{children}</Context.Provider>;
}
