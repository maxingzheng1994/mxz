package com.mxz.service.rss.controller;

import org.checkerframework.checker.units.qual.A;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/08/27 19:22
 */
public class Father {
    public int b;

    public Father() {
        System.out.println(((Son)this).c);
    }
}
