package com.jac.main;

import com.jac.util.KeyWords;
import com.jac.util.TransformImpl;

import java.io.File;
import java.util.List;


public class HTMLToMd {
    public static void main(String[] args)  {
        File input = new File("C:\\Users\\jacke\\Desktop\\blog");

        TransformImpl transform = new TransformImpl();
        transform.AllHTMLToMd(input);


//        transform.HTMLToMd(input);
    }

    public void HToMd() {

    }
}
