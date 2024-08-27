create table users
(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name  varchar(100),
    email  varchar(100),
    password  varchar(50),
    created timestamp,
    modified timestamp,
    lastlogin timestamp,
    token varchar(4000),
    active BOOLEAN
);