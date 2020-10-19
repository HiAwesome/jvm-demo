<%@ page import="java.lang.*" %>
<%@ page import="java.io.*" %>
<%@ page import="com.moqi.jvm.ch09.*" %>
<%
    InputStream is = new FileInputStream("/Users/moqi/Code/jvm-demo/src/main/java/com/moqi/jvm/ch09/A07TestClass.class");
    byte[] b = new byte[is.available()];
    is.read(b);
    is.close();

    out.println("<textarea style='width:1000;height=800'>");
    out.println(A06JavaClassExecutor.execute(b));
    out.println("</textarea>");
%>
