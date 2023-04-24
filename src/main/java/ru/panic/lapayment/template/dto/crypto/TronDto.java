package ru.panic.lapayment.template.dto.crypto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TronDto {
    private List<Data> data;
    private boolean success;
    private Meta meta;
    @lombok.Data
    @Getter
    @NoArgsConstructor
    public static class Data {
        private List<Ret> ret;
        private String txID;
        private long net_usage;
        private String raw_data_hex;
        private int net_fee;
        private int energy_usage;
        private long blockNumber;
        private long block_timestamp;
        private int energy_fee;
        private int energy_usage_total;
        private RawData raw_data;
        private List<Object> internal_transactions;
        @lombok.Data
        @NoArgsConstructor
        protected static class Ret {
            private String contractRet;
            private int fee;

        }
        @lombok.Data
        @NoArgsConstructor
        public static class RawData {
            private List<Contract> contract;
            private String ref_block_bytes;
            private String ref_block_hash;
            private long expiration;
            @lombok.Data
            @NoArgsConstructor
            public static class Contract {
                private Parameter parameter;
                private String type;
                @lombok.Data
                @NoArgsConstructor
                public static class Parameter {
                    private Value value;
                    private String type_url;
                    @lombok.Data
                    @NoArgsConstructor
                    public static class Value {
                        private long amount;
                        private String owner_address;
                        private String to_address;

                    }
                }

            }
        }

    }
    @lombok.Data
    @NoArgsConstructor
    protected static class Meta {
        private long at;
        private int page_size;
    }

}
