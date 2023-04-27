package ru.panic.lapayment.template.dto.crypto;

import lombok.Data;

import java.util.List;

@Data
public class MaticDto {
    public String status;
    public String message;
    public List<Result> result;
    @Data
    public static class Result {
        public String blockNumber;
        public long timeStamp;
        public String hash;
        public String nonce;
        public String blockHash;
        public String transactionIndex;
        public String from;
        public String to;
        public long value;
        public String gas;
        public String gasPrice;
        public String isError;
        public String txreceipt_status;
        public String input;
        public String contractAddress;
        public String cumulativeGasUsed;
        public String gasUsed;
        public String confirmations;
        public String methodId;
        public String functionName;
    }
}
