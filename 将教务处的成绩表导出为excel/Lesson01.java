package com.jacketzc;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lesson01 {
    
    @Test
    public void test3() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\jacke\\Desktop\\test.txt"));
        char[] c=new char[1000];
        int read = 0;
        StringBuilder builder = new StringBuilder();
        while ((read=br.read(c))!=-1){
            builder.append(c,0,read);
        }
        String s=builder.toString();
        String regex="(?<=<td>)(.*?)(?=</td>)";
//        String regex="<td>";
//        String regex="</td>";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(s);
        List<String> row=new ArrayList<>();
        List<List> rows=new ArrayList<>();
        String str;
        int i=0;
        while (matcher.find()){
            /*System.out.print(matcher.group()+" ");
            i++;
            if (i%10==0)
                System.out.println();*/
            str=matcher.group();
            if(str.equals("&nbsp;"))
                str="";
            row.add(str);
            i++;
            if(i%10==0)
            {
                rows.add(row);
                row=new ArrayList<>();
            }
        }

        /*for(List list:rows){
            for(Object obj:list)
                System.out.print(obj+" ");
            System.out.println();
        }*/
        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\jacke\\Desktop\\成绩表.xlsx");
        writer.write(rows);
        writer.flush();
        writer.close();
    }
    
}
