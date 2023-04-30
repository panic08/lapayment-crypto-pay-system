CREATE TABLE IF NOT EXISTS Payments(
     id serial PRIMARY KEY,
     merchantId text NOT NULL,
     oauth text NOT NULL,
     amount double precision NOT NULL,
     tron_amount double precision NOT NULL,
     bitcoin_amount double precision NOT NULL,
     ethereum_amount double precision NOT NULL,
     matic_amount double precision NOT NULL,
     currency text NOT NULL,
     status text NOT NULL,
     timestamp BIGINT NOT NULL
);
