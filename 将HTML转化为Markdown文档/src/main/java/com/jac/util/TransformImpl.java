package com.jac.util;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.*;

public class TransformImpl{

    //存放该文件的关键字，比如标题、创建时间、分类
    public static Map<String, String> keyWords = new HashMap<>();


    //将某个文件夹下（包括子目录）的所有文件都转化为Markdown文件
    public void AllHTMLToMd(File input) {
        List<File> files = FileUtil.loopFiles(input);
        for (File file : files) {
            //获取最终输出路径的父文件夹路径
            String outputParent = getOutputParent(file);
            System.out.println(file);
            System.out.println("输出父路径："+outputParent);
            //如果是HTML文件，则进行相应处理
            if (FileUtil.pathEndsWith(file, "html")) {
                System.out.println("这是一个html文件");
                String output = outputParent + suffixTransform(file.getName());
                System.out.println("输出目录："+output);
                String parentDirName = FileUtil.getParent(file, 1).getName();
                HTMLToMd(file,output,parentDirName,FileUtil.getPrefix(file));
            } else {
                //如果是一个普通文件，则拷贝到相应的目录
                FileUtil.copy(file, new File(outputParent,file.getName()), true);
            }
        }
    }

    //获取当前文件输出的路径(默认输出位置+父文件夹的名称+当前文件的名称)
    public String getOutputParent(File file) {
        return KeyWords.OUTPUTPATH+FileUtil.getParent(file, 1).getName()+ "\\";
    }

    //将HTML后缀换成md后缀
    public String suffixTransform(String suffix) {
        return StrUtil.addSuffixIfNot(StrUtil.removeSuffix(suffix, "html"), "md");
    }


    /**
     * 将单个文件由HTML转换为Markdown文件
     * @param input 需要处理的文件的路径
     * @param output 输出文件的路径
     * @param parentDirName 获取父级目录名称
     * @param title 标题
     */
    public void HTMLToMd(File input,String output,String parentDirName,String title) {
        List<String> lineList = HTMLToArrays(input);
        List<String> usefulInfo = collectContext(lineList);
        List<String> finalList = generateFinalList(usefulInfo,parentDirName,title);
        ArraysToMarkdown(finalList,output);
    }

    public List<String> HTMLToArrays(File input) {

//        File input = new File(KeyWords.INPUTPATH , "123.html");

        FileReader fileReader = new FileReader(input);


        return fileReader.readLines();
    }
    //收集有用的信息
    public List<String> collectContext(List<String> list) {
        List<String> res = new ArrayList<>();
        for (String line : list) {
            if (line.contains("<h1>")||line.contains("<span>")) {
                res.add(line);
                continue;
            }
            if(line.contains("<i>") ){
                int firstIndex = line.indexOf('i');
                int lastIndex = StrUtil.lastIndexOfIgnoreCase(line,"i");
                String sub = StrUtil.sub(line, firstIndex + 2, lastIndex - 2);
                keyWords.put("date", sub);
            }

        }

        return res;
    }

    //生成最终的list
    public List<String> generateFinalList(List<String> list, String parentDirName, String title) {
        List<String> finalList = new ArrayList<>();
        finalList.add("---");
        finalList.add("title: "+title);
        finalList.add("date: " + keyWords.get("date"));
        finalList.add("categories: ");
        finalList.add("- " + parentDirName);
        finalList.add("tags: ");
        finalList.add("- " + parentDirName);
        finalList.add("---");
        finalList.addAll(list);
        return finalList;
    }

    //将最终的list写入文件
    public void ArraysToMarkdown(List<String> list,String output) {
//        File output = new File(KeyWords.OUTPUTPATH , "123.md");

        FileWriter fileWriter = new FileWriter(output);

        fileWriter.writeLines(list);
    }
}
