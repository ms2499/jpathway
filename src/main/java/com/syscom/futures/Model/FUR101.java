package com.syscom.futures.Model;

import lombok.Data;

import java.nio.charset.StandardCharsets;

@Data
public class FUR101 {
    public int rows = 12;
    public String wr_key;
    public String wr_first_key;
    //WR-SHARE-DATA BEGIN
    public String[] wr_com_id = new String[rows];
    public String[] wr_com_name = new String[rows];
    public String[] wr_min_price = new String[rows];
    public String[] wr_up_dn = new String[rows];
    public String[] wr_value = new String[rows];
    public String[] wr_rate = new String[rows];
    public String[] wr_stock = new String[rows];
    //WR-SHARE-DATA END
    public String[] wr_fee = new String[rows];
    public String[] wr_up_fee = new String[rows];
    public String[] wr_dn_fee = new String[rows];
    public String[] wr_range = new String[rows];

    public void setValue(byte[] reply, int offset) {
        try {
            byte[] tempBytes = new byte[14];
            int startPos = offset;
            System.arraycopy(reply, startPos, tempBytes, 0, 14);
            wr_key = new String(tempBytes);
            System.out.println("FUR101:WR-KEY=" + wr_key);

            startPos += 14;
            System.arraycopy(reply, startPos, tempBytes, 0, 14);
            wr_first_key = new String(tempBytes);
            System.out.println("FUR101:WR-FIRST-KEY=" + wr_first_key);

            tempBytes = new byte[62];
            startPos += 14;
            for (int i = 0; i < rows; i++) {
                System.arraycopy(reply, startPos + 62 * i, tempBytes, 0, 62);
                System.out.println(new String(tempBytes, StandardCharsets.ISO_8859_1));
                wr_com_id[i] = new String(tempBytes, 0, 7).trim();
                wr_com_name[i] = new String(tempBytes, 8, 8, "BIG5").trim();
                wr_min_price[i] = new String(tempBytes, 17, 8).trim();
                wr_up_dn[i] = new String(tempBytes, 26, 6).trim();
                wr_value[i] = new String(tempBytes, 33, 10).trim();
                wr_rate[i] = new String(tempBytes, 44, 9).trim();
                wr_stock[i] = new String(tempBytes, 54, 8).trim();
            }

            tempBytes = new byte[14];
            startPos += 62 * 12;
            for (int i = 0; i < rows; i++) {
                System.arraycopy(reply, startPos + 14 * i, tempBytes, 0, 14);
                System.out.println(new String(tempBytes, StandardCharsets.ISO_8859_1));
                wr_fee[i] = new String(tempBytes, "BIG5").trim();
            }

            startPos += 14 * 12;
            for (int i = 0; i < rows; i++) {
                System.arraycopy(reply, startPos + 14 * i, tempBytes, 0, 14);
                System.out.println(new String(tempBytes, StandardCharsets.ISO_8859_1));
                wr_up_fee[i] = new String(tempBytes, "BIG5").trim();
            }

            startPos += 14 * 12;
            for (int i = 0; i < rows; i++) {
                System.arraycopy(reply, startPos + 14 * i, tempBytes, 0, 14);
                System.out.println(new String(tempBytes, StandardCharsets.ISO_8859_1));
                wr_dn_fee[i] = new String(tempBytes, "BIG5").trim();
            }

            tempBytes = new byte[1];
            startPos += 14 * 12;
            for (int i = 0; i < rows; i++) {
                System.arraycopy(reply, startPos + i, tempBytes, 0, 1);
                System.out.println(new String(tempBytes, StandardCharsets.ISO_8859_1));
                wr_range[i] = new String(tempBytes).trim();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
