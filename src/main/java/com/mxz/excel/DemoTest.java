
package com.mxz.excel;

import com.alibaba.excel.EasyExcel;
import com.mxz.branchBank.model.TbWechatExtraBranchBank;

import java.io.File;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/07/01 10:25
 */
public class DemoTest {

    public static void main(String[] args) {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        System.out.println(TestFileUtil.getPath());
        // 写法1：
        String fileName = TestFileUtil.getPath() + "bank" + File.separator + "《开户银行全称（含支行）对照表》-2020.03.10.xlsx";
        System.out.println(fileName);
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }

}
