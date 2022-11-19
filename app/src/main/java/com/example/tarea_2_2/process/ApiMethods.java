package com.example.tarea_2_2.process;

public class ApiMethods {
    public static final String domainName = "jsonplaceholder.typicode.com/";
    public static  String subDomain1 = "posts/";
    public static  String postNum = "";

    public static final String apiRead1 = "https://" + domainName + subDomain1;
    public static final String apiRead2 = "https://" + domainName + subDomain1 + postNum;

    public static String getApi(String _postNum){
        String apiRead = "https://" + domainName + subDomain1 + _postNum;
        return apiRead;
    }
}
