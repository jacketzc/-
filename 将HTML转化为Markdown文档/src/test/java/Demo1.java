import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import com.jac.util.KeyWords;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class Demo1 {
    @Test
    public void test1() {
        String str = "</b></td><td><i>2020/7/25 9:46</i></td></tr>";
        int firstIndex = str.indexOf('i');
        int lastIndex = StrUtil.lastIndexOfIgnoreCase(str,"i");
        System.out.println(firstIndex);
        System.out.println(lastIndex);
        String sub = StrUtil.sub(str, firstIndex + 2, lastIndex - 2);
        System.out.println(sub);

    }

    @Test
    public void test2() {
        File input = new File(KeyWords.INPUTPATH , "123.html");

        FileReader fileReader = new FileReader(input);

        List<String> list = fileReader.readLines();

        list.stream().filter(line->line.contains("<h1>")).filter(line->line.contains("<span>")).forEach(System.out::println);
    }

    @Test
    public void test3() {
        File input = new File("C:\\Users\\jacke\\Desktop\\blog");
        //getParent(File file, int level)
        List<File> files = FileUtil.loopFiles(input);
        for (File file : files) {
            System.out.println(FileUtil.getParent(file,1).getName());
            System.out.println(file);
        }

    }

    /**
     *
     * @param dir 当前处理的文件（或文件夹）
     * @param curLocation 当前处理文件（或文件夹）的父类文件夹名称
     * @param output
     */
    private void visitAllDirAndFiles(File dir,String curLocation,String output) {


    }

}
