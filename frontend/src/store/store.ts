import { configureStore, getDefaultMiddleware } from '@reduxjs/toolkit';

// slices
// Redux Persist
import { persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import rootReducer from '@/store/rootReducer';

const persistConfig = {
  key: 'root',
  storage,
  whiteList: ['userSlice'],
};

const persistedReducer = persistReducer(persistConfig, rootReducer);
const store = configureStore({
  reducer: persistedReducer,
  middleware: getDefaultMiddleware({
    serializableCheck: false,
    // {ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER]},
    devTools: process.env.NODE_ENV !== 'production',
  }),
});

export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;

export default store;