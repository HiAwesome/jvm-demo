package com.moqi.jvm.ch10;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementScanner8;
import java.util.Set;

/**
 * 自定义注册处理器
 *
 * @author moqi On 10/19/20 17:31
 */
// 可以用 * 表示支持所有的 Annotations
@SupportedAnnotationTypes("*")
// 只支持 JDK 8 的 Java 代码
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class A04NameCheckProcessor extends AbstractProcessor {

    private A03NameCheck nameCheck;

    /**
     * 初始化名称检查插件
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameCheck = new A03NameCheck(processingEnv);
    }

    /**
     * 对输入的语法树的各个环节进行名称检查
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()) {
            for (Element element : roundEnv.getRootElements()) {
                nameCheck.checkNames(element);
            }
        }
        return false;
    }

}
