CREATE TABLE users
(
    id               int4         NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
    username         varchar(20)  NOT NULL UNIQUE,
    first_name       varchar(20)  NOT NULL,
    last_name        varchar(20)  NOT NULL,
    email            varchar(30)  NOT NULL UNIQUE,
    subscribes_count int4,
    followers_count  int4,
    password         varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE posts
(
    id          int4         NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
    author_id   int4 REFERENCES users (id),
    description varchar(255) NOT NULL
);

CREATE TABLE likes
(
    user_id int4,
    post_id int4,
    PRIMARY KEY (user_id, post_id),
    CONSTRAINT fk_authorities_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_authorities_posts FOREIGN KEY (post_id) REFERENCES posts (id)
);

CREATE TABLE comments
(
    id      int4                       NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
    user_id int4 references users (id) NOT NULL,
    post_id int4 references posts (id) NOT NULL,
    comment varchar(150)               NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE follows
(
    user_id_subscribed int4,
    at_user_id         int4,
    PRIMARY KEY (user_id_subscribed, at_user_id),
    CONSTRAINT fk_authorities_users FOREIGN KEY (user_id_subscribed) REFERENCES users (id),
    CONSTRAINT fk_authorities_users FOREIGN KEY (at_user_id) REFERENCES users (id)
);