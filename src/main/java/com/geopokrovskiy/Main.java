package com.geopokrovskiy;

import com.geopokrovskiy.model.Developer;
import com.geopokrovskiy.repository.DeveloperRepository;
import com.geopokrovskiy.repository.hibernate.DeveloperRepositoryImpl;
import com.geopokrovskiy.view.MainView;

public class Main {
    private static final MainView mainVew = new MainView();
    public static void main(String[] args) {
        mainVew.start();
    }
}