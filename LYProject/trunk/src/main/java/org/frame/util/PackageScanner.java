package org.frame.util;


import java.io.IOException;
import java.util.List;

/**
 * 作者：Li.Wei
 * 时间：2018/8/10 9:47
 * 描述：
 */
public interface PackageScanner {
    public List<String> getFullyQualifiedClassNameList() throws IOException;
}
