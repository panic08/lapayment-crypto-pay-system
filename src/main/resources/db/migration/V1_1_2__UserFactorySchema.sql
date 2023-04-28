CREATE TABLE IF NOT EXISTS UserFactory(
     id serial PRIMARY KEY,
     merchantId text NOT NULL,
     principal text NOT NULL,
     apikey text NOT NULL,
     urlBack text NOT NULL,
     requestMethod text NOT NULL
);
