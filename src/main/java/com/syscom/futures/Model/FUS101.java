package com.syscom.futures.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.primitives.Bytes;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FUS101 {
    @JsonProperty("wsExchId")
    public String wsExchId;
    @JsonProperty("wsCommodityId")
    public String wsCommodityId;
    @JsonIgnore
    public char wsRange;
    @JsonProperty("wsFeeKind")
    public String wsFeeKind;
    @JsonProperty("wsUpFeeKind")
    public String wsUpFeeKind;
    @JsonProperty("wsDnFeeKind")
    public String wsDnFeeKind;
    @JsonProperty("wsStockNo")
    public String wsStockNo;
    @JsonProperty("wsProdKing")
    public char wsProdKing;
    @JsonIgnore
    public String wsSpoolNoIb;
    @JsonIgnore
    public char wsSpoolNo4;
    @JsonProperty("wsKey")
    public String wsKey;
    @JsonIgnore
    public String wsCopies;

    public void setWsExchId(String wsExchId) {
        this.wsExchId = String.format("%-7s", wsExchId);
    }

    public void setWsCommodityId(String wsCommodityId) {
        this.wsCommodityId = String.format("%-7s", wsCommodityId);
    }

    public void setWsFeeKind(String wsFeeKind) {
        this.wsFeeKind = String.format("%-7s", wsFeeKind);
    }

    public void setWsUpFeeKind(String wsUpFeeKind) {
        this.wsUpFeeKind = String.format("%-7s", wsUpFeeKind);
    }

    public void setWsDnFeeKind(String wsDnFeeKind) {
        this.wsDnFeeKind = String.format("%-7s", wsDnFeeKind);
    }

    public void setWsStockNo(String wsStockNo) {
        this.wsStockNo = String.format("%-7s", wsStockNo);
    }

    public void setWsProdKing(char wsProdKing) {
        this.wsProdKing = (wsProdKing == '\u0000') ? ' ' : String.format("%-1s", wsProdKing).charAt(0);
    }

    public void setWsKey(String wsKey) {
        this.wsKey = (wsKey == null) ? "              " : String.format("%-14s", wsKey);
    }

    public FUS101(){
        wsExchId = "F000000";
        wsCommodityId = "       ";
        wsRange = ' ';
        wsFeeKind = "       ";
        wsUpFeeKind = "       ";
        wsDnFeeKind = "       ";
        wsStockNo = "       ";
        wsProdKing = ' ';
        wsSpoolNoIb = "P00";
        wsSpoolNo4 = '2';
        wsKey = "              ";
        wsCopies = "01";
    }

    public byte[] getSendData() {
        List<Byte> bytes = new ArrayList<>();

        bytes.addAll(Bytes.asList(wsExchId.getBytes()));
        bytes.addAll(Bytes.asList(wsCommodityId.getBytes()));
        bytes.add((byte) wsRange);
        bytes.addAll(Bytes.asList(wsFeeKind.getBytes()));
        bytes.addAll(Bytes.asList(wsUpFeeKind.getBytes()));
        bytes.addAll(Bytes.asList(wsDnFeeKind.getBytes()));
        bytes.addAll(Bytes.asList(wsStockNo.getBytes()));
        bytes.add((byte) wsProdKing);
        bytes.addAll(Bytes.asList(wsSpoolNoIb.getBytes()));
        bytes.add((byte) wsSpoolNo4);
        bytes.addAll(Bytes.asList(wsKey.getBytes()));
        bytes.addAll(Bytes.asList(wsCopies.getBytes()));

        return Bytes.toArray(bytes);
    }
}
