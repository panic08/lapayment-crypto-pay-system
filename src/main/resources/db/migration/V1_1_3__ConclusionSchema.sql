CREATE TABLE IF NOT EXISTS Conclusions(
     id serial PRIMARY KEY,
     principal text NOT NULL,
     amount double precision NOT NULL,
     wallet text NOT NULL,
     cryptocurrency text NOT NULL,
     status text NOT NULL
);
