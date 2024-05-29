package com.syscom.futures;

import java.nio.charset.StandardCharsets;

public class CharsetTool {
    public String big52iso(String s){
        try {
                byte[] bytes = s.getBytes("BIG5");
                return new String(bytes, StandardCharsets.ISO_8859_1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public String iso2big5(String s){
        try {
            byte[] bytes = s.getBytes(StandardCharsets.ISO_8859_1);
            return new String(bytes, "BIG5");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public String utf82iso(String s){
        try {
            byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
            return new String(bytes, StandardCharsets.ISO_8859_1);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public boolean isEncoding(String s, String encode){
        try {
            if (s.equals(new String(s.getBytes(), encode)))
                return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
