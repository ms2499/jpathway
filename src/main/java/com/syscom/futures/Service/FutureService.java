package com.syscom.futures.Service;

import com.syscom.futures.CharsetTool;
import com.syscom.futures.Model.FUR101;
import com.syscom.futures.Model.FUS101;
import com.syscom.futures.Model.FUS101;
import com.syscom.futures.Model.IpmHeader;
import com.tandem.tsmp.TsmpServer;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

@Service
public class FutureService {
    CharsetTool cstool = new CharsetTool();
    public Object pathsend(String svrName, short funCode, FUS101 fus101) {
        try {
            String pmonName = "$YTPW"; //pathmon name
            TsmpServer getServer = new TsmpServer(pmonName, svrName); //pathsend用物件
            byte[] greply = new byte[4096]; //pathsend完放資料的array
            IpmHeader ipm = new IpmHeader(); //COBOL那邊定義的IPM-HEADER格式
            ipm.functionCode = funCode;
            //System.out.println("funcode:"+ipm.functionCode);
//            System.out.println("getWsExchId:"+fus101.getWsExchId());
//            System.out.println("getWsCommodityId:"+new String(fus101.getWsCommodityId().getBytes("BIG5"), StandardCharsets.ISO_8859_1));
//            System.out.println("getWsFeeKind:"+fus101.getWsFeeKind());
//            System.out.println("getWsUpFeeKind:"+fus101.getWsUpFeeKind());
//            System.out.println("getWsStockNo:"+fus101.getWsStockNo());
//            System.out.println("getWsProdKing:"+fus101.getWsProdKing());

            //Debug用
//            Field[] fields = fus101.getClass().getFields();
//            for (Field field : fields) {
//                System.out.println(field.toString() + " = " + field.get(fus101));
//            }

            byte[] ipmHeader = ipm.getBytes();
            byte[] fus101Send = fus101.getSendData();
            byte[] request = new byte[ipmHeader.length + fus101Send.length];
            System.arraycopy(ipmHeader, 0, request, 0, ipmHeader.length);
            System.arraycopy(fus101Send, 0, request, ipmHeader.length, fus101Send.length);
            getServer.service(request, request.length, greply); //pathsend

            //處理pathsend回來的資料
            FUR101 reply = new FUR101();
            int offset = 80;
            reply.setValue(greply, offset);

            return reply;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
