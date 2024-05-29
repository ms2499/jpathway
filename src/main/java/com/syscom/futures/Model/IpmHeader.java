package com.syscom.futures.Model;

import com.google.common.primitives.Bytes;
import com.syscom.futures.CharsetTool;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class IpmHeader {
    CharsetTool cstool = new CharsetTool();
    public short replyCode;
    public short transCode;
    public short functionCode;
    public String termId;
    public String brokerId;
    public String ibNo;
    public String brokerName;
    public String telNo;
    public int toDay;
    public char _2sysFlag;
    public String _2sysBroker;
    public String _2sysIbNo;
    public char dataProtect;

    public IpmHeader(){
        replyCode = 0;
        transCode = 1;
        functionCode = 1;
        termId = "252-ZTN0國內";
        brokerId = "F905000";
        ibNo = "P00";
        brokerName = "總公司    ";
        telNo = "1001        ";
        toDay = 20240119;
        _2sysFlag = ' ';
        _2sysBroker = "F905000";
        _2sysIbNo = "P00";
        dataProtect = ' ';
    }

    public byte[] getBytes(){
        List<Byte> bytes = new ArrayList<>();
        ByteBuffer buffer;

        buffer = ByteBuffer.allocate(2);
        buffer.putShort(replyCode).flip();
        bytes.addAll(Bytes.asList(buffer.array()));
        buffer = ByteBuffer.allocate(2);
        buffer.putShort(transCode).flip();
        bytes.addAll(Bytes.asList(buffer.array()));
        buffer = ByteBuffer.allocate(2);
        buffer.putShort(functionCode).flip();
        bytes.addAll(Bytes.asList(buffer.array()));
        bytes.addAll(Bytes.asList(cstool.big52iso(termId).getBytes()));
        bytes.addAll(Bytes.asList(brokerId.getBytes()));
        bytes.addAll(Bytes.asList(ibNo.getBytes()));
        bytes.addAll(Bytes.asList(cstool.big52iso(brokerName).getBytes()));
        bytes.addAll(Bytes.asList(telNo.getBytes()));
        buffer = ByteBuffer.allocate(4);
        buffer.putInt(toDay).flip();
        bytes.addAll(Bytes.asList(buffer.array()));
        bytes.add((byte)_2sysFlag);
        bytes.addAll(Bytes.asList(_2sysBroker.getBytes()));
        bytes.addAll(Bytes.asList(_2sysIbNo.getBytes()));
        bytes.add((byte)dataProtect);

        return Bytes.toArray(bytes);
    }
}
