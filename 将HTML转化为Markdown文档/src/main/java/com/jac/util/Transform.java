package com.jac.util;


import java.util.List;

/**
 * 存放将一个HTML文件转化为Markdown文件的所有方法
 */
public interface Transform {
    //读取文件，将HTML文件的内容存放到一个数组中
    List<String> HTMLToArrays();

    //对字符串数组进行提炼，只保留需要的内容
    List<String> collectContext(List<String> list);

    //将数组写入文件
    void ArraysToMarkdown(List<String> list);


}
