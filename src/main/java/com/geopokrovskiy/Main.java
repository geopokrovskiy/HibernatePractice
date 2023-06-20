package com.geopokrovskiy;

import com.geopokrovskiy.DAO.DAO;
import com.geopokrovskiy.model.Developer;
import com.geopokrovskiy.model.Speciality;
import com.geopokrovskiy.model.Status;
import com.geopokrovskiy.view.MainView;

import java.util.List;

public class Main {
    private static final MainView mainVew = new MainView();
    public static void main(String[] args) {
       mainVew.start();
    }
}