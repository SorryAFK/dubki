CREATE TABLE IF NOT EXISTS users
(
    id                         int4         NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
    username                   varchar(20)  NOT NULL UNIQUE,
    email                      varchar(30)  NOT NULL UNIQUE,
    role                       varchar(20)  NOT NULL,
    password                   varchar(255) NOT NULL,
    is_enabled                 boolean,
    is_account_non_expired     boolean,
    is_account_non_locked      boolean,
    is_credentials_non_expired boolean,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_data
(
    user_id          int4 PRIMARY KEY,
    firstname        varchar(20)  NOT NULL,
    lastname         varchar(20)  NOT NULL,
    avatar           varchar(100) NULL,
    dob              DATE         NOT NULL,
    subscribes_count int4,
    followers_count  int4,
    time_of_creating TIMESTAMP WITHOUT TIME ZONE,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS email_codes
(
    id int4 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code_owner_id int4,
    code          varchar(6) NOT NULL UNIQUE,
    FOREIGN KEY (code_owner_id) references users (id)
);

CREATE TABLE IF NOT EXISTS chats
(
    id   int4        NOT NULL GENERATED ALWAYS AS IDENTITY,
    name varchar(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS chat_members
(
    user_id     int4 PRIMARY KEY,
    chat_id     int4,
    is_approved bool,
    is_admin    bool,
    CONSTRAINT fk_chat_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_chat FOREIGN KEY (chat_id) REFERENCES chats (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS messages
(
    id        int4                       NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
    chat_id   int4 references chats (id) NOT NULL,
    message   varchar(300)               NOT NULL,
    timestamp TIMESTAMP WITHOUT TIME ZONE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS posts
(
    id          int4         NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
    author_id   int4 REFERENCES users (id),
    description varchar(255) NOT NULL,
    likes_count int4         NOT NULL,
    time        TIMESTAMP WITHOUT TIME ZONE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS likes
(
    user_id int4,
    post_id int4,
    PRIMARY KEY (user_id, post_id),
    CONSTRAINT fk_authorities_users FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_authorities_posts FOREIGN KEY (post_id) REFERENCES posts (id)
);

CREATE TABLE IF NOT EXISTS comments
(
    id      int4                       NOT NULL UNIQUE GENERATED ALWAYS AS IDENTITY,
    user_id int4 references users (id) NOT NULL,
    post_id int4 references posts (id) NOT NULL,
    comment varchar(150)               NOT NULL,
    time    TIMESTAMP WITHOUT TIME ZONE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS follows
(
    user_id_subscribed int4,
    at_user_id         int4,
    PRIMARY KEY (user_id_subscribed, at_user_id),
    CONSTRAINT fk_follows_subscribed FOREIGN KEY (user_id_subscribed) REFERENCES users (id),
    CONSTRAINT fk_follows_user FOREIGN KEY (at_user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS refresh_tokens
(
    id             int4         NOT NULL GENERATED ALWAYS AS IDENTITY,
    user_id        int4 UNIQUE,
    refresh_tokens varchar(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_participating_users FOREIGN KEY (user_id) REFERENCES users (id)
);
