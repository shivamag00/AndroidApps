package com.travelogue.repository;

import com.example.travelogue.Blog;

import java.util.List;

public interface DataFromDatabaseCallback {
    void onSuccess(List<Blog> blogList);
}