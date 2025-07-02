package com.model.user.management;

import com.model.user.datastorage.UsersDataStorageJsonStrategy;

public final class UsersManagerSingleton {

    private UsersManagerSingleton() {
    }

    private static class LazyHolder {
        private static final UsersManager SINGLETON = 
                new UsersManagerImpl(new UsersDataStorageJsonStrategy());
    }

    public static UsersManager getInstance() {
        return LazyHolder.SINGLETON;
    }
}
