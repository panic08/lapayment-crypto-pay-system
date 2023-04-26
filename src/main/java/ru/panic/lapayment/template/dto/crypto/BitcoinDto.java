package ru.panic.lapayment.template.dto.crypto;

import lombok.Data;

import java.util.List;
@Data

public class BitcoinDto {
    private String hash160;
    private String address;
    private int n_tx;
    private int n_unredeemed;
    private long total_received;
    private long total_sent;
    private long final_balance;
    private List<TxDto> txs;
    @Data
    public static class TxDto {
        private String hash;
        private int ver;
        private int vin_sz;
        private int vout_sz;
        private int size;
        private int weight;
        private long fee;
        private String relayed_by;
        private int lock_time;
        private long tx_index;
        private boolean double_spend;
        private int time;
        private int block_index;
        private int block_height;
        private List<InputDto> inputs;
        private List<OutputDto> out;
        private long result;
        private long balance;
        @Data
        public static class InputDto {
            private long sequence;
            private String witness;
            private String script;
            private int index;
            private PrevOutDto prev_out;
            @Data
            public static class PrevOutDto {
                private String addr;
                private int n;
                private String script;
                private List<SpendingOutpointDto> spending_outpoints;
                private boolean spent;
                private long tx_index;
                private int type;
                private long value;
            }
        }
        @Data
        public static class OutputDto {
            private int type;
            private boolean spent;
            private long value;
            private List<SpendingOutpointDto> spending_outpoints;
            private int n;
            private long tx_index;
            private String script;
            private String addr;
        }
        @Data
        public static class SpendingOutpointDto {
            private int n;
            private long tx_index;
        }
    }
}
